package controllers;

import models.*;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	
	static String message = "Welcome ";
	static String bitName = "bitCoupon";

	//static Form<PublicUser> input = new Form<PublicUser>(PublicUser.class);
	
	static Form<User> newUser = new Form<User>(User.class);
	static Form<Login> login = new Form<Login>(Login.class);

    public static Result index() {
    	String name = session("name");
    	if (name == null){
    		name = "Public user";	
    		return ok(index.render(message, name )); 
    	} else {
    		return redirect("/");
    	}
    }
    
    public static Result registration(){ 	
    	return ok(registration.render(bitName));
    }
    
    public static Result register(){
    	String username = newUser.bindFromRequest().get().username;
    	String mail = newUser.bindFromRequest().get().email;
    	String password = newUser.bindFromRequest().get().password;    	
    	session("name", username);
    	User.create(username, mail, password);
    	  return ok(userIndex.render(message, username ));  
    	
    }
    
    
  public static Result loginpage(){
    		return ok(Loginpage.render(bitName, "Login"));   	
    }
  
  public static Result login(){
	  String mail = login.bindFromRequest().get().email;
	  String password = login.bindFromRequest().get().password;  
	  User u = new User(null, mail, password);
	  if ( u.verify(u.email, u.password) == true ){
		  return ok(userIndex.render(message, mail )); 
	  }
	  
	  return ok(Loginpage.render(bitName,"Invalid email or password"));  //ako ne prodje username 
	  
  }
  
  public static Result logout() {
	  session().clear();
	    flash("OK!", "You've been logged out");
	    return redirect(
	        routes.Application.index()
	    );
  }

        
  

}
