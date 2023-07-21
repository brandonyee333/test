/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.zendesk.model.ZendeskOrganizationMembership;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Amos Fong
 */
@ProviderType
public interface ZendeskOrganizationMembershipWebService {

	public void createOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws Exception;

	public void deleteOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws Exception;

	public List<ZendeskOrganizationMembership> getOrganizationMemberships(
			long zendeskOrganizationId)
		throws PortalException;

	public List<ZendeskOrganizationMembership>
			getZendeskUserOrganizationMemberships(long zendeskUserId)
		throws PortalException;

}