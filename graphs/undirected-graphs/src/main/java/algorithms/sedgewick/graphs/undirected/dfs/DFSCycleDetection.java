package algorithms.sedgewick.graphs.undirected.dfs;

import algorithms.sedgewick.graphs.api.CycleDetection;
import algorithms.sedgewick.graphs.api.Graph;
import algorithms.sedgewick.graphs.undirected.UndirectedGraph;

/**
 * This class uses {@link DepthFirstSearch} algorithm to detect if a given
 * {@link Graph} has a cycle or not
 * 
 * @author Vikram Kommaraju
 */
public class DFSCycleDetection implements CycleDetection {
	
	Graph<Integer> g;
	boolean[] marked;
	boolean hasCycle;
	
	public DFSCycleDetection(Graph<Integer> g) {
		this.g = g;
		marked = new boolean[g.V()];
		hasCycle = false;
		
		for(int s=0; s<g.V(); s++) {
			if(!marked[s]) {
				dfs(g, s, s);
			}
		}
	}
	
	public boolean hasCycle() {
		return true;
	}
	
	private void dfs(Graph<Integer> g, int v, int u) {
		marked[v] = true;
		for(int w : g.adj(v)) {
			if(!marked[w]) {
				dfs(g, w, v);
			} else if( w != u) {
				hasCycle = true;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Graph<Integer> g = UndirectedGraph.createSmallGraph();
		DFSCycleDetection dfsCycle = new DFSCycleDetection(g);
		System.out.println(g);
		System.out.println("Does graph have a cycle ? " + dfsCycle.hasCycle());
	}

	public Iterable<Integer> cycle() {
		return null;
	}
	

}
