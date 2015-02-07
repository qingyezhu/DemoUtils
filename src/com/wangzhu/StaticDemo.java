package com.wangzhu;

public class StaticDemo {
	static {
		System.out.println("test 1");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	static {
		System.out.println("test 2");
	}
}
