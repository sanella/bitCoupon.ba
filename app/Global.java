import helpers.HashHelper;
import models.Coupon;
import models.EmailVerification;
import models.User;
import play.Application;
import play.GlobalSettings;


public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app){

		if (Coupon.checkByName("Vikend u Neumu") == false ){
		Coupon.createCoupon("Vikend u Neumu", 80, null, "http://static.panoramio.com/photos/large/26139268.jpg", 
				"Putovanja", "Vikend za dvoje u Neumu", "Vikend za dvoje u Neumu");
		}
		if (Coupon.checkByName("Teretana 2u1") == false){
		Coupon.createCoupon("Teretana 2u1", 40,  null, "http://www.thepullforhumanity.com/anytime_fitness/wp-content/gallery/general/gym-pics-097.jpg", 
				"Sport", "Jedan mjesec gratis treniranja", "Najnovije sprave");
		}
		if (Coupon.checkByName("Vecera za dvoje") == false){
		Coupon.createCoupon("Vecera za dvoje", 70,  null, "http://www.mitara.com/wp-content/uploads/2015/02/Candle-Light-Dinner-With-Romantic-Design-For-Beautiful-Valentine-Candle-Light-Dinner-Inspiring-Design-Ideas.jpg", 
				"Food", "Vecera za dvije osobe 30% jeftinije", "Ekskluzivna ponuda");
		}
		
		if (User.check("admin@mail.com") == false){
		User.createUser("Admin", "admin@mail.com", HashHelper.createPassword("bitadmin"), true);
		EmailVerification setVerified = new EmailVerification(1, true);
		setVerified.save();
		}
		
	}
}
