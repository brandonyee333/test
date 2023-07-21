/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.controller;

import com.liferay.osb.loop.model.LoopAuditEntry;
import com.liferay.osb.loop.web.internal.indexer.LoopAuditEntryIndexer;
import com.liferay.portal.kernel.search.Indexer;

/**
 * @author Timothy Bell
 */
public class AuditEntriesController extends LoopAlloyControllerImpl {

	public AuditEntriesController() {
		setAlloyServiceInvokerClass(LoopAuditEntry.class);
	}

	@Override
	protected Indexer buildIndexer() {
		return LoopAuditEntryIndexer.getInstance();
	}

}