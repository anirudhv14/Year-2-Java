package edu.amrita.cb.cen.aie.ds2.cdt.cons;

import edu.amrita.cb.cen.aie.ds2.adt.cons.*;

@SuppressWarnings("unchecked")
public class Cons<T> extends Conslist<T>
{
	/**
	 * This is an implementation of Conslist which is immutable and has multiple functions including reverse, reduce, map, etc.
	 * @author - anirudhvadakedath
	 */
	
	T h;
	Conslist<T> t;
	static int conLength;
	
	public static final Conslist<Object> Nil = new Conslist<Object>(){

		@Override
		public Object head() {	throw new NullPointerException("Nil has no head.");	}

		@Override
		public Conslist<Object> tail() {	return this;	}

		@Override
		public int length() {
			return 0;
		}

		@Override
		public Conslist<Object> reverse() {
			return null;
		}
		
		public Object reduce(ReduceClosure<Object> f) {
			throw new NullPointerException("Nil has no head.");
		}
	};
	
	public Cons(T h, Conslist<T> t)
	{
		this.h = h;
		this.t = t;
	}
	
	public static <T> Conslist<T> list(T...lists)
	{
//		return new Cons(lists[0], make(1,lists));
		return make_tr((Conslist<T>)Nil, lists.length-1, lists);
	}
	
	@SuppressWarnings({ "unused" })
	@Deprecated
	private static <T> Conslist<T> make(int index, T...lists)
	{
		if(index >= lists.length)
			return (Conslist<T>)Nil;
		else
			return new Cons<T>(lists[index], make(++index, lists));
	}
	
	// tail recursive make. 
	@SafeVarargs
	private static <T> Conslist<T> make_tr(Conslist<T> acc, int index, T...lists)
	{
		if(index < 0)
			return acc;
		else
		{
			conLength++;
			return make_tr(new Cons<T>(lists[index], acc), --index, lists);
		}
	}
	
	@Override
	public T head(){	return h;	}

	@Override
	public Conslist<T> tail() {	return t;	}
	
	@Override
	public boolean isEmpty() {	return false;	}
	
	@Override
	public void print()
	{
		System.out.print(h+" ");
		// tail recursion
		t.print();
	}
	
	@Override
	public int length() {
		return t.length()+1;
	}
	
	
	public Conslist<T> reverse() {
		return reverse((Conslist<T>)Nil,this);
	}
	
	public static <T> Conslist<T> reverse(Conslist<T> acc,Conslist<T> current) {
		if(current == Nil)	return acc;
		else return reverse(new Cons<T>(current.head(),acc),current.tail());
	}
	
    @Override
    public String toString() {

        return "List(" + toString(this) + ")";
    }

    private String toString(Conslist<T> list) {
        return list.isEmpty() ? "" :
               list.head() + 
               (list.tail().isEmpty() ? "" : 
               ", " + toString(list.tail()));
    }
	
	
	
	@Override
	public <U> Conslist<U> map(MapClosure<T,U> funct) {
		return map_i(funct);
	}
	
	@SuppressWarnings({ "rawtypes" })
	public <U> Conslist<U> map_r(MapClosure<T,U> f) {
		return new Cons(f.lambda(h), t.map(f));
	}
	
	public <U> Conslist<U> map_i(MapClosure<T,U> f) {
		Conslist<U> list = new Cons<U> (
				f.lambda(h),
				(Conslist<U>) Nil
				);
		
		Conslist<T> thisList = this.t;
		Conslist<U> newList = list;
		Conslist<U> newTail = (Conslist<U>) Nil;
		
		while(thisList != Nil) {
			newTail = new Cons<U> (
					f.lambda( ((Cons<T>)thisList).h ),
					(Conslist<U>) Nil
					);
			
			((Cons<U>)newList).t = newTail;
			newList = newTail;
			
			thisList = ((Cons<T>)thisList).t; 
		}
		
		return list;
		
	}
	
	@Override
	public T reduce(ReduceClosure<T> funct) {
		return reduce_tr(this.head(), this.tail(), funct);
	}
	
