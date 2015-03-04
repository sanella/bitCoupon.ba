package controllers;

import helpers.HashHelper;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;
import models.*;

public class UserController extends Controller {

	static String message = "Welcome ";
	static String bitName = "bitCoupon";
	static String name = null;

	static Form<User> newUser = new Form<User>(User.class);
	static Form<Login> login = new Form<Login>(Login.class);

	/**
	 * @return Renders the registration view
	 */
	public static Result signup() {
		return ok(signup.render("Registration", "Username", "Email"));
	}

	/**
	 * Pulls the input form from the three registration fields and creates a new
	 * user in the Database.
	 * 
	 * @return redirects to the index page with welcome.
	 */
	public static Result register() {

		 String username = newUser.bindFromRequest().get().username;
		 String mail = newUser.bindFromRequest().get().email;
		 String password = newUser.bindFromRequest().get().password;
		 String hashPass= HashHelper.createPassword(password);
		 String confPass = newUser.bindFromRequest().field("confirmPassword").value();
		 
		 if( username.length() < 4 || username.equals("Username")){
		 return ok(signup.render(
		 "Enter a username with minimum 4 characters !",null, mail ));
		 }
		 else if ( mail.equals("Email")){
		 return ok(signup.render(
		 "Email required for registration !",username, null ));
		 }
		 else if ( password.length() < 6 ){
		 return ok(signup.render(
		 "Enter a password with minimum 6 characters !",username, mail ));
		 }
		 else if ( !password.equals(confPass)){
			 return ok(signup.render(
					 "Passwords don't match, try again ",username, mail ));
		 }
		
		 /* Creating new user if the username or mail is free for use, and
		 there are no errors */
		
		 else if ( User.verifyRegistration(username, mail) == true){
		 session("name", username);
		 long id = User.createUser(username, mail, hashPass);
		 return redirect("/user/" + id);
		
		 } else {
		 return ok(signup.render("Username or email allready exists!",
		 username, mail ));
		 }
	}

	/**
	 * 
	 * @return renders the loginpage view
	 */
	public static Result loginpage() {
		return ok(Loginpage.render(bitName, "Login to your account"));
	}

	/**
	 * Pulls the value from two login fields and verifies if the mail exists and
	 * the password is valid by calling the verifyLogin() method from the User
	 * class.
	 * 
	 * @return redirects to index if the verification is ok, or reloads the
	 *         login page with a warning message.
	 */
	public static Result login() {
		String mail = login.bindFromRequest().get().email;
		String password = login.bindFromRequest().get().password;
		
		if (mail.isEmpty() || password.length() < 6) {
			return ok(Loginpage.render(bitName, "Login to your account"));
		}

		if (User.verifyLogin(mail, password) == true) {
			session("name", mail);
			return redirect("/user/" + User.getId(mail));
		}

		return ok(Loginpage.render(bitName, "Invalid email or password"));

	}

	/**
	 * Clears the session
	 * 
	 * @return redirects to index
	 */
	public static Result logout() {

		session().clear();
		flash("OK!", "You've been logged out");

		return redirect(routes.Application.index());
	}
	
	
	public static Result show(long id){
		User u = User.find(id);
		return ok(userIndex.render(message, u.username, null));
	}

}
