/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.util;

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.asset.entry.set.service.AssetEntrySetLocalServiceUtil;

/**
 * @author Timothy Bell
 */
public class LoopAssetEntrySetUtil {

	public static long getRootAssetEntrySetId(AssetEntrySet assetEntrySet)
		throws Exception {

		while (assetEntrySet.getParentAssetEntrySetId() > 0) {
			assetEntrySet = AssetEntrySetLocalServiceUtil.getAssetEntrySet(
				assetEntrySet.getParentAssetEntrySetId());
		}

		return assetEntrySet.getAssetEntrySetId();
	}

	public static long getRootAssetEntrySetId(long assetEntrySetId)
		throws Exception {

		AssetEntrySet assetEntrySet =
			AssetEntrySetLocalServiceUtil.getAssetEntrySet(assetEntrySetId);

		return getRootAssetEntrySetId(assetEntrySet);
	}

}