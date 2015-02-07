package com.wangzhu.poi;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public abstract class AbstractedConverter {
	/**
	 * 编码方式
	 */
	static final String ENCODING = "utf-8";
	/**
	 * 是否带有额外的空格
	 */
	static final String INDENT = "yes";
	/**
	 * 转化类型
	 */
	static final String METHOD = "html";

	/**
	 * 将Excel或Word转化为Html
	 * 
	 * @param sourcePath
	 *            源文件路径
	 * @param targetPath
	 *            目标文件路径
	 * @param onlinePath
	 *            图片的引用路径
	 * @throws Exception
	 */
	abstract void converter(String sourcePath, String targetPath,
			String onlinePath) throws Exception;

	/**
	 * 将内容写入文件中
	 * 
	 * @param content
	 *            文件内容
	 * @param path
	 *            文件路径
	 */
	void writeFile(String content, String path) {
		FileOutputStream fos = null;
		BufferedWriter writer = null;
		try {
			fos = new FileOutputStream(path);
			writer = new BufferedWriter(new OutputStreamWriter(fos,
					AbstractedConverter.ENCODING));
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
