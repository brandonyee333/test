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

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.AssetRecommendationEntry;
import com.liferay.osb.model.AssetRecommendationEntryConstants;
import com.liferay.osb.service.base.AssetRecommendationEntryLocalServiceBaseImpl;
import com.liferay.osb.util.comparator.AssetRecommendationEntryPurchasedAlsoPurchasedCountComparator;
import com.liferay.osb.util.comparator.AssetRecommendationEntryViewedAlsoPurchasedCountComparator;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Collections;
import java.util.List;

/**
 * @author Peter Shin
 */
public class AssetRecommendationEntryLocalServiceImpl
	extends AssetRecommendationEntryLocalServiceBaseImpl {

	public void deleteAssetRecommendationEntries(String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		assetRecommendationEntryPersistence.removeByC_C(classNameId, classPK);
	}

	public List<AssetRecommendationEntry> getAssetRecommendationEntries(
			long assetRecommendationSetId, int type, int start, int end)
		throws SystemException {

		if (type ==
				AssetRecommendationEntryConstants.
					TYPE_PURCHASED_ALSO_PURCHASED) {

			return assetRecommendationEntryPersistence.findByARSI_GtPAPC(
				assetRecommendationSetId, 0, start, end,
				getPurchasedAlsoPurchasedCountComparator());
		}
		else if (type ==
					AssetRecommendationEntryConstants.
						TYPE_VIEWED_ALSO_PURCHASED) {

			return assetRecommendationEntryPersistence.findByARSI_GtVAPC(
				assetRecommendationSetId, 0, start, end,
				getViewedAlsoPurchasedCountComparator());
		}

		return Collections.emptyList();
	}

	public AssetRecommendationEntry updateAssetRecommendationEntry(
			long assetRecommendationSetId, String className, long classPK,
			int type)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		AssetRecommendationEntry assetRecommendationEntry =
			assetRecommendationEntryPersistence.fetchByARSI_C_C(
				assetRecommendationSetId, classNameId, classPK);

		if (assetRecommendationEntry == null) {
			long assetRecommendationEntryId = counterLocalService.increment();

			assetRecommendationEntry =
				assetRecommendationEntryPersistence.create(
					assetRecommendationEntryId);

			assetRecommendationEntry.setAssetRecommendationSetId(
				assetRecommendationSetId);
			assetRecommendationEntry.setClassNameId(classNameId);
			assetRecommendationEntry.setClassPK(classPK);
			assetRecommendationEntry.setViewedAlsoPurchasedCount(0);
			assetRecommendationEntry.setPurchasedAlsoPurchasedCount(0);
		}

		if (type ==
				AssetRecommendationEntryConstants.
					TYPE_PURCHASED_ALSO_PURCHASED) {

			assetRecommendationEntry.setPurchasedAlsoPurchasedCount(
				assetRecommendationEntry.getPurchasedAlsoPurchasedCount() + 1);
		}
		else if (type ==
					AssetRecommendationEntryConstants.
						TYPE_VIEWED_ALSO_PURCHASED) {

			assetRecommendationEntry.setViewedAlsoPurchasedCount(
				assetRecommendationEntry.getViewedAlsoPurchasedCount() + 1);
		}

		assetRecommendationEntryPersistence.update(
			assetRecommendationEntry, false);

		return assetRecommendationEntry;
	}

	protected OrderByComparator getPurchasedAlsoPurchasedCountComparator() {
		OrderByComparator orderByComparator =
			new AssetRecommendationEntryPurchasedAlsoPurchasedCountComparator();

		return orderByComparator;
	}

	protected OrderByComparator getViewedAlsoPurchasedCountComparator() {
		OrderByComparator orderByComparator =
			new AssetRecommendationEntryViewedAlsoPurchasedCountComparator();

		return orderByComparator;
	}

}