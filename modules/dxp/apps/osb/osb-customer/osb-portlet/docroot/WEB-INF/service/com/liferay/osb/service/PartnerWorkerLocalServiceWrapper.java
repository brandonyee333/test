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
 * Provides a wrapper for {@link PartnerWorkerLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PartnerWorkerLocalService
 * @generated
 */
@ProviderType
public class PartnerWorkerLocalServiceWrapper
	implements PartnerWorkerLocalService,
		ServiceWrapper<PartnerWorkerLocalService> {
	public PartnerWorkerLocalServiceWrapper(
		PartnerWorkerLocalService partnerWorkerLocalService) {
		_partnerWorkerLocalService = partnerWorkerLocalService;
	}

	@Override
	public boolean hasPartnerWorker(long userId) {
		return _partnerWorkerLocalService.hasPartnerWorker(userId);
	}

	@Override
	public boolean hasPartnerWorker(long userId, long partnerEntryId) {
		return _partnerWorkerLocalService.hasPartnerWorker(userId,
			partnerEntryId);
	}

	@Override
	public boolean hasPartnerWorkerRole(long userId, int role) {
		return _partnerWorkerLocalService.hasPartnerWorkerRole(userId, role);
	}

	/**
	* Adds the partner worker to the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @return the partner worker that was added
	*/
	@Override
	public com.liferay.osb.model.PartnerWorker addPartnerWorker(
		com.liferay.osb.model.PartnerWorker partnerWorker) {
		return _partnerWorkerLocalService.addPartnerWorker(partnerWorker);
	}

	/**
	* Creates a new partner worker with the primary key. Does not add the partner worker to the database.
	*
	* @param partnerWorkerId the primary key for the new partner worker
	* @return the new partner worker
	*/
	@Override
	public com.liferay.osb.model.PartnerWorker createPartnerWorker(
		long partnerWorkerId) {
		return _partnerWorkerLocalService.createPartnerWorker(partnerWorkerId);
	}

	/**
	* Deletes the partner worker from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @return the partner worker that was removed
	*/
	@Override
	public com.liferay.osb.model.PartnerWorker deletePartnerWorker(
		com.liferay.osb.model.PartnerWorker partnerWorker) {
		return _partnerWorkerLocalService.deletePartnerWorker(partnerWorker);
	}

	/**
	* Deletes the partner worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker that was removed
	* @throws PortalException if a partner worker with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.PartnerWorker deletePartnerWorker(
		long partnerWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerWorkerLocalService.deletePartnerWorker(partnerWorkerId);
	}

	@Override
	public com.liferay.osb.model.PartnerWorker fetchPartnerWorker(
		long partnerWorkerId) {
		return _partnerWorkerLocalService.fetchPartnerWorker(partnerWorkerId);
	}

	@Override
	public com.liferay.osb.model.PartnerWorker fetchPartnerWorker(long userId,
		long partnerEntryId) {
		return _partnerWorkerLocalService.fetchPartnerWorker(userId,
			partnerEntryId);
	}

	/**
	* Returns the partner worker with the primary key.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker
	* @throws PortalException if a partner worker with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.PartnerWorker getPartnerWorker(
		long partnerWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerWorkerLocalService.getPartnerWorker(partnerWorkerId);
	}

	@Override
	public com.liferay.osb.model.PartnerWorker getPartnerWorker(long userId,
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerWorkerLocalService.getPartnerWorker(userId,
			partnerEntryId);
	}

	/**
	* Updates the partner worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @return the partner worker that was updated
	*/
	@Override
	public com.liferay.osb.model.PartnerWorker updatePartnerWorker(
		com.liferay.osb.model.PartnerWorker partnerWorker) {
		return _partnerWorkerLocalService.updatePartnerWorker(partnerWorker);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _partnerWorkerLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _partnerWorkerLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _partnerWorkerLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerWorkerLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerWorkerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of partner workers.
	*
	* @return the number of partner workers
	*/
	@Override
	public int getPartnerWorkersCount() {
		return _partnerWorkerLocalService.getPartnerWorkersCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _partnerWorkerLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _partnerWorkerLocalService.getOSGiServiceIdentifier();
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
		return _partnerWorkerLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _partnerWorkerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _partnerWorkerLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

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
	@Override
	public java.util.List<com.liferay.osb.model.PartnerWorker> getPartnerWorkers(
		int start, int end) {
		return _partnerWorkerLocalService.getPartnerWorkers(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.PartnerWorker> getPartnerWorkers(
		long partnerEntryId) {
		return _partnerWorkerLocalService.getPartnerWorkers(partnerEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.PartnerWorker> getPartnerWorkers(
		long partnerEntryId, int role) {
		return _partnerWorkerLocalService.getPartnerWorkers(partnerEntryId, role);
	}

	@Override
	public java.util.List<com.liferay.osb.model.PartnerWorker> getUserPartnerWorkers(
		long userId) {
		return _partnerWorkerLocalService.getUserPartnerWorkers(userId);
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
		return _partnerWorkerLocalService.dynamicQueryCount(dynamicQuery);
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
		return _partnerWorkerLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void addPartnerWorkers(long[] userIds, long partnerEntryId,
		int[] roles, int[] notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		_partnerWorkerLocalService.addPartnerWorkers(userIds, partnerEntryId,
			roles, notifications);
	}

	@Override
	public void deletePartnerWorkers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_partnerWorkerLocalService.deletePartnerWorkers(userId);
	}

	@Override
	public void deletePartnerWorkers(long[] userIds, long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_partnerWorkerLocalService.deletePartnerWorkers(userIds, partnerEntryId);
	}

	@Override
	public PartnerWorkerLocalService getWrappedService() {
		return _partnerWorkerLocalService;
	}

	@Override
	public void setWrappedService(
		PartnerWorkerLocalService partnerWorkerLocalService) {
		_partnerWorkerLocalService = partnerWorkerLocalService;
	}

	private PartnerWorkerLocalService _partnerWorkerLocalService;
}