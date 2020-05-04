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
 * @author Inácio Nery
 */
public class FormMetricTypeTest extends BaseEnumTestCase<FormMetricType> {

	@Test
	public void testAbandonments() {
		FormMetricType formMetricType = FormMetricType.of("abandonmentsMetric");

		Assert.assertEquals(FormMetricType.ABANDONMENTS, formMetricType);
	}

	@Test
	public void testAbandonmentsFieldName() {
		FormMetricType formMetricType = FormMetricType.ABANDONMENTS;

		Assert.assertEquals("abandonments", formMetricType.getFieldName());
	}

	@Test
	public void testAbandonmentsTrendClassificationOrder() {
		FormMetricType formMetricType = FormMetricType.ABANDONMENTS;

		Assert.assertEquals(
			TrendClassification.Order.DESC,
			formMetricType.getTrendClassificationOrder());
	}

	@Test
	public void testCompletionTime() {
		FormMetricType formMetricType = FormMetricType.of(
			"completionTimeMetric");

		Assert.assertEquals(FormMetricType.COMPLETION_TIME, formMetricType);
	}

	@Test
	public void testCompletionTimeFieldName() {
		FormMetricType formMetricType = FormMetricType.COMPLETION_TIME;

		Assert.assertEquals("submissionsTime", formMetricType.getFieldName());
	}

	@Test
	public void testCompletionTimeTrendClassificationOrder() {
		FormMetricType completionTime = FormMetricType.COMPLETION_TIME;

		Assert.assertEquals(
			TrendClassification.Order.DESC,
			completionTime.getTrendClassificationOrder());
	}

	@Test
	public void testSubmissions() {
		FormMetricType formMetricType = FormMetricType.of("submissionsMetric");

		Assert.assertEquals(FormMetricType.SUBMISSIONS, formMetricType);
	}

	@Test
	public void testSubmissionsFieldName() {
		FormMetricType formMetricType = FormMetricType.SUBMISSIONS;

		Assert.assertEquals("submissions", formMetricType.getFieldName());
	}

	@Test
	public void testSubmissionsTrendClassificationOrder() {
		FormMetricType formMetricType = FormMetricType.SUBMISSIONS;

		Assert.assertEquals(
			TrendClassification.Order.ASC,
			formMetricType.getTrendClassificationOrder());
	}

	@Test
	public void testViews() {
		FormMetricType formMetricType = FormMetricType.of("viewsMetric");

		Assert.assertEquals(FormMetricType.VIEWS, formMetricType);
	}

	@Test
	public void testViewsFieldName() {
		FormMetricType formMetricType = FormMetricType.VIEWS;

		Assert.assertEquals("views", formMetricType.getFieldName());
	}

	@Test
	public void testViewsTrendClassificationOrder() {
		FormMetricType formMetricType = FormMetricType.VIEWS;

		Assert.assertEquals(
			TrendClassification.Order.ASC,
			formMetricType.getTrendClassificationOrder());
	}

	@Override
	protected Class<? extends Enum<?>> getClazz() {
		return FormMetricType.class;
	}

}