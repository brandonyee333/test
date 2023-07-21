/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.instance.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Inácio Nery
 */
@ExtendedObjectClassDefinition(category = "forms-and-workflow")
@Meta.OCD(
	id = "com.liferay.portal.workflow.instance.web.configuration.WorkflowInstanceWebConfiguration"
)
public interface WorkflowInstanceWebConfiguration {

	@Meta.AD(
		deflt = "list", optionLabels = {"Descriptive", "List"},
		optionValues = {"descriptive", "list"}, required = false
	)
	public String defaultDisplayView();

}