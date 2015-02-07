package com.wangzhu.net;

import java.util.Map;

public class DownloadTest {
	public static void main(String[] args) throws Exception {
		final DownloadUtil downloadUtil = new DownloadUtil(
				"http://www.headfirstlabs.com/books/hfjava/hfjavafinalsamples.zip",
				"abc.zip", 4);
		// 开始下载
		downloadUtil.download();
		new Thread("A") {
			@Override
			public void run() {
				while (downloadUtil.getCompleteRate() < 1) {
					// 每隔0.1秒查询一次任务的完成进度，GUI程序中可根据该进度来绘制进度条
					System.out.println("已完成：" + downloadUtil.getCompleteRate());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("start====");
					Map<Thread, StackTraceElement[]> threadMap = Thread
							.getAllStackTraces();
					for (Thread thread : threadMap.keySet()) {
						System.out.println(thread.getName());
					}
					System.out.println("end====");

				}
				System.out.println("Over====");
				Map<Thread, StackTraceElement[]> threadMap = Thread
						.getAllStackTraces();
				for (Thread thread : threadMap.keySet()) {
					System.out.println(thread.getName());
				}
			};
		}.start();
	}

}
