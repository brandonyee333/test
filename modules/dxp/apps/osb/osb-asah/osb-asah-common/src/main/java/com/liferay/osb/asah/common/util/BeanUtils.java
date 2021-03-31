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

			Method targetPropertyReadMethod =
				targetPropertyDescriptor.getReadMethod();

			if (targetPropertyReadMethod == null) {
				continue;
			}

			String targetPropertyName = targetPropertyDescriptor.getName();

			Column column = targetPropertyReadMethod.getAnnotation(
				Column.class);

			if (column != null) {
				targetPropertyName = column.value();
			}

			Object value = source.get(targetPropertyName.toLowerCase());

			if (value == null) {
				continue;
			}

			Method targetPropertyWriteMethod =
				targetPropertyDescriptor.getWriteMethod();

			ResolvableType targetPropertyResolvableType =
				ResolvableType.forMethodParameter(targetPropertyWriteMethod, 0);

			Class<?> targetPropertyClass =
				targetPropertyResolvableType.getRawClass();

			try {
				if (targetPropertyClass.isEnum()) {
					targetPropertyWriteMethod.invoke(
						target,
						Enum.valueOf(
							(Class<Enum>)targetPropertyClass,
							value.toString()));
				}
				else {
					if (value instanceof Array) {
						Array array = (Array)value;

						value = new LinkedHashSet<>(
							Arrays.asList((Long[])array.getArray()));
					}

					targetPropertyWriteMethod.invoke(target, value);
				}
			}
			catch (Exception e) {
				_log.error("Unable to write property " + targetPropertyName, e);
			}
		}
	}

	private static final Log _log = LogFactory.getLog(BeanUtils.class);

}