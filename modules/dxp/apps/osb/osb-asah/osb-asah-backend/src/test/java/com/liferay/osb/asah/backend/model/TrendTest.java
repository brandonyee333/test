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

import com.liferay.osb.asah.backend.test.util.BaseBeanTestCase;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Inácio Nery
 */
public class TrendTest extends BaseBeanTestCase<Trend> {

	@Test
	public void testConstructor() {
		Trend trend = new Trend();

		trend.setPercentage(BigDecimal.valueOf(50D));
		trend.setTrendClassification(TrendClassification.POSITIVE);

		Assert.assertEquals(
			trend,
			new Trend(TrendClassification.POSITIVE, BigDecimal.valueOf(50D)));
	}

	@Override
	protected Trend newInstance() {
		return new Trend();
	}

}