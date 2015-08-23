package com.wangzhu.dateformat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 根据系统时间格式：不同的系统中可能不同（例如英文系统与中文系统，就不同！）
 * 
 * @author wangzhu
 * @date 2014-9-26下午10:06:03
 * 
 */
public class DemoDateFormat {

    /**
     * @param args
     */
    public static void main(String[] args) {
	Date date = new Date(113, 1, 10);
	// 默认调用DateFormat.getDateTimeInstance()，本地时间显示格式
	System.out.println(date.toLocaleString());// 2013-2-10
	// DemoDateFormat.parse("2013-02-10");
	// DemoDateFormat.parse2("2013-02-10");

	// test1();
	test2();

    }

    private static void test1() {
	ExecutorService executorService = Executors.newCachedThreadPool();
	executorService.execute(new DateFormateThread("A", "2015-05-17", true));
	executorService
		.execute(new DateFormateThread("B", "2008-11-06", false));
	executorService.shutdown();
    }

    private static void test2() {
	final String pattern1 = "yyyy-MM-dd";
	final String pattern2 = "yyyy-MM";

	Thread t1 = new Thread() {
	    @Override
	    public void run() {
		try {
		    DateFormatUtil.parse("1989-10-31", pattern1);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	    }
	};
	Thread t2 = new Thread() {
	    @Override
	    public void run() {
		try {
		    DateFormatUtil.parse("2008-09", pattern2);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	    }
	};

	Thread t3 = new Thread() {
	    @Override
	    public void run() {
		try {
		    DateFormatUtil.parse("1989-10-31", pattern1);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	    }
	};
	Thread t4 = new Thread() {
	    @Override
	    public void run() {
		try {
		    DateFormatUtil.parse("2008-09", pattern2);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	    }
	};
	Thread t5 = new Thread() {
	    @Override
	    public void run() {
		try {
		    DateFormatUtil.parse("2008-09-22", pattern1);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	    }
	};
	Thread t6 = new Thread() {
	    @Override
	    public void run() {
		try {
		    DateFormatUtil.parse("2008-09", pattern2);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	    }
	};

	System.out.println("单线程执行：");
	ExecutorService exec1 = Executors.newSingleThreadExecutor();
	exec1.execute(t1);
	exec1.execute(t2);
	exec1.execute(t3);
	exec1.execute(t4);
	exec1.execute(t5);
	exec1.execute(t6);
	exec1.shutdown();

	try {
	    TimeUnit.MILLISECONDS.sleep(1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	System.out.println("双线程执行：");
	ExecutorService exec2 = Executors.newFixedThreadPool(2);
	exec2.execute(t1);
	exec2.execute(t2);
	exec2.execute(t3);
	exec2.execute(t4);
	exec2.execute(t5);
	exec2.execute(t6);
	exec2.shutdown();
    }

    /**
     * 在实际运行环境中竟然发生异常，就改为下面的方法
     * 
     * @param dateStr
     */
    private static void parse(String dateStr) {
	// 默认调用本地显示时间格式
	DateFormat format = DateFormat.getDateInstance();
	try {
	    System.out.println(format.parse(dateStr));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
    }

    /**
     * 形参的格式必须是yyyy-MM-dd否则将抛出异常
     * 
     * @param dateStr
     */
    private static void parse2(String dateStr) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	try {
	    System.out.println(sdf.parse(dateStr));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
    }

    static class DateFormateThread extends Thread {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private String name;
	private String dateStr;
	private boolean sleep;

	public DateFormateThread(String name, String dateStr, boolean sleep) {
	    this.name = name;
	    this.dateStr = dateStr;
	    this.sleep = sleep;
	}

	@Override
	public void run() {
	    Date date = null;
	    if (sleep) {
		try {
		    TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	    try {
		date = sdf.parse(dateStr);
	    } catch (ParseException e) {
		e.printStackTrace();
	    }
	    System.out.println(name + " : date: " + date);
	}

    }

}
