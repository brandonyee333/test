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
 * This class is a wrapper for {@link AssetLicenseLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetLicenseLocalService
 * @generated
 */
public class AssetLicenseLocalServiceWrapper implements AssetLicenseLocalService,
	ServiceWrapper<AssetLicenseLocalService> {
	public AssetLicenseLocalServiceWrapper(
		AssetLicenseLocalService assetLicenseLocalService) {
		_assetLicenseLocalService = assetLicenseLocalService;
	}

	/**
	* Adds the asset license to the database. Also notifies the appropriate model listeners.
	*
	* @param assetLicense the asset license
	* @return the asset license that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetLicense addAssetLicense(
		com.liferay.osb.model.AssetLicense assetLicense)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.addAssetLicense(assetLicense);
	}

	/**
	* Creates a new asset license with the primary key. Does not add the asset license to the database.
	*
	* @param assetLicenseId the primary key for the new asset license
	* @return the new asset license
	*/
	public com.liferay.osb.model.AssetLicense createAssetLicense(
		long assetLicenseId) {
		return _assetLicenseLocalService.createAssetLicense(assetLicenseId);
	}

	/**
	* Deletes the asset license with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetLicenseId the primary key of the asset license
	* @return the asset license that was removed
	* @throws PortalException if a asset license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetLicense deleteAssetLicense(
		long assetLicenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.deleteAssetLicense(assetLicenseId);
	}

	/**
	* Deletes the asset license from the database. Also notifies the appropriate model listeners.
	*
	* @param assetLicense the asset license
	* @return the asset license that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetLicense deleteAssetLicense(
		com.liferay.osb.model.AssetLicense assetLicense)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.deleteAssetLicense(assetLicense);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetLicenseLocalService.dynamicQuery();
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
		return _assetLicenseLocalService.dynamicQuery(dynamicQuery);
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
		return _assetLicenseLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _assetLicenseLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _assetLicenseLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AssetLicense fetchAssetLicense(
		long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.fetchAssetLicense(assetLicenseId);
	}

	/**
	* Returns the asset license with the primary key.
	*
	* @param assetLicenseId the primary key of the asset license
	* @return the asset license
	* @throws PortalException if a asset license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetLicense getAssetLicense(
		long assetLicenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.getAssetLicense(assetLicenseId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset licenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @return the range of asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetLicense> getAssetLicenses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.getAssetLicenses(start, end);
	}

	/**
	* Returns the number of asset licenses.
	*
	* @return the number of asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public int getAssetLicensesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.getAssetLicensesCount();
	}

	/**
	* Updates the asset license in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetLicense the asset license
	* @return the asset license that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetLicense updateAssetLicense(
		com.liferay.osb.model.AssetLicense assetLicense)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.updateAssetLicense(assetLicense);
	}

	/**
	* Updates the asset license in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetLicense the asset license
	* @param merge whether to merge the asset license with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset license that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetLicense updateAssetLicense(
		com.liferay.osb.model.AssetLicense assetLicense, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.updateAssetLicense(assetLicense, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetLicenseLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetLicenseLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetLicenseLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.AssetLicense addAssetLicense(long userId,
		java.lang.String className, long classPK, java.lang.String licenseId,
		java.lang.String name, int usageType, int licenseType,
		long licenseTypeAllotment, long lifetime)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.addAssetLicense(userId, className,
			classPK, licenseId, name, usageType, licenseType,
			licenseTypeAllotment, lifetime);
	}

	public void copyAssetLicenses(java.lang.String sourceClassName,
		long sourceClassPK, java.lang.String targetClassName, long targetClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_assetLicenseLocalService.copyAssetLicenses(sourceClassName,
			sourceClassPK, targetClassName, targetClassPK);
	}

	public void deleteAssetLicenses(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetLicenseLocalService.deleteAssetLicenses(classNameId, classPK);
	}

	public void deleteAssetLicenses(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetLicenseLocalService.deleteAssetLicenses(className, classPK);
	}

	public com.liferay.osb.model.AssetLicense fetchAssetLicense(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.fetchAssetLicense(classNameId,
			classPK, usageType, licenseType, licenseTypeAllotment, status);
	}

	public com.liferay.osb.model.AssetLicense fetchAssetLicense(
		java.lang.String className, long classPK, int usageType,
		int licenseType, long licenseTypeAllotment, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.fetchAssetLicense(className, classPK,
			usageType, licenseType, licenseTypeAllotment, status);
	}

	public java.util.List<com.liferay.osb.model.AssetLicense> getAssetLicenses(
		long classNameId, long classPK, int usageType, int licenseType,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.getAssetLicenses(classNameId, classPK,
			usageType, licenseType, status, start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.AssetLicense> getAssetLicenses(
		long classNameId, long classPK, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.getAssetLicenses(classNameId, classPK,
			status, start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.AssetLicense> getAssetLicenses(
		java.lang.String className, long classPK, int usageType,
		int licenseType, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.getAssetLicenses(className, classPK,
			usageType, licenseType, status, start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.AssetLicense> getAssetLicenses(
		java.lang.String className, long classPK, int status, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.getAssetLicenses(className, classPK,
			status, start, end, obc);
	}

	public int getAssetLicensesCount(java.lang.String className, long classPK,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.getAssetLicensesCount(className,
			classPK, status);
	}

	public int getAssetLicensesCount(java.lang.String className, long classPK,
		int usageType, int licenseType, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.getAssetLicensesCount(className,
			classPK, usageType, licenseType, status);
	}

	public com.liferay.osb.model.AssetLicense updateAssetLicense(
		long assetLicenseId, java.lang.String name, int usageType,
		int licenseType, long licenseTypeAllotment, long lifetime, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetLicenseLocalService.updateAssetLicense(assetLicenseId,
			name, usageType, licenseType, licenseTypeAllotment, lifetime, status);
	}

	public void updateAssetLicenses(long userId, java.lang.String className,
		long classPK,
		java.util.List<com.liferay.osb.model.AssetLicense> assetLicenses)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_assetLicenseLocalService.updateAssetLicenses(userId, className,
			classPK, assetLicenses);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetLicenseLocalService getWrappedAssetLicenseLocalService() {
		return _assetLicenseLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetLicenseLocalService(
		AssetLicenseLocalService assetLicenseLocalService) {
		_assetLicenseLocalService = assetLicenseLocalService;
	}

	public AssetLicenseLocalService getWrappedService() {
		return _assetLicenseLocalService;
	}

	public void setWrappedService(
		AssetLicenseLocalService assetLicenseLocalService) {
		_assetLicenseLocalService = assetLicenseLocalService;
	}

	private AssetLicenseLocalService _assetLicenseLocalService;
}