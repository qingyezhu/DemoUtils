package com.wangzhu.net;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadUtil {
	/**
	 * ����������Դ��·��
	 */
	private String path;
	/**
	 * ָ�������ص��ļ��ı���λ��
	 */
	private String targetFile;
	/**
	 * ������Ҫʹ�ö��ٸ��߳�������Դ
	 */
	private int threadNum;
	/**
	 * �������ص��̶߳���
	 */
	private DownloadThread[] threads;
	/**
	 * �������ص��ļ����ܴ�С
	 */
	private int fileSize;

	public DownloadUtil(String path, String targetFile, int threadNum) {
		this.path = path;
		this.targetFile = targetFile;
		this.threadNum = threadNum;
		// ��ʼ��threads����
		threads = new DownloadThread[threadNum];
	}

	public void download() throws Exception {
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(5 * 1000);
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "image/gif,image/jpeg,"
				+ "application/x-shockwave-falsh,application/xaml+xml,"
				+ "application/vnd.ms-xpsdocument,application/x-ms-xbap,"
				+ "application/x-ms-application,application/vnd.ms-excel,"
				+ "application/vnd.ms-powerpoint,application/msword,*/*");
		connection.setRequestProperty("Accept-Language", "zh-CN");
		connection.setRequestProperty("Charset", "UTF-8");
		connection.setRequestProperty("Connection", "Keep-Alive");
		// �õ��ļ���С
		fileSize = connection.getContentLength();
		connection.disconnect();
		int currentPartSize = (fileSize / threadNum) + 1;
		RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
		// ���ñ����ļ��Ĵ�С
		file.setLength(fileSize);
		file.close();
		for (int i = 0; i < threadNum; i++) {
			// ����ÿ���߳����صĿ�ʼλ��
			int startPos = i * currentPartSize;
			// ÿ���߳�ʹ��һ��RandomAccessFile��������
			RandomAccessFile currentPart = new RandomAccessFile(targetFile,
					"rw");
			// ��λ���̵߳�����λ��
			currentPart.seek(startPos);
			// ���������߳�
			threads[i] = new DownloadThread(startPos, currentPartSize,
					currentPart);
			threads[i].setName("thread" + i);
			// ���������߳�
			threads[i].start();
		}
	}

	/**
	 * ��ȡ���ص���ɰٷֱ�
	 */
	public double getCompleteRate() {
		// ͳ�ƶ���߳��Ѿ����ص��ܴ�С
		int sumSize = 0;
		for (int i = 0; i < threadNum; i++) {
			sumSize += threads[i].length;
		}
		// �����Ѿ���ɵİٷֱ�
		return (1.0 * sumSize) / fileSize;
	}

	private class DownloadThread extends Thread {
		/**
		 * ��ǰ�̵߳�����λ��
		 */
		private int startPos;
		/**
		 * ���嵱ǰ�̸߳������ص��ļ���С
		 */
		private int currentPartSize;
		/**
		 * ��ǰ�߳���Ҫ���ص��ļ���
		 */
		private RandomAccessFile currentPart;
		/**
		 * ������س������ص��ֽ���
		 */
		public int length;

		public DownloadThread(int startPos, int currentPartSize,
				RandomAccessFile currentPart) {
			this.startPos = startPos;
			this.currentPartSize = currentPartSize;
			this.currentPart = currentPart;
		}

		@Override
		public void run() {
			try {
				URL url = new URL(path);
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setConnectTimeout(5 * 1000);
				connection.setRequestMethod("GET");
				connection
						.setRequestProperty(
								"Accept",
								"image/gif,image/jpeg,"
										+ "application/x-shockwave-falsh,application/xaml+xml,"
										+ "application/vnd.ms-xpsdocument,application/x-ms-xbap,"
										+ "application/x-ms-application,application/vnd.ms-excel,"
										+ "application/vnd.ms-powerpoint,application/msword,*/*");
				connection.setRequestProperty("Accept-Language", "zh-CN");
				connection.setRequestProperty("Charset", "UTF-8");
				InputStream inputStream = connection.getInputStream();
				// �������ֽڣ��������߳�ֻ�����Լ�������ǲ����ļ�
				inputStream.skip(startPos);
				byte[] buffer = new byte[1024];
				int hasRead = 0;
				// ��ȡ�������ݣ���д�뱾���ļ�
				while ((length < currentPartSize)
						&& ((hasRead = inputStream.read(buffer)) != -1)) {
					currentPart.write(buffer, 0, hasRead);
					// �ۼƸ��س����ص��ܴ�С
					length += hasRead;
				}
				currentPart.close();
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
