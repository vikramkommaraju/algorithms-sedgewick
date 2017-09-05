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

	private Graph<Integer> g;
	boolean[] marked;
	
	public DirectedDFS(Graph<Integer> g, List<Integer> sources) {
		super(g, sources);
		marked = new boolean[g.V()];
		for(int s : sources) {
			if(!marked[s]) {
				dfs(g, s);				
			}
		}
	}
	
	public DirectedDFS(Graph<Integer> g, int source) {
		super(g, source);
		marked = new boolean[g.V()];
		dfs(g, source);
	}

	@Override
	public boolean marked(int v) {
		return marked[v];
	}
	
	private void dfs(Graph<Integer> g, int v) {
		marked[v] = true;
		for(int w :  g.adj(v)) {
			if(!marked[w]) {
				dfs(g, w);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Graph<Integer> g = DirectedGraph.createSmallGraph();
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
