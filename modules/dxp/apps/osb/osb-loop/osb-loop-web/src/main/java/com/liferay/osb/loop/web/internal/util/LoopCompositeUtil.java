/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.loop.web.internal.util;

import com.liferay.osb.loop.web.internal.composite.BaseLoopComposite;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.TextFormatter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Timothy Bell
 */
public class LoopCompositeUtil {

	public static <T> T getComposite(
			Class<T> compositeClass, Class<?>[] parameterTypes,
			Object[] parameters)
		throws Exception {

		Constructor<T> constructor = compositeClass.getConstructor(
			parameterTypes);

		return constructor.newInstance(parameters);
	}

	public static <T> List<T> getComposites(
			List<?> objects, Class<T> compositeClass, Class<?>[] parameterTypes,
			Object[] parameters)
		throws Exception {

		return getComposites(
			objects, compositeClass, parameterTypes, parameters, true);
	}

	public static <T> List<T> getComposites(
			List<?> objects, Class<T> compositeClass, Class<?>[] parameterTypes,
			Object[] parameters, boolean sort)
		throws Exception {

		List<T> composites = new ArrayList<>();

		Object[] newParameters = new Object[parameters.length + 1];

		System.arraycopy(parameters, 0, newParameters, 1, parameters.length);

		for (Object object : objects) {
			newParameters[0] = object;

			composites.add(
				getComposite(compositeClass, parameterTypes, newParameters));
		}

		if (sort) {
			ListUtil.sort(composites);
		}

		return composites;
	}

	public static <T> JSONArray getCompositesJSONArray(
			List<?> objects, Class<T> compositeClass, Class<?>[] parameterTypes,
			Object[] parameters)
		throws Exception {

		return getCompositesJSONArray(
			objects, compositeClass, parameterTypes, parameters, true);
	}

	public static <T> JSONArray getCompositesJSONArray(
			List<?> objects, Class<T> compositeClass, Class<?>[] parameterTypes,
			Object[] parameters, boolean sort)
		throws Exception {

		List<T> composites = getComposites(
			objects, compositeClass, parameterTypes, parameters, sort);

		return getCompositesJSONArray(composites);
	}

	public static <T> JSONArray getCompositesJSONArray(List<T> composites)
		throws Exception {

		return getCompositesJSONArray(composites, new String[0], true);
	}

	public static <T> JSONArray getCompositesJSONArray(
			List<T> composites, String[] fields, boolean includeBaseFields)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (T composite : composites) {
			Method method = ReflectionUtil.getDeclaredMethod(
				BaseLoopComposite.class, "getJSONObject", String[].class,
				boolean.class);

			JSONObject jsonObject = (JSONObject)method.invoke(
				composite, new Object[] {fields, includeBaseFields});

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public static <T> void updateJSONObject(
			Object object, Class<T> clazz, String[] fields,
			JSONObject jsonObject)
		throws Exception {

		for (String field : fields) {
			Method method = null;

			try {
				method = clazz.getMethod(
					"get" + TextFormatter.format(field, TextFormatter.G));
			}
			catch (Exception e) {
				method = clazz.getMethod(
					"is" + TextFormatter.format(field, TextFormatter.G));
			}

			Object value = method.invoke(object);

			if (value instanceof Boolean) {
				jsonObject.put(field, (Boolean)value);
			}
			else if (value instanceof Date) {
				jsonObject.put(field, (Date)value);
			}
			else if (value instanceof Double) {
				jsonObject.put(field, (Double)value);
			}
			else if (value instanceof Integer) {
				jsonObject.put(field, (Integer)value);
			}
			else if (value instanceof JSONArray) {
				jsonObject.put(field, (JSONArray)value);
			}
			else if (value instanceof JSONObject) {
				jsonObject.put(field, (JSONObject)value);
			}
			else if (value instanceof Long) {
				jsonObject.put(field, (Long)value);
			}
			else if (value instanceof Short) {
				jsonObject.put(field, (Short)value);
			}
			else {
				jsonObject.put(field, String.valueOf(value));
			}
		}
	}

}