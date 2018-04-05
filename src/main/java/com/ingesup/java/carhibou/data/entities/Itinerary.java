package com.ingesup.java.carhibou.data.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the itineraries database table.
 * 
 */
@Entity
@Table(name="itineraries")
@NamedQuery(name="Itinerary.findAll", query="SELECT i FROM Itinerary i")
public class Itinerary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="start")
	private String start;

	@Column(name="arrival")
	private String arrival;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	//bi-directional many-to-one association to Segment
	@OneToMany(mappedBy="itinerary")
	private List<Point> points;
	
	@OneToMany(mappedBy="itinerary")
	private List<Commentary> commentaries;

	public Itinerary() {
	}
	
	public Itinerary(Itinerary i) {
		this.points = i.getPoints();
		this.setStart(i.getStartAsLatLng());
		this.setArrival(i.getArrivalAsLatLng());
		this.user = i.getUser();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Point getStartAsLatLng() {
		String[] latAndLng = this.start.split(";");
		double lat = Double.parseDouble(latAndLng[0]);
		double lng = Double.parseDouble(latAndLng[1]);
		
		return new Point(lat, lng);
	}
	
	public String getStart() {
		return this.start;
	}

	public void setStart(Point start) {
		this.start = start.getLat() + ";" + start.getLng();
	}

	public Point getArrivalAsLatLng() {
		String[] latAndLng = this.arrival.split(";");
		double lat = Double.parseDouble(latAndLng[0]);
		double lng = Double.parseDouble(latAndLng[1]);
		
		return new Point(lat, lng);
	}

	public String getArrival() {
		return this.arrival;
	}

	public void setArrival(Point arrival) {
		this.arrival = arrival.getLat() + ";" + arrival.getLng();
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Point> getPoints() {
		return this.points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public Point addPoint(Point point) {
		getPoints().add(point);
		point.setItinerary(this);

		return point;
	}

	public Point removePoint(Point segment) {
		getPoints().remove(segment);
		segment.setItinerary(null);

		return segment;
	}
}