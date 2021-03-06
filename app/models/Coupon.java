package models;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.data.validation.Constraints.MinLength;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;

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
	
	@Column(name="created_at")
	public Date dateCreated;

	@Column(name="expired_at")
	public Date dateExpire;

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

	public Coupon(String name, double price,
			Date dateExpire, String picture, String category_id,
			String description, String remark) {

		this.name = name;
		this.price = price;
		this.dateCreated = new Date();
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

	public static long createCoupon(String name, double price, Date dateExpire, String picture,
			String category, String description,String remark) {
		if(!picture.contains("http://") ){
			picture = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAyMuVdpfRWZohd288y7EIqVsnwJPi92txgrn5DBWxEOZDnhJL";
		}
		
		Coupon newCoupon = new Coupon(name, price,dateExpire,
				picture, category, description, remark);
		newCoupon.save();
		return newCoupon.id;
	}

	/**
	 * 
	 * @return price as String in 00.00 format
	 */
	public String getPriceString() {
		return String.format("%1.2f",price);
	}

	/**
	 * 
	 * @return all coupons as List<Coupon>
	 */
	public static List<Coupon> all() {
		List<Coupon> coupons = find.findList();
		return coupons;
	}

	/**
	 * Find coupon by id
	 * @param id long
	 * @return Coupon
	 */
	public static Coupon find(long id) {
		return find.byId(id);
	}

	
	/**
	 * Delete coupon by id
	 * @param id long
	 */
	public static void delete(long id) {
		find.byId(id).delete();
	}
	
	/**
	 * Checks if the coupon exists
	 * @param name of coupon String
	 * @return true or false
	 */
	public static boolean checkByName(String name){
		return find.where().eq("name", name).findUnique() != null;
	}
	
	/**
	 * Updates coupon
	 * @param coupon
	 */
	public static void updateCoupon(Coupon coupon){
		coupon.save();
	}

}
