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
import com.liferay.osb.customer.zendesk.connector.util.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection;
import com.liferay.osb.customer.zendesk.documentation.sync.service.base.ZendeskSectionLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class ZendeskSectionLocalServiceImpl
	extends ZendeskSectionLocalServiceBaseImpl {

	public ZendeskSection addZendeskSection(
			long zendeskCategoryId, String documentationKey,
			Map<String, String> nameMap, Map<String, String> descriptionMap,
			int position)
		throws PortalException {

		if (_log.isInfoEnabled()) {
			_log.info("Adding section " + documentationKey);
		}

		ZendeskCategory zendeskCategory =
			zendeskCategoryPersistence.findByPrimaryKey(zendeskCategoryId);

		JSONObject jsonObject = addRemoteZendeskSection(
			zendeskCategory.getRemoteId(), nameMap, descriptionMap, position);

		JSONObject sectionJSONObject = jsonObject.getJSONObject("section");

		long zendeskSectionId = counterLocalService.increment();

		ZendeskSection zendeskSection = zendeskSectionPersistence.create(
			zendeskSectionId);

		zendeskSection.setModifiedDate(new Date());
		zendeskSection.setZendeskCategoryId(zendeskCategoryId);
		zendeskSection.setDocumentationKey(documentationKey);
		zendeskSection.setRemoteId(sectionJSONObject.getLong("id"));
		zendeskSection.setRemoteHtmlURL(
			sectionJSONObject.getString("html_url"));

		return zendeskSectionPersistence.update(zendeskSection);
	}

	public List<ZendeskSection> getZendeskSections(long zendeskCategoryId) {
		return zendeskSectionPersistence.findByZendeskCategoryId(
			zendeskCategoryId);
	}

	public int getZendeskSectionsCount(long zendeskCategoryId) {
		return zendeskSectionPersistence.countByZendeskCategoryId(
			zendeskCategoryId);
	}

	public ZendeskSection updateZendeskSection(
			long zendeskSectionId, long zendeskCategoryId,
			String documentationKey, Map<String, String> nameMap,
			Map<String, String> descriptionMap, int position)
		throws PortalException {

		if (_log.isInfoEnabled()) {
			_log.info("Updating section " + documentationKey);
		}

		ZendeskSection zendeskSection =
			zendeskSectionPersistence.findByPrimaryKey(zendeskSectionId);

		ZendeskCategory zendeskCategory =
			zendeskCategoryPersistence.findByPrimaryKey(zendeskCategoryId);

		updateRemoteZendeskSection(
			zendeskSection.getRemoteId(), zendeskCategory.getRemoteId(),
			position);

		updateRemoteZendeskTranslations(
			zendeskSection.getRemoteId(), nameMap, descriptionMap);

		zendeskSection.setModifiedDate(new Date());
		zendeskSection.setZendeskCategoryId(zendeskCategoryId);

		return zendeskSectionPersistence.update(zendeskSection);
	}

	protected JSONObject addRemoteZendeskSection(
			long remoteCategoryId, Map<String, String> nameMap,
			Map<String, String> descriptionMap, int position)
		throws PortalException {

		JSONObject sectionJSONObject = JSONFactoryUtil.createJSONObject();

		sectionJSONObject.put("category_id", remoteCategoryId);
		sectionJSONObject.put("position", position);

		JSONArray translationsJSONArray = JSONFactoryUtil.createJSONArray();

		for (String locale : nameMap.keySet()) {
			JSONObject translationJSONObject =
				JSONFactoryUtil.createJSONObject();

			translationJSONObject.put("body", descriptionMap.get(locale));
			translationJSONObject.put("locale", locale);
			translationJSONObject.put("title", nameMap.get(locale));

			translationsJSONArray.put(translationJSONObject);
		}

		sectionJSONObject.put("translations", translationsJSONArray);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("section", sectionJSONObject);

		return _zendeskBaseWebService.post(
			ZendeskRESTEndpoints.URL_API_V2 + "help_center/categories/" +
				remoteCategoryId + "/sections.json",
			jsonObject.toString());
	}

	protected JSONObject updateRemoteZendeskSection(
			long remoteId, long remoteCategoryId, int position)
		throws PortalException {

		JSONObject sectionJSONObject = JSONFactoryUtil.createJSONObject();

		sectionJSONObject.put("category_id", remoteCategoryId);
		sectionJSONObject.put("position", position);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("section", sectionJSONObject);

		return _zendeskBaseWebService.put(
			ZendeskRESTEndpoints.URL_API_V2 + "help_center/sections/" +
				remoteId + ".json",
			jsonObject.toString());
	}

	protected void updateRemoteZendeskTranslations(
			long remoteId, Map<String, String> nameMap,
			Map<String, String> descriptionMap)
		throws PortalException {

		for (String locale : nameMap.keySet()) {
			JSONObject translationJSONObject =
				JSONFactoryUtil.createJSONObject();

			translationJSONObject.put("body", descriptionMap.get(locale));
			translationJSONObject.put("title", nameMap.get(locale));

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("translation", translationJSONObject);

			_zendeskBaseWebService.put(
				ZendeskRESTEndpoints.URL_API_V2 + "help_center/sections/" +
					remoteId + "/translations/" + locale + ".json",
				jsonObject.toString());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ZendeskSectionLocalServiceImpl.class);

	@ServiceReference(type = ZendeskBaseWebService.class)
	private ZendeskBaseWebService _zendeskBaseWebService;

}