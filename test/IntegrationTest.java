import java.util.Date;

import helpers.HashHelper;
import models.Category;
import models.Coupon;
import models.EmailVerification;
import models.User;

import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.junit.Assert.*;

public class IntegrationTest {

	/**
	 * add your integration test here in this example we just check if the
	 * welcome page is being shown
	 */
	@Test
	public void test() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())),
				HTMLUNIT, new Callback<TestBrowser>() {
					public void invoke(TestBrowser browser) {
						browser.goTo("http://localhost:3333");					
						assertThat(browser.pageSource()).contains("Registration");
						assertThat(browser.pageSource()).contains("Login");

					}
				});
	}

	/**
	 * Test for User registration
	 */
	@Test
	public void testRegistration() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())),
				HTMLUNIT, new Callback<TestBrowser>() {
					public void invoke(TestBrowser browser) {

						browser.goTo("http://localhost:3333/signup");
						browser.fill("#username").with("tester");
						browser.fill("#email").with("tester@mail.com");
						browser.fill("#password").with("123456");
						browser.fill("#confirmPassword").with("123456");
						browser.submit("#submit");
						assertThat(browser.pageSource()).contains("Registration");
						assertThat(browser.pageSource()).contains("Login");
						assertThat(browser.pageSource()).contains("A verification mail has been sent to your email address");

					}
				});
	}

	/**
	 * Test for User login
	 */
	@Test
	public void testLogin() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())),
				HTMLUNIT, new Callback<TestBrowser>() {
					public void invoke(TestBrowser browser) {
						// User.createUser("tester", "tester@mail.com",
						// "123456");

						User.createUser("tester", "tester@bitcamp.ba",
								HashHelper.createPassword("123456"), true);
						EmailVerification setVerified = new EmailVerification(2, true);
						setVerified.save();

						browser.goTo("http://localhost:3333/loginpage");
						browser.fill("#email").with("tester@bitcamp.ba");
						browser.fill("#password").with("123456");
						browser.submit("#submit");
						assertThat(browser.pageSource()).contains("tester");
						assertThat(browser.pageSource()).contains("Home");
						assertThat(browser.pageSource()).contains("Available Coupons");

					}
				});

	}

	/**
	 * Tests showing coupon which is made in this test
	 */
	@Test
	public void testShowAddedCoupon() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())),
				HTMLUNIT, new Callback<TestBrowser>() {
					public void invoke(TestBrowser browser) {

						Coupon.createCoupon("TestCoupon", 55.4, 
								new Date(), "url", new Category("test"), "description",
								"remark");
						browser.goTo("http://localhost:3333/");
						assertThat(browser.pageSource()).contains("TestCoupon");
						assertThat(browser.pageSource()).contains(
								"Only 55.40 KM");

					}
				});

	}

	/**
	 * Tests deleting coupon which is made in Global class
	 */
	@Test
	public void testDeleteCoupon() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())),
				HTMLUNIT, new Callback<TestBrowser>() {
					public void invoke(TestBrowser browser) {
						Coupon.delete(1);
						browser.goTo("http://localhost:3333/");
						assertThat(!browser.pageSource().contains(
								"Vikend u Neumu"));

					}
				});

	}

	/**
	 * Tests deleting coupon which is made in this test
	 */
	@Test
	public void testDeleteAddedCoupon() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())),
				HTMLUNIT, new Callback<TestBrowser>() {
					public void invoke(TestBrowser browser) {

						long couponId = Coupon.createCoupon("TestCoupon", 55.8, new Date(), "url", new Category("category"),
								"description", "remark");
						Coupon.delete(couponId);
						browser.goTo("http://localhost:3333/coupon/" + couponId);
						browser.submit("#delete");
						assertThat(!browser.pageSource().contains("TestCoupon"));
					}
				});

	}

}
