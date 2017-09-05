package algorithms.sedgewick.graphs.directed.dfs;

import algorithms.sedgewick.fundamentals.api.ConnectedComponent;
import algorithms.sedgewick.graphs.api.Digraph;

/**
 * Implementation of the {@link ConnectedComponent} API to determine "Strongly"
 * connected components in a {@link Digraph}
 * 
 * Definition: Two vertices v and w are strongly connected if they are mutually
 * reachable: that is, if there is a directed path from v to w AND a directed
 * path from w to v. A digraph is strongly connected if all its vertices are
 * strongly connected to one another. 
 * 
 * Calculating vertices that can reach a particular vertex V: 
 * Just reverse all the edges in G, and do a DFS starting from V.
 * 
 * Further Explanation:
 * https://www.youtube.com/watch?v=RpgcYiky7uw
 * 
 * https://algointuition.wordpress.com/2014/11/06/strongly-connected-components-algorithm-kosaraju/
 * 
 *
 *
 * @author Vikram Kommaraju
 */
public class KosarajuStronglyCC implements ConnectedComponent {

	private boolean marked[];
	private int id[];
	private int count;

	KosarajuStronglyCC(Digraph<Integer> g) {
		marked = new boolean[g.V()];
		id = new int[g.V()];
		count = 0;
		Digraph<Integer> reversedDigraph = g.reverse();
		DepthFirstOrder dfsOrder = new DepthFirstOrder(reversedDigraph);
		
		for(int v : dfsOrder.reversePost()) {
			if(!marked[v]) {
				dfs(g, v);
				count++;
			}
		}
		
	}
	
	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}
	
	public boolean connected(int v, int w) {
		return stronglyConnected(v, w);
	}
	
	private void dfs(Digraph<Integer> g, int v) {
		marked[v] = true;
		id[v] = count;
		for(int w : g.adj(v)) {
			if(!marked[w]) {
				dfs(g, w);
			}
		}
	}

	public int count() {
		return count;
	}

	public int id(int v) {
		return id[v];
	}

}
