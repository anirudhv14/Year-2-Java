package edu.amrita.cb.cen.aie.ds2.adt.cons;

import edu.amrita.cb.cen.aie.ds2.cdt.cons.Cons;
@SuppressWarnings("unchecked")
public abstract class Conslist<T>
{
	abstract public T head();
	abstract public Conslist<T> tail();
	abstract public T reduce(ReduceClosure<T> f);
	
	public boolean isEmpty(){	return true;	}
	public void print() {	}
	public int length()	{	return 0;	}
	public String toString() {	return "";	}
	
	public <U> Conslist<U> map(MapClosure<T,U> f){	return (Conslist<U>)Cons.Nil;	}
	public Conslist<T> reverse(){	return (Conslist<T>) Cons.Nil;	}
	public Conslist<T> filter(FilterPredicate<T> f) {	return (Conslist<T>) Cons.Nil;	}
	public Conslist<T> cat(Conslist<T> that){	return (Conslist<T>)Cons.Nil;	}
}