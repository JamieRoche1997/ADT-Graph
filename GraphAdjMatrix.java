/*
Author: Jamie Roche
Student Number: R00151829
Purpose: Non-Linear Data Structure & Algorithm
Date: 9/4/23
Class: SDH2-B
*/

import java.util.LinkedList;

// Importing the LinkedList class from the java.util package

/*
Implementation of the ADT Graph with adjacency matrix.
*/

public class GraphAdjMatrix implements Graph {

    // ATTRIBUTES:
    // TO-DO
    private final int[][] adjacencyMatrix; // Adjacency matrix to store the edges
    private final int numVertices; // Number of vertices in the graph
    private final boolean directed; // Boolean to indicate if the graph is directed or undirected
    private int numEdges; // Number of edges in the graph

    // CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges
    public GraphAdjMatrix(int numVertices, boolean directed) {
        //TO-DO
        this.numVertices = numVertices;
        this.directed = directed;
        adjacencyMatrix = new int[numVertices][numVertices];
        numEdges = 0;
    }

    // 1. IMPLEMENTATION METHOD numVerts:
    public int numVerts() {
        //TO-DO
        return numVertices;
    }

    // 2. IMPLEMENTATION METHOD numEdges:
    public int numEdges() {
        //TO-DO
        return numEdges;
    }

    // 3. IMPLEMENTATION METHOD addEdge:
    public void addEdge(int v1, int v2, int w) {
        //TO-DO
        // Check if vertex IDs are valid
        if (v1 < 0 || v1 >= numVertices || v2 < 0 || v2 >= numVertices) {
            System.err.println("Error: Vertex ID out of bounds.");
            return;
        }
        // Add edge to adjacency matrix
        adjacencyMatrix[v1][v2] = w;
        // If graph is undirected, add edge in reverse direction as well
        if (!directed) {
            adjacencyMatrix[v2][v1] = w;
        }
        numEdges++; // Increment number of edges in the graph
    }

    // 4. IMPLEMENTATION METHOD removeEdge:
    public void removeEdge(int v1, int v2) {
        //TO-DO
        // Check if vertex IDs are valid
        if (v1 < 0 || v1 >= numVertices || v2 < 0 || v2 >= numVertices) {
            System.err.println("Error: Vertex ID out of bounds.");
            return;
        }
        // Remove edge from adjacency matrix
        adjacencyMatrix[v1][v2] = 0;
        // If graph is undirected, remove edge in reverse direction as well
        if (!directed) {
            adjacencyMatrix[v2][v1] = 0;
        }
        numEdges--; // Decrement number of edges in the graph
    }

    // 5. IMPLEMENTATION METHOD hasEdge:
    public boolean hasEdge(int v1, int v2) {
        //TO-DO
        // Check if vertex IDs are valid
        if (v1 < 0 || v1 >= numVertices || v2 < 0 || v2 >= numVertices) {
            System.err.println("Error: Vertex ID out of bounds.");
            return false;
        }
        // Check if edge exists in adjacency matrix
        return adjacencyMatrix[v1][v2] != 0;
    }

    // 6. IMPLEMENTATION METHOD getWeightEdge:
    public int getWeightEdge(int v1, int v2) {
        //TO-DO
        // Check if vertex IDs are valid
        if (v1 < 0 || v1 >= numVertices || v2 < 0 || v2 >= numVertices) {
            System.err.println("Error: Vertex ID out of bounds.");
            return 0;
        }
        // Get weight of edge from adjacency matrix
        return adjacencyMatrix[v1][v2];
    }

    // 7. IMPLEMENTATION METHOD getNeighbors:
    // This method returns a linked list of all the neighbors of a vertex with ID 'v'.
    public LinkedList<Integer> getNeighbors(int v) {
        //TO-DO
        // Check if the vertex ID is valid, and print an error message if it is not.
        if (v < 0 || v >= numVertices) {
            System.err.println("Error: Vertex ID out of bounds.");
            return null;
        }
        // Create an empty linked list to store the neighbors.
        LinkedList<Integer> neighbors = new LinkedList<>();

        // Iterate through all vertices and check if the vertex 'v' is connected to them.
        for (int i = 0; i < numVertices; i++) {
            if (adjacencyMatrix[v][i] != 0) {
                // If 'v' is connected to the vertex, add the vertex's ID to the linked list.
                neighbors.add(i);
            }
        }
        // Return the linked list of neighbors.
        return neighbors;
    }

    // 8. IMPLEMENTATION METHOD getDegree:
    // This method returns the degree of a vertex with ID 'v' in the graph.
    public int getDegree(int v) {
        //TO-DO
        // Check if the vertex ID is valid, and print an error message if it is not.
        if (v < 0 || v >= numVertices) {
            System.err.println("Vertex ID out of bounds");
            return -1;
        }
        // Initialize a counter to keep track of the degree of the vertex.
        int degree = 0;
        // Iterate through all vertices and check if the vertex 'v' is connected to them.
        for (int i = 0; i < numVertices; i++) {
            if (adjacencyMatrix[v][i] != 0) {
                // If 'v' is connected to the vertex, increment the degree counter.
                degree++;
            }
        }
        // If the graph is undirected, iterate through all vertices again and check if they are connected to 'v'.
        if (!directed) {
            for (int i = 0; i < numVertices; i++) {
                if (adjacencyMatrix[i][v] != 0) {
                    // If the vertex is connected to 'v', increment the degree counter.
                    degree++;
                }
            }
        }
        // Return the degree of the vertex.
        return degree;
    }

    // 9. IMPLEMENTATION METHOD toString:
    // This method returns a string representation of the adjacency matrix of the graph.
    public String toString() {
        //TO-DO
        // Initialize a string builder to build the string representation.
        StringBuilder s = new StringBuilder();
        // Add a header to the string representation.
        s.append("Adjacency matrix:\n");
        // Iterate through all vertices and their connections, and add them to the string representation.
        for (int i = 0; i < numVertices; i++) {
            s.append(i).append(": ");
            for (int j = 0; j < numVertices; j++) {
                s.append(adjacencyMatrix[i][j]).append(" ");
            }
            s.append("\n");
        }
        // Return the string representation.
        return s.toString();
    }
}
