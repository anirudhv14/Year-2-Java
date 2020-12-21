package edu.amrita.cb.cen.aie.ds2.adt.graph;

public interface Graph {
	public int[] sssp(int src);
	public int[] mst();
	public int[] mst_DOUB();
	
	public void print();
	public void print_DOUB();
}
