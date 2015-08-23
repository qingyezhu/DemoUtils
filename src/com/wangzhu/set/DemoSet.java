package com.wangzhu.set;

import java.util.HashSet;
import java.util.Set;

/**
 * HashSet底层采用HashMap来保存所有元素<br/>
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
	Student stu = new Student(1, 12, "LiLei");
	set.add(stu);

	stu = new Student(2, 13, "LiLi");
	set.add(stu);
	stu = new Student(1, 12, "LiLei");
	set.add(stu);

	stu = new Student(4, 14, "LiLi");
	set.add(stu);
	System.out.println("change before: " + set);
	stu.setAge(21);
	System.out.println("change1 end: " + set);
	// set.add(stu);
	System.out.println("change2 end: " + set);
	set.remove(stu);
	System.out.println("change3 end: " + set);
    }

}

class Student {
    private int id;
    private int age;
    private String name;

    public Student(int id, int age, String name) {
	super();
	this.id = id;
	this.age = age;
	this.name = name;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
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
    public String toString() {
	return "{id=" + id + ", age=" + age + ", name=" + name + "}";
    }

    @Override
    public int hashCode() {
	System.out.println("调用hashCode方法：" + this);
	final int prime = 31;
	int result = 1;
	result = (prime * result) + age;
	result = (prime * result) + id;
	result = (prime * result) + ((name == null) ? 0 : name.hashCode());
	System.out.println(this + "=========" + result);
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	System.out.println("调用equals方法：" + this);
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
	if (id != other.id) {
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

}
