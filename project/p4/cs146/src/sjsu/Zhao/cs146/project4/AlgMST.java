package sjsu.Zhao.cs146.project4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

/**
 * This program will get the mst from a provided graph with new algorithm
 * @author Fangyi & Jennifer
 *
 */

public class AlgMST {

	/**
	 * 
	 * This is the class used to present edge
	 *
	 */
	private class Edge {

		int p1;
		int p2;
		double weight;

		Edge(int p1, int p2, double weight) {
			this.p1 = p1;
			this.p2 = p2;
			this.weight = weight;
		}
	}

	private double[][] graph; // graph from user
	private double[][] result; // final mst
	private boolean[] node; // to represent visited vertices
	private ArrayList<Edge> edge; // list of edge 
	private Stack<Integer> dfs; // stack used for dfs

	private int numVertice; // number of vertice
	private int numEdge; // number of edge

	/**
	 * The constructor will get the graph from user and initialize all variables
	 * Also, each edge will be stored into a arraylist with edge object in decreasing order
	 * @param graph input graph from user
	 */
	
	public AlgMST(double[][] graph) {
		edge = new ArrayList<Edge>();
		dfs = new Stack<Integer>();
		this.graph = graph;
		numVertice = graph.length;
		node = new boolean[numVertice];
		result = graph;

		for (int i = 0; i < numVertice; i++) {
			for (int j = 0; j < i; j++) {
				if (graph[i][j] != 0.0) {
					edge.add(new Edge(i, j, graph[i][j]));
				}
			}

			node[i] = false;
		}

		Collections.sort(edge, new Comparator<Edge>() {

			@Override
			public int compare(Edge e1, Edge e2) {
				if (e1.weight > e2.weight)
					return -1;
				else if (e1.weight < e2.weight)
					return 1;
				else
					return 0;
			}
		});
		numEdge = edge.size();
	}

	/**
	 * The method will print result mst 
	 */
	
	public void getMST() {
		for (int i = 0; i < numVertice; i++) {
			for (int j = 0; j < numVertice; j++)
				System.out.print(result[i][j] + "   ");

			System.out.println();
		}
	}

	/**
	 * Start from heaviest edge, check if it is in a cycle.
	 * If so, remove this edge and move to next one
	 */
	public void makeMST() {
		for (int i = 0; i < numEdge; i++) {
			if (checkCycle(edge.get(i))) {
				result[edge.get(i).p1][edge.get(i).p2] = 0;
				result[edge.get(i).p2][edge.get(i).p1] = 0;
			}
		}
	}

	/**
	 * Check if all vertices are visited by checking all true in node array
	 * @return true if all vertices are visited, false otherwise
	 */
	
	private boolean allVisited() {

		for (int i = 0; i < numVertice; i++) {
			if (!node[i])
				return false;
		}

		return true;
	}

	/**
	 * Check if target vertice has any neighbor vertice has not been visited
	 * @param target vertice
	 * @return true if this vertice has a neighbor vertice that has not been visited, false otherwise 
	 */
	
	private boolean anyWayOut(int vertice) {
		for (int i = 0; i < numVertice; i++) {
			if (result[vertice][i] != 0) {
				if (!node[i]) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * find a neighbor vertice of target vertice that has not been visited and return it
	 * @param target vertice
	 * @return a neighbor vertice of target vertice that has not been visited
	 */
	
	private int chooseNei(int vertice) {
		for (int i = 0; i < numVertice; i++) {
			if (result[vertice][i] != 0) {
				if (!node[i]) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * check if if either v1 or v2 is the neighbor vertice of target vertice
	 * @param target vertice
	 * @param v1 
	 * @param v2
	 * @return true if either v1 or v2 is the neighbor vertice of target vertice, false otherwise
	 */
	
	private boolean checkCycleStart(int vertice, int v1, int v2) {
		for (int i = 0; i < numVertice; i++) {
			if (result[vertice][i] != 0) {
				if (i == v1 || i == v2)
					return true;
			}
		}
		
		return false;
	}

	/**
	 * Check if edge is in a cycle
	 * @param target edge
	 * @return true of edge is in a cycle, false otherwise
	 */
	
	private boolean checkCycle(Edge edge) {

		// clear everything
		dfs.clear();
		for (int i = 0; i < numVertice; i++) {
			node[i] = false;
		}

		int p1 = edge.p1;
		int p2 = edge.p2;
		dfs.push(p1);
		node[p1] = true;
		dfs.push(p2);
		node[p2] = true;
		int curVertice = p2;
		int parent = p1;

		// use dfs to go through the graph until all vertices are visited
		while (!allVisited() && dfs.size() != 1) {
			if (checkCycleStart(curVertice, p1, p2) && (parent != p1 && parent != p2)) {
				return true;
			}

			// if there is a unvisited neighbor vertice, pust it into stack and make it the current vertice 
			if (anyWayOut(curVertice)) {
				int tmp = chooseNei(curVertice);
				parent = curVertice;
				curVertice = tmp;
				dfs.push(curVertice);
				node[curVertice] = true;
			} else { // if there is not a unvisited neighbor vertice, pop one and make it the current vertice
				curVertice = dfs.pop();
				parent = dfs.peek();
			}
		}

		return false;
	}
}
