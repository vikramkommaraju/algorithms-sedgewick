package algorithms.sedgewick.graphs.directed.dfs;

import algorithms.sedgewick.graphs.api.Digraph;

/**
 * Implementation of the all-pairs reachability problem. This API allows us to
 * find if ANY two pairs in a Digraph are reachable. Not just using a source
 * vertex rooted graph/tree.
 * 
 * Note: This solution is ideal for small or dense digraphs, but it is not a
 * solution for the large digraphs we might encounter in practice because the
 * constructor uses space proportional to V2 and time proportional to V (V E):
 * each of the V DirectedDFS objects takes space proportional to V (they all
 * have marked[] arrays of size V and examine E edges to compute the marks)
 * 
 * @author Vikram Kommaraju
 */
public class TransitiveClosure {

	// Perform DFS for every vertex as the source and store the reachability
	private DirectedDFS[] all;

	public TransitiveClosure(Digraph<Integer> g) {
		all = new DirectedDFS[g.V()];

		for (int s = 0; s < g.V(); s++) {
			all[s] = new DirectedDFS(g, s);
		}
	}

	public boolean reachable(int v, int w) {
		return all[v].marked(w);
	}

}
