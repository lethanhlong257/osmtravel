package com.thesis.omstravel.model.DAO.point;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IPointRepository extends MongoRepository<Point, Long> {
    @Override
    <S extends Point> S insert(S entity);

    Point findDistinctFirstByName(String name);
    Point findDistinctFirstByLatAndLon(double lat, double lon);

    List<Point> findPointsByNameContains(String Keyword);

    List<Point> findPointByLat(double lat);

    @Override
    List<Point> findAll();
}
