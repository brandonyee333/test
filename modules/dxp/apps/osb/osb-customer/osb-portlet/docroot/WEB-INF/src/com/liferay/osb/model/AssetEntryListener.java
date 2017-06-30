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

package com.liferay.osb.model;

import com.liferay.osb.service.AssetAuditLocalServiceUtil;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portlet.asset.model.AssetEntry;

/**
 * @author Peter Shin
 */
public class AssetEntryListener extends BaseModelListener<AssetEntry> {

	@Override
	public void onBeforeRemove(AssetEntry assetEntry)
		throws ModelListenerException {

		try {
			doOnBeforeRemove(assetEntry);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void doOnBeforeRemove(AssetEntry assetEntry) throws Exception {
		AssetAuditLocalServiceUtil.deleteAssetAudits(
			assetEntry.getClassName(), assetEntry.getClassPK());
	}

}