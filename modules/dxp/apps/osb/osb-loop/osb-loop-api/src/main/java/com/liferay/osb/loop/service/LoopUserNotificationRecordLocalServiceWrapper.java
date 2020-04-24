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

package com.liferay.osb.loop.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LoopUserNotificationRecordLocalService}.
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationRecordLocalService
 * @generated
 */
@ProviderType
public class LoopUserNotificationRecordLocalServiceWrapper
	implements LoopUserNotificationRecordLocalService,
		ServiceWrapper<LoopUserNotificationRecordLocalService> {
	public LoopUserNotificationRecordLocalServiceWrapper(
		LoopUserNotificationRecordLocalService loopUserNotificationRecordLocalService) {
		_loopUserNotificationRecordLocalService = loopUserNotificationRecordLocalService;
	}

	/**
	* Adds the loop user notification record to the database. Also notifies the appropriate model listeners.
	*
	* @param loopUserNotificationRecord the loop user notification record
	* @return the loop user notification record that was added
	*/
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationRecord addLoopUserNotificationRecord(
		com.liferay.osb.loop.model.LoopUserNotificationRecord loopUserNotificationRecord) {
		return _loopUserNotificationRecordLocalService.addLoopUserNotificationRecord(loopUserNotificationRecord);
	}

	/**
	* Creates a new loop user notification record with the primary key. Does not add the loop user notification record to the database.
	*
	* @param loopUserNotificationRecordId the primary key for the new loop user notification record
	* @return the new loop user notification record
	*/
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationRecord createLoopUserNotificationRecord(
		long loopUserNotificationRecordId) {
		return _loopUserNotificationRecordLocalService.createLoopUserNotificationRecord(loopUserNotificationRecordId);
	}

	/**
	* Deletes the loop user notification record with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopUserNotificationRecordId the primary key of the loop user notification record
	* @return the loop user notification record that was removed
	* @throws PortalException if a loop user notification record with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationRecord deleteLoopUserNotificationRecord(
		long loopUserNotificationRecordId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopUserNotificationRecordLocalService.deleteLoopUserNotificationRecord(loopUserNotificationRecordId);
	}

	/**
	* Deletes the loop user notification record from the database. Also notifies the appropriate model listeners.
	*
	* @param loopUserNotificationRecord the loop user notification record
	* @return the loop user notification record that was removed
	*/
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationRecord deleteLoopUserNotificationRecord(
		com.liferay.osb.loop.model.LoopUserNotificationRecord loopUserNotificationRecord) {
		return _loopUserNotificationRecordLocalService.deleteLoopUserNotificationRecord(loopUserNotificationRecord);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopUserNotificationRecordLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loopUserNotificationRecordLocalService.dynamicQuery();
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
		return _loopUserNotificationRecordLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopUserNotificationRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _loopUserNotificationRecordLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopUserNotificationRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _loopUserNotificationRecordLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _loopUserNotificationRecordLocalService.dynamicQueryCount(dynamicQuery);
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
		return _loopUserNotificationRecordLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationRecord fetchLoopUserNotificationRecord(
		long loopUserNotificationRecordId) {
		return _loopUserNotificationRecordLocalService.fetchLoopUserNotificationRecord(loopUserNotificationRecordId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _loopUserNotificationRecordLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _loopUserNotificationRecordLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the loop user notification record with the primary key.
	*
	* @param loopUserNotificationRecordId the primary key of the loop user notification record
	* @return the loop user notification record
	* @throws PortalException if a loop user notification record with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationRecord getLoopUserNotificationRecord(
		long loopUserNotificationRecordId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopUserNotificationRecordLocalService.getLoopUserNotificationRecord(loopUserNotificationRecordId);
	}

	/**
	* Returns a range of all the loop user notification records.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopUserNotificationRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop user notification records
	* @param end the upper bound of the range of loop user notification records (not inclusive)
	* @return the range of loop user notification records
	*/
	@Override
	public java.util.List<com.liferay.osb.loop.model.LoopUserNotificationRecord> getLoopUserNotificationRecords(
		int start, int end) {
		return _loopUserNotificationRecordLocalService.getLoopUserNotificationRecords(start,
			end);
	}

	/**
	* Returns the number of loop user notification records.
	*
	* @return the number of loop user notification records
	*/
	@Override
	public int getLoopUserNotificationRecordsCount() {
		return _loopUserNotificationRecordLocalService.getLoopUserNotificationRecordsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _loopUserNotificationRecordLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopUserNotificationRecordLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the loop user notification record in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param loopUserNotificationRecord the loop user notification record
	* @return the loop user notification record that was updated
	*/
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationRecord updateLoopUserNotificationRecord(
		com.liferay.osb.loop.model.LoopUserNotificationRecord loopUserNotificationRecord) {
		return _loopUserNotificationRecordLocalService.updateLoopUserNotificationRecord(loopUserNotificationRecord);
	}

	@Override
	public LoopUserNotificationRecordLocalService getWrappedService() {
		return _loopUserNotificationRecordLocalService;
	}

	@Override
	public void setWrappedService(
		LoopUserNotificationRecordLocalService loopUserNotificationRecordLocalService) {
		_loopUserNotificationRecordLocalService = loopUserNotificationRecordLocalService;
	}

	private LoopUserNotificationRecordLocalService _loopUserNotificationRecordLocalService;
}