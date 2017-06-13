# java-vertex-cover
Java application that runs and compares various vertex cover algorithms.

User Manual
The project relies on compiled VertexCoverTester, VertexCover, Graph, Edge, and Vertex classes.  All source files for these classes are included in the submission.
The application can be run simply using the hardcoded values, if appropriate.  In order to accomplish this, one simply needs to execute the main method of the VertexCoverTester class.  This class has been created simply for the a way to easily test the various Vertex Cover algorithm available in the VertexCover class.
As constituted, the main method within VertexCoverTester creates an instance of Graph, which includes several instances of Vertex and Edge objects, as well.  Each Graph object must be created with its particular set of Vertices and Edges.  After the Graph has been created, a VertexCover object is created with its Graph and int k-value specified, which then invokes the five Vertex Cover algorithms one after the other.  All the results of these statements are displayed to the console, as above.
If one would like to run the program with their own Graph specification, they will have to alter the hardcoded values in the main method of the VertexCoverTester class.  Interactional function was not built into the program, as the algorithms were the focus of the subject matter.
