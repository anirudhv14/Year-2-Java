package edu.amrita.cb.cen.aie.ds2.project;
import edu.amrita.cb.cen.aie.ds2.adt.cons.*;
import edu.amrita.cb.cen.aie.ds2.cdt.cons.Cons;
import edu.amrita.cb.cen.aie.ds2.cdt.graph.MatrixGraph;
import edu.amrita.cb.cen.aie.ds2.adt.graph.*;
import edu.amrita.cb.cen.aie.ds2.project.*;
import java.util.*;

/*
 * Project Topic:
 * Implement a method in MatrixGraph to generate an entire Conslist of Random ‘Point’
 * objects (with x,y type values, Conslist<Point> ) called randPointCons. Implement another
 * method called consToGraph that will return a MatrixGraph object by computing the
 * distances between the Points held by the Conslist. Implement a randomTSP function in
 * MatrixGraph that generate random Hamiltonians to search for the minimum cost cycle,
 * to solve a TSP over it.
 */

@SuppressWarnings("unused")
public class MatrixGraph_P {
	double x,y;
	
	public MatrixGraph_P(double X,double Y) {
		x = X;
		y = Y;
	}
	
	public static final Conslist<MatrixGraph_P> Nil = new Conslist<MatrixGraph_P>(){

		@Override
		public MatrixGraph_P head() {	throw new NullPointerException("Nil has no head.");	}

		@Override
		public Conslist<MatrixGraph_P> tail() {	return this;	}

		@Override
		public int length() {
			return 0;
		}

		@Override
		public MatrixGraph_P reduce(ReduceClosure<MatrixGraph_P> f) {
			return null;
		}
	};
		
	public static double calcDist(MatrixGraph_P Point1, MatrixGraph_P Point2) {
		return Math.sqrt(Math.pow((Point1.x-Point2.x),2) + Math.pow((Point1.y-Point2.y),2));
	}
	
	public static double[][] consToGraph(Conslist<MatrixGraph_P> randPointCons) {
		Conslist<MatrixGraph_P> rP_r = randPointCons;
		double adjMat[][] = new double[randPointCons.length()][randPointCons.length()];
		int i=0;
		while(i < randPointCons.length()) {
			MatrixGraph_P p1 = rP_r.head();
			Conslist<MatrixGraph_P> rP_c = randPointCons;
			int j=0;
			while(j < randPointCons.length()) {
				MatrixGraph_P p2 = rP_c.head();
				adjMat[i][j] = calcDist(p1,p2);
				rP_c = rP_c.tail();
				j++;
			}
			rP_r = rP_r.tail();
			i++;
		}
		return adjMat;
	}
		
	public static int[] randomTSP(int[][] possOrders, double[][] adjMat) {
		double bestEver = Integer.MAX_VALUE; 
		int bestOrder[] = new int[possOrders[0].length];

		for(int i=0; i<possOrders.length; i++) {
			double dist = 0;
			for(int j=0; j<possOrders[i].length - 1; j++) {
				dist += adjMat[possOrders[i][j]][possOrders[i][j+1]];
			}
			if(dist < bestEver) {
				bestEver = dist;
				bestOrder = possOrders[i];
			}
		}
		System.out.println("Distance="+bestEver);
		return bestOrder;		
	}
	
	private static int fact(int a) {
		if(a == 1)
			return 1;
		else
			return a * fact(--a);
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of points:");
		int numOfPoints = sc.nextInt();
		sc.close();
		long start = System.currentTimeMillis();
		MatrixGraph_P Point[] = new MatrixGraph_P[numOfPoints];
		int min = 0;
		int max = 10;
		for(int i=0; i<Point.length; i++) {
			double X = Math.random()*(max-min+1)+min;
			double Y = Math.random()*(max-min+1)+min;
			Point[i] = new MatrixGraph_P(X,Y);
		}
		Conslist<MatrixGraph_P> randPointCons = Cons.list(Point);
		
		double adjMat[][] = consToGraph(randPointCons);
				
		int arr[] = new int[randPointCons.length()];
		for(int i=0; i<randPointCons.length(); i++) {
			arr[i] = i;
		}
		
		int allPoss[][] = new int[fact(arr.length)][arr.length];
		int iter = 0;
		AllPermutation perm = new AllPermutation(arr); 
		allPoss[iter++] = perm.GetFirst_R();
		while (perm.HasNext()) 
		{ 
			allPoss[iter++] = perm.GetNext_R(); 
		}
		int best[] = randomTSP(allPoss, adjMat);
		
		Conslist<MatrixGraph_P> rP_r = randPointCons;
		int i=0;
		while(i < randPointCons.length()) {
			MatrixGraph_P p1 = rP_r.head();
			Conslist<MatrixGraph_P> rP_c = randPointCons;
			int j=0;
			while(j < randPointCons.length()) {
				MatrixGraph_P p2 = rP_c.head();
				if(j == best[i]){
					System.out.print("["+p2.x+","+p2.y+"]");
					if(i != best.length-1)
						System.out.print(",");
				}
				rP_c = rP_c.tail();
				j++;
			}
			rP_r = rP_r.tail();
			i++;
		}
		long end = System.currentTimeMillis();
		long elapsedTime = end - start;
		System.out.println();
		System.out.println("Time taken = "+elapsedTime+" milliseconds");
		
	}
}