package models;

import java.util.List;

import javax.persistence.*;

import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;


/**
 * 
 * Entity class for the User.
 * Creates a table in the database with all of
 * the properties
 *
 */

@Entity
public class User extends Model {
	
	@Id
	public int id;
	
	@Required
	@MinLength(4)
	public String username;
	@Required
	public String email;
	@Required
	@MinLength(6)
	public String password;

	
	public User(String username, String email, String password){
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	static Finder<Integer, User> find = new Finder<Integer,User>(
			Integer.class, User.class);
	
//	public static void create(Customer c){
//		c.save();
//	}
	
	
	public static void create(String username,String email, String password){
		new User(username, email, password).save();
	}
	
	public static List<User> all(String username){
		return find.where().eq("username", username).findList();
	}
	
	/**
	 * Login verification
	 * Verifies if the email and password exists by checking in the database
	 * @param mail
	 * @param password
	 * @return boolean true or false
	 */
	public static boolean verifyLogin(String mail, String password){
		List<User> us = find.where().eq("email", mail).findList();
		List<User> pas = find.where().eq("password", password).findList();
		if ( us.isEmpty() ){
			return false;
		} else if (pas.isEmpty()){
			
			return false;
		} else return true;
			
	}
	
	/**
	 * Checks if there already exists a user with given username or email
	 * and blocks registration if does.
	 * @param username
	 * @param email
	 * @return boolean true or false
	 */
	public static boolean verifyRegistration(String username, String email){
		List<User> usname = find.where().eq("username", username).findList();
		List<User> mail = find.where().eq("email", email).findList();
		if ( usname.isEmpty() && mail.isEmpty() ){
			return true;
			
		} else return false;
			
	}
	
	
	/* 
	 * TODO
	 * return a username by given mail  
	 */
//	public static List<User> findByMail(String mail){
//		User ll = find.where().eq("email", mail).
//		
//		return find.where().eq("email", mail).findList();
//	}
	
	
	/*
	 * Delete user by id
	 */
	public static void delete(int id){
		find.byId(id).delete();
	}

	/*
	 * Find user by ID
	 */
	public static User find(int id) {
		return find.byId(id);
	}

}
