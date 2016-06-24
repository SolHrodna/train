package com.springapp.mvc.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERTRAIN")
public class User {

	@Id
	@Column(name = "userId")
	@GeneratedValue
	private Integer userId;

	@Column(name = "LOGIN")
	private String login;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "FIRSTNAME")
	private String firstname;

	@Column(name = "SECONDNAME")
	private String secondname;

	@Column(name = "ROLE")
	private String role;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Order> orderSet = new HashSet<Order>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Temp> tempSet = new HashSet<Temp>();

	
	
	public Set<Temp> getTempSet() {
		return tempSet;
	}

	public void setTempSet(Set<Temp> tempSet) {
		this.tempSet = tempSet;
	}

	public Set<Order> getOrderSet() {
		return orderSet;
	}

	public void setOrderSet(Set<Order> orderSet) {
		this.orderSet = orderSet;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSecondname() {
		return secondname;
	}

	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {

		return login;
	}

	public String getPassword() {
		return password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
