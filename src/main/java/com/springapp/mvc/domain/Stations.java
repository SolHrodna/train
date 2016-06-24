package com.springapp.mvc.domain;

import javax.persistence.*;

@Entity
@Table(name = "STATIONS")
public class Stations {

	@Id
	@Column(name = "stationId")
	@GeneratedValue
	private Integer stationId;

	@Column(name = "STATION")
	private String station;

	public Stations() {

	}

	public Stations(Integer stationId, String station) {

		this.stationId = stationId;
		this.station = station;
	}

	public Stations(String station) {
		this.station = station;
	}

	public Integer getStationId() {
		return stationId;
	}

	public String getStation() {
		return station;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public void setStation(String station) {
		this.station = station;
	}

}
