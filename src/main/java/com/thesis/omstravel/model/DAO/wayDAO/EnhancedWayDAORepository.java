package com.thesis.omstravel.model.DAO.wayDAO;

import com.thesis.omstravel.model.Way;
import com.thesis.omstravel.utils.WayDAO2Way;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EnhancedWayDAORepository implements IEnhancedWayDAORepository {
    @Autowired
    IWayDAORepository wayDAORepository;

    @Autowired
    WayDAO2Way wayDAO2Way;

    @Override
    public List<Way> findAllWays() {
        List<WayDAO> wayDAOList = wayDAORepository.findAll();
        List<Way> wayList = new ArrayList<>();
        for (WayDAO wayDAO : wayDAOList) {
            Way way = wayDAO2Way.convert(wayDAO);
            wayList.add(way);
        }
        return wayList;
    }
}
