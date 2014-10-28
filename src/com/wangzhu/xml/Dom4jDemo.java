package com.wangzhu.xml;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * 使用dom4j解析Xml文件
 * 
 * @author wangzhu
 * @date 2014-10-28下午4:28:19
 * 
 */
public class Dom4jDemo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Document root = SAXReaderFactory.INSTANCE
				.getRootElement("jdbcDom4j.xml");
		Node node = root
				.selectSingleNode("//config[name='driverClassName']/value");
		System.out.println(node.getText());
	}
}

/**
 * 使用枚举实现单例模式
 * 
 * @author wangzhu
 * @date 2014-10-28下午4:48:23
 * 
 */
enum SAXReaderFactory {
	INSTANCE;
	public Document getRootElement(String filePath) throws Exception {
		Document root = null;
		SAXReader saxReader = new SAXReader();
		root = saxReader.read(SAXReaderFactory.class
				.getResourceAsStream(filePath));
		return root;
	}

}
