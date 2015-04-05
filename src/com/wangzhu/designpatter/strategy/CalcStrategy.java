package com.wangzhu.designpatter.strategy;

/**
 * 策略接口，主要是规范或者让结构程序知道如何进行调用<br/>
 * 
 * @author wangzhu
 * @date 2015-4-3上午11:27:55
 * 
 */
public interface CalcStrategy {
    int calc(int x, int y);
}
