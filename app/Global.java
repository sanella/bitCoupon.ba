import helpers.HashHelper;
import models.Category;
import models.Coupon;
import models.EmailVerification;
import models.User;
import play.Application;
import play.GlobalSettings;

public class Global extends GlobalSettings {
	String nameCoupon1="Dvije noći za dvoje u Hotelu Sunce Neum";
	String remarkCoupon1 = "Jedna osoba može kupiti maksimalno četiri kupona za ovu ponudu. Kuponi se mogu spajati.";
	
	String descriptionCoupon1="Poželjeli ste da udahnete miris mora i da na bar dva dana pobjegnete od svakodnevnice, da se opustite uz duge šetnje plažom? Uživajte u dvije noći u Hotelu \"Sunce\" u Neumu za dvije osobe uz doručak!";
	
	String nameCoupon2="Mesec dana korištenja teretane + kardio program ! Posljednje pripreme pred ljeto!";
	
	String remarkCoupon2="Ponudom se podrazumeva: Mjesec dana korištenja teretane + kardio program. Ponudom se podrazumijeva 12 termina. Ponuda je SAMO za NOVE članove. Možete kupiti najviše 1 kupon za sebe i više za druge – nove članove.";
	
	String descriptionCoupon2="Još uvek nije kasno da se pokrenete i da svoje zdravlje shvatite kao najprimarniju stvar o kojoj treba da vodite računa.";
	
	String nameCoupon3="Ručak ili večera za dvoje u prekrasnom restoranu hotela Brass ";
	
	String remarkCoupon3="Jedna osoba može kupiti neograničen broj kupona za ovu ponudu. Kupon se može iskoristiti odmah nakon obavljene kupovine. Ponuda se odnosi na večeru za dvoje. Ponuda uključuje jelo po izboru i piće za dvoje.";
	String descriptionCoupon3="Poželjeli ste odvesti dragu osobu na romantičnu večeru ili kasni ručak? "+
	"Hotel Brass unikatan po mnogo čemu: dizajnu, usluzi te uslužnom osoblju."+
    "Upravo ovakav ambijent začinit će i uljepšati Vašu romantičnu večeru.";


	

	@Override
	public void onStart(Application app) {
		
		Category food = new Category("Food");
		food.save();
		Category travel = new Category("Travel");
		travel.save();
		Category sport = new Category("Sport");
		sport.save();
		

		if (Coupon.checkByName(nameCoupon1) == false) {
			Coupon.createCoupon(nameCoupon1, 80, null,
					"http://static.panoramio.com/photos/large/26139268.jpg",
			travel,descriptionCoupon1,
					remarkCoupon1);
		}
		if (Coupon.checkByName(nameCoupon2) == false) {
			Coupon.createCoupon(
					nameCoupon2,
					40,
					null,
					"http://www.thepullforhumanity.com/anytime_fitness/wp-content/gallery/general/gym-pics-097.jpg",
					sport, descriptionCoupon2,
					remarkCoupon2);
		}
		if (Coupon.checkByName(nameCoupon3) == false) {
			Coupon.createCoupon(
					nameCoupon3,
					20,
					null,
					"http://www.mitara.com/wp-content/uploads/2015/02/Candle-Light-Dinner-With-Romantic-Design-For-Beautiful-Valentine-Candle-Light-Dinner-Inspiring-Design-Ideas.jpg",
					food, descriptionCoupon3,
					remarkCoupon3);
		}

		if (User.check("admin@mail.com") == false) {
			User.createUser("Admin", "admin@mail.com",
					HashHelper.createPassword("bitadmin"), true);
			EmailVerification setVerified = new EmailVerification(1, true);
			setVerified.save();
		}

	}
}
