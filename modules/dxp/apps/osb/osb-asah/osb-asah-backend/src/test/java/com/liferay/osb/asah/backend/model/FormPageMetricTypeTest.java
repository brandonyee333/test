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

import com.liferay.osb.asah.backend.test.util.BaseEnumTestCase;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author André Miranda
 */
public class FormPageMetricTypeTest
	extends BaseEnumTestCase<FormPageMetricType> {

	@Test
	public void testPageAbandonments() {
		FormPageMetricType formPageMetricType = FormPageMetricType.of(
			"pageAbandonmentsMetric");

		Assert.assertEquals(
			FormPageMetricType.PAGE_ABANDONMENTS, formPageMetricType);
	}

	@Test
	public void testPageAbandonmentsFieldName() {
		FormPageMetricType formPageMetricType =
			FormPageMetricType.PAGE_ABANDONMENTS;

		Assert.assertEquals("abandonments", formPageMetricType.getFieldName());
	}

	@Test
	public void testPageAbandonmentsTrendClassificationOrder() {
		FormPageMetricType formPageMetricType =
			FormPageMetricType.PAGE_ABANDONMENTS;

		Assert.assertEquals(
			TrendClassification.Order.DESC,
			formPageMetricType.getTrendClassificationOrder());
	}

	@Test
	public void testPageViews() {
		FormPageMetricType formPageMetricType = FormPageMetricType.of(
			"pageViewsMetric");

		Assert.assertEquals(FormPageMetricType.PAGE_VIEWS, formPageMetricType);
	}

	@Test
	public void testPageViewsFieldName() {
		FormPageMetricType formPageMetricType = FormPageMetricType.PAGE_VIEWS;

		Assert.assertEquals("views", formPageMetricType.getFieldName());
	}

	@Test
	public void testPageViewsTrendClassificationOrder() {
		FormPageMetricType formPageMetricType = FormPageMetricType.PAGE_VIEWS;

		Assert.assertEquals(
			TrendClassification.Order.ASC,
			formPageMetricType.getTrendClassificationOrder());
	}

	@Override
	protected Class<? extends Enum<?>> getClazz() {
		return FormMetricType.class;
	}

}