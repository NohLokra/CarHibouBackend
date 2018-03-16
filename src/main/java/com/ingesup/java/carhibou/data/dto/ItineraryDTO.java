package com.ingesup.java.carhibou.data.dto;

import java.util.List;

import com.ingesup.java.carhibou.data.entities.Point;

public class ItineraryDTO {
	private Point start;
	private Point arrival;
	private List<Point> path;
	
	public ItineraryDTO() {
		
	}
	
	public Point getStart() {
		return start;
	}
	
	public void setStart(Point start) {
		this.start = start;
	}
	
	public Point getArrival() {
		return arrival;
	}
	
	public void setArrival(Point arrival) {
		this.arrival = arrival;
	}

	public List<Point> getPath() {
		return path;
	}

	public void setPath(List<Point> path) {
		this.path = path;
	}
	
	
	
}
