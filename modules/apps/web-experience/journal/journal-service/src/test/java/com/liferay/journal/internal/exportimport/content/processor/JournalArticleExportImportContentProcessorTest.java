/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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