# java-vertex-cover
Java application that runs and compares various vertex cover algorithms.

- User Manual
  - The project relies on compiled VertexCoverTester, VertexCover, Graph, Edge, and Vertex classes.  All source files for these classes are included in the submission.
  - The application can be run simply using the hardcoded values, if appropriate.  In order to accomplish this, one simply needs to execute the main method of the VertexCoverTester class.  This class has been created simply for the a way to easily test the various Vertex Cover algorithm available in the VertexCover class.
  - As constituted, the main method within VertexCoverTester creates an instance of Graph, which includes several instances of Vertex and Edge objects, as well.  Each Graph object must be created with its particular set of Vertices and Edges.  After the Graph has been created, a VertexCover object is created with its Graph and int k-value specified, which then invokes the five Vertex Cover algorithms one after the other.  All the results of these statements are displayed to the console, as above.
  - If one would like to run the program with their own Graph specification, they will have to alter the hardcoded values in the main method of the VertexCoverTester class.  Interactional function was not built into the program, as the algorithms were the focus of the subject matter.

- Algorithm Design
  - The basic algorithm for the brute force approach is two-staged in nature.  It involves a method which first generates all the possible vertex cover combinations, followed by a method which tests each of these possibilities.  Ultimately, the algorithm either determines the minimum vertex cover, or that there is no vertex cover that is suitable for the given k value.
  - The first stage of the algorithm is recursive:
*possibleCovers is the list of potential vertex cover combinations
COMBINATIONS( vertices, k, current, currentTot, visit )
1 if currentTot >= k-1
2 	let temp a list to hold a temporary list of vertices
3 	for i = 0 to current.size
4 		temp = temp ꓴ currenti
5 	possibleCovers = possibleCovers ꓴ temp
6 	return
7 for i = 0 to vertices.size
8 	if !visit
9 		current.currentTot = verticesi
10 		visit.i = true
11 		COMBINATIONS( vertices, k, current, currentTot, visit )
12 		visit.i = false
13 		currentTot = currentTot – 1

  - The COMBINATIONS method is called from BRUTE-FORCE, with initial values of the set of graph vertices, the given k value, an array of vertices all initially set to zero, -1, and a Boolean array with an element for each vertex.  Line 1 tests to see whether the iteration of the current combination has reached a level of k total vertices.  If so, lines 2-4 build a list of the k vertices.  Line 5 then adds that list to the list of potential vertex covers, which will be tested for validity by the second stage of the algorithm.  Line 6 ends the recursion for the current vertex combination, letting the next combination start building.  Line 7 loops through all the vertices in the graph, controlling the building of potential vertex covers, and ensuring that all possibilities are accounted for.  Line 8 first checks whether the current ith vertex has been set.  If not, it is set in line 9, and marked as such in line 10.  A recursive call takes place in line 11, with the current variable settings.  Lines 12 and 13 perform recursion control maintenance, setting the visit variable for the current i to false and decrementing the currentTot value.
  - The second stage of the algorithm tests each of the potential vertex covers, searching for the minimum size cover, if one exists.  This section of the algorithm is found within a larger set of code, but the relevant steps are:
BRUTE-FORCE()
1 for i = 0 to possibleCovers.size
2 	for j = 0 to k
3 		remove from edges every edge incident on v
4 		if edges = 0
5 			if j+1 < min
6 				min = j+1
7 				let the index of possibleCovers be i

  - Line 1 loops through all the potential vertex covers established by stage one of the algorithm.  Line two loops through the vertices of each of these potential vertex covers, allowing line 3 to remove all the edges incident on this current vertex.  Line 4 checks to see whether the edge removal from line 3 has led to a successful vertex cover.  If so, line 5 then determines whether or not this successful vertex cover is small enough to be the new minimum vertex cover.  If the new vertex cover is the new minimum, the min variable is set in line 6 and the index of the cover, in terms of possibleCovers, is set in line 7.
  - The two stages taken together successfully have the ability to determine the minimum vertex cover for a given graph and k value.
