package algorithms.sedgewick.graphs.spt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import algorithms.sedgewick.graphs.api.DirectedEdge;
import algorithms.sedgewick.graphs.api.WeightedDigraph;

/**
 * Implemention of Arbitrage in curreny Exchange
 *
 * @author Vikram Kommaraju
 */
public class Arbitrage {
	static String[] name = {"USD", "EUR", "GBP", "CHF", "CAD"};
	WeightedDigraph g;
	
	private static final String RATES = "src/main/resources/rates.txt";
	
	private WeightedDigraph buildGraph(String inputFilePath) throws IOException {
		File inputFile = new File(inputFilePath);
		List<String> allLines = Files.readAllLines(inputFile.toPath());
		int N = Integer.parseInt(allLines.get(0));
		
		g = new EdgeWeightedDigraph(N);
		
		for(int v=0; v<N; v++) {
			String line = allLines.get(v+1);
			String[] input = line.split("\\s+");
			for(int w=1; w<input.length; w++) {
				Double rate = Double.parseDouble(input[w]);
				DirectedEdge edge = new WeightedDirectedEdge(v, w-1, -Math.log(rate));
				g.addEdge(edge);
			}
		}
	
		return g;
	}
	
	public static WeightedDigraph createArbitrageGraph() throws Exception {
		return new Arbitrage().buildGraph(RATES);
	}
	
	public static void main(String[] args) throws Exception {
		WeightedDigraph g = Arbitrage.createArbitrageGraph();
		System.out.println(g);
		
		int source = 0;
		BellmanFordSP spt = new BellmanFordSP(g, source);
		
		double stake = 1000.0;
		if(spt.hasNegativeCycle()) {
			
			for(DirectedEdge e : spt.negativeCycle()) {
				System.out.printf("%10.5f %s ", stake, name[e.from()]);
				stake *= Math.exp(-e.weight());
				System.out.printf("= %10.5f %s\n", stake, name[e.to()]);
			}
		}
	}

}
