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