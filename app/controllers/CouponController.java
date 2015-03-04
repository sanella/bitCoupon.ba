package controllers;

import java.util.Date;

import models.Coupon;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.Loginpage;
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

		// String name = couponForm.bindFromRequest().get().name;
		// double price = couponForm.bindFromRequest().get().price;
		// Date created = couponForm.bindFromRequest().get().created;
		// Date ending = couponForm.bindFromRequest().get().ending;
		// String picture = couponForm.bindFromRequest().get().picture;
		// String category = couponForm.bindFromRequest().get().category;
		// String description = couponForm.bindFromRequest().get().description;
		// Coupon.createCoupon(name, price, created, ending, picture, category,
		// description);
		// return ok(userIndex.render(null, null, name));

		if (couponForm.hasErrors()) {
			return redirect("/");// todo
		}
		Coupon newc = couponForm.bindFromRequest().get();
		long couponID = Coupon.createCoupon(newc.name, newc.price,
				newc.dateCreated, newc.dateExpire, newc.picture, newc.category,
				newc.description);

		return ok(userIndex.render(null, null, "Coupon \"" + newc.name
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

	public static Result viewCoupon(long id) {
		Coupon coupon = Coupon.find(id);
		return redirect("/");// todo//ok(viewCoupon.render(coupon, couponForm));
	}

}
