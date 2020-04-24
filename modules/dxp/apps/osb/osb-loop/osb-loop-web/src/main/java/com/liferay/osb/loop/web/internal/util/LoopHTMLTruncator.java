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

package com.liferay.osb.loop.web.internal.util;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Tag;

/**
 * @author Jonathan Lee
 * @author Timothy Bell
 */
public class LoopHTMLTruncator {

	public LoopHTMLTruncator(
		String portalURL, String html, int lineCharCount,
		int lineCharCountTableElement, int lineCountGrace, int lineCountImage,
		int lineCountImageTableElement, int lineCountMax) {

		_html = Jsoup.clean(
			removeNewLines(html), portalURL,
			LoopMarkdownUtil.getDefaultWhiteList(),
			LoopMarkdownUtil.getDefaultOutputSettings());
		_lineCharCount = lineCharCount;
		_lineCharCountTableElement = lineCharCountTableElement;
		_lineCountGrace = lineCountGrace;
		_lineCountImage = lineCountImage;
		_lineCountImageTableElement = lineCountImageTableElement;
		_lineCountMax = lineCountMax;

		_baseURI = portalURL;
	}

	public String truncateHtml() {
		String truncatedHtml = StringPool.BLANK;

		Document document = Jsoup.parse(_html);

		document.outputSettings(LoopMarkdownUtil.getDefaultOutputSettings());

		Element element = document.body();

		setTruncatePos(element.childNodes());

		if (_lineCountCur < (_lineCountMax + _lineCountGrace)) {
			return truncatedHtml;
		}

		if (_truncateText) {
			truncatedHtml = StringUtil.shorten(_html, _truncatePos);
		}
		else {
			truncatedHtml = _html.substring(0, _truncatePos);
		}

		truncatedHtml = Jsoup.clean(
			truncatedHtml, _baseURI, LoopMarkdownUtil.getDefaultWhiteList(),
			LoopMarkdownUtil.getDefaultOutputSettings());

		if (StringUtil.equalsIgnoreCase(truncatedHtml, _html)) {
			truncatedHtml = StringPool.BLANK;
		}

		return truncatedHtml;
	}

	protected int calculateTableElementLineCount(
		List<Node> childNodes, int lineCount) {

		for (Node childNode : childNodes) {
			if (childNode instanceof TextNode) {
				String ownText = childNode.toString();

				if (Validator.isNull(ownText)) {
					continue;
				}

				int ownTextLineCount =
					(ownText.length() + _lineCharCountTableElement - 1) /
						_lineCharCountTableElement;

				lineCount += ownTextLineCount;
			}
			else if (childNode instanceof Element) {
				if (childNode.childNodeSize() > 0) {
					lineCount = calculateTableElementLineCount(
						childNode.childNodes(), lineCount);
				}
				else {
					Element nestedChildElement = (Element)childNode;

					Tag tag = nestedChildElement.tag();

					String tagName = tag.getName();

					if (StringUtil.equalsIgnoreCase(tagName, "img")) {
						lineCount += _lineCountImageTableElement;
					}
				}
			}
		}

		return lineCount;
	}

	protected int calculateTableRowLineCount(Node node) {
		int tableRowLineCount = 1;

		for (Node childNode : node.childNodes()) {
			int tableElementLineCount = calculateTableElementLineCount(
				childNode.childNodes(), 0);

			if (tableElementLineCount > tableRowLineCount) {
				tableRowLineCount = tableElementLineCount;
			}
		}

		return tableRowLineCount;
	}

	protected String removeNewLines(String html) {
		String[] splitHtmls = html.split(StringPool.NEW_LINE);

		StringBundler sb = new StringBundler(splitHtmls.length);

		for (String splitHtml : splitHtmls) {
			sb.append(splitHtml.trim());
		}

		return sb.toString();
	}

	protected void setTruncatePos(int lineCount) {
		if ((_lineCountCur < _lineCountMax) &&
			((_lineCountCur + lineCount) >= _lineCountMax)) {

			_truncatePos = _curPos;
		}

		_lineCountCur += lineCount;
	}

	protected void setTruncatePos(List<Node> childNodes) {
		for (Node childNode : childNodes) {
			if (_lineCountCur > (_lineCountMax + _lineCountGrace)) {
				break;
			}

			if (childNode instanceof Element) {
				Element childElement = (Element)childNode;

				Tag tag = childElement.tag();

				String tagName = tag.getName();

				int lineCount = 0;

				if (tag.isSelfClosing() ||
					StringUtil.equalsIgnoreCase(tagName, "a") ||
					StringUtil.equalsIgnoreCase(tagName, "tr")) {

					if (StringUtil.equalsIgnoreCase(tagName, "tr")) {
						int tableRowLineCount = calculateTableRowLineCount(
							childNode);

						setTruncatePos(tableRowLineCount);
					}

					String nodeString = childNode.toString();

					_curPos += nodeString.length();

					if (StringUtil.equalsIgnoreCase(tagName, "br")) {
						lineCount = 1;
					}
					else if (StringUtil.equalsIgnoreCase(tagName, "img")) {
						lineCount = _lineCountImage;
					}
				}
				else {
					_curPos += tagName.length() + _TAG_OPEN_CHAR_COUNT;

					if (childElement.childNodeSize() > 0) {
						setTruncatePos(childElement.childNodes());
					}

					_curPos += tagName.length() + _TAG_CLOSING_CHAR_COUNT;

					if (_tagNamesTextLineBreak.contains(
							StringUtil.toLowerCase(tagName))) {

						lineCount = 1;
					}
				}

				if (lineCount > 0) {
					setTruncatePos(lineCount);
				}
			}
			else if (childNode instanceof TextNode) {
				String ownText = childNode.toString();

				int ownTextLineCount = ownText.length() / _lineCharCount;

				for (int i = 1; i <= ownTextLineCount; i++) {
					_lineCountCur++;

					if (_lineCountCur == _lineCountMax) {
						_curPos += i * _lineCharCount;

						_truncatePos = _curPos;

						_truncateText = true;
					}
				}

				if (_truncatePos == 0) {
					_curPos += ownText.length();
				}
			}
		}
	}

	private static final int _TAG_CLOSING_CHAR_COUNT = 3;

	private static final int _TAG_OPEN_CHAR_COUNT = 2;

	private static final List<String> _tagNamesTextLineBreak = Arrays.asList(
		"h1", "h2", "h3", "h4", "h5", "h6", "li", "p", "pre");

	private final String _baseURI;
	private int _curPos;
	private final String _html;
	private final int _lineCharCount;
	private final int _lineCharCountTableElement;
	private int _lineCountCur;
	private final int _lineCountGrace;
	private final int _lineCountImage;
	private final int _lineCountImageTableElement;
	private final int _lineCountMax;
	private int _truncatePos;
	private boolean _truncateText;

}