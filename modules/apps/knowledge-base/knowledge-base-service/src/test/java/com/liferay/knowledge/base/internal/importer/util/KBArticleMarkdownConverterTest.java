/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.internal.importer.util;

import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.portal.kernel.model.ModelHints;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.util.FileImpl;
import com.liferay.portal.util.HtmlImpl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Alejandro Tardín
 */
@RunWith(MockitoJUnitRunner.class)
public class KBArticleMarkdownConverterTest {

	@Before
	public void setUp() {
		FileUtil fileUtil = new FileUtil();

		fileUtil.setFile(new FileImpl());

		HtmlUtil htmlUtil = new HtmlUtil();

		htmlUtil.setHtml(new HtmlImpl());

		ModelHintsUtil modelHintsUtil = new ModelHintsUtil();

		modelHintsUtil.setModelHints(_modelHints);

		Mockito.when(
			_modelHints.getMaxLength(KBArticle.class.getName(), "urlTitle")
		).thenReturn(
			256
		);
	}

	@Test
	public void testGetSourceURLAddsTheMissingSlashInTheBaseURL()
		throws Exception {

		String markdown = "Title [](id=1234)\n=============";
		String fileEntryName = "some/unix/file";

		Map<String, String> metadata = new HashMap<>();

		metadata.put("base.source.url", "http://baseURL");

		KBArticleMarkdownConverter kbArticleMarkdownConverter =
			new KBArticleMarkdownConverter(markdown, fileEntryName, metadata);

		Assert.assertEquals(
			"http://baseURL/some/unix/file",
			kbArticleMarkdownConverter.getSourceURL());
	}

	@Test
	public void testGetSourceURLReplacesBackSlashesWithForwardSlashes()
		throws Exception {

		String markdown = "Title [](id=1234)\n=============";
		String fileEntryName = "some\\windows\\file";

		Map<String, String> metadata = new HashMap<>();

		metadata.put("base.source.url", "http://baseURL");

		KBArticleMarkdownConverter kbArticleMarkdownConverter =
			new KBArticleMarkdownConverter(markdown, fileEntryName, metadata);

		Assert.assertEquals(
			"http://baseURL/some/windows/file",
			kbArticleMarkdownConverter.getSourceURL());
	}

	@Test
	public void testGetSourceURLReturnsNullIfThereIsNoBaseURL()
		throws Exception {

		String markdown = "Title [](id=1234)\n=============";
		String fileEntryName = "some\\windows\\file";
		Map<String, String> metadata = new HashMap<>();

		KBArticleMarkdownConverter kbArticleMarkdownConverter =
			new KBArticleMarkdownConverter(markdown, fileEntryName, metadata);

		Assert.assertNull(kbArticleMarkdownConverter.getSourceURL());
	}

	@Test
	public void testGetSourceURLUsesTheSlashInTheBaseURL() throws Exception {
		String markdown = "Title [](id=1234)\n=============";
		String fileEntryName = "some/unix/file";

		Map<String, String> metadata = new HashMap<>();

		metadata.put("base.source.url", "http://baseURL/");

		KBArticleMarkdownConverter kbArticleMarkdownConverter =
			new KBArticleMarkdownConverter(markdown, fileEntryName, metadata);

		Assert.assertEquals(
			"http://baseURL/some/unix/file",
			kbArticleMarkdownConverter.getSourceURL());
	}

	@Mock
	private static ModelHints _modelHints;

}