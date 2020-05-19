package com.thesis.omstravel.model.DAO.wayDAO;

import com.thesis.omstravel.model.Way;

import java.util.List;

public interface IEnhancedWayDAORepository {
    List<Way> findAllWays();
}
