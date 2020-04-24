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

package com.liferay.osb.testray.taglib.util;

import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

import com.vladsch.flexmark.Extension;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Ethan Bustad
 */
@Component(
	immediate = true,
	property = {
		"json.web.service.context.name=osb-testray-taglib",
		"json.web.service.context.path=MarkdownUtil"
	},
	service = Object.class
)
@JSONWebService(mode = JSONWebServiceMode.MANUAL, value = "markdown")
public class MarkdownUtil {

	@JSONWebService(method = "GET", value = "markdown-to-html")
	public static String markdownToHtml(String markdown) {
		MarkdownUtil instance = _getInstance();

		return instance._markdownToHtml(markdown);
	}

	@Activate
	public void activate() {
		MutableDataSet mutableOptions = new MutableDataSet();

		List<Extension> extensions = new ArrayList<>();

		_setGitHubFlavoredMarkdownSettings(mutableOptions, extensions);

		mutableOptions.set(HtmlRenderer.SOFT_BREAK, "<br />\n");

		extensions.add(AnchorLinkExtension.create());

		mutableOptions.set(Parser.EXTENSIONS, extensions);

		HtmlRenderer.Builder htmlRendererBuilder = HtmlRenderer.builder(
			mutableOptions);

		_htmlRenderer = htmlRendererBuilder.build();

		Parser.Builder parserBuilder = Parser.builder(mutableOptions);

		_parser = parserBuilder.build();

		_instance = this;
	}

	private static MarkdownUtil _getInstance() {
		if (_instance == null) {
			_instance = new MarkdownUtil();
		}

		return _instance;
	}

	private static void _setGitHubFlavoredMarkdownSettings(
		MutableDataSet mutableOptions, List<Extension> extensions) {

		extensions.add(AutolinkExtension.create());
		extensions.add(StrikethroughExtension.create());
		extensions.add(TablesExtension.create());

		mutableOptions.set(TablesExtension.APPEND_MISSING_COLUMNS, true);
		mutableOptions.set(TablesExtension.COLUMN_SPANS, false);
		mutableOptions.set(TablesExtension.DISCARD_EXTRA_COLUMNS, true);
		mutableOptions.set(TablesExtension.HEADER_SEPARATOR_COLUMN_MATCH, true);
		mutableOptions.set(TablesExtension.MAX_HEADER_ROWS, 1);
		mutableOptions.set(TablesExtension.MIN_HEADER_ROWS, 1);
		mutableOptions.set(TablesExtension.WITH_CAPTION, false);
	}

	private String _markdownToHtml(String markdown) {
		Node node = _parser.parse(markdown);

		return _htmlRenderer.render(node);
	}

	private static MarkdownUtil _instance;

	private HtmlRenderer _htmlRenderer;
	private Parser _parser;

}