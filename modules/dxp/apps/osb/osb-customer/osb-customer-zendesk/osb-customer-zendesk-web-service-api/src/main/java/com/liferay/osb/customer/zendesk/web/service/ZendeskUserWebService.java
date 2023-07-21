/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.web.service.search.Query;
import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Set;

/**
 * @author Amos Fong
 */
@ProviderType
public interface ZendeskUserWebService {

	public ZendeskUser createOrUpdateZendeskUser(
			String externalId, String email, String zendeskLocale, String name,
			String organizationName, String role, Set<String> tags)
		throws Exception;

	public void createZendeskUserOrganizationSubscription(
			long zendeskUserId, long zendeskOrganizationId)
		throws Exception;

	public void deleteZendeskUser(long zendeskUserId) throws Exception;

	public ZendeskUser getZendeskUser(long zendeskUserId)
		throws PortalException;

	public ZendeskUser getZendeskUserByEmail(String email)
		throws PortalException;

	public ZendeskUser getZendeskUserByExternalId(String externalId)
		throws PortalException;

	public List<ZendeskUser> getZendeskUsers(long[] zendeskUserIds)
		throws PortalException;

	public SearchHits<ZendeskUser> getZendeskUsers(Query query)
		throws PortalException;

	public void updateZendeskUserTags(long zendeskUserId, Set<String> tags)
		throws Exception;

}