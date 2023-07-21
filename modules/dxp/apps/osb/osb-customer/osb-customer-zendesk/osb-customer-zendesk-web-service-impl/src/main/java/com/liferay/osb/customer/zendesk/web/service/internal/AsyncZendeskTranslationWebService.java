/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service.internal;

import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.osb.customer.zendesk.model.ZendeskTranslation;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTranslationWebService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = {"async=true", "service.ranking:Integer=1"},
	service = ZendeskTranslationWebService.class
)
public class AsyncZendeskTranslationWebService
	extends DefaultZendeskTranslationWebService {

	public ZendeskTranslation addZendeskTranslation(
			String sourceType, long sourceId, String zendeskLocale,
			String title, String body)
		throws Exception {

		String endpoint = getBaseEndpoint(sourceType, sourceId);

		JSONObject jsonObject = getZendeskTranslationJSONObject(
			zendeskLocale, title, body);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint + "/translations.json", "post", null, jsonObject, null);

		messagePublisher.publish(
			"zendesk.service", zendeskRequest.getMessage());

		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Adding ", zendeskLocale, " translation for ", sourceType,
					StringPool.POUND, String.valueOf(sourceId)));
		}

		return null;
	}

	public ZendeskTranslation updateZendeskTranslation(
			String sourceType, long sourceId, String zendeskLocale,
			String title, String body)
		throws Exception {

		String endpoint = getBaseEndpoint(sourceType, sourceId);

		JSONObject jsonObject = getZendeskTranslationJSONObject(
			null, title, body);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			StringBundler.concat(
				endpoint, "/translations/", zendeskLocale, ".json"),
			"put", null, jsonObject, null);

		messagePublisher.publish(
			"zendesk.service", zendeskRequest.getMessage());

		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Updating ", zendeskLocale, " translation for ", sourceType,
					StringPool.POUND, String.valueOf(sourceId)));
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AsyncZendeskTranslationWebService.class);

}