/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.definition.link.web.internal.portlet;

import com.liferay.portal.kernel.portlet.ControlPanelEntry;
import com.liferay.portal.workflow.WorkflowControlPanelEntry;
import com.liferay.portal.workflow.definition.link.web.internal.constants.WorkflowDefinitionLinkPortletKeys;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + WorkflowDefinitionLinkPortletKeys.WORKFLOW_DEFINITION_LINK,
	service = ControlPanelEntry.class
)
public class WorkflowDefinitionLinkControlPanelEntry
	extends WorkflowControlPanelEntry {
}