/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.corp.merge;

import com.liferay.osb.model.CorpEntry;
import com.liferay.portal.service.ServiceContext;

/**
 * @author Ryan Park
 */
public abstract class BaseCorpEntryMergeHelper implements CorpEntryMergeHelper {

	public void run() throws CorpEntryMergeHelperException {
		try {
			doRun();
		}
		catch (Exception e) {
			throw new CorpEntryMergeHelperException(e);
		}
	}

	public void setCorpEntry(CorpEntry corpEntry) {
		this.corpEntry = corpEntry;
	}

	public void setMergeCorpEntry(CorpEntry mergeCorpEntry) {
		this.mergeCorpEntry = mergeCorpEntry;
	}

	public void setServiceContext(ServiceContext serviceContext) {
		this.serviceContext = serviceContext;
	}

	protected abstract void doRun() throws Exception;

	protected CorpEntry corpEntry;
	protected CorpEntry mergeCorpEntry;
	protected ServiceContext serviceContext;

}