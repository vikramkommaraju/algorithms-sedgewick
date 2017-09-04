package algorithms.sedgewick.graphs.api;

/**
 * 
 * This class has util methods for {@link Graph}
 * 
 * @author Vikram Kommaraju
 */
public class GraphUtils {
	
	@SuppressWarnings("unused")
	public static int degree(Graph g, int v) {
		int degree = 0;
		Iterable<Integer> adjList = g.adj(v);
		for(int w : adjList) {
			degree++;
		}
		return degree;
	}
	
	public static int maxDegree(Graph g) {
		int max = 0;
		for(int v=0; v<g.V(); v++) {
			int degree = degree(g, v);
			if(degree > max) {
				max = degree;
			}
		}
		return max;
	}
	
	public static int avgDegree(Graph g) {
		return 2*g.E() / g.V();
	}
	
	public static int numberOfSelfLoops(Graph g) {
		int count=0;
		for(int v=0; v<g.V(); v++) {
			Iterable<Integer> adjList = g.adj(v);
			for(int w : adjList) {
				if(v==w) count++;
			}
		}
		return count/2;
	}	
}