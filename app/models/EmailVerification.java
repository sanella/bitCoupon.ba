package models;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

import helpers.HashHelper;

import java.util.List;

import javax.persistence.*;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class EmailVerification extends Model {

	@Id
	public String id;

	@Required
	public long userId;

	@Required
	public Date createdOn;

	@Required
	public boolean isVerified;

	public EmailVerification(long userId, boolean isVerified) {
		this.id = HashHelper.createPassword(userId + new Date().toString())
				.replace('/', '1');
		this.userId = userId;
		this.createdOn = new Date();
		this.isVerified = isVerified;
	}

	static Finder<String, EmailVerification> find = new Finder<String, EmailVerification>(
			String.class, EmailVerification.class);

	public EmailVerification(String recordId) {
	}

	public static String addNewRecord(long userId) {
		EmailVerification verify = new EmailVerification(userId, false);
		verify.save();
		return verify.id;
	}

	public static void updateRecord(EmailVerification recordToUpdate) {
		recordToUpdate.isVerified = true;
		recordToUpdate.save();
	}

	public static EmailVerification find(String id) {
		return find.byId(id);
	}

	public static boolean isEmailVerified(long userId) {
		List<EmailVerification> list = find.where().eq("userId", userId)
				.orderBy("createdOn").findList();

		return list.get(list.size() - 1).isVerified;
	}
}
