package algorithms.sedgewick.graphs.spt;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import algorithms.sedgewick.graphs.api.CycleDetection;
import algorithms.sedgewick.graphs.api.DirectedEdge;
import algorithms.sedgewick.graphs.api.NegativeCycle;
import algorithms.sedgewick.graphs.api.ShortestPath;
import algorithms.sedgewick.graphs.api.WeightedDigraph;

/**
 * Implementation of the {@link ShortestPath} API along with {@link NegativeCycle}
 *
 * @author Vikram Kommaraju
 */
public class BellmanFordSP extends ShortestPath implements NegativeCycle {

	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private Queue<Integer> verticesOnQueue;
	private boolean[] onQueue;
	private int cost; //number of calls to relax
	private Iterable<DirectedEdge> cycle;
	
	public BellmanFordSP(WeightedDigraph g, int source) {
		super(g, source);
		distTo = new double[g.V()];
		edgeTo = new DirectedEdge[g.V()];
		onQueue = new boolean[g.V()];
		verticesOnQueue = new ArrayDeque<Integer>();
		
		for(int v=0; v<g.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[source] = 0.0;
		verticesOnQueue.add(source);
		onQueue[source] = true;
		
		while(!verticesOnQueue.isEmpty() && !this.hasNegativeCycle()) {
			int v = verticesOnQueue.remove();
			onQueue[v] = false;
			relax(g, v);
		}
	}
	
	private void relax(WeightedDigraph g, int v) {
		for (DirectedEdge e : g.adj(v)) {
	        int w = e.to(); 
	        if (distTo[w] > distTo[v] + e.weight())  {
	           distTo[w] = distTo[v] + e.weight();
	           edgeTo[w] = e;
	           if (!onQueue[w]) {
	              verticesOnQueue.add(w);
	              onQueue[w] = true;
	           }
	        }
	        if (cost++ % g.V() == 0) { 
	        		findNegativeCycle();	        	
	        }
		}
	}

	private void findNegativeCycle() {
		int V = edgeTo.length;
		WeightedDigraph G = new EdgeWeightedDigraph(V);
		for(int i=0; i<V; i++) {
			if(edgeTo[i] != null) {
				G.addEdge(edgeTo[i]);
			}
		}
		
		CycleDetection c = new WeightedDigraphCycleDetection(G);
		cycle = c.cycle();
		
	}
	
	public boolean hasNegativeCycle() {
		return cycle != null;
	}

	public Iterable<DirectedEdge> negativeCycle() {
		return cycle;
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
		WeightedDigraph g = EdgeWeightedDigraph.createNegativeCycleDigraph();
		System.out.println(g);
		int source = 0;
		int sink = 3;
		ShortestPath spt = new BellmanFordSP(g, source);
		System.out.println(spt.distTo(sink));
		NegativeCycle ncyc = new BellmanFordSP(g, source);
		System.out.println("Has negative cycle: " + ncyc.hasNegativeCycle());
		System.out.println("Cycle: " + ncyc.negativeCycle());
		
	}

}
