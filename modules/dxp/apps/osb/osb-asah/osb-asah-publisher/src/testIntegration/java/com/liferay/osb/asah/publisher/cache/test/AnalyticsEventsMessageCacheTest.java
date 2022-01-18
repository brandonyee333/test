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

package com.liferay.osb.asah.publisher.cache.test;

import com.liferay.osb.asah.publisher.OSBAsahPublisherSpringTestContext;
import com.liferay.osb.asah.publisher.cache.AnalyticsEventsMessageCache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Inácio Nery
 */
public class AnalyticsEventsMessageCacheTest
	implements OSBAsahPublisherSpringTestContext {

	@BeforeEach
	public void setUp() {
		_analyticsEventsMessageCache.remove("abc");
	}

	@Test
	public void testAdd() {
		Assertions.assertTrue(_analyticsEventsMessageCache.add("abc"));

		Assertions.assertFalse(_analyticsEventsMessageCache.add("abc"));

		Assertions.assertTrue(_analyticsEventsMessageCache.add(null));

		Assertions.assertTrue(_analyticsEventsMessageCache.add(null));
	}

	@Test
	public void testRemove() {
		Assertions.assertTrue(_analyticsEventsMessageCache.add("abc"));

		_analyticsEventsMessageCache.remove("abc");

		Assertions.assertTrue(_analyticsEventsMessageCache.add("abc"));

		_analyticsEventsMessageCache.remove(null);
	}

	@Autowired
	private AnalyticsEventsMessageCache _analyticsEventsMessageCache;

}