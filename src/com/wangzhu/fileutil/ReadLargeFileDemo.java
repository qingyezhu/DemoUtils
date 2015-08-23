package com.wangzhu.fileutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class ReadLargeFileDemo {
    private static final Logger logger = LoggerFactory
	    .getLogger(ReadLargeFileDemo.class);

    private static final String path = "e:\\test.txt";

    /**
     * 将文件全部读到内存中
     * 
     * @throws IOException
     */
    @Test
    public final void googleReadFile() throws IOException {
	logger.info("googleReadFile start");
	this.logMemory();
	Files.readLines(new File(path), Charsets.UTF_8);
	this.logMemory();
	logger.info("googleReadFile end");
    }

    /**
     * 将文件全部读到内存中
     * 
     * @throws IOException
     */
    @Test
    public final void apacheReadFile() throws IOException {
	logger.info("apacheReadFile start");
	this.logMemory();
	FileUtils.readLines(new File(path));
	this.logMemory();
	logger.info("apacheReadFile end");
    }

    @Test
    public void normalReadFile() throws IOException {
	logger.info("normalReadFile start");
	this.logMemory();
	FileInputStream fis = null;
	Scanner scanner = null;
	try {
	    fis = new FileInputStream(path);
	    scanner = new Scanner(fis, "UTF-8");
	    while (scanner.hasNext()) {
		scanner.nextLine();
	    }
	} finally {
	    if (fis != null) {
		fis.close();
	    }
	    if (scanner != null) {
		scanner.close();
	    }
	}
	this.logMemory();
	logger.info("normalReadFile end");
    }

    @Test
    public void apache2ReadFile() throws IOException {
	logger.info("apache2ReadFile start");
	this.logMemory();
	final LineIterator iterator = FileUtils.lineIterator(new File(path),
		"UTF-8");
	try {
	    while (iterator.hasNext()) {
		iterator.nextLine();
	    }
	} finally {
	    LineIterator.closeQuietly(iterator);
	}
	this.logMemory();
	logger.info("apache2ReadFile end");
    }

    private final void logMemory() {
	logger.info("Max Memory:{}Mb",
		Runtime.getRuntime().maxMemory() / 1048576);
	logger.info("Total Memory:{}Mb",
		Runtime.getRuntime().totalMemory() / 1048576);
	logger.info("Free Memory:{}Mb",
		Runtime.getRuntime().freeMemory() / 1048576);
	logger.info("\n");

    }
}
