package com.wangzhu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理类，必须实现InvocationHandler接口<br/>
 * 
 * @author wangzhu
 * @date 2015-2-7下午3:49:04
 * 
 */
public class DynaProxyAnimal implements InvocationHandler {

    /**
     * 被代理的对象
     */
    private Object delegate;

    public DynaProxyAnimal() {
    }

    public DynaProxyAnimal(Object delegate) {
	this.delegate = delegate;
    }

    public Object bind(Object delegate) {
	Class<?> clazz = delegate.getClass();
	return this.bind(delegate, clazz);
    }

    private Object bind(Object delegate, Class<?> clazz) {

	this.delegate = delegate;
	return Proxy.newProxyInstance(clazz.getClassLoader(),
		clazz.getInterfaces(), this);
    }

    /**
     * proxy：代理的对象<br/>
     * method:表示真实主题要调用的执行方法<br/>
     * args:调用方法时所传递的参数<br/>
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
	    throws Throwable {
	Object ret = null;
	try {
	    // 执行原来的方法之前记录日志
	    AnimalLogger.start(method);

	    // JVM通过这条语句执行原来的方法（反射机制）
	    ret = method.invoke(delegate, args);

	    // 执行原来的方法之后记录日志
	    AnimalLogger.end(method);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	// 返回方法的返回值给调用者
	return ret;
    }

}
