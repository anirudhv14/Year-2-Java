package edu.amrita.cb.cen.aie.ds2.cdt.cons;

import edu.amrita.cb.cen.aie.ds2.adt.cons.*;

public class UsingCons {
	
	/**
	 * This is another implementation of Conslist used to check the speed of of iterative and taill recursive versions of various functions.
	 * @author - anirudhvadakedath
	 */
	
	@SuppressWarnings("unused")
	public static void main(String args[]) {
		Cons<Double> data = (Cons<Double>)Cons.list(0.794352877,0.837077909,0.917074847,0.541410983,0.263455635,0.224583847,0.321448514,0.134837577,0.050245486,0.64834954,0.685198118,0.526425335,0.09761753,0.029780358,0.228019787,0.454222654,0.509493037,0.080480061,0.511699839,0.249708907,0.914450773,0.118724969,0.424110375,0.956629128,0.438476108,0.304486728,0.492446991,0.451490524,0.589166522,0.254297352,0.086906394,0.095736755,0.34800893,0.576804352,0.583528137,0.427873522,0.092204537,0.685329519,0.424897529,0.756502951,0.575900992,0.447873874,0.887223295,0.74845992,0.181353969);		
		
		
		ReduceClosure<Double> computeSum = new ReduceClosure<Double>() {
			public Double lambda(Double acc, Double e) {	return acc+e;	}
		};
		
		double mean = data.reduce(computeSum)/data.length();
		
		MapClosure<Double,Double> deviationSqMap = new MapClosure<Double,Double>(){
			public Double lambda(Double e) {	return (e-mean)*(e-mean);	}
		};

		
		
		int i = 10;
		double sum = 0.0;
		double speedUp;
		while(i>0) {
			long tr = System.nanoTime();
			double varience_r = data.map_r(deviationSqMap).reduce(computeSum)/data.length();
			tr = System.nanoTime()-tr;
			
			long ti = System.nanoTime();
			double varience_i = data.map_i(deviationSqMap).reduce(computeSum)/data.length();
			ti = System.nanoTime()-ti;
			
			speedUp = ((double)tr/ti)*100.0;
			sum += speedUp;
			System.out.printf("\nTime for iMap = %d and for rMap = %d speedup = %.3f",ti,tr,speedUp);
			i--;
		}
		System.out.printf("\nAverage speedup = %.4f",sum/10.0);
		
		
//		Conslist<Double> data1 = Cons.list(0.794352877,0.837077909,0.917074847,0.541410983,0.263455635,0.224583847,0.321448514,0.134837577,0.050245486,0.64834954,0.685198118,0.526425335,0.09761753,0.029780358,0.228019787,0.454222654,0.509493037,0.080480061,0.511699839,0.249708907,0.914450773,0.118724969,0.424110375,0.956629128,0.438476108,0.304486728,0.492446991,0.451490524,0.589166522,0.254297352,0.086906394,0.095736755,0.34800893,0.576804352,0.583528137,0.427873522,0.092204537,0.685329519,0.424897529,0.756502951,0.575900992,0.447873874,0.887223295,0.74845992,0.181353969);
//		ReduceClosure<Double> computeSum1 = new ReduceClosure<Double>() {
//			public Double lambda(Double acc, Double e) {	return acc+e;	}
//		};
//		
//		double mean1 = data.reduce(computeSum1)/data1.length();
//		
//		MapClosure<Double,Double> deviationSqMap1 = new MapClosure<Double,Double>(){
//			public Double lambda(Double e) {	return (e-mean1)*(e-mean1);	}
//		};
//		
//		@SuppressWarnings("unchecked")
//		Conslist<Double> wer = data1.map(deviationSqMap1);
//		System.out.println();
//		wer.print();
		System.out.println("\n------------------------------------------------------------------------------------------------\n");
		
		Cons<Integer> data1 = (Cons<Integer>)Cons.list(98,30,93,81,61,100,79,17,83,9,74,39,75,52,70,42,30,50,47,7,29,50,34,2,15,75,50,56,61,69,30,49,15,100,82,21,94,18,87,24,76,86,69,23,43,40,100,85,60,49,40,71,23,75,59,31,21);
		
		int j = 10;
		double sum1 = 0.0;
		double speedUp1;
		
		FilterPredicate<Integer> even_test = new FilterPredicate<Integer>() {
			public boolean lambda(Integer h) {	return (h%2 == 0); }
		};
		
		while(j>0) {
			long tr = System.nanoTime();
			Conslist<Integer> filtered_r = data1.filter_r(even_test);
			tr = System.nanoTime()-tr;
			
			long ti = System.nanoTime();
			Conslist<Integer> filtered_i = data1.filter_i(even_test);
			ti = System.nanoTime()-ti;
			
			speedUp1 = ((double)tr/ti)*100.0;
			sum1 += speedUp1;
			System.out.printf("\nTime for iFilter = %d and for rFilter = %d speedup = %.3f",ti,tr,speedUp1);
			j--;
		}
		System.out.printf("\nAverage speedup = %.4f",sum1/10.0);
		System.out.println();
		
		
System.out.println("\n------------------------------------------------------------------------------------------------\n");
		
		Cons<Integer> dataList1 = (Cons<Integer>)Cons.list(98,30,93,81,61,100,79,0,42,50,47,7,3);
		Cons<Integer> dataList2 = (Cons<Integer>)Cons.list(17,83,9,74,39,75,52,70,42,79,98,30,93,81,61,1);
		
		int ij = 10;
		double sum2 = 0.0;
		double speedUp2;
		
		while(ij>0) {
			long tr = System.nanoTime();
			Conslist<Integer> conCating1 = dataList1.cat_r(dataList2);
			tr = System.nanoTime()-tr;
			
			long ti = System.nanoTime();
			Conslist<Integer> conCating2 = dataList1.cat_i(dataList2);
			ti = System.nanoTime()-ti;
			
			speedUp2 = ((double)tr/ti)*100.0;
			sum2 += speedUp2;
			System.out.printf("\nTime for iCat = %d and for rCat = %d speedup = %.3f",ti,tr,speedUp2);
			ij--;
		}
		System.out.printf("\nAverage speedup = %.4f",sum2/10.0);
		System.out.println();
		
	}
}