package edu.amrita.cb.cen.aie.ds2.cdt.cons;

import edu.amrita.cb.cen.aie.ds2.adt.cons.ConsSet;

public class NonEmpty<T extends Comparable<T>> extends ConsSet<T>{
	/**
	 * This is an immutable Set datastructure to store any value of a datatype which is a subclass of Comparable.
	 * @author - anirudhvadakedath
	 */

	T key;
	ConsSet<T> left, right;
	
	static final ConsSet<Comparable<Object>> Empty = new ConsSet<Comparable<Object>>() {

		@Override
		public boolean has(Comparable<Object> k) {
			return false;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public ConsSet add(Comparable<Object> k) {
			return new NonEmpty(this, k, this);
		}
		
		@Override
		public String toString() {
			return "_";
		}
		
	};
	
	@SuppressWarnings("unchecked")
	public ConsSet<T> getEmpty(){
		return (ConsSet<T>) Empty;
	}
	
	public NonEmpty(T k) {
		key = k;
		left = getEmpty();
		right = getEmpty();
	}
	
	
	public NonEmpty(ConsSet<T> l, T k, ConsSet<T> r) {
		key = k;
		left = l;
		right = r;
	}
		
	@Override
	public boolean has(T k) {
		if(k.compareTo(key) < 0)	return left.has(k);
		else if(k.compareTo(key) > 0)	return right.has(k);
		return true;
	}

	@Override
	public ConsSet<T> add(T k) {
		if(k.compareTo(key) < 0)	return new NonEmpty<T>(left.add(k), key, right);
		else if(k.compareTo(key) > 0)	return new NonEmpty<T>(left, key, right.add(k));
		return this;
	}
	
	@Override
	public String toString() {
		return '(' + left.toString() + "," 
						+ key + "," + 
					right.toString() + ')';
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String args[]) {
		ConsSet s = new NonEmpty(5);
//		System.out.println(s);
		ConsSet s1 = s.add(2);
//		System.out.println(s1);
		ConsSet s2 = s1.add(3);
//		System.out.println(s2);
		ConsSet s3 = s2.add(1);
//		System.out.println(s3);
		ConsSet s4 = s3.add(4);
//		System.out.println(s4);
		ConsSet s5 = s4.add(8);
//		System.out.println(s5);
		ConsSet s6 = s5.add(6);
//		System.out.println(s6);
		ConsSet s7 = s6.add(7);
//		System.out.println(s7);
		ConsSet s8 = s7.add(10);
//		System.out.println(s8);
		ConsSet s9 = s8.add(9);
//		System.out.println(s9);
		
//		System.out.println();
//		System.out.println(s);
//		System.out.println(s1);
//		System.out.println(s2);
//		System.out.println(s3);
//		System.out.println(s4);
//		System.out.println(s5);
//		System.out.println(s6);
//		System.out.println(s7);
//		System.out.println(s8);
		System.out.println("Set 1:"+s9);
		System.out.println("Is 1 there in Set 1"+s9.has(1));
		System.out.println();
		ConsSet we = new NonEmpty("D");
//		System.out.println(we);
		ConsSet we1 = we.add("B");
//		System.out.println(we1);
		ConsSet we2 = we1.add("A");
//		System.out.println(we2);
		ConsSet we3 = we2.add("C");
//		System.out.println(we3);
		ConsSet we4 = we3.add("F");
//		System.out.println(we4);
		ConsSet we5 = we4.add("E");
//		System.out.println(we5);
		ConsSet we6 = we5.add("G");
		System.out.println("Set 2:"+we6);
	}	
}