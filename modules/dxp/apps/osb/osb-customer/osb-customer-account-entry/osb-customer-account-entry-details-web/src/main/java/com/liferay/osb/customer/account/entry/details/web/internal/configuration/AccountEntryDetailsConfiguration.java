/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.account.entry.details.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Amos Fong
 */
@ExtendedObjectClassDefinition(category = "other")
@Meta.OCD(
	id = "com.liferay.osb.customer.account.entry.details.web.internal.configuration.AccountEntryDetailsConfiguration",
	localization = "content/Language", name = "account-entry-details-name"
)
public interface AccountEntryDetailsConfiguration {

	@Meta.AD(
		deflt = "https://support.liferay.com", name = "customer-portal-url",
		required = false
	)
	public String customerPortalURL();

	@Meta.AD(
		deflt = "https://help.liferay.com/hc/articles/4403652209933",
		name = "data-access-article-url", required = false
	)
	public String dataAccessArticleURL();

	@Meta.AD(
		deflt = "https://help.liferay.com/hc/sections/8712397409421",
		name = "video-tutorial-url", required = false
	)
	public String videoTutorialURL();

	@Meta.AD(
		deflt = "https://liferay-support.zendesk.com/agent/tickets/",
		name = "zendesk-ticket-url", required = false
	)
	public String zendeskTicketURL();

}