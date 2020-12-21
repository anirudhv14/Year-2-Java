package edu.amrita.cb.cen.aie.ds2.cdt.graph;

import edu.amrita.cb.cen.aie.ds2.adt.graph.Graph;

public class MatrixGraph implements Graph 
{
	int[][] graph;
	int V;
		
	int parent[], key[];
	boolean finished[];
	
	double[][] graph1;
		
	double key1[];
	
	public MatrixGraph(int[][] g)
	{
		graph = g;
		V = g.length;
		
		parent = new int[V];
		key = new int[V];
		finished = new boolean[V];
		initialisation_INT();
	}
	
	public MatrixGraph(double[][] g)
	{
		graph1 = g;
		V = g.length;
		
		parent = new int[V];
		key1 = new double[V];
		finished = new boolean[V];
		initialisation_DOUB();
	}
	
	public void initialisation_INT() {
		int root = 0;
		for(int i=0; i<V; i++) {
			parent[i] = 0;
			key[i] = Integer.MAX_VALUE;
			finished[i] = false;
		}
		parent[root] = -1;
		key[root] = 0;
	}
	
	public void initialisation_DOUB() {
		int root = 0;
		for(int i=0; i<V; i++) {
			parent[i] = 0;
			key1[i] = Integer.MAX_VALUE;
			finished[i] = false;
		}
		parent[root] = -1;
		key1[root] = 0.0;
	}

	public void print() {
		System.out.println("Parent \t \t Vertex \t Key \t \t Finished");
		for(int i=0; i<V; i++)
			System.out.println(parent[i] + "\t \t " + i + "\t \t" + key[i] + "\t \t" + finished[i]);
		
		System.out.println();
		System.out.println("Parent vertex pairs:");
		for(int i=0; i<V; i++)
			System.out.println(parent[i] + "-->" + i);
	}
	
	public void print_DOUB() {
		System.out.println("Parent \t \t Vertex \t Key \t \t Finished");
		for(int i=0; i<V; i++)
			System.out.println(parent[i] + "\t \t " + i + "\t \t" + key1[i] + "\t \t" + finished[i]);
		
		System.out.println();
		System.out.println("Parent vertex pairs:");
		for(int i=0; i<V; i++)
			System.out.println(parent[i] + "-->" + i);
	}

	// Signle source shortest path
	@Override
	public int[] sssp(int src) {
		return dijkstras(src);
	}
	
	@Override
	public int[] mst() {
		for(int i=0; i<V; i++) {
			int minKey = minimize(key, finished);
			finished[minKey] = true;
			
			for(int j=0; j<V; j++) {
				if(graph[minKey][j] != 0 && !finished[j])
					if(key[j] > graph[minKey][j]) {
						key[j] = graph[minKey][j];
						parent[j] = minKey;
					}
			}
		}
		return parent;
	}
	
	public int[] mst_DOUB() {
		for(int i=0; i<V; i++) {
			int minKey = minimize_DOUB(key1, finished);
			finished[minKey] = true;
			
			for(int j=0; j<V; j++) {
				if(graph1[minKey][j] != 0 && !finished[j])
					if(key1[j] > graph1[minKey][j]) {
						key1[j] = graph1[minKey][j];
						parent[j] = minKey;
					}
			}
		}
		return parent;
	}
	
	public int minimize(int[] cost, boolean[] taken) 
	{
		int minIndex = -1, minValue = Integer.MAX_VALUE;
		
		for(int i=0; i<V; i++)
		{
			if(!taken[i] && cost[i] < minValue)
			{
				minValue = cost[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	public int minimize_DOUB(double[] cost, boolean[] taken) 
	{
		int minIndex = -1;
		double minValue = Integer.MAX_VALUE;
		
		for(int i=0; i<V; i++)
		{
			if(!taken[i] && cost[i] < minValue)
			{
				minValue = cost[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

	public int[] dijkstras(int src)
	{
		int dist[] = new int[V];
		boolean taken[] = new boolean[V];
		
		for(int i=0; i<V; i++)
		{
			dist[i] = Integer.MAX_VALUE;
			taken[i] = false;
		}
		
		dist[src] = 0;
			
		for(int i=0; i<V-1; i++)
		{
			int u = minimize(dist, taken);
			taken[u] = true;
			
			for(int v=0; v<V; v++)
			{
				if(graph[u][v] != 0 && !taken[v])
					if(dist[v] > graph[u][v] + dist[u])
						dist[v] = graph[u][v] + dist[u];
			}
		}
		return dist;
	}
	
	public static void main(String args[])
	{
		int adjMatrix[][] = {
				{0,  5,  0,  0,  0,  4,  0},
				{5,  0, 10,  0,  0,  0,  0},
				{0, 10,  0,  7, 12,  0,  0},
				{0,  0,  7,  0, 21,  0,  9},
				{0,  0, 12, 21,  0,  1,  0},
				{4,  0,  0,  0,  1,  0, 11},
				{0,  0,  0,  9,  0, 11,  0},
		};
		
		Graph g = new MatrixGraph(adjMatrix);
		int spDist[] = g.sssp(6);
		
		for(int i=0; i<spDist.length; i++)
			System.out.println(i+"->"+spDist[i]);
		
		System.out.println();
		System.out.println("Prim's MST Algorithm:");
		
		g.mst();
		g.print();
		
	}
}