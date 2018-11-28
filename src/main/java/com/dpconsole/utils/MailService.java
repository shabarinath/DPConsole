package com.dpconsole.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FromStringTerm;
import javax.mail.search.OrTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

/**
 * @author SHABARINATH
 * 23-Nov-2018 5:55:43 pm 2018 
 */

public class MailService {
	 private static final String protocol = "imaps";
	 private static final String folder="inbox";
	 private static final String host = "imap.gmail.com";
	 Folder inbox = null;
	 Store store = null;
	 Properties props = null;
	 
	 @PostConstruct
	 private void init() {
		 props = new Properties();
		 props.put("mail.store.protocol", protocol);
	 }
	 
	public Message[] getMessagesWithCriteria(String email, String password, List<String> dpEmails, 
		String subject, Date stateDate, Date endDate) throws MessagingException, ParseException{
		props = new Properties();
		props.put("mail.store.protocol", protocol);
	 	Session session = Session.getDefaultInstance(props, null);
		store = session.getStore(protocol);
		store.connect(host, email,password);
		inbox = store.getFolder(folder);
		inbox.open(Folder.READ_ONLY);
		SearchTerm rangeAndFromAddressFilter = prepareCriteriaQuery(stateDate, endDate, dpEmails);
		Message[] messages = inbox.search(rangeAndFromAddressFilter);
		return messages;
	 }

	private SearchTerm prepareCriteriaQuery(Date stateDate, Date endDate,
			List<String> dpEmails) {
		SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GE, stateDate);
		SearchTerm olderThan = new ReceivedDateTerm(ComparisonTerm.LE, endDate);
		SearchTerm andTerm = new AndTerm(olderThan, newerThan);
		ArrayList<FromStringTerm> fromAddressSearchTermList = new ArrayList<FromStringTerm>(); 
		for(String dpOrdersEmail: dpEmails) {
			fromAddressSearchTermList.add(new FromStringTerm(dpOrdersEmail));
		}
		OrTerm orTerm = new OrTerm(fromAddressSearchTermList.toArray(
				new FromStringTerm[fromAddressSearchTermList.size()]));
		SearchTerm rangeAndFromAddressFilter = new AndTerm(andTerm, orTerm);
		return rangeAndFromAddressFilter;
	}
	
	public void disconnectEmailSession() throws MessagingException {
		if(inbox != null)
			inbox.close(true);
		if(store != null)
			store.close();
	}
}

