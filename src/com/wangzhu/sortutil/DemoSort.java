package com.wangzhu.sortutil;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class DemoSort {
	public static void main(String[] args) {
		Integer[] data = new Integer[] { 9, 0, -3, 1, 11, -2, 3 };
		System.out.println(DemoSort.getMax(data));
		System.out.println(DemoSort.getMaxSecond(data));

	}

	private static int getMax(Integer[] data) {
		for (int item : data) {
			System.out.print(item + " ");
		}
		System.out.println();
		Integer[] dataT = data.clone();
		dataT[0] = 11211;
		Arrays.sort(dataT);
		for (int item : dataT) {
			System.out.print(item + " ");
		}
		System.out.println();
		for (int item : data) {
			System.out.print(item + " ");
		}
		System.out.println();
		return dataT[dataT.length - 1];
	}

	private static int getMaxSecond(Integer[] data) {
		List<Integer> list = Arrays.asList(data);
		TreeSet<Integer> ts = new TreeSet<Integer>(list);
		return ts.lower(ts.last());
	}

}
