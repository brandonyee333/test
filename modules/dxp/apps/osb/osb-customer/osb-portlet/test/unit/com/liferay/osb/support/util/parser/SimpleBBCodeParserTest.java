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

package com.liferay.osb.support.util.parser;

import com.liferay.portal.kernel.test.TestCase;
import com.liferay.portal.kernel.util.StringBundler;

import org.junit.Test;

/**
 * @author Amos Fong
 */
public class SimpleBBCodeParserTest extends TestCase {

	@Test
	public void testBadSyntax() throws Exception {
		String parsedText = _simpleBBCodeParser.parse(
			"[quote][code][b]text[code][i][/u]");

		assertEquals("[quote][code][b]text[code][i][/u]", parsedText);
	}

	@Test
	public void testBadUrlSyntax() throws Exception {
		String parsedText = _simpleBBCodeParser.parse("[url]invalid[/url]");

		assertEquals("[url]invalid[/url]", parsedText);
	}

	@Test
	public void testCombination() throws Exception {
		String parsedText = _simpleBBCodeParser.parse(
			"In [b]the[/b] [u]beginning[/u], [code]out.print(\"light\");" +
				"[/code]. And He said, [quote]it was good[/quote]");

		StringBundler sb = new StringBundler(6);

		sb.append("In <strong>the</strong> <u>beginning</u>, ");
		sb.append("<div class=\"code\"><table class=\"lfr-table\"><tr>");
		sb.append("<td class=\"unselectable\">1</td><td>out.print(\"light\");");
		sb.append("</td></tr></table></div>. And He said, ");
		sb.append("<div class=\"quote\"><div class=\"quote-content\">");
		sb.append("it was good</div></div>");

		assertEquals(sb.toString(), parsedText);
	}

	@Test
	public void testTagBold() throws Exception {
		String parsedText = _simpleBBCodeParser.parse("[b]text[/b]");

		assertEquals("<strong>text</strong>", parsedText);
	}

	@Test
	public void testTagCode() throws Exception {
		StringBundler sb = new StringBundler(3);

		sb.append("<div class=\"code\"><table class=\"lfr-table\"><tr>");
		sb.append("<td class=\"unselectable\">1<br />2<br />3<br /></td><td>");
		sb.append("line1<br />line2<br />line3<br /></td></tr></table></div>");

		String parsedText = _simpleBBCodeParser.parse(
			"[code]line1\nline2\nline3[/code]");

		assertEquals(sb.toString(), parsedText);
	}

	@Test
	public void testTagCodeWithNestedTags() throws Exception {
		StringBundler sb = new StringBundler(3);

		sb.append("text<div class=\"code\"><table class=\"lfr-table\"><tr>");
		sb.append("<td class=\"unselectable\">1</td><td>text[b]text[/b]text");
		sb.append("[quote]text[/quote]text</td></tr></table></div>text");

		String parsedText = _simpleBBCodeParser.parse(
			"text[code]text[b]text[/b]text[quote]text[/quote]text[/code]text");

		assertEquals(sb.toString(), parsedText);
	}

	@Test
	public void testTagItalics() throws Exception {
		String parsedText = _simpleBBCodeParser.parse("[i]text[/i]");

		assertEquals("<em>text</em>", parsedText);
	}

	@Test
	public void testTagQuote() throws Exception {
		String parsedText = _simpleBBCodeParser.parse("[quote]text[/quote]");

		assertEquals(
			"<div class=\"quote\"><div class=\"quote-content\">text</div>" +
				"</div>",
			parsedText);
	}

	@Test
	public void testTagUnderline() throws Exception {
		String parsedText = _simpleBBCodeParser.parse("[u]text[/u]");

		assertEquals("<u>text</u>", parsedText);
	}

	@Test
	public void testTagUrl() throws Exception {
		String parsedText = _simpleBBCodeParser.parse(
			"[url=http://www.liferay.com]Liferay[/url]");

		assertEquals(
			"<a href=\"http://www.liferay.com\" target=\"_blank\">Liferay</a>",
			parsedText);

		parsedText = _simpleBBCodeParser.parse(
			"[url=https://www.liferay.com/web/support/-/LRDCOM-123?_(lesa)&" +
				"page=com.liferay.osb:comment#4@date%567]This is a link." +
					"[/url]");

		assertEquals(
			"<a href=\"https://www.liferay.com/web/support/-/LRDCOM-123?_(" +
				"lesa)&page=com.liferay.osb:comment#4@date%567\" " +
					"target=\"_blank\">This is a link.</a>",
			parsedText);
	}

	private SimpleBBCodeParser _simpleBBCodeParser = new SimpleBBCodeParser();

}