package com.wangzhu.thread;

import java.util.LinkedList;
import java.util.Random;

public class ThreadDemo1 {
	public static void main(String[] args) {
		Table<Food> t = new Table<Food>(10);

		new Chef("Chef1", t).start();
		new Chef("Chef2", t).start();
		new Chef("Chef3", t).start();
		new Chef("Chef4", t).start();

		new Eater("Eater1", t).start();
		new Eater("Eater2", t).start();
		new Eater("Eater3", t).start();
		new Eater("Eater4", t).start();
		new Eater("Eater5", t).start();
		new Eater("Eater6", t).start();
	}

}

/**
 * 事物
 * 
 * @author wangzhu
 * @date 2015-1-20上午11:21:55
 * 
 */
class Food {

}

/**
 * 桌子
 * 
 * @author wangzhu
 * @date 2015-1-20上午11:23:20
 * 
 * @param <E>
 */
class Table<E> extends LinkedList<E> {
	int maxSize;

	public Table(int maxSize) {
		this.maxSize = maxSize;
	}

	public synchronized void putE(E e) {
		while (this.size() >= this.maxSize) {
			try {
				this.wait();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		this.add(e);
		this.notifyAll();
	}

	public synchronized E getE() {
		while (this.size() <= 0) {
			try {
				this.wait();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		E e = this.removeFirst();
		this.notifyAll();
		return e;
	}

}

/**
 * 厨师
 * 
 * @author wangzhu
 * @date 2015-1-20上午11:28:27
 * 
 */
class Chef extends Thread {
	String name;
	Table<Food> t;
	Random r = new Random(12345);

	public Chef(String name, Table<Food> t) {
		this.name = name;
		this.t = t;
	}

	@Override
	public void run() {
		while (true) {
			Food f = this.make();
			System.out.println(name + "put a Food." + f);
			t.putE(f);
		}
	}

	private Food make() {
		try {
			Thread.sleep(200 + r.nextInt(200));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new Food();
	}
}

/**
 * 食客
 * 
 * @author wangzhu
 * @date 2015-1-20上午11:35:17
 * 
 */
class Eater extends Thread {
	String name;
	Table<Food> t;
	Random r = new Random(54321);

	public Eater(String name, Table<Food> t) {
		this.name = name;
		this.t = t;
	}

	@Override
	public void run() {
		while (true) {
			Food f = t.getE();
			System.out.println(name + " get a Food:" + f);
			this.eat(f);
		}

	}

	private void eat(Food f) {
		try {
			Thread.sleep(400 + r.nextInt(200));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
