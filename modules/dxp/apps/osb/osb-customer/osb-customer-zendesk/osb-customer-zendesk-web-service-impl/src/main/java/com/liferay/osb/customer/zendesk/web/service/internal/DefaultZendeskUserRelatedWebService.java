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
import com.liferay.osb.customer.zendesk.model.ZendeskUserRelated;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserRelatedWebService;
import com.liferay.osb.customer.zendesk.web.service.internal.util.ZendeskConverter;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "service.ranking:Integer=100",
	service = ZendeskUserRelatedWebService.class
)
public class DefaultZendeskUserRelatedWebService
	implements ZendeskUserRelatedWebService {

	public ZendeskUserRelated getZendeskUserRelated(long zendeskUserId)
		throws PortalException {

		String endpoint = StringBundler.concat(
			ZendeskRESTEndpoints.URL_API_V2, "users/",
			String.valueOf(zendeskUserId), ZendeskRESTEndpoints.RELATED);

		JSONObject responseJSONObject = zendeskBaseWebService.get(
			endpoint, StringPool.BLANK);

		JSONObject relatedJSONObject = responseJSONObject.getJSONObject(
			"user_related");

		return zendeskConverter.toZendeskUserRelated(relatedJSONObject);
	}

	@Reference
	protected ZendeskBaseWebService zendeskBaseWebService;

	@Reference
	protected ZendeskConverter zendeskConverter;

}