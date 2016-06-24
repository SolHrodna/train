package com.springapp.mvc.domain;

import javax.persistence.*;

@Entity
@Table(name = "ORDERTRAIN")
public class Order {

	@Id
	@Column(name = "orderId")
	@GeneratedValue
	private Integer orderId;

	@Column(name = "STATUS")
	private String status;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "routeId")
	private Routers routers;

	public Routers getRouters() {
		return routers;
	}

	public void setRouters(Routers routers) {
		this.routers = routers;
	}

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	public Order() {

	}

	public Order(String status, User user) {

		this.status = status;
		this.user = user;
	}

	public Order(String status) {

		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;

	}
}
