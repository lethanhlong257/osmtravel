package com.thesis.omstravel.controller.node;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface NodeRepository extends MongoRepository<Node, Long> {
    @Override
    List<Node> findAll();



    List<Node> findNodesByLatBetweenAndLonBetween(String minLat, String maxLat, String  minLon, String maxLon);

    List<Node> findNodesByLatBetween(String minLat, String maxLat);

}
