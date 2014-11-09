package com.wangzhu;

import java.io.Serializable;

/**
 * ���л�����
 * 
 * @author wangzhu
 * @date 2014-11-2����12:21:15
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
	 * �����transient����һ��ʵ��������������洢ʱ������ֵ����Ҫά�֡�
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