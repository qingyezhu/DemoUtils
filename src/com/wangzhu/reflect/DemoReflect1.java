package com.wangzhu.reflect;

import java.lang.reflect.Field;

public class DemoReflect1 {

	public static void main(String[] args) throws Exception {
		DemoReflect1 reflect1 = new DemoReflect1();
		reflect1.reflectPerson();
		reflect1.reflectAnimal();
	}

	private void reflectPerson() throws Exception {
		Person person = new Person();
		this.reflect(person);
		this.saveSql(person);
		person = new Person("Lili", 12);
		this.reflect(person);
		this.saveSql(person);
	}

	private void reflectPerson(Person person) throws Exception {
		Class<?> clazz = person.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			// 取消访问权限检查
			field.setAccessible(true);
			System.out.println(field.getName() + "===" + field.get(person));
		}
	}

	private void reflectAnimal() throws Exception {
		Animal animal = new Animal();
		this.reflect(animal);
		this.saveSql(animal);
		animal = new Animal(12, "这是一只猫");
		this.reflect(animal);
		this.saveSql(animal);

	}

	private void reflectAnimal(Animal animal) throws Exception {
		Class<?> clazz = animal.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			System.out.println(field.getName() + "==" + field.get(animal));
		}
	}

	private <T> void reflect(T t) throws Exception {
		Class<? extends Object> clazz = t.getClass();
		System.out.println(clazz.getSimpleName());
		for (Field field : clazz.getDeclaredFields()) {
			// 取消访问权限检查
			field.setAccessible(true);
			StringBuffer accum = new StringBuffer();
			accum.append(field.getName()).append("=");
			accum.append(field.get(t));
			System.out.println(accum);
		}
	}

	private <T> void saveSql(T t) throws Exception {
		Class<? extends Object> clazz = t.getClass();
		StringBuffer prefixAccum = new StringBuffer();
		prefixAccum.append("INSERT INTO ").append(clazz.getSimpleName())
				.append("(");
		StringBuffer suffixAccum = new StringBuffer();
		for (Field field : clazz.getDeclaredFields()) {
			// 取消访问权限检查
			field.setAccessible(true);
			if (suffixAccum.length() > 0) {
				prefixAccum.append(",");
				suffixAccum.append(",");
			}
			prefixAccum.append(field.getName());
			Object obj = field.get(t);

			Class<?> typeClazz = field.getType();
			if (!typeClazz.getSimpleName().equals(typeClazz.getName())) {
				suffixAccum.append("'").append(obj).append("'");
			} else {
				suffixAccum.append(obj);
			}
		}
		if (suffixAccum.length() > 0) {
			prefixAccum.append(")").append(" VALUES ( ").append(suffixAccum)
					.append(")");
		}
		System.out.println(prefixAccum);
	}
}

class Person {
	private String name;
	private Integer age;

	public Person() {
		super();
	}

	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}

class Animal {
	private Integer id;
	private String desc;

	public Animal() {
		super();
	}

	public Animal(Integer id, String desc) {
		super();
		this.id = id;
		this.desc = desc;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}