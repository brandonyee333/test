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

package com.liferay.osb.asah.common.util;

import java.beans.PropertyDescriptor;

import java.lang.reflect.Method;

import java.sql.Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.core.ResolvableType;
import org.springframework.data.relational.core.mapping.Column;

/**
 * @author Inácio Nery
 */
public class BeanUtils {

	public static void copyProperties(
		Map<String, Object> source, Object target) {

		PropertyDescriptor[] targetPropertyDescriptors =
			org.springframework.beans.BeanUtils.getPropertyDescriptors(
				target.getClass());

		for (PropertyDescriptor targetPropertyDescriptor :
				targetPropertyDescriptors) {

			String targetPropertyName = _getPropertyName(
				targetPropertyDescriptor);

			Object value = source.get(targetPropertyName);

			if (value == null) {
				continue;
			}

			_setTargetPropertyValue(
				targetPropertyName, value, targetPropertyDescriptor, target);
		}
	}

	public static void copyProperties(Object source, Object target) {
		copyProperties(_toSourceMap(source), target);
	}

	private static String _getPropertyName(
		PropertyDescriptor propertyDescriptor) {

		String propertyName = propertyDescriptor.getName();

		Method propertyReadMethod = propertyDescriptor.getReadMethod();

		if (propertyReadMethod == null) {
			return propertyName.toLowerCase();
		}

		Column column = propertyReadMethod.getAnnotation(Column.class);

		if (column != null) {
			propertyName = column.value();
		}

		return propertyName.toLowerCase();
	}

	private static void _setTargetPropertyValue(
		String targetPropertyName, Object targetPropertyValue,
		PropertyDescriptor targetPropertyDescriptor, Object target) {

		Method targetPropertyWriteMethod =
			targetPropertyDescriptor.getWriteMethod();

		if (targetPropertyWriteMethod == null) {
			return;
		}

		ResolvableType targetPropertyResolvableType =
			ResolvableType.forMethodParameter(targetPropertyWriteMethod, 0);

		Class<?> targetPropertyClass =
			targetPropertyResolvableType.getRawClass();

		try {
			if ((targetPropertyClass != null) && targetPropertyClass.isEnum()) {
				targetPropertyWriteMethod.invoke(
					target,
					Enum.valueOf(
						(Class<Enum>)targetPropertyClass,
						targetPropertyValue.toString()));
			}
			else {
				if (targetPropertyValue instanceof Array) {
					Array array = (Array)targetPropertyValue;

					targetPropertyValue = new LinkedHashSet<>(
						Arrays.asList((Long[])array.getArray()));
				}

				targetPropertyWriteMethod.invoke(target, targetPropertyValue);
			}
		}
		catch (Exception e) {
			_log.error("Unable to write property " + targetPropertyName, e);
		}
	}

	private static Map<String, Object> _toSourceMap(Object source) {
		Map<String, Object> sourceMap = new HashMap<>();

		PropertyDescriptor[] sourcePropertyDescriptors =
			org.springframework.beans.BeanUtils.getPropertyDescriptors(
				source.getClass());

		for (PropertyDescriptor sourcePropertyDescriptor :
				sourcePropertyDescriptors) {

			Method sourcePropertyReadMethod =
				sourcePropertyDescriptor.getReadMethod();

			if (sourcePropertyReadMethod == null) {
				continue;
			}

			String sourcePropertyName = _getPropertyName(
				sourcePropertyDescriptor);

			try {
				Object value = sourcePropertyReadMethod.invoke(source);

				if (value == null) {
					continue;
				}

				sourceMap.put(sourcePropertyName, value);
			}
			catch (Exception e) {
				_log.error("Unable to read property " + sourcePropertyName, e);
			}
		}

		return sourceMap;
	}

	private static final Log _log = LogFactory.getLog(BeanUtils.class);

}