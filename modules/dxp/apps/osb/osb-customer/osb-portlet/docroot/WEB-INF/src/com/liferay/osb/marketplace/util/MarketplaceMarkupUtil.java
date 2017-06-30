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

package com.liferay.osb.marketplace.util;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ryan Park
 */
public class MarketplaceMarkupUtil {

	public static String getHTML(String markup) {
		String html = markup;

		html = HtmlUtil.escape(html);

		Matcher matcher = _boldPattern.matcher(html);

		html = matcher.replaceAll("<strong>$1</strong>");

		matcher = _italicPattern.matcher(html);

		html = matcher.replaceAll("<em>$1</em>");

		matcher = _urlPattern.matcher(html);

		html = matcher.replaceAll("<a href=\"$1\" target=\"_blank\">$1</a>");

		String[] htmlSegments = StringUtil.split(html, "\n\n");

		StringBundler sb = new StringBundler(htmlSegments.length * 2);

		for (String htmlSegment : htmlSegments) {
			sb.append(getHTMLSegment(htmlSegment));
			sb.append(StringPool.NEW_LINE);
		}

		return sb.toString();
	}

	public static String getSummary(String markup, int length) {
		String summary = StringUtil.shorten(markup, length, StringPool.BLANK);

		summary = HtmlUtil.escape(summary);

		Matcher matcher = _boldPattern.matcher(summary);

		summary = matcher.replaceAll("$1");

		matcher = _italicPattern.matcher(summary);

		summary = matcher.replaceAll("$1");

		matcher = _listItemPattern.matcher(summary);

		summary = matcher.replaceAll("$1 $2");

		summary = summary.replace(StringPool.NEW_LINE, "<br />");

		return summary;
	}

	protected static String getHTMLSegment(String htmlSegment) {
		htmlSegment = htmlSegment.trim();

		if (Validator.isNull(htmlSegment)) {
			return StringPool.BLANK;
		}

		boolean list = false;

		Matcher matcher = _listItemPattern.matcher(htmlSegment);

		if (matcher.find()) {
			list = true;
		}

		htmlSegment = matcher.replaceAll("<li>$2</li>");

		matcher = _listPattern.matcher(htmlSegment);

		htmlSegment = matcher.replaceAll("<ul>$1</ul>");

		String[] lines = StringUtil.split(htmlSegment, StringPool.NEW_LINE);

		StringBundler sb = new StringBundler(lines.length * 2);

		for (String line : lines) {
			sb.append(line);

			if (!line.matches("^</?(ul|li)>.+")) {
				sb.append("<br/>\n");
			}
		}

		if (!list) {
			return "<p>" + sb.toString() + "</p>";
		}
		else {
			return "<div>" + sb.toString() + "</div>";
		}
	}

	private static Pattern _boldPattern = Pattern.compile("\\*\\*(.+)\\*\\*");
	private static Pattern _italicPattern = Pattern.compile("__(.+)__");
	private static Pattern _listItemPattern = Pattern.compile(
		"^\\s?([*-])\\s+(.+?)$", Pattern.MULTILINE);
	private static Pattern _listPattern = Pattern.compile(
		"((^<li>.+</li>(\n|\\z))+)", Pattern.MULTILINE);
	private static Pattern _urlPattern = Pattern.compile(
		"(https?://[a-zA-Z0-9@#%_+=;:./-]*(\\?([a-zA-Z0-9(&amp;)%_+=;./-])*)?" +
			"[a-zA-Z0-9#%_+=;/-])");

}