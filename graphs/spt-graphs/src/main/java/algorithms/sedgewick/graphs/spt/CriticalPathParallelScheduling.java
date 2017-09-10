package algorithms.sedgewick.graphs.spt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import algorithms.sedgewick.graphs.api.WeightedDigraph;

/**
 * Implementation for Critical path method for parallel precedence-constrained
 * job scheduling
 *
 * @author Vikram Kommaraju
 */
public class CriticalPathParallelScheduling {

	private WeightedDigraph g;
	
	private static final String JOBS = "src/main/resources/jobsPC.txt";

	private WeightedDigraph buildGraph(String inputFilePath) throws IOException {
		File inputFile = new File(inputFilePath);
		List<String> allLines = Files.readAllLines(inputFile.toPath());
		int N = Integer.parseInt(allLines.get(0));
		
		g = new EdgeWeightedDigraph(2*N + 2);
		
		int s = 2*N;
		int t = 2*N +1;
		for(int i=1; i<allLines.size(); i++) {
			String line = allLines.get(i);
			String[] input = line.split("\\s+");
			double duration = Double.parseDouble(input[0]);
	           g.addEdge(new WeightedDirectedEdge(i-1, i-1+N, duration));
	           g.addEdge(new WeightedDirectedEdge(s, i-1, 0.0));
	           g.addEdge(new WeightedDirectedEdge(i-1+N, t, 0.0));
	           for (int j = 1; j < input.length; j++)
	           {
	              int successor = Integer.parseInt(input[j]);
	              g.addEdge(new WeightedDirectedEdge(i-1+N, successor, 0.0));
	           }
		}
		
		return g;
	}
	
	public static WeightedDigraph createJobsDAG() throws Exception {
		return new CriticalPathParallelScheduling().buildGraph(JOBS);
	}
	
	public static void main(String[] args) throws Exception {
		WeightedDigraph g = CriticalPathParallelScheduling.createJobsDAG();
		System.out.println(g);
		int N = 10;
		int s = 2*N;
		int t = 2*N+1;
		AcyclicSingleSourceLP lpt = new AcyclicSingleSourceLP(g, s);
		System.out.println("Start times...");
		for(int i=0; i<N; i++) {
			System.out.println(lpt.distTo(i));
		}
		System.out.println("Finish time : " + lpt.distTo(t));
		
	}
}
