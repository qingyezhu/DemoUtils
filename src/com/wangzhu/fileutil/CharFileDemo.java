package com.wangzhu.fileutil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符流读写文件
 * 
 * @author wangzhu
 * @date 2014-9-27上午10:02:30
 * 
 */
public class CharFileDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CharFileDemo
				.writerFile(
						"201409270955.txt",
						"go to the school!中国人，此情可待成追忆，只是当时已惘然！庄生晓梦迷蝴蝶，望帝春心托杜鹃！沧海月明珠有泪，蓝田日暖玉生烟！锦瑟无端五十弦，一弦一柱思华年！go to the school!中国人，此情可待成追忆，只是当时已惘然！庄生晓梦迷蝴蝶，望帝春心托杜鹃！沧海月明珠有泪，蓝田日暖玉生烟！锦瑟无端五十弦，一弦一柱思华年！go to the school!中国人，此情可待成追忆，只是当时已惘然！庄生晓梦迷蝴蝶，望帝春心托杜鹃！沧海月明珠有泪，蓝田日暖玉生烟！锦瑟无端五十弦，一弦一柱思华年！",
						true);
		CharFileDemo.readFile("201409270955.txt");

	}

	private static final int LEN = 1024;

	/**
	 * 将文件内容从文件中输入到内存中
	 * 
	 * @param fileName
	 */
	public static void readFile(final String fileName) {
		FileReader reader = null;

		try {
			reader = new FileReader(fileName);
			char[] buf = new char[CharFileDemo.LEN];
			int hasRead = 0;
			while ((hasRead = reader.read(buf)) > 0) {
				System.out.println(new String(buf, 0, hasRead));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
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
	 * @param fileName
	 * @param content
	 * @param append
	 */
	public static void writerFile(final String fileName, final String content,
			final boolean append) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(fileName, append);
			writer.write(content + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != writer) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
