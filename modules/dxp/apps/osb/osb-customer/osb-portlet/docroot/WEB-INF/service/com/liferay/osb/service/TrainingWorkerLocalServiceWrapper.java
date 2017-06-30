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
 * This class is a wrapper for {@link TrainingWorkerLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingWorkerLocalService
 * @generated
 */
public class TrainingWorkerLocalServiceWrapper
	implements TrainingWorkerLocalService,
		ServiceWrapper<TrainingWorkerLocalService> {
	public TrainingWorkerLocalServiceWrapper(
		TrainingWorkerLocalService trainingWorkerLocalService) {
		_trainingWorkerLocalService = trainingWorkerLocalService;
	}

	/**
	* Adds the training worker to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingWorker the training worker
	* @return the training worker that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker addTrainingWorker(
		com.liferay.osb.model.TrainingWorker trainingWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingWorkerLocalService.addTrainingWorker(trainingWorker);
	}

	/**
	* Creates a new training worker with the primary key. Does not add the training worker to the database.
	*
	* @param trainingWorkerId the primary key for the new training worker
	* @return the new training worker
	*/
	public com.liferay.osb.model.TrainingWorker createTrainingWorker(
		long trainingWorkerId) {
		return _trainingWorkerLocalService.createTrainingWorker(trainingWorkerId);
	}

	/**
	* Deletes the training worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingWorkerId the primary key of the training worker
	* @return the training worker that was removed
	* @throws PortalException if a training worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker deleteTrainingWorker(
		long trainingWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingWorkerLocalService.deleteTrainingWorker(trainingWorkerId);
	}

	/**
	* Deletes the training worker from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingWorker the training worker
	* @return the training worker that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker deleteTrainingWorker(
		com.liferay.osb.model.TrainingWorker trainingWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingWorkerLocalService.deleteTrainingWorker(trainingWorker);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _trainingWorkerLocalService.dynamicQuery();
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
		return _trainingWorkerLocalService.dynamicQuery(dynamicQuery);
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
		return _trainingWorkerLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _trainingWorkerLocalService.dynamicQuery(dynamicQuery, start,
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
		return _trainingWorkerLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.TrainingWorker fetchTrainingWorker(
		long trainingWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingWorkerLocalService.fetchTrainingWorker(trainingWorkerId);
	}

	/**
	* Returns the training worker with the primary key.
	*
	* @param trainingWorkerId the primary key of the training worker
	* @return the training worker
	* @throws PortalException if a training worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker getTrainingWorker(
		long trainingWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingWorkerLocalService.getTrainingWorker(trainingWorkerId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingWorkerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the training workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training workers
	* @param end the upper bound of the range of training workers (not inclusive)
	* @return the range of training workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingWorker> getTrainingWorkers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingWorkerLocalService.getTrainingWorkers(start, end);
	}

	/**
	* Returns the number of training workers.
	*
	* @return the number of training workers
	* @throws SystemException if a system exception occurred
	*/
	public int getTrainingWorkersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingWorkerLocalService.getTrainingWorkersCount();
	}

	/**
	* Updates the training worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingWorker the training worker
	* @return the training worker that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker updateTrainingWorker(
		com.liferay.osb.model.TrainingWorker trainingWorker)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingWorkerLocalService.updateTrainingWorker(trainingWorker);
	}

	/**
	* Updates the training worker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingWorker the training worker
	* @param merge whether to merge the training worker with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the training worker that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker updateTrainingWorker(
		com.liferay.osb.model.TrainingWorker trainingWorker, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingWorkerLocalService.updateTrainingWorker(trainingWorker,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _trainingWorkerLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trainingWorkerLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trainingWorkerLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.TrainingWorker addTrainingWorker(long userId,
		long classNameId, long classPK, long userProfileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingWorkerLocalService.addTrainingWorker(userId,
			classNameId, classPK, userProfileId);
	}

	public void addTrainingWorkers(long[] userIds, long classNameId,
		long classPK,
		java.util.Map<java.lang.Long, java.lang.Long> userProfileIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_trainingWorkerLocalService.addTrainingWorkers(userIds, classNameId,
			classPK, userProfileIds);
	}

	public void deleteTrainingWorkers(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_trainingWorkerLocalService.deleteTrainingWorkers(userId);
	}

	public void deleteTrainingWorkers(long[] userIds, long classNameId,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		_trainingWorkerLocalService.deleteTrainingWorkers(userIds, classNameId,
			classPK);
	}

	public com.liferay.osb.model.TrainingWorker fetchTrainingWorker(
		long userId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingWorkerLocalService.fetchTrainingWorker(userId,
			classNameId, classPK);
	}

	public java.util.List<com.liferay.osb.model.TrainingWorker> getTrainingWorkers(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingWorkerLocalService.getTrainingWorkers(classNameId,
			classPK);
	}

	public boolean hasTrainingWorker(long userId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingWorkerLocalService.hasTrainingWorker(userId,
			classNameId, classPK);
	}

	public com.liferay.osb.model.TrainingWorker updateTrainingWorker(
		long trainingWorkerId, long userId, long classNameId, long classPK,
		long userProfileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingWorkerLocalService.updateTrainingWorker(trainingWorkerId,
			userId, classNameId, classPK, userProfileId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TrainingWorkerLocalService getWrappedTrainingWorkerLocalService() {
		return _trainingWorkerLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTrainingWorkerLocalService(
		TrainingWorkerLocalService trainingWorkerLocalService) {
		_trainingWorkerLocalService = trainingWorkerLocalService;
	}

	public TrainingWorkerLocalService getWrappedService() {
		return _trainingWorkerLocalService;
	}

	public void setWrappedService(
		TrainingWorkerLocalService trainingWorkerLocalService) {
		_trainingWorkerLocalService = trainingWorkerLocalService;
	}

	private TrainingWorkerLocalService _trainingWorkerLocalService;
}