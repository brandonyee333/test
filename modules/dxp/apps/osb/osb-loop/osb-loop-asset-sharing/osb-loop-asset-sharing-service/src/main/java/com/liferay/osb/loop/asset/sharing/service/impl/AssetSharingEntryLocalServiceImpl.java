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

package com.liferay.osb.loop.asset.sharing.service.impl;

import com.liferay.osb.loop.asset.sharing.model.AssetSharingEntry;
import com.liferay.osb.loop.asset.sharing.service.base.AssetSharingEntryLocalServiceBaseImpl;
import com.liferay.osb.loop.asset.sharing.service.persistence.AssetSharingEntryPK;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Sherry Yang
 */
public class AssetSharingEntryLocalServiceImpl
	extends AssetSharingEntryLocalServiceBaseImpl {

	@Override
	public void addAssetSharingEntries(
		long classNameId, long classPK,
		Map<Long, Set<Long>> sharedToClassPKsMap) {

		for (Map.Entry<Long, Set<Long>> entry :
				sharedToClassPKsMap.entrySet()) {

			Set<Long> sharedToClassPKs = entry.getValue();

			if (sharedToClassPKs.isEmpty()) {
				continue;
			}

			for (long sharedToClassPK : sharedToClassPKs) {
				addAssetSharingEntry(
					classNameId, classPK, entry.getKey(), sharedToClassPK);
			}
		}
	}

	@Override
	public void addAssetSharingEntry(
		long classNameId, long classPK, long sharedToClassNameId,
		long sharedToClassPK) {

		AssetSharingEntryPK assetSharingEntryPK = new AssetSharingEntryPK(
			classNameId, classPK, sharedToClassNameId, sharedToClassPK);

		AssetSharingEntry assetSharingEntry =
			assetSharingEntryPersistence.fetchByPrimaryKey(assetSharingEntryPK);

		if (assetSharingEntry == null) {
			assetSharingEntry = assetSharingEntryPersistence.create(
				assetSharingEntryPK);

			assetSharingEntryPersistence.update(assetSharingEntry);
		}
	}

	@Override
	public void deleteAssetSharingEntries(long classNameId, long classPK) {
		assetSharingEntryPersistence.removeByC_C(classNameId, classPK);
	}

	public void deleteSharedToAssetSharingEntries(
		long sharedToClassNameId, long sharedToClassPK) {

		assetSharingEntryPersistence.removeByS_S(
			sharedToClassNameId, sharedToClassPK);
	}

	@Override
	public List<AssetSharingEntry> getAssetSharingEntries(
		long classNameId, long classPK) {

		return assetSharingEntryPersistence.findByC_C(classNameId, classPK);
	}

	@Override
	public List<AssetSharingEntry> getAssetSharingEntries(
		long classNameId, long classPK, long sharedToClassNameId) {

		return assetSharingEntryPersistence.findByC_C_S(
			classNameId, classPK, sharedToClassNameId);
	}

	@Override
	public List<AssetSharingEntry> getSharedToAssetSharingEntries(
		long sharedToClassNameId, long sharedToClassPK, int start, int end) {

		return assetSharingEntryPersistence.findByS_S(
			sharedToClassNameId, sharedToClassPK, start, end);
	}

	@Override
	public List<AssetSharingEntry> getSharedToAssetSharingEntries(
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		int start, int end) {

		return assetSharingEntryPersistence.findByC_S_S(
			classNameId, sharedToClassNameId, sharedToClassPK, start, end);
	}

	@Override
	public int getSharedToAssetSharingEntriesCount(
		long sharedToClassNameId, long sharedToClassPK) {

		return assetSharingEntryPersistence.countByS_S(
			sharedToClassNameId, sharedToClassPK);
	}

	@Override
	public int getSharedToAssetSharingEntriesCount(
		long classNameId, long sharedToClassNameId, long sharedToClassPK) {

		return assetSharingEntryPersistence.countByC_S_S(
			classNameId, sharedToClassNameId, sharedToClassPK);
	}

}