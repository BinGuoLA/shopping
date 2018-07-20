package com.lanqiao.shopping.utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

public class MyBeanUtils {

	public static void populate(Object obj, Map<String, String[]> map) {
		try {
			// 由于BeanUtils将字符串"1992-3-3"向user对象的setBithday();方法传�?�参数有问题,手动向BeanUtils注册�?个时间类型转换器
			// 1_创建时间类型的转换器
			DateConverter dt = new DateConverter();
			// 2_设置转换的格�?
			dt.setPattern("yyyy-MM-dd");
			// 3_注册转换�?
			ConvertUtils.register(dt, java.util.Date.class);
			
			BeanUtils.populate(obj, map);//实现数据复制拷贝操作,要求：表单中name的�?�要与javaBean中的属�?�名同名
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static<T> T  populate(Class<T> clazz, Map<String, String[]> map) {
		try {
			
			T obj=clazz.newInstance();
			
			// 由于BeanUtils将字符串"1992-3-3"向user对象的setBithday();方法传�?�参数有问题,手动向BeanUtils注册�?个时间类型转换器
			// 1_创建时间类型的转换器
			DateConverter dt = new DateConverter();
			// 2_设置转换的格�?
			dt.setPattern("yyyy-MM-dd");
			// 3_注册转换�?
		    ConvertUtils.register(dt, java.util.Date.class);  //页面传�?�到servlet日期类型是String,处理后的日期类型为util.Date —�?�sql.Date
			
			BeanUtils.populate(obj, map);
			
			return obj;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}
	
}
