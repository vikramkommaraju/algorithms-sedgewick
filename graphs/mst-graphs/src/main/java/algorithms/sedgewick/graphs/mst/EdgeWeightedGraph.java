package algorithms.sedgewick.graphs.mst;

import java.util.ArrayList;
import java.util.List;

import algorithms.sedgewick.graphs.api.UndirectedEdge;
import algorithms.sedgewick.graphs.api.WeightedGraph;
import algorithms.sedgewick.utils.FileUtils;
import algorithms.sedgewick.utils.GraphFileInput;

/**
 * Implementaion of the {@link WeightedGraph} API
 *
 * @author Vikram Kommaraju
 */
public class EdgeWeightedGraph implements WeightedGraph {

	private int numberOfVertices;
	private int numberOfEdges;
	private List<UndirectedEdge>[] adjacencyList;
	
	private static final String TINY_EWGRAPH_INPUT = "src/main/resources/tinyEWG.txt";
	
	public EdgeWeightedGraph(int V) {
		this.numberOfVertices = V;
		this.numberOfEdges = 0;
		initializeAdjacencyList();
	}
	
	public EdgeWeightedGraph(String inputFilePath) throws Exception {
		GraphFileInput inputGraph = FileUtils.getInputDataFromFile(inputFilePath);
		numberOfVertices = inputGraph.getNumberOfVertices();
		initializeAdjacencyList();
		addAllEdgesFromFile(inputGraph);
	}
	
	public static WeightedGraph createSmallGraph() throws Exception {
		return new EdgeWeightedGraph(TINY_EWGRAPH_INPUT);
	}
	
	public int V() {
		return this.numberOfVertices;
	}

	public int E() {
		return this.numberOfEdges;
	}

	public void addEdge(UndirectedEdge e) {
		int v = e.either();
		int w = e.other(v);
		this.adjacencyList[v].add(0, e);
		this.adjacencyList[w].add(0, e);
		this.numberOfEdges++;
	}

	public Iterable<UndirectedEdge> adj(int v) {
		return adjacencyList[v];
	}

	public Iterable<UndirectedEdge> edges() {
		List<UndirectedEdge> allEdges = new ArrayList<UndirectedEdge>();
		for(int v=0; v<this.numberOfVertices; v++) {
			for(UndirectedEdge e : adjacencyList[v]) {
				if(e.other(v) > v) {
					allEdges.add(e); // Each edge is stored twice. Add only once					
				}
			}
		}
		return allEdges;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(numberOfVertices + " vertices, " + numberOfEdges + " edges");
		for(int i=0; i<numberOfVertices; i++) {
			str.append("\n" + i+": ");
			
			for(UndirectedEdge e : adjacencyList[i]) {
				str.append(e + " ");
			}
		}
		return str.toString();
	}
	
	private void initializeAdjacencyList() {
		this.adjacencyList = new ArrayList [numberOfVertices];
		for(int i=0; i<numberOfVertices; i++) {
			adjacencyList[i] = new ArrayList<UndirectedEdge>();
		}
	}
	
	private void addAllEdgesFromFile(GraphFileInput inputGraph) {
		for(String edge : inputGraph.getEdges()) {
			int v = Integer.parseInt(edge.split(" ")[0]);
			int w = Integer.parseInt(edge.split(" ")[1]);
			double weight = Double.parseDouble(edge.split(" ")[2]);
			
			UndirectedEdge e = new WeightedUndirectedEdge(v, w, weight);
			addEdge(e);
		}
	}
	
	public static void main(String[] args) throws Exception {
		WeightedGraph g = EdgeWeightedGraph.createSmallGraph();
		System.out.println(g);
		System.out.println(g.edges());		
	}
}
