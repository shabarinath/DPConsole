package com.dpconsole.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
@Entity
@Table(name="user_roles")
public class UserRole implements GrantedAuthority{
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
    private User user;
	
	@Column(name="authority")
	@Enumerated(value = EnumType.STRING)
	private String authority;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
	
}
