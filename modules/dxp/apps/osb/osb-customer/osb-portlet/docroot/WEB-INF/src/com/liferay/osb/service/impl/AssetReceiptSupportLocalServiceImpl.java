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
import com.liferay.osb.model.AssetReceiptSupport;
import com.liferay.osb.service.base.AssetReceiptSupportLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Ryan Park
 * @author Joan Kim
 */
public class AssetReceiptSupportLocalServiceImpl
	extends AssetReceiptSupportLocalServiceBaseImpl {

	public AssetReceiptSupport addAssetReceiptSupport(
			long userId, long assetReceiptId, long assetLicenseId,
			long assetEntryId, long ownerClassNameId, long ownerClassPK,
			long productClassNameId, long productClassPK, String productId,
			Date startDate, Date endDate, int usageType, long supportLifetime)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		long assetReceiptSupportId = counterLocalService.increment();

		AssetReceiptSupport assetReceiptSupport =
			assetReceiptSupportPersistence.create(assetReceiptSupportId);

		assetReceiptSupport.setUserId(userId);
		assetReceiptSupport.setUserName(user.getFullName());
		assetReceiptSupport.setCreateDate(now);
		assetReceiptSupport.setModifiedDate(now);
		assetReceiptSupport.setAssetReceiptId(assetReceiptId);
		assetReceiptSupport.setAssetLicenseId(assetLicenseId);
		assetReceiptSupport.setAssetEntryId(assetEntryId);
		assetReceiptSupport.setOwnerClassNameId(ownerClassNameId);
		assetReceiptSupport.setOwnerClassPK(ownerClassPK);
		assetReceiptSupport.setProductClassNameId(productClassNameId);
		assetReceiptSupport.setProductClassPK(productClassPK);
		assetReceiptSupport.setProductId(productId);
		assetReceiptSupport.setStartDate(startDate);
		assetReceiptSupport.setEndDate(endDate);
		assetReceiptSupport.setUsageType(usageType);
		assetReceiptSupport.setSupportLifetime(supportLifetime);

		assetReceiptSupportPersistence.update(assetReceiptSupport, false);

		return assetReceiptSupport;
	}

	public List<AssetReceiptSupport> getActiveAssetReceiptSupports(
			long assetReceiptId, int usageType, int start, int end)
		throws SystemException {

		Date now = new Date();

		return assetReceiptSupportPersistence.findByARI_LtSD_GtED_UT(
			assetReceiptId, now, now, usageType, start, end);
	}

	public int getActiveAssetReceiptSupportsCount(
			long assetReceiptId, int usageType)
		throws SystemException {

		Date now = new Date();

		return assetReceiptSupportPersistence.countByARI_LtSD_GtED_UT(
			assetReceiptId, now, now, usageType);
	}

	public List<AssetReceiptSupport> getAssetReceiptSupports(
			long assetReceiptId, String productClassName, long productClassPK,
			int start, int end, OrderByComparator obc)
		throws SystemException {

		long productClassNameId = PortalUtil.getClassNameId(productClassName);

		return assetReceiptSupportPersistence.findByARI_PCNI_PCPK(
			assetReceiptId, productClassNameId, productClassPK, start, end,
			obc);
	}

	public List<AssetReceiptSupport> getRenewedAssetReceiptSupports(
			long assetReceiptId, int usageType, int start, int end)
		throws SystemException {

		return assetReceiptSupportPersistence.findByARI_GtSD_UT(
			assetReceiptId, new Date(), usageType, start, end);
	}

	public int getRenewedAssetReceiptSupportsCount(
			long assetReceiptId, int usageType)
		throws SystemException {

		return assetReceiptSupportPersistence.countByARI_GtSD_UT(
			assetReceiptId, new Date(), usageType);
	}

	public AssetReceiptSupport updateAssetReceiptSupport(
			long assetReceiptSupportId, Date endDate)
		throws PortalException, SystemException {

		AssetReceiptSupport assetReceiptSupport =
			assetReceiptSupportPersistence.findByPrimaryKey(
				assetReceiptSupportId);

		assetReceiptSupport.setModifiedDate(new Date());
		assetReceiptSupport.setEndDate(endDate);

		assetReceiptSupportPersistence.update(assetReceiptSupport, false);

		return assetReceiptSupport;
	}

	public List<AssetReceiptSupport> updateAssetReceiptSupports(
			long assetReceiptId, int usageType, Date startDate, Date endDate)
		throws PortalException, SystemException {

		Date now = new Date();

		List<AssetReceiptSupport> assetReceiptSupports =
			assetReceiptSupportPersistence.findByARI_LtSD_GtED_UT(
				assetReceiptId, now, now, usageType);

		for (AssetReceiptSupport assetReceiptSupport : assetReceiptSupports) {
			assetReceiptSupport.setModifiedDate(now);

			if (startDate != null) {
				assetReceiptSupport.setStartDate(startDate);
			}

			if (endDate != null) {
				assetReceiptSupport.setEndDate(endDate);
			}

			assetReceiptSupportPersistence.update(assetReceiptSupport, false);
		}

		return assetReceiptSupports;
	}

}