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

package com.liferay.osb.marketplaceserver.processor;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @author Ryan Park
 */
public class MarketplaceServerResponseProcessor {

	public String process() {
		StopWatch stopWatch = null;

		if (_log.isInfoEnabled()) {
			stopWatch = new StopWatch();

			stopWatch.start();
		}

		rewriteEscapedURLs();
		rewriteURLs();

		rewriteInputNameTags();
		rewriteResourceAttributes();
		rewriteScriptTags();

		rewritePortletNamespace();

		if (_log.isInfoEnabled()) {
			stopWatch.stop();

			_log.info("Processed response in " + stopWatch.getTime() + "ms");
		}

		return _html;
	}

	public void setCDNHost(String cdnHost) {
		_cdnHost = cdnHost;
	}

	public void setClientAuthToken(String clientAuthToken) {
		_clientAuthToken = clientAuthToken;

		_marketplaceServerURLProcessor = null;
	}

	public void setClientPortletId(String clientPortletId) {
		_clientPortletId = clientPortletId;

		_clientPortletNamespace = PortalUtil.getPortletNamespace(
			_clientPortletId);

		_marketplaceServerURLProcessor = null;
	}

	public void setClientURL(String clientURL) {
		_clientURL = clientURL;

		_marketplaceServerURLProcessor = null;
	}

	public void setHTML(String html) {
		_html = html;
	}

	public void setPortalURL(String portalURL) {
		_portalURL = portalURL;
	}

	public void setServerPortletNamespace(String serverPortletNamespace) {
		_serverPortletNamespace = serverPortletNamespace;

		_marketplaceServerURLProcessor = null;
	}

	protected MarketplaceServerURLProcessor getMarketplaceServerURLProcessor() {
		if (_marketplaceServerURLProcessor != null) {
			return _marketplaceServerURLProcessor;
		}

		_marketplaceServerURLProcessor = new MarketplaceServerURLProcessor();

		_marketplaceServerURLProcessor.setClientAuthToken(_clientAuthToken);
		_marketplaceServerURLProcessor.setClientPortletId(_clientPortletId);
		_marketplaceServerURLProcessor.setClientURL(_clientURL);
		_marketplaceServerURLProcessor.setServerPortletNamespace(
			_serverPortletNamespace);

		return _marketplaceServerURLProcessor;
	}

	protected String getPortletName(String portletNamespace) {
		if (portletNamespace.length() > 2) {
			return portletNamespace.substring(1, portletNamespace.length() - 1);
		}

		return portletNamespace;
	}

	protected boolean hasRestrictedDomainNames(String tag) {
		for (String restrictedDomainNames : _restrictedDomainNames) {
			if (tag.contains(restrictedDomainNames)) {
				return true;
			}
		}

		return false;
	}

	protected void rewriteEnclosedJavaScriptFunction() {
		Matcher matcher = _enclosedJavaScriptFunctionPattern.matcher(_html);

		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			String html = matcher.group();

			if (hasRestrictedDomainNames(html)) {
				html = StringPool.BLANK;
			}

			matcher.appendReplacement(sb, Matcher.quoteReplacement(html));
		}

		matcher.appendTail(sb);

