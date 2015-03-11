package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.Coupon;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class CouponController extends Controller {

	static Form<Coupon> couponForm = new Form<Coupon>(Coupon.class);
	

	
	
//	/**
//	 * Shows view for adding coupon
//	 * 
//	 * @return redirect to create coupon view
//	 */
//	public static Result createCouponView() {
//		// TODO
//		return redirect("/");// todo//ok(adminPanel.render("Create your coupon",
//								// couponForm));
//	}
	
	
	public static Result couponControl(){
		
		return ok(couponPanel.render(session("name"), null));
	}

	/**
	 * First check if coupon form have errors. If have, return us to create
	 * coupon view, else creates new coupon.
	 * 
	 * @return redirect to create coupon view
	 * @throws ParseException 
	 */
	public static Result addCoupon() throws ParseException {

		if (couponForm.hasErrors()) {
			return redirect("/");// todo
		}

		// TODO handle invalid inputs

		String name = couponForm.bindFromRequest().field("name").value();
		if (name.length() < 4) {
			return ok(couponPanel.render(session("name"), null));

		}
		
		double price;
		String strPrice = couponForm.bindFromRequest().field("price").value();
		strPrice = strPrice.replace(",", ".");
		try{
			price = Double.valueOf(strPrice);
		} catch (NumberFormatException e){
			//TODO Logger(e);
			return ok(couponPanel.render(session("name"), "Enter a valid price"));
		}
		
		
		String dateExpire = couponForm.bindFromRequest().field("dateExpire").value();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date date = formatter.parse(dateExpire);
		
		String picture = couponForm.bindFromRequest().field("picture").value();
		String category = couponForm.bindFromRequest().field("category").value();
		String description = couponForm.bindFromRequest().field("description").value();
		String remark = couponForm.bindFromRequest().field("remark").value();
		long couponID = Coupon.createCoupon(name, price, date, picture, category,
				description, remark);


		return ok(couponPanel.render( session("name"), "Coupon \"" + name
				+ "\" added"));
	}

	/**
	 * Finds certain coupon
	 * 
	 * @return
	 */
	public static Result find() {
		return TODO;
	}

	public static Result viewCoupon(int id) {
		Coupon coupon = Coupon.find(id);
		return redirect("/");// todo//ok(viewCoupon.render(coupon, couponForm));
	}

	/**
	 * Finds coupon using id and shows it
	 * @param id - Coupon id
	 * @return redirect to the Coupon view
	 */
	public static Result showCoupon(long id) {
		Coupon current = Coupon.find(id);
		User cUser = User.find(session("name"));
		return ok(coupontemplate.render(session("name"),cUser, current));
	}

	/**
	 * Delete coupon using id
	 * @param id - Coupon id
	 * @return redirect to the view of all existing coupons
	 */
	public static Result deleteCoupon(long id) {
		Coupon.delete(id);
		return redirect("/");
	}
	
	public static Result editCoupon(long id){
	Coupon coupon=Coupon.find(id);
		return ok(updateCouponView.render(coupon, null));
	
	}
	
	public static Result updateCoupon(long id){
		Coupon coupon=Coupon.find(id);
		
		if (couponForm.hasErrors()) {
			return redirect("/");
		}

		// TODO handle invalid inputs

		coupon.name = couponForm.bindFromRequest().field("name").value();
		if (coupon.name.length() < 4) {
			return ok(updateCouponView.render(coupon, "Name must be minimal 4 characters"));		}

		String strPrice = couponForm.bindFromRequest().field("price").value();
		strPrice = strPrice.replace(",", ".");
		try{
			coupon.price = Double.valueOf(strPrice);
		} catch (NumberFormatException e){
			//TODO Logger(e);
			return ok(updateCouponView.render(coupon, null));
		}
	//	coupon.dateCreated = couponForm.bindFromRequest().field("dateCreated").value();
	//	coupon.dateExpire = couponForm.bindFromRequest().field("dateExpire").value();
		coupon.picture = couponForm.bindFromRequest().field("picture").value();
		coupon.category = couponForm.bindFromRequest().field("category").value();
		coupon.description = couponForm.bindFromRequest().field("description").value();
		coupon.remark = couponForm.bindFromRequest().field("remark").value();
		Coupon.updateCoupon(coupon);


		return ok(updateCouponView.render(coupon, "updated"));
	
		
	}
}

