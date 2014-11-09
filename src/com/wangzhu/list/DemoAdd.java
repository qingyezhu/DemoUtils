package com.wangzhu.list;

import java.util.ArrayList;
import java.util.List;

/**
 * 请为集合指定初始容量
 * 
 * @author wangzhu
 * @date 2014-11-9下午3:33:54
 * 
 */
public class DemoAdd {

	private static final int LEN = 100000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoAdd.add1();
		DemoAdd.add2();

	}

	private static void add1() {
		long start = System.currentTimeMillis();
		List<Student> list1 = new ArrayList<Student>();
		Student stu = null;
		for (int i = 0; i < DemoAdd.LEN; i++) {
			stu = new Student(i, "" + i);
			list1.add(stu);
			stu = null;
		}
		long end = System.currentTimeMillis();
		System.out.println("add1 time: " + (end - start));

	}

	private static void add2() {
		long start = System.currentTimeMillis();
		List<Student> list1 = new ArrayList<Student>(DemoAdd.LEN);
		Student stu = null;
		for (int i = 0; i < DemoAdd.LEN; i++) {
			stu = new Student(i, "" + i);
			list1.add(stu);
			stu = null;
		}
		long end = System.currentTimeMillis();
		System.out.println("add2 time: " + (end - start));

	}
}

class Student {
	private int age;
	private String name;

	public Student(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
