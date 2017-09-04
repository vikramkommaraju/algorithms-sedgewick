package algorithms.sedgewick.graphs.undirected.dfs;

import algorithms.sedgewick.graphs.api.Graph;
import algorithms.sedgewick.graphs.api.Search;
import algorithms.sedgewick.graphs.undirected.UndirectedGraph;

/**
 * DepthFirst Implementation of the {@link Search} API to find connected vertices
 * for a given source vertex in a {@link Graph}
 * 
 * @author Vikram Kommaraju
 *
 */
public class DepthFirstSearch extends Search {

	private boolean[] markedVertices;
	private int count;
	
	public DepthFirstSearch(Graph g, int source) {
		super(g, source);
		markedVertices = new boolean[g.V()];
		dfs(g, source);
	}

	@Override
	public boolean marked(int w) {
		return markedVertices[w];
	}

	@Override
	public int count() {
		return count;
	}
	
	private void dfs(Graph g, int v) {
		markedVertices[v] = true;
		count++;
		Iterable<Integer> adjList = g.adj(v);
		for(int w : adjList) {
			if(!marked(w)) dfs(g, w);
		}
	}
	
	public static void main(String[] args) throws Exception {
		Graph g = UndirectedGraph.createSmallGraph();
		System.out.println(g);
		int source = 0;
		Search s = new DepthFirstSearch(g, source);
		System.out.println(s.marked(1));
		System.out.println(s.count());
	}

}
