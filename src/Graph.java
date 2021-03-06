import java.util.ArrayList;
/**
 *
 * @author MattFriedman
 *
 *
 */

public class Graph {
	private boolean directed = Display.isDirected;
	private boolean weighted = Display.isWeighted;
	private ArrayList<Node> nodes = new ArrayList<Node>();
	
	/** 
	 * @return the arraylist of nodes
	 */
	public ArrayList<Node> getNodes() {
	    return new ArrayList<Node>(nodes);
	}
	
	/** 
	 * 
	 * @param isWeighted, given input of whether graph should be weighted
	 */
	public void setWeighted(boolean isWeighted) {
	    this.weighted = isWeighted;
	}
	/**
	 * Sets the boolean of whether graph is directed or not
	 * @param isDirected
	 */
	public void setDirected(boolean isDirected) {
        this.directed = isDirected;
    }
	
	/**
	 * gets whether graph is directed
	 * @return returns true if directed, false if undirected
	 */
	public boolean getDirected() {
        return directed;
    }

	/**
	 * Adding an edge given the node names and weight
	 * @param n1 - first node, where the edge starts from
	 * @param edgeWeight - weight of the edge, 1 if unweighted
	 * @param n2 - adjacent node in the edge
	 * @return boolean of whether edge was added. If one of the noded doesn't exist, 
	 * or edge already exists, will return false
	 */
	public boolean addEdge(String n1, int edgeWeight, String n2) {
		boolean added = true;
		Node node1 = getNodeFromName(n1);
		Node node2 = getNodeFromName(n2);

		if (weighted) {
			if (directed) {
				added = added && node1.addEdge(edgeWeight, n2);
			} else {
				added = added && node1.addEdge(edgeWeight, n2);
				added = added && node2.addEdge(edgeWeight, n1);
			}
		} else {
			if (directed) {
				added = added && node1.addEdge(1, n2);
			} else {
				added = added && node1.addEdge(1, n2);
				added = added && node2.addEdge(1, n1);
			}
		}
		return added;
	}

	/**
	 * helper method to add edges in the implementation of Prim's algorithm
	 * @param node1 the starting {@code Node} object
	 * @param edgeWeight the weight of the edge
	 * @param node2 the ending {@code Node} object
	 * @return true if the specified edge does not already exist in the graph and has been successfully added
	 * and false otherwise
	 */
	public boolean addEdge(Node node1, int edgeWeight, Node node2) {
		boolean added = true;
		if (weighted) {
			if (directed) {
				added = added && node1.addEdge(edgeWeight, node2);
			} else {
				added = added && node1.addEdge(edgeWeight, node2);
				added = added && node2.addEdge(edgeWeight, node1);
			}
		} else {
			if (directed) {
				added = added && node1.addEdge(1, node2);
			} else {
				added = added && node1.addEdge(1, node2);
				added = added && node2.addEdge(1, node1);
			}
		}
		return added;
	}
	
	/**
	 * deleted edge between 2 nodes
	 * @param n1 - starting node
	 * @param n2 - ending node
	 * @return boolean of whether the edge was deleted, false if edge doesn't exist
	 */
	public boolean deleteEdge(String n1, String n2) {
		boolean removed = true;
		Node node1 = getNodeFromName(n1);
		Node node2 = getNodeFromName(n2);
		if (directed) {
			removed = removed && node1.removeEdge(n2);
		} else {
			removed = removed && node1.removeEdge(n2);
			removed = removed && node2.removeEdge(n1);
		}
		return removed;
	}

	/**
	 * adds a node to the graph
	 * @param n1 - string name of the node to be added
	 * @return whether the node was added
	 */
	public boolean addNode(String n1) {
		for (Node n: nodes) {
			if (n.getName().equals(n1)) {
				return false;
			}
		}
		Node n = new Node(n1);
		nodes.add(n);
		return true;
	}
	
