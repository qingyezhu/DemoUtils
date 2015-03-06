package com.wangzhu;

import org.apache.log4j.Logger;

public final class OtherUtils {
    private static Logger log = Logger.getLogger(OtherUtils.class);
    private static int mod = 1048576 / 1024;

    /**
     * 检查内存使用情况
     */
    public static void logMemory() {
	log.info("Max Memory: " + (Runtime.getRuntime().maxMemory() / mod)
		+ "MB");
	log.info("Total Memory: " + (Runtime.getRuntime().totalMemory() / mod)
		+ "MB");
	log.info("Free Memory: " + (Runtime.getRuntime().freeMemory() / mod)
		+ "MB");
	log.info("=======================");
    }

}
