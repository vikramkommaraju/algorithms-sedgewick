package algorithms.sedgewick.graphs.undirected.dfs;

import algorithms.sedgewick.graphs.api.Graph;
import algorithms.sedgewick.graphs.api.SingleSourceGraphSearch;
import algorithms.sedgewick.graphs.undirected.UndirectedGraph;

/**
 * DepthFirst Implementation of the {@link SingleSourceGraphSearch} API to find connected vertices
 * for a given source vertex in a {@link Graph}
 * 
 * @author Vikram Kommaraju
 *
 */
public class DepthFirstSearch extends SingleSourceGraphSearch {

	private boolean[] markedVertices;
	private int count;
	
	public DepthFirstSearch(Graph<Integer> g, int source) {
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
	
	private void dfs(Graph<Integer> g, int v) {
		markedVertices[v] = true;
		count++;
		for(int w : g.adj(v)) {
			if(!marked(w)) dfs(g, w);
		}
	}
	
	public static void main(String[] args) throws Exception {
		Graph<Integer> g = UndirectedGraph.createSmallGraph();
		System.out.println(g);
		int source = 0;
		SingleSourceGraphSearch s = new DepthFirstSearch(g, source);
		System.out.println(s.marked(1));
		System.out.println(s.count());
	}

}
