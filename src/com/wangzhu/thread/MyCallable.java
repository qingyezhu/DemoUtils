package com.wangzhu.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.apache.log4j.Logger;

/**
 * 第三种线程实现方式：实现Callable接口<br/>
 * 
 * @author wangzhu
 * @date 2015-4-5下午7:20:12
 * 
 */
public class MyCallable implements Callable<String> {
    private static final Logger logger = Logger.getLogger(MyCallable.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
	MyCallable callable = new MyCallable();

	FutureTask<String> futureTask = new FutureTask<String>(callable);
	new Thread(futureTask).start();
	logger.info("MyCallable thread start");
	try {
	    logger.info("MyCallable thread result: " + futureTask.get());
	} catch (InterruptedException e) {
	    e.printStackTrace();
	} catch (ExecutionException e) {
	    e.printStackTrace();
	}
	logger.info("MyCallable thread end");
    }

    @Override
    public String call() throws Exception {
	Thread.sleep(1000);
	return "thread call";
    }

}
