package com.wangzhu.designpatter.strategy;

/**
 * 程序的结构，里面约束了整个程序的框架和执行的大概流程，但未涉及到业务层面的东西，<br/>
 * 只是将一个数据如何流入如何流出做了规范，只是提供了一个默认的逻辑实现<br/>
 * 
 * @author wangzhu
 * @date 2015-4-3上午11:28:36
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
     * 只需关注接口，并将接口用到的传入参数传递进去即可，并不关心到底具体是要如何进行业务封装<br/>
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
