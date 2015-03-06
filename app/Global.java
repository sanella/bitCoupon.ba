import helpers.HashHelper;
import models.Coupon;
import models.User;
import play.Application;
import play.GlobalSettings;


public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app){
		Coupon.createCoupon("Vikend u Neumu", 80, "04.03.2015", "11.03.2015", "http://static.panoramio.com/photos/large/26139268.jpg", 
				"Putovanja", "Vikend za dvoje u Neumu", "Vikend za dvoje u Neumu");
		
		Coupon.createCoupon("Teretana 2u1", 40, "06.03.2015", "06.05.2015", "http://www.fitingym.com/images/gym_instructor_courses_qualifications_container.jpg", 
				"Sport", "Jedan mjesec gratis treniranja", "Najnovije sprave");
		
		Coupon.createCoupon("Vecera za dvoje", 70, "07.03.2015", "16.03.2015", "http://www.ramadasuitesorlandoairport.com/images/dinner.jpg", 
				"Food", "Vecera za dvije osobe 30% jeftinije", "Ekskluzivna ponuda");
		
		User.createUser("Admin", "admin@mail.com", HashHelper.createPassword("bitadmin"), true);
		
	}
}
