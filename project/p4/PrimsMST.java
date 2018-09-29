package sjsu.Ma.cs146.project4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Chenhui Ma
 * 		   Bowen Han
 * Project 4: Graph Algorithms Minimum Spanning Tree
 * This class implements Prim's algorithm of solving MST
 */
public class PrimsMST
{
	public static final double INFINITE = 999; // to imitate the infinite weight value
    
	private boolean[] unsettledVertices; // the vertices that are not settled
    private boolean[] settledVertices; // the vertices that are already settled
    private int verticesNumber; // the total number of vertices
    private double[][] graph; // the 2d array to show the adjacency of all vertices
    private double[] verticeKey; // the current key of the vertice, use for prim algorithm
    private int[] sources; // the source of all vertices
    private double[][] MST;
 
    public PrimsMST(int numberofvertices)
    {
    	// initialize all the fields
        this.verticesNumber = numberofvertices;
        unsettledVertices = new boolean[numberofvertices];
        settledVertices = new boolean[numberofvertices];
        graph = new double[numberofvertices][numberofvertices];
        verticeKey = new double[numberofvertices];
        sources = new int[numberofvertices];
        MST = new double[verticesNumber][verticesNumber];
    }
 
    /**
     * @param unsettled input unsettled vertices
     * @return return the number of vertices that are still unsettled
     */
    public int unsettledVerticesNumber(boolean[] unsettled)
    {
        int count = 0;
        for (int index = 0; index < unsettled.length; index++)
        {
            if (unsettled[index])
            {
                count++;
            }
        }
        return count;
    }
 
    
    /**
     * @param unsettled input unsettled vertices
     * @return return vertex that has the minimum key
     */
    private int getMinKey(boolean[] unsettled)
    {
        double min = Integer.MAX_VALUE;
        int node = 0;
        for (int vertex = 0; vertex < verticesNumber; vertex++)
        {
            if (unsettledVertices[vertex] == true && verticeKey[vertex] < min)
            {
                node = vertex;
                min = verticeKey[vertex];
            }
        }
        return node;
    }
    
    /**
     * evaluate all the adjacency vertex
     * @param source the source vertex that needs to evaluate
     */
    public void evaluate(int source)
    {

    	for (int destination = 0; destination < verticesNumber; destination++)
    	{
    		if (settledVertices[destination] == false
    				&& graph[source][destination] != INFINITE)
    		{
    			if (graph[source][destination] < verticeKey[destination])
    			{
    				// if the meaningful vertex is not settled and its key bigger than the edge, update the key
    				verticeKey[destination] = graph[source][destination];
    				sources[destination] = source;
    			}
    			unsettledVertices[destination] = true;

    		}
    	}
    }

    /**
     * Prim's algorithm to solve MST
     * @param graph the input graph that is used to solve MST
     */
    public void primsAlgorithm(double graph[][])
    {
        int currentVertex;
        for (int source = 0; source < verticesNumber; source++)
        {
            for (int destination = 0; destination < verticesNumber; destination++)
            {
            	if (graph[source][destination] == 0){
            		this.graph[source][destination] = INFINITE;
            	}
            	else{
            		this.graph[source][destination] = graph[source][destination];
            	}
            }
        } // copy all the data to the instance variable
 

        
        for (int index = 0; index < verticesNumber; index++)
        {
            verticeKey[index] = INFINITE;
        } // set the key of all vertices to be infinite
        
        verticeKey[0] = 0;
        unsettledVertices[0] = true;
        sources[0] = 1; // initialize source and prepare for the main algorithm
 
        while (unsettledVerticesNumber(unsettledVertices) != 0)
        {
            currentVertex = getMinKey(unsettledVertices);
            unsettledVertices[currentVertex] = false;
            settledVertices[currentVertex] = true; // update settled and unsettled
            evaluate(currentVertex);
        }
        
        
    	for (int i = 1; i < verticesNumber; i++){
    		MST[i][sources[i]] = graph[i][sources[i]];
    		MST[sources[i]][i] = graph[sources[i]][i];
    	} // convert the result to MST in 2D array form
    } 
 
    /**
	 * print the MST as a 2d form
	 */
    public void printMST()
    {
    	System.out.println("The spanning tree using Prim's algorithm ");
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
    
    public void showMST()
    {
        System.out.println("SOURCE  - DESTINATION   :      WEIGHT");
        for (int vertex = 1; vertex < verticesNumber; vertex++)
        {
            System.out.println("  " + sources[vertex] + "\t-\t" + vertex +"\t:\t"+ graph[sources[vertex]][vertex]);
        }
    }
    
    public double[][] getMST(){
    	return MST;
    }
}
