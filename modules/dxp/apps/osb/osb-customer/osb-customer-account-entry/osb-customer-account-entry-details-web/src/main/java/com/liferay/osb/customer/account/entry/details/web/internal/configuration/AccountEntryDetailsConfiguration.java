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

	@Meta.AD(name = "data-access-knowledge-base-article", required = false)
	public String dataAccessKnowledgeBaseArticle();

	@Meta.AD(
		deflt = "https://liferaysupport1528999723.zendesk.com/agent/tickets/",
		name = "zendesk-ticket-url", required = false
	)
	public String zendeskTicketURL();

}