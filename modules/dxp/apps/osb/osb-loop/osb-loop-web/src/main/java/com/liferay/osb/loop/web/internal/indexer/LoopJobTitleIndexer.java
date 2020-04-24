/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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

	private static LoopJobTitleIndexer _instance = new LoopJobTitleIndexer();

}