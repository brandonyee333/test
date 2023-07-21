/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service.internal;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.model.ZendeskUserIdentity;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserIdentityWebService;
import com.liferay.osb.customer.zendesk.web.service.internal.util.ZendeskConverter;
import com.liferay.osb.distributed.messaging.publishing.MessagePublisher;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "service.ranking:Integer=100",
	service = ZendeskUserIdentityWebService.class
)
public class DefaultZendeskUserIdentityWebService
	implements ZendeskUserIdentityWebService {

	public void createZendeskUserIdentity(
			long zendeskUserId, String type, String value)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	public void deleteZendeskUserIdentity(
			long zendeskUserId, long zendeskUserIdentityId, String type)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	public List<ZendeskUserIdentity> getZendeskUserIdentities(
			long zendeskUserId)
		throws PortalException {

		String endpoint = StringBundler.concat(
			ZendeskRESTEndpoints.URL_API_V2, "users/",
			String.valueOf(zendeskUserId), ZendeskRESTEndpoints.IDENTITIES);

		JSONObject responseJSONObject = zendeskBaseWebService.get(
			endpoint, StringPool.BLANK);

		JSONArray identitiesJSONArray = responseJSONObject.getJSONArray(
			"identities");

		return zendeskConverter.toZendeskUserIdentities(identitiesJSONArray);
	}

	public void updateZendeskUserIdentity(
			long zendeskUserId, long zendeskUserIdentityId, String value)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	@Reference
	protected MessagePublisher messagePublisher;

	@Reference
	protected ZendeskBaseWebService zendeskBaseWebService;

	@Reference
	protected ZendeskConverter zendeskConverter;

}