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

package com.liferay.osb.asah.common.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author André Miranda
 */
public class URLUtilTest {

	@Test
	public void testDecode() {
		Assert.assertEquals(
			"http://www.google.com/?q=\"hsR=0'hsR={{31337*31337}}>&lt;hsR>",
			URLUtil.decode(
				"http://www.google.com/%3Fq=%22hsR=0'hsR=%7B%7B31337*31337%7D" +
					"%7D%3E&lt;hsR%3E"));
	}

	@Test
	public void testToURI() throws URISyntaxException {
		URI uri = URLUtil.toURI("https://liferay.com/foo?q=escaping em ação!");

		Assert.assertEquals("/foo?q=escaping em ação!", uri.getPath());
		Assert.assertEquals("liferay.com", uri.getHost());
	}

}