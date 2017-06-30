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

import com.liferay.osb.ContractAuditAcceptanceException;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.ContractEntry;
import com.liferay.osb.service.base.AssetReceiptServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAppEntryPermission;
import com.liferay.osb.service.permission.OSBAssetReceiptPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.security.auth.PrincipalException;

/**
 * @author Douglas Wong
 * @author Ryan Park
 * @author Joan Kim
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AssetReceiptServiceImpl extends AssetReceiptServiceBaseImpl {

	public AssetReceipt fetchAssetReceipt(long assetReceiptId)
		throws PortalException, SystemException {

		AssetReceipt assetReceipt = assetReceiptLocalService.fetchAssetReceipt(
			assetReceiptId);

		if (assetReceipt != null) {
			OSBAssetReceiptPermission.check(
				getPermissionChecker(), assetReceipt, OSBActionKeys.VIEW);
		}

		return assetReceipt;
	}

	public AssetReceipt fetchAssetReceipt(
			String ownerClassName, long ownerClassPK, String productClassName,
			long productClassPK)
		throws PortalException, SystemException {

		AssetReceipt assetReceipt = assetReceiptLocalService.fetchAssetReceipt(
			ownerClassName, ownerClassPK, productClassName, productClassPK);

		if (assetReceipt != null) {
			OSBAssetReceiptPermission.check(
				getPermissionChecker(), assetReceipt, OSBActionKeys.VIEW);
		}

		return assetReceipt;
	}

	public AssetReceipt getAssetReceipt(long assetReceiptId)
		throws PortalException, SystemException {

		OSBAssetReceiptPermission.check(
			getPermissionChecker(), assetReceiptId, OSBActionKeys.VIEW);

		return assetReceiptLocalService.getAssetReceipt(assetReceiptId);
	}

	public AssetReceipt getAssetReceipt(
			String ownerClassName, long ownerClassPK, String productClassName,
			long productClassPK)
		throws PortalException, SystemException {

		AssetReceipt assetReceipt = assetReceiptLocalService.getAssetReceipt(
			ownerClassName, ownerClassPK, productClassName, productClassPK);

		OSBAssetReceiptPermission.check(
			getPermissionChecker(), assetReceipt, OSBActionKeys.VIEW);

		return assetReceipt;
	}

	public AssetReceipt purchaseAssets(
			String ownerClassName, long ownerClassPK, String legalEntityName,
			String productClassName, long productClassPK, long type,
			long ecDocumentEntryId, long countryId, int domain,
			boolean validateContractEntries, long eulaContractEntryId,
			long tosContractEntryId)
		throws PortalException, SystemException {

		if (productClassName.equals(AppEntry.class.getName())) {
			OSBAppEntryPermission.check(
				getPermissionChecker(), productClassPK,
				OSBActionKeys.PURCHASE_APP);
		}

		validate(productClassName, productClassPK);

		updateContractAudit(
			ownerClassName, ownerClassPK, productClassName, productClassPK,
			validateContractEntries, eulaContractEntryId, tosContractEntryId);

		return assetReceiptLocalService.purchaseAssets(
			getUserId(), ownerClassName, ownerClassPK, legalEntityName,
			productClassName, productClassPK, type, ecDocumentEntryId,
			countryId, domain);
	}

	public AssetReceipt updateAssetReceipt(
			long assetReceiptId, String ownerClassName, long ownerClassPK)
		throws PortalException, SystemException {

		OSBAssetReceiptPermission.check(
			getPermissionChecker(), assetReceiptId, OSBActionKeys.UPDATE);

		return assetReceiptLocalService.updateAssetReceipt(
			assetReceiptId, ownerClassName, ownerClassPK);
	}

	protected void updateContractAudit(
			String ownerClassName, long ownerClassPK, String productClassName,
			long productClassPK, boolean validateContractEntries,
			long eulaContractEntryId, long tosContractEntryId)
		throws PortalException, SystemException {

		if (validateContractEntries) {
			ContractEntry contractEntry =
				contractEntryPersistence.fetchByPrimaryKey(eulaContractEntryId);

			if (contractEntry == null) {
				throw new ContractAuditAcceptanceException();
			}

			contractEntry = contractEntryPersistence.fetchByPrimaryKey(
				tosContractEntryId);

			if (contractEntry == null) {
				throw new ContractAuditAcceptanceException();
			}

			contractAuditLocalService.addContractAudit(
				getUserId(), eulaContractEntryId, ownerClassName, ownerClassPK,
				productClassName, productClassPK);

			contractAuditLocalService.addContractAudit(
				getUserId(), tosContractEntryId, ownerClassName, ownerClassPK,
				productClassName, productClassPK);
		}
		else if (!roleLocalService.hasUserRole(
					getUserId(), OSBConstants.ROLE_OSB_MARKETPLACE_ADMIN_ID)) {

			throw new PrincipalException();
		}
	}

	protected void validate(String productClassName, long productClassPK)
		throws PortalException, SystemException {

		if (productClassName.equals(AppEntry.class.getName())) {
			appEntryLocalService.validatePurchase(productClassPK);
		}
	}

}