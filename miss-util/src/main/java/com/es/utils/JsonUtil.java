package com.es.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Timestamp;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

public class JsonUtil {

	/**
	 * JSONObject����תJavaBean
	 * 
	 * @param JSONObject
	 * @param JavaBean��class
	 * @return ת��������쳣����·���null��
	 */
	public static Object jsonToBean(JSONObject json, Class<?> cls) {
		Object obj = null;

		try {
			obj = cls.newInstance();

			// ȡ��Bean��������з���
			Method[] methods = cls.getMethods();
			for (int i = 0; i < methods.length; i++) {
				// ȡ��������
				String methodName = methods[i].getName();
				// ȡ������������
				Class<?>[] clss = methods[i].getParameterTypes();
				if (clss.length != 1) {
					continue;
				}

				// ���Ƿ�����������set��ʼ�����˳�����ѭ��
				if (methodName.indexOf("set") < 0) {
					continue;
				}

				// ����
				String type = clss[0].getSimpleName();

				String key = methodName.substring(3, 4).toLowerCase()
						+ methodName.substring(4);

				// ���map���и�key
				if (json.has(key) && json.get(key) != null) {
					setValue(type, json.get(key), methods[i], obj);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return obj;
	}

	/**
	 * ����key��JSONObject������ȡ�ö�Ӧֵ
	 * 
	 * @param json
	 * @param key
	 * @return
	 * @throws JSONException
	 */
	public static String getString(JSONObject json, String key)
			throws JSONException {
		String retVal = null;
		if (json.isNull(key)) {
			retVal = "";
		} else {
			retVal = json.getString(key);
		}
		return retVal;
	}

	/**
	 * ��JavaBean��ÿ��������ֵ
	 * 
	 * @param type
	 * @param value
	 * @param method
	 * @param bean
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ParseException
	 */
	private static void setValue(String type, Object value, Method method,
			Object bean) {
		if (value != null && !"".equals(value)) {
			try {
				if ("String".equals(type)) {
					method.invoke(bean, new Object[] { value });
				} else if ("int".equals(type) || "Integer".equals(type)) {
					method.invoke(bean,
							new Object[] { new Integer("" + value) });
				} else if ("double".equals(type) || "Double".equals(type)) {
					method.invoke(bean, new Object[] { new Double("" + value) });
				} else if ("float".equals(type) || "Float".equals(type)) {
					method.invoke(bean, new Object[] { new Float("" + value) });
				} else if ("long".equals(type) || "Long".equals(type)) {
					method.invoke(bean, new Object[] { new Long("" + value) });
				} else if ("int".equals(type) || "Integer".equals(type)) {
					method.invoke(bean,
							new Object[] { new Integer("" + value) });
				} else if ("boolean".equals(type) || "Boolean".equals(type)) {
					method.invoke(bean,
							new Object[] { Boolean.valueOf("" + value) });
				} else if ("BigDecimal".equals(type)) {
					method.invoke(bean, new Object[] { new BigDecimal(""
							+ value) });
				} else if ("Date".equals(type)) {
					Class<?> dateType = method.getParameterTypes()[0];
					if ("java.util.Date".equals(dateType.getName())) {
						java.util.Date date = null;
						if ("String".equals(value.getClass().getSimpleName())) {
							String time = String.valueOf(value);
							String format = null;
							if (time.indexOf(":") > 0) {
								if (time.indexOf(":") == time.lastIndexOf(":")) {
									format = "yyyy-MM-dd H:mm";
								} else {
									format = "yyyy-MM-dd H:mm:ss";
								}
							} else {
								format = "yyyy-MM-dd";
							}
							SimpleDateFormat sf = new SimpleDateFormat();
							sf.applyPattern(format);
							date = sf.parse(time);
						} else {
							date = (java.util.Date) value;
						}

						if (date != null) {
							method.invoke(bean, new Object[] { date });
						}
					} else if ("java.sql.Date".equals(dateType.getName())) {
						Date date = null;
						if ("String".equals(value.getClass().getSimpleName())) {
							String time = String.valueOf(value);
							String format = null;
							if (time.indexOf(":") > 0) {
								if (time.indexOf(":") == time.lastIndexOf(":")) {
									format = "yyyy-MM-dd H:mm";
								} else {
									format = "yyyy-MM-dd H:mm:ss";
								}
							} else {
								format = "yyyy-MM-dd";
							}
							SimpleDateFormat sf = new SimpleDateFormat();
							sf.applyPattern(format);
							date = new Date(sf.parse(time).getTime());
						} else {
							date = (Date) value;
						}

						if (date != null) {
							method.invoke(bean, new Object[] { date });
						}
					}
				} else if ("Timestamp".equals(type)) {
					Timestamp timestamp = null;
					if ("String".equals(value.getClass().getSimpleName())) {
						String time = String.valueOf(value);
						String format = null;
						if (time.indexOf(":") > 0) {
							if (time.indexOf(":") == time.lastIndexOf(":")) {
								format = "yyyy-MM-dd H:mm";
							} else {
								format = "yyyy-MM-dd H:mm:ss";
							}
						} else {
							format = "yyyy-MM-dd";
						}
						SimpleDateFormat sf = new SimpleDateFormat();
						sf.applyPattern(format);
						timestamp = new Timestamp(sf.parse(time).getTime());
					} else {
						timestamp = (Timestamp) value;
					}

					if (timestamp != null) {
						method.invoke(bean, new Object[] { timestamp });
					}
				} else if ("byte[]".equals(type)) {
					method.invoke(bean,
							new Object[] { new String("" + value).getBytes() });
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * ��Modelת����JSONObject
	 */
	public static JSONObject coverModelToJSONObject(Object o) throws Exception {
		JSONObject json = new JSONObject();
		Class<? extends Object> clazz = o.getClass();
		Class<? extends Object> superclazz = clazz.getSuperclass();
		
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			json.put(f.getName(), invokeMethod(clazz, f.getName(), o));
		}
		
		//�������Ը�ֵ
		if(superclazz!=null){
			Field[] fields2 = superclazz.getDeclaredFields();
			for (int i = 0; i < fields2.length; i++) {
				Field f = fields2[i];
				json.put(f.getName(), invokeMethod(superclazz, f.getName(), o));
			}
		}
		
		return json;	
	}

	/**
	 * ��listת����JSONArray
	 */
	public static JSONArray coverModelToJSONArray(List<?> list)
			throws Exception {
		JSONArray array = null;
		if (list.isEmpty()) {
			return array;
		}
		array = new JSONArray();
		
		for (Object o : list) {
			array.put(coverModelToJSONObject(o));
		}
		return array;
	}

	private static Object invokeMethod(Class<? extends Object> c,
			String fieldName, Object o) {
		String methodName = fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		Method method = null;
		try {
			method = c.getMethod("get" + methodName);
			return method.invoke(o);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
