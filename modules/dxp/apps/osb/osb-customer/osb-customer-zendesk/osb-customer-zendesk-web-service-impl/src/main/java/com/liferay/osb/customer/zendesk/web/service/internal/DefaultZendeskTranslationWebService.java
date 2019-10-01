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
import com.liferay.osb.customer.zendesk.web.service.internal.util.ZendeskConverter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

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
			String sourceType, long sourceId, String zendeskLocale,
			String title, String body)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public List<ZendeskTranslation> getZendeskTranslations(
			String sourceType, long sourceId)
		throws PortalException {

		String endpoint = getBaseEndpoint(sourceType, sourceId);

		JSONObject responseJSONObject = zendeskBaseWebService.get(
			endpoint + "/translations.json", StringPool.BLANK);

		return zendeskConverter.toZendeskTranslations(
			responseJSONObject.getJSONArray("translations"));
	}

	public ZendeskTranslation updateZendeskTranslation(
			String sourceType, long sourceId, String zendeskLocale,
			String title, String body)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	protected String getBaseEndpoint(String sourceType, long sourceId) {
		StringBundler sb = new StringBundler(4);

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

		return sb.toString();
	}

	protected JSONObject getZendeskTranslationJSONObject(
		String zendeskLocale, String title, String body) {

		JSONObject translationJSONObject = JSONFactoryUtil.createJSONObject();

		translationJSONObject.put("body", body);

		if (Validator.isNotNull(zendeskLocale)) {
			translationJSONObject.put("locale", zendeskLocale);
		}

		translationJSONObject.put("title", title);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("translation", translationJSONObject);

		return jsonObject;
	}

	@Reference
	protected MessagePublisherUtil messagePublisherUtil;

	@Reference
	protected ZendeskBaseWebService zendeskBaseWebService;

	@Reference
	protected ZendeskConverter zendeskConverter;

}