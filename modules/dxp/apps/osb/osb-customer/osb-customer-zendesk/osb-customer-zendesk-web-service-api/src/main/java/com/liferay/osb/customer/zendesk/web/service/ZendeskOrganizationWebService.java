/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.zendesk.model.ZendeskOrganization;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Set;

/**
 * @author Kyle Bischof
 */
@ProviderType
public interface ZendeskOrganizationWebService {

	public ZendeskOrganization createOrUpdateZendeskOrganization(
			String accountCode, String accountKey, String country,
			String details, String externalId, String name, String notes,
			String partnerFirstLineSupport, String partnerJiraProject,
			String partnerCode, String sla, String status,
			String supportLanguage, String supportRegion, String tier,
			List<String> externalLinks, Set<String> tags)
		throws Exception;

	public void deleteZendeskOrganization(long zendeskOrganizationId)
		throws Exception;

	public ZendeskOrganization getZendeskOrganization(String externalId)
		throws PortalException;

}