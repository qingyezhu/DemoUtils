package com.wangzhu.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeepCloneDemo {

    public static void main(String[] args) {
	Point p1 = new Point(1, 2);
	System.out.println("before: " + p1);
	Point p2 = (Point) p1.clone();
	DeepCloneDemo.changPoint(p2);
	System.out.println("after: " + p1 + "===" + p2);

	String[] strArr = { "abc", "11", "efg", "22", "33" };
	System.out.println(Arrays.asList(strArr));
	String[] tStrArr = Arrays.copyOf(strArr, strArr.length);
	System.out.println(Arrays.asList(tStrArr));
	tStrArr[1] = "222222222222222";
	System.out.println(Arrays.asList(strArr));
	System.out.println(Arrays.asList(tStrArr));

	List<Point> list = new ArrayList<Point>(3);
	list.add(p1);
	list.add(p2);
	List<Point> list1 = new ArrayList<Point>(3);
	list1.add(p2);
	list1.add(p2);
	list1.add(p1);
	list1.add(p2);
	// 目标List中的元素个数必须大于源List中的元素个数
	Collections.copy(list1, list);
	System.out.println(list1);
	System.out.println(list);
    }

    private static void changPoint(Point p1) {
	p1.x = 3;
	p1.y = 4;
	System.out.println("change: " + p1);
    }

}

class Point implements Cloneable {
    int x, y;

    public Point(int x, int y) {
	this.x = x;
	this.y = y;
    }

    @Override
    public String toString() {
	return "{x=" + x + ", y=" + y + "}";
    }

    @Override
    protected Object clone() {
	Point point = null;
	try {
	    point = (Point) super.clone();
	} catch (CloneNotSupportedException e) {
	    e.printStackTrace();
	}
	return point;
    }
}