import helpers.HashHelper;
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
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
    @Test
    public void test() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("Welcome ");
                assertThat(browser.pageSource()).contains("Registration");
                assertThat(browser.pageSource()).contains("Login");

            }
        });
    }

 
   @Test
    public void testRegistration() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
  
                browser.goTo("http://localhost:3333/signup");
                browser.fill("#username").with("tester");
                browser.fill("#email").with("tester@mail.com");
                browser.fill("#password").with("123456");
                browser.fill("#confirmPassword").with("123456");
                browser.submit("#submit");
                assertThat(browser.pageSource()).contains("tester");
                assertThat(browser.pageSource()).contains("Home");
                assertThat(browser.pageSource()).contains("Logout");

            }
        });
    }

   
   @Test
   public void testLogin() {
       running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
           public void invoke(TestBrowser browser) {
        	 //  User.createUser("tester", "tester@mail.com", "123456");
        	   
              User.createUser("tester", "tester@bitcamp.ba", HashHelper.createPassword("123456"), true);
        	   
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

}
