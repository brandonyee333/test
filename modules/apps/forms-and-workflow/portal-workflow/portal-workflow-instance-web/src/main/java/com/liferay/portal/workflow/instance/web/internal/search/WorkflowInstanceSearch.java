/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.instance.web.internal.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.workflow.instance.web.internal.util.WorkflowInstancePortletUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Leonardo Barros
 */
public class WorkflowInstanceSearch extends SearchContainer<WorkflowInstance> {

	public static List<String> headerNames = new ArrayList<>();

	static {
		headerNames.add("asset-title");
		headerNames.add("asset-type");
		headerNames.add("status");
		headerNames.add("definition");
		headerNames.add("last-activity-date");
		headerNames.add("end-date");
	}

	public WorkflowInstanceSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new DisplayTerms(portletRequest), null,
			DEFAULT_CUR_PARAM, DEFAULT_DELTA, iteratorURL, headerNames, null);

		String orderByCol = ParamUtil.getString(portletRequest, "orderByCol");
		String orderByType = ParamUtil.getString(portletRequest, "orderByType");

		PortalPreferences preferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(portletRequest);

		if (Validator.isNotNull(orderByCol) &&
			Validator.isNotNull(orderByType)) {

			preferences.setValue(
				PortletKeys.MY_WORKFLOW_INSTANCE, "order-by-col", orderByCol);
			preferences.setValue(
				PortletKeys.MY_WORKFLOW_INSTANCE, "order-by-type", orderByType);
		}
		else {
			orderByCol = preferences.getValue(
				PortletKeys.MY_WORKFLOW_INSTANCE, "order-by-col",
				"last-activity-date");
			orderByType = preferences.getValue(
				PortletKeys.MY_WORKFLOW_INSTANCE, "order-by-type", "asc");
		}

		OrderByComparator<WorkflowInstance> orderByComparator =
			WorkflowInstancePortletUtil.getWorkflowInstanceOrderByComparator(
				orderByCol, orderByType);

		setOrderByCol(orderByCol);
		setOrderByType(orderByType);
		setOrderByComparator(orderByComparator);
	}

}