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
public class PageMetricTypeTest extends BaseEnumTestCase<PageMetricType> {

	@Test
	public void testAvgTimeOnPage() {
		PageMetricType pageMetricType = PageMetricType.of(
			"avgTimeOnPageMetric");

		Assert.assertEquals(PageMetricType.AVG_TIME_ON_PAGE, pageMetricType);
	}

	@Test
	public void testAvgTimeOnPageFieldName() {
		PageMetricType pageMetricType = PageMetricType.AVG_TIME_ON_PAGE;

		Assert.assertEquals("timeOnPage", pageMetricType.getFieldName());
	}

	@Test
	public void testAvgTimeOnPageTrendClassificationOrder() {
		PageMetricType pageMetricType = PageMetricType.AVG_TIME_ON_PAGE;

		Assert.assertEquals(
			TrendClassification.Order.ASC,
			pageMetricType.getTrendClassificationOrder());
	}

	@Test
	public void testBounceRate() {
		PageMetricType pageMetricType = PageMetricType.of("bounceRateMetric");

		Assert.assertEquals(PageMetricType.BOUNCE_RATE, pageMetricType);
	}

	@Test
	public void testBounceRateFieldName() {
		PageMetricType pageMetricType = PageMetricType.BOUNCE_RATE;

		Assert.assertEquals("bounce", pageMetricType.getFieldName());
	}

	@Test
	public void testBounceRateTrendClassificationOrder() {
		PageMetricType pageMetricType = PageMetricType.BOUNCE_RATE;

		Assert.assertEquals(
			TrendClassification.Order.DESC,
			pageMetricType.getTrendClassificationOrder());
	}

	@Test
	public void testDirectAccess() {
		PageMetricType pageMetricType = PageMetricType.of("directAccessMetric");

		Assert.assertEquals(PageMetricType.DIRECT_ACCESS, pageMetricType);
	}

	@Test
	public void testDirectAccessFieldName() {
		PageMetricType pageMetricType = PageMetricType.DIRECT_ACCESS;

		Assert.assertEquals("directAccess", pageMetricType.getFieldName());
	}

	@Test
	public void testDirectAccessTrendClassificationOrder() {
		PageMetricType pageMetricType = PageMetricType.DIRECT_ACCESS;

		Assert.assertEquals(
			TrendClassification.Order.ASC,
			pageMetricType.getTrendClassificationOrder());
	}

	@Test
	public void testEngagement() {
		PageMetricType pageMetricType = PageMetricType.of("engagementMetric");

		Assert.assertEquals(PageMetricType.ENGAGEMENT, pageMetricType);
	}

	@Test
	public void testEngagementFieldName() {
		PageMetricType pageMetricType = PageMetricType.ENGAGEMENT;

		Assert.assertEquals("engagementScore", pageMetricType.getFieldName());
	}

	@Test
	public void testEngagementTrendClassificationOrder() {
		PageMetricType pageMetricType = PageMetricType.ENGAGEMENT;

		Assert.assertEquals(
			TrendClassification.Order.ASC,
			pageMetricType.getTrendClassificationOrder());
	}

	@Test
	public void testUsers() {
		PageMetricType pageMetricType = PageMetricType.of("visitorsMetric");

		Assert.assertEquals(PageMetricType.VISITORS, pageMetricType);
	}

	@Test
	public void testUsersFieldName() {
		PageMetricType pageMetricType = PageMetricType.VISITORS;

		Assert.assertEquals("visitors", pageMetricType.getFieldName());
	}

	@Test
	public void testUsersTrendClassificationOrder() {
		PageMetricType pageMetricType = PageMetricType.VISITORS;

		Assert.assertEquals(
			TrendClassification.Order.ASC,
			pageMetricType.getTrendClassificationOrder());
	}

	@Test
	public void testViews() {
		PageMetricType pageMetricType = PageMetricType.of("viewsMetric");

		Assert.assertEquals(PageMetricType.VIEWS, pageMetricType);
	}

	@Test
	public void testViewsFieldName() {
		PageMetricType pageMetricType = PageMetricType.VIEWS;

		Assert.assertEquals("views", pageMetricType.getFieldName());
	}

	@Test
	public void testViewsTrendClassificationOrder() {
		PageMetricType pageMetricType = PageMetricType.VIEWS;

		Assert.assertEquals(
			TrendClassification.Order.ASC,
			pageMetricType.getTrendClassificationOrder());
	}

	@Override
	protected Class<? extends Enum<?>> getClazz() {
		return PageMetricType.class;
	}

}