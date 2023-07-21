/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.summary;

import com.liferay.portal.kernel.search.highlight.HighlightUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.search.summary.Summary;
import com.liferay.portal.search.summary.SummaryBuilder;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class SummaryBuilderImplTest {

	@Test
	public void testContent() {
		String content = RandomTestUtil.randomString();

		_summaryBuilder.setContent(content);

		Summary summary = _summaryBuilder.build();

		Assert.assertEquals(content, summary.getContent());
	}

	@Test
	public void testContentHighlight() {
		_summaryBuilder.setContent(
			StringBundler.concat(
				"AAA<strong>BBB</strong>CCC", HighlightUtil.HIGHLIGHT_TAG_OPEN,
				"DDD<strong>EEE</strong>FFF", HighlightUtil.HIGHLIGHT_TAG_CLOSE,
				"GGG<strong>HHH</strong>III"));

		_summaryBuilder.setHighlight(true);

		Summary summary = _summaryBuilder.build();

		Assert.assertEquals(
			StringBundler.concat(
				"AAA&lt;strong&gt;BBB&lt;/strong&gt;CCC",
				HighlightUtil.HIGHLIGHTS[0],
				"DDD&lt;strong&gt;EEE&lt;/strong&gt;FFF",
				HighlightUtil.HIGHLIGHTS[1],
				"GGG&lt;strong&gt;HHH&lt;/strong&gt;III"),
			summary.getContent());
	}

	@Test
	public void testContentHighlightUnescaped() {
		_summaryBuilder.setContent(
			StringBundler.concat(
				"AAA<strong>BBB</strong>CCC", HighlightUtil.HIGHLIGHT_TAG_OPEN,
				"DDD<strong>EEE</strong>FFF", HighlightUtil.HIGHLIGHT_TAG_CLOSE,
				"GGG<strong>HHH</strong>III"));

		_summaryBuilder.setEscape(false);
		_summaryBuilder.setHighlight(true);

		Summary summary = _summaryBuilder.build();

		Assert.assertEquals(
			StringBundler.concat(
				"AAA<strong>BBB</strong>CCC", HighlightUtil.HIGHLIGHTS[0],
				"DDD<strong>EEE</strong>FFF", HighlightUtil.HIGHLIGHTS[1],
				"GGG<strong>HHH</strong>III"),
			summary.getContent());
	}

	@Test
	public void testMaxContentLength() {
		String content = "12345678";

		testMaxContentLength(content, -99, content);
		testMaxContentLength(content, 0, content);
		testMaxContentLength(content, 2, "12");
		testMaxContentLength(content, 3, "...");
		testMaxContentLength(content, 4, "1...");
		testMaxContentLength(content, 7, "1234...");
		testMaxContentLength(content, 8, content);
		testMaxContentLength(content, 99, content);
	}

	@Test
	public void testMaxContentLengthIgnoredForTitle() {
		String title = RandomTestUtil.randomString(8);

		_summaryBuilder.setTitle(title);

		_summaryBuilder.setMaxContentLength(1);

		Summary summary = _summaryBuilder.build();

		Assert.assertEquals(title, summary.getTitle());
	}

	@Test
	public void testMaxContentLengthIgnoredWhenHighlight() {
		String content = RandomTestUtil.randomString(8);

		_summaryBuilder.setContent(content);

		_summaryBuilder.setHighlight(true);

		_summaryBuilder.setMaxContentLength(1);

		Summary summary = _summaryBuilder.build();

		Assert.assertEquals(content, summary.getContent());
	}

	@Test
	public void testTitle() {
		String title = RandomTestUtil.randomString();

		_summaryBuilder.setTitle(title);

		Summary summary = _summaryBuilder.build();

		Assert.assertEquals(title, summary.getTitle());
	}

	@Test
	public void testTitleHighlight() {
		_summaryBuilder.setTitle(
			StringBundler.concat(
				"AAA<strong>BBB</strong>CCC", HighlightUtil.HIGHLIGHT_TAG_OPEN,
				"DDD<strong>EEE</strong>FFF", HighlightUtil.HIGHLIGHT_TAG_CLOSE,
				"GGG<strong>HHH</strong>III"));

		_summaryBuilder.setHighlight(true);

		Summary summary = _summaryBuilder.build();

		Assert.assertEquals(
			StringBundler.concat(
				"AAA&lt;strong&gt;BBB&lt;/strong&gt;CCC",
				HighlightUtil.HIGHLIGHTS[0],
				"DDD&lt;strong&gt;EEE&lt;/strong&gt;FFF",
				HighlightUtil.HIGHLIGHTS[1],
				"GGG&lt;strong&gt;HHH&lt;/strong&gt;III"),
			summary.getTitle());
	}

	@Test
	public void testTitleHighlightUnescaped() {
		_summaryBuilder.setTitle(
			StringBundler.concat(
				"AAA<strong>BBB</strong>CCC", HighlightUtil.HIGHLIGHT_TAG_OPEN,
				"DDD<strong>EEE</strong>FFF", HighlightUtil.HIGHLIGHT_TAG_CLOSE,
				"GGG<strong>HHH</strong>III"));

		_summaryBuilder.setEscape(false);
		_summaryBuilder.setHighlight(true);

		Summary summary = _summaryBuilder.build();

		Assert.assertEquals(
			StringBundler.concat(
				"AAA<strong>BBB</strong>CCC", HighlightUtil.HIGHLIGHTS[0],
				"DDD<strong>EEE</strong>FFF", HighlightUtil.HIGHLIGHTS[1],
				"GGG<strong>HHH</strong>III"),
			summary.getTitle());
	}

	protected void testMaxContentLength(
		String content, int maxContentLength, String expected) {

		SummaryBuilder summaryBuilder = new SummaryBuilderImpl();

		summaryBuilder.setContent(content);
		summaryBuilder.setMaxContentLength(maxContentLength);

		Summary summary = summaryBuilder.build();

		Assert.assertEquals(expected, summary.getContent());
	}

	private final SummaryBuilder _summaryBuilder = new SummaryBuilderImpl();

}