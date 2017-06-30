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

package com.liferay.osb.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * The utility for the asset receipt remote service. This utility wraps {@link com.liferay.osb.service.impl.AssetReceiptServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptService
 * @see com.liferay.osb.service.base.AssetReceiptServiceBaseImpl
 * @see com.liferay.osb.service.impl.AssetReceiptServiceImpl
 * @generated
 */
public class AssetReceiptServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AssetReceiptServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.osb.model.AssetReceipt fetchAssetReceipt(
		long assetReceiptId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAssetReceipt(assetReceiptId);
	}

	public static com.liferay.osb.model.AssetReceipt fetchAssetReceipt(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String productClassName, long productClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchAssetReceipt(ownerClassName, ownerClassPK,
			productClassName, productClassPK);
	}

	public static com.liferay.osb.model.AssetReceipt getAssetReceipt(
		long assetReceiptId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetReceipt(assetReceiptId);
	}

	public static com.liferay.osb.model.AssetReceipt getAssetReceipt(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String productClassName, long productClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetReceipt(ownerClassName, ownerClassPK,
			productClassName, productClassPK);
	}

	public static com.liferay.osb.model.AssetReceipt purchaseAssets(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String legalEntityName, java.lang.String productClassName,
		long productClassPK, long type, long ecDocumentEntryId, long countryId,
		int domain, boolean validateContractEntries, long eulaContractEntryId,
		long tosContractEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .purchaseAssets(ownerClassName, ownerClassPK,
			legalEntityName, productClassName, productClassPK, type,
			ecDocumentEntryId, countryId, domain, validateContractEntries,
			eulaContractEntryId, tosContractEntryId);
	}

	public static com.liferay.osb.model.AssetReceipt updateAssetReceipt(
		long assetReceiptId, java.lang.String ownerClassName, long ownerClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAssetReceipt(assetReceiptId, ownerClassName,
			ownerClassPK);
	}

	public static void clearService() {
		_service = null;
	}

	public static AssetReceiptService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AssetReceiptService.class.getName());

			if (invokableService instanceof AssetReceiptService) {
				_service = (AssetReceiptService)invokableService;
			}
			else {
				_service = new AssetReceiptServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(AssetReceiptServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AssetReceiptService service) {
	}

	private static AssetReceiptService _service;
}