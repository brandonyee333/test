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
import com.liferay.osb.model.AssetAudit;
import com.liferay.osb.model.AssetAuditConstants;
import com.liferay.osb.model.AssetRecommendationEntryConstants;
import com.liferay.osb.model.AssetRecommendationSet;
import com.liferay.osb.service.base.AssetRecommendationSetLocalServiceBaseImpl;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.util.List;

/**
 * @author Peter Shin
 */
public class AssetRecommendationSetLocalServiceImpl
	extends AssetRecommendationSetLocalServiceBaseImpl {

	public AssetRecommendationSet addAssetRecommendationSet(
			String className, long classPK)
		throws SystemException {

		// Asset recommendation set

		long classNameId = PortalUtil.getClassNameId(className);

		long assetRecommendationSetId = counterLocalService.increment();

		AssetRecommendationSet assetRecommendationSet =
			assetRecommendationSetPersistence.create(assetRecommendationSetId);

		assetRecommendationSet.setClassNameId(classNameId);
		assetRecommendationSet.setClassPK(classPK);

		assetRecommendationSetPersistence.update(assetRecommendationSet, false);

		return assetRecommendationSet;
	}

	public void deleteAssetRecommendationSet(String className, long classPK)
		throws SystemException {

		// Asset recommendation set

		long classNameId = PortalUtil.getClassNameId(className);

		AssetRecommendationSet assetRecommendationSet =
			assetRecommendationSetPersistence.fetchByC_C(classNameId, classPK);

		if (assetRecommendationSet == null) {
			return;
		}

		assetRecommendationSetPersistence.remove(assetRecommendationSet);

		// Asset recommendation entries

		assetRecommendationEntryLocalService.deleteAssetRecommendationEntries(
			className, classPK);
	}

	public AssetRecommendationSet fetchAssetRecommendationSet(
			String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return assetRecommendationSetPersistence.fetchByC_C(
			classNameId, classPK);
	}

	public void updateAssetRecommendationSets(
			long userId, String className, long classPK, int type)
		throws PortalException, SystemException {

		if (type != AssetAuditConstants.TYPE_PURCHASE) {
			return;
		}

		User user = userPersistence.findByPrimaryKey(userId);

		if (user.isDefaultUser()) {
			return;
		}

		List<AssetAudit> pvAssetAudits = assetAuditFinder.findByU_T(
			userId, AssetAuditConstants.TYPE_VIEW, 0,
			PortletPropsValues.ASSET_RECOMMENDATION_SET_MAX_ASSET_AUDITS);

		for (AssetAudit pvAssetAudit : pvAssetAudits) {
			String curClassName = pvAssetAudit.getClassName();
			long curClassPK = pvAssetAudit.getClassPK();

			if (curClassName.equals(className) && (curClassPK == classPK)) {
				continue;
			}

			// Asset recommendation set

			AssetRecommendationSet assetRecommendationSet =
				assetRecommendationSetPersistence.fetchByC_C(
					PortalUtil.getClassNameId(curClassName), curClassPK);

			if (assetRecommendationSet == null) {
				assetRecommendationSet =
					assetRecommendationSetLocalService.
						addAssetRecommendationSet(curClassName, curClassPK);
			}

			// Asset recommendation entry

			assetRecommendationEntryLocalService.updateAssetRecommendationEntry(
				assetRecommendationSet.getAssetRecommendationSetId(), className,
				classPK,
				AssetRecommendationEntryConstants.TYPE_VIEWED_ALSO_PURCHASED);
		}

		List<AssetAudit> ppAssetAudits = assetAuditFinder.findByU_T(
			userId, AssetAuditConstants.TYPE_PURCHASE, 0,
			PortletPropsValues.ASSET_RECOMMENDATION_SET_MAX_ASSET_AUDITS);

		for (AssetAudit ppAssetAudit : ppAssetAudits) {
			String curClassName = ppAssetAudit.getClassName();
			long curClassPK = ppAssetAudit.getClassPK();

			if (curClassName.equals(className) && (curClassPK == classPK)) {
				continue;
			}

			// Asset recommendation set

			AssetRecommendationSet assetRecommendationSet =
				assetRecommendationSetPersistence.fetchByC_C(
					PortalUtil.getClassNameId(curClassName), curClassPK);

			if (assetRecommendationSet == null) {
				assetRecommendationSet =
					assetRecommendationSetLocalService.
						addAssetRecommendationSet(curClassName, curClassPK);
			}

			// Asset recommendation entry

			assetRecommendationEntryLocalService.updateAssetRecommendationEntry(
				assetRecommendationSet.getAssetRecommendationSetId(), className,
				classPK,
				AssetRecommendationEntryConstants.
					TYPE_PURCHASED_ALSO_PURCHASED);
		}
	}

}