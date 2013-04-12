package com.dolphinnlp.java.algorithms.core;

import java.io.File;
import java.util.Scanner;

/**
 * WeightedQuickUnionUF.java	
 * Weighted Quick Union Algorithm for Union Find.
 * @author Miao Fan
 * @afflication Department of Computer Science and Technology in Tsinghua University
 * @email fanmiao.cslt.thu@gmail.com
*/

public class WeightedQuickUnionUF {
	
	private int[] id;
	private int[] sz;
	private int count;
	
	public WeightedQuickUnionUF(int N)
	{
		count = N;
		id = new int[N];
		sz = new int[N];
		
		for(int i = 0; i < N; i++)
		{
			id[i] = i;
			sz[i] = 1;
		}
	}
	
	public int count()
	{
		return count;
	}
	
	public int find(int p)
	{
		while(p != id[p])
			p = id[p];
		return p;
	}
	
	public boolean connected(int p, int q)
	{
		return find(p) == find(q);
	}
	
	public void union(int p, int q)
	{
		int i = find(p);
		int j = find(q);
		
		if(i == j)
			return;
		
		if(sz[i] < sz[j])
		{
			id[i] = j;
			sz[j] += sz[i];
		}
		else {
			id[j] = i;
			sz[i] += sz[j];
		}
		
		count --;
	}
	
	/**
	 * 
	 * @param args [example: data\\tinyUF.txt]
	 * @throws Exception
	 */
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		if(args.length == 0)
		{
			System.err.println("Parameter args[0] must be the path of input file!");
			return;
		}

		Scanner scan = new Scanner(new File(args[0]));
		
		int N = scan.nextInt();
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
			
		while(scan.hasNext())
		{
			int p = scan.nextInt();
			int q = scan.nextInt();
			
			if(uf.connected(p, q))
			{
				System.out.println(p + " and " + q + " has connected!");
				continue;
			}
			uf.union(p, q);
			System.out.println(p + " union " + q);
		}
		
		System.out.println(uf.count() + " components");
	}

}