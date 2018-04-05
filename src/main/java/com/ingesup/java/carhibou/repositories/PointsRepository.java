package com.ingesup.java.carhibou.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ingesup.java.carhibou.data.entities.Point;

public interface PointsRepository extends CrudRepository<Point, Long> {
	List<Point> findAll();
	List<Point> findByLatGreaterThanAndLatLessThanAndLngGreaterThanAndLatLessThan(double minLat, double maxLat, double minLng, double maxLng);
	
	Point findById(int id);
	@SuppressWarnings("unchecked")
	Point save(Point p);
}
