import helpers.HashHelper;
import models.Coupon;
import models.User;
import play.Application;
import play.GlobalSettings;


public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app){
		Coupon.createCoupon("Vikend u Neumu", 80, "04.03.2015", "11.03.2015", "http://i.imgur.com/n94Jv7I.gif", 
				"Putovanja", "Vikend za dvoje u Neumu", "Vikend za dvoje u Neumu");
		
		Coupon.createCoupon("Vikend u Neumu", 80, "04.03.2015", "11.03.2015", "http://i.imgur.com/n94Jv7I.gif", 
				"Putovanja", "Vikend za dvoje u Neumu", "Vikend za dvoje u Neumu");
		
		Coupon.createCoupon("Vikend u Neumu", 80, "04.03.2015", "11.03.2015", "http://i.imgur.com/n94Jv7I.gif", 
				"Putovanja", "Vikend za dvoje u Neumu", "Vikend za dvoje u Neumu");
		
		User.createUser("Admin", "admin@mail.com", HashHelper.createPassword("bitCoupon"), true);
		
	}
}
