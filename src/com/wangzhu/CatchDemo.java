package com.wangzhu;

public class CatchDemo {
	public CatchDemo() {
	}

	boolean testEx() throws Exception {
		boolean ret = true;
		try {
			ret = this.testEx1();
			return ret;
		} catch (Exception e) {
			System.out.println("testEx, catch exception");
			ret = false;
			throw e;
		} finally {
			System.out.println("testEx, finally; return value=" + ret);
			// return ret;
		}
	}

	boolean testEx1() throws Exception {
		boolean ret = true;
		try {
			ret = this.testEx2();
			if (!ret) {
				return false;
			}
			System.out.println("testEx1, at the end of try");
			return !ret;
		} catch (Exception e) {
			System.out.println("testEx1, catch exception");
			ret = false;
			throw e;
		} finally {
			System.out.println("testEx1, finally; return value=" + ret);
			// return ret;
		}
	}

	boolean testEx2() throws Exception {
		boolean ret = true;
		try {

			int b = 12;
			for (int i = 2; i >= -2; i--) {
				System.out.println("i=" + (b / i));
			}
			return true;
		} catch (Exception e) {
			System.out.println("testEx2, catch exception");
			ret = false;
			throw e;
		} finally {
			System.out.println("testEx2, finally; return value=" + ret);
			// return ret;
		}
	}

	public static void main(String[] args) {
		CatchDemo testException1 = new CatchDemo();
		try {
			testException1.testEx();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}