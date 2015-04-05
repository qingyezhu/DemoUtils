package com.wangzhu.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.apache.log4j.Logger;

/**
 * CyclicBarrier是一个同步辅助类，它允许一组线程相互等待，知道到达某个公共屏障点。<br/>
 * 在涉及一组固定大小的额线程中，这些线程必须不时地互相等待，此时CyclicBarrier很有用。<br/>
 * 因为该CyclicBarrier在释放等待线程后可以重用，所以称它为循环的CyclicBarrier。<br/>
 * 
 * 应用场景<br/>
 * 需要统计全国的业务数据，其中各省的数据库是独立的，也就是说按省分库，并且统计的数据量很大，<br/>
 * 统计过程也比较慢，为了提高性能，快速计算，我们采取并发的方式，多个线程同时计算各省数据，各个省下面又用多线程，最后再汇总统计。<br/>
 * 
 * @author wangzhu
 * @date 2015-4-5下午8:00:04
 * 
 */
public class CyclicBarrierDemo {

    private static final Logger logger = Logger
	    .getLogger(CyclicBarrierDemo.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
	CyclicBarrier barrier = new CyclicBarrier(3, new TotalTask());
	new UnitTask(barrier, "北京").start();
	new UnitTask(barrier, "上海").start();
	new UnitTask(barrier, "深圳").start();

    }

    static class TotalTask implements Runnable {

	@Override
	public void run() {
	    logger.info("TotalTask run");
	}

    }

    static class UnitTask extends Thread {
	private CyclicBarrier barrier;
	private String name;

	public UnitTask(CyclicBarrier barrier, String name) {
	    super();
	    this.barrier = barrier;
	    this.name = name;
	}

	@Override
	public void run() {
	    logger.info("UnitTask " + name + " start");
	    try {
		Thread.sleep(1000);
	    } catch (InterruptedException e1) {
		e1.printStackTrace();
	    }
	    logger.info("UnitTask " + name + " end");
	    try {
		barrier.await();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    } catch (BrokenBarrierException e) {
		e.printStackTrace();
	    }
	}
    }
}
