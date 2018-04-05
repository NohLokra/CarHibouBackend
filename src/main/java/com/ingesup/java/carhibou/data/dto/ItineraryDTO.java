package com.ingesup.java.carhibou.data.dto;

import java.util.List;

import com.ingesup.java.carhibou.data.entities.Point;

public class ItineraryDTO {
	private Point start;
	private Point arrival;
	private List<Point> path;
	private int user_id;
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

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
