package controllers;

import java.util.Date;
import java.util.List;



import com.avaje.ebeaninternal.server.persist.BindValues.Value;

import helpers.HashHelper;
import helpers.MailHelper;
import play.*;
import play.api.mvc.Session;
import play.data.Form;
import play.mvc.*;
import views.html.*;
import models.*;

public class UserController extends Controller {

	/* TODO move all messages to conf */
	static String message = "Welcome ";
	static String bitName = "bitCoupon";
	static String name = null;

	static Form<User> userForm = new Form<User>(User.class);

	/**
	 * @return Renders the registration view
	 */
	public static Result signup() {
		return ok(signup.render("Registration", "Username", "Email"));
	}

	/**
	 * Pulls the input form from the registration form fields and creates a new
	 * user in the Database.
	 * 
	 * @return redirects to the index page with welcome,
	 * or renders the page repeatedly if any error occurs
	 */
	public static Result register() {
		
		if (userForm.hasErrors()) {
			return redirect("/signup ");
		}

		String username = userForm.bindFromRequest().get().username;
		String mail = userForm.bindFromRequest().get().email;
		String password = userForm.bindFromRequest().get().password;
		String hashPass = HashHelper.createPassword(password);
		String confPass = userForm.bindFromRequest().field("confirmPassword")
				.value();

		if (username.length() < 4 || username.equals("Username")) {
			return ok(signup.render(
					"Enter a username with minimum 4 characters !", null, mail));
		} else if (mail.equals("Email")) {
			return ok(signup.render("Email required for registration !",
					username, null));
		} else if (password.length() < 6) {
			return ok(signup.render(
					"Enter a password with minimum 6 characters !", username,
					mail));
		} else if (!password.equals(confPass)) {
			return ok(signup.render("Passwords don't match, try again ",
					username, mail));
		}
		
		/*
		 * Creating new user if the username or mail is free for use, and there
		 * are no errors
		 */
		else if (User.verifyRegistration(username, mail) == true) {
			/*session().clear();
			session("name", username);*/

			long id = User.createUser(username, mail, hashPass, false);
			String verificationEmail = EmailVerification.addNewRecord(id);
			MailHelper.send(mail, "Click on the link below to verify your e-mail adress <br>"
					+ "http://localhost:9000/verifyEmail/" + verificationEmail);
			//User cc = User.getUser(mail);
			
			return ok(Loginpage.render("A verification mail has been sent to your email address"));

		} else {
			return ok(signup.render("Username or email allready exists!",
					username, mail));
		}

	}
	
	/**
	 * Method sends the current user to the userUpdate() method
	 * @return Renders the user update view for editing profile
	 */
	public static Result userUpdateView() {
		User u = User.find(session("name"));
		return ok(userUpdate.render(u, "Update account"));
	}

	/**
	 * Update user by getting the values from the form in the
	 * userUpdate view. This method is for every user that is editing
	 * his/her own profile.
	 * 
	 * @param useName received from the userUpdateView() method
	 * @return Result renders the update view with info messages
	 * according to update success or fail
	 */
	public static Result updateUser(String useName) {

		if (userForm.hasErrors()) {
			return redirect("/updateUser ");
		}
		
		String username = userForm.bindFromRequest().field("username").value();
		String email = userForm.bindFromRequest().field("email").value();
		String oldPass = userForm.bindFromRequest().field("password").value();
		String newPass = userForm.bindFromRequest().field("newPassword").value();

		User cUser = User.find(useName);
		cUser.username = username;
		cUser.email = email;

		if (HashHelper.checkPass(oldPass, cUser.password) == true) {
			cUser.password = HashHelper.createPassword(newPass);
			cUser.save();
			return ok(userUpdate.render(cUser, "Update Successful"));
		} else {
			return ok(userUpdate.render(cUser, "Incorrect Password"));
		}

	}
	
	/**
	 * Receives a user id, initializes the user, and renders the adminEditUser
	 * passing the user to the view
	 * @param id of the User (long)
	 * @return Result render adminEditUser
	 */
	public static Result adminEditUserView(long id){
		User u = User.find(id);
		return ok(adminEditUser.render(u, "Update user"));
	}
	
	/**
	 * Updates the user from the Admin control.
	 * @param id of the user to update
	 * @return Result render the vies
	 */
	public static Result adminUpdateUser(long id){
		
		if (userForm.hasErrors()) {
			return redirect("/@editUser/:"+id); //provjeriti
		}

		String username = userForm.bindFromRequest().field("username").value();
		String email = userForm.bindFromRequest().field("email").value();
		String newPass = userForm.bindFromRequest().field("newPassword")
				.value();
		String admin = userForm.bindFromRequest().field("isAdmin").value();

		User cUser = User.find(id);
		cUser.username = username;
		cUser.email = email;
		/* if admin doesn't explicitly change the users password, it stays intact */
		if (newPass.length() > 0) { cUser.password = HashHelper.createPassword(newPass); }
	    cUser.isAdmin = Boolean.parseBoolean(admin);
	    cUser.updated = new Date();
		cUser.save();
		return ok(adminEditUser.render(cUser, "Update successful!"));
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	public static Result controlPanel(long id) {

		User u = User.find(id);
		if (!u.username.equals(session("name"))) {
			return redirect("/");
		}

		return ok(adminPanel.render(u.username, null));

	}

	/**
	 * Renders the profile page view
	 * @param username
	 * @return Result
	 */
	public static Result profilePage(String username) {
		User u = User.find(username); 
		if (!u.username.equals(session("name"))) {
			return redirect("/");
		}

		return ok(profile.render(u));
	} 
	
	/**
	 * Renders the user list view.
	 * Lists all user from the database
	 *
	 * @return Result
	 */
	public static Result listUsers(){
		
		return ok( userList.render(session("name"),User.all()) );
	}
	
	/**
	 * Delete user by id.
	 * Delete is possible only for own deletion, or if it's
	 * done by Admin. 
	 * @param id Long
	 * @return Result renders the same view
	 */
	public static Result deleteUser(Long id){
	    User user=User.find(id);
	    List<User> adminList=User.findAdmins(true);
		User currentUser = Sesija.getCurrentUser(ctx());
		if (currentUser.id == id || Sesija.adminCheck(ctx())){
			if((user.isAdmin==true)&&(adminList.size()>1)){
			User.delete(id);
			if(currentUser.id==id){
				return redirect("/signup ");
			}
			}
			else {
				return ok( userList.render(session("name"),User.all()) );
			}
		}
		return ok( userList.render(session("name"),User.all()) );
		

	}
	
	/**
	 * Compare if the verification period is expired and send
	 * verification mail to user e-mail adress
	 * @param id - verification mail
	 * @return redirect to the login view
	 */
	public static Result verifyEmail(String id){
		EmailVerification recordToUpdate = EmailVerification.find(id);
		String message = "";
		if(recordToUpdate.createdOn.compareTo(new Date()) < 0){
			EmailVerification.updateRecord(recordToUpdate);
			message = "You're e-mail is now verified. To login click on the button below";
		}
		else{
			message = "Verification period is expired. If you want to receive a new verification mail, click on the button 'Resend'";
		}		
		return ok(verifyEmail.render(message));
	}

}
