/*
 * VertexCover class - represents a class for performing Vertex Cover's on a Graph.
 * 
 * Can be used with the Graph classes.
 * Provides multiple ways to perform a Vertex Cover on a Graph.
 */

import java.time.Duration;// imports library used for measuring the runtime of methods
import java.time.Instant;// imports library used for measuring the runtime of methods
import java.util.ArrayList;// imports library used to create lists for Vertices, Edges, and Covers
import java.util.HashMap;// imports library used to hold Vertices and the number of times they appear in a Graph
import java.util.Map;// imports library used to hold Vertices and the number of times they appear in a Graph
import java.util.Random;// imports library used to randomize the edges chosen in the Approx Cover

public class VertexCover {
	private Graph graph;// instance variable of VertexCover, represents the Graph
	private int k;// instance variable of VertexCover, represents the max number of accepted Vertices in a cover
	private int min;// instance variable of VertexCover, represents the min number of Vertices in a cover (optimizedBruteForce())
	private ArrayList<Vertex> cover;// instance variable of VertexCover, represents the Vertices in a cover set
	ArrayList<ArrayList<Vertex>> possibleCovers = new ArrayList<ArrayList<Vertex>>();// instance variable of VertexCover, represents the possible covers
	
	// Constructor for a Graph and a k-value
	public VertexCover(Graph graph, int k) {
		this.graph = graph;// assigns the Graph provided by the user to the current instance of VertexCover
		this.k = k;// assigns the k-value provided by the user to the current instance of VertexCover
	}// VertexCover(Graph, int) constructor
	
	// Allows VertexCover approaches to remove Edges that contain a specified Vertex
	// ***must be done in 2 stepd to avoid concurrent modification of edgeList ArrayList***
	protected void removeEdges(Vertex vertex, ArrayList<Edge> edgeList) {
		ArrayList<Edge> removeEdge = new ArrayList<Edge>();// creates new instance for edges
		for (int i = 0; i < edgeList.size(); i++) {// loops through the edgeList associated with the current search
			if ((edgeList.get(i)).hasVertex(vertex))// determines if the current Edge contains the specified Vertex
				removeEdge.add(edgeList.get(i));// adds the Edge to the remove list if it contains the Vertex
		}// for (int i)
		for (int i = 0; i < removeEdge.size(); i++)// loops through the list of Edges to remove
			edgeList.remove(removeEdge.get(i));// removes the Edges that contain the specified Vertex
	}// removeEdges(Vertex, ArrayList<Edge>)
	
	// Allows VertexCover approaches to get the Vertex that has the most associated Edges
	protected Vertex getMaxDegree(ArrayList<Vertex> vertices, ArrayList<Edge> edges) {
		Map<Vertex, Integer> map = new HashMap<Vertex, Integer>();// creates new instance to hold Vertices and their associated Vertex count
		int max = 0;// used to hold/determine which Vertex has the highest associated Vertex count
		Vertex maxVertex = new Vertex();// used to hold the Vertex with the highest associated Vertex count
		for (Vertex vertex : vertices) {// loops through the list of remaining Vertices in the search
			map.put(vertex,  0);// adds each Vertex to the map with an initial value of 0
			for (Edge edge : edges) {// loops through the list of remaining Edges in the search
				if (edge.hasVertex(vertex)) {// determines if an Edge contains a specific Vertex
					map.put(vertex,map.get(vertex) + 1);// if the Edge contains the Vertex, the count associated with that Vertex is incremented
				}// if (edge.hasVertex)
			}// for (Edge)
			if (map.get(vertex) > max) {// determines if the current Vertex has more associated Edges than the current max
				max = map.get(vertex);// if so, sets new max amount
				maxVertex = vertex;// if so, sets the maxVertex to the new Vertex that has the current max associated Edges
			}// if (map.get)
		}// for (Vertex)
		return maxVertex;// returns the Vertex with the most associated Edges
	}// getMaxDegree(ArrayList<Vertex>, ArrayList<Edge>)
	
	// Allows VertexCover approaches to get all the possible combinations of Vertex covers - recursive method
	protected void combinations(ArrayList<Vertex> vertices, int k, ArrayList<Vertex> current, int currentTot, boolean[] visit) {
		if (currentTot >= k-1) {// determines if the current permutation holds k or more values
			ArrayList<Vertex> temp = new ArrayList<Vertex>();// creates a temp ArrayList<Vertex> to hold current permutation
			for (int i = 0; i < current.size(); i++)// loops through the current permutation
				temp.add(current.get(i));// adds each Vertex in the current permutation to the temp ArrayList<Vertex>
			possibleCovers.add(temp);// adds the current Vertex permutation to the list of possible covers
			return;// returns from the current iteration of the recursive method
		}// if (currentTot)
		// loops through the Vertices in the Graph
		for (int i = 0; i < vertices.size(); i++) {
			if(!visit[i]) {// determines if the ith Vertex has already been set
				current.set(++currentTot, vertices.get(i));// if not, sets the ith Vertex in the current permutation, increments vertex count
				visit[i] = true;// sets the ith Vertex as being set
				combinations(vertices, k, current, currentTot, visit);// recursively calls itself with updated values
				visit[i] = false;// resets ith Vertex to unset for next permutation
				currentTot--;// decrements the vertex count
			}// if (!visit)
		}// for (int i)
	}// combinations(ArrayList<Vertex>, int, ArrayList<Vertex>, int, boolean[]) method
	
