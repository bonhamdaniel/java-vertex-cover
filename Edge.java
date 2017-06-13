/*
 * Edge class - represents the edge in a graph with two endpoints.
 * 
 * Can be used with the Graph and Vertex classes, though not entirely necessary.
 * Allows the language of a graph to be used when programming.
 */
public class Edge {
	private Vertex vertex1;// instance variable of Edge, represents the first endpoint Vertex
	private Vertex vertex2;// instance variable of Edge, represents the second endpoint Vertex
	
	// Constructor for two Vertices
	public Edge(Vertex vertex1, Vertex vertex2) {
		this.vertex1 = vertex1;// assigns the first Vertex value to the first endpoint Vertex
		this.vertex2 = vertex2;// assigns the second Vertex value to the second endpoint Vertex
	}// Edge(Vertex, Vertex) constructor
		
	// Constructor for two ints
	public Edge(int vertex1, int vertex2) {
		this.vertex1 = new Vertex(vertex1);// assigns the first int value to the first endpoint Vertex
		this.vertex2 = new Vertex(vertex2);// assigns the second int value to the second endpoint Vertex
	}// Edge(int, int) constructor
	
	// Constructor for a Vertex array
		public Edge(Vertex[] edge) {
			this.vertex1 = edge[0];// assigns the first value of the array to the first endpoint Vertex
			this.vertex2 = edge[1];// assigns the second value of the array to the second endpoint Vertex
		}// Edge(Vertex[]) constructor
	
	// Constructor for an int array
	public Edge(int[] edge) {
		this.vertex1 = new Vertex(edge[0]);// assigns the first value of the array to the first endpoint Vertex
		this.vertex2 = new Vertex(edge[1]);// assigns the second value of the array to the second endpoint Vertex
	}// Edge(int[]) constructor
	
	// Constructor for multiple Vertex arrays
		public Edge(Vertex[]... edges) {
			for (Vertex[] edge : edges) {// loops through the Vertex[]
				new Edge(edge);// calls constructor for each Vertex array
			}// for(Vertex[] edge)
		}// Edge(Vertex[]...) constructor
	
	// Constructor for multiple int arrays
	public Edge(int[]... edges) {
		for (int[] edge : edges) {// loops through the int[]
			new Edge(edge);// calls constructor for each int array
		}// for(int[] edge)
	}// Edge(int[]...) constructor
	
	// Allows the user to get the first Vertex endpoint of an Edge
	public Vertex getVertex1() {
		return this.vertex1;// returns the first Vertex endpoint of the Edge	
	}// getVertex1() method
	
	// Allows the user to get the second Vertex endpoint of an Edge
	public Vertex getVertex2() {
		return this.vertex2;// returns the second Vertex endpoint of the Edge
	}// getVertex2() method
	
	// Allows the user to determine if an Edge has a specific Vertex as an endpoint
	public boolean hasVertex(Vertex vertex) {
		// determines if the Edge has the Vertex as an endpoint
		if (this.vertex1.getValue() == vertex.getValue() || this.vertex2.getValue() == vertex.getValue())
			return true;// returns true if an Edge does not have the Vertex for an endpoint 
		else
			return false;// returns false if an Edge does not have the Vertex for an endpoint
	}// hasVertex(Vertex) method
	
	// Overwrites the toString() method to define how an Edge is printed
	public String toString() {
		return "(" + vertex1 + ", " + vertex2 + ")";// defines how to display an Edge when printed
	}// toString() method
}// Edge class
