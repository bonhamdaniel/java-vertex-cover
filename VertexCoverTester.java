/*
 * VertexCoverTester class - a tester class for the VertexCover class.
 * 
 * Can be used with the VertexCover, Graph, Edge, and Vertex classes.
 * Allows the language of a graph to be used when programming.
 */
public class VertexCoverTester {
	// Main method - contains various tests relating to VertexCover methods
	public static void main(String[] args) {
		// Graph 1 details and tests
		// Vertices for the first testGraph
		Vertex[] vertices = {new Vertex(1), new Vertex(2), new Vertex(3),
							 new Vertex(5), new Vertex(4)};
		// Edges for the first testGraph
		Edge[] graphEdges = {new Edge(1,2), new Edge(1,3), new Edge(1,4),
				   	    new Edge(1,5), new Edge(2,3), new Edge(2,4),
				   	    new Edge(4,5)};
		Graph testGraph = new Graph(vertices, graphEdges);// creates first testGraph
		// Displays the first testGraph information
		System.out.println("# of Vertices: " + testGraph.numVertices());
		System.out.println(testGraph);
		// Creates the testGraph and runs various Vertex Cover approaches
		VertexCover vertexCover = new VertexCover(testGraph, 2);// creates VertexCover instance with Graph and k
		vertexCover.bruteForce();// runs Brute Force approach
		vertexCover.optimizedBruteForce();// runs Optimized Brute Force approach
		vertexCover.firstSolution();// runs First Solution approach
		vertexCover.greedyCover();// runs Greedy Cover approach
		vertexCover.approxCover();// runs Approx Cover approach
		System.out.println();// displays blank line between graphs/tests
		
		// Graph 2 details and texts
		// Vertices for the second testGraph
		Vertex[] vertices2 = {new Vertex(1), new Vertex(2), new Vertex(3),
					new Vertex(4), new Vertex(5), new Vertex(6),
					new Vertex(7)};
		// Edges for the second testGraph
		Edge[] graphEdges2 = {new Edge(1,2), new Edge(2,3), new Edge(3,4),
				   	    new Edge(3,5), new Edge(4,5), new Edge(4,6),
				   	    new Edge(4,7), new Edge(5,6)};
		testGraph = new Graph(vertices2, graphEdges2);// creates second testGraph
		// Displays the second testGraph information
		System.out.println("# of Vertices: " + testGraph.numVertices());
		System.out.println(testGraph);
		// Creates the testGraph and runs various Vertex Cover approaches
		vertexCover = new VertexCover(testGraph, 6);// creates VertexCover instance with Graph and k
		vertexCover.bruteForce();// runs Brute Force approach
		vertexCover.optimizedBruteForce();// runs Optimized Brute Force approach
		vertexCover.firstSolution();// runs First Solution approach
		vertexCover.greedyCover();// runs Greedy Cover approach
		vertexCover.approxCover();// runs Approx Cover approach
		System.out.println();// displays blank line between graphs/tests
		
		// Graph 2 details and texts
		// Vertices for the third testGraph
		Vertex[] vertices3 = {new Vertex(1), new Vertex(2), new Vertex(3),
					new Vertex(4), new Vertex(5), new Vertex(6),
					new Vertex(7), new Vertex(8), new Vertex(9)};
		// Edges for the third testGraph
		Edge[] graphEdges3 = {new Edge(1,2), new Edge(2,3), new Edge(3,4),
				   	    new Edge(3,5), new Edge(4,5), new Edge(4,6),
				   	    new Edge(4,7), new Edge(5,6), new Edge(4,8),
				   	    new Edge(5,9)};
		testGraph = new Graph(vertices3, graphEdges3);// creates third testGraph
		// Displays the third testGraph information
		System.out.println("# of Vertices: " + testGraph.numVertices());
		System.out.println(testGraph);
		// Creates the testGraph and runs various Vertex Cover approaches
		vertexCover = new VertexCover(testGraph, 7);// creates VertexCover instance with Graph and k
		vertexCover.bruteForce();// runs Brute Force approach
		vertexCover.optimizedBruteForce();// runs Optimized Brute Force approach
		vertexCover.firstSolution();// runs First Solution approach
		vertexCover.greedyCover();// runs Greedy Cover approach
		vertexCover.approxCover();// runs Approx Cover approach
		System.out.println();// displays blank line between graphs/tests
	}// main(Str[]) method
}// VertexCoverTester class
