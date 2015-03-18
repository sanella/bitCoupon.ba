package models;

import java.util.List;

import javax.persistence.*;

import play.data.validation.Constraints.MinLength;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Category extends Model{

	@Id
	public long id;
	
	@MinLength(4)
	public String name;
	
	String image;
	
	public int numberOfCoupons;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	public List<Coupon> coupons;
    
    
    static Finder<Long, Category> find = new Finder<Long, Category>(Long.class,
			Category.class);
    
    public Category(String name, String image){
    	this.name=name;
    	this.image=image;
    	this.numberOfCoupons=coupons.size();
    	}
    
    public static long createCategory(String name,String image){
    	Category newCategory=new Category(name, image);
    	newCategory.save();
    	return newCategory.id;
    	
    }
    
    public static List<Category>all(){
    	return find.all();
    }
    
    public static Category find(long id){
    	return find.byId(id);
    }
    
    public static void delete(long id){
    	find.byId(id).delete();
    }
}
