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

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lin Cui
 * @author Amos Fong
 */
public class SimpleBBCodeParser {

	public String parse(String text) {
		StringBundler sb = new StringBundler();

		Stack<String> tags = new Stack<String>();

		int previousTagEnd = 0;

		boolean isCodeTag = false;
		boolean hasEndTag = false;

		Matcher matcher = _pattern.matcher(text);

		while (matcher.find(previousTagEnd)) {
			String tag = matcher.group(1);

			int start = matcher.start();
			int end = matcher.end();

			handleData(sb, text, start, previousTagEnd, isCodeTag);

			if (isOpen(tag)) {
				while (matcher.find()) {
					String endTag = matcher.group(1);

					if ((endTag.equals(StringPool.SLASH + tag) &&
						 !tag.equals("url")) ||
						(tag.startsWith("url=") && endTag.equals("/url"))) {

						if (tag.equals("code")) {
							isCodeTag = true;

							handleTagStart(text, sb, matcher, tag, end);

							previousTagEnd = matcher.end();
						}
						else {
							handleTagStart(
								text, sb, matcher, tag, previousTagEnd);
						}

						tags.push(endTag);

						hasEndTag = true;

						break;
					}
				}

				if (!hasEndTag) {
					handleData(sb, text, start, end, isCodeTag);
				}
			}
			else {
				if (tags.contains(tag)) {
					handleTagEnd(sb, tag);

					tags.remove(tag);
				}
				else {
					handleData(sb, text, start, end, isCodeTag);
				}
			}

			if (!isCodeTag) {
				previousTagEnd = end;
			}

			isCodeTag = false;
			hasEndTag = false;
		}

		handleData(sb, text, text.length(), previousTagEnd, isCodeTag);

		return sb.toString();
	}

	protected void handleCode(StringBundler sb, String data) {
		data = data.replaceAll("^(\\n|\\r)+", StringPool.BLANK);
		data = data.replaceAll("(\\n|\\r)+$", StringPool.BLANK);
		data = data.replaceAll(StringPool.TAB, StringPool.FOUR_SPACES);

		String[] lines = data.split("\r?\n");

		String digits = String.valueOf(lines.length + 1);

		StringBundler sb1 = new StringBundler();
		StringBundler sb2 = new StringBundler();

		for (int i = 0; i < lines.length; i++) {
			String index = String.valueOf(i + 1);

			for (int j = 0; j < digits.length() - index.length(); j++) {
				sb1.append(StringPool.NBSP);
			}

			lines[i] = StringUtil.replace(
				lines[i], StringPool.THREE_SPACES, "&nbsp; &nbsp;");
			lines[i] = StringUtil.replace(
				lines[i], StringPool.DOUBLE_SPACE, "&nbsp; ");

			sb1.append(index);

			sb2.append(lines[i]);

			if (index.length() < lines.length) {
				sb1.append("<br />");
				sb2.append("<br />");
			}
		}

		sb.append("<div class=\"code\">");
		sb.append("<table class=\"lfr-table\"><tr><td class=\"unselectable\">");
		sb.append(sb1.toString());
		sb.append("</td><td>");
		sb.append(sb2.toString());
		sb.append("</td></tr></table></div>");
	}

	protected void handleData(
		StringBundler sb, String text, int curTagStart, int previousTagEnd,
		boolean isCodeTag) {

		if ((curTagStart > previousTagEnd) && !isCodeTag) {
			sb.append(text.substring(previousTagEnd, curTagStart));
		}
		else if (curTagStart < previousTagEnd) {
			sb.append(text.substring(curTagStart, previousTagEnd));
		}
	}

	protected void handleQuoteStart(StringBundler sb) {
		sb.append("<div class=\"quote\"><div class=\"quote-content\">");
	}

	protected void handleSimpleTagStart(StringBundler sb, String tag) {
		if (tag.equals("/b")) {
			sb.append("<strong>");
		}
		else if (tag.equals("/i")) {
			sb.append("<em>");
		}
		else if (tag.equals("/u")) {
			sb.append("<u>");
		}
	}

	protected void handleTagEnd(StringBundler sb, String tag) {
		if (tag.equals("/b")) {
			sb.append("</strong>");
		}
		else if (tag.equals("/i")) {
			sb.append("</em>");
		}
		else if (tag.equals("/quote")) {
			sb.append("</div></div>");
		}
		else if (tag.equals("/u")) {
			sb.append("</u>");
		}
		else if (tag.equals("/url")) {
			sb.append("</a>");
		}
	}

	protected void handleTagStart(
		String text, StringBundler sb, Matcher matcher, String startTag,
		int previousTagEnd) {

		String tag = matcher.group(1);

		if (tag.equals("/code")) {
			handleCode(sb, text.substring(previousTagEnd, matcher.start()));
		}
		else if (tag.equals("/quote")) {
			handleQuoteStart(sb);
		}
		else if (tag.equals("/url")) {
			handleUrl(sb, startTag);
		}
		else {
			handleSimpleTagStart(sb, tag);
		}
	}

	protected void handleUrl(StringBundler sb, String tag) {
		String url = tag.substring(4);

		sb.append("<a href=\"");
		sb.append(url);
		sb.append("\" target=\"_blank\">");
	}

	protected boolean isOpen(String tag) {
		if (Validator.isNull(tag)) {
			return false;
		}

		if (StringUtil.startsWith(tag, StringPool.SLASH)) {
			return false;
		}

		return true;
	}

	private static Pattern _pattern = Pattern.compile(
		"\\[(/?(?:b|i|u|code|quote|(?:(url=https?://[-\\p{L}0-9~!@#%&()_+=|:;" +
			",.?/]+)|url)))\\]",
		Pattern.CASE_INSENSITIVE);

}