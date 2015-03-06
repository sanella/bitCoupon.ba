package controllers;

import java.util.Date;

import models.Coupon;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.Loginpage;
import views.html.coupontemplate;
import views.html.userIndex;

public class CouponController extends Controller {

	static Form<Coupon> couponForm = new Form<Coupon>(Coupon.class);

	/**
	 * Shows view for adding coupon
	 * 
	 * @return redirect to create coupon view
	 */
	public static Result createCouponView() {
		// TODO
		return redirect("/");// todo//ok(userIndex.render("Create your coupon",
								// couponForm));
	}

	/**
	 * First check if coupon form have errors. If have, return us to create
	 * coupon view, else creates new coupon.
	 * 
	 * @return redirect to create coupon view
	 */
	public static Result addCoupon() {

		if (couponForm.hasErrors()) {
			return redirect("/");// todo
		}

		// TODO handle invalid inputs

		String name = couponForm.bindFromRequest().field("name").value();
		if (name.length() < 4) {
			return ok(userIndex.render("Welcome", session("name"), null));

		}
		
		double price;
		String strPrice = couponForm.bindFromRequest().field("price").value();
		strPrice = strPrice.replace(",", ".");
		try{
			price = Double.valueOf(strPrice);
		} catch (NumberFormatException e){
			//TODO Logger(e);
			return ok(userIndex.render(null, session("name"), "Enter a valid price"));
		}
		
		String dateCreated = couponForm.bindFromRequest().field("dateCreated").value();
		String dateExpire = couponForm.bindFromRequest().field("dateExpire").value();
		String picture = couponForm.bindFromRequest().field("price").value();
		String category = couponForm.bindFromRequest().field("category").value();
		String description = couponForm.bindFromRequest().field("description").value();
		String remark = couponForm.bindFromRequest().field("remark").value();
		long couponID = Coupon.createCoupon(name, price,
				dateCreated, dateExpire, picture, category,
				description, remark);


		return ok(userIndex.render(null, null, "Coupon \"" + name
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

	public static Result showCoupon(long id) {
		Coupon current = Coupon.find(id);

		return ok(coupontemplate.render(session("name"), current));

	}

	public static Result deleteCoupon(long id) {
		Coupon current = Coupon.find(id);
		current.delete();
		return redirect("/");
	}
}
