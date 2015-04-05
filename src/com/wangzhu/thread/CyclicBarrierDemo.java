package com.wangzhu.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.apache.log4j.Logger;

/**
 * CyclicBarrier��һ��ͬ�������࣬������һ���߳��໥�ȴ���֪������ĳ���������ϵ㡣<br/>
 * ���漰һ��̶���С�Ķ��߳��У���Щ�̱߳��벻ʱ�ػ���ȴ�����ʱCyclicBarrier�����á�<br/>
 * ��Ϊ��CyclicBarrier���ͷŵȴ��̺߳�������ã����Գ���Ϊѭ����CyclicBarrier��<br/>
 * 
 * Ӧ�ó���<br/>
 * ��Ҫͳ��ȫ����ҵ�����ݣ����и�ʡ�����ݿ��Ƕ����ģ�Ҳ����˵��ʡ�ֿ⣬����ͳ�Ƶ��������ܴ�<br/>
 * ͳ�ƹ���Ҳ�Ƚ�����Ϊ��������ܣ����ټ��㣬���ǲ�ȡ�����ķ�ʽ������߳�ͬʱ�����ʡ���ݣ�����ʡ�������ö��̣߳�����ٻ���ͳ�ơ�<br/>
 * 
 * @author wangzhu
 * @date 2015-4-5����8:00:04
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
	new UnitTask(barrier, "����").start();
	new UnitTask(barrier, "�Ϻ�").start();
	new UnitTask(barrier, "����").start();

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
