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

import com.liferay.osb.loop.model.LoopUserNotificationRecord;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for LoopUserNotificationRecord. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationRecordLocalServiceUtil
 * @see com.liferay.osb.loop.service.base.LoopUserNotificationRecordLocalServiceBaseImpl
 * @see com.liferay.osb.loop.service.impl.LoopUserNotificationRecordLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LoopUserNotificationRecordLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopUserNotificationRecordLocalServiceUtil} to access the loop user notification record local service. Add custom service methods to {@link com.liferay.osb.loop.service.impl.LoopUserNotificationRecordLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the loop user notification record to the database. Also notifies the appropriate model listeners.
	*
	* @param loopUserNotificationRecord the loop user notification record
	* @return the loop user notification record that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LoopUserNotificationRecord addLoopUserNotificationRecord(
		LoopUserNotificationRecord loopUserNotificationRecord);

	/**
	* Creates a new loop user notification record with the primary key. Does not add the loop user notification record to the database.
	*
	* @param loopUserNotificationRecordId the primary key for the new loop user notification record
	* @return the new loop user notification record
	*/
	@Transactional(enabled = false)
	public LoopUserNotificationRecord createLoopUserNotificationRecord(
		long loopUserNotificationRecordId);

	/**
	* Deletes the loop user notification record with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopUserNotificationRecordId the primary key of the loop user notification record
	* @return the loop user notification record that was removed
	* @throws PortalException if a loop user notification record with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public LoopUserNotificationRecord deleteLoopUserNotificationRecord(
		long loopUserNotificationRecordId) throws PortalException;

	/**
	* Deletes the loop user notification record from the database. Also notifies the appropriate model listeners.
	*
	* @param loopUserNotificationRecord the loop user notification record
	* @return the loop user notification record that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public LoopUserNotificationRecord deleteLoopUserNotificationRecord(
		LoopUserNotificationRecord loopUserNotificationRecord);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LoopUserNotificationRecord fetchLoopUserNotificationRecord(
		long loopUserNotificationRecordId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the loop user notification record with the primary key.
	*
	* @param loopUserNotificationRecordId the primary key of the loop user notification record
	* @return the loop user notification record
	* @throws PortalException if a loop user notification record with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LoopUserNotificationRecord getLoopUserNotificationRecord(
		long loopUserNotificationRecordId) throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LoopUserNotificationRecord> getLoopUserNotificationRecords(
		int start, int end);

	/**
	* Returns the number of loop user notification records.
	*
	* @return the number of loop user notification records
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLoopUserNotificationRecordsCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Updates the loop user notification record in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param loopUserNotificationRecord the loop user notification record
	* @return the loop user notification record that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LoopUserNotificationRecord updateLoopUserNotificationRecord(
		LoopUserNotificationRecord loopUserNotificationRecord);
}