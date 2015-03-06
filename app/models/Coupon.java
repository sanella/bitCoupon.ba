package models;

import java.util.List;

import javax.persistence.*;

import play.data.validation.Constraints.MinLength;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

/**
 * 
 * Entity class for the Coupon. Creates a table in the database with all of the
 * properties
 *
 */

@Entity
public class Coupon extends Model {

	@Id
	public long id;

	@MinLength(4)
	public String name;

	public double price;

	public String dateCreated;

	public String dateExpire;

	public String picture;

	public String category;

	public String description;
	
	public String remark;

	/*
	 * public String code;
	 * 
	 * public boolean lastMinute;
	 * 
	 * public double duration;
	 * 
	 * public boolean specialPrice;
	 * 
	 * public int viewCount;
	 * 
	 * public boolean specialOffer;
	 * 
	 * public long multiOffer_id;
	 * 
	 * public boolean status;
	 * 
	 * public long company_id;
	 * 
	 * public long comment_user_id;
	 * 
	 * public long response_company_id;
	 */

	public Coupon(String name, double price, String dateCreated,
			String dateExpire, String picture, String category_id,
			String description, String remark) {

		this.name = name;
		this.price = price;
		this.dateCreated = dateCreated;
		this.dateExpire = dateExpire;
		this.picture = picture;
		this.category = category_id;
		this.description = description;
		this.remark=remark;
		/*
		 * this.code = code; this.lastMinute = lastMinute; this.duration =
		 * duration; this.specialPrice = specialPrice; this.viewCount =
		 * viewCount; this.specialOffer = specialOffer; this.multiOffer_id =
		 * multiOffer_id; this.status = status; this.company_id = company_id;
		 * this.comment_user_id = comment_user_id;
		 * this.response_company_id = response_company_id;
		 */
	}

	static Finder<Long, Coupon> find = new Finder<Long, Coupon>(Long.class,
			Coupon.class);

	/**
	 * Creates a new Coupon and saves it to the database
	 * 
	 * @return the id of the new Coupon (long)
	 */

	public static long createCoupon(String name, double price,
			String dateCreated, String dateExpire, String picture,
			String category, String description,String remark) {
		Coupon newCoupon = new Coupon(name, price, dateCreated, dateExpire,
				picture, category, description, remark);
		newCoupon.save();
		return newCoupon.id;
	}

	/*
	 * Find list of all Coupons
	 */
	public static List<Coupon> all() {
		List<Coupon> coupons = find.findList();
		return coupons;
	}

	/*
	 * Find Coupon by ID
	 */

	public static Coupon find(long id) {
		return find.byId(id);
	}

	/*
	 * Delete Coupon by id
	 */

	public static void delete(long id) {
		find.byId(id).delete();
	}
	
	public static boolean checkByName(String name){
		return find.where().eq("name", name).findUnique() != null;
	}

}
