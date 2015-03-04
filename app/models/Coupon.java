package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.openqa.selenium.support.FindAll;

import play.data.validation.Constraints.MinLength;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

/**
 * 
 * Entity class for the Coupon.
 * Creates a table in the database with all of
 * the properties
 *
 */

@Entity
public class Coupon extends Model {

	@Id
	public long id;

	@MinLength(5)
	public String name;

	@MinLength(20)
	public String description;

	public String picture;

	public long category_id;

	public String code;

	public boolean lastMinute;

	public Date created;

	public double duration;

	public Date ending;

	public boolean specialPrice;

	public double price;

	public int viewCount;

	public boolean specialOffer;

	public long multiOffer_id;

	public boolean status;

	public long company_id;

	public String remark;

	public long comment_user_id;

	public long response_company_id;

	public Coupon(long id, String name, String description, String picture,
			long category_id, String code, boolean lastMinute, Date created,
			double duration, Date ending, boolean specialPrice, double price,
			int viewCount, boolean specialOffer, long multiOffer_id,
			boolean status, long company_id, String remark,
			long comment_user_id, long response_company_id) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.picture = picture;
		this.category_id = category_id;
		this.code = code;
		this.lastMinute = lastMinute;
		this.created = created;
		this.duration = duration;
		this.ending = ending;
		this.specialPrice = specialPrice;
		this.price = price;
		this.viewCount = viewCount;
		this.specialOffer = specialOffer;
		this.multiOffer_id = multiOffer_id;
		this.status = status;
		this.company_id = company_id;
		this.remark = remark;
		this.comment_user_id = comment_user_id;
		this.response_company_id = response_company_id;
	}

	static Finder<Long, Coupon> find = new Finder<Long, Coupon>(Long.class,
			Coupon.class);

	/**
	 * Creates a new Coupon and saves it to the database 
	 * @return the id of the new Coupon (long)
	 */
	
	public static long createCoupon(long id, String name, String description,
			String picture, long category_id, String code, boolean lastMinute,
			Date created, double duration, Date ending, boolean specialPrice,
			double price, int viewCount, boolean specialOffer,
			long multiOffer_id, boolean status, long company_id, String remark,
			long comment_user_id, long response_company_id) {
		Coupon newCoupon = new Coupon(id, name, description, picture,
				category_id, code, lastMinute, created, duration, ending,
				specialPrice, price, viewCount, specialOffer, multiOffer_id,
				status, company_id, remark, comment_user_id,
				response_company_id);
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
	
	public static void delete(long id){
		find.byId(id).delete();
	}


}
