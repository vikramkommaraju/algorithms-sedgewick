package algorithms.sedgewick.graphs.mst;

import java.util.ArrayList;
import java.util.List;

import algorithms.sedgewick.graphs.api.Edge;
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
	private List<Edge>[] adjacencyList;
	
	private static final String TINY_EWGRAPH_INPUT = "src/main/resources/tinyEWG.txt";
	
	EdgeWeightedGraph(int V) {
		this.numberOfVertices = V;
		this.numberOfEdges = 0;
		this.adjacencyList = new List[V];
		for(int i=0; i<numberOfVertices; i++) {
			this.adjacencyList[i] = new ArrayList<Edge>();
		}
	}
	EdgeWeightedGraph(String inputFilePath) throws Exception {
		GraphFileInput inputGraph = FileUtils.getInputDataFromFile(inputFilePath);
		numberOfVertices = inputGraph.getNumberOfVertices();
		numberOfEdges = inputGraph.getNumberOfEdges();
		initializeAdjacencyList();
		addAllEdgesFromFile(inputGraph);
	}
	
	public static EdgeWeightedGraph createSmallGraph() throws Exception {
		return new EdgeWeightedGraph(TINY_EWGRAPH_INPUT);
	}
	
	public int V() {
		return this.numberOfVertices;
	}

	public int E() {
		return this.numberOfEdges;
	}

	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		this.adjacencyList[v].add(0, e);
		this.adjacencyList[w].add(0, e);
		this.numberOfEdges++;
	}

	public Iterable<Edge> adj(int v) {
		return adjacencyList[v];
	}

	public Iterable<Edge> edges() {
		List<Edge> allEdges = new ArrayList<Edge>();
		for(int v=0; v<this.numberOfVertices; v++) {
			for(Edge e : adjacencyList[v]) {
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
			
			for(Edge e : adjacencyList[i]) {
				str.append(e + " ");
			}
		}
		return str.toString();
	}
	
	private void initializeAdjacencyList() {
		this.adjacencyList = new ArrayList [numberOfVertices];
		for(int i=0; i<numberOfVertices; i++) {
			adjacencyList[i] = new ArrayList<Edge>();
		}
	}
	
	private void addAllEdgesFromFile(GraphFileInput inputGraph) {
		for(String edge : inputGraph.getEdges()) {
			int v = Integer.parseInt(edge.split(" ")[0]);
			int w = Integer.parseInt(edge.split(" ")[1]);
			double weight = Double.parseDouble(edge.split(" ")[2]);
			
			Edge e = new WeightedEdge(v, w, weight);
			addEdge(e);
		}
	}
	
	public static void main(String[] args) throws Exception {
		EdgeWeightedGraph g = EdgeWeightedGraph.createSmallGraph();
		System.out.println(g);
		System.out.println(g.edges());
		
	}
}
