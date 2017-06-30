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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.AssetListAssetEntry;
import com.liferay.osb.service.base.AssetListAssetEntryLocalServiceBaseImpl;
import com.liferay.osb.util.comparator.AssetListAssetEntryPKComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portlet.asset.NoSuchEntryException;
import com.liferay.portlet.asset.model.AssetEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 */
public class AssetListAssetEntryLocalServiceImpl
	extends AssetListAssetEntryLocalServiceBaseImpl {

	public AssetListAssetEntry addAssetListAssetEntry(
			long assetListId, long assetEntryId)
		throws PortalException, SystemException {

		// Asset list asset entry

		AssetEntry assetEntry = assetEntryPersistence.findByPrimaryKey(
			assetEntryId);

		long assetListEntryId = counterLocalService.increment();

		AssetListAssetEntry assetListAssetEntry =
			assetListAssetEntryPersistence.create(assetListEntryId);

		assetListAssetEntry.setAssetEntryId(assetEntryId);
		assetListAssetEntry.setAssetListId(assetListId);

		assetListAssetEntryPersistence.update(assetListAssetEntry, false);

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			assetEntry.getClassName());

		indexer.reindex(assetEntry.getClassName(), assetEntry.getClassPK());

		return assetListAssetEntry;
	}

	@Override
	public AssetListAssetEntry deleteAssetListAssetEntry(
			AssetListAssetEntry assetListAssetEntry)
		throws PortalException, SystemException {

		assetListAssetEntryPersistence.remove(assetListAssetEntry);

		// Indexer

		AssetEntry assetEntry = assetEntryPersistence.findByPrimaryKey(
			assetListAssetEntry.getAssetEntryId());

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			assetEntry.getClassName());

		indexer.reindex(assetEntry.getClassName(), assetEntry.getClassPK());

		return assetListAssetEntry;
	}

	@Override
	public AssetListAssetEntry deleteAssetListAssetEntry(
			long assetListAssetEntryId)
		throws PortalException, SystemException {

		AssetListAssetEntry assetListAssetEntry =
			assetListAssetEntryPersistence.findByPrimaryKey(
				assetListAssetEntryId);

		return deleteAssetListAssetEntry(assetListAssetEntry);
	}

	public void deleteAssetListAssetListAssetEntry(long asestListId)
		throws PortalException, SystemException {

		List<AssetListAssetEntry> assetListAssetEntries =
			assetListAssetEntryPersistence.findByAssetListId(asestListId);

		for (AssetListAssetEntry assetListAssetEntry : assetListAssetEntries) {
			deleteAssetListAssetEntry(assetListAssetEntry);
		}
	}

	public List<AssetEntry> getAssetEntries(long assetListId)
		throws PortalException, SystemException {

		List<AssetEntry> assetEntries = new ArrayList<AssetEntry>();

		List<AssetListAssetEntry> assetListAssetEntries =
			assetListAssetEntryPersistence.findByAssetListId(
				assetListId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new AssetListAssetEntryPKComparator(true));

		for (AssetListAssetEntry assetListAssetEntry : assetListAssetEntries) {
			try {
				assetEntries.add(assetListAssetEntry.getAssetEntry());
			}
			catch (NoSuchEntryException nsee) {
				assetListAssetEntryPersistence.removeByAssetEntryId(
					assetListAssetEntry.getAssetEntryId());
			}
		}

		return assetEntries;
	}

	public List<AssetListAssetEntry> getAssetListAssetEntries(long assetListId)
		throws SystemException {

		return assetListAssetEntryPersistence.findByAssetListId(assetListId);
	}

	public void setAssetListAssetEntries(
			long assetListId, List<AssetEntry> assetEntries)
		throws PortalException, SystemException {

		deleteAssetListAssetListAssetEntry(assetListId);

		for (AssetEntry assetEntry : assetEntries) {
			addAssetListAssetEntry(assetListId, assetEntry.getEntryId());
		}
	}

	public void setAssetListAssetEntries(long assetListId, long[] assetEntryIds)
		throws PortalException, SystemException {

		deleteAssetListAssetListAssetEntry(assetListId);

		for (long assetEntryId : assetEntryIds) {
			addAssetListAssetEntry(assetListId, assetEntryId);
		}
	}

}