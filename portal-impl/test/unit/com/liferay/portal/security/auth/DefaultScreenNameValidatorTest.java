/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.security.auth.DefaultScreenNameValidator;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.util.HtmlImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Drew Brokke
 */
public class DefaultScreenNameValidatorTest extends DefaultScreenNameValidator {

	@Before
	public void setUp() {
		HtmlUtil htmlUtil = new HtmlUtil();

		htmlUtil.setHtml(new HtmlImpl());
	}

	@Test
	public void testGetJSEscapedSpecialChars() {
		_specialCharacters = "-._'";

		Assert.assertEquals(
			HtmlUtil.escapeJS(_specialCharacters), getJSEscapedSpecialChars());
	}

	@Override
	protected String getSpecialChars() {
		return _specialCharacters;
	}

	private String _specialCharacters = "-._";

}