package com.wangzhu.designpatter.strategy;

/**
 * ����Ľṹ������Լ������������Ŀ�ܺ�ִ�еĴ�����̣���δ�漰��ҵ�����Ķ�����<br/>
 * ֻ�ǽ�һ�����������������������˹淶��ֻ���ṩ��һ��Ĭ�ϵ��߼�ʵ��<br/>
 * 
 * @author wangzhu
 * @date 2015-4-3����11:28:36
 * 
 */
public class Calculator {
    private int x = 0;
    private int y = 0;
    private CalcStrategy strategy;

    public Calculator(int x, int y) {
	this.x = x;
	this.y = y;
    }

    public Calculator(int x, int y, CalcStrategy strategy) {
	this(x, y);
	this.strategy = strategy;
    }

    private int calc(int x, int y) {
	return x + y;
    }

    /**
     * ֻ���ע�ӿڣ������ӿ��õ��Ĵ���������ݽ�ȥ���ɣ��������ĵ��׾�����Ҫ��ν���ҵ���װ<br/>
     * 
     * @return
     */
    public int result() {
	if (strategy != null) {
	    return strategy.calc(x, y);
	}
	return this.calc(x, y);
    }
}
