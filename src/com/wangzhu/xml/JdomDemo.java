package com.wangzhu.xml;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

/**
 * ʹ��jdom����Xml�ļ�
 * 
 * @author wangzhu
 * @date 2014-10-28����4:28:42
 * 
 */
public class JdomDemo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Document root = SAXBuilderFactory.INSTANCE
				.getRootElement("jdbcJdom.xml");
		List configList = XPath.selectNodes(root.getRootElement(), "config");
		for (int i = 0, size = configList.size(); i < size; i++) {
			Element element = (Element) configList.get(i);
			System.out.println(element.getAttributeValue("name") + "="
					+ element.getAttributeValue("value"));
		}

		Element element = (Element) XPath.selectSingleNode(root,
				"JdbcConfig/config[@name='driverClassName']");
		System.out.println(element.getAttributeValue("value"));

	}

}

/**
 * ʹ��ö��ʵ�ֵ���ģʽ
 * 
 * @author wangzhu
 * @date 2014-10-28����4:52:24
 * 
 */
enum SAXBuilderFactory {
	INSTANCE;

	public Document getRootElement(String filePath) throws Exception {
		Document document = null;
		SAXBuilder saxBuilder = new SAXBuilder();
		document = saxBuilder.build(SAXBuilderFactory.class
				.getResourceAsStream(filePath));
		return document;
	}
}
