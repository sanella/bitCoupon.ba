package controllers;

import models.FAQ;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class FAQController extends Controller{
	

	public static Result showFAQ(){
		
		return ok(FAQview.render(FAQ.all()));
	}
	
	public static Result addFAQ(){
		
		Form<FAQ> form = new Form<FAQ>(FAQ.class); 
		return TODO;
	}

}
