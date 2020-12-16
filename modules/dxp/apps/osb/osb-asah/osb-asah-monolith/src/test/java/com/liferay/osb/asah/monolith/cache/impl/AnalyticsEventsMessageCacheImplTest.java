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

import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Geyson Silva
 */
public class AnalyticsEventsMessageCacheImplTest {

	@Before
	public void setUp() {
		_analyticsEventsMessageCacheImpl =
			new AnalyticsEventsMessageCacheImpl();
	}

	@Test
	public void testAdd() {
		try {
			ProjectIdThreadLocal.setProjectId("test1");

			_analyticsEventsMessageCacheImpl.add(null);
			_analyticsEventsMessageCacheImpl.add("analytics");
			_analyticsEventsMessageCacheImpl.add("liferay");

			Assert.assertFalse(_analyticsEventsMessageCacheImpl.add(null));
			Assert.assertTrue(_analyticsEventsMessageCacheImpl.add("cloud"));
			Assert.assertFalse(_analyticsEventsMessageCacheImpl.add("liferay"));

			ProjectIdThreadLocal.setProjectId("test2");

			Assert.assertTrue(_analyticsEventsMessageCacheImpl.add("liferay"));
		}
		finally {
			ProjectIdThreadLocal.remove();
		}
	}

	@Test
	public void testRemove() {
		try {
			ProjectIdThreadLocal.setProjectId("test1");

			_analyticsEventsMessageCacheImpl.add("analytics");
			_analyticsEventsMessageCacheImpl.add("liferay");

			_analyticsEventsMessageCacheImpl.remove("liferay");

			Assert.assertTrue(
				_analyticsEventsMessageCacheImpl.contains("analytics"));
			Assert.assertFalse(
				_analyticsEventsMessageCacheImpl.contains("liferay"));

			ProjectIdThreadLocal.setProjectId("test2");

			_analyticsEventsMessageCacheImpl.add("analytics");

			_analyticsEventsMessageCacheImpl.remove("analytics");

			ProjectIdThreadLocal.setProjectId("test1");

			Assert.assertTrue(
				_analyticsEventsMessageCacheImpl.contains("analytics"));
		}
		finally {
			ProjectIdThreadLocal.remove();
		}
	}

	private AnalyticsEventsMessageCacheImpl _analyticsEventsMessageCacheImpl;

}