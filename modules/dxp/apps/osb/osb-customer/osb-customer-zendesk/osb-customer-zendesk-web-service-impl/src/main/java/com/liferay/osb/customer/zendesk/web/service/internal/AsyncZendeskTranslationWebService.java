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

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "async=true",
	service = ZendeskTranslationWebService.class
)
public class AsyncZendeskTranslationWebService
	extends DefaultZendeskTranslationWebService {

	public ZendeskTranslation addZendeskTranslation(
			String sourceType, long sourceId, String locale, String title,
			String body)
		throws PortalException {

		String endpoint = getEndpoint(sourceType, sourceId);

		JSONObject jsonObject = getZendeskTranslationJSONObject(
			locale, title, body);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "post", null, jsonObject, null);

		messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);

		return null;
	}

}