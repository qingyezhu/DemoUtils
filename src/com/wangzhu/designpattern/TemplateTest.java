package com.wangzhu.designpattern;

import com.wangzhu.designpattern.template.Diagram;
import com.wangzhu.designpattern.template.StarDiagram;

/**
 * 模版方法【Template】：父类实现算法，子类实现细节<br/>
 * 为什么实现模版的抽象方法都是protected的呢？<br/>
 * 因为我们不想让调用者关注到我们实现的细节，这也是面向对象思想封装的一个体现；<br/>
 * 为什么display方法是不可继承的呢？<br/>
 * 因为算法一旦确定就允许更改，更改也只允许算法的所有者也就是他的主任更改，<br/>
 * 如果调用者都可通过继承进行修改，那么算法将没有严谨性可言；<br/>
 * 
 * @author wangzhu
 * @date 2015-4-3上午10:38:56
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
