package controllers;

import java.text.ParseException;

import models.Category;
import models.Coupon;
import play.data.Form;
import play.mvc.Result;
import views.html.*;
import play.mvc.Controller;

public class CategoryController extends Controller {

	static Form<Category> categoryForm = new Form<Category>(Category.class);
	
	public static Result addCategoryView(){
		
		return ok(categoryPanel.render(session("name"), null));
	}
	
	public static Result addCategory() {

		if (categoryForm.hasErrors()) {
			return redirect("/categoryPanel");
		}

		// TODO handle invalid inputs

		String name = categoryForm.bindFromRequest().field("name").value();
		if (name.length() < 4) {
			return ok(categoryPanel.render(session("name"), "Name must be 4 characters long"));

		}
		if(name.length()>120){
			return ok(categoryPanel.render(session("name"),"Name must be max 120 characters long"));
		}
		
		String image = categoryForm.bindFromRequest().field("image").value();
		
		Category.createCategory(name,image);
		
		return ok(categoryPanel.render( session("name"), "Category \"" + name
				+ "\" added"));
	}
	
	public static Result deleteCategory(long id) {
		Category.delete(id);
		return redirect("/");
	}
	
}
