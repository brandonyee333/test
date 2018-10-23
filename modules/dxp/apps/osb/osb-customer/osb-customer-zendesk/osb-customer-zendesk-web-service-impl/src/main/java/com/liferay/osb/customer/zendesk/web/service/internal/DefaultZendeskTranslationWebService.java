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

package com.liferay.osb.customer.zendesk.web.service.internal;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.constants.ZendeskTranslationConstants;
import com.liferay.osb.customer.zendesk.model.ZendeskTranslation;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTranslationWebService;
import com.liferay.osb.customer.zendesk.web.service.internal.util.MessagePublisherUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ZendeskTranslationWebService.class)
public class DefaultZendeskTranslationWebService
	implements ZendeskTranslationWebService {

	public ZendeskTranslation addZendeskTranslation(
			String sourceType, long sourceId, String locale, String title,
			String body)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public List<ZendeskTranslation> getZendeskTranslations(
			String sourceType, long sourceId)
		throws PortalException {

		String endpoint = getEndpoint(sourceType, sourceId);

		JSONObject responseJSONObject = zendeskBaseWebService.get(
			endpoint, StringPool.BLANK);

		return toZendeskTranslations(
			responseJSONObject.getJSONArray("translations"));
	}

	protected String getEndpoint(String sourceType, long sourceId) {
		StringBundler sb = new StringBundler(5);

		sb.append(ZendeskRESTEndpoints.URL_API_V2);
		sb.append("help_center/");

		if (sourceType.equals(
				ZendeskTranslationConstants.SOURCE_TYPE_ARTICLE)) {

			sb.append("articles/");
		}
		else if (sourceType.equals(
					ZendeskTranslationConstants.SOURCE_TYPE_CATEGORY)) {

			sb.append("categories/");
		}
		else if (sourceType.equals(
					ZendeskTranslationConstants.SOURCE_TYPE_SECTION)) {

			sb.append("sections/");
		}

		sb.append(sourceId);
		sb.append("/translations.json");

		return sb.toString();
	}

	protected JSONObject getZendeskTranslationJSONObject(
		String locale, String title, String body) {

		JSONObject translationJSONObject = JSONFactoryUtil.createJSONObject();

		translationJSONObject.put("body", body);
		translationJSONObject.put("locale", locale);
		translationJSONObject.put("title", title);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("translation", translationJSONObject);

		return jsonObject;
	}

	protected ZendeskTranslation toZendeskTranslation(JSONObject jsonObject) {
		ZendeskTranslation zendeskTranslation = new ZendeskTranslation();

		zendeskTranslation.setBody(jsonObject.getString("body"));
		zendeskTranslation.setLocale(jsonObject.getString("locale"));
		zendeskTranslation.setSourceId(jsonObject.getLong("source_id"));
		zendeskTranslation.setSourceType(jsonObject.getString("source_type"));
		zendeskTranslation.setTitle(jsonObject.getString("title"));
		zendeskTranslation.setZendeskTranslationId(jsonObject.getLong("id"));

		return zendeskTranslation;
	}

	protected List<ZendeskTranslation> toZendeskTranslations(
		JSONArray jsonArray) {

		List<ZendeskTranslation> zendeskTranslations = new ArrayList<>(
			jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			zendeskTranslations.add(toZendeskTranslation(jsonObject));
		}

		return zendeskTranslations;
	}

	@Reference
	protected MessagePublisherUtil messagePublisherUtil;

	@Reference
	protected ZendeskBaseWebService zendeskBaseWebService;

}