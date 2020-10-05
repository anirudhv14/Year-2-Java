package edu.amrita.cb.cen.aie.ds2.adt;

public interface Seq 
{
	public void append(int i);
	public void print();
	public boolean isEmpty();
	public void delete(int pos);
	public void insert(int pos,int val);
	public int search(int val);
}