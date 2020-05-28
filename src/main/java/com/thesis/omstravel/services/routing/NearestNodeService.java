package com.thesis.omstravel.services.routing;

import com.thesis.omstravel.model.NodeT;
import com.thesis.omstravel.model.Way;
import com.thesis.omstravel.utils.CalculateDistance;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Component
public class NearestNodeService implements INearestNodeService {
    public NodeT find(List<Way> listWay, String coordinate) {
        NodeT result = null;
        boolean checked = false;
        StringTokenizer st = new StringTokenizer(coordinate, ",");
        double lat = Double.parseDouble(st.nextToken());
        double lon = Double.parseDouble(st.nextToken());

        for (int i = 0; i < listWay.size(); i++) {
            List<NodeT> listNodesOfWay = listWay.get(i).getNodes();
            for (int j = 0; j < listNodesOfWay.size(); j++) {
                if (listNodesOfWay.get(j).getLat() == lat
                        && listNodesOfWay.get(j).getLon() == lon) {
                    result = listNodesOfWay.get(j);
                    checked = true;

                    break;
                }
            }
            if (checked) {
                break;
            }
        }

        return result;
    }

    @Override
    public NodeT findNearestNode(List<NodeT> listNode, String coordinate) {

        CalculateDistance calc = new CalculateDistance();

        List<Double> distOfNodeInWay = new ArrayList<Double>();

        StringTokenizer st = new StringTokenizer(coordinate, ",");
        double lat = Double.parseDouble(st.nextToken());
        double lon = Double.parseDouble(st.nextToken());

        for (int i = 0; i < listNode.size(); i++) {
            distOfNodeInWay.add(calc.getDistance(lat, lon, listNode.get(i)
                    .getLat(), listNode.get(i).getLon()));
        }
        int minPos = findMinDist(distOfNodeInWay);

        return listNode.get(minPos);
    }


    public int findMinDist(List<Double> distanceList) {

        double min = distanceList.get(0);

        for (int i = 0; i < distanceList.size(); i++) {
            if (min >= distanceList.get(i)) {
                min = distanceList.get(i);
            }
        }

        for (int i = 0; i < distanceList.size(); i++) {
            if (min == distanceList.get(i)) {
                return i;
            }
        }

        return 0;
    }


    public List<NodeT> convertToNodeList(List<Way> listWay) {

        List<NodeT> convertedList = new ArrayList<NodeT>();

        for (int i = 0; i < listWay.size(); i++) {
            List<NodeT> nodeOfWay = listWay.get(i).getNodes();
            for (int j = 0; j < nodeOfWay.size(); j++) {
                convertedList.add(nodeOfWay.get(j));
            }
        }

        return convertedList;
    }
}
