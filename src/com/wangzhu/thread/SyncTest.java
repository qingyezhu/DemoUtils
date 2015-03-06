package com.wangzhu.thread;

public class SyncTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
	Foo f1 = new Foo("1", "printValue");
	f1.start();
	Foo f2 = new Foo("2", "printValue");
	f2.start();
    }

}

class Foo extends Thread {

    private String val;
    private String name;

    public Foo(String val, String name) {
	this.val = val;
	this.name = name;
    }

    public void printVal() {
	synchronized (val) {
	    while (true) {
		System.out.println(name + val);
	    }
	}
    }

    @Override
    public void run() {
	this.printVal();
    }
}