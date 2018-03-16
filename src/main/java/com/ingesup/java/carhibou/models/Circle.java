package com.ingesup.java.carhibou.models;

import com.ingesup.java.carhibou.data.entities.Point;

public class Circle {
	private Point center;
	private int radius;
	
	public Circle(double latitude, double longitude, int radius) {
		this.center = new Point();
		this.center.setLat(latitude);
		this.center.setLng(longitude);
		
		this.radius = radius;
	}
	
	public Point getCenter() {
		return center;
	}
	
	public void setCenter(Point center) {
		this.center = center;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public boolean hasPoint(Point point) {
		// Calcul des écarts en degrés de longitude et degrés de latitude
		double xDist = point.getLng() - this.getCenter().getLng();
		double yDist = point.getLat() - this.getCenter().getLat();
		
		final int earthRadius = 6378;
		
		// Calculs des mètre par degré de longitude et par degré de latitude
		double metersPerLongitudeDegree = 6.48 * earthRadius * Math.cos(point.getLat());
		double metersPerLatitudeDegree = 2 * Math.PI / 360 * earthRadius;
		
		// Conversion des distances en mètre
		xDist *= metersPerLatitudeDegree;
		yDist *= metersPerLongitudeDegree;
		
		// Calcul des valeurs pour le théorème de Pythagore
		double xValue = Math.pow(xDist, 2);
		double yValue = Math.pow(yDist, 2);
		
		// De par les conversions précédentes, ici on est a la distance en mètres
		double distance = Math.sqrt(xValue + yValue);
		
		if ( distance <= radius ) {
			return true;
		} else {
			return false;
		}
	}
}
