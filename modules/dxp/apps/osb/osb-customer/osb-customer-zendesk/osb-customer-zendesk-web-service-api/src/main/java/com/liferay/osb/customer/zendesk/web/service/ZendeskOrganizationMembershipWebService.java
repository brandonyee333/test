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

import com.liferay.portal.kernel.exception.PortalException;

import java.util.Map;

/**
 * @author Amos Fong
 */
@ProviderType
public interface ZendeskOrganizationMembershipWebService {

	public void createOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException;

	public void deleteOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException;

	public Map<Long, Long> getOrganizationMemberships(
			long zendeskOrganizationId)
		throws PortalException;

	public Map<Long, Long> getZendeskUserOrganizationMemberships(
			long zendeskUserId)
		throws PortalException;

}