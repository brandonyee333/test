/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.form.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Lino Alves
 */
@ExtendedObjectClassDefinition(
	category = "forms-and-workflow",
	scope = ExtendedObjectClassDefinition.Scope.GROUP
)
@Meta.OCD(
	id = "com.liferay.dynamic.data.lists.form.web.configuration.DDLFormWebConfiguration",
	localization = "content/Language", name = "ddl-form-web-configuration-name"
)
public interface DDLFormWebConfiguration {

	@Meta.AD(
		deflt = "enabled-with-warning", name = "csv-export",
		optionLabels = {"Enabled", "enabled-with-warning", "Disabled"},
		optionValues = {"enabled", "enabled-with-warning", "disabled"},
		required = false
	)
	public String csvExport();

	@Meta.AD(
		deflt = "descriptive", name = "default-display-view",
		optionLabels = {"Descriptive", "List"},
		optionValues = {"descriptive", "list"}, required = false
	)
	public String defaultDisplayView();

}