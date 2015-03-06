package com.wangzhu.thread;

/**
 * 死锁<br/>
 * notify和notifyAll的区别：<br/>
 * 都是Object对象用于通知处在等待该对象的线程的方法。<br/>
 * notify：唤醒一个正在等待该对象的线程。<br/>
 * notifyAll：唤醒所有正在等待该对象的线程。<br>
 * 最大区别：<br/>
 * notifyAll使所有原来在该对象上等待被notify的线程统统退出的状态，<br/>
 * 变成等待该对象上的锁，一旦该对象被解锁，他们就会去竞争。<br/>
 * notify，只是选择一个wait状态线程进行通知，并使它获得该对象上的锁，<br/>
 * 但不惊动其他同样在等待被该对象notify的线程，当第一个线程运行完毕以后释放对象上的锁，<br/>
 * 此时如果该对象没有再次使用notify语句，即便该对象已经空闲，其他wait状态等待的线程由于没有得到该对象的通知，<br/>
 * 继续处在wait状态， 直到这个对象发出一个notifyAll或notify，它们等待的是被notifyAll或notify，而不是锁。
 * 
 * @author wangzhu
 * @date 2015-3-2下午2:59:07
 * 
 */
public class DeadLockDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
	final OutTurn ot = new OutTurn();
	for (int j = 0; j < 100; j++) {
	    new Thread(new Runnable() {

		@Override
		public void run() {
		    for (int i = 0; i < 5; i++) {
			ot.sub();
		    }
		}
	    }).start();
	    new Thread(new Runnable() {

		@Override
		public void run() {
		    for (int i = 0; i < 5; i++) {
			ot.main();
		    }
		}
	    }).start();

	}

    }

}

class OutTurn {
    private boolean isSub = true;
    private int count = 0;

    public synchronized void sub() {
	try {
	    while (!isSub) {
		this.wait();
	    }
	    System.out.println("sub=====" + count);
	    isSub = false;
	    // this.notify();
	    this.notifyAll();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	count++;
    }

    public synchronized void main() {
	try {
	    while (isSub) {
		this.wait();
	    }
	    System.out.println("main=====" + count);
	    isSub = true;
	    // this.notify();
	    this.notifyAll();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	count++;
    }
}