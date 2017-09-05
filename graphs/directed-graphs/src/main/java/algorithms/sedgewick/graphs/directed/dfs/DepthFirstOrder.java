package algorithms.sedgewick.graphs.directed.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

import algorithms.sedgewick.graphs.api.Digraph;

/**
 * This class has methods to get pre, post and reverse post order of a directed
 * graph. This ability is useful in the development of advanced
 * digraph-processing algorithms
 *
 * @author Vikram Kommaraju
 */
public class DepthFirstOrder {

	private boolean marked[];
	private Queue<Integer> pre;
	private Queue<Integer> post;
	private Stack<Integer> reversePost;

	public DepthFirstOrder(Digraph<Integer> g) {
		marked = new boolean[g.V()];
		pre = new ArrayDeque();
		post = new ArrayDeque();
		reversePost = new Stack();

		for (int v = 0; v < g.V(); v++) {
			if (!marked[v]) {
				dfs(g, v);
			}
		}
	}

	private void dfs(Digraph<Integer> g, int v) {
		marked[v] = true;
		Iterable<Integer> adjList = g.adj(v);
		pre.add(v);
		for (int w : adjList) {
			if (!marked[w]) {
				dfs(g, w);
			}
		}
		post.add(v);
		reversePost.push(v);
	}

	public Iterable<Integer> pre() {
		return pre;
	}

	public Iterable<Integer> post() {
		return post;
	}

	public Iterable<Integer> reversePost() {
		// Bug in java.util.Stack. Iterator iterates in insertion order
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int v : reversePost) {
			arr.add(0, v);
		}

		return arr;
	}
}