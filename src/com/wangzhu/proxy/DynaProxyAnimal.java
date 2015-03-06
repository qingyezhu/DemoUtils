package com.wangzhu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ��̬�����࣬����ʵ��InvocationHandler�ӿ�<br/>
 * 
 * @author wangzhu
 * @date 2015-2-7����3:49:04
 * 
 */
public class DynaProxyAnimal implements InvocationHandler {

    /**
     * ������Ķ���
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
     * proxy������Ķ���<br/>
     * method:��ʾ��ʵ����Ҫ���õ�ִ�з���<br/>
     * args:���÷���ʱ�����ݵĲ���<br/>
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
	    throws Throwable {
	Object ret = null;
	try {
	    // ִ��ԭ���ķ���֮ǰ��¼��־
	    AnimalLogger.start(method);

	    // JVMͨ���������ִ��ԭ���ķ�����������ƣ�
	    ret = method.invoke(delegate, args);

	    // ִ��ԭ���ķ���֮���¼��־
	    AnimalLogger.end(method);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	// ���ط����ķ���ֵ��������
	return ret;
    }

}
