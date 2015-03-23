package com.wangzhu.thread;

import java.util.Random;

/**
 * ThreadLocal实现线程内的数据共享<br/>
 * 
 * @author wangzhu
 * @date 2015-3-22下午12:56:47
 * 
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> intLocal = new ThreadLocal<Integer>();

    public static void main(String[] args) {
	for (int i = 0; i < 1000; i++) {
	    new Thread(new Runnable() {

		@Override
		public void run() {
		    int data = new Random().nextInt();
		    System.out.println("currentthreadName: "
			    + Thread.currentThread().getName()
			    + " random data: " + data);
		    intLocal.set(data);

		    MyThreadData.getInstance().setName("name==" + data);
		    MyThreadData.getInstance().setAge(data);
		    new A().get();
		    new B().get();
		}
	    }).start();
	}
    }

    static class A {
	public void get() {
	    int data = intLocal.get();
	    System.out.println("A threadName: "
		    + Thread.currentThread().getName() + " data: " + data);
	    MyThreadData threadData = MyThreadData.getInstance();
	    System.out.println("A threadData: " + threadData);
	}
    }

    static class B {
	public void get() {
	    int data = intLocal.get();
	    System.out.println("B threadName: "
		    + Thread.currentThread().getName() + " data: " + data);
	    MyThreadData threadData = MyThreadData.getInstance();
	    System.out.println("B threadData: " + threadData);
	}
    }

}

class MyThreadData {
    private String name;
    private int age;

    private MyThreadData() {

    }

    private static ThreadLocal<MyThreadData> local = new ThreadLocal<MyThreadData>();

    public static synchronized MyThreadData getInstance() {
	MyThreadData instance = local.get();
	if (instance == null) {
	    instance = new MyThreadData();
	    local.set(instance);
	}
	return instance;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getAge() {
	return age;
    }

    public void setAge(int age) {
	this.age = age;
    }

    @Override
    public String toString() {
	return "thread=====" + Thread.currentThread().getName() + "{name="
		+ name + ", age=" + age + "}";
    }

}