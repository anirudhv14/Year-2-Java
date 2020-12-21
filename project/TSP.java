package edu.amrita.cb.cen.aie.ds2.project;
import edu.amrita.cb.cen.aie.ds2.adt.cons.*;
import edu.amrita.cb.cen.aie.ds2.cdt.cons.Cons;

public class TSP<N extends Number>{
	N x,y;
	
	public TSP(N X,N Y) {
		x = X;
		y = Y;
	}
	
	public int calcDist(TSP<N> Point) {
//		return Math.sqrt((Point.x)^2 + (Point.y)^2);
		return 0;
	}
	
	public void consToGraph() {
		
	}
	
	public void randomTSP() {
		
	}
	
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public static void main(String args[]) {
		TSP<Double> Point[] = new TSP[5];
		double min = 0;
		double max = 10;
		for(int i=0; i<Point.length; i++) {
			double X = Math.random()*(max-min+1)+min;
			double Y = Math.random()*(max-min+1)+min;
			Point[i] = new TSP<Double>(X,Y);
		}
		Conslist<TSP> randPointCons = Cons.list(Point);
		
//		Conslist<Point> randPointsTSP;
	}
}
