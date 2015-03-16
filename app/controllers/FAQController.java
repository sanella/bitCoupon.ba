package controllers;

import models.FAQ;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class FAQController extends Controller{
	
	
	
	public static Result showFAQ(){
		
		return ok(FAQview.render(session("name"), FAQ.all()));
	}
	
	//TODO admin check
	public static Result addFAQView(){
		
		return ok(NewFAQ.render(session("name"), "infoMSG")); //TODO
	}
	
	//TODO admin check, error handling
	public static Result addFAQ(){
		
		Form<FAQ> form = new Form<FAQ>(FAQ.class); 
		String title = form.bindFromRequest().get().title;
		String content = form.bindFromRequest().get().content;
		FAQ.createFAQ(title, content);
		
		return ok(NewFAQ.render(session("name"), "..created")); 
	}

}
