package sjsu.Zhao.cs146.project4;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MSTJUnit {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testTiming() throws IOException {
		double[][] g1 = createGraph("tinyEWG.txt");
		double[][] g2 = createGraph("mediumEWG.txt");
//		double[][] g3 = createGraph("largeEWG.txt");
		double[][] g4 = createGraph("1000EWG.txt");
		double[][] g5 = createGraph("10000EWG.txt");
		
		
		long start = System.currentTimeMillis();
		AlgMST newMst = new AlgMST(g1);
		newMst.makeMST();
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		
		System.out.println("The running time for new algorithm is " + elapsed + " ms.");
		
		start = System.currentTimeMillis();
		PrimMST prim = new PrimMST(g1);
		prim.makeMST();
		end = System.currentTimeMillis();
		elapsed = end - start;
		System.out.println("The running time for prim algorithm is " + elapsed + " ms.");
		
		
		start = System.currentTimeMillis();
		AlgMST newMst2 = new AlgMST(g2);
		newMst2.makeMST();
		end = System.currentTimeMillis();
		elapsed = end - start;
		
		System.out.println("The running time for new algorithm is " + elapsed + " ms.");
		
		start = System.currentTimeMillis();
		PrimMST prim2 = new PrimMST(g2);
		prim2.makeMST();
		end = System.currentTimeMillis();
		elapsed = end - start;
		System.out.println("The running time for prim algorithm is " + elapsed + " ms.");
		
		
		
		start = System.currentTimeMillis();
		AlgMST newMst4 = new AlgMST(g4);
		newMst4.makeMST();
		end = System.currentTimeMillis();
		elapsed = end - start;
		
		System.out.println("The running time for new algorithm is " + elapsed + " ms.");
		
		start = System.currentTimeMillis();
		PrimMST prim4 = new PrimMST(g4);
		prim4.makeMST();
		end = System.currentTimeMillis();
		elapsed = end - start;
		System.out.println("The running time for prim algorithm is " + elapsed + " ms.");
		
		
		
		
		
		start = System.currentTimeMillis();
		AlgMST newMst5 = new AlgMST(g5);
		newMst5.makeMST();
		end = System.currentTimeMillis();
		elapsed = end - start;
		
		System.out.println("The running time for new algorithm is " + elapsed + " ms.");
		
		start = System.currentTimeMillis();
		PrimMST prim5 = new PrimMST(g5);
		prim5.makeMST();
		end = System.currentTimeMillis();
		elapsed = end - start;
		System.out.println("The running time for prim algorithm is " + elapsed + " ms.");
		
		
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
