package com.dpconsole.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpconsole.dao.UserDao;
import com.dpconsole.model.user.User;

@Service("userDetailsService")
public class UserAuthenticationService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationService.class);

	private UserDao userDao;
	private Assembler assembler;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		User userEntity = null;
		try {
			userEntity = userDao.getUserByUsername(username);
		} catch (Exception e) {
			logger.error("Failed to load user: " + username, e);
		}
		if (userEntity == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return assembler.buildUserFromUserEntity(userEntity);
	}

	@Transactional(readOnly = true)
	public User loadUserObjectByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		User userEntity = userDao.getUserByUsername(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return userEntity;
	}

	@Autowired
	@Required
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	@Required
	public void setAssembler(Assembler assembler) {
		this.assembler = assembler;
	}

}