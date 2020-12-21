package edu.amrita.cb.cen.aie.ds2.cdt.graph;

import edu.amrita.cb.cen.aie.ds2.adt.graph.Graph;

public class TryingMST implements Graph {

	
	int[][] graph;
	int V;
	
	int parent[], key[];
	boolean finished[];
	
	public TryingMST(int[][] g)
	{
		graph = g;
		V = g.length;
		
		parent = new int[V];
		key = new int[V];
		finished = new boolean[V];
		initialisation();
	}
	
	public void initialisation() {
		int root = 0;
		for(int i=0; i<parent.length; i++) {
			parent[i] = 0;
			key[i] = Integer.MAX_VALUE;
			finished[i] = false;
		}
		parent[root] = -1;
		key[root] = 0;
	}
	
	public void print() {
		System.out.println("Parent \t \t Vertex \t Key \t \t Finished");
		for(int i=0; i<parent.length; i++) {
			System.out.println(parent[i] + "\t \t" + i + "\t \t" + key[i] + "\t \t" + finished[i]);
		}
		
		System.out.println();
		System.out.println("Parent vertex pair:");
		for(int i=0; i<V; i++) {
			System.out.println(parent[i] + " --> " + i);
		}
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
					if(key[j] > graph[minKey][j]){
						key[j] = graph[minKey][j];
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
		
		Graph g = new TryingMST(adjMatrix);
		int src = 6;
		System.out.println("Dijkstras Shortest path for source:"+src);
		int spDist[] = g.sssp(src);
		
		for(int i=0; i<spDist.length; i++)
			System.out.println(i+" --> "+spDist[i]);
		
		
		System.out.println();
		System.out.println("Prim's MST Algorith");

		g.mst();
		g.print();
		
		int adjMatrix2[][] = {
				{ 0, 12,  0,  0, 36,  0,  0},
				{12,  0,  2,  0,  0,  0,  0},
				{ 0,  2,  0, 13,  0, 21,  0},
				{ 0,  0, 13,  0,  0,  1,  0},
				{36,  0,  0,  0,  0, 10,  5},
				{ 0,  0, 21,  1, 10,  0,  7},
				{ 0,  0,  0,  0,  5,  7,  0},
		};

		System.out.println("Graph 2:");
		
		Graph g1 = new TryingMST(adjMatrix2);
		int src1 = 0;
		System.out.println("Dijkstras Shortest path for source:"+src1);
		int spDist1[] = g1.sssp(src1);
		
		for(int i=0; i<spDist1.length; i++)
			System.out.println(i+" --> "+spDist1[i]);
		
		
		System.out.println();
		System.out.println("Prim's MST Algorith");

		g1.mst();
		g1.print();

		System.out.println("Graph 3:");
		
		int adjMatrix3[][] = {
				{0,	5,	0,	0,	0,	4,	0},   
				{5,	0,	10,	0,	11,	0,	3},  
				{0,	10,	0,	7,	5,	0,	0},   
				{0,	0,	7,	0,	6,	0,	9},   
				{0,	11,	5,	6,	0,	2,	15},  
				{4,	0,	0,	0,	2,	0,	9},   
				{0,	3,	0,	9,	15,	9,	0}
		};
		

		Graph g2 = new TryingMST(adjMatrix3);
		int src2 = 0;
		System.out.println("Dijkstras Shortest path for source:"+src2);
		int spDist2[] = g2.sssp(src2);
		
		for(int i=0; i<spDist2.length; i++)
			System.out.println(i+" --> "+spDist2[i]);
		
		
		System.out.println();
		System.out.println("Prim's MST Algorith");

		g2.mst();
		g2.print();
	}

	@Override
	public int[] mst_DOUB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print_DOUB() {
		// TODO Auto-generated method stub
		
	}
}