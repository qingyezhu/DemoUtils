package com.wangzhu.set;

import java.util.HashSet;
import java.util.Set;

/**
 * 当hashcode一样时，才调用equals方法<br/>
 * 若equals返回true，则hashcode相等<br/>
 * 若equals返回false，则hashcode有可能相等<br/>
 * 
 * @author wangzhu
 * @date 2014-11-9下午9:43:58
 * 
 */
public class DemoSet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<Student> set = new HashSet<Student>();
		Student stu = new Student(12, "LiLei");
		set.add(stu);

		stu = new Student(13, "LiLi");
		set.add(stu);
		stu = new Student(12, "LiLei");
		set.add(stu);

		stu = new Student(14, "LiLi");
		set.add(stu);

		System.out.println(set);
	}

}

class Student {
	private int age;
	private String name;

	public Student(int age, String name) {
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

	@Override
	public int hashCode() {
		System.out.println("调用hashCode方法");
		final int prime = 31;
		int result = 1;
		result = (prime * result) + this.age;
		result = (prime * result)
				+ ((this.name == null) ? 0 : this.name.hashCode());
		System.out.println(this.name + " : " + result);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println("调用equals方法");
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Student other = (Student) obj;
		if (this.age != other.age) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "{age=" + this.age + ", name=" + this.name + "}";
	}

}
