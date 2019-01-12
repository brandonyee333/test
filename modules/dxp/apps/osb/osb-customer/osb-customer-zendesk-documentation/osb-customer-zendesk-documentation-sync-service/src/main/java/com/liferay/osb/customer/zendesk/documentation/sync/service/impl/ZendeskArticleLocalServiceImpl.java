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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class ZendeskArticleLocalServiceImpl
	extends ZendeskArticleLocalServiceBaseImpl {

	public ZendeskArticle addZendeskArticle(
			long zendeskSectionId, String documentationKey,
			String documentationOriginalURL, Map<String, String> titleMap,
			Map<String, String> bodyMap, int position, String[] labelNames,
			Map<String, byte[]> attachments)
		throws PortalException {

		// Zendesk article

		if (_log.isInfoEnabled()) {
			_log.info("Adding article " + documentationKey);
		}

		ZendeskSection zendeskSection =
			zendeskSectionPersistence.findByPrimaryKey(zendeskSectionId);

		JSONObject jsonObject = addRemoteZendeskArticle(
			zendeskSection.getRemoteId(), titleMap, bodyMap, position,
			labelNames);

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
		zendeskArticle.setRemoteId(articleJSONObject.getLong("id"));
		zendeskArticle.setRemoteHtmlURL(
			articleJSONObject.getString("html_url"));

		zendeskArticlePersistence.update(zendeskArticle);

		// Zendesk article attachments

		for (Map.Entry<String, byte[]> entry : attachments.entrySet()) {
			ZendeskArticleAttachment zendeskArticleAttachment =
				zendeskArticleAttachmentLocalService.
					addZendeskArticleAttachment(
						zendeskArticleId, entry.getKey(), entry.getValue());

			transformAttachmentLinks(bodyMap, zendeskArticleAttachment);
		}

		// Zendesk article translations

		updateRemoteZendeskTranslations(
			zendeskArticle.getRemoteId(), titleMap, bodyMap);

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

	public ZendeskArticle updateZendeskArticle(
			long zendeskArticleId, long zendeskSectionId,
			String documentationKey, String documentationOriginalURL,
			Map<String, String> titleMap, Map<String, String> bodyMap,
			int position, String[] labelNames, Map<String, byte[]> attachments)
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
			position, labelNames);

		// Zendesk article attachments

		List<ZendeskArticleAttachment> zendeskArticleAttachments =
			zendeskArticleAttachmentLocalService.getZendeskArticleAttachments(
				zendeskArticleId);

		for (ZendeskArticleAttachment zendeskArticleAttachment :
				zendeskArticleAttachments) {

			if (!attachments.containsKey(
					zendeskArticleAttachment.getFilePath())) {

				zendeskArticleAttachmentLocalService.
					deleteZendeskArticleAttachment(zendeskArticleAttachment);
			}
		}

		for (Map.Entry<String, byte[]> entry : attachments.entrySet()) {
			ZendeskArticleAttachment zendeskArticleAttachment =
				zendeskArticleAttachmentLocalService.
					updateZendeskArticleAttachment(
						zendeskArticleId, entry.getKey(), entry.getValue());

			transformAttachmentLinks(bodyMap, zendeskArticleAttachment);
		}

		// Zendesk article translations

		updateRemoteZendeskTranslations(
			zendeskArticle.getRemoteId(), titleMap, bodyMap);

		zendeskArticle.setModifiedDate(new Date());
		zendeskArticle.setZendeskSectionId(zendeskSectionId);
		zendeskArticle.setDocumentationOriginalURL(documentationOriginalURL);

		return zendeskArticlePersistence.update(zendeskArticle);
	}

	protected JSONObject addRemoteZendeskArticle(
			long remoteSectionId, Map<String, String> titleMap,
			Map<String, String> bodyMap, int position, String[] labelNames)
		throws PortalException {

		JSONObject articleJSONObject = JSONFactoryUtil.createJSONObject();

		if (!ArrayUtil.isEmpty(labelNames)) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			for (String labelName : labelNames) {
				jsonArray.put(labelName);
			}

			articleJSONObject.put("label_names", jsonArray);
		}

		articleJSONObject.put("position", position);

		JSONArray translationsJSONArray = JSONFactoryUtil.createJSONArray();

		for (String locale : titleMap.keySet()) {
			JSONObject translationJSONObject =
				JSONFactoryUtil.createJSONObject();

			translationJSONObject.put("body", bodyMap.get(locale));
			translationJSONObject.put("locale", locale);
			translationJSONObject.put("title", titleMap.get(locale));

			translationsJSONArray.put(translationJSONObject);
		}

		articleJSONObject.put("translations", translationsJSONArray);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("article", articleJSONObject);

		return _zendeskBaseWebService.post(
			ZendeskRESTEndpoints.URL_API_V2 + "help_center/sections/" +
				remoteSectionId + "/articles.json",
			jsonObject.toString());
	}

	protected void transformAttachmentLinks(
		Map<String, String> bodyMap,
		ZendeskArticleAttachment zendeskArticleAttachment) {

		for (Map.Entry<String, String> entry : bodyMap.entrySet()) {
			String body = StringUtil.replace(
				entry.getValue(), zendeskArticleAttachment.getFilePath(),
				zendeskArticleAttachment.getRemoteContentURL());

			entry.setValue(body);
		}
	}

	protected JSONObject updateRemoteZendeskArticle(
			long remoteId, long remoteSectionId, int position,
			String[] labelNames)
		throws PortalException {

		JSONObject articleJSONObject = JSONFactoryUtil.createJSONObject();

		if (!ArrayUtil.isEmpty(labelNames)) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			for (String labelName : labelNames) {
				jsonArray.put(labelName);
			}

			articleJSONObject.put("label_names", jsonArray);
		}

		if (ZendeskDocumentationSyncConfigurationValues.
				ZENDESK_ARTICLE_PERMISSION_GROUP_ID > 0) {

			articleJSONObject.put(
				"permission_group_id",
				ZendeskDocumentationSyncConfigurationValues.
					ZENDESK_ARTICLE_PERMISSION_GROUP_ID);
		}

		articleJSONObject.put("position", position);
		articleJSONObject.put("section_id", remoteSectionId);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("article", articleJSONObject);

		return _zendeskBaseWebService.put(
			ZendeskRESTEndpoints.URL_API_V2 + "help_center/articles/" +
				remoteId + ".json",
			jsonObject.toString());
	}

	protected void updateRemoteZendeskTranslations(
			long remoteId, Map<String, String> titleMap,
			Map<String, String> bodyMap)
		throws PortalException {

		for (String locale : titleMap.keySet()) {
			JSONObject translationJSONObject =
				JSONFactoryUtil.createJSONObject();

			translationJSONObject.put("body", bodyMap.get(locale));
			translationJSONObject.put("title", titleMap.get(locale));

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("translation", translationJSONObject);

			_zendeskBaseWebService.put(
				ZendeskRESTEndpoints.URL_API_V2 + "help_center/articles/" +
					remoteId + "/translations/" + locale + ".json",
				jsonObject.toString());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ZendeskArticleLocalServiceImpl.class);

	@ServiceReference(type = ZendeskBaseWebService.class)
	private ZendeskBaseWebService _zendeskBaseWebService;

}