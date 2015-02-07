package com.wangzhu.map;

import java.util.HashMap;

/**
 * hashCode方法的主要作用是为了配合基于散列的集合一起正常运行，<br/>
 * 这样的散列集合包括HashSet、HashMap以及HashTable。<br/>
 * 能否可以直接根据hashCode值判断两个对象是否相等呢？<br/>
 * 答案：肯定是不可以的，因为不同的对象可能会生成相同的hashCode值。<br/>
 * 虽然不能根据hashCode值判断两个对象是否相等，但是可以直接根据hashCode值判断两个对象不等，<br/>
 * 如果两个对象的hashCode值不等，则必定是两个不同的对象。如果要判断两个对象是否真正相等，则必须通过equals方法。<br/>
 * 也就是说对于两个对象，如果调用equals方法得到的结果为true，则两个对象的hashCode值必定相等。<br/>
 * 如果equals方法得到的结果为，则两个对象的hashCode值不一定不同； <br/>
 * 如果两个对象的hashCode值不等，则equals方法得到的结果必为；<br/>
 * 如果两个对象的hashCode值相等，则equals方法得到的结果未知。<br/>
 * 
 * @author wangzhu
 * @date 2015-2-1下午11:22:42
 * 
 */
public class DemoMap2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Student stu = new Student(21, "John");
		HashMap<Student, Integer> map = new HashMap<Student, Integer>();
		map.put(stu, 1);
		System.out.println(map.get(stu));// 1
		System.out.println(map.get(new Student(21, "John")));// 1
		/**
		 * 若Student只重写equals方法而没有没有重写hashCode方法时，则输出为null
		 */

		stu.setAge(22);
		System.out.println(map.get(stu));// null
		/**
		 * 在程序执行期间，只要equals方法的比较操作用到的信息没有被修改，<br/>
		 * 那么对这同一个对象调用多次， hashCode方法必须始终如一地返回同一个整数。<br/>
		 * 
		 * 设计hashCode()时最终稿的因素就是：无论何时，对同一个对象调用hashCode()都应该产生同样的值。<br/>
		 * 如果在将一个对象用put()添加进HashMap时产生一个hashCode值，而用get()取出时却产生另一个hashCode值，<br/>
		 * 那么就无法获取该对象了。所以如果你的hashCode方法依赖于对象中易变的数据，用户就要当心了，<br/>
		 * 因为此数据变化时，hashCode() 方法就会生成一个不同的散列码。<br/>
		 */
		map.put(stu, 2);
		stu = null;
		System.out.println(map);
	}

}

class Student {
	int age;
	String name;

	public Student(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + age;
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * 重写时，需要让equals方法与hashCode方法始终在逻辑上保持一致性。
	 */
	@Override
	public boolean equals(Object obj) {
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
		if (age != other.age) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "{age=" + age + ", name=" + name + "}";
	}

}