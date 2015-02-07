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
 * 去除文件中重复的行
 * 
 * @author wangzhu
 * 
 */
public class FileUtil {
	private static final Logger LOGGER = Logger.getLogger(FileUtil.class);

	/**
	 * 获取文件名
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
		FileUtil.LOGGER.debug("main 所用时间：" + (end - start));
	}

	/**
	 * 将文件中的每行添加到Set中
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
			// 防止读取中文乱码
			final InputStreamReader isr = new InputStreamReader(
					new FileInputStream(file), "UTF-8");
			reader = new BufferedReader(isr);
			String line = null;
			while (null != (line = reader.readLine())) {
				set.add(line);
			}
		} catch (final Exception e) {
			FileUtil.LOGGER.error("readFile 发生异常：", e);
		} finally {
			try {
				if (null != reader) {
					reader.close();
				}
			} catch (final Exception e) {
				FileUtil.LOGGER.error("readFile 发生异常：", e);
			}
		}
		final long end = System.currentTimeMillis();
		FileUtil.LOGGER.debug("readFile 所用时间：" + (end - start));
	}

	/**
	 * 将Set中的内容一行一行的写入文件中
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
			// 防止写入中文乱码
			final OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8");
			writer = new BufferedWriter(osw);
			for (final String line : set) {
				writer.write(line + "\r\n");
			}
			writer.flush();
		} catch (final IOException e) {
			FileUtil.LOGGER.error("writeFile 发生异常：", e);
		} finally {
			try {
				if (null != writer) {
					writer.close();
				}
			} catch (final IOException e) {
				FileUtil.LOGGER.error("writeFile 发生异常：", e);
			}
		}
		final long end = System.currentTimeMillis();
		FileUtil.LOGGER.debug("writeFile 所用时间：" + (end - start));
	}
}
