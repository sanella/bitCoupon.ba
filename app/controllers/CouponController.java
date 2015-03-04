package controllers;

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
		//TODO
		return ok(userIndex.render("Create your coupon", couponForm));
	}

	/**
	 * First check if coupon form have errors. If have, return us to create
	 * coupon view, else creates new coupon.
	 * 
	 * @return redirect to create coupon view
	 */
	public static Result create() {

		if (couponForm.hasErrors()) {
			return ok(createCouponView.render());
		}
		Coupon coupon = couponForm.get();
		Coupon.createCoupon(coupon.name, coupon.description,
				coupon.picture, coupon.category_id, coupon.created,
				coupon.ending, coupon.price);
				
		return ok(createCouponView.render());
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
			return ok(viewCoupon.render(coupon, couponForm));
		}
	
}
