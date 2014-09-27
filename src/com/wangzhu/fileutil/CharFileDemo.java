package com.wangzhu.fileutil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * �ַ�����д�ļ�
 * 
 * @author wangzhu
 * @date 2014-9-27����10:02:30
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
						"go to the school!�й��ˣ�����ɴ���׷�䣬ֻ�ǵ�ʱ���Ȼ��ׯ�������Ժ��������۴����жž飡�׺����������ᣬ������ů�����̣���ɪ�޶���ʮ�ң�һ��һ��˼���꣡go to the school!�й��ˣ�����ɴ���׷�䣬ֻ�ǵ�ʱ���Ȼ��ׯ�������Ժ��������۴����жž飡�׺����������ᣬ������ů�����̣���ɪ�޶���ʮ�ң�һ��һ��˼���꣡go to the school!�й��ˣ�����ɴ���׷�䣬ֻ�ǵ�ʱ���Ȼ��ׯ�������Ժ��������۴����жž飡�׺����������ᣬ������ů�����̣���ɪ�޶���ʮ�ң�һ��һ��˼���꣡",
						true);
		CharFileDemo.readFile("201409270955.txt");

	}

	private static final int LEN = 1024;

	/**
	 * ���ļ����ݴ��ļ������뵽�ڴ���
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
	 * ���ַ���������ļ���<br/>
	 * append(true:׷�ӵ��ļ�ĩβ��false:����д�룩
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
