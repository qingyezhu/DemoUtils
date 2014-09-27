package com.wangzhu.fileutil;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Command���ģʽ
 * 
 * @author wangzhu
 * @date 2014-9-27����9:13:44
 * 
 */
public class FilenameFilterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("./src/com/wangzhu/fileutil");
		String[] nameList = file.list(new MyFilenameFilter());
		for (String name : nameList) {
			System.out.println("name: " + name);
		}
	}

}

// ʵ���Լ���FilenameFilterʵ����
class MyFilenameFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		// dir:��ǰ�ļ����ڵ�Ŀ¼
		// name:��ǰ�ļ���
		// System.out.println(dir.getName() + "========" + name);
		return name.endsWith(".java");
	}
}