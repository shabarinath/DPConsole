/*
 * This computer program is the confidential information and proprietary trade
 * secret of DP Console Project. Possessions and use of this program must
 * conform strictly to the license agreement between the user and
 * DP Console Project, and receipt or possession does not convey any rights
 * to divulge, reproduce, or allow others to use this program without specific
 * written authorization of DP Console Project.
 *
 * Copyright 2018 DP Console Project. All Rights Reserved.
 */
package com.dpconsole.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

/**
 * @author nanda.malve
 * created on 22-Nov-2018 11:00:35 PM
 */
public class GmailService {

	private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "tokens";

	/**
	 * Global instance of the scopes required by this quickstart.
	 * If modifying these scopes, delete your previously saved tokens/ folder.
	 */
	private static final List<String> SCOPES = Collections.unmodifiableList(Arrays.asList(GmailScopes.GMAIL_LABELS
			, GmailScopes.MAIL_GOOGLE_COM, GmailScopes.GMAIL_READONLY));
	private static final String CREDENTIALS_FILE_PATH = "credentials.json";

	/**
	 * Creates an authorized Credential object.
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If the credentials.json file cannot be found.
	 */
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		// Load client secrets.
		//InputStream in = GmailService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		File initialFile = new File(CREDENTIALS_FILE_PATH);
		System.out.println(initialFile.exists());
		System.out.println(initialFile.getAbsolutePath());
		InputStream in = new FileInputStream(initialFile);

		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline")
				.build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	public static void main(String... args) throws IOException, GeneralSecurityException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME)
				.build();
		// Print the labels in the user's account.
		String user = "me";
		ListLabelsResponse listResponse = service.users().labels().list(user).execute();
		List<Label> labels = listResponse.getLabels();
		if (labels.isEmpty()) {
			System.out.println("No labels found.");
		} else {
			System.out.println("Labels:");
			for (Label label : labels) {
				System.out.printf("- %s\n", label.getName());
			}
		}
		ListMessagesResponse messagesResponse = service.users().messages().list(user).setQ("from:noreply@zomato.com").execute();
		for(Message m : messagesResponse.getMessages()) {
			Message email = service.users().messages().get(user, m.getId()).execute();
			try {
				String c = null;
				if(email.getPayload() != null
						&& email.getPayload().getBody() != null) {
					c = new String(email.getPayload().getBody().decodeData());
				}

				if(email.getPayload() != null
						&& email.getPayload().getParts() != null
						&& !email.getPayload().getParts().isEmpty()
						&& email.getPayload().getParts().get(0).getBody() != null) {
					c = new String(email.getPayload().getParts().get(0).getBody().decodeData(), Charset.forName("UTF-8"));
				}

				System.out.println(m.getId());
				System.out.println(c);
				System.out.println(new String(email.getPayload().getParts().get(0).getParts().get(0).getBody().decodeData(), Charset.forName("UTF-8")));
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println(messagesResponse.getMessages().size());

	}
}
// [END gmail_quickstart]