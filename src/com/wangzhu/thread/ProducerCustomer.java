package com.wangzhu.thread;

import org.apache.log4j.Logger;

/**
 * wait：与sleep一样就是放弃CPU执行权，但是其与sleep不一样的地方是需要等待另外一个持有相同锁的线程对其进行唤醒操作，<br/>
 * 并且wait方法必须有一个同步锁，否则抛出异常<br/>
 * notify：将之前处在零时状态的线程唤醒，并且获取执行权，等待CPU的再次调用，但是有一点需要注意的是必须和wait方法用到的锁是同一个<br/>
 * notifyAll：将所有wait中的线程都进行唤醒，当然前提就是唤醒的线程持有和自己一样的锁，否则将不能被唤醒<br/>
 * 
 * @author wangzhu
 * @date 2015-4-3下午3:16:40
 * 
 */
public class ProducerCustomer {
    private static final Logger logger = Logger
	    .getLogger(ProducerCustomer.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
	final Factory factory = new Factory();
	int count = 2;
	for (int i = 0; i < count; i++) {
	    new Thread(new Producer(factory)).start();
	    new Thread(new Consumer(factory)).start();
	}

	new Thread(new Runnable() {

	    @Override
	    public void run() {
		while (true) {
		    if ((factory.getI() & 1) == 0) {
			synchronized (factory) {
			    logger.info(Thread.currentThread().getName()
				    + "===wait===");
			    try {
				factory.wait();
			    } catch (InterruptedException e) {
				e.printStackTrace();
			    }
			}
		    }

		    logger.info(Thread.currentThread().getName()
			    + "thread==================");
		    try {
			Thread.sleep(200);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	    }
	}, "other-thread").start();
    }
}

/**
 * 封装了数据生产工厂，该工厂中提供了生产和消费方法<br/>
 * 
 * @author wangzhu
 * @date 2015-4-3下午3:07:35
 * 
 */
class Factory {
    private static final Logger logger = Logger.getLogger(Factory.class);
    private int i = 0;
    private boolean created = false;

    public int getI() {
	return i;
    }

    public void create() {
	synchronized (this) {
	    if (!created) {
		i = i + 1;
		logger.info(Thread.currentThread().getName() + "---create---"
			+ i);
		created = true;
		this.notifyAll();
	    } else {
		try {
		    this.wait();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    public void consume() {
	synchronized (this) {
	    if (created) {
		logger.info(Thread.currentThread().getName() + "---consume---"
			+ i);
		created = false;
		this.notifyAll();
	    } else {
		try {
		    this.wait();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

}

/**
 * 生产者与消费者的基类
 * 
 * @author wangzhu
 * @date 2015-4-3下午3:09:52
 * 
 */
abstract class AbsFactory implements Runnable {
    protected Factory factory = null;

    public AbsFactory(Factory factory) {
	super();
	this.factory = factory;
    }

    abstract protected void execute();

    @Override
    public void run() {
	while (true) {
	    this.execute();
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }
}

class Producer extends AbsFactory {

    public Producer(Factory factory) {
	super(factory);
    }

    @Override
    protected void execute() {
	factory.create();
    }

}

class Consumer extends AbsFactory {

    public Consumer(Factory factory) {
	super(factory);
    }

    @Override
    protected void execute() {
	factory.consume();
    }

}