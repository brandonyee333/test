/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.entry.set.model.impl;

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetEntrySetImpl extends AssetEntrySetBaseImpl {

	public AssetEntrySetImpl() {
	}

	@JSON(include = true)
	@Override
	public List<AssetEntrySet> getChildAssetEntrySets() {
		return _childAssetEntrySets;
	}

	@Override
	public void setChildAssetEntrySets(List<AssetEntrySet> childAssetEntrySets)
		throws PortalException {

		_childAssetEntrySets = childAssetEntrySets;
	}

	private List<AssetEntrySet> _childAssetEntrySets = new ArrayList<>();

}