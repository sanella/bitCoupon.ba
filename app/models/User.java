package models;

import java.util.List;

import javax.persistence.*;

import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

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
	
	public static boolean verify(String username, String password){
		List<User> us = find.where().eq("username", username).findList();
		List<User> pas = find.where().eq("password", password).findList();
		if ( us.isEmpty() ){
			return false;
		} else if (pas.isEmpty()){
			
			return false;
		} else return true;
	
			 //find.where().contains("username", username) != null;
	
	}
	
	public static void delete(int id){
		find.byId(id).delete();
	}

}
