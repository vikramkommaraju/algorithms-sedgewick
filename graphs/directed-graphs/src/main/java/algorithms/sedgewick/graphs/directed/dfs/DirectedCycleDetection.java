package algorithms.sedgewick.graphs.directed.dfs;

import java.util.List;
import java.util.Stack;

import algorithms.sedgewick.graphs.api.Digraph;

/**
 * This class uses DFS to determine if a given {@link Digraph} has a cycle or
 * not
 *
 * @author Vikram Kommaraju
 */
public class DirectedCycleDetection {
	
	private Digraph<Integer> g;
	private boolean[] marked;
	private int[] edgeTo;
	private boolean[] onStack;
	private Stack<Integer> verticesInCycle;
	
	public DirectedCycleDetection(Digraph<Integer> g) {
		this.g = g;
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		onStack = new boolean[g.V()];
		
		for(int v=0; v<g.V(); v++) {
			if(!marked[v]) {
				dfs(g, v);
			}
		}
	}
	
	private void dfs(Digraph g, int v) {
		marked[v] = true;
		onStack[v] = true;
		Iterable<Integer> adjList = g.adj(v);
		for(int w : adjList) {
			if(!marked[w]) {
				edgeTo[w] = v;
				dfs(g, w);
			} else {
				if(onStack[w]) { // Cycle has been detected at w. Unwind back
					verticesInCycle = new Stack<Integer>();
					for(int x=v; x != w; x = edgeTo[x]) {
						verticesInCycle.push(x);
					}
					verticesInCycle.push(w);
					verticesInCycle.push(v);
				}
			}
		}
		onStack[v] = false;
		
	}
	
	public Stack<Integer> cycle() {
		return verticesInCycle;
	}

	public boolean hasCycle() {
		return verticesInCycle != null;
	}

}
