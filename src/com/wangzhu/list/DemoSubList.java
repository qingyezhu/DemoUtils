package com.wangzhu.list;

import java.util.ArrayList;
import java.util.List;

public class DemoSubList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(2);
		list1.add(1);

		List<Integer> list2 = new ArrayList<Integer>(list1);
		List<Integer> list3 = list1.subList(0, list1.size());
		list3.add(4);

		System.out.println("list1==list2: " + (list1.equals(list2)));
		System.out.println("list1==list3: " + (list1.equals(list3)));
		System.out.println("list1.size(): " + list1.size());
		System.out.println("list3.size(): " + list3.size());

		list1.add(3);

		// 操作通过subList获得的子序列会影响原来的序列，但操作原来的序列同样会影响其子序列并抛出异常
		System.out.println("list1.size(): " + list1.size());
		// System.out.println("list3.size(): " + list3.size());//
		// java.util.ConcurrentModificationException

		list1.subList(0, 1).clear();
		System.out.println(list1);
	}

}
