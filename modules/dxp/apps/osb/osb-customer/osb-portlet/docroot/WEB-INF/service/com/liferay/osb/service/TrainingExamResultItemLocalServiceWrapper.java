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
 * This class is a wrapper for {@link TrainingExamResultItemLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingExamResultItemLocalService
 * @generated
 */
public class TrainingExamResultItemLocalServiceWrapper
	implements TrainingExamResultItemLocalService,
		ServiceWrapper<TrainingExamResultItemLocalService> {
	public TrainingExamResultItemLocalServiceWrapper(
		TrainingExamResultItemLocalService trainingExamResultItemLocalService) {
		_trainingExamResultItemLocalService = trainingExamResultItemLocalService;
	}

	/**
	* Adds the training exam result item to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultItem the training exam result item
	* @return the training exam result item that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem addTrainingExamResultItem(
		com.liferay.osb.model.TrainingExamResultItem trainingExamResultItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultItemLocalService.addTrainingExamResultItem(trainingExamResultItem);
	}

	/**
	* Creates a new training exam result item with the primary key. Does not add the training exam result item to the database.
	*
	* @param trainingExamResultItemId the primary key for the new training exam result item
	* @return the new training exam result item
	*/
	public com.liferay.osb.model.TrainingExamResultItem createTrainingExamResultItem(
		long trainingExamResultItemId) {
		return _trainingExamResultItemLocalService.createTrainingExamResultItem(trainingExamResultItemId);
	}

	/**
	* Deletes the training exam result item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultItemId the primary key of the training exam result item
	* @return the training exam result item that was removed
	* @throws PortalException if a training exam result item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem deleteTrainingExamResultItem(
		long trainingExamResultItemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultItemLocalService.deleteTrainingExamResultItem(trainingExamResultItemId);
	}

	/**
	* Deletes the training exam result item from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultItem the training exam result item
	* @return the training exam result item that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem deleteTrainingExamResultItem(
		com.liferay.osb.model.TrainingExamResultItem trainingExamResultItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultItemLocalService.deleteTrainingExamResultItem(trainingExamResultItem);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _trainingExamResultItemLocalService.dynamicQuery();
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
		return _trainingExamResultItemLocalService.dynamicQuery(dynamicQuery);
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
		return _trainingExamResultItemLocalService.dynamicQuery(dynamicQuery,
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
		return _trainingExamResultItemLocalService.dynamicQuery(dynamicQuery,
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
		return _trainingExamResultItemLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.TrainingExamResultItem fetchTrainingExamResultItem(
		long trainingExamResultItemId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultItemLocalService.fetchTrainingExamResultItem(trainingExamResultItemId);
	}

	/**
	* Returns the training exam result item with the primary key.
	*
	* @param trainingExamResultItemId the primary key of the training exam result item
	* @return the training exam result item
	* @throws PortalException if a training exam result item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem getTrainingExamResultItem(
		long trainingExamResultItemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultItemLocalService.getTrainingExamResultItem(trainingExamResultItemId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultItemLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the training exam result items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training exam result items
	* @param end the upper bound of the range of training exam result items (not inclusive)
	* @return the range of training exam result items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResultItem> getTrainingExamResultItems(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultItemLocalService.getTrainingExamResultItems(start,
			end);
	}

	/**
	* Returns the number of training exam result items.
	*
	* @return the number of training exam result items
	* @throws SystemException if a system exception occurred
	*/
	public int getTrainingExamResultItemsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultItemLocalService.getTrainingExamResultItemsCount();
	}

	/**
	* Updates the training exam result item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultItem the training exam result item
	* @return the training exam result item that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem updateTrainingExamResultItem(
		com.liferay.osb.model.TrainingExamResultItem trainingExamResultItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultItemLocalService.updateTrainingExamResultItem(trainingExamResultItem);
	}

	/**
	* Updates the training exam result item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultItem the training exam result item
	* @param merge whether to merge the training exam result item with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the training exam result item that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem updateTrainingExamResultItem(
		com.liferay.osb.model.TrainingExamResultItem trainingExamResultItem,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultItemLocalService.updateTrainingExamResultItem(trainingExamResultItem,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _trainingExamResultItemLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trainingExamResultItemLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trainingExamResultItemLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.osb.model.TrainingExamResultItem addTrainingExamResultItem(
		long trainingExamResultId, long trainingExamResultSectionId,
		java.lang.String name, java.lang.String status, java.lang.String key,
		int distractorCount, java.lang.String type, java.lang.String response,
		java.lang.String score, int time, java.lang.String learningResources)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultItemLocalService.addTrainingExamResultItem(trainingExamResultId,
			trainingExamResultSectionId, name, status, key, distractorCount,
			type, response, score, time, learningResources);
	}

	public java.util.List<com.liferay.osb.model.TrainingExamResultItem> getTrainingExamResultItems(
		long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultItemLocalService.getTrainingExamResultItems(trainingExamResultId);
	}

	public java.util.List<com.liferay.osb.model.TrainingExamResultItem> search(
		long trainingExamResultId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultItemLocalService.search(trainingExamResultId,
			params);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TrainingExamResultItemLocalService getWrappedTrainingExamResultItemLocalService() {
		return _trainingExamResultItemLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTrainingExamResultItemLocalService(
		TrainingExamResultItemLocalService trainingExamResultItemLocalService) {
		_trainingExamResultItemLocalService = trainingExamResultItemLocalService;
	}

	public TrainingExamResultItemLocalService getWrappedService() {
		return _trainingExamResultItemLocalService;
	}

	public void setWrappedService(
		TrainingExamResultItemLocalService trainingExamResultItemLocalService) {
		_trainingExamResultItemLocalService = trainingExamResultItemLocalService;
	}

	private TrainingExamResultItemLocalService _trainingExamResultItemLocalService;
}