package com.wangzhu.designpattern;

/**
 * 单例设计模式：静态内部类<br/>
 * 当JVM启动时，虽然该Singleton3类会被加载，但并不会初始化单例类，<br/>
 * 当调用getInstance方法时，才会被加载SingletonHolder类 ，并初始化。<br/>
 * 不过可以通过反射获取实例，故有了使用枚举的方法创建单例模式，即Singleton4类<br/>
 * 
 * @author wangzhu
 * @date 2014-10-31上午10:07:39
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
