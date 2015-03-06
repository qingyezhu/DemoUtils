package com.wangzhu.set;

import java.util.Set;

public class DemoSet1 {

    public static void main(String[] args) {
	Point p = new Point(1, 2);

	ColoredPoint cp = new ColoredPoint(1, 2, Color.RED);

	System.out.println(p.equals(cp)); // 打印真 true

	System.out.println(cp.equals(p)); // 打印假 false

	Set<Point> hashSet1 = new java.util.HashSet<Point>();
	hashSet1.add(p);
	System.out.println(hashSet1.contains(cp)); // 打印 false

	Set<Point> hashSet2 = new java.util.HashSet<Point>();
	hashSet2.add(cp);
	System.out.println(hashSet2.contains(p)); // 打印 true
    }
}

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
	this.x = x;
	this.y = y;
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    @Override
    public boolean equals(Object other) {
	boolean result = false;
	if (other instanceof Point) {
	    Point that = (Point) other;
	    result = ((this.getX() == that.getX()) && (this.getY() == that
		    .getY()));
	}
	return result;
    }

    @Override
    public int hashCode() {
	return ((41 * (41 + this.getX())) + this.getY());
    }

}

enum Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET;
}

class ColoredPoint extends Point {
    private final Color color;

    public ColoredPoint(int x, int y, Color color) {
	super(x, y);
	this.color = color;
    }

    @Override
    public boolean equals(Object other) {
	boolean result = false;
	if (other instanceof ColoredPoint) {
	    ColoredPoint that = (ColoredPoint) other;
	    result = (color.equals(that.color) && super.equals(that));
	}
	return result;
    }

}