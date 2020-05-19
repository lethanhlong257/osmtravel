package com.thesis.omstravel.model;

import java.util.ArrayList;
import java.util.List;

public class Way extends Element {
    // ATTRIBUTES
    /**
     * The list of nodes of the way
     **/
    private List<NodeT> nodes;

    // CONSTRUCTOR

    /**
     * Default constructor
     *
     * @param id    The object ID
     * @param nodes Its nodes
     */
    public Way(long id, List<NodeT> nodes) {
        super(id);

        // Conditions on nodes
        if (nodes == null) {
            throw new NullPointerException("Nodes list can't be null");
        }
        if (nodes.size() < 2) {
            throw new RuntimeException("A way should have at least two nodes");
        }

        this.nodes = nodes;
    }

    /**
     * Constructor without nodes, not safe to use ! Don't forget to add at least
     * two nodes
     *
     * @param id The object ID
     */
    public Way(long id) {
        super(id);
        this.nodes = new ArrayList<NodeT>();
    }

    public Way() {
        super();
    }

    // ACCESSORS
    @Override
    public String getId() {
        return "W" + id;
    }

    /**
     * @return The list of nodes of the way
     */
    public List<NodeT> getNodes() {
        return nodes;
    }

    // MODIFIERS

    /**
     * @param n The node to add at the end of the way
     */
    public void addNode(NodeT n) {
        nodes.add(n);
    }

    /**
     * @param index The index of the node to remove
     */
    public void removeNode(int index) {
        if (nodes.size() == 2) {
            throw new RuntimeException("Can't remove node, only two remaining");
        }
        nodes.remove(index);
    }

    @Override
    public String toString() {
        return "Way{" +
                ", id=" + id +
                "nodes=" + nodes +
                ", tags=" + tags +
                '}';
    }
}
