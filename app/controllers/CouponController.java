package controllers;

import java.text.ParseException;
import java.util.Date;
import models.Coupon;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class CouponController extends Controller {
	
	
	static Form<Coupon> couponForm = new Form<Coupon>(Coupon.class);
	
	/**
	 * 
	 * @return renders the view for coupon add form
	 */
	public static Result addCouponView(){
		
		return ok(couponPanel.render(session("name"), null));
	}
	

	/**
	 * First checks if the coupon form has errors.
	 * Creates a new coupon ot renders the view again if any error
	 * occurs.
	 * 
	 * @return redirect to create coupon view
	 * @throws ParseException 
	 */
	public static Result addCoupon() throws ParseException {

		if (couponForm.hasErrors()) {
			return redirect("/couponPanel");
		}

		// TODO handle invalid inputs

		String name = couponForm.bindFromRequest().field("name").value();
		if (name.length() < 4) {
			return ok(couponPanel.render(session("name"), "Name must be 4 characters long"));

		}
		if(name.length()>120){
			return ok(couponPanel.render(session("name"),"Name must be max 120 characters long"));
		}
		
		/* Taking the price value from the string and checking if it's valid*/
		double price;
		String stringPrice = couponForm.bindFromRequest().field("price").value();
		stringPrice = stringPrice.replace(",", ".");
		try{
			price = Double.valueOf(stringPrice);
			if ( price <= 0){
				return ok(couponPanel.render(session("name"), "Enter a valid price"));
			}
		} catch (NumberFormatException e){
			//TODO Logger(e);
			return ok(couponPanel.render(session("name"), "Enter a valid price"));
		}
		
		Date current = new Date();
		Date date = couponForm.bindFromRequest().get().dateExpire;	
		if ( date.before(current)){
			return ok(couponPanel.render(session("name"), "Enter a valid expiration date"));
		}
		
		String picture = couponForm.bindFromRequest().field("picture").value();
		String category = couponForm.bindFromRequest().field("category").value();
		String description = couponForm.bindFromRequest().field("description").value();
		String remark = couponForm.bindFromRequest().field("remark").value();
		
		/* Creating the new coupon */
		Coupon.createCoupon(name, price, date, picture, category,
				description, remark);


		return ok(couponPanel.render( session("name"), "Coupon \"" + name
				+ "\" added"));
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
	 * @param id - Coupon id (long)
	 * @return redirect to index after delete
	 */
	public static Result deleteCoupon(long id) {
		Coupon.delete(id);
		return redirect("/");
	}
	
	
	/**
	 * Renders the view of a coupon.
	 * Method receives the id of the coupon and finds 
	 * the coupon by id and send's the coupon to the view.
	 * @param id long
	 * @return Result render couponView
	 */
	public static Result editCoupon(long id){
		Coupon coupon=Coupon.find(id);
		return ok(updateCouponView.render(session("name"),coupon, null));
	
	}
	
	/**
	 * Update coupon 
	 * Method receives an id, finds the specific coupon and
	 * renders the update View for the coupon.
	 * If any error occurs, the view is rendered repeatedly.
	 * 
	 * @param id long
	 * @return Result render the coupon update view
	 */
	public static Result updateCoupon(long id){
		
		Coupon coupon=Coupon.find(id);	
		if (couponForm.hasErrors()) {
			return redirect("/");
		}

		// TODO handle invalid inputs

		coupon.name = couponForm.bindFromRequest().field("name").value();
		if (coupon.name.length() < 4) {
			return ok(updateCouponView.render(session("name"),coupon, "Name must be minimal 4 characters"));
			}
		if(coupon.name.length() > 120){
			return ok(couponPanel.render(session("name"),"Name must be max 120 characters long"));
		}

		String strPrice = couponForm.bindFromRequest().field("price").value();
		strPrice = strPrice.replace(",", ".");
		try{
			coupon.price = Double.valueOf(strPrice);
		} catch (NumberFormatException e){
			//TODO Logger(e);
			return ok(updateCouponView.render(session("name"), coupon, null));
		}
		Date current = new Date();
		Date date = couponForm.bindFromRequest().get().dateExpire;
		if (date != null){  
			if ( date.before(current)){
				return ok(updateCouponView.render(session("name"), coupon, "Enter a valid expiration date"));
			}
			coupon.dateExpire = date;
		}
	
		coupon.picture = couponForm.bindFromRequest().field("picture").value();
		coupon.category = couponForm.bindFromRequest().field("category").value();
		coupon.description = couponForm.bindFromRequest().field("description").value();
		coupon.remark = couponForm.bindFromRequest().field("remark").value();
		Coupon.updateCoupon(coupon);


		return ok(updateCouponView.render(session("name"), coupon, "updated"));
	
		
	}
}

