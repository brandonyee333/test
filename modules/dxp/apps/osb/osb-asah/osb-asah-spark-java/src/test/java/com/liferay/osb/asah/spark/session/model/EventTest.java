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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Robson Pastor
 */
public class EventTest {

	@Test
	public void testIsInteraction() {
		Assertions.assertFalse(Event.isInteraction("webContentViewed"));
		Assertions.assertTrue(Event.isInteraction("pageDepthReached"));
	}

	@Test
	public void testIsPageViewed() {
		Assertions.assertFalse(Event.isPageViewed("Page", "pageDepthReached"));
		Assertions.assertFalse(
			Event.isPageViewed("WebContent", "pageDepthReached"));
		Assertions.assertFalse(Event.isPageViewed("WebContent", "pageViewed"));
		Assertions.assertFalse(
			Event.isPageViewed("WebContent", "webContentViewed"));
		Assertions.assertTrue(Event.isPageViewed("Page", "pageViewed"));
	}

}