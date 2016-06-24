package com.springapp.mvc.domain;

import javax.persistence.*;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ROUTERS")
public class Routers {

	@Id
	@Column(name = "routeId")
	@GeneratedValue
	private Integer routeId;

	@Column(name = "QUEUE")
	private String queue;

	
	public String getQueue() {
		return queue;
	}
	
	

	public void setQueue(String queue) {
		this.queue = queue;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "STATIONS_ROUTERS", joinColumns = { @JoinColumn(name = "ROUTE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "STATION_ID") })
	private Set<Stations> stationsSet = new HashSet<Stations>();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "trainId")
	private Train train;

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Routers() {

	}

	public Routers(Date date, Set<Stations> stationsSet, Train train) {
		
		this.stationsSet = stationsSet;
		this.train = train;
	}

	public void setStationsSet(Set<Stations> stationsSet) {
		this.stationsSet = stationsSet;
		
	}

	public Set<Stations> getStationsSet() {

		return stationsSet;
	}

	public Integer getRouteId() {
		return routeId;
	}

	

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public String getShortName(){
		
		List<Stations> stations = getStationByOrder();
		
		return stations.size()>0 ? stations.get(0).getStation() + " - " + stations.get(stations.size()-1).getStation() : "";
	}


	public List<Stations> getStationByOrder(){
		String[] queue = this.getQueue().split(" ");
		List<Stations> stationses = new ArrayList<Stations>();

		for (int i = 0; i < queue.length; i++) {
			for (Stations stations : this.stationsSet) {

				if (stations.getStationId().equals(Integer.parseInt(queue[i]))) {
					stationses.add(stations);
					break;
				}

			}
		}

		return stationses;
	}
	
	
	

}
