package com.ingesup.java.carhibou.data.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(unique=true, nullable=false)
	private String email;

	@Lob
	@Column(unique=true, nullable=false)
	private String password;

	private String username;
	
	@Lob
	private String token;

	//bi-directional many-to-one association to Itinerary
	@JsonIgnore
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Itinerary> itineraries;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Notification> notifications;

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getToken() {
		return this.token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	public List<Itinerary> getItineraries() {
		return this.itineraries;
	}

	public void setItineraries(List<Itinerary> itineraries) {
		this.itineraries = itineraries;
	}

	public Itinerary addItinerary(Itinerary itinerary) {
		getItineraries().add(itinerary);
		itinerary.setUser(this);

		return itinerary;
	}

	public Itinerary removeItinerary(Itinerary itinerary) {
		getItineraries().remove(itinerary);
		itinerary.setUser(null);

		return itinerary;
	}

}