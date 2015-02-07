package com.wangzhu.net;

import java.util.Map;

public class DownloadTest {
	public static void main(String[] args) throws Exception {
		final DownloadUtil downloadUtil = new DownloadUtil(
				"http://www.headfirstlabs.com/books/hfjava/hfjavafinalsamples.zip",
				"abc.zip", 4);
		// ��ʼ����
		downloadUtil.download();
		new Thread("A") {
			@Override
			public void run() {
				while (downloadUtil.getCompleteRate() < 1) {
					// ÿ��0.1���ѯһ���������ɽ��ȣ�GUI�����пɸ��ݸý��������ƽ�����
					System.out.println("����ɣ�" + downloadUtil.getCompleteRate());
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
