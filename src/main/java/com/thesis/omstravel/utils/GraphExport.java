package com.thesis.omstravel.utils;

import com.thesis.omstravel.model.NodeT;
import com.thesis.omstravel.model.Relation;
import com.thesis.omstravel.model.Way;

import java.util.*;

public class GraphExport {
    private static int ONE_WAY = 1;
    private static int NORMAL_WAY = 2;
    private static int REMOVED_WAY = 3;
//	private static final int U_TURN = 4;
//	private static final int OTHER_TURN = 5;

    private static final String[] checkHighWayList = { "motorway", "trunk",
            "primary", "secondary", "tertiary", "unclassified", "service",
            "motorway_link", "trunk_link", "primary_link", "secondary_link",
            "tertiary_link", "living_street", "	track", "road", "residential" };

    public List<NodeT> buildGraph(List<Way> listWay, List<Relation> listRelation) {

        List<NodeT> listGraphNode = new ArrayList<NodeT>();

        for (int i = 0; i < listWay.size(); i++) {
            Way wayInList = listWay.get(i);

            int checkingCondition2 = REMOVED_WAY;

            @SuppressWarnings("rawtypes")
            Set set = wayInList.getTags().entrySet();
            @SuppressWarnings("rawtypes")
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                @SuppressWarnings("rawtypes")
                Map.Entry me = (Map.Entry) iterator.next();

                if (me.getKey().equals("highway")) {
                    for (int z = 0; z < checkHighWayList.length; z++) {
                        if (me.getValue().toString()
                                .contains(checkHighWayList[z])) {
                            checkingCondition2 = NORMAL_WAY;
                            break;
                        }
                    }
                }

                if (me.getKey().equals("service")) {
                    if (me.getValue().toString().equals("alley")
                            || me.getValue().toString().equals("drive-through")) {
                        checkingCondition2 = REMOVED_WAY;
                        break;
                    }
                }
            }

            if (checkingCondition2 != NORMAL_WAY) {
                listWay.remove(i);
            }
            checkingCondition2 = REMOVED_WAY;
        }


        for (int i = 0; i < listWay.size(); i++) {
            System.out.println("way index "+i);
            Way wayInList = listWay.get(i);
            int oneWay = 0;
            int checkingCondition = REMOVED_WAY;

            @SuppressWarnings("rawtypes")
            Set set = wayInList.getTags().entrySet();
            @SuppressWarnings("rawtypes")
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                @SuppressWarnings("rawtypes")
                Map.Entry me = (Map.Entry) iterator.next();

                if (me.getKey().equals("highway")) {
                    for (int z = 0; z < checkHighWayList.length; z++) {
                        if (me.getValue().toString()
                                .contains(checkHighWayList[z])) {
                            checkingCondition = NORMAL_WAY;
                            break;
                        }
                    }
                }

                if (me.getKey().equals("service")) {
                    if (me.getValue().toString().equals("alley")
                            || me.getValue().toString().equals("drive-through")) {
                        checkingCondition = REMOVED_WAY;
                        break;
                    }
                }

                // Check if oneway
                if (me.getKey().equals("oneway")) {
                    if (me.getValue().equals("yes")) {
                        oneWay = ONE_WAY;
                    }
                }
            }

            if (checkingCondition != NORMAL_WAY) {
                continue;
            }


