package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class FAQ extends Model {
	
	@Id
	public int id;
	
	@Required
	public String title;
	
	@Required
	public String content;
	
	static Finder<Integer, FAQ> find = new Finder<Integer, FAQ>(Integer.class, FAQ.class);
	
	
	public FAQ(String title, String content){
		this.title = title;
		this.content = content;
	}
	
	public static int createFAQ(String title, String content){
		FAQ newFaq = new FAQ(title, content);
		newFaq.save();
		return newFaq.id;
	}
	
	public static List<FAQ> all(){
		return find.all();
	}

}