	private static <T> T reduce_tr(T acc , Conslist<T> current , ReduceClosure<T> f)
	{
		if(current == Nil)
			return acc;
		else
			return reduce_tr(f.lambda(acc, current.head()), current.tail(), f);
	}
	
	
	
	public Conslist<T> filter(FilterPredicate<T> p){
//		return filter_r(p);
		return filter_i(p).reverse();
	}
	
	public Conslist<T> filter_r(FilterPredicate<T> p){
		if(p.lambda(h)) 
			return new Cons<T>(h,t.filter(p));
		else
			return t.filter(p);
	}
	
	public Conslist<T> filter_i(FilterPredicate<T> p){
		Conslist<T> thisList = this;
		Conslist<T> newList = (Conslist<T>) Nil;
		while(thisList != Nil) {
			if(p.lambda(thisList.head())) {
				newList = new Cons<T>(thisList.head(),newList);
			}
			thisList = ((Cons<T>)thisList).t; 
		}
		return newList;
	}
	
	public Conslist<T> cat(Conslist<T> that){
//		return cat_i(that);
		return cat_r(that);
	}
	
	public Conslist<T> cat_i(Conslist<T> that){
		Conslist<T> i = this.reverse();
		Conslist<T> newList = (Conslist<T>)Nil;
		Conslist<T> j = that.reverse();
		while(j != Nil) {
			newList = new Cons<T>(j.head(),newList);
			j = ((Cons<T>)j).t;
		}
		while(i != Nil) {
			newList = new Cons<T>(i.head(),newList);
			i = ((Cons<T>)i).t;
		}
		return newList;
	}
	
	public Conslist<T> cat_r(Conslist<T> that){
		return (this.t == Nil)? new Cons<T>(this.h,that):new Cons<T>(this.h,this.t.cat(that));
	}
	
	public static void main(String args[])
	{
		Conslist<Integer> c = Cons.list(1,2,3,4,5,6,7,8,9,10);
		
		c.print();
		System.out.println();
		
		MapClosure<Integer,Integer> m = new MapClosure<Integer,Integer>() {
			public Integer lambda(Integer e) {	return e*e;	}
		};
		
		int l = c.length();
		System.out.println(l);
		
		Conslist<Integer> rev = (Conslist<Integer>) c.reverse();
		rev.print();
		System.out.println();
		
		Conslist<Integer> cSq = c.map(m);
		cSq.print();
		System.out.println();
		
		Conslist<Integer> crSq = rev.map(m);
		crSq.print();
		System.out.println();
		
		ReduceClosure<Integer> r = new ReduceClosure<Integer>() {
			public Integer lambda(Integer acc, Integer e) {	return acc+e;	}
		};
		System.out.println(cSq.reduce(r));
		
		// Product of even numbers.
		ReduceClosure<Integer> r1 = new ReduceClosure<Integer>() {
			public Integer lambda(Integer acc, Integer e) { 
				if(e % 2 == 0) return acc * e;
				else return (acc % 2 != 0) ? 1 : acc ; 
		                // This is a ternary operator just like 'if(condition) then(?) else(:)'
			}
		};
		Conslist<Integer> c1 = Cons.list(2,4,6,8,9,10);
		System.out.println("Reduced:"+c1.reduce(r1));
		c1.print();
		System.out.println();
		
		double msq = java.lang.Math.sqrt((c.map(m).reduce(r))/ c.length());
		System.out.println("Root Mean Squared = "+msq);
		
		FilterPredicate<Integer> even_test = new FilterPredicate<Integer>() {
			public boolean lambda(Integer h) {	return (h%2 == 0); }
		};
		Conslist<Integer> c_even = c.filter(even_test);
		System.out.print("List of even nos:");
		c_even.print();
		
		
		System.out.println();
		System.out.println("Concatenated list");
		
		Conslist<Integer> conCating1 = Cons.list(1,2,3,4);
		Conslist<Integer> conCating2 = Cons.list(5,4,3,2);
		Conslist<Integer> conCating3 = conCating1.cat(conCating2);  
		conCating3.print();
		
		System.out.println("\nList:");
		System.out.println(conCating3.toString());
	}
}