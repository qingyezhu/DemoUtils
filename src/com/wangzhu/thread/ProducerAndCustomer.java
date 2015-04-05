package com.wangzhu.thread;

import org.apache.log4j.Logger;

public class ProducerAndCustomer {
    private static final NumberFactory numberFactory = new NumberFactory();

    /**
     * @param args
     */
    public static void main(String[] args) {
	new Thread(new Runnable() {

	    @Override
	    public void run() {
		while (true) {
		    numberFactory.create();
		}
	    }
	}).start();
	new Thread(new Runnable() {

	    @Override
	    public void run() {
		while (true) {
		    numberFactory.consume();
		}
	    }
	}).start();
    }
}

class NumberFactory {
    private static final Logger logger = Logger.getLogger(NumberFactory.class);
    private int i;
    private Object lock = new Object();
    private boolean created = false;

    public void create() {
	synchronized (lock) {
	    if (!created) {
		i++;
		logger.info("create: " + i);
		lock.notify();
		created = true;
	    } else {
		try {
		    lock.wait();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    public void consume() {
	synchronized (lock) {
	    if (created) {
		logger.info("consume: " + i);
		created = false;
		lock.notify();
	    } else {
		try {
		    lock.wait();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
}
