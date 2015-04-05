package com.wangzhu.designpatter.strategy;

public class SubStrategy implements CalcStrategy {

    @Override
    public int calc(int x, int y) {
	return x - y;
    }
}