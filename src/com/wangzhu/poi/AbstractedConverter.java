package com.wangzhu.poi;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public abstract class AbstractedConverter {
	/**
	 * ���뷽ʽ
	 */
	static final String ENCODING = "utf-8";
	/**
	 * �Ƿ���ж���Ŀո�
	 */
	static final String INDENT = "yes";
	/**
	 * ת������
	 */
	static final String METHOD = "html";

	/**
	 * ��Excel��Wordת��ΪHtml
	 * 
	 * @param sourcePath
	 *            Դ�ļ�·��
	 * @param targetPath
	 *            Ŀ���ļ�·��
	 * @param onlinePath
	 *            ͼƬ������·��
	 * @throws Exception
	 */
	abstract void converter(String sourcePath, String targetPath,
			String onlinePath) throws Exception;

	/**
	 * ������д���ļ���
	 * 
	 * @param content
	 *            �ļ�����
	 * @param path
	 *            �ļ�·��
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
