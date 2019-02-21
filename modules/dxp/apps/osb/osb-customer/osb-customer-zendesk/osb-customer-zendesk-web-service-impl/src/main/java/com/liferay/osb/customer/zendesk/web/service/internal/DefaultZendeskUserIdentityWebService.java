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

import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserIdentityWebService;
import com.liferay.osb.customer.zendesk.web.service.internal.util.MessagePublisherUtil;
import com.liferay.portal.kernel.exception.PortalException;

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
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public void deleteZendeskUserIdentity(
			long zendeskUserId, long zendeskUserIdentityId, String type)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public void updateZendeskUserIdentity(
			long zendeskUserId, long zendeskUserIdentityId, String value)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Reference
	protected MessagePublisherUtil messagePublisherUtil;

	@Reference
	protected ZendeskBaseWebService zendeskBaseWebService;

}