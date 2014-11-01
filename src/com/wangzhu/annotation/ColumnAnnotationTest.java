package com.wangzhu.annotation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 通过类的实例使用反射与注解形成插入或更新Sql语句
 * 
 * @author wangzhu
 * @date 2014-11-1下午4:25:31
 * 
 */
public class ColumnAnnotationTest {

	public static void main(String[] args) throws Exception {
		ColumnAnnotationTest annotationTest = new ColumnAnnotationTest();
		annotationTest.reflect();
	}

	private void reflect() throws Exception {
		Student stu = new Student();
		this.reflectSaveOrUpdate(stu);
		stu = new Student(1, "Zhangsan", 12, "Welcome to the world!", false);
		this.reflectSaveOrUpdate(stu);
	}

	private static final Class<Column> columnClazz = Column.class;
	private static final Class<ID> idClazz = ID.class;

	private <T> void reflectSave(T t) throws Exception {
		Class<? extends Object> clazz = t.getClass();
		StringBuffer prefixAccum = new StringBuffer();
		prefixAccum.append("INSERT INTO ").append(clazz.getSimpleName())
				.append("(");
		StringBuffer suffixAccum = new StringBuffer();
		for (Method method : clazz.getMethods()) {
			if (method.isAnnotationPresent(ColumnAnnotationTest.columnClazz)) {
				Column column = method
						.getAnnotation(ColumnAnnotationTest.columnClazz);
				if (suffixAccum.length() > 0) {
					suffixAccum.append(",");
					prefixAccum.append(",");
				}
				prefixAccum.append(column.name());
				Class<?> returnTypeClazz = method.getReturnType();
				if (returnTypeClazz.getSimpleName().equals("void")) {
					throw new Exception("类型出错");
				}
				Object obj = method.invoke(t);
				if (returnTypeClazz.getSimpleName().equals(
						returnTypeClazz.getName())) {
					suffixAccum.append(obj);
				} else {
					suffixAccum.append("'").append(obj).append("'");
				}
			}
		}
		if (suffixAccum.length() > 0) {
			prefixAccum.append(") VALUES(").append(suffixAccum).append(")");
		}
		System.out.println(prefixAccum);
	}

	private <T> void reflectSaveOrUpdate(T t) throws Exception {
		Class<? extends Object> clazz = t.getClass();
		String clazzName = clazz.getSimpleName().toUpperCase();
		Map<String, Object> columnMap = new HashMap<String, Object>();
		Map<String, Object> idMap = new HashMap<String, Object>();
		Object idKey = null;
		for (Method method : clazz.getMethods()) {
			if (method.isAnnotationPresent(ColumnAnnotationTest.columnClazz)) {
				Column column = method
						.getAnnotation(ColumnAnnotationTest.columnClazz);
				String key = column.name();
				Object value = method.invoke(t);
				Class<?> returnTypeClazz = method.getReturnType();
				if (!returnTypeClazz.getName().equals(
						returnTypeClazz.getSimpleName())) {
					value = "'" + value + "'";
				}
				if (method.isAnnotationPresent(ColumnAnnotationTest.idClazz)) {
					idMap.put(key, value);
					idKey = value;
				} else {
					columnMap.put(key, value);
				}
			}
		}
		StringBuffer accum = new StringBuffer();
		if (null == idKey) {
			// insert
			StringBuffer prefixAccum = new StringBuffer();
			StringBuffer suffixAccum = new StringBuffer();
			// this.getInsertFromEntry(idMap, prefixAccum, suffixAccum);
			this.getInsertFromEntry(columnMap, prefixAccum, suffixAccum);
			accum.append("INSERT INTO ").append(clazzName);
			accum.append(" (").append(prefixAccum).append(")");
			accum.append(" VALUES(").append(suffixAccum).append(")");

		} else {
			// update
			accum.append("UPDATE ").append(clazzName).append(" SET ");
			this.getUpdateFromEntry(columnMap, accum);
			accum.append(" WHERE ");
			this.getUpdateFromEntry(idMap, accum);
		}
		System.out.println(accum);
	}

	private void getUpdateFromEntry(Map<String, Object> map, StringBuffer accum) {
		boolean flag = false;
		for (Entry<String, Object> entry : map.entrySet()) {
			if (flag) {
				accum.append(",");
			}
			flag = true;
			accum.append(entry.getKey()).append("=").append(entry.getValue());
		}
	}

	private void getInsertFromEntry(Map<String, Object> map,
			StringBuffer prefixAccum, StringBuffer suffixAccum) {
		for (Entry<String, Object> entry : map.entrySet()) {
			if (prefixAccum.length() > 0) {
				prefixAccum.append(",");
				suffixAccum.append(",");
			}
			prefixAccum.append(entry.getKey());
			suffixAccum.append(entry.getValue());
		}
	}
}

class Student {
	private Integer id;
	private String name;
	private Integer age;
	private String desc;
	private Boolean flag;

	public Student() {
		super();
	}

	public Student(Integer id, String name, Integer age, String desc,
			Boolean flag) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.desc = desc;
		this.flag = flag;
	}

	@ID
	@Column(name = "ID")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "AGE")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "DESC")
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "FLAG")
	public Boolean getFlag() {
		return this.flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
}