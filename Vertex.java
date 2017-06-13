/*
 * Vertex class - represents the vertex in a graph with an int value.
 * 
 * Can be used with the Graph and Edge classes, though not entirely necessary.
 * Allows the language of a graph to be used when programming.
 */
public class Vertex {
	private int vertex;// instance variable Vertex, represents the Vertex value
	
	// Constructor for a single int value
	public Vertex(int vertex) {
		this.vertex = vertex;// sets the int value of the Vertex to the specified value
	}// Vertex(int) constructor
	
	// Constructor for multiple ints
	public Vertex(int... vertices) {
		for (int vertex : vertices) {// allows a batch of int to be converted to Vertices at once
			new Vertex(vertex);// creates a new Vertex instance for each int value
		}// for(int vertex)
	}// Vertex(int...) constructor
	
	// Allow user to get int value of the Vertex
	protected int getValue() {
		return vertex;// returns the ine value of the Vertex to the caller
	}// getValue() method
	
	// Overwrites the toString() method so that a Vertex's value is printed
	public String toString() {
		return Integer.toString(vertex);// returns the printing prepared version of the Vertex
	}// toString() method
}// class Vertex
