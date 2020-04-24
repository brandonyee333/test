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