package com.ingesup.java.carhibou.models;

import java.util.List;

public class PostItinerary {
	private LatLng start;
	private LatLng arrival;
	private List<LatLng> points;
	public LatLng getStart() {
		return start;
	}
	public void setStart(LatLng start) {
		this.start = start;
	}
	public LatLng getArrival() {
		return arrival;
	}
	public void setArrival(LatLng arrival) {
		this.arrival = arrival;
	}
	public List<LatLng> getPoints() {
		return points;
	}
	public void setPoints(List<LatLng> points) {
		this.points = points;
	}
	
}
