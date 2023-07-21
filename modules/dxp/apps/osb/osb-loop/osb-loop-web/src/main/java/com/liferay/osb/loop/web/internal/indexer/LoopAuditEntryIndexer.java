/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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

	private static final LoopAuditEntryIndexer _instance =
		new LoopAuditEntryIndexer();

}