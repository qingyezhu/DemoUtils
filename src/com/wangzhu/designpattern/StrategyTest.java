package com.wangzhu.designpattern;

import com.wangzhu.designpatter.strategy.CalcStrategy;
import com.wangzhu.designpatter.strategy.Calculator;
import com.wangzhu.designpatter.strategy.SubStrategy;

public class StrategyTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
	Calculator calculator1 = new Calculator(42, 35);
	System.out.println("default: " + calculator1.result());

	Calculator calculator2 = new Calculator(42, 35, new SubStrategy());
	System.out.println("sub: " + calculator2.result());
	Calculator calculator3 = new Calculator(42, 35, new CalcStrategy() {

	    @Override
	    public int calc(int x, int y) {
		return ((x + 10) - (y * 3)) / 2;
	    }
	});
	System.out.println("user-defined: " + calculator3.result());
    }

}
