package com.dpconsole.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.dpconsole.model.BaseDo;


@Entity
@Table(name="users")
@DiscriminatorColumn(name = "class_code")
@SuppressWarnings("serial")
public class User extends BaseDo {

	private String username;

	private String password;

	private boolean accountExpired = false;

	private boolean credentialsExpired = false;

	private boolean accountLocked = false;

	private List<UserRole> userRoles = new ArrayList<UserRole>();

	private transient String currentPassword;

	private transient String confirmPassword;

	@Column(name="username",nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="password",nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="account_expired",nullable=false)
	public boolean getAccountExpired() {
		return accountExpired;
	}
	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	@Column(name="credentials_expired",nullable=false)
	public boolean getCredentialsExpired() {
		return credentialsExpired;
	}
	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	@Column(name="account_locked",nullable=false)
	public boolean getAccountLocked() {
		return accountLocked;
	}
	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	@OneToMany(mappedBy="user",fetch=FetchType.EAGER,cascade={CascadeType.ALL,CascadeType.MERGE})
	public List<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Transient
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	@Transient
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}