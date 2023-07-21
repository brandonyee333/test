/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import com.steadystate.css.parser.CSSOMParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSStyleRule;
import org.w3c.dom.css.CSSStyleSheet;

/**
 * @author Timothy Bell
 */
public class LoopSassUtil {

	public static String injectCSS(String html, String... cssStyleSheets)
		throws Exception {

		Document document = Jsoup.parse(html);

		Map<Element, Map<String, String>> elementStyles = new HashMap<>();

		for (String css : cssStyleSheets) {
			InputStream inputStream = new ByteArrayInputStream(
				css.getBytes(StandardCharsets.UTF_8));

			InputSource inputSource = new InputSource(
				new InputStreamReader(inputStream));

			CSSStyleSheet cssStyleSheet = _cssomParser.parseStyleSheet(
				inputSource, null, null);

			CSSRuleList cssRuleList = cssStyleSheet.getCssRules();

			for (int i = 0; i < cssRuleList.getLength(); i++) {
				CSSRule cssRule = cssRuleList.item(i);

				if (!(cssRule instanceof CSSStyleRule)) {
					continue;
				}

				CSSStyleRule cssStyleRule = (CSSStyleRule)cssRule;

				String selector = cssStyleRule.getSelectorText();

				if (selector.contains(StringPool.COLON)) {
					continue;
				}

				selector = StringUtil.replace(
					selector, "*.markdown-content", StringPool.BLANK);

				Elements selectedElements = document.select(selector);

				for (Element selected : selectedElements) {
					if (!elementStyles.containsKey(selected)) {
						elementStyles.put(
							selected, new LinkedHashMap<String, String>());
					}

					CSSStyleDeclaration styleDeclaration =
						cssStyleRule.getStyle();

					for (int j = 0; j < styleDeclaration.getLength(); j++) {
						Map<String, String> elementStyle = elementStyles.get(
							selected);

						String propertyName = styleDeclaration.item(j);

						String propertyValue =
							styleDeclaration.getPropertyValue(propertyName);

						elementStyle.put(propertyName, propertyValue);
					}
				}
			}
		}

		for (Map.Entry<Element, Map<String, String>> elementEntry :
				elementStyles.entrySet()) {

			Element element = elementEntry.getKey();

			Map<String, String> styleEntries = elementEntry.getValue();

			StringBuilder sb = new StringBuilder((styleEntries.size() * 4) + 1);

			for (Map.Entry<String, String> styleEntry :
					styleEntries.entrySet()) {

				sb.append(styleEntry.getKey());
				sb.append(StringPool.COLON);
				sb.append(styleEntry.getValue());
				sb.append(StringPool.SEMICOLON);
			}

			sb.append(element.attr("style"));

			element.attr("style", sb.toString());

			element.removeAttr("class");
		}

		return document.html();
	}

	private static final CSSOMParser _cssomParser = new CSSOMParser();

}