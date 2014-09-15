package com.wangzhu.enumutil;

/**
 * 为枚举类定义一个抽象方法，<br/>
 * 这个抽象方法由不同的枚举值提供不同的实现
 * 
 * @author wangzhu
 * @date 2014-9-15下午5:26:20
 * 
 */
public enum EnumOption {

	PLUS {
		@Override
		public double eval(double x, double y) {
			return x + y;
		}
	},
	MINUS {
		@Override
		public double eval(double x, double y) {
			return x - y;
		}
	},
	TIMES {
		@Override
		public double eval(double x, double y) {
			return x * y;
		}
	},
	DIVIDE {
		@Override
		public double eval(double x, double y) {
			return x / y;
		}
	};
	public abstract double eval(double x, double y);
}
