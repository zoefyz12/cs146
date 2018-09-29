package sjsu.Zhao.cs146.project4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MSTTester {

	public static void main(String[] args) throws IOException {

		double[][] g1 = createGraph("test.txt");

/*		for (int i = 0; i < g1.length; i++) {
			for (int j = 0; j < g1.length; j ++) {
				System.out.print(g1[i][j] + "  ");
			}
			
			System.out.println();
		}*/
		
		AlgMST newMst = new AlgMST(g1);
		newMst.makeMST();
		newMst.getMST();
	}

	public static double[][] createGraph(String fileName) throws IOException {

		double[][] graph = null;
		String line = "";
		int counter = 0;
		int numVertice = 0;
		int numEdge = 0;
		String[] splitLine;

		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		while ((line = bufferedReader.readLine()) != null) {
			counter++;
			splitLine = line.trim().split("\\s+");
			
			if (counter == 1) {
				numVertice = Integer.parseInt(splitLine[0]);
				
				graph = new double[numVertice][numVertice]; 
				
				for (int i = 0; i < numVertice; i++) {
					for (int j = 0; j < numVertice; j ++) {
						graph[i][j] = 0.0;
					}
				}
				
			} else if (counter == 2) {
				numEdge = Integer.parseInt(splitLine[0]);
			} else {
				// first be the col, second be the row
				graph[Integer.parseInt(splitLine[0])][Integer.parseInt(splitLine[1])] = Double.parseDouble(splitLine[2]);
				graph[Integer.parseInt(splitLine[1])][Integer.parseInt(splitLine[0])] = Double.parseDouble(splitLine[2]);
			}
		}

		bufferedReader.close(); 
		
		return graph;
	}

}
