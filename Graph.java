/*
 * Graph class - represents a Graph with vertices and edges.
 * 
 * Can be used with the Edge and Vertex classes, though not entirely necessary.
 * Allows the language of a graph to be used when programming.
 */

import java.util.ArrayList;// imports library used to create lists of Vertices and Edges 

public class Graph {
	private ArrayList<Vertex> vertices;// instance variable of Graph, represents the Vertices of the Graph
	private ArrayList<Edge> edges;// instance variable of Graph, represents the Edges of the Graph
	
	// Constructor for int and int arrays
		public Graph(int[] newVertices, int[]... newEdges) {
			edges = new ArrayList<Edge>();// creates new instance for edges
			for (int[] edgeList : newEdges)// loops through the int[] provided by the user
				this.edges.add(new Edge(new Vertex(edgeList[0]), new Vertex(edgeList[1])));// adds each Edge provided by the user to the Graph
			vertices = new ArrayList<Vertex>();// creates new instance for vertices
			for (int vertexList : newVertices)// loops through the int[] provided by the user
				this.vertices.add(new Vertex(vertexList));// adds each Vertex provided by the user to the Graph
		}// Graph(int[], int[]) constructor
	
	// Constructor for Vertex and Edges arrays
	public Graph(Vertex[] newVertices, Edge[] newEdges) {
		edges = new ArrayList<Edge>();// creates new instance for edges
		for (Edge edgeList : newEdges)// loops through the Edge[] provided by the user
			this.edges.add(edgeList);// adds each Edge provided by the user to the Graph
		vertices = new ArrayList<Vertex>();// creates new instance for vertices
		for (Vertex vertexList : newVertices)// loops through the Vertex[] provided by the user
			this.vertices.add(vertexList);// adds each Vertex provided by the user to the Graph
	}// Graph(Vertex[], Edge[]) constructor
	
	// Allows the user to get a specified Vertex from a Graph
	protected Vertex getVertex(int i) {
		return vertices.get(i);// returns the Vertex associated with the int provided
	}// getVertex(int) method
	
	// Allows the user to get the Edges of a Graph
	protected ArrayList<Edge> getEdges() {
		ArrayList<Edge> edges = new ArrayList<Edge>();// creates a new instance of edges to hold Edges for user
		for (Edge edge : this.edges)// loops through the Edges in the Graph
			edges.add(edge);// copies each Edge from the Graph to the list provided to the user
		return edges;// returns a copy of the Edges of the Graph
	}// getEdges() method
	
	// Allows the user to get the Vertices of a Graph
	protected ArrayList<Vertex> getVertices() {
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();// creates a new instance of vertices to hold Vertices for user
		for (Vertex vertex : this.vertices)// loops through the Vertices in the Graph
			vertices.add(vertex);// copies each Vertex from the Graph to the list provided to the user
		return vertices;// returns a copy of the Vertices of the Graph
	}// getVertices() method
	
	// Allows the user to get the number of Vertices in a Graph
	protected int numVertices() {
		return vertices.size();// returns the number of Vertices in the Graph
	}// numVertices() method
	
	// Overwrites the toString() method so that a Graph's Vertices and Edges are displayed
	public String toString() {
		return "Vertices: " + vertices.toString() + "\nEdges: " + edges.toString();// defines how to display the Graph when printed
	}// toString() method
}// Graph class
