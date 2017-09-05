package algorithms.sedgewick.graphs.directed.dfs;

import java.util.List;

import com.google.common.collect.ImmutableList;

import algorithms.sedgewick.graphs.api.Graph;
import algorithms.sedgewick.graphs.api.MultipleSourceGraphSearch;
import algorithms.sedgewick.graphs.directed.DirectedGraph;

/**
 * Implementation of the {@link MultipleSourceGraphSearch} API for {@link DirectedGraph}s
 * This also handles single source reachability problem
 *
 * @author Vikram Kommaraju
 */
public class DirectedDFS extends MultipleSourceGraphSearch {

	private Graph g;
	boolean[] marked;
	
	public DirectedDFS(Graph g, List<Integer> sources) {
		super(g, sources);
		marked = new boolean[g.V()];
		for(int s : sources) {
			if(!marked[s]) {
				dfs(g, s);				
			}
		}
	}
	
	public DirectedDFS(Graph g, int source) {
		super(g, source);
		marked = new boolean[g.V()];
		dfs(g, source);
	}

	@Override
	public boolean marked(int v) {
		return marked[v];
	}
	
	private void dfs(Graph g, int v) {
		marked[v] = true;
		Iterable<Integer> adjList = g.adj(v);
		for(int w : adjList) {
			if(!marked[w]) {
				dfs(g, w);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Graph g = DirectedGraph.createSmallGraph();
		System.out.println(g);
		int singleSource = 2;
		List<Integer> multipleSources = ImmutableList.of(1, 2, 6);
		
		MultipleSourceGraphSearch s = new DirectedDFS(g, multipleSources);
		for(int v=0; v<g.V(); v++) {
			if(s.marked(v)) {
				System.out.print(v + " ");
			}
		}
	}

}
