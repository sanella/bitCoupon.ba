import helpers.HashHelper;
import models.Category;
import models.Coupon;
import models.EmailVerification;
import models.FAQ;
import models.User;
import play.Application;
import play.GlobalSettings;
import play.Logger;

public class Global extends GlobalSettings {
	
	
	String nameCoupon1 = "Dvije noći za dvoje u Hotelu Sunce Neum";
	String remarkCoupon1 = "Jedna osoba može kupiti maksimalno četiri kupona za ovu ponudu. Kuponi se mogu spajati.";


	String descriptionCoupon1 = "Poželjeli ste da udahnete miris mora i da na bar dva dana pobjegnete od svakodnevnice, da se opustite uz duge šetnje plažom? Uživajte u dvije noći u Hotelu \"Sunce\" u Neumu za dvije osobe uz doručak!";

	String nameCoupon2 = "Mesec dana korištenja teretane + kardio program ! Posljednje pripreme pred ljeto!";

	String remarkCoupon2 = "Ponudom se podrazumeva: Mjesec dana korištenja teretane + kardio program. Ponudom se podrazumijeva 12 termina. Ponuda je SAMO za NOVE članove. Možete kupiti najviše 1 kupon za sebe i više za druge – nove članove.";

	String descriptionCoupon2 = "Još uvek nije kasno da se pokrenete i da svoje zdravlje shvatite kao najprimarniju stvar o kojoj treba da vodite računa.";

	String nameCoupon3 = "Ručak ili večera za dvoje u prekrasnom restoranu hotela Brass ";

	String remarkCoupon3 = "Jedna osoba može kupiti neograničen broj kupona za ovu ponudu. Kupon se može iskoristiti odmah nakon obavljene kupovine. Ponuda se odnosi na večeru za dvoje. Ponuda uključuje jelo po izboru i piće za dvoje.";
	String descriptionCoupon3 = "Poželjeli ste odvesti dragu osobu na romantičnu večeru ili kasni ručak? "
			+ "Hotel Brass unikatan po mnogo čemu: dizajnu, usluzi te uslužnom osoblju."
			+ "Upravo ovakav ambijent začinit će i uljepšati Vašu romantičnu večeru.";


	


	@Override
	public void onStart(Application app) {
		
		Logger.trace("test trace level");
		Logger.debug("test debug level");
		Logger.info("test info logger");
		Logger.warn("test warn logger");
		Logger.error("test error logger");
		
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
		
		if (User.check("john@mail.com") == false) {
			User.createUser("John", "john@mail.com",
					HashHelper.createPassword("johndoe"), false);
			EmailVerification setVerified = new EmailVerification(2, true);
			setVerified.save();
		}
		
		
		if (FAQ.checkByQuestion("Želim kupiti današnju ponudu. Kako da to učinim?") == false){
			FAQ.createFAQ("Želim kupiti današnju ponudu. Kako da to učinim?", "Morate biti "
					+ "registrovani i ulogovani na stranicu. Ukoliko još nemate nalog,"
					+ " registracija je besplatna i traje manje od 30 sekundi, a također se "
					+ "možete uvezati sa Vašim Facebook nalogom. Kada se ulogujete kliknite na"
					+ " dugme Kupi i pratite jednostavne korake.");
		}
		
		if (FAQ.checkByQuestion("Na koji način plaćam ponudu i je li sigurno?") == false){
			FAQ.createFAQ("Na koji način plaćam ponudu i je li sigurno?", "Plaćanje možete obaviti na"
					+ " jedan od sljedećih načina:"
					+"1.	Putem eKredita – eKredit možete uplatiti u banci ili pošti na naš transkcijski "
					+ "račun, a on će Vam biti dodijeljen sljedeći radni dan ili najkasnije 48 sati nakon"
					+ " uplate. Kada Vaš eKredit bude prikazan na Vašem profilu možete obaviti željenu kupovinu. "
					+ "Više informacija možete pronaći na ovom linku nakon prijave na Vaš profil."
					+"2.	Putem kartice – Plaćanje je zbog sigurnosti, jednostavnije realizacije i brzine "
					+ "obrade zahtjeva limitirano na kreditne i debitne MasterCard, Maestro i VISA kartice. "
					+ "Plaćanje je potpuno sigurno, a više o tome možete pročitati na - sigurnost plaćanja."
					+"3.	Dio plaćate eKreditom, a dio karticom – Prilikom kupovine potrebno je da unesete iznos "
					+ "eKredita koji želite da Vam se odbije i dalje idete na „Nastavi kupovinu“. Unesete sve"
					+ " potrebne podatke i na taj način će Vam se jedan dio odbiti u vidu eKredita a ostatak"
					+ " vrijednosti će biti rezervisan na Vašoj kartici."
					+"4. Gotovinska uplata kupona ili ekredita - uplatu možete izvršiti na eKupon prodajnim mjestima u"
					+ " BBI tržnom centru i Grand centru na Ilidži. "
					+"5. Kupovina poklon bona na ime i prezime osobe koju želite bez obzira da li je registrovan korisnik ili ne.");
		}
		
		if (FAQ.checkByQuestion("Šta se dešava ako ponuda dana ne dosegne minimalan broj kupaca?")==false){
			FAQ.createFAQ("Šta se dešava ako ponuda dana ne dosegne minimalan broj kupaca?", "Da bi ponuda "
					+ "uspjela mora dostići minimalan broj kupaca koji se određuje u dogovoru s partnerima."
					+ "U slučaju da se ne kupi dovoljan broj kupona, ponuda propada i ništa Vam se ne naplaćuje. "
					+ "Da izbjegnete ovakve situacije, pozovite što više osoba da se uključe na ponudu na "
					+ "kojoj ste učestvovali.");
		}

	}
	
}
