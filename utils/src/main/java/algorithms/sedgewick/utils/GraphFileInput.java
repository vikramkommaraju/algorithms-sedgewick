package algorithms.sedgewick.utils;

import java.util.List;

/**
 * This class represents the input to a Graph class constructed from a file with
 * input data
 * 
 * @author Vikram Kommaraju
 *
 */
public class GraphFileInput {

	private int numberOfVertices;
	private int numberOfEdges;
	private List<String> edges;

	private GraphFileInput(int numberOfVertices, int numberOfEdges, List<String> edges) {
		this.numberOfVertices = numberOfVertices;
		this.numberOfEdges = numberOfEdges;
		this.edges = edges;
	}

	public static GraphFileInput create(int numberOfVertices, int numberOfEdges, List<String> edges) {
		return new GraphFileInput(numberOfVertices, numberOfEdges, edges);
	}
	
	public int getNumberOfVertices() {
		return numberOfVertices;
	}

	public int getNumberOfEdges() {
		return numberOfEdges;
	}

	public List<String> getEdges() {
		return edges;
	}
}