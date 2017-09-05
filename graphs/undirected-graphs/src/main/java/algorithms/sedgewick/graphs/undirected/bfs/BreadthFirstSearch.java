package algorithms.sedgewick.graphs.undirected.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

import algorithms.sedgewick.graphs.api.Graph;
import algorithms.sedgewick.graphs.api.SingleSourceGraphSearch;
import algorithms.sedgewick.graphs.undirected.UndirectedGraph;

/**
 * BreadthFirst Implementation of the {@link SingleSourceGraphSearch} API to find connected vertices
 * for a given source vertex in a {@link Graph}
 * 
 * @author Vikram Kommaraju
 *
 */
public class BreadthFirstSearch extends SingleSourceGraphSearch {
	boolean marked[];
	int count;

	protected BreadthFirstSearch(Graph g, int source) {
		super(g, source);
		marked = new boolean[g.V()];
		bfs(g, source);
	}

	private void bfs(Graph g, int source) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		marked[source] = true;
		q.add(source);

		while(!q.isEmpty()) {
			int v = q.remove();
			Iterable<Integer> adjList = g.adj(v);
			for(int w : adjList) {
				if(!marked[w]) {
					marked[w] = true;
					q.add(w);	
				}
			}
		}
	}
	
	@Override
	public boolean marked(int v) {
		return marked[v];
	}

	@Override
	public int count() {
		int count = 0;
		Iterable<Integer> adj = g.adj(source);
		for(int w : adj) {
			count++;
		}
		return count;
	}
	
	public static void main(String[] args) throws Exception {
		Graph g = UndirectedGraph.createSmallGraph();
		System.out.println(g);
		int source = 0;
		int sink = 3;
		SingleSourceGraphSearch s = new BreadthFirstSearch(g, source);
		System.out.println(s.marked(sink));
		System.out.println(s.count());
	}

}
