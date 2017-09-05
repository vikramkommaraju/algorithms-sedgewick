package algorithms.sedgewick.graphs.undirected.bfs;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

import algorithms.sedgewick.graphs.api.Graph;
import algorithms.sedgewick.graphs.api.Paths;
import algorithms.sedgewick.graphs.undirected.UndirectedGraph;

/**
 * Implementation of {@link Paths} that uses {@link BreadthFirstSearch}
 * 
 * @author Vikram Kommaraju
 *
 */
public class BreadthFirstSearchPaths extends Paths {

	private boolean marked[];
	private int edgeTo[];
	
	public BreadthFirstSearchPaths(Graph<Integer> g, int source) {
		super(g, source);
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		bfs(g, source);
	}

	@Override
	public boolean hasPathTo(int v) {
		return marked[v];
	}

	@Override
	public Iterable<Integer> pathTo(int v) {
		Stack<Integer> path = new Stack<Integer>();
		if (!hasPathTo(v)) {
			return null;
		}
		/*
		 * edgeTo is a vertex indexed array. Follow the trace back by using edgeTo until
		 * you reach the source
		 */
		for (int x = v; x != source; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(source);
		return path;
	}
	
	private void bfs(Graph<Integer> g, int source) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		marked[source] = true;
		q.add(source);

		while(!q.isEmpty()) {
			int v = q.remove();
			for(int w : g.adj(v)) {
				if(!marked[w]) {
					marked[w] = true;
					edgeTo[w] = v; // We reached w from v
					q.add(w);	
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		int source = 3;
		int sink = 6;
		Graph<Integer> g = UndirectedGraph.createSmallGraph();
		Paths p = new BreadthFirstSearchPaths(g, source);
		System.out.println("The graph is: " + g);
		System.out.println("Is there a path from " + source + " to " + sink + " ? : " + p.hasPathTo(sink));
		System.out.println("Path from " + source + " to " + sink + " is " + p.pathTo(sink));
	}

}
