package com.wangzhu.thread;

import java.util.Arrays;

import com.wangzhu.dateutil.DateUtil;

/**
 * Java�첽����
 * 
 * @author wangzhu
 * @date 2015-3-1����10:24:32
 * 
 */
public class AsyncTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

	System.out.println("main start: " + DateUtil.getYMDHMST());
	final FutureTicker ft = new FutureTicker(new MyProcessData());
	// �����߳��е��ú�ʱ����
	new Thread() {
	    @Override
	    public void run() {
		ft.makeRealData();
	    }
	}.start();
	ft.processData();
	System.out.println("main end: " + DateUtil.getYMDHMST());
    }
}

interface ProcessData {
    void process(Object data);
}

class MyProcessData implements ProcessData {

    @Override
    public void process(Object data) {
	System.out.println(data.toString() + " over!");
    }

}

class FutureTicker {
    private Object data = null;
    private boolean completed = false;
    private ProcessData pd;

    public FutureTicker(ProcessData pd) {
	this.pd = pd;
    }

    public synchronized void makeRealData() {
	System.out.println("makeRealData start: " + DateUtil.getYMDHMST());
	if (completed) {
	    return;
	}
	// ��ȡ���ݵĺ�ʱ������ʹ��Sleep����
	try {
	    Thread.sleep(10000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	data = "this content!";
	completed = true;
	this.notifyAll();
	System.out.println("makeRealData end: " + DateUtil.getYMDHMST());
    }

    public synchronized void processData() {
	System.out.println(Arrays
		.asList(Thread.currentThread().getStackTrace())
		+ "=====methodName: "
		+ Thread.currentThread().getStackTrace()[1].getMethodName());
	System.out.println("processData start: " + DateUtil.getYMDHMST());
	while (!completed) {
	    try {
		this.wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	pd.process(data);
	System.out.println("processData end: " + DateUtil.getYMDHMST());
    }
}