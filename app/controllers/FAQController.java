package controllers;

import models.FAQ;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.faq.*;

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
		String question = form.bindFromRequest().get().question;
		String answer = form.bindFromRequest().get().answer;
		FAQ.createFAQ(question, answer);
		
		return ok(NewFAQ.render(session("name"), "..created")); 
	}
	
	public static Result editFAQView(int id){
		
		FAQ question = FAQ.find(id);
		return ok(EditFAQ.render(session("name"), question));
	}
	
	//TODO admin check, error handling, flash message..
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
	public static Result deleteFAQ(int id){
		FAQ.delete(id);
		return ok(FAQview.render(session("name"), FAQ.all()));
	}
	
	

}
