package algorithms.sedgewick.graphs.spt;

import java.util.List;
import java.util.Stack;

import algorithms.sedgewick.graphs.api.CycleDetection;
import algorithms.sedgewick.graphs.api.DirectedEdge;
import algorithms.sedgewick.graphs.api.WeightedDigraph;

/**
 * Implementation of {@link CycleDetection} API for {@link WeightedDigraph}s
 *
 * @author Vikram Kommaraju
 */
public class WeightedDigraphCycleDetection implements CycleDetection {

	private WeightedDigraph g;
	private boolean[] marked;
	private DirectedEdge[] edgeTo;
	private boolean[] onStack;
	private List<DirectedEdge> verticesInCycle;
	
	public WeightedDigraphCycleDetection(WeightedDigraph g) {
		this.g = g;
		marked = new boolean[g.V()];
		edgeTo = new DirectedEdge[g.V()];
		onStack = new boolean[g.V()];
		
		for(int v=0; v<g.V(); v++) {
			if(!marked[v]) {
				dfs(g, v);
			}
		}
	}
	
	private void dfs(WeightedDigraph g, int v) {
		marked[v] = true;
		onStack[v] = true;
		for(DirectedEdge e : g.adj(v)) {
			int w = e.to();
			if(!marked[w]) {
				edgeTo[w] = e;
				dfs(g, w);
			} else {
				if(onStack[w]) { // Cycle has been detected at w. Unwind back
					verticesInCycle = new Stack<DirectedEdge>();

					for (DirectedEdge x=e; x != edgeTo[w]; x=edgeTo[x.from()]) {
	                		verticesInCycle.add(0, x);
	                }
				}
			}
		}
		onStack[v] = false;
		
	}
	
	public List<DirectedEdge> cycle() {
		return verticesInCycle;
	}

	public boolean hasCycle() {
		return verticesInCycle != null;
	}

}
