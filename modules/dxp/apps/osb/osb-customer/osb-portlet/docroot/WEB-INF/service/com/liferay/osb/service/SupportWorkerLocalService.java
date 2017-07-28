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

import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.model.TicketEntry;

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
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for SupportWorker. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerLocalServiceUtil
 * @see com.liferay.osb.service.base.SupportWorkerLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.SupportWorkerLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SupportWorkerLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportWorkerLocalServiceUtil} to access the support worker local service. Add custom service methods to {@link com.liferay.osb.service.impl.SupportWorkerLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasSupportWorker(long userId, int notRole);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasSupportWorker(long userId, int role,
		long locationSupportRegionId, java.lang.Integer supportTeamType);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasSupportWorker(long userId, long supportTeamId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasSupportWorkerRole(long userId, int role);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isClockedIn(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isManagerOfWorker(long userId, long workerUserId)
		throws PortalException;

	/**
	* Adds the support worker to the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorker the support worker
	* @return the support worker that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SupportWorker addSupportWorker(SupportWorker supportWorker);

	/**
	* Creates a new support worker with the primary key. Does not add the support worker to the database.
	*
	* @param supportWorkerId the primary key for the new support worker
	* @return the new support worker
	*/
	public SupportWorker createSupportWorker(long supportWorkerId);

	/**
	* Deletes the support worker from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorker the support worker
	* @return the support worker that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public SupportWorker deleteSupportWorker(SupportWorker supportWorker);

	/**
	* Deletes the support worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerId the primary key of the support worker
	* @return the support worker that was removed
	* @throws PortalException if a support worker with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public SupportWorker deleteSupportWorker(long supportWorkerId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SupportWorker fetchSupportWorker(long supportWorkerId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SupportWorker getAvailableSupportWorker(TicketEntry ticketEntry)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SupportWorker getLongestOpenSupportWorker(
		List<SupportWorker> supportWorkers, TicketEntry ticketEntry)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SupportWorker getMostAvailableSupportWorker(
		TicketEntry ticketEntry,
		LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SupportWorker getNextOpenSupportWorker(
		List<SupportWorker> supportWorkers, TicketEntry ticketEntry)
		throws PortalException;

	/**
	* Returns the support worker with the primary key.
	*
	* @param supportWorkerId the primary key of the support worker
	* @return the support worker
	* @throws PortalException if a support worker with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SupportWorker getSupportWorker(long supportWorkerId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SupportWorker getSupportWorker(long userId, long supportTeamId)
		throws PortalException;

	/**
	* Updates the support worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportWorker the support worker
	* @return the support worker that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SupportWorker updateSupportWorker(SupportWorker supportWorker);

	public SupportWorker updateSupportWorker(long supportWorkerId,
		long supportTeamId, boolean autoAssign, double maxWork,
		int escalationlevel, int escalationLevel2Role, int notifications)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public double getAssignedWork(long userId);

	/**
	* Returns the number of support workers.
	*
	* @return the number of support workers
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSupportWorkersCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSupportWorkersCountBySupportLaborId(long supportLaborId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long supportLaborId, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String screenName, java.lang.String emailAddress,
		java.lang.String supportTeamName, boolean andSearch);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long supportLaborId, java.lang.String keywords);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the support workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support workers
	* @param end the upper bound of the range of support workers (not inclusive)
	* @return the range of support workers
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportWorker> getSupportWorkers(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportWorker> getSupportWorkersBySupportLaborId(
		long supportLaborId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportWorker> getSupportWorkersBySupportRegionId(
		long supportRegionId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportWorker> getTeamSupportWorkers(long supportTeamId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportWorker> getUserSupportTeamManagers(long userId,
		java.lang.Integer supportTeamType) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportWorker> getUserSupportWorkers(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportWorker> search(java.lang.Boolean overUtilization,
		int escalationLevel,
		LinkedHashMap<java.lang.String, java.lang.Object> params);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportWorker> search(long supportLaborId,
		java.lang.String firstName, java.lang.String middleName,
		java.lang.String lastName, java.lang.String screenName,
		java.lang.String emailAddress, java.lang.String supportTeamName,
		boolean andSearch, int start, int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportWorker> search(long supportLaborId,
		java.lang.String keywords, int start, int end, OrderByComparator obc);

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

	public void addSupportWorkers(long[] userIds, long supportTeamId,
		double[] maxWork, int[] escalationLevels, int[] roles,
		int[] notifications) throws PortalException;

	public void clockInOut(long supportWorkerId) throws PortalException;

	public void decreaseAssignedWork(long userId, double work);

	public void decreaseTicketEntryAssignedWork(long ticketEntryId, double work);

	public void deleteSupportWorkers(long userId) throws PortalException;

	public void deleteSupportWorkers(long[] userIds, long supportTeamId)
		throws PortalException;

	public void increaseAssignedWork(long userId, double work);

	public void increaseTicketEntryAssignedWork(long ticketEntryId, double work);

	public void recalculateUtilization();
}