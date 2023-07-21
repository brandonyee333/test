/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.definition.web.internal.search;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Leonardo Barros
 */
public class WorkflowDefinitionSearchTerms
	extends WorkflowDefinitionDisplayTerms {

	public WorkflowDefinitionSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		name = DAOParamUtil.getString(portletRequest, NAME);
		title = DAOParamUtil.getString(portletRequest, TITLE);
	}

}