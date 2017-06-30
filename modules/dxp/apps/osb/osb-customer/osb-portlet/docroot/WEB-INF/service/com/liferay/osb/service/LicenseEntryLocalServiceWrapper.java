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
 * This class is a wrapper for {@link LicenseEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LicenseEntryLocalService
 * @generated
 */
public class LicenseEntryLocalServiceWrapper implements LicenseEntryLocalService,
	ServiceWrapper<LicenseEntryLocalService> {
	public LicenseEntryLocalServiceWrapper(
		LicenseEntryLocalService licenseEntryLocalService) {
		_licenseEntryLocalService = licenseEntryLocalService;
	}

	/**
	* Adds the license entry to the database. Also notifies the appropriate model listeners.
	*
	* @param licenseEntry the license entry
	* @return the license entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry addLicenseEntry(
		com.liferay.osb.model.LicenseEntry licenseEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntryLocalService.addLicenseEntry(licenseEntry);
	}

	/**
	* Creates a new license entry with the primary key. Does not add the license entry to the database.
	*
	* @param licenseEntryId the primary key for the new license entry
	* @return the new license entry
	*/
	public com.liferay.osb.model.LicenseEntry createLicenseEntry(
		long licenseEntryId) {
		return _licenseEntryLocalService.createLicenseEntry(licenseEntryId);
	}

	/**
	* Deletes the license entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseEntryId the primary key of the license entry
	* @return the license entry that was removed
	* @throws PortalException if a license entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry deleteLicenseEntry(
		long licenseEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntryLocalService.deleteLicenseEntry(licenseEntryId);
	}

	/**
	* Deletes the license entry from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseEntry the license entry
	* @return the license entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry deleteLicenseEntry(
		com.liferay.osb.model.LicenseEntry licenseEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntryLocalService.deleteLicenseEntry(licenseEntry);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _licenseEntryLocalService.dynamicQuery();
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
		return _licenseEntryLocalService.dynamicQuery(dynamicQuery);
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
		return _licenseEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _licenseEntryLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _licenseEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.LicenseEntry fetchLicenseEntry(
		long licenseEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntryLocalService.fetchLicenseEntry(licenseEntryId);
	}

	/**
	* Returns the license entry with the primary key.
	*
	* @param licenseEntryId the primary key of the license entry
	* @return the license entry
	* @throws PortalException if a license entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry getLicenseEntry(
		long licenseEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntryLocalService.getLicenseEntry(licenseEntryId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the license entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @return the range of license entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LicenseEntry> getLicenseEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntryLocalService.getLicenseEntries(start, end);
	}

	/**
	* Returns the number of license entries.
	*
	* @return the number of license entries
	* @throws SystemException if a system exception occurred
	*/
	public int getLicenseEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntryLocalService.getLicenseEntriesCount();
	}

	/**
	* Updates the license entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param licenseEntry the license entry
	* @return the license entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry updateLicenseEntry(
		com.liferay.osb.model.LicenseEntry licenseEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntryLocalService.updateLicenseEntry(licenseEntry);
	}

	/**
	* Updates the license entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param licenseEntry the license entry
	* @param merge whether to merge the license entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the license entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LicenseEntry updateLicenseEntry(
		com.liferay.osb.model.LicenseEntry licenseEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntryLocalService.updateLicenseEntry(licenseEntry, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _licenseEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_licenseEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _licenseEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.LicenseEntry addLicenseEntry(long userId,
		long productEntryId, java.lang.String name, java.lang.String type,
		int portalVersionMin, int portalVersionMax)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntryLocalService.addLicenseEntry(userId,
			productEntryId, name, type, portalVersionMin, portalVersionMax);
	}

	public java.util.List<com.liferay.osb.model.LicenseEntry> getLicenseEntries(
		long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntryLocalService.getLicenseEntries(productEntryId);
	}

	public java.util.List<com.liferay.osb.model.LicenseEntry> getLicenseEntries(
		long productEntryId, int portalVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntryLocalService.getLicenseEntries(productEntryId,
			portalVersion);
	}

	public com.liferay.osb.model.LicenseEntry getLicenseEntry(
		long productEntryId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntryLocalService.getLicenseEntry(productEntryId, type);
	}

	public com.liferay.osb.model.LicenseEntry updateLicenseEntry(
		long licenseEntryId, long productEntryId, java.lang.String name,
		java.lang.String type, int portalVersionMin, int portalVersionMax)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseEntryLocalService.updateLicenseEntry(licenseEntryId,
			productEntryId, name, type, portalVersionMin, portalVersionMax);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public LicenseEntryLocalService getWrappedLicenseEntryLocalService() {
		return _licenseEntryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedLicenseEntryLocalService(
		LicenseEntryLocalService licenseEntryLocalService) {
		_licenseEntryLocalService = licenseEntryLocalService;
	}

	public LicenseEntryLocalService getWrappedService() {
		return _licenseEntryLocalService;
	}

	public void setWrappedService(
		LicenseEntryLocalService licenseEntryLocalService) {
		_licenseEntryLocalService = licenseEntryLocalService;
	}

	private LicenseEntryLocalService _licenseEntryLocalService;
}