package com.wangzhu.arrays;

import java.util.Arrays;

public class DeepCloneDemo {

	public static void main(String[] args) {
		Point p1 = new Point(1, 2);
		System.out.println("before: " + p1);
		DeepCloneDemo.changPoint((Point) p1.clone());
		System.out.println("after: " + p1);

		String[] strArr = { "abc", "11", "efg", "22", "33" };
		System.out.println(Arrays.asList(strArr));
		String[] tStrArr = Arrays.copyOf(strArr, strArr.length);
		System.out.println(Arrays.asList(tStrArr));
		tStrArr[1] = "222222222222222";
		System.out.println(Arrays.asList(strArr));
		System.out.println(Arrays.asList(tStrArr));

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