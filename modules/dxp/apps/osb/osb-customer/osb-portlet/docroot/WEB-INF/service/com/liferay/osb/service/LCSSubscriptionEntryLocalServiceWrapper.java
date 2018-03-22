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
 * Provides a wrapper for {@link LCSSubscriptionEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LCSSubscriptionEntryLocalService
 * @generated
 */
@ProviderType
public class LCSSubscriptionEntryLocalServiceWrapper
	implements LCSSubscriptionEntryLocalService,
		ServiceWrapper<LCSSubscriptionEntryLocalService> {
	public LCSSubscriptionEntryLocalServiceWrapper(
		LCSSubscriptionEntryLocalService lcsSubscriptionEntryLocalService) {
		_lcsSubscriptionEntryLocalService = lcsSubscriptionEntryLocalService;
	}

	/**
	* Adds the lcs subscription entry to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntry the lcs subscription entry
	* @return the lcs subscription entry that was added
	*/
	@Override
	public com.liferay.osb.model.LCSSubscriptionEntry addLCSSubscriptionEntry(
		com.liferay.osb.model.LCSSubscriptionEntry lcsSubscriptionEntry) {
		return _lcsSubscriptionEntryLocalService.addLCSSubscriptionEntry(lcsSubscriptionEntry);
	}

	/**
	* Creates a new lcs subscription entry with the primary key. Does not add the lcs subscription entry to the database.
	*
	* @param lcsSubscriptionEntryId the primary key for the new lcs subscription entry
	* @return the new lcs subscription entry
	*/
	@Override
	public com.liferay.osb.model.LCSSubscriptionEntry createLCSSubscriptionEntry(
		long lcsSubscriptionEntryId) {
		return _lcsSubscriptionEntryLocalService.createLCSSubscriptionEntry(lcsSubscriptionEntryId);
	}

	/**
	* Deletes the lcs subscription entry from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntry the lcs subscription entry
	* @return the lcs subscription entry that was removed
	*/
	@Override
	public com.liferay.osb.model.LCSSubscriptionEntry deleteLCSSubscriptionEntry(
		com.liferay.osb.model.LCSSubscriptionEntry lcsSubscriptionEntry) {
		return _lcsSubscriptionEntryLocalService.deleteLCSSubscriptionEntry(lcsSubscriptionEntry);
	}

	/**
	* Deletes the lcs subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntryId the primary key of the lcs subscription entry
	* @return the lcs subscription entry that was removed
	* @throws PortalException if a lcs subscription entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.LCSSubscriptionEntry deleteLCSSubscriptionEntry(
		long lcsSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsSubscriptionEntryLocalService.deleteLCSSubscriptionEntry(lcsSubscriptionEntryId);
	}

	@Override
	public com.liferay.osb.model.LCSSubscriptionEntry fetchLCSSubscriptionEntry(
		long lcsSubscriptionEntryId) {
		return _lcsSubscriptionEntryLocalService.fetchLCSSubscriptionEntry(lcsSubscriptionEntryId);
	}

	/**
	* Returns the lcs subscription entry with the primary key.
	*
	* @param lcsSubscriptionEntryId the primary key of the lcs subscription entry
	* @return the lcs subscription entry
	* @throws PortalException if a lcs subscription entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.LCSSubscriptionEntry getLCSSubscriptionEntry(
		long lcsSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsSubscriptionEntryLocalService.getLCSSubscriptionEntry(lcsSubscriptionEntryId);
	}

	/**
	* Updates the lcs subscription entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntry the lcs subscription entry
	* @return the lcs subscription entry that was updated
	*/
	@Override
	public com.liferay.osb.model.LCSSubscriptionEntry updateLCSSubscriptionEntry(
		com.liferay.osb.model.LCSSubscriptionEntry lcsSubscriptionEntry) {
		return _lcsSubscriptionEntryLocalService.updateLCSSubscriptionEntry(lcsSubscriptionEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _lcsSubscriptionEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _lcsSubscriptionEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _lcsSubscriptionEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsSubscriptionEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsSubscriptionEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of lcs subscription entries.
	*
	* @return the number of lcs subscription entries
	*/
	@Override
	public int getLCSSubscriptionEntriesCount() {
		return _lcsSubscriptionEntryLocalService.getLCSSubscriptionEntriesCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _lcsSubscriptionEntryLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsSubscriptionEntryLocalService.getOSGiServiceIdentifier();
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
		return _lcsSubscriptionEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsSubscriptionEntryLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsSubscriptionEntryLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the lcs subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lcs subscription entries
	* @param end the upper bound of the range of lcs subscription entries (not inclusive)
	* @return the range of lcs subscription entries
	*/
	@Override
	public java.util.List<com.liferay.osb.model.LCSSubscriptionEntry> getLCSSubscriptionEntries(
		int start, int end) {
		return _lcsSubscriptionEntryLocalService.getLCSSubscriptionEntries(start,
			end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LCSSubscriptionEntry> getLCSSubscriptionEntries(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsSubscriptionEntryLocalService.getLCSSubscriptionEntries(corpProjectId);
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
		return _lcsSubscriptionEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _lcsSubscriptionEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void syncToLCS(long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsSubscriptionEntryLocalService.syncToLCS(corpProjectId);
	}

	@Override
	public LCSSubscriptionEntryLocalService getWrappedService() {
		return _lcsSubscriptionEntryLocalService;
	}

	@Override
	public void setWrappedService(
		LCSSubscriptionEntryLocalService lcsSubscriptionEntryLocalService) {
		_lcsSubscriptionEntryLocalService = lcsSubscriptionEntryLocalService;
	}

	private LCSSubscriptionEntryLocalService _lcsSubscriptionEntryLocalService;
}