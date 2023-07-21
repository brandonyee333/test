/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.content.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author     Daniel Couso
 * @author     Brian I. Kim
 *
 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
 */
@Deprecated
@ExtendedObjectClassDefinition(category = "web-experience")
@Meta.OCD(
	id = "com.liferay.journal.content.web.configuration.JournalContentConfiguration",
	localization = "content/Language",
	name = "journal-content-configuration-name"
)
public interface JournalContentConfiguration {

	@Meta.AD(
		deflt = "true", description = "journal-content-single-menu-help",
		name = "journal-content-single-menu", required = false
	)
	public boolean singleMenu();

	@Meta.AD(
		deflt = "single-menu-application",
		description = "journal-content-display-menu-configuration-help",
		name = "journal-content-display-menu-configuration",
		optionLabels = {
			"journal-content-single-menu-content-actions-first",
			"journal-content-single-menu-application-actions-first"
		},
		optionValues = {"single-menu-content", "single-menu-application"},
		required = false
	)
	public String menuStyle();

}