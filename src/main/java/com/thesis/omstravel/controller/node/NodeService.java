package com.thesis.omstravel.controller.node;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class NodeService implements INodeService {

    @Autowired
    NodeRepository nodeRepository;

    // A degree of longitude is widest at the equator at 69.172 miles (111.321) and gradually shrinks to zero at the poles
    public final static double KM_EQUATOR_IN_DEGREE = 111.321;

    /**
     *
     * Each degree of latitude is approximately 69 miles (111 kilometers) apart.
     * The range varies (due to the earth's slightly ellipsoid shape) from 68.703 miles (110.567 km)
     * at the equator to 69.407 (111.699 km) at the poles.
     *
     */
    public final static double LATITUDE_CONSTANT_KM = 111;
    public final static double MILE_EQUATOR_IN_DEGREE = 69.172;
    public final static String KM_UNIT = "KM";
    public final static String MILE_UNIT = "MILE";
    public final static String DIGIT_ROUND = "#.#######";
    public static DecimalFormat format = new DecimalFormat(DIGIT_ROUND);

    @Override
    public List<Node> listNodeInDistance(double lat, double lon, double distance) {
        List listNodesInDistance = new ArrayList();

        double latVicinity = diffLatidute(distance, KM_UNIT);
        double lonVicinity = diffLongtitude(distance, KM_UNIT,lat);

        String maxLat = format.format(lat + latVicinity);
        String minLat = format.format(lat - latVicinity);
        String maxLon = format.format(lon + lonVicinity);
        String minLon = format.format(lon - lonVicinity);

        listNodesInDistance = nodeRepository.findNodesByLatBetweenAndLonBetween(minLat, maxLat, minLon, maxLon);

        return listNodesInDistance;
    }

    @Override
    public List<Node> listNodeInDistanceByLat(double lat, double distance) {
        List listNodesInDistance;

        double latVicinity = diffLatidute(distance, KM_UNIT);

        String maxLat = format.format(lat + latVicinity);
        String minLat = format.format(lat - latVicinity);

        listNodesInDistance = nodeRepository.findNodesByLatBetween(minLat, maxLat);

        return listNodesInDistance;
    }

    public double diffLongtitude(double dist, String unit, double degree) {
        double diffDeg = 0.00000;
        double kmPerDegree = 0.00000;
        if (unit.equalsIgnoreCase(KM_UNIT)) {
            kmPerDegree = kmPerDegree(degree);
            diffDeg = dist/kmPerDegree;
            System.out.println(kmPerDegree);
        } else if (unit.equalsIgnoreCase(MILE_UNIT)) {
            // Mile code here
        } else {
            System.out.println("Uni khong ro rang. km hoac mile");
        }
        return Double.parseDouble(format.format(diffDeg));
    }

    public double diffLatidute(double dist, String unit) {
        double diffLat = 0.00000;
        if (unit.equalsIgnoreCase(KM_UNIT)) {
            diffLat = dist/LATITUDE_CONSTANT_KM;
        } else if (unit.equalsIgnoreCase(MILE_UNIT)) {
            // Mile code here
        } else {
            System.out.println("Uni khong ro rang. km hoac mile");
        }
        return Double.parseDouble(format.format(diffLat));

    }

    public double kmPerDegree(double degree) {
        double radDegree = Math.toRadians(degree);
        double kmPerDegree = KM_EQUATOR_IN_DEGREE * Math.cos(radDegree);
        return Double.parseDouble(format.format(kmPerDegree));
    }
}
