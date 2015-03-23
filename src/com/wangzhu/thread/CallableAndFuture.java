package com.wangzhu.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 任务(Callable)与结果(Future)<br/>
 * 
 * @author wangzhu
 * @date 2015-3-22下午3:13:22
 * 
 */
public class CallableAndFuture {

    /**
     * @param args
     */
    public static void main(String[] args) {
	ExecutorService threadPool = Executors.newSingleThreadExecutor();

	Future<String> future = threadPool.submit(new Callable<String>() {

	    @Override
	    public String call() throws Exception {
		Thread.sleep(1000);
		return "welcome back!";
	    }
	});
	System.out.println("wait.........");
	try {
	    System.out.println("result=====" + future.get());
	} catch (InterruptedException e) {
	    e.printStackTrace();
	} catch (ExecutionException e) {
	    e.printStackTrace();
	}

	// 提交一组callable
	ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
	CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(
		threadPool2);
	for (int i = 0; i < 20; i++) {
	    final int seq = i;
	    completionService.submit(new Callable<Integer>() {

		@Override
		public Integer call() throws Exception {
		    return seq;
		}
	    });
	}
	for (int i = 0; i < 20; i++) {
	    try {
		System.out.println(completionService.take().get());
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    } catch (ExecutionException e) {
		e.printStackTrace();
	    }
	}
    }

}
