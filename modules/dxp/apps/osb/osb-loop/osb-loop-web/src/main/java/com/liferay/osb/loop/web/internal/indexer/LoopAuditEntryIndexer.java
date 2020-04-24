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

import com.liferay.osb.loop.model.LoopAuditEntry;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;

/**
 * @author Timothy Bell
 */
public class LoopAuditEntryIndexer extends BaseLoopIndexer {

	public static LoopAuditEntryIndexer getInstance() {
		return _instance;
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
		LoopAuditEntry loopAuditEntry = (LoopAuditEntry)baseModel;

		Document document = getBaseModelDocument(null, loopAuditEntry);

		document.addText(Field.NAME, loopAuditEntry.getName());

		return document;
	}

	private LoopAuditEntryIndexer() {
		setClassName(LoopAuditEntry.class.getName());
	}

	private static LoopAuditEntryIndexer _instance =
		new LoopAuditEntryIndexer();

}