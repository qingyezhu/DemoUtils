package com.wangzhu.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

import com.wangzhu.string.StringUtils;

public class BeanFactory {
    private static BeanFactory instance = new BeanFactory();

    /**
     * 是否已初始化
     */
    private boolean isInitialized = false;

    /**
     * 存放Bean
     */
    private Map<String, Object> beanMap = new HashMap<String, Object>();

    /**
     * 记录依赖关系
     */
    private Map<String, String> dependenceMap = new HashMap<String, String>();

    private BeanFactory() {
    }

    public static BeanFactory getInstance() {
	return instance;
    }

    public Object getBean(String beanName) {
	return beanMap.get(beanName);
    }

    public void init(Set<Class<?>> clazzs) {
	if (isInitialized || (clazzs == null) || (clazzs.size() == 0)) {
	    return;
	}
	this.initBeanAndDependence(clazzs);

	this.injectBean();

	isInitialized = true;
    }

    /**
     * 扫描注解，创建对象，并记录依赖关系
     * 
     * @param clazzs
     */
    private void initBeanAndDependence(Set<Class<?>> clazzs) {
	Iterator<Class<?>> iterator = clazzs.iterator();

	while (iterator.hasNext()) {
	    Class<?> item = iterator.next();
	    Bean bean = item.getAnnotation(Bean.class);
	    if (bean != null) {
		String beanName = bean.value();
		try {
		    beanMap.put(beanName, item.newInstance());
		} catch (InstantiationException e) {
		    e.printStackTrace();
		} catch (IllegalAccessException e) {
		    e.printStackTrace();
		}

		Field[] fields = item.getDeclaredFields();
		for (Field field : fields) {
		    Resource resource = field.getAnnotation(Resource.class);
		    if (resource != null) {
			String resourceName = resource.value();
			String fieldName = field.getName();
			if (StringUtils.isEmpty(resourceName)) {
			    resourceName = fieldName;
			}
			dependenceMap.put(beanName + "." + fieldName,
				resourceName);
		    }
		}
	    }
	}
    }

    private void injectBean() {
	Iterator<Map.Entry<String, String>> iterator = dependenceMap.entrySet()
		.iterator();
	while (iterator.hasNext()) {
	    Map.Entry<String, String> item = iterator.next();
	    String key = item.getKey();
	    String value = item.getValue();
	    String[] arr = key.split("\\.");
	    try {
		PropertyUtils.setProperty(beanMap.get(arr[0]), arr[1],
			beanMap.get(value));
	    } catch (IllegalAccessException e) {
		e.printStackTrace();
	    } catch (InvocationTargetException e) {
		e.printStackTrace();
	    } catch (NoSuchMethodException e) {
		e.printStackTrace();
	    }
	}

    }
}
