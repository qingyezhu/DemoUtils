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

	System.out.println("list1==list2: " + (list1.equals(list2)));// false
	System.out.println("list1==list3: " + (list1.equals(list3)));// true
	System.out.println("list1.size(): " + list1.size());// 3
	System.out.println("list3.size(): " + list3.size());// 3

	list1.add(3);

	// ����ͨ��subList��õ������л�Ӱ��ԭ�������У�������ԭ��������ͬ����Ӱ���������в��׳��쳣
	System.out.println("list1.size(): " + list1.size());// 4
	// System.out.println("list3.size(): " + list3.size());//
	// java.util.ConcurrentModificationException

	list1.subList(0, 1).clear();
	System.out.println(list1);// [1,4,3]
    }

}
