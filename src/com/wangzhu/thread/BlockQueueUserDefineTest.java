package com.wangzhu.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

public class BlockQueueUserDefineTest {

    private static final Logger logger = Logger
	    .getLogger(BlockQueueUserDefineTest.class);

    public static void main(String[] args) {
	// testMyBlockingQueue1(new MyBlockingQueue1());
	// testMyBlockingQueue(new MyBlockingQueue2());
	testMyBlockingQueue(new MyBlockingQueue3());

    }

    private static void testMyBlockingQueue1(final MyBlockingQueue queue) {
	new Thread() {
	    @Override
	    public void run() {
		while (queue.size() < 10) {
		    new Producer(queue).create();
		}
	    };
	}.start();
	new Thread() {
	    @Override
	    public void run() {
		while (queue.size() < 9) {
		    new Customer(queue).consume();
		}
	    };
	}.start();
    }

    private static void testMyBlockingQueue(final MyBlockingQueue queue) {
	new Thread() {
	    @Override
	    public void run() {
		while (true) {
		    new Producer(queue).create();
		}
	    };
	}.start();
	new Thread() {
	    @Override
	    public void run() {
		while (true) {
		    new Customer(queue).consume();
		}
	    };
	}.start();
    }

    static class Producer {
	private MyBlockingQueue queue;

	public Producer(MyBlockingQueue queue) {
	    this.queue = queue;
	}

	public void create() {
	    try {
		Thread.sleep(10);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    double m = Math.random();
	    logger.info("create: " + m);
	    try {
		queue.offer(m);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

    static class Customer {
	private MyBlockingQueue queue;

	public Customer(MyBlockingQueue queue) {
	    this.queue = queue;
	}

	public void consume() {
	    try {
		Thread.sleep(10);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    try {
		logger.info("consume: " + queue.take());
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

}

interface MyBlockingQueue {
    Object take() throws InterruptedException;

    void offer(Object obj) throws InterruptedException;

    int size();
}

class MyBlockingQueue1 implements MyBlockingQueue {
    private Object notEmpty = new Object();

    private Queue<Object> linkedList = new LinkedList<Object>();

    @Override
    public Object take() throws InterruptedException {
	synchronized (notEmpty) {
	    if (linkedList.size() == 0) {
		// Ҫִ��wait������������ȡ�øö��������ִ��wait����֮�������ͷţ�������֮ǰ����Ҫ�Ȼ������
		notEmpty.wait();
	    }
	    return linkedList.poll();
	}
    }

    @Override
    public void offer(Object obj) {
	synchronized (notEmpty) {
	    if (linkedList.size() == 0) {
		// Ҫִ��notify��notifyAll�������������Ȼ�øö��������
		notEmpty.notifyAll();
	    }
	    linkedList.add(obj);
	}
    }

    @Override
    public int size() {
	synchronized (notEmpty) {
	    return linkedList.size();
	}
    }
}

class MyBlockingQueue2 implements MyBlockingQueue {

    private Queue<Object> linkedList = new LinkedList<Object>();

    private Object notEmpty = new Object();
    private Object notFull = new Object();

    private int maxLength = 10;

    @Override
    public Object take() throws InterruptedException {
	synchronized (notEmpty) {
	    if (linkedList.size() == 0) {
		notEmpty.wait();
	    }
	    synchronized (notFull) {
		if (linkedList.size() == maxLength) {
		    notFull.notifyAll();
		}
		return linkedList.poll();
	    }
	}
    }

    @Override
    public void offer(Object obj) throws InterruptedException {
	// �ֱ���Ҫ��notEmpty��notFull����
	synchronized (notEmpty) {
	    if (linkedList.size() == 0) {
		notEmpty.notifyAll();
	    }
	    synchronized (notFull) {
		if (linkedList.size() == maxLength) {
		    notFull.wait();
		}
		linkedList.add(obj);
	    }
	}
    }

    @Override
    public int size() {
	return linkedList.size();
    }

}

class MyBlockingQueue3 implements MyBlockingQueue {
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    private Queue<Object> linkedList = new LinkedList<Object>();
    private int maxLength = 10;

    @Override
    public Object take() throws InterruptedException {
	lock.lock();
	try {
	    // Ҫִ��await������������ȡ�ø�Condition������ִ��await����֮�������ͷţ�������֮ǰ����Ҫ�Ȼ������
	    if (linkedList.size() == 0) {
		notEmpty.await();
	    }
	    if (linkedList.size() == maxLength) {
		notFull.signalAll();
	    }
	    return linkedList.poll();
	} finally {
	    lock.unlock();
	}
    }

    @Override
    public void offer(Object obj) throws InterruptedException {
	lock.lock();
	try {
	    // Ҫִ��signal��signalAll��������������ȡ�øö��������
	    if (linkedList.size() == 0) {
		notEmpty.signalAll();
	    }
	    if (linkedList.size() == maxLength) {
		notFull.await();
	    }
	    linkedList.add(obj);
	} finally {
	    lock.unlock();
	}
    }

    @Override
    public int size() {
	return 0;
    }

}
