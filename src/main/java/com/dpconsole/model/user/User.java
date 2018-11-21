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

	@Column(name="username",nullable=false)
	private String username;

	@Column(name="password",nullable=false)
	private String password;

	@Column(name="account_expired",nullable=false)
	private boolean accountExpired=false;

	@Column(name="credentials_expired",nullable=false)
	private boolean credentialsExpired=false;

	@Column(name="account_locked",nullable=false)
	private boolean accountLocked = false;

	@OneToMany(mappedBy="user",fetch=FetchType.EAGER,cascade={CascadeType.ALL,CascadeType.MERGE})
	private List<UserRole> userRoles = new ArrayList<UserRole>();

	@Transient
	private transient String currentPassword;

	@Transient
	private transient String confirmPassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean getCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public boolean getAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}