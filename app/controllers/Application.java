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
    	}else{
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
    	return ok(index.render(message, username )); 
    	
    }
    
 
    
  public static Result loginpage(){
  	  		
    		return ok(Loginpage.render(bitName));   	
    }
  
  public static Result login(){
	  String username = login.bindFromRequest().get().username;
	  String password = login.bindFromRequest().get().password;  
	  User u = new User(username, null, password);
	  if ( u.verify(u.username, u.password) == true ){
		  return ok(index.render(message, username )); 
	  }
	  
	  return ok(loginFailed.render(bitName));   //ako ne prodje username 
	  
  }

        
  

}
