package com.wangzhu.poi;

public class ConverterFactory {
	public static AbstractedConverter getConverter(String type) {
		AbstractedConverter converter = null;
		if ("doc".equals(type)) {
			converter = new WordConverter();
		} else if ("xls".equals(type)) {
			converter = new ExcelConverter();
		}
		return converter;
	}
}
