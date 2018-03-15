package com.ingesup.java.carhibou.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the segments database table.
 * 
 */
@Entity
@Table(name="segments")
@NamedQuery(name="Segment.findAll", query="SELECT s FROM Segment s")
public class Segment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="end_lat_lng")
	private String endLatLng;

	@Column(name="start_lat_lng")
	private String startLatLng;

	//bi-directional many-to-one association to Itinerary
	@ManyToOne
	@JoinColumn(name="itinerary_id")
	private Itinerary itinerary;

	public Segment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEndLatLng() {
		return this.endLatLng;
	}

	public void setEndLatLng(String endLatLng) {
		this.endLatLng = endLatLng;
	}

	public String getStartLatLng() {
		return this.startLatLng;
	}

	public void setStartLatLng(String startLatLng) {
		this.startLatLng = startLatLng;
	}

	public Itinerary getItinerary() {
		return this.itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

}