import models.Coupon;
import models.User;
import play.Application;
import play.GlobalSettings;


public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app){
		Coupon.createCoupon("Vikend u Neumu", 80, "04.03.2015", "11.03.2015", "http:/nekilinkzsliku", 
				"Putovanja", "Vikend za dvoje u Neumu");
		
		Coupon.createCoupon("Vecera", 30, "05.03.2015", "18.03.2015", "http:/nekilinkzsliku", 
				"Gastro", "Vecera za dvoje");
		
		Coupon.createCoupon("Teretana", 35, "08.03.2015", "08.04.2015", "http:/nekilinkzsliku", 
				"Sport", "50% Popust za teretanu");
	}
}
