package com.wangzhu.designpatter.strategy;

public class AddStrategy implements CalcStrategy {

    @Override
    public int calc(int x, int y) {
	return x + y;
    }

}
