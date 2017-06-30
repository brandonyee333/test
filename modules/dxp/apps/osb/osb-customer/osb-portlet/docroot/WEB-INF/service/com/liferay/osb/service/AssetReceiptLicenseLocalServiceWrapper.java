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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AssetReceiptLicenseLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetReceiptLicenseLocalService
 * @generated
 */
public class AssetReceiptLicenseLocalServiceWrapper
	implements AssetReceiptLicenseLocalService,
		ServiceWrapper<AssetReceiptLicenseLocalService> {
	public AssetReceiptLicenseLocalServiceWrapper(
		AssetReceiptLicenseLocalService assetReceiptLicenseLocalService) {
		_assetReceiptLicenseLocalService = assetReceiptLicenseLocalService;
	}

	/**
	* Adds the asset receipt license to the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicense the asset receipt license
	* @return the asset receipt license that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense addAssetReceiptLicense(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.addAssetReceiptLicense(assetReceiptLicense);
	}

	/**
	* Creates a new asset receipt license with the primary key. Does not add the asset receipt license to the database.
	*
	* @param assetReceiptLicenseId the primary key for the new asset receipt license
	* @return the new asset receipt license
	*/
	public com.liferay.osb.model.AssetReceiptLicense createAssetReceiptLicense(
		long assetReceiptLicenseId) {
		return _assetReceiptLicenseLocalService.createAssetReceiptLicense(assetReceiptLicenseId);
	}

	/**
	* Deletes the asset receipt license with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license that was removed
	* @throws PortalException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense deleteAssetReceiptLicense(
		long assetReceiptLicenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.deleteAssetReceiptLicense(assetReceiptLicenseId);
	}

	/**
	* Deletes the asset receipt license from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicense the asset receipt license
	* @return the asset receipt license that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense deleteAssetReceiptLicense(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.deleteAssetReceiptLicense(assetReceiptLicense);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetReceiptLicenseLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AssetReceiptLicense fetchAssetReceiptLicense(
		long assetReceiptLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.fetchAssetReceiptLicense(assetReceiptLicenseId);
	}

	/**
	* Returns the asset receipt license with the primary key.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license
	* @throws PortalException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense getAssetReceiptLicense(
		long assetReceiptLicenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicense(assetReceiptLicenseId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset receipt licenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @return the range of asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getAssetReceiptLicenses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicenses(start,
			end);
	}

	/**
	* Returns the number of asset receipt licenses.
	*
	* @return the number of asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	public int getAssetReceiptLicensesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicensesCount();
	}

	/**
	* Updates the asset receipt license in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicense the asset receipt license
	* @return the asset receipt license that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense updateAssetReceiptLicense(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.updateAssetReceiptLicense(assetReceiptLicense);
	}

	/**
	* Updates the asset receipt license in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicense the asset receipt license
	* @param merge whether to merge the asset receipt license with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset receipt license that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense updateAssetReceiptLicense(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.updateAssetReceiptLicense(assetReceiptLicense,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetReceiptLicenseLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetReceiptLicenseLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetReceiptLicenseLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.osb.model.AssetReceiptLicense addAssetReceiptLicense(
		long userId, long assetReceiptId, long assetLicenseId,
		long assetEntryId, long ownerClassNameId, long ownerClassPK,
		long productClassNameId, long productClassPK,
		java.lang.String productId, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.addAssetReceiptLicense(userId,
			assetReceiptId, assetLicenseId, assetEntryId, ownerClassNameId,
			ownerClassPK, productClassNameId, productClassPK, productId,
			startDate, endDate);
	}

	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getActiveAssetReceiptLicenses(
		long assetReceiptId, int usageType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getActiveAssetReceiptLicenses(assetReceiptId,
			usageType, start, end);
	}

	public int getActiveAssetReceiptLicensesCount(long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getActiveAssetReceiptLicensesCount(assetReceiptId);
	}

	public int getActiveAssetReceiptLicensesCount(long assetReceiptId,
		int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getActiveAssetReceiptLicensesCount(assetReceiptId,
			usageType);
	}

	public long getActiveAssetReceiptLicenseTypeAllotment(long assetReceiptId,
		int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getActiveAssetReceiptLicenseTypeAllotment(assetReceiptId,
			usageType);
	}

	public com.liferay.osb.model.AssetReceiptLicense getAssetReceiptLicense(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicense(uuid);
	}

	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getAssetReceiptLicenses(
		long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicenses(assetReceiptId);
	}

	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getAssetReceiptLicenses(
		long assetReceiptId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicenses(assetReceiptId,
			start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getAssetReceiptLicenses(
		java.lang.String ownerClassName, long ownerClassPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicenses(ownerClassName,
			ownerClassPK, start, end);
	}

	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getAssetReceiptLicenses(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicenses(ownerClassName,
			ownerClassPK, productId);
	}

	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getAssetReceiptLicenses(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String productClassName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicenses(ownerClassName,
			ownerClassPK, productClassName, start, end);
	}

	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getAssetReceiptLicenses(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String productId, int licenseType, long licenseLifetime,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicenses(ownerClassName,
			ownerClassPK, productId, licenseType, licenseLifetime, start, end);
	}

	public int getAssetReceiptLicensesCount(long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicensesCount(assetReceiptId);
	}

	public int getAssetReceiptLicensesCount(long ownerClassNameId,
		long ownerClassPK, java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicensesCount(ownerClassNameId,
			ownerClassPK, productId);
	}

	public int getAssetReceiptLicensesCount(java.lang.String ownerClassName,
		long ownerClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicensesCount(ownerClassName,
			ownerClassPK);
	}

	public int getAssetReceiptLicensesCount(java.lang.String ownerClassName,
		long ownerClassPK, java.lang.String productClassName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicensesCount(ownerClassName,
			ownerClassPK, productClassName);
	}

	public int getAssetReceiptLicensesCount(java.lang.String ownerClassName,
		long ownerClassPK, java.lang.String productId, int licenseType,
		long licenseLifetime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getAssetReceiptLicensesCount(ownerClassName,
			ownerClassPK, productId, licenseType, licenseLifetime);
	}

	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getRenewedAssetReceiptLicenses(
		long assetReceiptId, int usageType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getRenewedAssetReceiptLicenses(assetReceiptId,
			usageType, start, end);
	}

	public int getRenewedAssetReceiptLicensesCount(long assetReceiptId,
		int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getRenewedAssetReceiptLicensesCount(assetReceiptId,
			usageType);
	}

	public long getRenewedAssetReceiptLicenseTypeAllotment(
		long assetReceiptId, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.getRenewedAssetReceiptLicenseTypeAllotment(assetReceiptId,
			usageType);
	}

	public boolean hasAssetReceiptLicense(long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.hasAssetReceiptLicense(assetLicenseId);
	}

	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> updateAssetReceiptLicenses(
		long assetReceiptId, int usageType, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptLicenseLocalService.updateAssetReceiptLicenses(assetReceiptId,
			usageType, startDate, endDate);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetReceiptLicenseLocalService getWrappedAssetReceiptLicenseLocalService() {
		return _assetReceiptLicenseLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetReceiptLicenseLocalService(
		AssetReceiptLicenseLocalService assetReceiptLicenseLocalService) {
		_assetReceiptLicenseLocalService = assetReceiptLicenseLocalService;
	}

	public AssetReceiptLicenseLocalService getWrappedService() {
		return _assetReceiptLicenseLocalService;
	}

	public void setWrappedService(
		AssetReceiptLicenseLocalService assetReceiptLicenseLocalService) {
		_assetReceiptLicenseLocalService = assetReceiptLicenseLocalService;
	}

	private AssetReceiptLicenseLocalService _assetReceiptLicenseLocalService;
}