package models;

import java.util.Date;

import helpers.HashHelper;

import org.junit.*;

import controllers.UserController;
import play.test.WithApplication;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;
import static play.test.Helpers.*;

public class ModelsTest extends WithApplication {
	@Before
	public void setUp() {
		
		fakeApplication( inMemoryDatabase()); //,fakeGlobal() ne radi u play verzijama > 2.0, uzeti u obzir admina na mjestu 1 zbog global klase
	}

	@Test
	public void testCreate() {
		User.createUser("tester", "test@mail.com", "654321", false);
		User u = User.find(2);
		assertNotNull(u);
		assertEquals(u.username, "tester");
		assertEquals(u.email, "test@mail.com");
		assertEquals(u.password, "654321");
	}

	@Test
	public void testFindNonExisting() {
		User u = User.find(1000);

		assertNull(u);
	}

	@Test
	public void testDelete() {
		User.createUser("test", "test@bitcamp.ba", HashHelper.createPassword("54321"), false);
		User.delete(1);
		User b = User.find(1);
		assertNull(b);
	}
	
	@Test
	public void testCouponCreate(){
		
		Coupon.createCoupon("Test", 55.3, new Date(), "url", new Category("sport"), "description", "remark");
		Coupon c = Coupon.find(4);
		assertNotNull(c);
		
	}
	
	@Test
	public void testFindNonExistingCoupon(){
		Coupon c = Coupon.find(1500);
		assertNull(c);
	}
	
	@Test
	public void deleteCoupon(){
		Coupon.createCoupon("test", 2.22, new Date(), "testurl", new Category("food"), "description", "remark");
		Coupon.delete(4);
		Coupon c = Coupon.find(4);
		assertNull(c);
		
	}
	@Test
	public void deleteExistingCoupon(){  //tests delete coupon which is made in Global class
		Coupon.delete(2);
		Coupon c = Coupon.find(2);
		assertNull(c);
	}
	
	@Test
	public void updateUser(){
		User.createUser("tester", "tester@bitcamp.ba",
				HashHelper.createPassword("123456"), false);
		EmailVerification setVerified = new EmailVerification(2, true);
		setVerified.save();
		
		User user = User.find(2);
		user.username = "fixer";
		user.isAdmin = true;
		assertEquals(user.username, "fixer");
		assertEquals(user.isAdmin, true);
		
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public  void updateCoupon(){
		//Coupon.createCoupon("Rucak", 15, null, null, null, "Rucak","Test za rucak");
		Coupon c = new Coupon("Rucak", 15, null, null, null, "Rucak za dvoje", "Test za rucak");
		c.save();
		Coupon coupon=Coupon.find(4);
		coupon.name="Vecera";
		coupon.description = "Rucak za troje";
		coupon.remark = "Test za rucak promjena";
		coupon.save();
		assertEquals(coupon.name,"Vecera");
		assertEquals(coupon.description,"Rucak za troje");
		assertEquals(coupon.remark,"Test za rucak promjena");
	}

}
