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

import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.osb.customer.zendesk.model.ZendeskTranslation;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTranslationWebService;
import com.liferay.portal.kernel.exception.PortalException;
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
		throws PortalException {

		String endpoint = getBaseEndpoint(sourceType, sourceId);

		JSONObject jsonObject = getZendeskTranslationJSONObject(
			zendeskLocale, title, body);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint + "/translations.json", "post", null, jsonObject, null);

		messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Adding " + zendeskLocale + " translation for " + sourceType +
					StringPool.POUND + sourceId);
		}

		return null;
	}

	public ZendeskTranslation updateZendeskTranslation(
			String sourceType, long sourceId, String zendeskLocale,
			String title, String body)
		throws PortalException {

		String endpoint = getBaseEndpoint(sourceType, sourceId);

		JSONObject jsonObject = getZendeskTranslationJSONObject(
			null, title, body);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint + "/translations/" + zendeskLocale + ".json", "put", null,
			jsonObject, null);

		messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Updating " + zendeskLocale + " translation for " + sourceType +
					StringPool.POUND + sourceId);
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AsyncZendeskTranslationWebService.class);

}