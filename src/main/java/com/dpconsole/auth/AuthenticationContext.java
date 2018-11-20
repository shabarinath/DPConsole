package com.dpconsole.auth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dpconsole.controller.ChangePasswordController;

/**
 * 
 * @author Shabarinath.Volam
 * created on MAR 01, 2014 04:14:26 PM
 */

public final class AuthenticationContext {
	
	private static final Logger logger = LoggerFactory.getLogger(ChangePasswordController.class);
	
	private AuthenticationContext() {	}

	/**
	 * @return loggedin user id
	 */
	public static long getCurrentUserId() {
		UserDetails details = getCurrentUserDetails();
		return details != null ? details.getUserId() : 0;
	}
	
	/**
	 * @return current loggedin username
	 */
	
	/**
	 * @return Returns current user time zone. If user not yet logged in this will return browser timezone.
	 */
	public static TimeZone getCurrentUserTimeZone(){
		try {
			if (getCurrentUserDetails() != null && getCurrentUserDetails().getTimeZone() != null) 
				return getCurrentUserDetails().getTimeZone();
			if (getServletRequest().getSession().getAttribute("userOffsetTime") != null) {
				String[] availIds = TimeZone.getAvailableIDs(Integer.parseInt((String) getServletRequest().getSession().getAttribute("userOffsetTime"))* 60 * 1000);
				if (availIds != null && availIds.length > 0) 
					return TimeZone.getTimeZone(availIds[0]);
			}
		} catch (Exception e) {
			logger.warn("Failed to get user TimeZone" + e.getMessage());
		}
		return TimeZone.getDefault();
	}
	
	/**
	 * @return current time string. if user logged in, returns timestring in his timezone. if user is not loggedin, it returns browser time
	 */
	public static String getCurrentTimeString() {
		TimeZone timezone = getCurrentUserTimeZone();
		Calendar today = Calendar.getInstance(timezone);
		SimpleDateFormat hourFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
		hourFormat.setTimeZone(timezone);
		return hourFormat.format(today.getTime());
	}
	
	/**
	 * @return request object - written only to use in this class
	 */
	public static ServletRequestAttributes getServletRequestAttributes() {
		return (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
	}
	
	public static HttpServletRequest getServletRequest() {
		return getServletRequestAttributes().getRequest(); 
	}
	
	/**
	 * @return load userdetails object
	 */
	public static UserDetails getCurrentUserDetails() {
		try{
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(principal != null && principal instanceof UserDetails ) {
				return (UserDetails) principal;
			}
		}catch (Exception e) {
			logger.warn("Failed to load userdetails object" + e.getMessage());
		}
		return null;
	}
}