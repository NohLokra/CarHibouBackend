package com.ingesup.java.carhibou.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingesup.java.carhibou.data.entities.Point;
import com.ingesup.java.carhibou.repositories.PointsRepository;

@Service
public class PointsService {
	@Autowired
	private PointsRepository pointsDao;
	
	public Point save(Point p) {
		return pointsDao.save(p);
	}
	
	public Point findById(int id) {
		return pointsDao.findById(id);
	}
}
