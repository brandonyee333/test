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

import com.liferay.osb.loop.constants.LoopConstants;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.service.LoopDivisionLocalServiceUtil;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.service.LoopTopicLocalServiceUtil;
import com.liferay.osb.loop.web.internal.constants.LoopAssetEntrySetConstants;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import com.vladsch.flexmark.Extension;
import com.vladsch.flexmark.LoopAttributeProvider;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.AttributeProvider;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.IndependentAttributeProviderFactory;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/**
 * @author Calvin Keum
 */
public class LoopMarkdownUtil {

	public static void appendSharedTo(JSONObject payloadJSONObject) {
		JSONArray sharedToJSONArray = payloadJSONObject.getJSONArray(
			LoopAssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO);

		Matcher atMentionTokenMatcher = _atMentionTokenPattern.matcher(
			payloadJSONObject.getString("message"));

		while (atMentionTokenMatcher.find()) {
			boolean missingSharedTo = true;

			long entityClassNameId = GetterUtil.getLong(
				atMentionTokenMatcher.group(2));
			long entityClassPK = GetterUtil.getLong(
				atMentionTokenMatcher.group(3));

			for (int i = 0; i < sharedToJSONArray.length(); i++) {
				JSONObject sharedToJSONObject = sharedToJSONArray.getJSONObject(
					i);

				if ((sharedToJSONObject.getLong("entityClassNameId") ==
						entityClassNameId) &&
					(sharedToJSONObject.getLong("entityClassPK") ==
						entityClassPK)) {

					missingSharedTo = false;

					break;
				}
			}

			if (missingSharedTo) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put("entityClassNameId", entityClassNameId);
				jsonObject.put("entityClassPK", entityClassPK);

				sharedToJSONArray.put(jsonObject);
			}
		}