	// Brute force approach to finding a Vertex Cover of k or less Vertices
	protected void bruteForce() {
		Instant startTime = Instant.now();// gets current time as bruteForce start time
		possibleCovers = new ArrayList<ArrayList<Vertex>>();// create new instance of possibleCovers list
		ArrayList<Vertex> v = new ArrayList<Vertex>();// creates new ArrayList<Vertex> for use with combinations method
		for(int i = 0; i < this.k; i++) {// loops through k values
			v.add(new Vertex(i));// adds new Vertices which will hold each permutation Vertex values
		}// for (int i)
		boolean[] visit = new boolean[graph.getVertices().size()];// creates boolean array for use with combination method
		for(int i = 0; i < graph.getVertices().size(); i++) {// loops through the number of Vertices in the graph times
			visit[i] = false;// sets each boolean value in the array to an initial value of false
		}// for (int i)
		this.combinations(graph.getVertices(), this.k, v, -1, visit);// calls combinations method to set all possible permutations of Vertex covers
		ArrayList<Edge> edges = graph.getEdges();// gets all the Edges from the Graph
		cover = new ArrayList<Vertex>();// initializes a new ArrayList<Vertex> to hold the cover
		int min = this.k + 1;// sets min to initial value of k + 1
		int coverIndex = 0;// will be used to store the index of the minimum Vertex cover in the possibleCovers list
		for (int i = 0; i < possibleCovers.size(); i++) {// loops through all the possible Vertex cover permutations
			for (int j = 0; j < this.k; j++) {// loops through k values of the potential cover
				this.removeEdges(possibleCovers.get(i).get(j), edges);// removes all Edges associated with the current Vertex of the potential Vertex cover
				if (edges.isEmpty()) {// determines if a Vertex cover has been found for the graph
					if ((j + 1) < min) {// determines if the new Vertex cover is smaller than the current minimum
						min = (j + 1);// if so, sets the new minimum to the size of the current Vertex cover
						coverIndex = i;// if so, sets the index value to the current Vertex cover
					}// if (j + 1)
				}// if (edges)
			}// (for (int j)
			edges = graph.getEdges();// resets the edges list to hold all Graph Edges, ready for next potential Vertex cover
		}// for (int i)
		if (min <= k) {// determines if the smallest Vertex cover found meets the k requirement
			for (int i = 0; i < min; i++)// loops through the number of Vertices in the selected Vertex cover
				cover.add(possibleCovers.get(coverIndex).get(i));// sets each Vertex in the selected Vertex cover
			System.out.println("------------------------------------------------------------------------");// for formatting purposes
			System.out.println("----------BRUTE FORCE---------------------------------------------------");// for formatting purposes
			System.out.println("A brute force cover has been found that satisfies k = " + k);// displays the k Vertices in cover requirement
			System.out.println("The vertices of the smallest cover are: " + cover.toString());// displays the Vertices in the minimum cover
		}// if (min)
		else// if no Vertex cover was found that meets the k requirement
			System.out.println("No brute force cover that satisfies k = " + k + " has been found.");// displays that no suitable cover was found
		Instant endTime = Instant.now();// gets current time as bruteForce end time
		System.out.println("Brute force Duration: " + (Duration.between(startTime,endTime).toNanos()));// displays run time for bruteForce
		System.out.println("------------------------------------------------------------------------");// for formatting purposes
		possibleCovers.clear();// resets the list of possible Vertex covers
	}// bruteForce() method
	
