/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.exportimport.content.processor;

import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.PropsTestUtil;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mariano Álvaro Sáiz
 */
public class JournalArticleExportImportContentProcessorTest {

	@Before
	public void setUp() throws Exception {
		setUpPropsUtil();
	}

	@Test
	public void testMultipleLinesHTMLComment() {
		_testExcludeHTMLComments(
			"<p>Just a test</p>",
			"<p>Just <!-- with a\n HTML comment -->a test</p>");
	}

	@Test
	public void testNestedHTMLComment() {
		_testExcludeHTMLComments(
			"<p>Just a test</p>",
			"<p>Just <!-- with a <!-- inside --> HTML comment -->a test</p>");
	}

	@Test
	public void testNoHTMLComment() {
		_testExcludeHTMLComments("<p>Just a test</p>", "<p>Just a test</p>");
	}

	@Test
	public void testSingleLineHTMLComment() {
		_testExcludeHTMLComments(
			"<p>Just a test</p>",
			"<p>Just <!-- with a HTML comment -->a test</p>");
	}

	protected void setUpPropsUtil() {
		PropsTestUtil.setProps(Collections.emptyMap());
	}

	private void _testExcludeHTMLComments(
		String expectedContent, String content) {

		JournalArticleExportImportContentProcessor
			journalArticleExportImportContentProcessor =
				new JournalArticleExportImportContentProcessor();

		String excludedHtmlCommentContent = ReflectionTestUtil.invoke(
			journalArticleExportImportContentProcessor, "_excludeHTMLComments",
			new Class<?>[] {String.class}, content);

		Assert.assertEquals(expectedContent, excludedHtmlCommentContent);
	}

}