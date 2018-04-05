package com.ingesup.java.carhibou.data.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the segments database table.
 * 
 */
@Entity
@Table(name="points")
@NamedQuery(name="Point.findAll", query="SELECT p FROM Point p")
public class Point implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="lat")
	private double lat;

	@Column(name="lng")
	private double lng;

	//bi-directional many-to-one association to Itinerary
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="itinerary_id")
	private Itinerary itinerary;

	public Point() {
	}
	
	public Point(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}
	
	public String toString() {
		return String.valueOf(lat) + ":" + String.valueOf(lng);
	}

}