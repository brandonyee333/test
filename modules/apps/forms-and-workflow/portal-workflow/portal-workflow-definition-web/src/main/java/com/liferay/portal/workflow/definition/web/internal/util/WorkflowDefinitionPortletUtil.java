/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.definition.web.internal.util;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.workflow.definition.web.internal.util.comparator.WorkflowDefinitionActiveComparator;
import com.liferay.portal.workflow.definition.web.internal.util.comparator.WorkflowDefinitionNameComparator;
import com.liferay.portal.workflow.definition.web.internal.util.comparator.WorkflowDefinitionTitleComparator;

import java.util.Locale;

/**
 * @author Leonardo Barros
 */
public class WorkflowDefinitionPortletUtil {

	public static OrderByComparator<WorkflowDefinition>
		getWorkflowDefitionOrderByComparator(
			String orderByCol, String orderByType, Locale locale) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<WorkflowDefinition> orderByComparator = null;

		if (orderByCol.equals("active")) {
			orderByComparator = new WorkflowDefinitionActiveComparator(
				orderByAsc, locale);
		}
		else if (orderByCol.equals("title")) {
			orderByComparator = new WorkflowDefinitionTitleComparator(
				orderByAsc, locale);
		}
		else {
			orderByComparator = new WorkflowDefinitionNameComparator(
				orderByAsc);
		}

		return orderByComparator;
	}

}