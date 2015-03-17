package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class FAQ extends Model {
	
	@Id
	public int id;
	
	@Required
	public String question;
	
	@Required
	@Column(columnDefinition = "TEXT")
	public String answer;
	
	static Finder<Integer, FAQ> find = new Finder<Integer, FAQ>(Integer.class, FAQ.class);
	
	
	public FAQ(String question, String answer){
		this.question = question;
		this.answer = answer;
	}
	
	public static int createFAQ(String question, String answer){
		FAQ newFaq = new FAQ(question, answer);
		newFaq.save();
		return newFaq.id;
	}
	
	public static List<FAQ> all(){
		List<FAQ> faqs = find.findList();
		return faqs;
	}
	
	public static boolean checkByTitle(String question){
		return find.where().eq("question", question).findUnique() != null;
	}
	
	public static FAQ find(int id){
		return find.byId(id);
	}
	
	public static void update(FAQ faq){
		faq.save();
	}
	
	public static void delete(int id){
		find.byId(id).delete();
	}
	
}
