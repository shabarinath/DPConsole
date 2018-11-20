package com.dpconsole.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpconsole.domain.UserRole;

@Service("assembler")
public class Assembler {

  @Transactional(readOnly = true)
  User buildUserFromUserEntity(com.dpconsole.domain.User userEntity) {

    String username = userEntity.getUsername();
    String password = userEntity.getPassword();
    boolean enabled = userEntity.getActive();
    // 
    boolean accountNonExpired = !userEntity.getAccountExpired();
    boolean credentialsNonExpired = !userEntity.getCredentialsExpired();
    boolean accountNonLocked = !userEntity.getAccountExpired();
    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for (UserRole role : userEntity.getUserRoles()) {
    	authorities.add(new GrantedAuthorityImpl(role.getAuthority()));
    }

    User user = new User(username, password, enabled,accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    return user;
  }
}