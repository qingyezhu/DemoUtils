package com.wangzhu.fileutil;

import java.io.File;

/**
 * ʹ��renameTo�����ļ����ƶ���<br/>
 * ע�⣺�ļ�ϵͳ�Ĳ�ͬ�Ͳ����Խ����໥֮����ƶ�����NTFS �����໥�ƶ� FAT32��<br/>
 * 
 * @author wangzhu
 * @date 2015-3-15����1:44:33
 * 
 */
public class DemorenameTo {
    public static void main(String[] args) {
	move("E:\\apps\\hainanwenjiangui\\temp\\gwssi.debug.log",
		"H:\\gwssi.debug.log");
    }

    private static void move(String src, String source) {
	File srcFile = new File(src);
	File sourceFile = new File(source);
	System.out.println(srcFile.renameTo(sourceFile));
    }
}
