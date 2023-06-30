/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Kyle Bischof
 */
public class ZendeskConnectorConfigurationValues {

	public static final String ZENDESK_ADMIN_USER_EMAIL = GetterUtil.getString(
		ZendeskConnectorConfigurationUtil.get("zendesk.admin.user.email"));

	public static final String ZENDESK_AUTH_USER_EMAIL = GetterUtil.getString(
		ZendeskConnectorConfigurationUtil.get("zendesk.auth.user.email"));

	public static final String ZENDESK_SUBDOMAIN = GetterUtil.getString(
		ZendeskConnectorConfigurationUtil.get("zendesk.subdomain"));

	public static final Long ZENDESK_TICKET_TOKEN_CUSTOM_FIELD_ID =
		GetterUtil.getLong(
			ZendeskConnectorConfigurationUtil.get(
				"zendesk.ticket.token.custom.field.id"));

}