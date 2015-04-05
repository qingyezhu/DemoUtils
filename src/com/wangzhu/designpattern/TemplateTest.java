package com.wangzhu.designpattern;

import com.wangzhu.designpattern.template.Diagram;
import com.wangzhu.designpattern.template.StarDiagram;

/**
 * ģ�淽����Template��������ʵ���㷨������ʵ��ϸ��<br/>
 * Ϊʲôʵ��ģ��ĳ��󷽷�����protected���أ�<br/>
 * ��Ϊ���ǲ����õ����߹�ע������ʵ�ֵ�ϸ�ڣ���Ҳ���������˼���װ��һ�����֣�<br/>
 * Ϊʲôdisplay�����ǲ��ɼ̳е��أ�<br/>
 * ��Ϊ�㷨һ��ȷ����������ģ�����Ҳֻ�����㷨��������Ҳ�����������θ��ģ�<br/>
 * ��������߶���ͨ���̳н����޸ģ���ô�㷨��û���Ͻ��Կ��ԣ�<br/>
 * 
 * @author wangzhu
 * @date 2015-4-3����10:38:56
 * 
 */
public class TemplateTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
	Diagram diagram = new StarDiagram('*');
	diagram.display("qingyezhu");
    }

}
