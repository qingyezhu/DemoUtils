package com.wangzhu.thread;

import org.apache.log4j.Logger;

/**
 * wait����sleepһ�����Ƿ���CPUִ��Ȩ����������sleep��һ���ĵط�����Ҫ�ȴ�����һ��������ͬ�����̶߳�����л��Ѳ�����<br/>
 * ����wait����������һ��ͬ�����������׳��쳣<br/>
 * notify����֮ǰ������ʱ״̬���̻߳��ѣ����һ�ȡִ��Ȩ���ȴ�CPU���ٴε��ã�������һ����Ҫע����Ǳ����wait�����õ�������ͬһ��<br/>
 * notifyAll��������wait�е��̶߳����л��ѣ���Ȼǰ����ǻ��ѵ��̳߳��к��Լ�һ�����������򽫲��ܱ�����<br/>
 * 
 * @author wangzhu
 * @date 2015-4-3����3:16:40
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
 * ��װ�����������������ù������ṩ�����������ѷ���<br/>
 * 
 * @author wangzhu
 * @date 2015-4-3����3:07:35
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
 * �������������ߵĻ���
 * 
 * @author wangzhu
 * @date 2015-4-3����3:09:52
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