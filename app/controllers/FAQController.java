package controllers;

import helpers.AdminFilter;
import models.FAQ;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.admin.faq.*;

public class FAQController extends Controller{
	
	
	
	public static Result showFAQ(){
		
		return ok(FAQview.render(session("name"), FAQ.all()));
	}
	
	//TODO flash message
	@Security.Authenticated(AdminFilter.class)
	public static Result addFAQView(){
		
		return ok(NewFAQ.render(session("name"), "infoMSG")); //TODO
	}
	
	//TODO  error handling
	@Security.Authenticated(AdminFilter.class)
	public static Result addFAQ(){
		
		Form<FAQ> form = new Form<FAQ>(FAQ.class); 
		String question = form.bindFromRequest().get().question;
		String answer = form.bindFromRequest().get().answer;
		FAQ.createFAQ(question, answer);
		
		return ok(NewFAQ.render(session("name"), "..created")); 
	}
	
	@Security.Authenticated(AdminFilter.class)
	public static Result editFAQView(int id){
		
		FAQ question = FAQ.find(id);
		return ok(EditFAQ.render(session("name"), question));
	}
	
	//TODO  error handling, flash message..
	@Security.Authenticated(AdminFilter.class)
	public static Result updateFAQ(int id){
		Form<FAQ> form = new Form<FAQ>(FAQ.class);
		String question = form.bindFromRequest().get().question;
		String answer = form.bindFromRequest().get().answer;
		FAQ FAQToUpdate = FAQ.find(id);
		FAQToUpdate.question = question;
		FAQToUpdate.answer = answer;
		FAQ.update(FAQToUpdate);
		
		return ok(EditFAQ.render(session("name"), FAQToUpdate));
	}
	
	//TODO flash message
	@Security.Authenticated(AdminFilter.class)
	public static Result deleteFAQ(int id){
		FAQ.delete(id);
		return ok(FAQview.render(session("name"), FAQ.all()));
	}
	
	

}
