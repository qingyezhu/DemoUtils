package com.wangzhu.list;

import java.util.ArrayList;

/**
 * ArrayList中只填加不重复的
 * 
 * @author wangzhu
 * @date 2014-10-22上午8:48:39
 * 
 */
public class DemoArrayList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new DemoArrayList().add();
	}

	private void add() {
		ArrayList<Node> nodeList = new ArrayList<DemoArrayList.Node>();
		nodeList.add(new Node(1, "name1"));
		nodeList.add(new Node(1, "name1"));
		nodeList.add(new Node(1, "name1"));
		nodeList.add(new Node(1, "name1"));
		System.out.println(nodeList);
	}

	class Node {
		int age;
		String name;

		public Node() {

		}

		public Node(int age, String name) {
			this.age = age;
			this.name = name;
		}

		@Override
		public String toString() {
			return "{age=" + this.age + ", name=" + this.name + "}";
		}

	}
}
