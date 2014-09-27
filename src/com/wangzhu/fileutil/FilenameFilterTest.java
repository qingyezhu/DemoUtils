package com.wangzhu.fileutil;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Command设计模式
 * 
 * @author wangzhu
 * @date 2014-9-27上午9:13:44
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

// 实现自己的FilenameFilter实现类
class MyFilenameFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		// dir:当前文件所在的目录
		// name:当前文件名
		// System.out.println(dir.getName() + "========" + name);
		return name.endsWith(".java");
	}
}