	// Optimized brute force approach to finding a Vertex Cover of k or less Vertices
	protected void optimizedBruteForce() {
		Instant startTime = Instant.now();// gets current time as optimizedBruteForce start time
		ArrayList<Vertex> v = new ArrayList<Vertex>();// creates new ArrayList<Vertex> for use with combinations method
		for(int i = 0; i < this.k; i++) {// loops through k values
			v.add(new Vertex(i));// adds new Vertices which will hold each permutation Vertex values
		}// for (int i)
		boolean[] visit = new boolean[graph.getVertices().size()];// creates boolean array for use with combination method
		for(int i = 0; i < graph.getVertices().size(); i++) {// loops through the number of Vertices in the graph times
			visit[i] = false;// sets each boolean value in the array to an initial value of false
		}// for (int i)
		this.min = k + 1;// sets min to k + 1
		cover = new ArrayList<Vertex>();// new ArrayList that will hold the cover
		this.optimizedBruteForce(graph.getVertices(), this.k, v, -1, visit);// calls tandem method to determine minimum Vertex cover
		if (!this.cover.isEmpty()) {// determines whether a suitable cover has been found
			System.out.println("----------OPTIMIZED BRUTE FORCE------------------------------------------");// for formatting purposes
			System.out.println("An optimized brute force cover has been found that satisfies k = " + k);// displays the k Vertices in cover requirement
			System.out.println("The vertices of the smallest cover are: " + this.cover.toString());// displays the Vertices in the minimum cover
		}// if (!isEmpty)
		else
			System.out.println("No optimized brute force cover that satisfies k = " + k + " has been found.");// displays that no suitable cover was found
		Instant endTime = Instant.now();// gets current time as optimizedBruteForce end time
		System.out.println("Optimized brute force Duration: " + (Duration.between(startTime,endTime).toNanos()));// displays run time for optimizedBruteForce
		System.out.println("------------------------------------------------------------------------");// for formatting purposes
	}// optimizedBruteForce() method
	
	protected void optimizedBruteForce(ArrayList<Vertex> vertices, int k, ArrayList<Vertex> current,int currentTot , boolean[] visit) {
		ArrayList<Edge> edges = graph.getEdges();
		
		if (currentTot < k-1) {
			if (currentTot < min) {
				for (int j = 0; j < current.size(); j++) {
					this.removeEdges(current.get(j), edges);
					if (edges.isEmpty()) {
						if ((j + 1) < this.min) {
							this.min = (j + 1);
							cover.clear();
										for (int i = 0; i <= j; i++) {
								cover.add(current.get(i));
							}	
						}
					}
				}
			}
			edges = graph.getEdges();
			
		} else if (currentTot == k-1 && currentTot < min) {
			for (int j = 0; j < current.size(); j++) {
				this.removeEdges(current.get(j), edges);
				if (edges.isEmpty()) {
					if ((j + 1) < this.min) {
						this.min = (j + 1);
						cover.clear();
						for (int i = 0; i <= j; i++) {
							cover.add(current.get(i));
						}	
					}
				}
			}
			edges = graph.getEdges();
			return;
		} else {
			return;
		}
		
		for (int i = 0; i < vertices.size(); i++) {
			if(!visit[i]) {
				current.set(++currentTot, vertices.get(i));
				visit[i] = true;
				optimizedBruteForce(vertices, k, current, currentTot, visit);
				visit[i] = false;
				currentTot--;
			}
		}
	}// optimizedBruteForce(ArrayList<Vertex>, int, ArrayList<Vertex>,int , boolean[])
	
	// First Solution approach to finding a Vertex Cover of k or less Vertices
	protected void firstSolution() {
		possibleCovers = new ArrayList<ArrayList<Vertex>>();// create new instance of possibleCovers list
		Instant startTime = Instant.now();// gets current time as start time for firstSolution approach
		ArrayList<Vertex> v = new ArrayList<Vertex>();// creates an Vertex ArrayList for use in the combinations method
		for(int i = 0; i < this.k; i++) {// loops through to populate k Vertices in the above created list
			v.add(new Vertex(i));// adds a new Vertex for each element in the Vertex list
		}// for (int i)
		boolean[] visit = new boolean[graph.getVertices().size()];// creates a boolean array the size of the number of the Vertices in the Graph for use in combinations method
		for(int i = 0; i < graph.getVertices().size(); i++) {// loops through all elements in the boolean[] visit array
			visit[i] = false;// sets each element in the array to false
		}// for (int i)
		this.combinations(graph.getVertices(), this.k, v, -1, visit);// calls the combinations method to get all potential Vertex cover permutations
		ArrayList<Edge> edges = graph.getEdges();// gets all the Edges in the Graph
		cover = new ArrayList<Vertex>();// creates a new ArrayList<Vertex> to hold the Vertex cover
		for (int i = 0; i < possibleCovers.size(); i++) {// loops through the potential Vertex Cover permutations
			for (int j = 0; j < this.k; j++) {// loops through the Vertices in the current potential Vertex cover permutation
				this.removeEdges(possibleCovers.get(i).get(j), edges);// removes all the Edges that contain the current Vertex as an endpoint
				if (edges.isEmpty()) {// determines if a Vertex cover has been found
					if (j < k) {// determines if the Vertex cover found meets the k requirement
						for (int l = 0; l < j + 1; l++)// loops through each Vertex in the Vertex cover found
							cover.add(possibleCovers.get(i).get(l));// adds each Vertex in the Vertex cover found
						System.out.println("----------FIRST SOLUTION------------------------------------------------");// for formatting purposes
						System.out.println("A first solution cover has been found that satisfies k = " + k);// displays that a suitable cover was found
						System.out.println("The vertices of the first solution cover are: " + cover.toString());// displays the Vertices in the first solution cover
						Instant endTime = Instant.now();// gets current time as end time for firstSolution approach
						System.out.println("First solution Duration: " + (Duration.between(startTime,endTime).toNanos()));// displays run time for firstSolution
						System.out.println("------------------------------------------------------------------------");// for formatting purposes
						return;// returns from method if suitable solution is found
					}// if (j + 1)
				}// if (edges)
			}// for (int j)
			edges = graph.getEdges();// resets the edges list to hold all Graph Edges, ready for next potential Vertex cover 
		}// for (int i)
		System.out.println("No first solution cover that satisfies k = " + k + " has been found.");// displays that no suitable cover was found
		Instant endTime = Instant.now();// gets current time as end time for firstSolution approach
		System.out.println("First solution Duration: " + (Duration.between(startTime,endTime).toNanos()));// displays run time for firstSolution
		System.out.println("------------------------------------------------------------------------");// for formatting purposes
	}// firstSolution() method
	
