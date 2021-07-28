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

package com.liferay.osb.asah.spark.session.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Robson Pastor
 */
public class EventTest {

	@Test
	public void testIsInteraction() {
		Assert.assertTrue(Event.isInteraction("pageDepthReached"));
		Assert.assertFalse(Event.isInteraction("webContentViewed"));
	}

	@Test
	public void testIsPageViewed() {
		Assert.assertTrue(Event.isPageViewed("Page", "pageViewed"));
		Assert.assertFalse(Event.isPageViewed("WebContent", "pageViewed"));
		Assert.assertFalse(Event.isPageViewed("Page", "pageDepthReached"));
		Assert.assertFalse(
			Event.isPageViewed("WebContent", "pageDepthReached"));
		Assert.assertFalse(
			Event.isPageViewed("WebContent", "webContentViewed"));
	}

}