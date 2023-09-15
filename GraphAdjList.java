/*
Author: Jamie Roche
Student Number: R00151829
Purpose: Non-Linear Data Structure & Algorithm
Date: 9/4/23
Class: SDH2-B
*/

import java.util.LinkedList;
import java.util.Iterator;

/**
 * Graph implementation that uses Adjacency Lists to store edges. It
 * contains one linked-list for every vertex i of the graph. The list for i
 * contains one instance of VertexAdjList for every vertex that is adjacent to i.
 * For directed graphs, if there is an edge from vertex i to vertex j then there is
 * a corresponding element in the adjacency list of node i (only). For
 * undirected graphs, if there is an edge between vertex i and vertex j, then there is a
 * corresponding element in the adjacency lists of *both* vertex i and vertex j. The
 * edges are not sorted; they contain the adjacent nodes in *order* of
 * edge insertion. In other words, for a graph, the node at the head of
 * the list of some vertex i corresponds to the edge involving i that was
 * added to the graph least recently (and has not been removed, yet).
 */
public class GraphAdjList  implements Graph {

	// ATTRIBUTES:
	//TO-DO
	private final int V;   // number of vertices
	private final boolean directed; // directed or undirected graph
	private final LinkedList<Edge>[] adj; // adjacency list of edges
	private int E; // number of edges

	// Edge class
	private static class Edge {
		int to, weight;
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	/*
	 * CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges.
	 * It initializes the array of adjacency edges so that each list is empty.
	 */
	public GraphAdjList(int V, boolean directed) {
		//TO-DO
		this.V = V;
		this.directed = directed;
		adj = new LinkedList[V];  // create an array of linked lists for adjacency lists
		for (int i = 0; i < V; i++) {
			adj[i] = new LinkedList<>(); // initialize the linked lists as empty
		}
		E = 0;  // initialize the number of edges to 0
	}

	// 1. IMPLEMENTATION METHOD numVerts:
	public int numVerts() {
		//TO-DO
		return V;  // return the number of vertices
	}

	// 2. IMPLEMENTATION METHOD numEdges:
	public int numEdges() {
		//TO-DO
		return E;  // return the number of edges
	}

	// 3. IMPLEMENTATION METHOD addEdge:
	public void addEdge(int v1, int v2, int w) {
		//TO-DO
		if (v1 < 0 || v1 >= V || v2 < 0 || v2 >= V) {  // check that vertex IDs are valid
			System.err.println("Error: vertex ID out of bounds");
			return;
		}
		adj[v1].add(new Edge(v2, w));  // add a new edge to the adjacency list of v1
		E++;  // increment the number of edges
		if (!directed) {  // if the graph is undirected
			adj[v2].add(new Edge(v1, w));  // add a new edge to the adjacency list of v2 as well
			E++;  // increment the number of edges
		}
	}

	// 4. IMPLEMENTATION METHOD removeEdge:
	// This method removes an edge from the graph, given two vertex IDs as input.
	public void removeEdge(int v1, int v2) {
		//TO-DO
		// Check if the vertex IDs are out of bounds.
		if (v1 < 0 || v1 >= V || v2 < 0 || v2 >= V) {
			System.err.println("Error: vertex ID out of bounds");
			return;
		}
		// Iterate over the adjacency list for vertex v1.
		Iterator<Edge> it = adj[v1].iterator();
		while (it.hasNext()) {
			Edge e = it.next();
			// Check if there exists an edge from v1 to v2.
			if (e.to == v2) {
				// Remove the edge from v1 to v2.
				it.remove();
				// Decrement the number of edges in the graph.
				E--;
				// If the graph is undirected, also remove the edge from v2 to v1.
				if (!directed) {
					Iterator<Edge> it2 = adj[v2].iterator();
					while (it2.hasNext()) {
						Edge f = it2.next();
						if (f.to == v1) {
							it2.remove();
							E--;
							break;
						}
					}
				}
				break;
			}
		}
	}

	// 5. IMPLEMENTATION METHOD hasEdge:
	// This method checks if there exists an edge between two given vertices.
	public boolean hasEdge(int v1, int v2) {
		//TO-DO
		// Check if the vertex IDs are out of bounds.
		if (v1 < 0 || v1 >= V || v2 < 0 || v2 >= V) {
			System.err.println("Error: vertex ID out of bounds");
			return false;
		}
		// Iterate over the adjacency list for vertex v1.
		for (Edge e : adj[v1]) {
			// Check if there exists an edge from v1 to v2.
			if (e.to == v2) {
				return true;
			}
		}
		return false;
	}

	// 6. IMPLEMENTATION METHOD getWeightEdge:
	// This method returns the weight of the edge between two given vertices.
	public int getWeightEdge(int v1, int v2) {
		//TO-DO
		// Check if the vertex IDs are out of bounds.
		if (v1 < 0 || v1 >= V || v2 < 0 || v2 >= V) {
			System.err.println("Error: vertex ID out of bounds");
			return -1;
		}
		// Iterate over the adjacency list for vertex v1.
		for (Edge e : adj[v1]) {
			// Check if there exists an edge from v1 to v2.
			if (e.to == v2) {
				return e.weight;
			}
		}
		return -1;
	}

	// 7. IMPLEMENTATION METHOD getNeighbors:
	// This method returns a linked list containing the neighbors of a given vertex.
	public LinkedList getNeighbors(int v) {
		//TO-DO
		// Check if the vertex ID is out of bounds.
		if (v < 0 || v >= V) {
			System.err.println("Error: vertex ID out of bounds");
			return null;
		}
		LinkedList<Integer> neighbors = new LinkedList<>();
		// Iterate over the adjacency list for vertex v.
		for (Edge e : adj[v]) {
			// Add the neighboring vertex to the linked list.
			neighbors.add(e.to);
		}
		return neighbors;
	}

	// 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v) {
		//TO-DO
		// Check if the vertex ID is within the bounds of the graph
		if (v < 0 || v >= V) {
			System.err.println("Error: vertex ID out of bounds");
			return -1;
		}
		// Initialize the degree of vertex v
		int degree = 0;
		// Iterate through all the edges adjacent to vertex v
		for (Edge e : adj[v]) {
			// Check if the graph is directed or if the edge is pointing to a vertex greater than or equal to v
			if (directed || e.to >= v) {
				// If the above condition is true, increment the degree of vertex v
				degree++;
			}
		}
		// Return the degree of vertex v
		return degree;
	}

	// 9. IMPLEMENTATION METHOD toString:
	public String toString() {
		//TO-DO
		// Create a new StringBuilder object to store the string representation of the graph
		StringBuilder s = new StringBuilder();
		// Append the number of vertices and edges to the StringBuilder
		s.append(V).append(" vertices, ").append(E).append(" edges\n");
		// Iterate through all the vertices of the graph
		for (int v = 0; v < V; v++) {
			// Append the vertex ID to the StringBuilder
			s.append(v).append(": ");
			// Iterate through all the edges adjacent to vertex v
			for (Edge e : adj[v]) {
				// If the weight of the edge is not zero, append the destination vertex ID and weight to the StringBuilder
				if (e.weight != 0) {
					s.append(e.to).append("(").append(e.weight).append(") ");
				}
				// If the weight of the edge is zero, append only the destination vertex ID to the StringBuilder
				else {
					s.append(e.to).append(" ");
				}
			}
			// Append a new line character to the StringBuilder after all the edges adjacent to vertex v have been appended
			s.append("\n");
		}
		// Return the string representation of the graph
		return s.toString();
	}
}


