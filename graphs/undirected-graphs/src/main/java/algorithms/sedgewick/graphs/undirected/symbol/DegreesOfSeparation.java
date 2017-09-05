package algorithms.sedgewick.graphs.undirected.symbol;

import algorithms.sedgewick.graphs.api.Graph;
import algorithms.sedgewick.graphs.api.ISymbolGraph;
import algorithms.sedgewick.graphs.api.Paths;
import algorithms.sedgewick.graphs.undirected.bfs.BreadthFirstSearchPaths;

/**
 * This class implements the degrees of separation problem using a
 * {@link SymbolGraph} to store the {@link Graph} and
 * {@link BreadthFirstSearchPaths} to calculate the shortest paths separating
 * two vertices if such a path exists
 * 
 * @author Vikram Kommaraju
 */
public class DegreesOfSeparation {

	public static void main(String[] args) throws Exception {
		ISymbolGraph symGraph = SymbolGraph.createRoutesGraph();
		Graph<Integer> g = symGraph.G();

		String sourceVertex = "JFK";
		int sourceIndex = symGraph.index(sourceVertex);
		String sinkVertex = "LAS";
		int sinkIndex = symGraph.index(sinkVertex);

		Paths bfs = new BreadthFirstSearchPaths(g, sourceIndex);
		System.out.println("Is " + sinkVertex + " connected to " + sourceVertex + " ? " + bfs.hasPathTo(sinkIndex));
		Iterable<Integer> path = bfs.pathTo(sinkIndex);
		
		for(int w : path) {
			String airportName = symGraph.name(w);
			System.out.println("  " + airportName);
		}

	}

}
