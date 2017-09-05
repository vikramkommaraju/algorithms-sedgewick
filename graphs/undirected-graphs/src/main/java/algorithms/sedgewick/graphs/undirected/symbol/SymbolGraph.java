package algorithms.sedgewick.graphs.undirected.symbol;

import java.util.HashMap;
import java.util.List;

import algorithms.sedgewick.graphs.api.Graph;
import algorithms.sedgewick.graphs.api.ISymbolGraph;
import algorithms.sedgewick.graphs.undirected.UndirectedGraph;
import algorithms.sedgewick.utils.FileUtils;

/**
 * Implementation class of the {@link ISymbolGraph} API
 * 
 * @author Vikram Kommaraju
 */
public class SymbolGraph implements ISymbolGraph {

	private Graph<Integer> g;
	private HashMap<String, Integer> keyToIndexMap;
	private String keys[];
	
	private static final String ROUTES_FILE = "src/main/resources/routes.txt";
	private static final String ROUTES_FILE_SEPARATOR = " ";	
	private static final String MOVIES_FILE = "src/main/resources/movies.txt";
	private static final String MOVIES_FILE_SEPARATOR = "/";	
	
	private SymbolGraph(String inputFilePath, String separator) throws Exception {
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
		this.g = new UndirectedGraph(keyToIndexMap.size());
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
	
	public static SymbolGraph createRoutesGraph() throws Exception {
		return new SymbolGraph(ROUTES_FILE, ROUTES_FILE_SEPARATOR);
	}
	
	public static SymbolGraph createMoviesGraph() throws Exception {
		return new SymbolGraph(MOVIES_FILE, MOVIES_FILE_SEPARATOR);
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

	public Graph G() {
		return g;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(g.V() + " vertices, " + g.E() + " edges");
		for(int i=0; i<g.V(); i++) {
			str.append("\n" + keys[i]+": ");
			for(int w : g.adj(i)) {
				str.append(keys[w] + " ");
			}
		}
		return str.toString();
	}

	public static void main(String[] args) throws Exception {
		ISymbolGraph symGraph = SymbolGraph.createRoutesGraph();
		System.out.println("Graph is : " + symGraph);
		System.out.println("###");
		ISymbolGraph symGraph2 = SymbolGraph.createMoviesGraph();
		String movieName = "Bacon, Kevin";
		int indexOfMovieName = symGraph2.index(movieName);
		
		Iterable<Integer> performerIndices = symGraph2.G().adj(indexOfMovieName);
		for(int w : performerIndices) {
			String performerName = symGraph2.name(w);
			System.out.print(performerName + "\n");
		}
		System.out.println();
		
	}

}
