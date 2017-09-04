package algorithms.sedgewick.graphs.undirected;

import java.util.ArrayList;
import java.util.List;

import algorithms.sedgewick.graphs.api.Graph;
import algorithms.sedgewick.graphs.api.GraphUtils;
import algorithms.sedgewick.utils.FileUtils;
import algorithms.sedgewick.utils.GraphFileInput;

/**
 * Implementation class for an {@link Graph}
 * 
 * @author Vikram Kommaraju
 */
@SuppressWarnings("unchecked")
public class UndirectedGraph implements Graph<Integer> {

	private final int numberOfVertices;
	private int numberOfEdges;
	private List<Integer>[] adjacencyList;
	
	private static final String TINY_GRAPH_INPUT = "src/main/resources/tinyG.txt";
	private static final String MEDIUM_GRAPH_INPUT = "src/main/resources/mediumG.txt";
	private static final String LARGE_GRAPH_INPUT = "src/main/resources/largeG.txt";
	
	public UndirectedGraph(int numberOfVertices) {
		this.numberOfVertices = numberOfVertices;
		this.numberOfEdges = 0;
		initializeAdjacencyList();
	}
	
	private UndirectedGraph(String inputFilePath) throws Exception {
		GraphFileInput inputGraph = FileUtils.getInputDataFromFile(inputFilePath);
		numberOfVertices = inputGraph.getNumberOfVertices();
		numberOfEdges = inputGraph.getNumberOfEdges();
		initializeAdjacencyList();
		addAllEdgesFromFile(inputGraph);	
	}
	
	public static UndirectedGraph createSmallGraph() throws Exception {
		return new UndirectedGraph(TINY_GRAPH_INPUT);
	}
	
	public static UndirectedGraph createMediumGraph() throws Exception {
		return new UndirectedGraph(MEDIUM_GRAPH_INPUT);
	}
	
	public static UndirectedGraph createLargeGraph() throws Exception {
		return new UndirectedGraph(LARGE_GRAPH_INPUT);
	}
	
	public int V() {
		return numberOfVertices;
	}

	public int E() {
		return numberOfEdges;
	}

	public void addEdge(Integer v, Integer w) {
		adjacencyList[v].add(0, w);
		adjacencyList[w].add(0, v);
	}

	public Iterable<Integer> adj(Integer v) {
		return adjacencyList[v];
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
	private void addAllEdgesFromFile(GraphFileInput inputGraph) {
		for(String edge : inputGraph.getEdges()) {
			int v = Integer.parseInt(edge.split(" ")[0]);
			int w = Integer.parseInt(edge.split(" ")[1]);
			
			adjacencyList[v].add(0, w);
			adjacencyList[w].add(0, v);
		}
	}
	
	private void initializeAdjacencyList() {
		this.adjacencyList = new ArrayList [numberOfVertices];
		for(int i=0; i<numberOfVertices; i++) {
			adjacencyList[i] = new ArrayList<Integer>();
		}
	}
	
	public static void main(String... args) throws Exception {
		UndirectedGraph g = UndirectedGraph.createSmallGraph();
		System.out.println(g);
		
		int v = 11;
		System.out.println("Degree of vertex " + v + " is " + GraphUtils.degree(g, v));
		System.out.println("Max degree of any vertex is " + GraphUtils.maxDegree(g));
				
	}
}