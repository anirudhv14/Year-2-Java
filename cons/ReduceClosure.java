package edu.amrita.cb.cen.aie.ds2.adt.cons;

public interface ReduceClosure<T>
{
	public T lambda(T acc, T e);
}
