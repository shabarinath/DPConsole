package com.dpconsole.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordEncoder extends Md5PasswordEncoder {

	@Override
	public String encodePassword(String arg0, Object arg1) throws DataAccessException {
		return super.encodePassword(arg0,null);
	}
	
	@Override
	public boolean isPasswordValid(String encPasswd, String plainPasswd, Object salt) throws DataAccessException {
		return super.isPasswordValid(encPasswd, plainPasswd, null);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PasswordEncoder end = new PasswordEncoder();
		end.setEncodeHashAsBase64(false);
		//e569646e2344debde78cfd644e3bfb94
		System.out.println(end.encodePassword("change.me", null));
		//5e8edd851d2fdfbd7415232c67367cc3
		
		System.out.println(end.isPasswordValid("cfed2815f33f81ed7c13f8fc0ce28714","change.me", null));
	}

}
