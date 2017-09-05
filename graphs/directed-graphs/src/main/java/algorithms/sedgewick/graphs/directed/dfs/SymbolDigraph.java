package algorithms.sedgewick.graphs.directed.dfs;

import java.util.HashMap;
import java.util.List;

import algorithms.sedgewick.graphs.api.Digraph;
import algorithms.sedgewick.graphs.api.Graph;
import algorithms.sedgewick.graphs.api.ISymbolGraph;
import algorithms.sedgewick.graphs.directed.DirectedGraph;
import algorithms.sedgewick.utils.FileUtils;

/**
 * Implementation class of the {@link ISymbolGraph} API using a {@link DirectedGraph}
 * 
 * @author Vikram Kommaraju
 */
public class SymbolDigraph implements ISymbolGraph {

	private Digraph<Integer> g;
	private HashMap<String, Integer> keyToIndexMap;
	private String keys[];
	
	private static final String JOBS_FILE = "src/main/resources/jobs.txt";
	private static final String JOBS_FILE_SEPARATOR = "/";	

	private SymbolDigraph(String inputFilePath, String separator) throws Exception {
		keyToIndexMap = new HashMap<String, Integer>();
		List<String> allLinesFromFile = FileUtils.readAllLines(inputFilePath);
		buildKeyToIndexMap(separator, allLinesFromFile);
		buildInvertedKeyIndex();
		createGraphAndAddEdges(separator, allLinesFromFile);
		
	}

	/*
	 * For each line, we are going to add an edge between the first
	 * vertex and other vertices in that line
	 */
	private void createGraphAndAddEdges(String separator, List<String> allLinesFromFile) {
		this.g = new DirectedGraph(keyToIndexMap.size());
		for(String line : allLinesFromFile) {
			
			String[] keys = line.split(separator);
			
			//get the index of the first vertex
			int v = keyToIndexMap.get(keys[0]);
			
			//Parse through the remain vertices in that line
			for(int i=1; i<keys.length; i++) {
				
				//Get the index of current vertex
				int w = keyToIndexMap.get(keys[i]);
				
				//Add an edge between the first vertex and current vertex
				g.addEdge(v, w);
			}
		}
	}

	private void buildInvertedKeyIndex() {
		keys = new String[keyToIndexMap.size()];
		
		for(String key : keyToIndexMap.keySet()) {
			int indexOfKey = keyToIndexMap.get(key);
			keys[indexOfKey] = key;
		}
	}

	private void buildKeyToIndexMap(String separator, List<String> allLines) {
		for(String line : allLines) {
			String[] keys = line.split(separator);
			
			for(String key : keys) {
				if(!keyToIndexMap.containsKey(key)) {
					keyToIndexMap.put(key, keyToIndexMap.size());
				}
			}
		}
	}
	
	public static SymbolDigraph createJobsGraph() throws Exception {
		return new SymbolDigraph(JOBS_FILE, JOBS_FILE_SEPARATOR);
	}	
	
	public boolean contains(String key) {
		return keyToIndexMap.containsKey(key);
	}

	public int index(String key) {
		return keyToIndexMap.get(key);
	}

	public String name(int v) {
		return keys[v];
	}

	public Digraph G() {
		return g;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(g.V() + " vertices, " + g.E() + " edges");
		for(int i=0; i<g.V(); i++) {
			str.append("\n" + keys[i]+": ");
			for(int w :  g.adj(i)) {
				str.append(keys[w] + " ");
			}
		}
		return str.toString();
	}

	public static void main(String[] args) throws Exception {
		ISymbolGraph symGraph = SymbolDigraph.createJobsGraph();
		System.out.println("Graph is : " + symGraph);
		System.out.println("###");
		String movieName = "Bacon, Kevin";
		
//		Iterable<Integer> performerIndices = symGraph.G().adj(indexOfMovieName);
//		for(int w : performerIndices) {
//			String performerName = symGraph2.name(w);
//			System.out.print(performerName + "\n");
//		}
		System.out.println();
		
	}
}