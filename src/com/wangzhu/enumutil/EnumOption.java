package com.wangzhu.enumutil;

/**
 * Ϊö���ඨ��һ�����󷽷���<br/>
 * ������󷽷��ɲ�ͬ��ö��ֵ�ṩ��ͬ��ʵ��
 * 
 * @author wangzhu
 * @date 2014-9-15����5:26:20
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
