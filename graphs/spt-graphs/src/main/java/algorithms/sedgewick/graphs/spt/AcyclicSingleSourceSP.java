package algorithms.sedgewick.graphs.spt;

import java.util.ArrayList;

import algorithms.sedgewick.graphs.api.DirectedEdge;
import algorithms.sedgewick.graphs.api.ShortestPath;
import algorithms.sedgewick.graphs.api.WeightedDigraph;

/**
 * Implementation of the {@link ShortestPath} API which can be applied to
 * edge-weighted DAGs
 *
 * @author Vikram Kommaraju
 */
public class AcyclicSingleSourceSP extends ShortestPath {
	
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	
	public AcyclicSingleSourceSP(WeightedDigraph g, int source) {
		super(g, source);
		edgeTo = new DirectedEdge[g.V()];
		distTo = new double[g.V()];
		for(int i=0; i<distTo.length; i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		distTo[source] = 0.0;
		
		DirectedDFSOrder dfs = new DirectedDFSOrder(g, source);
		// reverse port gets the topological order
		for(int v : dfs.reversePost()) {
			relax(g, v);
		}	
	}
	
	private void relax(WeightedDigraph g, int v) {
		
		for(DirectedEdge e : g.adj(v)) {
			int w = e.to();
			
			if(distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
			}
		}
	}

	@Override
	public double distTo(int v) {
		return distTo[v];
	}

	@Override
	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	@Override
	public Iterable<DirectedEdge> path(int v) {
		ArrayList<DirectedEdge> path = new ArrayList<DirectedEdge>();
		for(DirectedEdge e=edgeTo[v]; e!=null; e=edgeTo[e.from()]) {
			path.add(0, e);
		}
		return path;
	}
	
	public static void main(String[] args) throws Exception {
		WeightedDigraph g = EdgeWeightedDigraph.createSmallDAG();
		System.out.println(g);
		
		int source=5;
		int sink=6;
		for(int i=0; i<g.V(); i++) {
			ShortestPath spt = new AcyclicSingleSourceSP(g, source);
			System.out.println(spt.distTo(i));
			System.out.println(spt.path(i));			
		}
	}

}
