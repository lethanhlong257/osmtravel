package com.thesis.omstravel.controller.point;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.*;
import java.util.List;

public interface PointRepository extends MongoRepository<Point, Long> { //Long is type of Point Id
    /*
    We just need to define interface base on right pattern, Mongo respository will auto map the implementation
    Read more at: 6.3 Query methods
    https://docs.spring.io/spring-data/mongodb/docs/1.2.0.RELEASE/reference/html/mongo.repositories.html
    */


    @Override
    List<Point> findAll();

    @Override
    List<Point> findAll(Sort sort);

}
