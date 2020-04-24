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