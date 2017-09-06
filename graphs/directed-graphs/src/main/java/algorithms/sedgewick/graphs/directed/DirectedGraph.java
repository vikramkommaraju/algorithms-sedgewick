package algorithms.sedgewick.graphs.directed;

import java.util.ArrayList;
import java.util.List;

import algorithms.sedgewick.graphs.api.Digraph;
import algorithms.sedgewick.graphs.api.Graph;
import algorithms.sedgewick.graphs.api.GraphUtils;
import algorithms.sedgewick.utils.FileUtils;
import algorithms.sedgewick.utils.GraphFileInput;

/**
 * Implementation class of {@link Digraph} API
 *
 * @author Vikram Kommaraju
 */
public class DirectedGraph implements Digraph<Integer> {

	private int numberOfVertices;
	private int numberOfEdges;
	private List<Integer>[] adjacencyList;
	
	private static final String TINY_DIGRAPH_INPUT = "src/main/resources/tinyDG.txt";
	private static final String MEDIUM_DIGRAPH_INPUT = "src/main/resources/mediumDG.txt";
	private static final String LARGE_DIGRAPH_INPUT = "src/main/resources/largeDG.txt";	
	
	public DirectedGraph(int numberOfVertices) {
		this.numberOfVertices = numberOfVertices;
		this.numberOfEdges = 0;
		initializeAdjacencyList();
	}
	
	private DirectedGraph(String inputFilePath) throws Exception {
		GraphFileInput inputGraph = FileUtils.getInputDataFromFile(inputFilePath);
		numberOfVertices = inputGraph.getNumberOfVertices();
		numberOfEdges = inputGraph.getNumberOfEdges();
		initializeAdjacencyList();
		addAllEdgesFromFile(inputGraph);	
	}
	
	public static Graph createSmallGraph() throws Exception {
		return new DirectedGraph(TINY_DIGRAPH_INPUT);
	}
	
	public static Graph createMediumGraph() throws Exception {
		return new DirectedGraph(MEDIUM_DIGRAPH_INPUT);
	}
	
	public static Graph createLargeGraph() throws Exception {
		return new DirectedGraph(LARGE_DIGRAPH_INPUT);
	}
	
	public int V() {
		return numberOfVertices;
	}

	public int E() {
		return numberOfEdges;
	}

	public void addEdge(Integer v, Integer w) {
		adjacencyList[v].add(0, w);
		numberOfEdges++;
	}

	public Iterable<Integer> adj(Integer v) {
		return adjacencyList[v];
	}

	public Digraph reverse() {
		Digraph<Integer> reverseDiGraph = new DirectedGraph(numberOfVertices);
		for(int i=0; i<numberOfVertices; i++) {
			for(int w : adjacencyList[i]) {
				reverseDiGraph.addEdge(w, i); // Add edge in reverse order
			}
		}
		return reverseDiGraph;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(numberOfVertices + " vertices, " + numberOfEdges + " edges");
		for(int i=0; i<numberOfVertices; i++) {
			str.append("\n" + i+": ");
			
			for(int w : adjacencyList[i]) {
				str.append(w + " ");
			}
		}
		return str.toString();
	}
	
	//Private helper methods
	private void initializeAdjacencyList() {
		this.adjacencyList = new ArrayList [numberOfVertices];
		for(int i=0; i<numberOfVertices; i++) {
			adjacencyList[i] = new ArrayList<Integer>();
		}
	}
	
	private void addAllEdgesFromFile(GraphFileInput inputGraph) {
		for(String edge : inputGraph.getEdges()) {
			int v = Integer.parseInt(edge.trim().split(" ")[0]);
			int w = Integer.parseInt(edge.trim().split(" ")[1]);
			
			addEdge(v, w);
		}
	}
	
	public static void main(String[] args) throws Exception {
		Graph<Integer> g = DirectedGraph.createSmallGraph();
		System.out.println(g);
		
		int v = 11;
		System.out.println("Degree of vertex " + v + " is " + GraphUtils.degree(g, v));
		System.out.println("Max degree of any vertex is " + GraphUtils.maxDegree(g));
	}

}
