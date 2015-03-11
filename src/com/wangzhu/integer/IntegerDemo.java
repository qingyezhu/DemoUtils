package com.wangzhu.integer;

/**
 * Java的数学计算是在内存栈里操作的
 * 
 * @author wangzhu
 * @date 2015-3-8下午7:52:30
 * 
 */
public class IntegerDemo {

    public static void main(String[] args) {
	try {
	    testCache();
	    testNoCache();
	    // } catch (IOException ioe) {
	    // 在没有发生异常的区域可以捕获非检查性异常，不可捕获检查性异常
	} catch (RuntimeException re) {

	} catch (Exception e) {

	} finally {

	}
    }

    private static void testCache() {
	Integer t1 = 40, t2 = 40, t3 = 0, t4 = new Integer(40), t5 = new Integer(
		40), t6 = new Integer(0);
	System.out.println("t1 == t2\t" + (t1 == t2));
	System.out.println("t1 == (t2 + t3)\t" + (t1 == (t2 + t3)));
	System.out.println("t4 == t5\t" + (t4 == t5));
	System.out.println("t4 == (t5 + t6)\t" + (t4 == (t5 + t6)));
    }

    // t1 == t2 true
    // t1 == (t2 + t3) true
    // t4 == t5 false
    // t4 == (t5 + t6) true

    private static void testNoCache() {
	Integer t1 = 400, t2 = 400, t3 = 0, t4 = new Integer(400), t5 = new Integer(
		400), t6 = new Integer(0);
	System.out.println("t1 == t2\t" + (t1 == t2));
	System.out.println("t1 == (t2 + t3)\t" + (t1 == (t2 + t3)));
	System.out.println("t4 == t5\t" + (t4 == t5));
	System.out.println("t4 == (t5 + t6)\t" + (t4 == (t5 + t6)));
    }
    // t1 == t2 false
    // t1 == (t2 + t3) true
    // t4 == t5 false
    // t4 == (t5 + t6) true
}
