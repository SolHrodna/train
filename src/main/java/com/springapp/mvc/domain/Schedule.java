package com.springapp.mvc.domain;



import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SCHEDULE")
public class Schedule {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "day")
	private String day;
	
	@Column(name = "startroute")
	private Time startRoute;
	
	@Column(name = "finishroute")
	private Time finishRoute;
	
	@Column(name = "placesuse")
	private Integer placesuse;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "price")
	private float price;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "routeid")
	private Routers routers;
	

	public Routers getRouters() {
		return routers;
	}
	
	

	public float getPrice() {
		return price;
	}



	public void setPrice(float price) {
		this.price = price;
	}



	public void setRouters(Routers routers) {
		this.routers = routers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}



	public Time getStartRoute() {
		return startRoute;
	}



	public void setStartRoute(Time startRoute) {
		this.startRoute = startRoute;
	}



	public Time getFinishRoute() {
		return finishRoute;
	}



	public void setFinishRoute(Time finishRoute) {
		this.finishRoute = finishRoute;
	}



	public Integer getPlacesuse() {
		return placesuse;
	}

	public void setPlacesuse(Integer placesuse) {
		this.placesuse = placesuse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public LocalTime getStartRouteLoclaTime(){
		return startRoute.toLocalTime();
	}
	

}
