package com.wangzhu;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HeapSizeDifferences {

    static Collection<Object> objects = new ArrayList<Object>();

    static long lastMaxMemory = 0;

    public static void main(String[] args) {

	try {
	    List<String> inputArguments = ManagementFactory.getRuntimeMXBean()
		    .getInputArguments();
	    System.out.println("Running with: " + inputArguments);

	    while (true) {
		OtherUtils.logMemory();
		printMaxMemory();
		consumeSpace();
	    }
	} catch (OutOfMemoryError oom) {
	    oom.printStackTrace();
	    OtherUtils.logMemory();
	    freeSpace();
	    printMaxMemory();
	}
    }

    static void printMaxMemory() {
	long currentMaxMemory = Runtime.getRuntime().maxMemory();
	if (currentMaxMemory != lastMaxMemory) {
	    lastMaxMemory = currentMaxMemory;
	    System.out.format("Runtime.getRuntime().maxMemory(): %,dK.%n",
		    currentMaxMemory / 1024);
	}
    }

    static void consumeSpace() {
	objects.add(new Integer[1000000]);
    }

    static void freeSpace() {
	objects.clear();
    }

}
