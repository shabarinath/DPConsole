package com.dpconsole.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpconsole.model.user.UserRole;

@Service("assembler")
public class Assembler {

	@Transactional(readOnly = true)
	User buildUserFromUserEntity(com.dpconsole.model.user.User userEntity) {

		String username = userEntity.getUsername();
		String password = userEntity.getPassword();
		boolean enabled = userEntity.isActive();
		boolean accountNonExpired = !userEntity.getAccountExpired();
		boolean credentialsNonExpired = !userEntity.getCredentialsExpired();
		boolean accountNonLocked = !userEntity.getAccountExpired();
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (UserRole role : userEntity.getUserRoles()) {
			authorities.add(new GrantedAuthorityImpl(role.getAuthority()));
		}

		return new User(username, password, enabled,accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
}