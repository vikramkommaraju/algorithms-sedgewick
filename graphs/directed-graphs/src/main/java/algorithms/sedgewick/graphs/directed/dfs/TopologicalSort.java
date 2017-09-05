package algorithms.sedgewick.graphs.directed.dfs;

import algorithms.sedgewick.graphs.api.Digraph;

/**
 * Implementation of Topological ordering of vertices in a Directed Acyclic
 * Graph
 *
 * @author Vikram Kommaraju
 */
public class TopologicalSort {
	
	private Iterable<Integer> order;
	
	public TopologicalSort(Digraph<Integer> g) {
		DirectedCycleDetection cycleFinder = new DirectedCycleDetection(g);
		if(!cycleFinder.hasCycle()) {
			DepthFirstOrder dfsOrder = new DepthFirstOrder(g);
			order = dfsOrder.reversePost();
		}
	}
	
	public boolean isDAG() {
		return this.order != null;
	}
	
	public Iterable<Integer> order() {
		return this.order;
	}
	
	public static void main(String[] args) throws Exception {
		SymbolDigraph symDag = SymbolDigraph.createJobsGraph();
		TopologicalSort sort = new TopologicalSort(symDag.G());
		//System.out.println(symDag);
		System.out.println("Digraph is a DAG ? " + sort.isDAG());

		for(int v : sort.order()) {
			System.out.println(symDag.name(v));
		}
		System.out.println();
		
	}

}
