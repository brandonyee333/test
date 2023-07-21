/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.definition.web.internal.util.filter;

import com.liferay.portal.kernel.util.PredicateFilter;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;

/**
 * @author Marcellus Tavares
 */
public class WorkflowDefinitionTitlePredicateFilter
	implements PredicateFilter<WorkflowDefinition> {

	public WorkflowDefinitionTitlePredicateFilter(String keywords) {
		_keywords = keywords;
	}

	@Override
	public boolean filter(WorkflowDefinition workflowDefinition) {
		if (Validator.isNull(_keywords)) {
			return true;
		}

		return StringUtil.containsIgnoreCase(
			workflowDefinition.getTitle(), _keywords, StringPool.SPACE);
	}

	private final String _keywords;

}