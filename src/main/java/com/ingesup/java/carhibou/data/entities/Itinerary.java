package com.ingesup.java.carhibou.data.entities;

import java.io.Serializable;
import javax.persistence.*;
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

	@Column(name="departure_label")
	private String departureLabel;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	//bi-directional many-to-one association to Segment
	@OneToMany(mappedBy="itinerary")
	private List<Segment> segments;

	public Itinerary() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartureLabel() {
		return this.departureLabel;
	}

	public void setDepartureLabel(String departureLabel) {
		this.departureLabel = departureLabel;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Segment> getSegments() {
		return this.segments;
	}

	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}

	public Segment addSegment(Segment segment) {
		getSegments().add(segment);
		segment.setItinerary(this);

		return segment;
	}

	public Segment removeSegment(Segment segment) {
		getSegments().remove(segment);
		segment.setItinerary(null);

		return segment;
	}

}