package com.ingesup.java.carhibou.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="commentaries")
@NamedQuery(name="Commentary.findAll", query="SELECT c FROM Commentary c")
public class Commentary implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="content")
	private String content;

	//bi-directional many-to-one association to Itinerary
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="itinerary_id")
	private Itinerary itinerary;

	public Commentary() {
	}
	
	public Commentary(Commentary c) {
		this.id = c.getId();
		this.content = c.getContent();
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

	
}
