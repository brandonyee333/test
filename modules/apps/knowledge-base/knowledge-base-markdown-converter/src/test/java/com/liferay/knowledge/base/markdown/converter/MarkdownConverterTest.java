/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.markdown.converter;

import com.liferay.knowledge.base.markdown.converter.factory.MarkdownConverterFactoryUtil;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Andy Wu
 */
public class MarkdownConverterTest {

	@Test
	public void testLiferayToHtmlSerializer() throws Exception {
		String markdownString =
			"### The liferay-ui:logo-selector Tag Requires Parameter Changes " +
				"[](id=the-liferay-uilogo-selector-tag-requires-parameter-" +
					"changes)";

		MarkdownConverter markdownConverter =
			MarkdownConverterFactoryUtil.create();

		String html = markdownConverter.convert(markdownString);

		int index = html.indexOf(
			"id=\"the-liferay-uilogo-selector-tag-requires-parameter-" +
				"changes\"");

		Assert.assertNotEquals(-1, index);
	}

}