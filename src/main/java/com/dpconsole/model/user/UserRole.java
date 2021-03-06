package com.dpconsole.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.dpconsole.model.Persistent;

@Entity
@Table(name="user_roles")
@SuppressWarnings("serial")
public class UserRole extends Persistent implements GrantedAuthority {

	private User user;

	private String authority;

	@Override
	@Column(name="authority")
	@Enumerated(value = EnumType.STRING)
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}