/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.definition.web.internal.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Leonardo Barros
 */
public class WorkflowDefinitionDisplayTerms extends DisplayTerms {

	public static final String NAME = "name";

	public static final String TITLE = "title";

	public WorkflowDefinitionDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		name = ParamUtil.getString(portletRequest, NAME);
		title = ParamUtil.getString(portletRequest, TITLE);
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	protected String name;
	protected String title;

}