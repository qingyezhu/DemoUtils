package com.wangzhu.fileutil;

import java.io.File;

/**
 * 使用renameTo进行文件的移动，<br/>
 * 注意：文件系统的不同就不可以进行相互之间的移动！（NTFS 不能相互移动 FAT32）<br/>
 * 
 * @author wangzhu
 * @date 2015-3-15下午1:44:33
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
