package sjsu.Zhao.cs146.project4;

/**
 * 
 * @author Jennifer Nguyen and Fangyi Zhao
 *
 */

public class PrimMST {

	private double[][] graph; 
	private boolean[] visited; //an array to represent which vertex has not been visited
	private int[] parent; // array to store the info of the minimum spanning tree
	private double[] keyValue; // array of value to present the lowest weighted edge/

	private int numVertice;

	/**
	 * This function is to construct the PrimMST class
	 * @param graph is the graph that is used to find MST.
	 */
	public PrimMST(double[][] graph) {
		this.graph = graph;
		numVertice = graph.length;
		visited = new boolean[numVertice];
		parent = new int[numVertice];
		keyValue = new double[numVertice];
	}

	/**
	 * This method prints out the minimum spanning tree in a form of a 2d array. 
	 */
	public void getMST() {
		for (int i = 0; i < numVertice; i++) {
			for (int j = 0; j < numVertice; j++)
				System.out.print(graph[i][j] + "   ");

			System.out.println();
		}
	}

	/**
	 * this method converts the graph into a MST
	 */
	public void makeMST() {

		// reset everything
		for (int i = 0; i < numVertice; i++) {
			keyValue[i] = Integer.MAX_VALUE; //sets all the keys to infinity
			visited[i] = false; //sets all the vertices in visited to false;
		}

		keyValue[0] = 0;
		parent[0] = -1;

		for (int i = 0; i < numVertice - 1; i++) {
			int tmpVertice = extractMin();
			visited[tmpVertice] = true;

			for (int j = 0; j < numVertice; j++)
				if (graph[tmpVertice][j] != 0) {
					if (!visited[j] && graph[tmpVertice][j] < keyValue[j]) {
						parent[j] = tmpVertice;
						keyValue[j] = graph[tmpVertice][j];
					}
				}
		}
	}

	/**
	 * this function is used to find the vertex that has the smallest key value
	 * @return the index of the vertex that is not included in our visited
	 */
	private int extractMin() {
		double min = Double.MAX_VALUE;
		int minIndex = 0;

		for (int i = 0; i < numVertice; i++)
			if (!visited[i]) {
				if (keyValue[i] < min) {
					min = keyValue[i];
					minIndex = i;
				}
			}

		return minIndex;
	}
}
