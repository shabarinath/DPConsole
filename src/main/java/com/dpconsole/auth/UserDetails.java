package com.dpconsole.auth;

import java.util.Collection;
import java.util.List;
import java.util.TimeZone;

import org.springframework.security.core.GrantedAuthority;

import com.dpconsole.model.user.User;

/**
 * @author Shabarinath.Volam
 */
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

	private static final long serialVersionUID = -5606540655147546201L;
	private List<GrantedAuthority> authorities = null;
	private boolean accountNonLocked;
	private User user;//(mainly for getUserName and getPassword)

	private TimeZone timeZone = null;//(time zone against user in db is setted)

	public UserDetails(User dbUser) {
		this.user = dbUser;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public String getTimeZoneShort() {
		if(timeZone != null){
			return timeZone.getDisplayName(false, TimeZone.SHORT);
		}
		return null;
	}

	public long getUserId() {
		return user.getId();
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
}