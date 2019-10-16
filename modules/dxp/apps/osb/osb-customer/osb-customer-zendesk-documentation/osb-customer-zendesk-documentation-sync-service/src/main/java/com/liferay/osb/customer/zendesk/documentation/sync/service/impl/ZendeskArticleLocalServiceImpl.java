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

package com.liferay.osb.customer.zendesk.documentation.sync.service.impl;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.documentation.sync.configuration.ZendeskDocumentationSyncConfigurationValues;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection;
import com.liferay.osb.customer.zendesk.documentation.sync.service.base.ZendeskArticleLocalServiceBaseImpl;
import com.liferay.osb.customer.zendesk.util.ZendeskLocaleUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * @author Amos Fong
 */
public class ZendeskArticleLocalServiceImpl
	extends ZendeskArticleLocalServiceBaseImpl {

	public ZendeskArticle addZendeskArticle(
			long zendeskSectionId, String documentationKey,
			String documentationOriginalURL, Map<Locale, String> remoteTitleMap,
			Map<Locale, String> remoteBodyMap,
			String previousArticleDocumentationKey,
			String nextArticleDocumentationKey, int position,
			long remoteUserSegmentId, String[] labelNames,
			Map<String, byte[]> attachments)
		throws PortalException {

		// Zendesk article

		if (_log.isInfoEnabled()) {
			_log.info("Adding article " + documentationKey);
		}

		ZendeskSection zendeskSection =
			zendeskSectionPersistence.findByPrimaryKey(zendeskSectionId);

		JSONObject jsonObject = addRemoteZendeskArticle(
			zendeskSection.getRemoteId(), remoteTitleMap, remoteBodyMap,
			position, remoteUserSegmentId, labelNames);

		JSONObject articleJSONObject = jsonObject.getJSONObject("article");

		long zendeskArticleId = counterLocalService.increment();

		ZendeskArticle zendeskArticle = zendeskArticlePersistence.create(
			zendeskArticleId);

		zendeskArticle.setModifiedDate(new Date());
		zendeskArticle.setZendeskCategoryId(
			zendeskSection.getZendeskCategoryId());
		zendeskArticle.setZendeskSectionId(zendeskSectionId);
		zendeskArticle.setDocumentationKey(documentationKey);
		zendeskArticle.setDocumentationOriginalURL(documentationOriginalURL);
		zendeskArticle.setPreviousArticleDocumentationKey(
			previousArticleDocumentationKey);
		zendeskArticle.setNextArticleDocumentationKey(
			nextArticleDocumentationKey);
		zendeskArticle.setRemoteId(articleJSONObject.getLong("id"));
		zendeskArticle.setRemoteHtmlURL(
			articleJSONObject.getString("html_url"));
		zendeskArticle.setRemoteTitleMap(remoteTitleMap);

		zendeskArticlePersistence.update(zendeskArticle);

		// Zendesk article attachments

		List<ZendeskArticleAttachment> zendeskArticleAttachments =
			new ArrayList<>();

		for (Map.Entry<String, byte[]> entry : attachments.entrySet()) {
			ZendeskArticleAttachment zendeskArticleAttachment =
				zendeskArticleAttachmentLocalService.
					addZendeskArticleAttachment(
						zendeskArticleId, entry.getKey(), entry.getValue());

			zendeskArticleAttachments.add(zendeskArticleAttachment);
		}

		// Zendesk article translations

		for (Locale locale : remoteTitleMap.keySet()) {
			String remoteBody = transformAttachmentLinks(
				remoteBodyMap.get(locale), zendeskArticleAttachments);

			updateRemoteZendeskTranslations(
				zendeskArticle.getRemoteId(), locale,
				remoteTitleMap.get(locale), remoteBody);
		}

		return zendeskArticle;
	}

	public ZendeskArticle deleteZendeskArticle(ZendeskArticle zendeskArticle)
		throws PortalException {

		if (_log.isInfoEnabled()) {
			_log.info(
				"Deleting article " + zendeskArticle.getDocumentationKey());
		}

		_zendeskBaseWebService.delete(
			ZendeskRESTEndpoints.URL_API_V2 + "help_center/articles/" +
				zendeskArticle.getRemoteId() + ".json",
			StringPool.BLANK);

		return zendeskArticlePersistence.remove(zendeskArticle);
	}

	public ZendeskArticle fetchZendeskArticle(
		long zendeskCategoryId, String documentationKey) {

		return zendeskArticlePersistence.fetchByZCI_DK(
			zendeskCategoryId, documentationKey);
	}

	public ZendeskArticle fetchZendeskArticle(String documentationOriginalURL) {
		return zendeskArticlePersistence.fetchByDocumentationOriginalURL(
			documentationOriginalURL);
	}

	public int getZendeskArticleCount(long zendeskSectionId) {
		return zendeskArticlePersistence.countByZendeskSectionId(
			zendeskSectionId);
	}

	public List<ZendeskArticle> getZendeskCategoryArticles(
		long zendeskCategoryId) {

		return zendeskArticlePersistence.findByZendeskCategoryId(
			zendeskCategoryId);
	}

	public ZendeskArticle updateRemoteUserSegmentId(
			long zendeskArticleId, long remoteUserSegmentId)
		throws PortalException {

		ZendeskArticle zendeskArticle =
			zendeskArticlePersistence.findByPrimaryKey(zendeskArticleId);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Updating article user segment " +
					zendeskArticle.getDocumentationKey());
		}

		updateRemoteZendeskArticle(
			zendeskArticle.getRemoteId(), 0, -1, remoteUserSegmentId, null);

		return zendeskArticle;
	}

	public ZendeskArticle updateZendeskArticle(
			long zendeskArticleId, long zendeskSectionId,
			String documentationKey, String documentationOriginalURL,
			Map<Locale, String> remoteTitleMap,
			Map<Locale, String> remoteBodyMap,
			String previousArticleDocumentationKey,
			String nextArticleDocumentationKey, int position,
			long remoteUserSegmentId, String[] labelNames,
			Map<String, byte[]> attachments)
		throws PortalException {

		// Zendesk article

		if (_log.isInfoEnabled()) {
			_log.info("Updating article " + documentationKey);
		}

		ZendeskArticle zendeskArticle =
			zendeskArticlePersistence.findByPrimaryKey(zendeskArticleId);

		ZendeskSection zendeskSection =
			zendeskSectionPersistence.findByPrimaryKey(zendeskSectionId);

		updateRemoteZendeskArticle(
			zendeskArticle.getRemoteId(), zendeskSection.getRemoteId(),
			position, remoteUserSegmentId, labelNames);

		// Zendesk article attachments

		List<ZendeskArticleAttachment> curZendeskArticleAttachments =
			zendeskArticleAttachmentLocalService.getZendeskArticleAttachments(
				zendeskArticleId);

		for (ZendeskArticleAttachment zendeskArticleAttachment :
				curZendeskArticleAttachments) {

			if (!attachments.containsKey(
					zendeskArticleAttachment.getFilePath())) {

				zendeskArticleAttachmentLocalService.
					deleteZendeskArticleAttachment(zendeskArticleAttachment);
			}
		}

		List<ZendeskArticleAttachment> zendeskArticleAttachments =
			new ArrayList<>();

		for (Map.Entry<String, byte[]> entry : attachments.entrySet()) {
			ZendeskArticleAttachment zendeskArticleAttachment =
				zendeskArticleAttachmentLocalService.
					updateZendeskArticleAttachment(
						zendeskArticleId, entry.getKey(), entry.getValue());

			zendeskArticleAttachments.add(zendeskArticleAttachment);
		}

		// Zendesk article translations

		Map<Locale, String> curRemoteTitleMap =
			zendeskArticle.getRemoteTitleMap();

		for (Locale locale : remoteTitleMap.keySet()) {
			if (Objects.equals(
					curRemoteTitleMap.get(LocaleUtil.US),
					curRemoteTitleMap.get(locale))) {

				String remoteBody = transformAttachmentLinks(
					remoteBodyMap.get(locale), zendeskArticleAttachments);

				updateRemoteZendeskTranslations(
					zendeskArticle.getRemoteId(), locale,
					remoteTitleMap.get(locale), remoteBody);
			}
			else {
				remoteTitleMap.put(locale, curRemoteTitleMap.get(locale));
			}
		}

		zendeskArticle.setModifiedDate(new Date());
		zendeskArticle.setZendeskSectionId(zendeskSectionId);
		zendeskArticle.setDocumentationOriginalURL(documentationOriginalURL);
		zendeskArticle.setPreviousArticleDocumentationKey(
			previousArticleDocumentationKey);
		zendeskArticle.setNextArticleDocumentationKey(
			nextArticleDocumentationKey);
		zendeskArticle.setRemoteTitleMap(remoteTitleMap);

		return zendeskArticlePersistence.update(zendeskArticle);
	}

	public ZendeskArticle updateZendeskArticleTranslation(
			long zendeskArticleId, Locale locale, String remoteTitle,
			String remoteBody)
		throws PortalException {

		// Zendesk article

		ZendeskArticle zendeskArticle =
			zendeskArticlePersistence.findByPrimaryKey(zendeskArticleId);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Updating article translation " +
					zendeskArticle.getDocumentationKey());
		}

		// Zendesk article attachments

		List<ZendeskArticleAttachment> zendeskArticleAttachments =
			zendeskArticleAttachmentPersistence.findByZendeskArticleId(
				zendeskArticleId);

		remoteBody = transformAttachmentLinks(
			remoteBody, zendeskArticleAttachments);

		// Zendesk article translations

		updateRemoteZendeskTranslations(
			zendeskArticle.getRemoteId(), locale, remoteTitle, remoteBody);

		zendeskArticle.setModifiedDate(new Date());
		zendeskArticle.setRemoteTitle(remoteTitle, locale);

		return zendeskArticlePersistence.update(zendeskArticle);
	}

	protected JSONObject addRemoteZendeskArticle(
			long remoteSectionId, Map<Locale, String> remoteTitleMap,
			Map<Locale, String> remoteBodyMap, int position,
			long remoteUserSegmentId, String[] labelNames)
		throws PortalException {

		JSONObject articleJSONObject = JSONFactoryUtil.createJSONObject();

		if (labelNames != null) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			for (String labelName : labelNames) {
				jsonArray.put(labelName);
			}

			articleJSONObject.put("label_names", jsonArray);
		}

		articleJSONObject.put(
			"permission_group_id",
			ZendeskDocumentationSyncConfigurationValues.
				ZENDESK_ARTICLE_PERMISSION_GROUP_ID);
		articleJSONObject.put("position", position);

		Map<Locale, String> curRemoteTitleMap = new HashMap<>();

		curRemoteTitleMap.putAll(remoteTitleMap);

		JSONArray translationsJSONArray = JSONFactoryUtil.createJSONArray();

		if (!curRemoteTitleMap.isEmpty()) {
			JSONObject translationJSONObject =
				JSONFactoryUtil.createJSONObject();

			translationJSONObject.put("body", remoteBodyMap.get(LocaleUtil.US));
			translationJSONObject.put(
				"locale",
				_zendeskLocaleUtil.convertToZendeskLocale(LocaleUtil.US));
			translationJSONObject.put(
				"title", curRemoteTitleMap.get(LocaleUtil.US));

			translationsJSONArray.put(translationJSONObject);

			curRemoteTitleMap.remove(LocaleUtil.US);
		}

		for (Locale locale : curRemoteTitleMap.keySet()) {
			JSONObject translationJSONObject =
				JSONFactoryUtil.createJSONObject();

			translationJSONObject.put("body", remoteBodyMap.get(locale));
			translationJSONObject.put(
				"locale", _zendeskLocaleUtil.convertToZendeskLocale(locale));
			translationJSONObject.put("title", curRemoteTitleMap.get(locale));

			translationsJSONArray.put(translationJSONObject);
		}

		articleJSONObject.put("translations", translationsJSONArray);

		if (remoteUserSegmentId > 0) {
			articleJSONObject.put("user_segment_id", remoteUserSegmentId);
		}
		else if (!Validator.isBlank(
					ZendeskDocumentationSyncConfigurationValues.
						ZENDESK_ARTICLE_USER_SEGMENT_ID)) {

			articleJSONObject.put(
				"user_segment_id",
				ZendeskDocumentationSyncConfigurationValues.
					ZENDESK_ARTICLE_USER_SEGMENT_ID);
		}
		else {
			articleJSONObject.put("user_segment_id", "null");
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("article", articleJSONObject);

		String jsonObjectString = jsonObject.toString();

		jsonObjectString = jsonObjectString.replace("\"null\"", "null");

		return _zendeskBaseWebService.post(
			ZendeskRESTEndpoints.URL_API_V2 + "help_center/sections/" +
				remoteSectionId + "/articles.json",
			jsonObjectString);
	}

	protected String transformAttachmentLinks(
		String remoteBody,
		List<ZendeskArticleAttachment> zendeskArticleAttachments) {

		for (ZendeskArticleAttachment zendeskArticleAttachment :
				zendeskArticleAttachments) {

			remoteBody = StringUtil.replace(
				remoteBody, zendeskArticleAttachment.getFilePath(),
				zendeskArticleAttachment.getRemoteContentURL());
		}

		return remoteBody;
	}

	protected JSONObject updateRemoteZendeskArticle(
			long remoteId, long remoteSectionId, int position,
			long remoteUserSegmentId, String[] labelNames)
		throws PortalException {

		JSONObject articleJSONObject = JSONFactoryUtil.createJSONObject();

		if (labelNames != null) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			for (String labelName : labelNames) {
				jsonArray.put(labelName);
			}

			articleJSONObject.put("label_names", jsonArray);
		}

		articleJSONObject.put(
			"permission_group_id",
			ZendeskDocumentationSyncConfigurationValues.
				ZENDESK_ARTICLE_PERMISSION_GROUP_ID);

		if (position >= 0) {
			articleJSONObject.put("position", position);
		}

		if (remoteSectionId > 0) {
			articleJSONObject.put("section_id", remoteSectionId);
		}

		if (remoteUserSegmentId > 0) {
			articleJSONObject.put("user_segment_id", remoteUserSegmentId);
		}
		else if (!Validator.isBlank(
					ZendeskDocumentationSyncConfigurationValues.
						ZENDESK_ARTICLE_USER_SEGMENT_ID)) {

			articleJSONObject.put(
				"user_segment_id",
				ZendeskDocumentationSyncConfigurationValues.
					ZENDESK_ARTICLE_USER_SEGMENT_ID);
		}
		else {
			articleJSONObject.put("user_segment_id", "null");
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("article", articleJSONObject);

		String jsonObjectString = jsonObject.toString();

		jsonObjectString = jsonObjectString.replace("\"null\"", "null");

		return _zendeskBaseWebService.put(
			ZendeskRESTEndpoints.URL_API_V2 + "help_center/articles/" +
				remoteId + ".json",
			jsonObjectString);
	}

	protected void updateRemoteZendeskTranslations(
			long remoteId, Locale locale, String remoteTitle, String remoteBody)
		throws PortalException {

		String zendeskLocale = _zendeskLocaleUtil.convertToZendeskLocale(
			locale);

		JSONObject translationJSONObject = JSONFactoryUtil.createJSONObject();

		translationJSONObject.put("body", remoteBody);
		translationJSONObject.put("title", remoteTitle);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("translation", translationJSONObject);

		_zendeskBaseWebService.put(
			ZendeskRESTEndpoints.URL_API_V2 + "help_center/articles/" +
				remoteId + "/translations/" + zendeskLocale + ".json",
			jsonObject.toString());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ZendeskArticleLocalServiceImpl.class);

	@ServiceReference(type = ZendeskBaseWebService.class)
	private ZendeskBaseWebService _zendeskBaseWebService;

	@ServiceReference(type = ZendeskLocaleUtil.class)
	private ZendeskLocaleUtil _zendeskLocaleUtil;

}