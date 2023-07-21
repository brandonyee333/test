/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.indexer;

import com.liferay.osb.loop.model.LoopJobTitle;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Timothy Bell
 */
public class LoopJobTitleIndexer extends BaseLoopIndexer {

	public static LoopJobTitleIndexer getInstance() {
		return _instance;
	}

	@Override
	public void postProcessContextBooleanFilter(
		BooleanFilter contextBooleanFilter, SearchContext searchContext) {

		Integer status = (Integer)searchContext.getAttribute(Field.STATUS);

		if (status != null) {
			contextBooleanFilter.addRequiredTerm(
				Field.STATUS, status.intValue());
		}
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter booleanFilter,
			SearchContext searchContext)
		throws Exception {

		addSearchTerm(searchQuery, searchContext, Field.NAME, true);
	}

	@Override
	protected Document doGetDocument(BaseModel<?> baseModel) {
		LoopJobTitle loopJobTitle = (LoopJobTitle)baseModel;

		Document document = getBaseModelDocument(null, loopJobTitle);

		document.addText(Field.NAME, loopJobTitle.getName());
		document.addKeyword("name_sortable", loopJobTitle.getName());
		document.addKeyword(Field.STATUS, loopJobTitle.getStatus());

		int statusPriority = 0;

		if (loopJobTitle.getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			statusPriority = 1;
		}

		document.addNumber("statusPriority", statusPriority);

		return document;
	}

	private LoopJobTitleIndexer() {
		setClassName(LoopJobTitle.class.getName());
	}

	private static final LoopJobTitleIndexer _instance =
		new LoopJobTitleIndexer();

}