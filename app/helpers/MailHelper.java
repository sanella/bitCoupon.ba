package helpers;

import play.libs.mailer.Email;
import play.libs.mailer.MailerPlugin;

public class MailHelper {

	public static void send(String email, String message) {

		/**
		 * Set subject, body and sender of mail and send mail
		 */
		Email mail = new Email();
		mail.setSubject("Mail for registration to bitCoupon.ba");
		mail.setFrom("bitCoupon.ba <bit.play.test@gmail.com>");
		mail.addTo("bitCoupon.ba Contact <bit.play.test@gmail.com>");
		mail.addTo(email);
		
		mail.setBodyText(message);
		mail.setBodyHtml(String
				.format("<html><body><strong> %s </strong>: <p> %s </p> </body></html>",
						email, message));
		MailerPlugin.send(mail);

	}
	/**
	 * This is a specific mail sender for contact form, used for sending feedback to admins.
	 * @param email
	 * @param name
	 * @param phone
	 * @param message
	 */
	public static void sendFeedback(String email, String name, String phone, String message) {

		String adminMail = "haris.krkalic@bitcamp.ba";
		
		/**
		 * Set subject, body and sender of mail and send mail
		 */
		Email mail = new Email();
		mail.setSubject("bitCoupon.ba Feedback!");
		mail.setFrom(email);
		mail.addTo("bitCoupon.ba Feedback <bit.play.test@gmail.com>");
		mail.addTo(adminMail);
		
		mail.setBodyText(message);
		mail.setBodyHtml(String
				.format("<html>"
						+ "<body>"
						+ "<strong> My email </strong>: " + "%s"
						+"\n"
						+ "<strong> My name  </strong>: " + "%s"
						+"\n"
						+ "<strong> My phone number </strong>: " + "%s" 
						+"\n"
						+"\n"
						+ "<p> %s </p> "
						+ "</body>"
						+ "</html>",
						name, phone, email, message));
		MailerPlugin.send(mail);

	}
	
}