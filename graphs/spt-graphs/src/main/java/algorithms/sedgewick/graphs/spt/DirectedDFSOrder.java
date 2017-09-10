package algorithms.sedgewick.graphs.spt;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

import algorithms.sedgewick.graphs.api.DirectedEdge;
import algorithms.sedgewick.graphs.api.WeightedDigraph;

/**
 * Similar to DepthFirstOrder but for Digraphs
 *
 * @author Vikram Kommaraju
 */
public class DirectedDFSOrder {
	
	private boolean marked[];
	private Queue<Integer> pre;
	private Queue<Integer> post;
	private Stack<Integer> reversePost;

	public DirectedDFSOrder(WeightedDigraph g, int source) {
		marked = new boolean[g.V()];
		pre = new ArrayDeque();
		post = new ArrayDeque();
		reversePost = new Stack();

		dfs(g, source);
	}

	private void dfs(WeightedDigraph g, int v) {
		marked[v] = true;
		pre.add(v);
		for (DirectedEdge e : g.adj(v)) {
			int w = e.to();
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
	
	public static void main(String[] args) throws Exception {
		WeightedDigraph g = EdgeWeightedDigraph.createSmallDAG();
		System.out.println(g);
		
		int source = 5;
		DirectedDFSOrder dfs = new DirectedDFSOrder(g, source);
		System.out.println(dfs.reversePost);
	}

}
