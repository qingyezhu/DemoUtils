package com.wangzhu;

import java.io.Serializable;

/**
 * 序列化对象
 * 
 * @author wangzhu
 * @date 2014-11-2下午12:21:15
 * 
 */
public class SerialStudent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -463184211929252910L;
	private int id;
	private String name;
	/**
	 * 如果用transient声明一个实例变量，当对象存储时，它的值不需要维持。
	 */
	private transient int age;

	public SerialStudent(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "{id=" + this.id + ", name=" + this.name + ", age=" + this.age
				+ "}";
	}

}