		payloadJSONObject.put(
			LoopAssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO,
			sharedToJSONArray);
	}

	public static Document.OutputSettings getDefaultOutputSettings() {
		return _defaultOutputSettings;
	}

	public static Whitelist getDefaultWhiteList() {
		return _defaultWhitelist;
	}

	public static String interpretTokens(
			long companyId, String htmlSource, JSONArray sharedToJSONArray,
			boolean generateHtmlLinks)
		throws Exception {

		StringBuffer stringBuffer = new StringBuffer();

		Matcher atMentionTokenMatcher = _atMentionTokenPattern.matcher(
			htmlSource);

		while (atMentionTokenMatcher.find()) {
			String replacement = StringPool.BLANK;

			long entityClassNameId = GetterUtil.getLong(
				atMentionTokenMatcher.group(2));
			long entityClassPK = GetterUtil.getLong(
				atMentionTokenMatcher.group(3));

			if (entityClassNameId == PortalUtil.getClassNameId(
					LoopDivision.class)) {

				LoopDivision loopDivision =
					LoopDivisionLocalServiceUtil.fetchLoopDivision(
						entityClassPK);

				if (loopDivision != null) {
					Organization organization =
						OrganizationLocalServiceUtil.getOrganization(
							loopDivision.getOrganizationId());

					if (generateHtmlLinks) {
						replacement = generateHtmlLink(
							_CLASS_AT_MENTION, organization.getName(),
							LoopUtil.getDisplayURL(
								companyId,
								PortalUtil.getClassNameId(LoopDivision.class),
								entityClassPK),
							entityClassPK,
							PortalUtil.getClassNameId(LoopDivision.class));
					}
					else {
						replacement = organization.getName();
					}
				}
			}
			else if (entityClassNameId == PortalUtil.getClassNameId(
						LoopPerson.class)) {

				LoopPerson loopPerson =
					LoopPersonLocalServiceUtil.fetchLoopPerson(entityClassPK);

				if (loopPerson != null) {
					User user = UserLocalServiceUtil.getUser(
						loopPerson.getPersonUserId());

					if (generateHtmlLinks) {
						replacement = generateHtmlLink(
							_CLASS_AT_MENTION, user.getFullName(),
							LoopUtil.getDisplayURL(
								companyId,
								PortalUtil.getClassNameId(LoopPerson.class),
								entityClassPK),
							entityClassPK,
							PortalUtil.getClassNameId(LoopPerson.class));
					}
					else {
						replacement = user.getFullName();
					}
				}
			}

			if (Validator.isNull(replacement) && (sharedToJSONArray != null)) {
				for (int i = 0; i < sharedToJSONArray.length(); i++) {
					JSONObject sharedToJSONObject =
						sharedToJSONArray.getJSONObject(i);

					if ((entityClassNameId == sharedToJSONObject.getLong(
							"entityClassNameId")) &&
						(entityClassPK == sharedToJSONObject.getLong(
							"entityClassPK"))) {

						replacement = sharedToJSONObject.getString("name");

						break;
					}
				}
			}

			if (Validator.isNull(replacement)) {
				replacement = atMentionTokenMatcher.group();
			}

			appendReplacement(atMentionTokenMatcher, stringBuffer, replacement);
		}

		atMentionTokenMatcher.appendTail(stringBuffer);

		htmlSource = stringBuffer.toString();

		stringBuffer = new StringBuffer();

		Matcher hashtagTokenMatcher = _hashtagTokenPattern.matcher(htmlSource);

		while (hashtagTokenMatcher.find()) {
			try {
				LoopTopic loopTopic = LoopTopicLocalServiceUtil.getLoopTopic(
					companyId, hashtagTokenMatcher.group(2));

				String replacement = StringPool.BLANK;

				if (generateHtmlLinks) {
					replacement = generateHtmlLink(
						_CLASS_TOPIC, StringPool.POUND + loopTopic.getName(),
						LoopUtil.getDisplayURL(
							companyId,
							PortalUtil.getClassNameId(LoopTopic.class),
							loopTopic.getLoopTopicId()),
						loopTopic.getLoopTopicId(),
						PortalUtil.getClassNameId(LoopTopic.class));
				}
				else {
					replacement = StringPool.POUND + loopTopic.getName();
				}

				appendReplacement(
					hashtagTokenMatcher, stringBuffer, replacement);
			}
			catch (Exception e) {
				appendReplacement(
					hashtagTokenMatcher, stringBuffer,
					hashtagTokenMatcher.group(1));
			}
		}

		hashtagTokenMatcher.appendTail(stringBuffer);

		return stringBuffer.toString();
	}

	public static String markdownToHtml(
			long companyId, String markdownSource, boolean tokenize,
			boolean interpretTokens)
		throws Exception {

		return markdownToHtml(
			companyId, markdownSource, tokenize, interpretTokens, false);
	}

	public static String markdownToHtml(
			long companyId, String markdownSource, boolean tokenize,
			boolean interpretTokens, boolean anchorLinkEnabled)
		throws Exception {

		if (Validator.isNull(markdownSource)) {
			return StringPool.BLANK;
		}

		markdownSource = StringUtil.replace(
			markdownSource, _NO_BREAK_SPACE_CHARACTER, StringPool.SPACE);

		if (tokenize) {
			markdownSource = tokenizeHashtags(markdownSource);
		}

		List<Extension> extensions = new ArrayList<>(_defaultExtensions);

		if (anchorLinkEnabled) {
			extensions.add(AnchorLinkExtension.create());
		}

		MutableDataSet options = new MutableDataSet(getDefaultOptions());

		options.set(Parser.EXTENSIONS, extensions);

		Parser.Builder parserBuilder = Parser.builder(options);

		Parser parser = parserBuilder.build();

		Node document = parser.parse(markdownSource);

		HtmlRenderer.Builder htmlRendererBuilder = HtmlRenderer.builder(
			options);

		htmlRendererBuilder.attributeProviderFactory(
			new IndependentAttributeProviderFactory() {

				@Override
				public AttributeProvider create(
					NodeRendererContext nodeRendererContext) {

					return new LoopAttributeProvider();
				}

			});

		HtmlRenderer htmlRenderer = htmlRendererBuilder.build();

		String markdownHtml = htmlRenderer.render(document);

		Company company = CompanyLocalServiceUtil.getCompany(companyId);

		String portalURL = PortalUtil.getPortalURL(
			company.getVirtualHostname(), Http.HTTPS_PORT, true);

		markdownHtml = Jsoup.clean(
			markdownHtml, portalURL, getDefaultWhiteList(),
			getDefaultOutputSettings());

		if (interpretTokens) {
			markdownHtml = interpretTokens(companyId, markdownHtml);
		}

		return markdownHtml;
	}

	protected static void appendReplacement(
		Matcher matcher, StringBuffer stringBuffer, String replacement) {

		replacement = StringUtil.replace(
			replacement, '$', StringPool.BACK_SLASH + StringPool.DOLLAR);

		matcher.appendReplacement(stringBuffer, replacement);
	}

	protected static String generateHtmlLink(
		String hrefClass, String name, String url, long classPK,
		long classNameId) {

		StringBundler sb = new StringBundler(11);

		sb.append("<a class=\"");
		sb.append(hrefClass);
		sb.append("\" href=\"");
		sb.append(url);
		sb.append("\" data-summaryId=\"");
		sb.append(classNameId);
		sb.append(StringPool.DASH);
		sb.append(classPK);
		sb.append("\">");
		sb.append(name);
		sb.append("</a>");

		return sb.toString();
	}

	protected static MutableDataSet getDefaultOptions() {
		return _defaultOptions;
	}

	protected static String interpretTokens(long companyId, String htmlSource)
		throws Exception {

		return interpretTokens(companyId, htmlSource, null, true);
	}

	protected static String tokenizeHashtags(String markdownSource) {
		StringBuffer stringBuffer = new StringBuffer();

		Matcher hashtagMatcher = _hashtagPattern.matcher(markdownSource);

		while (hashtagMatcher.find()) {
			if (hashtagMatcher.start() > 0) {
				char prevChar = markdownSource.charAt(
					hashtagMatcher.start() - 1);

				if (!Character.isWhitespace(prevChar)) {
					continue;
				}
			}

			appendReplacement(
				hashtagMatcher, stringBuffer,
				LoopConstants.ENCODE_TOKEN + hashtagMatcher.group(1) +
					LoopConstants.ENCODE_TOKEN);
		}

		hashtagMatcher.appendTail(stringBuffer);

		return stringBuffer.toString();
	}

	private static final String _CLASS_AT_MENTION = "mention";

	private static final String _CLASS_TOPIC = "topic";

	private static final String _NO_BREAK_SPACE_CHARACTER = "\u00A0";

	private static final String _REGEX_AT_MENTION = "(\\@(\\d+)\\:(\\d+))";

	private static final String _REGEX_ENCODE_TOKEN = Pattern.quote(
		LoopConstants.ENCODE_TOKEN);

	private static final String _REGEX_HASHTAG = "(\\#(?!\\d)(\\w+))";

	private static final Pattern _atMentionTokenPattern = Pattern.compile(
		_REGEX_ENCODE_TOKEN + _REGEX_AT_MENTION + _REGEX_ENCODE_TOKEN);
	private static final ArrayList<Extension> _defaultExtensions =
		new ArrayList<>();
	private static final MutableDataSet _defaultOptions = new MutableDataSet();
	private static final Document.OutputSettings _defaultOutputSettings =
		new Document.OutputSettings();
	private static final Whitelist _defaultWhitelist = Whitelist.relaxed();
	private static final Pattern _hashtagPattern = Pattern.compile(
		_REGEX_HASHTAG);
	private static final Pattern _hashtagTokenPattern = Pattern.compile(
		_REGEX_ENCODE_TOKEN + _REGEX_HASHTAG + _REGEX_ENCODE_TOKEN);

	static {
		_defaultExtensions.add(AutolinkExtension.create());
		_defaultExtensions.add(StrikethroughExtension.create());
		_defaultExtensions.add(TablesExtension.create());

		_defaultOptions.set(HtmlRenderer.ESCAPE_HTML, true);
		_defaultOptions.set(HtmlRenderer.GENERATE_HEADER_ID, true);
		_defaultOptions.set(HtmlRenderer.SOFT_BREAK, "<br />\n");

		_defaultOutputSettings.prettyPrint(false);

		_defaultWhitelist.addAttributes(
			"a", "class", "href", "id", "rel", "target");
		_defaultWhitelist.addTags("del", "hr");
		_defaultWhitelist.preserveRelativeLinks(true);
	}

}