package edu.amrita.cb.cen.aie.ds2.adt.cons;

public abstract class ConsSet<T> {
	public abstract boolean has(T key);
	public abstract ConsSet<T> add(T key);
}