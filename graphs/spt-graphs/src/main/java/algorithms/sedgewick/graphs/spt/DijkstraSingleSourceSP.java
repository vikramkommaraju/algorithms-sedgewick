package algorithms.sedgewick.graphs.spt;

import java.util.ArrayList;

import algorithms.sedgewick.graphs.api.DirectedEdge;
import algorithms.sedgewick.graphs.api.ShortestPath;
import algorithms.sedgewick.graphs.api.WeightedDigraph;
import algorithms.sedgewick.utils.IndexMinPQ;

/**
 * Implementation of the {@link ShortestPath} API computed for a single source vertex
 *
 * @author Vikram Kommaraju
 */
public class DijkstraSingleSourceSP extends ShortestPath {

	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pQueue;
	
	public DijkstraSingleSourceSP(WeightedDigraph g, int source) {
		super(g, source);
		
		edgeTo = new WeightedDirectedEdge[g.V()];
		distTo = new double[g.V()];
		pQueue = new IndexMinPQ<Double>(g.V());
		
		for(int i=0; i<g.V(); i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		
		distTo[source] = 0.0;
		pQueue.insert(source, 0.0);
		
		while(!pQueue.isEmpty()) {
			int v = pQueue.delMin();
			relax(g, v);
		}
	}
	
	private void relax(WeightedDigraph g, int v) {
		
		for(DirectedEdge e : g.adj(v)) {
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if(pQueue.contains(w)) {
					pQueue.changeKey(w, distTo[w]);
				} else {
					pQueue.insert(w, distTo[w]);
				}
				
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
		WeightedDigraph g = EdgeWeightedDigraph.createSmallGraph();
		System.out.println(g);
		
		int source=0;
		int sink=1;
		ShortestPath spt = new DijkstraSingleSourceSP(g, source);
		System.out.println(spt.distTo(sink));
		System.out.println(spt.path(sink));
	}
	

}
