package com.dpconsole.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;

import com.dpconsole.model.user.Role;

public class SecurityUtils {

	public static String generateHash(String password) throws Exception{
		String hashword = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(password.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			hashword = hash.toString(16);
		} catch (NoSuchAlgorithmException nsae) {
			// ignore
		}
		return hashword;
	}

	public static String getLoggedInUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	public static boolean isAdminUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || auth.getAuthorities() == null) {
			return false;
		}
		if(auth.getAuthorities().contains(new GrantedAuthorityImpl(Role.ROLE_ADMIN.getCode()))) {
			return true;
		}
		return false;
	}
}