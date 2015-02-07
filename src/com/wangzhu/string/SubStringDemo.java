package com.wangzhu.string;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class SubStringDemo {
	private final Logger logger = Logger.getLogger(SubStringDemo.class);
	private static final int LEN = 5900;
	private String largeString = new String(new byte[SubStringDemo.LEN]);

	String getString() {
		return largeString.substring(0, 2);
		// return new String("ab");
	}

	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				new SubStringDemo().logMemory();
			}
		});
		ArrayList<String> list = new ArrayList<String>();
		SubStringDemo demo = null;
		demo = new SubStringDemo();
		for (int i = 0; i < SubStringDemo.LEN; i++) {
			demo = new SubStringDemo();
			list.add(demo.getString());
		}

	}

	public void implicitUseStringBuilder(String[] strArr) {
		String ret = "";
		for (String element : strArr) {
			ret += element;
		}
		System.out.println(ret);
	}

	private final void logMemory() {
		logger.info("Max Memory: "
				+ (Runtime.getRuntime().maxMemory() / 1048576) + "MB");
		logger.info("Total Memory: "
				+ (Runtime.getRuntime().totalMemory() / 1048576) + "MB");
		logger.info("Free Memory: "
				+ (Runtime.getRuntime().freeMemory() / 1048576) + "MB");

	}
}
