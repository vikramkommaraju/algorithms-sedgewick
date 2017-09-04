package algorithms.sedgewick.graphs.undirected.dfs;

import algorithms.sedgewick.graphs.api.Graph;
import algorithms.sedgewick.graphs.undirected.UndirectedGraph;

/**
 * This class uses {@link DepthFirstSearch} algorithm to detect if a given
 * {@link Graph} has a cycle or not
 * 
 * @author Vikram Kommaraju
 */
public class DFSCycleDetection {
	
	Graph g;
	boolean[] marked;
	boolean hasCycle;
	
	public DFSCycleDetection(Graph g) {
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
	
	private void dfs(Graph g, int v, int u) {
		marked[v] = true;
		Iterable<Integer> adj = g.adj(v);
		for(int w : adj) {
			if(!marked[w]) {
				dfs(g, w, v);
			} else if( w != u) {
				hasCycle = true;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Graph g = UndirectedGraph.createSmallGraph();
		DFSCycleDetection dfsCycle = new DFSCycleDetection(g);
		System.out.println(g);
		System.out.println("Does graph have a cycle ? " + dfsCycle.hasCycle());
	}
	

}
