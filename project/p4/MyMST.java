package sjsu.Ma.cs146.project4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author Chenhui Ma
 * 		   Bowen Han
 * Project 4: Graph Algorithms Minimum Spanning Tree
 * This class implements the new algorithm of solving MST
 */
public class MyMST
{
	// Inner class edge, provide the edge model.
	// includes fields: the source vertex, the destination vertex, and the weight of the edge
	public class Edge
	{
		int sourceVertex;
		int destinationVertex;
		double weight;

	}

	// The comparator of the edge, in order make sort method works
	public class EdgeComparator implements Comparator<Edge>
	{
		@Override
		public int compare(Edge edge1, Edge edge2)
		{
			// return 1 if the weight is smaller and -1 if is larger 
			// because we need to sort in decending order
			if (edge1.weight < edge2.weight)
				return 1;
			if (edge1.weight > edge2.weight)
				return -1;
			return 0;
		}
	}
	
	public static final int INFINITE = 10; // to imitate the infinite weight value

	private List<Edge> edges; // the list includes all provided edges
	private int verticesNumber; // the number of total vertices
	private int[] visited; // the vertices that is already visited
	private double[][] MST; // the constructed minimum spanning tree


	public MyMST(int numberOfVertices)
	{
		// initialize all fields
		this.verticesNumber = numberOfVertices;
		edges = new LinkedList<Edge>();
		visited = new int[verticesNumber];
		MST = new double[numberOfVertices][numberOfVertices];
	}

	/**
	 * construct an edges list
	 * @param graph the input graph that is used to construct the edges list
	 */
	public void construcEdges(double[][] graph){
		for (int source = 0; source < verticesNumber; source++)
		{
			for (int destination = 0; destination < verticesNumber; destination++)
			{
				if (graph[source][destination] != INFINITE && source != destination)
				{
					Edge edge = new Edge(); 
					edge.sourceVertex = source;
					edge.destinationVertex = destination;
					edge.weight = graph[source][destination];
					graph[destination][source] = INFINITE; // do this for not adding the same edge twice
					edges.add(edge);
				}
			}
		}

		Collections.sort(edges, new EdgeComparator()); // sort the edges list in decending order
	}
	
	/**
	 * the new algorithm of solving the MST
	 * @param graph the input graph that is used to solve MST
	 */
	public void myAlgorithm(double[][] graph)
	{
		construcEdges(graph); // construct edges list first
		
		boolean finished = false; // use finished variable to provide the end point of the algorithm

		for (Edge edge : edges){
			MST[edge.sourceVertex][edge.destinationVertex] = edge.weight;
			MST[edge.destinationVertex][edge.sourceVertex] = edge.weight;
		} // initially set all edges true in the MST


		for (Edge edge : edges) // checking each edge in decending order
		{
			cycleChecker checkCycle = new cycleChecker();
			if (checkCycle.isCycle(MST, edge.sourceVertex, edge.destinationVertex))
			{
				MST[edge.sourceVertex][edge.destinationVertex] = 0;
				MST[edge.destinationVertex][edge.sourceVertex] = 0;
				edge.weight = -1;
				continue;
			} // if there is a cycle at this edge, eliminate this edge from MST

			visited[edge.sourceVertex] = 1;
			visited[edge.destinationVertex] = 1; // set this edge as visited

			for (int i = 0; i < visited.length; i++)
			{
				if (visited[i] == 0)
				{
					finished = false;
					break;
				} else
				{
					finished = true;
				}
			}
			if (finished) // if all edge is visited, end this method
				break;
		}

	}

	/**
	 * print the MST as a 2d form
	 */
	public void printMST() 
	{
		System.out.println("The spanning tree using new algorithm ");
		for (int i = 0; i < verticesNumber; i++)
			System.out.print("\t" + i);
		System.out.println();
		for (int source = 0; source < verticesNumber; source++)
		{
			System.out.print(source + "\t");
			for (int destination = 0; destination < verticesNumber; destination++)
			{
				System.out.print(MST[source][destination] + "\t");
			}
			System.out.println();
		}
	}
	
	 public double[][] getMST(){
	    	return MST;
	  }

	
	// Inner class that use to check the if there is a cycle
	public class cycleChecker
	{
		private Stack<Integer> stack; // need for DFS
		private double[][] graph;

		public cycleChecker()
		{
			stack = new Stack<Integer>();
		}


		/**
		 * Check if there is a cycle at the input edge
		 * start from destination vertex. Check if there is a way to the source vertex
		 * @param inputGraph input Graph that is used to evaluate if there is a cycle
		 * @param source the source vertex of the edge
		 * @param destination the destination vertex of the edge
		 * @return return true if there is a cycle, and false if not
		 */
		public boolean isCycle(double[][] inputGraph, int source, int destination)
		{
			boolean hasCycle = false;
			int nodeNumber = inputGraph[source].length;

			graph = new double[nodeNumber][nodeNumber];
			
			for (int sourceVertex = 0; sourceVertex < nodeNumber; sourceVertex++)
			{
				for (int destinationVertex = 0; destinationVertex < nodeNumber; destinationVertex++)
				{
					graph[sourceVertex][destinationVertex] = inputGraph[sourceVertex][destinationVertex];
				}
			} // first, copy all the data from the input graph to local instance variable

			int visited[] = new int[nodeNumber];
			int element = destination;
			int i = destination;
			visited[destination] = 1;
			visited[source] = 1;
			stack.push(source);
			stack.push(destination); // push destination
			graph[source][destination] = -1;
			graph[destination][source] = -1; // set this edge as visited, -1 means already visited
			while (!stack.isEmpty() && stack.peek() != source)
			{
				element = stack.peek();
				i = 0; // check all vertices

				while (i < nodeNumber)
				{
					if (graph[element][i] > 0 && visited[i] == 0)
					{
						stack.push(i);
						visited[i] = 1;
						graph[element][i] = -1;
						graph[i][element] = -1; // if there is a new meaningful edge, set it as visited
						element = i;
						i = 0; // continue searching
						continue;
					}
					
					if (graph[element][i] > 0 && visited[i] == 1)
					{
						// if there is a visited vertex and that vertex is the source, return true
						if (stack.contains(i) && i == source) 
						{

							hasCycle = true;
							return hasCycle;
						}
					}

					i++;
				}
				stack.pop();
			}
			return hasCycle;
		}
	}

	

}
 

