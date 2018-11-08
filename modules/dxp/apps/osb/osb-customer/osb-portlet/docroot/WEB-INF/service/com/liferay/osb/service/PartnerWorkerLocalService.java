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

import com.liferay.osb.model.PartnerWorker;

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

import java.util.List;

/**
 * Provides the local service interface for PartnerWorker. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see PartnerWorkerLocalServiceUtil
 * @see com.liferay.osb.service.base.PartnerWorkerLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.PartnerWorkerLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface PartnerWorkerLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PartnerWorkerLocalServiceUtil} to access the partner worker local service. Add custom service methods to {@link com.liferay.osb.service.impl.PartnerWorkerLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasPartnerWorker(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasPartnerWorker(long userId, long partnerEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasPartnerWorkerRole(long userId, int role);

	/**
	* Adds the partner worker to the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @return the partner worker that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public PartnerWorker addPartnerWorker(PartnerWorker partnerWorker);

	public PartnerWorker addPartnerWorker(long partnerEntryId,
		java.lang.String emailAddress, int role, int notifications)
		throws PortalException;

	public PartnerWorker addPartnerWorker(long partnerEntryId, long userId,
		int role, int notifications) throws PortalException;

	/**
	* Creates a new partner worker with the primary key. Does not add the partner worker to the database.
	*
	* @param partnerWorkerId the primary key for the new partner worker
	* @return the new partner worker
	*/
	public PartnerWorker createPartnerWorker(long partnerWorkerId);

	/**
	* Deletes the partner worker from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @return the partner worker that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public PartnerWorker deletePartnerWorker(PartnerWorker partnerWorker);

	/**
	* Deletes the partner worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker that was removed
	* @throws PortalException if a partner worker with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public PartnerWorker deletePartnerWorker(long partnerWorkerId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PartnerWorker fetchPartnerWorker(long partnerWorkerId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PartnerWorker fetchPartnerWorker(long userId, long partnerEntryId);

	/**
	* Returns the partner worker with the primary key.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker
	* @throws PortalException if a partner worker with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PartnerWorker getPartnerWorker(long partnerWorkerId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PartnerWorker getPartnerWorker(long userId, long partnerEntryId)
		throws PortalException;

	/**
	* Updates the partner worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @return the partner worker that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public PartnerWorker updatePartnerWorker(PartnerWorker partnerWorker);

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

	/**
	* Returns the number of partner workers.
	*
	* @return the number of partner workers
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPartnerWorkersCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the partner workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.PartnerWorkerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of partner workers
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PartnerWorker> getPartnerWorkers(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PartnerWorker> getPartnerWorkers(long partnerEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PartnerWorker> getPartnerWorkers(long partnerEntryId, int role);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PartnerWorker> getUserPartnerWorkers(long userId);

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

	public void deletePartnerWorkers(long userId) throws PortalException;

	public void syncPartnerWorkers(long partnerEntryId,
		java.lang.String oldDossieraAccountKey,
		java.lang.String newDossieraAccountKey) throws PortalException;

	public void updatePartnerWorker(long partnerWorkerId, int role,
		int notifications) throws PortalException;
}