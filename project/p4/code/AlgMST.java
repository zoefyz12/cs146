package sjsu.Zhao.cs146.project4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class AlgMST {

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

	private double[][] graph;
	private double[][] result;
	private boolean[] node;
	private ArrayList<Edge> edge;
	private Stack<Integer> dfs;

	private int numVertice;
	private int numEdge;

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

	public void getMST() {
		for (int i = 0; i < numVertice; i++) {
			for (int j = 0; j < numVertice; j++)
				System.out.print(result[i][j] + "   ");

			System.out.println();
		}
	}

	public void makeMST() {
		for (int i = 0; i < numEdge; i++) {
			if (checkCycle(edge.get(i))) {
				result[edge.get(i).p1][edge.get(i).p2] = 0;
				result[edge.get(i).p2][edge.get(i).p1] = 0;
			}
		}
	}

	private boolean allVisited() {

		for (int i = 0; i < numVertice; i++) {
			if (!node[i])
				return false;
		}

		return true;
	}

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

	private boolean checkCycleStart(int vertice, int v1, int v2) {
		for (int i = 0; i < numVertice; i++) {
			if (result[vertice][i] != 0) {
				if (i == v1 || i == v2)
					return true;
			}
		}
		
		return false;
	}

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

		while (!allVisited() && dfs.size() != 1) {
			if (checkCycleStart(curVertice, p1, p2) && (parent != p1 && parent != p2)) {
				return true;
			}

			if (anyWayOut(curVertice)) {
				int tmp = chooseNei(curVertice);
				parent = curVertice;
				curVertice = tmp;
				dfs.push(curVertice);
				node[curVertice] = true;
			} else {
				curVertice = dfs.pop();
				parent = dfs.peek();
			}
		}

		return false;
	}
}
