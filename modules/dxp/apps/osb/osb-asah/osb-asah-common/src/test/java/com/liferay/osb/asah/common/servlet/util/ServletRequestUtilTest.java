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

package com.liferay.osb.asah.common.servlet.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Ivica Cardic
 */
public class ServletRequestUtilTest {

	@Test
	public void testGetOriginalURL() {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.addHeader(
			"X-Forwarded-Host", "www.first-second.com");
		mockHttpServletRequest.addHeader("X-Forwarded-Proto", "https");

		String originalURL = ServletRequestUtil.getOriginalURL(
			mockHttpServletRequest);

		Assertions.assertEquals("https://www.first-second.com", originalURL);

		mockHttpServletRequest = new MockHttpServletRequest();

		mockHttpServletRequest.addHeader(
			"X-Forwarded-Host", "www.first second com");
		mockHttpServletRequest.addHeader("X-Forwarded-Proto", "https ");

		originalURL = ServletRequestUtil.getOriginalURL(mockHttpServletRequest);

		Assertions.assertEquals("https+://www.first+second+com", originalURL);
	}

}