            List<NodeT> listNodesOfWay = listWay.get(i).getNodes();
            for (int j = 0; j < listNodesOfWay.size(); j++) {

                // add each node to graph
                NodeT currentNode = listNodesOfWay.get(j);

                if (!checkExistNode(currentNode, listGraphNode)) {
                    // Store Adjacent Nodes in same way
                    if (j == 0) {
                        if (!checkExistNode(listNodesOfWay.get(1),
                                currentNode.getAdjNode())) {
                            currentNode.getAdjNode().add(listNodesOfWay.get(1));
                        }
                    } else if (j == listNodesOfWay.size() - 1) {
                        if (oneWay != ONE_WAY) {
                            if (!checkExistNode(listNodesOfWay.get(j - 1),
                                    currentNode.getAdjNode())) {
                                currentNode.getAdjNode().add(
                                        listNodesOfWay.get(j - 1));
                            }
                        }
                    } else {
                        if (oneWay != ONE_WAY) {
                            if (!checkExistNode(listNodesOfWay.get(j - 1),
                                    currentNode.getAdjNode())) {
                                currentNode.getAdjNode().add(
                                        listNodesOfWay.get(j - 1));
                            }
                        }
                        if (!checkExistNode(listNodesOfWay.get(j + 1),
                                currentNode.getAdjNode())) {
                            currentNode.getAdjNode().add(
                                    listNodesOfWay.get(j + 1));
                        }
                    }

                    // Store Adjacent Nodes in Different Way
                    for (int k = 0; k < listWay.size(); k++) {
                        if (k != i) {
                            // ======================================================
                            Way tempWay = listWay.get(k);
                            int checkLoop2 = REMOVED_WAY;
                            int oneWay2 = 0;

                            @SuppressWarnings("rawtypes")
                            Set set2 = tempWay.getTags().entrySet();
                            @SuppressWarnings("rawtypes")
                            Iterator iterator2 = set2.iterator();
                            while (iterator2.hasNext()) {
                                @SuppressWarnings("rawtypes")
                                Map.Entry me = (Map.Entry) iterator2.next();

                                if (me.getKey().equals("highway")) {
                                    for (int z = 0; z < checkHighWayList.length; z++) {
                                        if (me.getValue().toString()
                                                .contains(checkHighWayList[z])) {
                                            checkLoop2 = NORMAL_WAY;
                                            break;
                                        }
                                    }
                                }

                                if (me.getKey().equals("service")) {
                                    if (me.getValue().toString()
                                            .equals("alley")
                                            || me.getValue().toString()
                                            .equals("drive-through")) {
                                        checkLoop2 = REMOVED_WAY;
                                    }
                                }
                                if (me.getKey().equals("oneway")) {
                                    if (me.getValue().equals("yes")) {
                                        oneWay2 = ONE_WAY;
                                    }
                                }
                            }
                            if (checkLoop2 != NORMAL_WAY) {
                                continue;
                            }
                            // ===========================================
                            List<NodeT> listNodesOfWay2 = tempWay.getNodes();
                            for (int m = 0; m < listNodesOfWay2.size(); m++) {
                                NodeT lookingNode = listNodesOfWay2.get(m);

                                if (currentNode.getId().equals(
                                        lookingNode.getId())) {

                                    if (m == 0) {
                                        if (!checkExistNode(
                                                listNodesOfWay2.get(1),
                                                currentNode.getAdjNode())) {
                                            currentNode.getAdjNode().add(
                                                    listNodesOfWay2.get(1));
                                        }
                                    } else if (m == listNodesOfWay2.size() - 1) {
                                        if (oneWay2 != ONE_WAY) {
                                            if (!checkExistNode(
                                                    listNodesOfWay2.get(m - 1),
                                                    currentNode.getAdjNode()))
                                                currentNode.getAdjNode().add(
                                                        listNodesOfWay2
                                                                .get(m - 1));
                                        }
                                    } else {
                                        if (oneWay2 != ONE_WAY) {
                                            if (!checkExistNode(
                                                    listNodesOfWay2.get(m - 1),
                                                    currentNode.getAdjNode())) {
                                                currentNode.getAdjNode().add(
                                                        listNodesOfWay2
                                                                .get(m - 1));
                                            }
                                        }
                                        if (!checkExistNode(
                                                listNodesOfWay2.get(m + 1),
                                                currentNode.getAdjNode())) {
                                            currentNode.getAdjNode().add(
                                                    listNodesOfWay2.get(m + 1));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    listGraphNode.add(currentNode);
                }
            }
        }

        return listGraphNode;
    }

    private boolean checkExistNode(NodeT nodeT, List<NodeT> listNode) {
        if (listNode == null) {
            return false;
        }

        for (int i = 0; i < listNode.size(); i++) {
            if (nodeT.getId().equals(listNode.get(i).getId())) {
                return true;
            }
        }
        return false;
    }
}
