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
 * This class is a wrapper for {@link TrainingLinkedUserLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingLinkedUserLocalService
 * @generated
 */
public class TrainingLinkedUserLocalServiceWrapper
	implements TrainingLinkedUserLocalService,
		ServiceWrapper<TrainingLinkedUserLocalService> {
	public TrainingLinkedUserLocalServiceWrapper(
		TrainingLinkedUserLocalService trainingLinkedUserLocalService) {
		_trainingLinkedUserLocalService = trainingLinkedUserLocalService;
	}

	/**
	* Adds the training linked user to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingLinkedUser the training linked user
	* @return the training linked user that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser addTrainingLinkedUser(
		com.liferay.osb.model.TrainingLinkedUser trainingLinkedUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUserLocalService.addTrainingLinkedUser(trainingLinkedUser);
	}

	/**
	* Creates a new training linked user with the primary key. Does not add the training linked user to the database.
	*
	* @param trainingLinkedUserId the primary key for the new training linked user
	* @return the new training linked user
	*/
	public com.liferay.osb.model.TrainingLinkedUser createTrainingLinkedUser(
		long trainingLinkedUserId) {
		return _trainingLinkedUserLocalService.createTrainingLinkedUser(trainingLinkedUserId);
	}

	/**
	* Deletes the training linked user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingLinkedUserId the primary key of the training linked user
	* @return the training linked user that was removed
	* @throws PortalException if a training linked user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser deleteTrainingLinkedUser(
		long trainingLinkedUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUserLocalService.deleteTrainingLinkedUser(trainingLinkedUserId);
	}

	/**
	* Deletes the training linked user from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingLinkedUser the training linked user
	* @return the training linked user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser deleteTrainingLinkedUser(
		com.liferay.osb.model.TrainingLinkedUser trainingLinkedUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUserLocalService.deleteTrainingLinkedUser(trainingLinkedUser);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _trainingLinkedUserLocalService.dynamicQuery();
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
		return _trainingLinkedUserLocalService.dynamicQuery(dynamicQuery);
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
		return _trainingLinkedUserLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
		return _trainingLinkedUserLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _trainingLinkedUserLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.TrainingLinkedUser fetchTrainingLinkedUser(
		long trainingLinkedUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUserLocalService.fetchTrainingLinkedUser(trainingLinkedUserId);
	}

	/**
	* Returns the training linked user with the primary key.
	*
	* @param trainingLinkedUserId the primary key of the training linked user
	* @return the training linked user
	* @throws PortalException if a training linked user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser getTrainingLinkedUser(
		long trainingLinkedUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUserLocalService.getTrainingLinkedUser(trainingLinkedUserId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUserLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the training linked users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training linked users
	* @param end the upper bound of the range of training linked users (not inclusive)
	* @return the range of training linked users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingLinkedUser> getTrainingLinkedUsers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUserLocalService.getTrainingLinkedUsers(start, end);
	}

	/**
	* Returns the number of training linked users.
	*
	* @return the number of training linked users
	* @throws SystemException if a system exception occurred
	*/
	public int getTrainingLinkedUsersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUserLocalService.getTrainingLinkedUsersCount();
	}

	/**
	* Updates the training linked user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingLinkedUser the training linked user
	* @return the training linked user that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser updateTrainingLinkedUser(
		com.liferay.osb.model.TrainingLinkedUser trainingLinkedUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUserLocalService.updateTrainingLinkedUser(trainingLinkedUser);
	}

	/**
	* Updates the training linked user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingLinkedUser the training linked user
	* @param merge whether to merge the training linked user with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the training linked user that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser updateTrainingLinkedUser(
		com.liferay.osb.model.TrainingLinkedUser trainingLinkedUser,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUserLocalService.updateTrainingLinkedUser(trainingLinkedUser,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _trainingLinkedUserLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trainingLinkedUserLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trainingLinkedUserLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public void addTrainingLinkedUsers(long primaryUserId,
		long oldPrimaryUserId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_trainingLinkedUserLocalService.addTrainingLinkedUsers(primaryUserId,
			oldPrimaryUserId, userIds);
	}

	public com.liferay.osb.model.TrainingLinkedUser fetchUserTrainingLinkedUser(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUserLocalService.fetchUserTrainingLinkedUser(userId);
	}

	public java.util.List<com.liferay.osb.model.TrainingLinkedUser> getTrainingLinkedUsers(
		long primaryUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUserLocalService.getTrainingLinkedUsers(primaryUserId);
	}

	public boolean isPrimaryTrainingLinkedUser(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUserLocalService.isPrimaryTrainingLinkedUser(userId);
	}

	public void unsetTrainingLinkedUsers(long primaryUserId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_trainingLinkedUserLocalService.unsetTrainingLinkedUsers(primaryUserId,
			userIds);
	}

	public void updateTrainingLinkedUsers(long primaryUserId,
		long oldPrimaryUserId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_trainingLinkedUserLocalService.updateTrainingLinkedUsers(primaryUserId,
			oldPrimaryUserId, userIds);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TrainingLinkedUserLocalService getWrappedTrainingLinkedUserLocalService() {
		return _trainingLinkedUserLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTrainingLinkedUserLocalService(
		TrainingLinkedUserLocalService trainingLinkedUserLocalService) {
		_trainingLinkedUserLocalService = trainingLinkedUserLocalService;
	}

	public TrainingLinkedUserLocalService getWrappedService() {
		return _trainingLinkedUserLocalService;
	}

	public void setWrappedService(
		TrainingLinkedUserLocalService trainingLinkedUserLocalService) {
		_trainingLinkedUserLocalService = trainingLinkedUserLocalService;
	}

	private TrainingLinkedUserLocalService _trainingLinkedUserLocalService;
}