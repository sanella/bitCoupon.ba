package models;

import helpers.HashHelper;

import org.junit.*;

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
		
		Coupon.createCoupon("Test", 55, "11.11.1111", "11.11.2222", "url", "category", "description", "remark");
		Coupon c = Coupon.find(4);
		assertNotNull(c);
		
	}


}
