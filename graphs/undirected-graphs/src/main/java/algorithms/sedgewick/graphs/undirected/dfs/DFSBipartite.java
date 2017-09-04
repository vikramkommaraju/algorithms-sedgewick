package algorithms.sedgewick.graphs.undirected.dfs;

import algorithms.sedgewick.graphs.api.Graph;
import algorithms.sedgewick.graphs.undirected.UndirectedGraph;

/**
 * This class uses {@link DepthFirstSearch} algorithm to detect if a given
 * {@link Graph} is bipartite or not
 * 
 * @author Vikram Kommaraju
 */
public class DFSBipartite {
	
	private Graph g;
	private boolean[] marked;
	private boolean[] color;
	
	boolean isBipartite;

	public DFSBipartite(Graph g) {
		this.g = g;
		
		marked = new boolean[g.V()];
		color = new boolean[g.V()];
		isBipartite = true;
		
		for(int s=0; s<g.V(); s++) {
			if(!marked[s]) {
				dfs(g, s);
			}
		}
	}
	
	public boolean isBipartite() {
		return isBipartite;
	}
	
	private void dfs(Graph g, int v) {
		marked[v] = true;
		color[v] = true;
		
		Iterable<Integer> adj = g.adj(v);
		for(int w : adj) {
			if(!marked[w]) {
				color[w] = !color[v];
				dfs(g, w);
			} else {
				if(color[w] == color[v]) {
					isBipartite = false;
				}
			}
		}		
	}
	
	public static void main(String[] args) throws Exception {
		Graph g = UndirectedGraph.createSmallGraph();
		DFSBipartite dfsBipartite = new DFSBipartite(g);
		System.out.println(g);
		System.out.println("Is the graph bipartite ? " + dfsBipartite.isBipartite());
	}
	
}
