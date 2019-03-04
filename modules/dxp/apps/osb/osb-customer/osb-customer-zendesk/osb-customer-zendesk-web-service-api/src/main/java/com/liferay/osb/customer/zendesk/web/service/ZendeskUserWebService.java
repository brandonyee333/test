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

package com.liferay.osb.customer.zendesk.web.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.web.service.search.Query;
import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Map;
import java.util.Set;

/**
 * @author Amos Fong
 */
@ProviderType
public interface ZendeskUserWebService {

	public void addZendeskUserTags(long zendeskUserId, Set<String> tags)
		throws PortalException;

	public ZendeskUser createOrUpdateZendeskUser(
			String externalId, String email, String locale, String name,
			String organizationName, Set<String> tags)
		throws PortalException;

	public void createZendeskUserOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException;

	public void createZendeskUserOrganizationSubscription(
			long zendeskUserId, long zendeskOrganizationId)
		throws PortalException;

	public void deleteZendeskUserOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException;

	public void deleteZendeskUserTags(long zendeskUserId, Set<String> tags)
		throws PortalException;

	public Map<Long, Long> getOrganizationMemberships(long zendeskUserId)
		throws PortalException;

	public ZendeskUser getZendeskUserByEmail(String email)
		throws PortalException;

	public ZendeskUser getZendeskUserByExternalId(String externalId)
		throws PortalException;

	public ZendeskUser getZendeskUserByZendeskUserId(long zendeskUserId)
		throws PortalException;

	public SearchHits<ZendeskUser> getZendeskUsers(Query query)
		throws PortalException;

}