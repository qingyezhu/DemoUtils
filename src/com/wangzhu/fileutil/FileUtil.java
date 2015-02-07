package com.wangzhu.fileutil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.wangzhu.UuidGenerator;
import com.wangzhu.dateutil.DateUtil;

/**
 * ȥ���ļ����ظ�����
 * 
 * @author wangzhu
 * 
 */
public class FileUtil {
	private static final Logger LOGGER = Logger.getLogger(FileUtil.class);

	/**
	 * ��ȡ�ļ���
	 * 
	 * @return
	 */
	public static String getUuidFileName() {
		return UuidGenerator.getUUid() + "_" + DateUtil.getYMDHMSTS();
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		final long start = System.currentTimeMillis();
		final String filePath = "h:\\201407151806.txt";
		final Set<String> set = new HashSet<String>();
		FileUtil.readFile(filePath, set);
		FileUtil.LOGGER.info("set.size: " + set.size());
		FileUtil.writeFile("h:\\201407151806Tmp.dic", set);
		final long end = System.currentTimeMillis();
		FileUtil.LOGGER.debug("main ����ʱ�䣺" + (end - start));
	}

	/**
	 * ���ļ��е�ÿ����ӵ�Set��
	 * 
	 * @param filePath
	 * @param set
	 */
	public static void readFile(final String filePath, final Set<String> set) {

		final long start = System.currentTimeMillis();
		final File file = new File(filePath);
		BufferedReader reader = null;
		try {
			// reader = new BufferedReader(new FileReader(file));
			// ��ֹ��ȡ��������
			final InputStreamReader isr = new InputStreamReader(
					new FileInputStream(file), "UTF-8");
			reader = new BufferedReader(isr);
			String line = null;
			while (null != (line = reader.readLine())) {
				set.add(line);
			}
		} catch (final Exception e) {
			FileUtil.LOGGER.error("readFile �����쳣��", e);
		} finally {
			try {
				if (null != reader) {
					reader.close();
				}
			} catch (final Exception e) {
				FileUtil.LOGGER.error("readFile �����쳣��", e);
			}
		}
		final long end = System.currentTimeMillis();
		FileUtil.LOGGER.debug("readFile ����ʱ�䣺" + (end - start));
	}

	/**
	 * ��Set�е�����һ��һ�е�д���ļ���
	 * 
	 * @param filePath
	 * @param set
	 */
	public static void writeFile(final String filePath, final Set<String> set) {
		final long start = System.currentTimeMillis();
		final File file = new File(filePath);
		BufferedWriter writer = null;
		try {
			// writer = new BufferedWriter(new FileWriter(file));
			// ��ֹд����������
			final OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8");
			writer = new BufferedWriter(osw);
			for (final String line : set) {
				writer.write(line + "\r\n");
			}
			writer.flush();
		} catch (final IOException e) {
			FileUtil.LOGGER.error("writeFile �����쳣��", e);
		} finally {
			try {
				if (null != writer) {
					writer.close();
				}
			} catch (final IOException e) {
				FileUtil.LOGGER.error("writeFile �����쳣��", e);
			}
		}
		final long end = System.currentTimeMillis();
		FileUtil.LOGGER.debug("writeFile ����ʱ�䣺" + (end - start));
	}
}
