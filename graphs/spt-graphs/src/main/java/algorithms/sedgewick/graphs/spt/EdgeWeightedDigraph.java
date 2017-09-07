package algorithms.sedgewick.graphs.spt;

import java.util.ArrayList;
import java.util.List;

import algorithms.sedgewick.graphs.api.DirectedEdge;
import algorithms.sedgewick.graphs.api.UndirectedEdge;
import algorithms.sedgewick.graphs.api.WeightedDigraph;
import algorithms.sedgewick.graphs.api.WeightedGraph;
import algorithms.sedgewick.utils.FileUtils;
import algorithms.sedgewick.utils.GraphFileInput;

/**
 * Implementation of the {@link WeightedDigraph} API
 *
 * @author Vikram Kommaraju
 */
public class EdgeWeightedDigraph implements WeightedDigraph {

	private final int numberOfVertices;
	private int numberOfEdges;
	private List<DirectedEdge>[] adjacencyList;
	
	private static final String TINY_EWDGRAPH_INPUT = "src/main/resources/tinyEWDG.txt";
	
	public EdgeWeightedDigraph(int V) {
		this.numberOfVertices = V;
		this.numberOfEdges = 0;
		initializeAdjacencyList();		
	}

	public EdgeWeightedDigraph(String inputFilePath) throws Exception {
		GraphFileInput inputGraph = FileUtils.getInputDataFromFile(inputFilePath);
		numberOfVertices = inputGraph.getNumberOfVertices();
		initializeAdjacencyList();
		addAllEdgesFromFile(inputGraph);
	}
	
	public static WeightedDigraph createSmallGraph() throws Exception {
		return new EdgeWeightedDigraph(TINY_EWDGRAPH_INPUT);
	}
	
	public int V() {
		return this.numberOfVertices;
	}

	public int E() {
		return this.numberOfEdges;
	}

	public void addEdge(DirectedEdge e) {
		int v = e.from();
		this.adjacencyList[v].add(0, e);
		this.numberOfEdges++;
	}

	public Iterable<DirectedEdge> adj(int v) {
		return adjacencyList[v];
	}

	public Iterable<DirectedEdge> edges() {
		List<DirectedEdge> allEdges = new ArrayList<DirectedEdge>();
		for(int v=0; v<this.numberOfVertices; v++) {
			for(DirectedEdge e : adjacencyList[v]) {
				allEdges.add(e); 
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
			
			for(DirectedEdge e : adjacencyList[i]) {
				str.append(e + " ");
			}
		}
		return str.toString();
	}
	private void initializeAdjacencyList() {
		this.adjacencyList = new List[this.numberOfVertices];
		for(int i=0; i<numberOfVertices; i++) {
			this.adjacencyList[i] = new ArrayList<DirectedEdge>();
		}
	}
	
	private void addAllEdgesFromFile(GraphFileInput inputGraph) {
		for(String edge : inputGraph.getEdges()) {
			int v = Integer.parseInt(edge.split(" ")[0]);
			int w = Integer.parseInt(edge.split(" ")[1]);
			double weight = Double.parseDouble(edge.split(" ")[2]);
			
			DirectedEdge e = new WeightedDirectedEdge(v, w, weight);
			addEdge(e);
		}
	}
	
	public static void main(String[] args) throws Exception {
		WeightedDigraph g = EdgeWeightedDigraph.createSmallGraph();
		System.out.println(g);
		System.out.println(g.edges());		
	}

}
