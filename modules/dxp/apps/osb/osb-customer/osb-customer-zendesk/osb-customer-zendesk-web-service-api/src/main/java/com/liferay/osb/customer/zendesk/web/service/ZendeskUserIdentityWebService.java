/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.zendesk.model.ZendeskUserIdentity;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Amos Fong
 */
@ProviderType
public interface ZendeskUserIdentityWebService {

	public void createZendeskUserIdentity(
			long zendeskUserId, String type, String value)
		throws Exception;

	public void deleteZendeskUserIdentity(
			long zendeskUserId, long zendeskUserIdentityId, String type)
		throws Exception;

	public List<ZendeskUserIdentity> getZendeskUserIdentities(
			long zendeskUserId)
		throws PortalException;

	public void updateZendeskUserIdentity(
			long zendeskUserId, long zendeskUserIdentityId, String value)
		throws Exception;

}