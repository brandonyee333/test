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

package com.liferay.osb.asah.backend.model;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import java.lang.reflect.Method;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Marcellus Tavares
 */
public class FormPageMetric {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FormPageMetric)) {
			return false;
		}

		FormPageMetric formPageMetric = (FormPageMetric)obj;

		if (Objects.equals(
				_formFieldMetrics, formPageMetric._formFieldMetrics) &&
			Objects.equals(
				_pageAbandonmentsMetric,
				formPageMetric._pageAbandonmentsMetric) &&
			Objects.equals(_pageIndex, formPageMetric._pageIndex) &&
			Objects.equals(_pageName, formPageMetric._pageName) &&
			Objects.equals(_pageViewsMetric, formPageMetric._pageViewsMetric)) {

			return true;
		}

		return false;
	}

	public Set<Metric> getAvailableMetrics() {
		Set<Metric> availableMetrics = new HashSet<>();

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(getClass());

			for (PropertyDescriptor propertyDescriptor :
					beanInfo.getPropertyDescriptors()) {

				Method readMethod = propertyDescriptor.getReadMethod();

				if ((readMethod != null) &&
					Objects.equals(
						propertyDescriptor.getPropertyType(), Metric.class)) {

					availableMetrics.add((Metric)readMethod.invoke(this));
				}
			}
		}
		catch (IntrospectionException | ReflectiveOperationException e) {
		}

		return availableMetrics;
	}

	public List<FormFieldMetric> getFormFieldMetrics() {
		return _formFieldMetrics;
	}

	public Metric getPageAbandonmentsMetric() {
		return _pageAbandonmentsMetric;
	}

	public String getPageIndex() {
		return _pageIndex;
	}

	public String getPageName() {
		return _pageName;
	}

	public Metric getPageViewsMetric() {
		return _pageViewsMetric;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_formFieldMetrics, _pageAbandonmentsMetric, _pageIndex, _pageName,
			_pageViewsMetric);
	}

	public void setFormFieldMetrics(List<FormFieldMetric> formFieldMetrics) {
		_formFieldMetrics = formFieldMetrics;
	}

	public void setPageAbandonmentsMetric(Metric pageAbandonmentsMetric) {
		_pageAbandonmentsMetric = pageAbandonmentsMetric;
	}

	public void setPageIndex(String pageIndex) {
		_pageIndex = pageIndex;
	}

	public void setPageName(String pageName) {
		_pageName = pageName;
	}

	public void setPageViewsMetric(Metric pageViewsMetric) {
		_pageViewsMetric = pageViewsMetric;
	}

	private List<FormFieldMetric> _formFieldMetrics;
	private Metric _pageAbandonmentsMetric;
	private String _pageIndex;
	private String _pageName;
	private Metric _pageViewsMetric;

}