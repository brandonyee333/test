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
public class TrendClassificationTest
	extends BaseEnumTestCase<TrendClassification> {

	@Test
	public void testNegativeTrendClassification1() {
		TrendClassification trendClassification = TrendClassification.classify(
			-10, TrendClassification.Order.ASC);

		Assert.assertEquals(TrendClassification.NEGATIVE, trendClassification);
	}

	@Test
	public void testNegativeTrendClassification2() {
		TrendClassification trendClassification = TrendClassification.classify(
			10, TrendClassification.Order.DESC);

		Assert.assertEquals(TrendClassification.NEGATIVE, trendClassification);
	}

	@Test
	public void testNeutralTrendClassification1() {
		TrendClassification trendClassification = TrendClassification.classify(
			0, TrendClassification.Order.ASC);

		Assert.assertEquals(TrendClassification.NEUTRAL, trendClassification);
	}

	@Test
	public void testNeutralTrendClassification2() {
		TrendClassification trendClassification = TrendClassification.classify(
			0, TrendClassification.Order.DESC);

		Assert.assertEquals(TrendClassification.NEUTRAL, trendClassification);
	}

	@Test
	public void testPositiveTrendClassification1() {
		TrendClassification trendClassification = TrendClassification.classify(
			10, TrendClassification.Order.ASC);

		Assert.assertEquals(TrendClassification.POSITIVE, trendClassification);
	}

	@Test
	public void testPositiveTrendClassification2() {
		TrendClassification trendClassification = TrendClassification.classify(
			-10, TrendClassification.Order.DESC);

		Assert.assertEquals(TrendClassification.POSITIVE, trendClassification);
	}

	@Override
	protected Class<? extends Enum<?>> getClazz() {
		return TrendClassification.class;
	}

}