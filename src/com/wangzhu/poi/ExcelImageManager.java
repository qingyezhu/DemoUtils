package com.wangzhu.poi;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFShape;

public interface ExcelImageManager {
	List<String> getImagePath(List<HSSFShape> children);
}
