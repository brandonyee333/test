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
 * This class is a wrapper for {@link PartnerWorkerLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PartnerWorkerLocalService
 * @generated
 */
public class PartnerWorkerLocalServiceWrapper
	implements PartnerWorkerLocalService,
		ServiceWrapper<PartnerWorkerLocalService> {
	public PartnerWorkerLocalServiceWrapper(
		PartnerWorkerLocalService partnerWorkerLocalService) {
		_partnerWorkerLocalService = partnerWorkerLocalService;
	}

	/**
	* Adds the partner worker to the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @return the partner worker that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker addPartnerWorker(
		com.liferay.osb.model.PartnerWorker partnerWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.addPartnerWorker(partnerWorker);
	}

	/**
	* Creates a new partner worker with the primary key. Does not add the partner worker to the database.
	*
	* @param partnerWorkerId the primary key for the new partner worker
	* @return the new partner worker
	*/
	public com.liferay.osb.model.PartnerWorker createPartnerWorker(
		long partnerWorkerId) {
		return _partnerWorkerLocalService.createPartnerWorker(partnerWorkerId);
	}

	/**
	* Deletes the partner worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker that was removed
	* @throws PortalException if a partner worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker deletePartnerWorker(
		long partnerWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.deletePartnerWorker(partnerWorkerId);
	}

	/**
	* Deletes the partner worker from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @return the partner worker that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker deletePartnerWorker(
		com.liferay.osb.model.PartnerWorker partnerWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.deletePartnerWorker(partnerWorker);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _partnerWorkerLocalService.dynamicQuery();
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
		return _partnerWorkerLocalService.dynamicQuery(dynamicQuery);
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
		return _partnerWorkerLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _partnerWorkerLocalService.dynamicQuery(dynamicQuery, start,
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
		return _partnerWorkerLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.PartnerWorker fetchPartnerWorker(
		long partnerWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.fetchPartnerWorker(partnerWorkerId);
	}

	/**
	* Returns the partner worker with the primary key.
	*
	* @param partnerWorkerId the primary key of the partner worker
	* @return the partner worker
	* @throws PortalException if a partner worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker getPartnerWorker(
		long partnerWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.getPartnerWorker(partnerWorkerId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the partner workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of partner workers
	* @param end the upper bound of the range of partner workers (not inclusive)
	* @return the range of partner workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerWorker> getPartnerWorkers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.getPartnerWorkers(start, end);
	}

	/**
	* Returns the number of partner workers.
	*
	* @return the number of partner workers
	* @throws SystemException if a system exception occurred
	*/
	public int getPartnerWorkersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.getPartnerWorkersCount();
	}

	/**
	* Updates the partner worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @return the partner worker that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker updatePartnerWorker(
		com.liferay.osb.model.PartnerWorker partnerWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.updatePartnerWorker(partnerWorker);
	}

	/**
	* Updates the partner worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param partnerWorker the partner worker
	* @param merge whether to merge the partner worker with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the partner worker that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerWorker updatePartnerWorker(
		com.liferay.osb.model.PartnerWorker partnerWorker, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.updatePartnerWorker(partnerWorker,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _partnerWorkerLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_partnerWorkerLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _partnerWorkerLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void addPartnerWorkers(long[] userIds, long partnerEntryId,
		int[] roles, int[] notifications)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_partnerWorkerLocalService.addPartnerWorkers(userIds, partnerEntryId,
			roles, notifications);
	}

	public void deletePartnerWorkers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_partnerWorkerLocalService.deletePartnerWorkers(userId);
	}

	public void deletePartnerWorkers(long[] userIds, long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_partnerWorkerLocalService.deletePartnerWorkers(userIds, partnerEntryId);
	}

	public com.liferay.osb.model.PartnerWorker fetchPartnerWorker(long userId,
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.fetchPartnerWorker(userId,
			partnerEntryId);
	}

	public com.liferay.osb.model.PartnerWorker getPartnerWorker(long userId,
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.getPartnerWorker(userId,
			partnerEntryId);
	}

	public java.util.List<com.liferay.osb.model.PartnerWorker> getPartnerWorkers(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.getPartnerWorkers(partnerEntryId);
	}

	public java.util.List<com.liferay.osb.model.PartnerWorker> getPartnerWorkers(
		long partnerEntryId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.getPartnerWorkers(partnerEntryId, role);
	}

	public java.util.List<com.liferay.osb.model.PartnerWorker> getUserPartnerWorkers(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.getUserPartnerWorkers(userId);
	}

	public boolean hasPartnerWorker(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.hasPartnerWorker(userId);
	}

	public boolean hasPartnerWorker(long userId, long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.hasPartnerWorker(userId,
			partnerEntryId);
	}

	public boolean hasPartnerWorkerRole(long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorkerLocalService.hasPartnerWorkerRole(userId, role);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PartnerWorkerLocalService getWrappedPartnerWorkerLocalService() {
		return _partnerWorkerLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPartnerWorkerLocalService(
		PartnerWorkerLocalService partnerWorkerLocalService) {
		_partnerWorkerLocalService = partnerWorkerLocalService;
	}

	public PartnerWorkerLocalService getWrappedService() {
		return _partnerWorkerLocalService;
	}

	public void setWrappedService(
		PartnerWorkerLocalService partnerWorkerLocalService) {
		_partnerWorkerLocalService = partnerWorkerLocalService;
	}

	private PartnerWorkerLocalService _partnerWorkerLocalService;
}