		_html = sb.toString();
	}

	protected void rewriteEscapedURLs() {
		Matcher matcher = _escapedURLPattern.matcher(_html);

		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			String url = matcher.group();

			url = StringEscapeUtils.unescapeHtml4(url);

			if (url.contains("/mpserver")) {
				url = rewriteURL(url);
			}

			url = HtmlUtil.escapeAttribute(url);

			matcher.appendReplacement(sb, url);
		}

		matcher.appendTail(sb);

		_html = sb.toString();
	}

	protected String rewriteInputNameTag(String tag) {
		int x = tag.indexOf("name=\"");

		if (x < 0) {
			return tag;
		}

		int y = tag.indexOf(StringPool.QUOTE, x + 6);

		String name = tag.substring(x + 6, y);

		if (!name.equals("p_p_state")) {
			return tag;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(tag.substring(0, x + 6));
		sb.append("remoteWindowState");
		sb.append(tag.substring(y));

		sb.append("<input name=\"p_p_state\" type=\"hidden\" value=\"");
		sb.append(LiferayWindowState.EXCLUSIVE.toString());
		sb.append("\" />");

		return sb.toString();
	}

	protected void rewriteInputNameTags() {
		Matcher matcher = _inputTagPattern.matcher(_html);

		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			String inputTag = matcher.group();

			inputTag = rewriteInputNameTag(inputTag);

			matcher.appendReplacement(sb, inputTag);
		}

		matcher.appendTail(sb);

		_html = sb.toString();
	}

	protected String rewriteLiferayMenuInclude(String tag) {
		String include = "'liferay-menu',";

		int x = tag.indexOf(include);

		if (x < 0) {
			include = "\"liferay-menu\",";

			x = tag.indexOf(include);
		}

		if (x < 0) {
			return tag;
		}

		tag = tag.substring(0, x) + tag.substring(x + include.length());

		String constructor = "new Liferay.Menu();";

		x = tag.indexOf(constructor, x);

		if (x < 0) {
			return tag;
		}

		String emptyObject = "Liferay.Menu={register:function(){}};";

		tag =
			tag.substring(0, x) + emptyObject +
				tag.substring(x + constructor.length());

		return tag;
	}

	protected String rewriteModulePathResources(String tag) {
		if (!tag.contains("/combo") || Validator.isNotNull(_cdnHost)) {
			return tag;
		}

		return tag.replace("/combo", _portalURL + "/combo");
	}

	protected void rewritePortletNamespace() {
		String html = _html;

		int x = html.indexOf("<body");
		int y = html.indexOf("</body>", x) + 7;

		if ((x >= 0) && (y >= 0)) {
			html = _html.substring(x, y);
		}

		String portletName = getPortletName(_serverPortletNamespace);
		String clientPortletName = getPortletName(_clientPortletNamespace);

		html = html.replaceAll(portletName, clientPortletName);

		if (x < 0) {
			_html = html;
		}
		else {
			_html = _html.substring(0, x) + html + _html.substring(y);
		}
	}

	protected String rewriteResourceAttribute(String tag) {
		String resourceAttribute = StringPool.BLANK;

		int x = 0;

		for (String curResourceAttribute : _resourceAttributes) {
			x = tag.indexOf(curResourceAttribute);

			if (x >= 0) {
				resourceAttribute = curResourceAttribute;

				break;
			}
		}

		if (x < 0) {
			return tag;
		}

		int resourceAttributeLength = resourceAttribute.length() + 2;

		int y = tag.indexOf(StringPool.QUOTE, x + resourceAttributeLength);

		String url = tag.substring(x + resourceAttributeLength, y);

		boolean escapeURL = false;

		if (url.startsWith(_ESCAPED_FORWARD_SLASH)) {
			url = StringEscapeUtils.unescapeHtml4(url);

			escapeURL = true;
		}

		if (!url.startsWith(StringPool.FORWARD_SLASH)) {
			return tag;
		}

		url = _portalURL + url;

		if (escapeURL) {
			url = HtmlUtil.escapeAttribute(url);
		}

		return
			tag.substring(0, x + resourceAttributeLength) + url +
				tag.substring(y);
	}

	protected void rewriteResourceAttributes() {
		Matcher matcher = _resourceAttributePattern.matcher(_html);

		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			String html = matcher.group();

			html = rewriteResourceAttribute(html);

			matcher.appendReplacement(sb, Matcher.quoteReplacement(html));
		}

		matcher.appendTail(sb);

		_html = sb.toString();
	}

	protected void rewriteScriptTags() {
		Matcher matcher = _scriptTagPattern.matcher(_html);

		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			String html = matcher.group();

			if (hasRestrictedDomainNames(html)) {
				rewriteEnclosedJavaScriptFunction();
			}

			if (html.contains("Liferay.AUI")) {
				html = rewriteModulePathResources(html);
			}

			if (html.contains("liferay-menu")) {
				html = rewriteLiferayMenuInclude(html);
			}

			Matcher attributeMatcher = _scriptTagAttributesPattern.matcher(
				html);

			if (attributeMatcher.find()) {
				String scriptTagAttributes = rewriteResourceAttribute(
					attributeMatcher.group(1));

				html.replace(attributeMatcher.group(1), scriptTagAttributes);
			}

			matcher.appendReplacement(sb, Matcher.quoteReplacement(html));
		}

		matcher.appendTail(sb);

		_html = sb.toString();
	}

	protected String rewriteURL(String url) {
		MarketplaceServerURLProcessor marketplaceServerURLProcessor =
			getMarketplaceServerURLProcessor();

		marketplaceServerURLProcessor.setURL(url);

		return marketplaceServerURLProcessor.process();
	}

	protected void rewriteURLs() {
		Matcher matcher = _urlPattern.matcher(_html);

		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			String url = matcher.group();

			if (url.contains("/mpserver")) {
				url = rewriteURL(url);
			}

			matcher.appendReplacement(sb, url);
		}

		matcher.appendTail(sb);

		_html = sb.toString();
	}

	private static final String _ESCAPED_FORWARD_SLASH = "&#x2f;";

	private static Log _log = LogFactoryUtil.getLog(
		MarketplaceServerResponseProcessor.class);

	private static Pattern _enclosedJavaScriptFunctionPattern = Pattern.compile(
		"\\(function\\((.*?)\\)\\{(.*?)}(.*?)}\\);");
	private static Pattern _escapedURLPattern = Pattern.compile(
		"(https?&#x3a;&#x2f;&#x2f;[a-zA-Z0-9@#%&_+=;:./-]*)");
	private static Pattern _inputTagPattern = Pattern.compile("<input (.*?)>");
	private static Pattern _resourceAttributePattern = Pattern.compile(
		"<[^>]*?(href|src)(.*?)>");
	private static String[] _resourceAttributes = {
		"href", "src"
	};
	private static String[] _restrictedDomainNames = {
		"google", "hubspot", "newrelic"
	};
	private static Pattern _scriptTagAttributesPattern = Pattern.compile(
		"<script(.*?)>(?:.*?)</script>", Pattern.DOTALL);
	private static Pattern _scriptTagPattern = Pattern.compile(
		"<script(.*?)</script>", Pattern.DOTALL);
	private static Pattern _urlPattern = Pattern.compile(
		"(https?://[a-zA-Z0-9@#%_+=;:./-]*(\\?([a-zA-Z0-9(&amp;)%_+=;./-])*)?" +
			"[a-zA-Z0-9#%_+=;/-])");

	private String _cdnHost;
	private String _clientAuthToken;
	private String _clientPortletId;
	private String _clientPortletNamespace;
	private String _clientURL;
	private String _html;
	private MarketplaceServerURLProcessor _marketplaceServerURLProcessor;
	private String _portalURL;
	private String _serverPortletNamespace;

}