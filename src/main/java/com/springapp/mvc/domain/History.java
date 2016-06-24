package com.springapp.mvc.domain;





import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HISTORY")
public class History {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "startroute")
	private Time startRoute;
	
	@Column(name = "finishroute")
	private Time finishRoute;
	
	@Column(name = "places")
	private Integer places;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "NUMBER")
	private String number;

	@Column(name = "MODEL")
	private String model;

	@Column(name = "NUMPLACES")
	private Integer numPlaces;
	
	@Column(name = "SHORTNAME")
	private String shortName;
	
	
	@Column(name = "scheduleId")
	private Integer scheduleId;
	
	
	
	

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
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

	public Integer getPlaces() {
		return places;
	}

	public void setPlaces(Integer places) {
		this.places = places;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getNumPlaces() {
		return numPlaces;
	}

	public void setNumPlaces(Integer numPlaces) {
		this.numPlaces = numPlaces;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
