package algorithms.sedgewick.graphs.spt;

import algorithms.sedgewick.graphs.api.DirectedEdge;
import algorithms.sedgewick.graphs.api.ShortestPath;
import algorithms.sedgewick.graphs.api.WeightedDigraph;

/**
 * Supports client queries of the form "Given a source vertex s and a target
 * vertex t, is there a path from s to t? If so, find a shortest such path (one
 * whose total weight is minimal)"
 *
 * @author Vikram Kommaraju
 */
public class DijkstraAllPairsSP {
	
	private ShortestPath[] singleSourceSP;
	
	DijkstraAllPairsSP(WeightedDigraph g) {
		
		singleSourceSP = new ShortestPath[g.V()];
		for(int v=0; v<g.V(); v++) {
			singleSourceSP[v] = new DijkstraSingleSourceSP(g, v);
		}
		
	}
	
	public Iterable<DirectedEdge> path(int s, int t) {
		return singleSourceSP[s].path(t);
	}
	
	public double dist(int s, int t) {
		return singleSourceSP[s].distTo(t);
	}
	
	public static void main(String[] args) throws Exception {
		WeightedDigraph g = EdgeWeightedDigraph.createSmallGraph();
		System.out.println(g);
		
		DijkstraAllPairsSP spt = new DijkstraAllPairsSP(g);
		System.out.println(spt.path(0, 1));
		System.out.println(spt.path(0, 6));
		
	}

}
