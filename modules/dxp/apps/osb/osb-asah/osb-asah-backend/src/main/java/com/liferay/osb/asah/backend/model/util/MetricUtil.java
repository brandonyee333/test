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

package com.liferay.osb.asah.backend.model.util;

import com.liferay.osb.asah.backend.model.Metric;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import java.lang.reflect.Method;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author André Miranda
 */
public class MetricUtil {

	public static Set<Metric> getAvailableMetrics(Object metric) {
		Set<Metric> availableMetrics = new HashSet<>();

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(metric.getClass());

			for (PropertyDescriptor propertyDescriptor :
					beanInfo.getPropertyDescriptors()) {

				Method readMethod = propertyDescriptor.getReadMethod();

				if ((readMethod != null) &&
					Objects.equals(
						propertyDescriptor.getPropertyType(), Metric.class)) {

					availableMetrics.add((Metric)readMethod.invoke(metric));
				}
			}
		}
		catch (IntrospectionException | ReflectiveOperationException e) {
		}

		return availableMetrics;
	}

}