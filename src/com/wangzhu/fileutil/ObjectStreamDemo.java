package com.wangzhu.fileutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectStreamDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ObjectStreamDemo.writerObject("201409271036.txt");
		ObjectStreamDemo.readObject("201409271036.txt");

	}

	public static void readObject(final String filePath) {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(filePath));
			Person per = (Person) ois.readObject();
			Student stu = (Student) ois.readObject();
			Teacher tec1 = (Teacher) ois.readObject();
			Teacher tec2 = (Teacher) ois.readObject();
			Student stu1 = (Student) ois.readObject();
			Child child = (Child) ois.readObject();
			System.out.println("person: " + per);
			System.out.println("student: " + stu);
			System.out.println("teacher: " + tec1 + "=====student: "
					+ tec1.getStu());
			System.out.println("teacher: " + tec2 + "=====student: "
					+ tec2.getStu());
			System.out.println("student: " + stu1);
			System.out.println("child: " + child);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void writerObject(final String filePath) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(filePath));
			Person per = new Person(22, "LiSi");
			Student stu = new Student(22, "Zhangsan", "Desc");
			Teacher tec1 = new Teacher(34, "Wangwu", stu);
			Teacher tec2 = new Teacher(28, "Xusi", stu);
			Child child = new Child(100, "name--0002", "type--0001");
			oos.writeObject(per);
			oos.writeObject(stu);
			oos.writeObject(tec1);
			oos.writeObject(tec2);
			oos.writeObject(stu);
			oos.writeObject(child);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != oos) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private int age;
	private String name;

	public Person() {
		this.age = 10000;
		this.name = "person-name";
	}

	public Person(int age, String name) {
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
	public String toString() {
		return "{age=" + this.age + ", name=" + this.name + ", getAge()="
				+ this.getAge() + ", getName()=" + this.getName()
				+ ", getClass()=" + this.getClass() + ", hashCode()="
				+ this.hashCode() + ", toString()=" + super.toString() + "}";
	}

}

class Student extends Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String desc;

	public Student() {
		super();
	}

	public Student(int age, String name, String desc) {
		super(age, name);
		this.desc = desc;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "{desc=" + this.desc + ", getDesc()=" + this.getDesc()
				+ ", getAge()=" + this.getAge() + ", getName()="
				+ this.getName() + ", getClass()=" + this.getClass()
				+ ", hashCode()=" + this.hashCode() + ", toString()="
				+ super.toString() + "}";
	}

}

class Teacher extends Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Student stu;

	public Teacher() {
		super();
	}

	public Teacher(int age, String name, Student stu) {
		super(age, name);
		this.stu = stu;
	}

	public Student getStu() {
		return this.stu;
	}

	public void setStu(Student stu) {
		this.stu = stu;
	}

	@Override
	public String toString() {
		return "{stu=" + this.stu + ", getStu()=" + this.getStu()
				+ ", getAge()=" + this.getAge() + ", getName()="
				+ this.getName() + ", getClass()=" + this.getClass()
				+ ", hashCode()=" + this.hashCode() + ", toString()="
				+ super.toString() + "}";
	}
}

/**
 * 在继承中，当父类没有被序列化，则当子类序列化时，父类需要提供无参构造函数，否则子类序列化失败
 * 
 * @author wangzhu
 * @date 2014-9-27上午10:57:24
 * 
 */
class Father {
	private int age;
	private String name;

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

	public Father() {
		super();
	}

	public Father(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

}

class Child extends Father implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;

	public Child() {
		super();
	}

	public Child(int age, String name, String type) {
		super(age, name);
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "{type=" + this.type + ", getType()=" + this.getType()
				+ ", getAge()=" + this.getAge() + ", getName()="
				+ this.getName() + ", getClass()=" + this.getClass()
				+ ", hashCode()=" + this.hashCode() + ", toString()="
				+ super.toString() + "}";
	}

}
