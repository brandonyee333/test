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
public class JournalMetricTypeTest extends BaseEnumTestCase<JournalMetricType> {

	@Test
	public void testMostClicked() {
		JournalMetricType journalMetricType = JournalMetricType.of(
			"mostClicked");

		Assert.assertEquals(JournalMetricType.MOST_CLICKED, journalMetricType);
	}

	@Test
	public void testMostClickedFieldName() {
		JournalMetricType journalMetricType = JournalMetricType.MOST_CLICKED;

		Assert.assertEquals("clicks", journalMetricType.getFieldName());
	}

	@Test
	public void testMostClickedTrendClassificationOrder() {
		JournalMetricType journalMetricType = JournalMetricType.MOST_CLICKED;

		Assert.assertEquals(
			TrendClassification.Order.ASC,
			journalMetricType.getTrendClassificationOrder());
	}

	@Test
	public void testViews() {
		JournalMetricType journalMetricType = JournalMetricType.of(
			"viewsMetric");

		Assert.assertEquals(JournalMetricType.VIEWS, journalMetricType);
	}

	@Test
	public void testViewsFieldName() {
		JournalMetricType journalMetricType = JournalMetricType.VIEWS;

		Assert.assertEquals("views", journalMetricType.getFieldName());
	}

	@Test
	public void testViewsTrendClassificationOrder() {
		JournalMetricType journalMetricType = JournalMetricType.VIEWS;

		Assert.assertEquals(
			TrendClassification.Order.ASC,
			journalMetricType.getTrendClassificationOrder());
	}

	@Override
	protected Class<? extends Enum<?>> getClazz() {
		return JournalMetricType.class;
	}

}