	/**
	 * adds a node to the graph with a color
	 * @param n1 - string name of the node to be added
	 * @param color - color of the node if graph is colored
	 * @return whether the node was added
	 */
	public boolean addNode(String n1, String color) {
        for (Node n: nodes) {
            if (n.getName().equals(n1)) {
                return false;
            }
        }
        Node n = new Node(n1, color);
        nodes.add(n);
        return true;
    }

	/**
	 * helper method to add Nodes in the implementation of Prim's algorithm
	 * @param newNode the {@code Node} object to be added to the graph
	 * @return true if the Node object passed in does not already exist in the graph and has been successfully added
	 * and false otherwise
	 */
	public boolean addNode(Node newNode) {
		for (Node n : nodes) {
			if (n.getName().equals(newNode.getName())) {
				return false;
			}
		}
		nodes.add(newNode);
		return true;
	}
	
	/**
	 * deletes a node in the graph
	 * @param n1 - name of node to be deleted
	 * @return boolean of whether node was deleted, false if node not found in graph
	 */
	public boolean deleteNode(String n1) {
		for (Node n: nodes) {
			if (n.getName().equals(n1)) {
				deleteIncidentEdges(n1);
				nodes.remove(n);
				return true;
			}
		}

		return false;
	}

	/**
	 * deletes all incident edges of a node
	 * @param n1 - string name of node who's edges are to be deleted
	 */
	public void deleteIncidentEdges(String n1) {
		ArrayList<Node> neighbors = getOutgoingNeighbors(n1);
		for (Node n : neighbors) {
			deleteEdge(n1, n.getName());
		}
	}
	
	/**
	 * gets the out degree of a node
	 * @param n1 - string name of node
	 * @return - integer of out degree
	 */
	public int getOutDegree(String n1) {
		for (Node n: nodes) {
			if (n.getName().equals(n1)) {
				return n.getDegree();
			}
		}
		return 0;
	}

	/**
	 * gets the number of vertices in the graph
	 * @return int of number of vertices
	 */
	public int numOfVertices() {
	    return nodes.size();
	}

	/**
	 * gets all the outgoing neighbor nodes from a give node
	 * @param n1 - string name of the node
	 * @return arraylist of outgoing neighbors as node objects
	 */
	public ArrayList<Node> getOutgoingNeighbors(String n1) {
	    ArrayList<Object[]> edges = null;
	    ArrayList<Node> neighbors = new ArrayList<Node>();

	    for (Node n: nodes) {
	        if (n.getName().equals(n1)) {
	            edges = n.getEdges();
	        }
	    }

	    for (Object[] e : edges) {
	        neighbors.add(getNodeFromName(e[2].toString()));
	    }

	    return neighbors;
	}
	
	/**
	 * gets all the outgoing neighbor nodes from a give node
	 * @param n1 - node object
	 * @return arraylist of outgoing neighbors as node objects
	 */
	public ArrayList<Node> getOutgoingNeighbors(Node n1) {
	    ArrayList<Object[]> edges = null;
	    ArrayList<Node> neighbors = new ArrayList<Node>();

	    for (Node n: nodes) {
	        if (n.getName().equals(n1.getName())) {
	            edges = n.getEdges();
	        }
	    }

	    for (Object[] e : edges) {
	        neighbors.add(getNodeFromName(e[2].toString()));
	    }

	    return neighbors;
	}
	/**
	 * gets the node from it's string name
	 * @param n1 - string name of the node
	 * @return the node associated to the name
	 */
	public Node getNodeFromName(String n1) {
	    for (Node n: nodes) {
	        if (n.getName().equals(n1)) {
	            return n;
	        }
	    }
	    return null;
	}

	public String getNameFromIndex(int index) {
	    return nodes.get(index).getName();
	}

	/**
	 * accessor method for nodes
	 * @param index the index of the node to be returned
	 * @return the node in the specified index in the {@code nodes} ArrayList 
	 */
	public Node getNodeFromIndex(int index) {
		return nodes.get(index);
	}
	
	/**
	 * Iterators through the nodes to find the node with the name "name"
	 * @param name
	 * @return the index that this node is at
	 */
	
	public Integer getIndexFromName(String name) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getName().equals(name)) {
                return i;
            }
        }
        
        return null;
    }
}
