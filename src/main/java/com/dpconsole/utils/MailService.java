package com.dpconsole.utils;

import java.security.GeneralSecurityException;
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
import javax.mail.search.SubjectTerm;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dpconsole.parsers.ZomatoParser;
import com.sun.mail.util.MailSSLSocketFactory;

/**
 * @author SHABARINATH
 * 23-Nov-2018 5:55:43 pm 2018 
 */

public class MailService {
	 private static final String protocol = "imaps";
	 private static final String host = "imap.gmail.com";
	 Folder inbox = null;
	 Store store = null;
	 Properties props = null;
	 
	 private static final Logger logger = LoggerFactory.getLogger(ZomatoParser.class);
	 
	 @PostConstruct
	 private void init() {
		 props = new Properties();
		 props.put("mail.store.protocol", protocol);
		 props.setProperty("mail.imap.ssl.trust", "*");
	 }
	 
	public Message[] getMessagesWithCriteria(String email, String password, List<String> dpEmails, 
		String subject, Date stateDate, Date endDate, String mailBoxFolder) throws MessagingException, ParseException, GeneralSecurityException{
		props = new Properties();
		props.put("mail.store.protocol", protocol);
		props.setProperty("mail.imap.ssl.trust", "*");
		props.put("mail.imaps.ssl.trust", "*");
		props.put("mail.smtp.ssl.trust", "*");
		
		MailSSLSocketFactory socketFactory= new MailSSLSocketFactory();
		socketFactory.setTrustAllHosts(true);
		props.put("mail.imap.ssl.socketFactory", socketFactory);
		
	 	Session session = Session.getDefaultInstance(props, null);
		store = session.getStore(protocol);
		store.connect(host, email,password);
		inbox = store.getFolder(mailBoxFolder);
		inbox.open(Folder.READ_ONLY);
		SearchTerm rangeAndFromAddressFilter = prepareCriteriaQuery(stateDate, endDate, dpEmails, subject);
		Message[] messages = inbox.search(rangeAndFromAddressFilter);
		messages = filterMessageByTime(messages, stateDate, endDate);
		logger.error("Mails fetched for given criteria: "+messages.length);
		return messages;
	 }

	private Message[] filterMessageByTime(Message[] messages, Date stateDate, Date endDate) throws MessagingException {
		List<Message> msgs = new ArrayList<Message>();
		for(Message msg : messages) {
			Date receivedDate = msg.getReceivedDate();
			if(receivedDate.after(stateDate) && receivedDate.before(endDate)) {
				msgs.add(msg);
			}
		}
		return msgs.toArray(new Message[msgs.size()]);
	}

	private SearchTerm prepareCriteriaQuery(Date stateDate, Date endDate,
			List<String> dpEmails, String subject) {
		SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GE, stateDate);
		SearchTerm olderThan = new ReceivedDateTerm(ComparisonTerm.LE, endDate);
		SearchTerm andTerm = new AndTerm(olderThan, newerThan);
		SearchTerm sTerm = null;
		if(StringUtils.isNotEmpty(subject)) {
			sTerm = new SubjectTerm(subject);
		}
		ArrayList<FromStringTerm> fromAddressSearchTermList = new ArrayList<FromStringTerm>();
		SearchTerm fromAddressTerm =  null;
		OrTerm orTerm = null;
		if(dpEmails.size() ==1) {
			fromAddressTerm = new FromStringTerm(dpEmails.get(0));
		} else {
			for(String dpOrdersEmail: dpEmails) {
				fromAddressSearchTermList.add(new FromStringTerm(dpOrdersEmail));
			}
			orTerm = new OrTerm(fromAddressSearchTermList.toArray(
					new FromStringTerm[fromAddressSearchTermList.size()]));
		}
		SearchTerm rangeAndFromAddressFilter = fromAddressTerm != null ? new AndTerm(andTerm, fromAddressTerm) :
			new AndTerm(andTerm, orTerm);
		SearchTerm searchTerm = StringUtils.isNotEmpty(subject) ? new AndTerm(rangeAndFromAddressFilter, sTerm) : rangeAndFromAddressFilter;
		return searchTerm;
	}
	
	public void disconnectEmailSession() throws MessagingException {
		if(inbox != null)
			inbox.close(true);
		if(store != null)
			store.close();
	}
}

