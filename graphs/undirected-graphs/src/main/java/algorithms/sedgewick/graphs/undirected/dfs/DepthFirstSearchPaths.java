package algorithms.sedgewick.graphs.undirected.dfs;

import java.util.Stack;

import algorithms.sedgewick.graphs.api.Graph;
import algorithms.sedgewick.graphs.api.Paths;
import algorithms.sedgewick.graphs.undirected.UndirectedGraph;

/**
 * Implementation of {@link Paths} that uses {@link DepthFirstSearch}
 * 
 * @author Vikram Kommaraju
 *
 */
public class DepthFirstSearchPaths extends Paths {

	private int[] edgeTo;
	private boolean[] markedVertices;

	public DepthFirstSearchPaths(Graph<Integer> g, int source) {
		super(g, source);
		markedVertices = new boolean[g.V()];
		edgeTo = new int[g.V()];
		dfs(g, source);
	}

	private void dfs(Graph<Integer> g, int v) {
		markedVertices[v] = true;
		for (int w : g.adj(v)) {
			if (!isMarked(w)) {
				edgeTo[w] = v; // We reached w from v
				dfs(g, w);
			}
		}
	}

	@Override
	public boolean hasPathTo(int v) {
		return markedVertices[v];
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

	private boolean isMarked(int w) {
		return markedVertices[w];
	}

	public static void main(String[] args) throws Exception {
		int source = 0;
		int sink = 5;
		Graph<Integer> g = UndirectedGraph.createSmallGraph();
		Paths p = new DepthFirstSearchPaths(g, source);
		System.out.println("The graph is: " + g);
		System.out.println("Is there a path from " + source + " to " + sink + " ? : " + p.hasPathTo(sink));
		System.out.println("Path from " + source + " to " + sink + " is " + p.pathTo(sink));
	}
}
