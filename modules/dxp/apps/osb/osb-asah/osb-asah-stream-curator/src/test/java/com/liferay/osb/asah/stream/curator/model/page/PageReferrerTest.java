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

package com.liferay.osb.asah.stream.curator.model.page;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author André Miranda
 */
public class PageReferrerTest {

	@Test
	public void testReferrerURL1() {
		PageReferrer pageReferrer = new PageReferrer();

		pageReferrer.setReferrer(
			"https://liferay.com/some-random-path?q=liferay portal");

		Assert.assertEquals("liferay.com", pageReferrer.getReferrerHost());
		Assert.assertEquals(
			"https://liferay.com/some-random-path?q=liferay portal",
			pageReferrer.getReferrerCanonicalUrl());
	}

	@Test
	public void testReferrerURL2() {
		PageReferrer pageReferrer = new PageReferrer();

		pageReferrer.setReferrer(
			"http://www.google.com/?q=\"hsR=0'hsR={{31337*31337}}>&lt;hsR>");

		Assert.assertEquals("www.google.com", pageReferrer.getReferrerHost());
		Assert.assertEquals(
			"http://www.google.com/?q=\"hsR=0'hsR={{31337*31337}}>&lt;hsR>",
			pageReferrer.getReferrerCanonicalUrl());
	}

}