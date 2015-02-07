package com.wangzhu.map;

import java.util.HashMap;

/**
 * hashCode��������Ҫ������Ϊ����ϻ���ɢ�еļ���һ���������У�<br/>
 * ������ɢ�м��ϰ���HashSet��HashMap�Լ�HashTable��<br/>
 * �ܷ����ֱ�Ӹ���hashCodeֵ�ж����������Ƿ�����أ�<br/>
 * �𰸣��϶��ǲ����Եģ���Ϊ��ͬ�Ķ�����ܻ�������ͬ��hashCodeֵ��<br/>
 * ��Ȼ���ܸ���hashCodeֵ�ж����������Ƿ���ȣ����ǿ���ֱ�Ӹ���hashCodeֵ�ж��������󲻵ȣ�<br/>
 * ������������hashCodeֵ���ȣ���ض���������ͬ�Ķ������Ҫ�ж����������Ƿ�������ȣ������ͨ��equals������<br/>
 * Ҳ����˵�������������������equals�����õ��Ľ��Ϊtrue�������������hashCodeֵ�ض���ȡ�<br/>
 * ���equals�����õ��Ľ��Ϊ�������������hashCodeֵ��һ����ͬ�� <br/>
 * ������������hashCodeֵ���ȣ���equals�����õ��Ľ����Ϊ��<br/>
 * ������������hashCodeֵ��ȣ���equals�����õ��Ľ��δ֪��<br/>
 * 
 * @author wangzhu
 * @date 2015-2-1����11:22:42
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
		 * ��Studentֻ��дequals������û��û����дhashCode����ʱ�������Ϊnull
		 */

		stu.setAge(22);
		System.out.println(map.get(stu));// null
		/**
		 * �ڳ���ִ���ڼ䣬ֻҪequals�����ıȽϲ����õ�����Ϣû�б��޸ģ�<br/>
		 * ��ô����ͬһ��������ö�Σ� hashCode��������ʼ����һ�ط���ͬһ��������<br/>
		 * 
		 * ���hashCode()ʱ���ո�����ؾ��ǣ����ۺ�ʱ����ͬһ���������hashCode()��Ӧ�ò���ͬ����ֵ��<br/>
		 * ����ڽ�һ��������put()��ӽ�HashMapʱ����һ��hashCodeֵ������get()ȡ��ʱȴ������һ��hashCodeֵ��<br/>
		 * ��ô���޷���ȡ�ö����ˡ�����������hashCode���������ڶ������ױ�����ݣ��û���Ҫ�����ˣ�<br/>
		 * ��Ϊ�����ݱ仯ʱ��hashCode() �����ͻ�����һ����ͬ��ɢ���롣<br/>
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
	 * ��дʱ����Ҫ��equals������hashCode����ʼ�����߼��ϱ���һ���ԡ�
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