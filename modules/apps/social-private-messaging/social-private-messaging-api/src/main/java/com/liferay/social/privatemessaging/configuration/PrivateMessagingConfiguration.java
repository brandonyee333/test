/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.privatemessaging.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Peter Fellwock
 */
@ExtendedObjectClassDefinition(
	category = "other", scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.social.privatemessaging.configuration.PrivateMessagingConfiguration",
	localization = "content/Language",
	name = "privatemessaging-service-configuration-name"
)
public interface PrivateMessagingConfiguration {

	@Meta.AD(
		deflt = "com/liferay/social/privatemessaging/dependencies/notification_message_body.tmpl",
		name = "email-body", required = false
	)
	public String emailBody();

	@Meta.AD(
		deflt = "20", name = "autocomplete-recipient-max", required = false
	)
	public int autocompleteRecipientMax();

	@Meta.AD(
		deflt = "", name = "autocomplete-recipient-site-excludes",
		required = false
	)
	public String[] autocompleteRecipientSiteExcludes();

	@Meta.AD(
		deflt = "com/liferay/social/privatemessaging/dependencies/notification_message_subject.tmpl",
		name = "email-subject", required = false
	)
	public String emailSubject();

	@Meta.AD(
		deflt = "all", name = "autocomplete-recipient-type", required = false
	)
	public String autocompleteRecipientType();

}