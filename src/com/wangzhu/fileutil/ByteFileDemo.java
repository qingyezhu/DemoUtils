package com.wangzhu.fileutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * 字节流读写文件
 * 
 * @author wangzhu
 * @date 2014-9-27上午9:38:17
 * 
 */
public class ByteFileDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ByteFileDemo.writerFile("201409270939.txt",
				"Welcome to The world!中国人！", true);
		ByteFileDemo.readFile("201409270939.txt");

	}

	private static final int LEN = 1024;
	private static final String CHARESETNAME = "UTF-8";

	/**
	 * 将文件内容从文件中输入到内存中
	 * 
	 * @param filePath
	 */
	public static void readFile(final String filePath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			byte[] buf = new byte[ByteFileDemo.LEN];
			int hasRead = 0;
			while ((hasRead = fis.read(buf)) > 0) {
				System.out.println(new String(buf, 0, hasRead,
						ByteFileDemo.CHARESETNAME));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 将字符串输出到文件中<br/>
	 * append(true:追加到文件末尾，false:重新写入）
	 * 
	 * @param filePath
	 * @param content
	 * @param append
	 */
	public static void writerFile(final String filePath, final String content,
			final boolean append) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath, append);
			byte[] buf = content.getBytes(ByteFileDemo.CHARESETNAME);
			fos.write(buf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 执行close()方法之前，自动执行输出流的flush()方法
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
