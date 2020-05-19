package com.thesis.omstravel.model.DAO.wayDAO;

import com.thesis.omstravel.model.DAO.nodeDAO.NodeDAO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IWayDAORepository extends MongoRepository<WayDAO, Long> {
    @Override
    List<WayDAO> findAll();
}
