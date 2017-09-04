package algorithms.sedgewick.graphs.undirected.dfs;

import java.util.HashSet;
import java.util.Set;

import algorithms.sedgewick.fundamentals.api.ConnectedComponent;
import algorithms.sedgewick.graphs.api.Graph;
import algorithms.sedgewick.graphs.undirected.UndirectedGraph;

/**
 * Implementation of the {@link ConnectedComponent} API using {@link DepthFirstSearch}
 * 
 * @author Vikram Kommaraju
 *
 */
public class DepthFirstSearchCC implements ConnectedComponent {

	private Graph g;
	private boolean[] markedVertices;
	private int[] id;
	private int count;
	
	DepthFirstSearchCC(Graph g) {
		this.g = g;
		markedVertices = new boolean[g.V()];
		id = new int[g.V()];
		
		for(int v=0; v<g.V(); v++) {
			if(!marked(v)) {
				dfs(g, v);
				count++;
			}
		}
	}
	
	public boolean connected(int v, int w) {
		return id[v] == id[w];
	}

	public int count() {
		return count;
	}

	public int id(int v) {
		return id[v];
	}

	private void dfs(Graph g, int v) {
		markedVertices[v] = true;
		id[v] = count;
		
		Iterable<Integer> adjList = g.adj(v);
		for(int w : adjList) {
			if(!marked(w)) {
				dfs(g, w);
			}
		}
	}
	
	private boolean marked(int w) {
		return markedVertices[w];
	}
	
	
	public static void main(String[] args) throws Exception {
		Graph g = UndirectedGraph.createSmallGraph();
		DepthFirstSearchCC cc = new DepthFirstSearchCC(g);
		int numberOfComponents = cc.count();
		
		Set<Integer>[] components = new HashSet[numberOfComponents];
		for(int i=0; i<numberOfComponents; i++) {
			components[i] = new HashSet<Integer>();
		}
		for(int v=0; v<g.V(); v++) {
			int componentOfV = cc.id(v);
			components[componentOfV].add(v);
		}
		for(int i=0; i<numberOfComponents; i++) {
			System.out.println("Component: " + (i+1));
			System.out.println(components[i]);
		}
		System.out.println(cc.count());		
	}
}
