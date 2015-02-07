package com.wangzhu.arrays;

import java.util.Arrays;

/**
 * ���ǻ����������ͣ�����String��ʱ���ǿ���ֵ�������<br/>
 * ����������ʱ�����ǿ������ã���ǳ����
 * 
 * @author wangzhu
 * @date 2015-1-2����10:19:51
 * 
 */
public class ShallowCloneDemo {

	public static void main(String[] args) {
		ShallowCloneDemo.initBalloon();
		ShallowCloneDemo.initStudent();
	}

	private static void initBalloon() {
		// ��������
		int ballonNum = 7;
		// ��һ������
		Balloon[] box1 = new Balloon[ballonNum];
		// ��ʼ����һ�������е�����
		for (int i = 0; i < ballonNum; i++) {
			box1[i] = new Balloon(i, Color.values()[i]);
		}

		// �ڶ������ӵ������ǿ����ĵ�һ���������
		// �����ǳ����

		// Balloon[] box2 = Arrays.copyOf(box1, ballonNum);

		// Balloon[] box2 = new Balloon[ballonNum];
		// System.arraycopy(box1, 0, box2, 0, ballonNum);

		Balloon[] box2 = box1.clone();
		// �޸����һ��������ɫ
		System.out.println("before: " + Arrays.asList(box2));
		ShallowCloneDemo.changeBox((Balloon) box2[6].clone());
		System.out.println("after: " + Arrays.asList(box2));
		// ��ӡ����һ�������е�������ɫ
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
 * ������ɫ
 * 
 * @author wangzhu
 * @date 2015-1-2����9:43:02
 * 
 */
enum Color {
	Red, Orange, Yellow, Green, Indigo, Blue, Violet;
}

/**
 * ����
 * 
 * @author wangzhu
 * @date 2015-1-2����9:42:59
 * 
 */
class Balloon implements Cloneable {
	/**
	 * ���
	 */
	private int id;
	/**
	 * ��ɫ
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