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

package com.liferay.osb.asah.monolith.cache.impl;

import com.liferay.osb.asah.publisher.cache.AnalyticsEventsMessageCache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Geyson Silva
 */
public class AnalyticsEventsMessageCacheImplTest {

	@Before
	public void setUp() {
		_analyticsEventsMessageCache = new AnalyticsEventsMessageCacheImpl();
	}

	@Test
	public void testCache() {
		_analyticsEventsMessageCache.add(null);

		_analyticsEventsMessageCache.add("analytics");

		_analyticsEventsMessageCache.add("liferay");

		Assert.assertFalse(_analyticsEventsMessageCache.has(null));

		Assert.assertFalse(_analyticsEventsMessageCache.has("cloud"));

		Assert.assertTrue(_analyticsEventsMessageCache.has("liferay"));
	}

	private AnalyticsEventsMessageCache _analyticsEventsMessageCache;

}