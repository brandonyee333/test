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
 * This class is a wrapper for {@link LicenseKeySetLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LicenseKeySetLocalService
 * @generated
 */
public class LicenseKeySetLocalServiceWrapper
	implements LicenseKeySetLocalService,
		ServiceWrapper<LicenseKeySetLocalService> {
	public LicenseKeySetLocalServiceWrapper(
		LicenseKeySetLocalService licenseKeySetLocalService) {
		_licenseKeySetLocalService = licenseKeySetLocalService;
	}

	/**
	* Adds the license key set to the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKeySet the license key set
	* @return the license key set that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKeySet addLicenseKeySet(
		com.liferay.osb.model.LicenseKeySet licenseKeySet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetLocalService.addLicenseKeySet(licenseKeySet);
	}

	/**
	* Creates a new license key set with the primary key. Does not add the license key set to the database.
	*
	* @param licenseKeySetId the primary key for the new license key set
	* @return the new license key set
	*/
	public com.liferay.osb.model.LicenseKeySet createLicenseKeySet(
		long licenseKeySetId) {
		return _licenseKeySetLocalService.createLicenseKeySet(licenseKeySetId);
	}

	/**
	* Deletes the license key set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKeySetId the primary key of the license key set
	* @return the license key set that was removed
	* @throws PortalException if a license key set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKeySet deleteLicenseKeySet(
		long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetLocalService.deleteLicenseKeySet(licenseKeySetId);
	}

	/**
	* Deletes the license key set from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKeySet the license key set
	* @return the license key set that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKeySet deleteLicenseKeySet(
		com.liferay.osb.model.LicenseKeySet licenseKeySet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetLocalService.deleteLicenseKeySet(licenseKeySet);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _licenseKeySetLocalService.dynamicQuery();
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
		return _licenseKeySetLocalService.dynamicQuery(dynamicQuery);
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
		return _licenseKeySetLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _licenseKeySetLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _licenseKeySetLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.LicenseKeySet fetchLicenseKeySet(
		long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetLocalService.fetchLicenseKeySet(licenseKeySetId);
	}

	/**
	* Returns the license key set with the primary key.
	*
	* @param licenseKeySetId the primary key of the license key set
	* @return the license key set
	* @throws PortalException if a license key set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKeySet getLicenseKeySet(
		long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetLocalService.getLicenseKeySet(licenseKeySetId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the license key sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @return the range of license key sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseKeySet> getLicenseKeySets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetLocalService.getLicenseKeySets(start, end);
	}

	/**
	* Returns the number of license key sets.
	*
	* @return the number of license key sets
	* @throws SystemException if a system exception occurred
	*/
	public int getLicenseKeySetsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetLocalService.getLicenseKeySetsCount();
	}

	/**
	* Updates the license key set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param licenseKeySet the license key set
	* @return the license key set that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKeySet updateLicenseKeySet(
		com.liferay.osb.model.LicenseKeySet licenseKeySet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetLocalService.updateLicenseKeySet(licenseKeySet);
	}

	/**
	* Updates the license key set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param licenseKeySet the license key set
	* @param merge whether to merge the license key set with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the license key set that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseKeySet updateLicenseKeySet(
		com.liferay.osb.model.LicenseKeySet licenseKeySet, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetLocalService.updateLicenseKeySet(licenseKeySet,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _licenseKeySetLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_licenseKeySetLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _licenseKeySetLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.LicenseKeySet addLicenseKeySet(long userId,
		long accountEntryId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetLocalService.addLicenseKeySet(userId,
			accountEntryId, name);
	}

	public java.util.List<com.liferay.osb.model.LicenseKeySet> getAccountEntryLicenseKeySets(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetLocalService.getAccountEntryLicenseKeySets(accountEntryId,
			start, end);
	}

	public int getAccountEntryLicenseKeySetsCount(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetLocalService.getAccountEntryLicenseKeySetsCount(accountEntryId);
	}

	public com.liferay.osb.model.LicenseKeySet updateLicenseKeySet(
		long licenseKeySetId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetLocalService.updateLicenseKeySet(licenseKeySetId,
			name);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public LicenseKeySetLocalService getWrappedLicenseKeySetLocalService() {
		return _licenseKeySetLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedLicenseKeySetLocalService(
		LicenseKeySetLocalService licenseKeySetLocalService) {
		_licenseKeySetLocalService = licenseKeySetLocalService;
	}

	public LicenseKeySetLocalService getWrappedService() {
		return _licenseKeySetLocalService;
	}

	public void setWrappedService(
		LicenseKeySetLocalService licenseKeySetLocalService) {
		_licenseKeySetLocalService = licenseKeySetLocalService;
	}

	private LicenseKeySetLocalService _licenseKeySetLocalService;
}