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

package com.liferay.osb.model.impl;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.osb.model.AssetListConstants;
import com.liferay.osb.service.AssetListAssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.asset.model.AssetEntry;

import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Douglas Wong
 * @author Joan Kim
 */
public class AssetListImpl extends AssetListBaseImpl {

	public AssetListImpl() {
	}

	public List<AssetEntry> getAssetEntries(boolean visible)
		throws PortalException, SystemException {

		List<AssetEntry> assetEntries =
			AssetListAssetEntryLocalServiceUtil.getAssetEntries(
				getAssetListId());

		long assetCategoryId = getAssetCategoryId();

		Iterator<AssetEntry> itr = assetEntries.iterator();

		while (itr.hasNext()) {
			AssetEntry assetEntry = itr.next();

			if ((visible && !assetEntry.getVisible()) ||
				((assetCategoryId > 0) &&
				 !ArrayUtil.contains(
					assetEntry.getCategoryIds(), assetCategoryId))) {

				itr.remove();
			}
		}

		return assetEntries;
	}

	public String getTypeLabel() {
		return AssetListConstants.getTypeLabel(getType(), getAssetCategoryId());
	}

}