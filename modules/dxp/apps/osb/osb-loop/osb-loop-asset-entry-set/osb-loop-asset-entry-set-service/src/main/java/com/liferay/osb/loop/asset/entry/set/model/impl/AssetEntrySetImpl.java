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