package com.dpConsole;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import javax.mail.Message;

import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.parsers.ZomatoParser;
import com.dpconsole.utils.MailService;

/**
 * @author SHABARINATH
 * 23-Nov-2018 7:01:12 pm 2018
 */

public class MailServiceTest {

	public static void main(String[] args) throws Exception {
		MailService mailService = new MailService();
		String[] dpEmails = new String[2];
		dpEmails[0]="volamshabarinath@gmail.com";
		dpEmails[1]="noreply@zomato.com";

		SimpleDateFormat df1 = new SimpleDateFormat("MM/dd/yy");
		String pastDateStr = "11/14/18";
		Date pastDate = df1.parse(pastDateStr);
		String futureDateStr = "11/28/18";
		Date futureDate = df1.parse(futureDateStr);
		Message[] messages = mailService.getMessagesWithCriteria("Kitchensofchina@gmail.com", "koc654321", Arrays.asList(dpEmails), "", pastDate, futureDate);
		ZomatoParser parser = new ZomatoParser();
		parser.parse(new Kitchen(), new HashMap<>(), Arrays.asList(messages));
		MailService mailSvc = new MailService();
		mailSvc.disconnectEmailSession();
	}
}