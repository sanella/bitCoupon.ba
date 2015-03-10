package controllers;

import models.*;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	
	static String loginMsg = "Login to your account";
	static String name = null;


	/**
	 * 
	 * @return redirects to index according to session name
	 */
	public static Result index() {
		name = session("name");
		if (name == null) {
			//name = "Public user";
			return ok(index.render(null, Coupon.all()));
		} else {
			User currentUser = User.find(name);
			return ok(index.render(currentUser, Coupon.all()));
		}
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
		Form<Login> login = new Form<Login>(Login.class);
		if (login.hasGlobalErrors()) {
			return ok(Loginpage.render(loginMsg));
		}
		String mail = login.bindFromRequest().get().email;
		String password = login.bindFromRequest().get().password;

		if (mail.isEmpty() || password.length() < 6) {
			return ok(Loginpage.render(loginMsg));
		}

		if (User.verifyLogin(mail, password) == true) {
			User cc = User.getUser(mail);
			session().clear();
			session("name", cc.username);
			 return ok(index.render(cc, Coupon.all()));
		}

		return ok(Loginpage.render("Invalid email or password"));

	}

	/**
	 * 
	 * @return renders the loginpage view
	 */
	public static Result loginpage() {
		return ok(Loginpage.render(loginMsg));
	}

	/**
	 * Clears the session
	 * 
	 * @return redirects to index
	 */
	public static Result logout() {

		session().clear();
		return redirect("/");
	}

}
