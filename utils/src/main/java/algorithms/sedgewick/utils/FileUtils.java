package algorithms.sedgewick.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * This class has helper methods for IO operations
 * 
 * @author Vikram Kommaraju
 *
 */
public class FileUtils {
	
	public static GraphFileInput getInputDataFromFile(String inputFilePath) throws Exception {	
		File inputFile = new File(inputFilePath);
		List<String> allLines = Files.readAllLines(inputFile.toPath());
		int numberOfVertices = Integer.parseInt(allLines.get(0));
		int numberOfEdges = Integer.parseInt(allLines.get(1));
		List<String> edges = allLines.subList(2, allLines.size());
		return GraphFileInput.create(numberOfVertices, numberOfEdges, edges);
	}
	
	public static List<String> readAllLines(String inputFilePath) throws Exception {
		File inputFile = new File(inputFilePath);
		return Files.readAllLines(inputFile.toPath());
	}	
}
