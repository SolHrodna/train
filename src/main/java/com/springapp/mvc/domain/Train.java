package com.springapp.mvc.domain;

import javax.persistence.*;

@Entity
@Table(name = "TRAIN")
public class Train {

	@Id
	@Column(name = "trainId")
	@GeneratedValue
	private Integer trainId;

	@Column(name = "NUMBER")
	private String number;

	@Column(name = "MODEL")
	private String model;

	@Column(name = "NUMPLACES")
	private Integer numPlaces;

	
	public Train() {

	}

	public Train(String number, String model, Integer numPlaces) {
		this.number = number;
		this.model = model;
		this.numPlaces = numPlaces;
	}

	public Integer getTrainId() {
		return trainId;
	}

	public String getNumber() {
		return number;
	}

	public String getModel() {
		return model;
	}

	public Integer getNumPlaces() {
		return numPlaces;
	}

	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setNumPlaces(Integer numPlaces) {
		this.numPlaces = numPlaces;
	}
}
