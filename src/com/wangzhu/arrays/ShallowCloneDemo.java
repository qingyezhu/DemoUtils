package com.wangzhu.arrays;

import java.util.Arrays;

/**
 * 当是基本数据类型（包括String）时，是拷贝值，即深拷贝<br/>
 * 当是类类型时，则是拷贝引用，即浅拷贝
 * 
 * @author wangzhu
 * @date 2015-1-2下午10:19:51
 * 
 */
public class ShallowCloneDemo {

	public static void main(String[] args) {
		ShallowCloneDemo.initBalloon();
		ShallowCloneDemo.initStudent();
	}

	private static void initBalloon() {
		// 气球数量
		int ballonNum = 7;
		// 第一个箱子
		Balloon[] box1 = new Balloon[ballonNum];
		// 初始化第一个箱子中的气球
		for (int i = 0; i < ballonNum; i++) {
			box1[i] = new Balloon(i, Color.values()[i]);
		}

		// 第二个箱子的气球是拷贝的第一个箱子里的
		// 数组的浅拷贝

		// Balloon[] box2 = Arrays.copyOf(box1, ballonNum);

		// Balloon[] box2 = new Balloon[ballonNum];
		// System.arraycopy(box1, 0, box2, 0, ballonNum);

		Balloon[] box2 = box1.clone();
		// 修改最后一个气球颜色
		System.out.println("before: " + Arrays.asList(box2));
		ShallowCloneDemo.changeBox((Balloon) box2[6].clone());
		System.out.println("after: " + Arrays.asList(box2));
		// 打印出第一个箱子中的气球颜色
		for (Balloon balloon : box1) {
			System.out.println(balloon);
		}
	}

	private static void changeBox(Balloon balloon) {
		balloon.setColor(Color.Blue);
		System.out.println("change: " + balloon);
	}

	private static void initStudent() {
		int studentNum = 7;
		Student[] stuArr = new Student[studentNum];
		for (int i = 0; i < studentNum; i++) {
			stuArr[i] = new Student(i, "stu" + i);
		}
		Student[] tStuArr = Arrays.copyOf(stuArr, studentNum);
		System.out.println(Arrays.asList(stuArr));
		System.out.println(Arrays.asList(tStuArr));

		tStuArr[6].setName("new name");

		// ShallowCloneDemo.changeStudent((Student) tStuArr[6].clone());

		System.out.println(Arrays.asList(stuArr));
		System.out.println(Arrays.asList(tStuArr));

	}

	private static void changeStudent(Student student) {
		student.setName("new student");
		System.out.println(student);
	}

}

/**
 * 气球颜色
 * 
 * @author wangzhu
 * @date 2015-1-2下午9:43:02
 * 
 */
enum Color {
	Red, Orange, Yellow, Green, Indigo, Blue, Violet;
}

/**
 * 气球
 * 
 * @author wangzhu
 * @date 2015-1-2下午9:42:59
 * 
 */
class Balloon implements Cloneable {
	/**
	 * 编号
	 */
	private int id;
	/**
	 * 颜色
	 */
	private Color color;

	public Balloon(int _id, Color _color) {
		id = _id;
		color = _color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", color=" + color + "}";
	}

	@Override
	protected Object clone() {
		Balloon balloon = null;
		try {
			balloon = (Balloon) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return balloon;
	}
}

class Student implements Cloneable {
	private int id;
	private String name;

	public Student(int _id, String _name) {
		id = _id;
		name = _name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", name=" + name + "}";
	}

	@Override
	public Object clone() {
		Student student = null;
		try {
			student = (Student) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return student;
	}
}