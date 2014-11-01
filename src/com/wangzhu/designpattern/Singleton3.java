package com.wangzhu.designpattern;

/**
 * �������ģʽ����̬�ڲ���<br/>
 * ��JVM����ʱ����Ȼ��Singleton3��ᱻ���أ����������ʼ�������࣬<br/>
 * ������getInstance����ʱ���Żᱻ����SingletonHolder�� ������ʼ����<br/>
 * ��������ͨ�������ȡʵ����������ʹ��ö�ٵķ�����������ģʽ����Singleton4��<br/>
 * 
 * @author wangzhu
 * @date 2014-10-31����10:07:39
 * 
 */
public class Singleton3 {
	private Singleton3() {
	}

	private static class SingletonHolder {
		private static final Singleton3 INSTANCE = new Singleton3();
	}

	public static Singleton3 getInstance() {
		return SingletonHolder.INSTANCE;
	}

}