	// Random-edge-selection approximation approach to finding a Vertex Cover
	protected void approxCover() {
		Instant startTime = Instant.now();// gets current time as start time for greedyCover approach
		ArrayList<Edge> edges = graph.getEdges();// gets all the Edges in the Graph
		cover = new ArrayList<Vertex>();// creates a new ArrayList<Vertex> to hold the Vertex cover
		while (!edges.isEmpty()) {// loops until a Vertex cover has been found
			Random random = new Random();// creates a random generator
			int edge = random.nextInt(edges.size());// generates a random number between 0 and the last index in the Edge list
			Edge e = edges.get(edge);// gets the Edge associated with the random number generated
			cover.add(e.getVertex1());// adds the first Vertex in the Edge found above to the Vertex cover list
			cover.add(e.getVertex2());// adds the second Vertex in the Edge found above to the Vertex cover list
			this.removeEdges(e.getVertex1(), edges);// removes all the Edges that contain the first Vertex in the Edge selected above
			this.removeEdges(e.getVertex2(), edges);// removes all the Edges that contain the second Vertex in the Edge selected above
		}// while (!edges)
		Instant endTime = Instant.now();// gets current time as end time for approxCover approach
		System.out.println("----------APPROX COVER--------------------------------------------------");// for formatting purposes
		System.out.println("An approximation cover for the graph has been found:");// displays that a cover has been found
		System.out.println("The vertices of the approximation cover are: " + cover.toString());// displays the Vertices in the approx cover
		System.out.println("Approx Duration: " + (Duration.between(startTime,endTime).toNanos()));// displays run time for approxCover
		System.out.println("------------------------------------------------------------------------");// for formatting purposes
	}// approxCover() method
	
	// Greedy Cover approximation approach to finding a Vertex Cover
	protected void greedyCover() {
		Instant startTime = Instant.now();// gets current time as start time for greedyCover approach
		ArrayList<Edge> edges = graph.getEdges();// gets all the Edges in the Graph
		ArrayList<Vertex> vertices = graph.getVertices();// gets all the Vertices in the Graph
		cover = new ArrayList<Vertex>();// creates a new ArrayList<Vertex> to hold the Vertex cover
		while (!edges.isEmpty()) {// loops until a Vertex cover has been found
			Vertex vertex = getMaxDegree(vertices, edges);// gets the Vertex with the most associated Edges
			cover.add(vertex);// adds the Vertex found above to the Vertex cover list
			vertices.remove(vertex);// removes the Vertex found above from the list of available Vertices
			this.removeEdges(vertex, edges);// removes all the Edges that contain the Vertex found above as an endpoint
		}// while (!edges)
		Instant endTime = Instant.now();// gets current time as end time for greedyCover approach
		System.out.println("----------GREEDY COVER--------------------------------------------------");// for formatting purposes
		System.out.println("A greedy cover for the graph has been found:");// displays that a cover has been found
		System.out.println("The vertices of the greedy cover are: " + cover.toString());// displays the Vertices in the greedy cover
		System.out.println("Greedy Duration: " + (Duration.between(startTime,endTime).toNanos()));// displays run time for greedyCover
		System.out.println("------------------------------------------------------------------------");// for formatting purposes
	}// greedyCover() method
}// VertexCover class
