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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LicenseEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntryLocalService
 * @generated
 */
@ProviderType
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
	*/
	@Override
	public com.liferay.osb.model.LicenseEntry addLicenseEntry(
		com.liferay.osb.model.LicenseEntry licenseEntry) {
		return _licenseEntryLocalService.addLicenseEntry(licenseEntry);
	}

	@Override
	public com.liferay.osb.model.LicenseEntry addLicenseEntry(long userId,
		long productEntryId, java.lang.String name, java.lang.String type,
		int portalVersionMin, int portalVersionMax)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseEntryLocalService.addLicenseEntry(userId,
			productEntryId, name, type, portalVersionMin, portalVersionMax);
	}

	/**
	* Creates a new license entry with the primary key. Does not add the license entry to the database.
	*
	* @param licenseEntryId the primary key for the new license entry
	* @return the new license entry
	*/
	@Override
	public com.liferay.osb.model.LicenseEntry createLicenseEntry(
		long licenseEntryId) {
		return _licenseEntryLocalService.createLicenseEntry(licenseEntryId);
	}

	/**
	* Deletes the license entry from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseEntry the license entry
	* @return the license entry that was removed
	*/
	@Override
	public com.liferay.osb.model.LicenseEntry deleteLicenseEntry(
		com.liferay.osb.model.LicenseEntry licenseEntry) {
		return _licenseEntryLocalService.deleteLicenseEntry(licenseEntry);
	}

	/**
	* Deletes the license entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseEntryId the primary key of the license entry
	* @return the license entry that was removed
	* @throws PortalException if a license entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.LicenseEntry deleteLicenseEntry(
		long licenseEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseEntryLocalService.deleteLicenseEntry(licenseEntryId);
	}

	@Override
	public com.liferay.osb.model.LicenseEntry fetchLicenseEntry(
		long licenseEntryId) {
		return _licenseEntryLocalService.fetchLicenseEntry(licenseEntryId);
	}

	/**
	* Returns the license entry with the primary key.
	*
	* @param licenseEntryId the primary key of the license entry
	* @return the license entry
	* @throws PortalException if a license entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.LicenseEntry getLicenseEntry(
		long licenseEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseEntryLocalService.getLicenseEntry(licenseEntryId);
	}

	@Override
	public com.liferay.osb.model.LicenseEntry getLicenseEntry(
		long productEntryId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseEntryLocalService.getLicenseEntry(productEntryId, type);
	}

	/**
	* Updates the license entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param licenseEntry the license entry
	* @return the license entry that was updated
	*/
	@Override
	public com.liferay.osb.model.LicenseEntry updateLicenseEntry(
		com.liferay.osb.model.LicenseEntry licenseEntry) {
		return _licenseEntryLocalService.updateLicenseEntry(licenseEntry);
	}

	@Override
	public com.liferay.osb.model.LicenseEntry updateLicenseEntry(
		long licenseEntryId, long productEntryId, java.lang.String name,
		java.lang.String type, int portalVersionMin, int portalVersionMax)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseEntryLocalService.updateLicenseEntry(licenseEntryId,
			productEntryId, name, type, portalVersionMin, portalVersionMax);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _licenseEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _licenseEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _licenseEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of license entries.
	*
	* @return the number of license entries
	*/
	@Override
	public int getLicenseEntriesCount() {
		return _licenseEntryLocalService.getLicenseEntriesCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _licenseEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _licenseEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _licenseEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _licenseEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _licenseEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the license entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @return the range of license entries
	*/
	@Override
	public java.util.List<com.liferay.osb.model.LicenseEntry> getLicenseEntries(
		int start, int end) {
		return _licenseEntryLocalService.getLicenseEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseEntry> getLicenseEntries(
		long productEntryId) {
		return _licenseEntryLocalService.getLicenseEntries(productEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseEntry> getLicenseEntries(
		long productEntryId, int portalVersion) {
		return _licenseEntryLocalService.getLicenseEntries(productEntryId,
			portalVersion);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _licenseEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _licenseEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public LicenseEntryLocalService getWrappedService() {
		return _licenseEntryLocalService;
	}

	@Override
	public void setWrappedService(
		LicenseEntryLocalService licenseEntryLocalService) {
		_licenseEntryLocalService = licenseEntryLocalService;
	}

	private LicenseEntryLocalService _licenseEntryLocalService;
}