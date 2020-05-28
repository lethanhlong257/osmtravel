package com.thesis.omstravel.utils;

import com.thesis.omstravel.model.NodeT;
import com.thesis.omstravel.model.RoutingResult;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DijkstraRouting {
    public RoutingResult findDijkstraShortestPath(NodeT startNode, NodeT endNode) {

        // Initialize variable
        RoutingResult result = new RoutingResult();
        LinkedList<NodeT> resultNodeList = new LinkedList<NodeT>();
        NodeT currentNode = startNode;
        List<NodeT> expandingNodeList = new ArrayList<NodeT>();
        // Initialize class
        CalculateDistance calc = new CalculateDistance();

        // Main loop
        // ==================================================
        int countProblem = 0;
        NodeT checkProblemNode = null;
        // ==================================================
        while (!currentNode.equals(endNode)) {
            // ==================================================
            if (currentNode.equals(checkProblemNode)) {
                countProblem++;
            }
            if (countProblem > 50) {
                break;
            }
            // ==================================================
            // Initialize new nodes
            LinkedList<NodeT> shortestPath = new LinkedList<NodeT>();
            shortestPath = currentNode.getShortestPath();
            shortestPath.add(currentNode);
            boolean endIn2 = false;

            for (int i = 0; i < currentNode.getAdjNode().size(); i++) {
                NodeT checkingNode = currentNode.getAdjNode().get(i);

                if (currentNode.equals(startNode) && checkingNode.equals(endNode)) {
                    double distFromCurrentToCheckingNode = calc
                            .calcDistanceOfTwoNodes(currentNode, checkingNode);
                    currentNode.getAdjNode().get(i)
                            .setDistance(distFromCurrentToCheckingNode);
                    expandingNodeList.add(checkingNode);
                    currentNode.getAdjNode().get(i)
                            .setShortestPath(shortestPath);
                    currentNode = checkingNode;
                    endIn2 = true;
                    break;
                }

                if (!checkingNode.isChecked()) {

                    double distFromCurrentToCheckingNode = calc
                            .calcDistanceOfTwoNodes(currentNode, checkingNode);

                    double distFromStart = currentNode.getDistance()
                            + distFromCurrentToCheckingNode;

                    // New nodes have distance = 0
                    if (checkingNode.getDistance() == 0) {

                        // Add new node to expanding list
                        expandingNodeList.add(checkingNode);

                        // Set distance for new node
                        currentNode.getAdjNode().get(i)
                                .setDistance(distFromStart);

                        // Set path to new node
                        currentNode.getAdjNode().get(i)
                                .setShortestPath(shortestPath);

                    } else { // Check old nodes
                        if (distFromStart < checkingNode.getDistance()) {
                            currentNode.getAdjNode().get(i)
                                    .setDistance(distFromStart);

                            currentNode.getAdjNode().get(i)
                                    .setShortestPath(shortestPath);
                            for (int m = 0; m < currentNode.getAdjNode().get(i) // log
                                    .getShortestPath().size(); m++) {
                            }
                        }
                    }

                }
            }

            // ==================================================
            checkProblemNode = currentNode;
            // ==================================================

            // Find minimal distance
            if (!endIn2) {
                int indexMinNode = findMinNode(expandingNodeList);
                currentNode = expandingNodeList.get(indexMinNode);
                System.out.println("Current: " + currentNode.getId());
                expandingNodeList.get(indexMinNode).setChecked(true);
            }
        }

        System.out.println("\nShortest distance: " + currentNode.getDistance());
        // set result's distance
        result.setDistance(currentNode.getDistance());
        for (int i = 0; i < currentNode.getShortestPath().size(); i++) {
            resultNodeList.add(currentNode.getShortestPath().get(i));
        }
        resultNodeList.add(currentNode);

        System.out.println("\nTraversed node: " + expandingNodeList.size());
        // Clear data
        for (int i = 0; i < expandingNodeList.size(); i++) {
            expandingNodeList.get(i).clearData();
        }
        startNode.clearData();

        System.out.println("\nRouting result: ");
        for (int i = 0; i < resultNodeList.size(); i++) {
            System.out.print(" " + resultNodeList.get(i).getId());
        }
        System.out.println("\nRouting size: " + resultNodeList.size());

        result.setShortestPath(resultNodeList);
        return result;
    }

    private int findMinNode(List<NodeT> expandingNodeList1) {
        int count = 0;
        double minDist = 0;
        int indexMinNode = 0;

        // Set min for any node that dist > 0
        while (count < expandingNodeList1.size()) {
            NodeT temp = expandingNodeList1.get(count);
            if (temp.getDistance() > 0 && !temp.isChecked()) {
                minDist = temp.getDistance();
                indexMinNode = count;
                break;
            }
            count++;
        }

        // Find the Node that has the shortest distance
        for (int i = 0; i < expandingNodeList1.size(); i++) {
            NodeT checkingNode = expandingNodeList1.get(i);
            if (!checkingNode.isChecked()
                    && minDist > checkingNode.getDistance()) {
                minDist = checkingNode.getDistance();
                indexMinNode = i;
            }
        }
        return indexMinNode;
    }

    public void writeTextFile(File output, String text) throws IOException {
        Writer w = new OutputStreamWriter(new FileOutputStream(output));
        w.write(text);
        w.close();
    }
}
