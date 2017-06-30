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
 * This class is a wrapper for {@link TrainingLocationLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingLocationLocalService
 * @generated
 */
public class TrainingLocationLocalServiceWrapper
	implements TrainingLocationLocalService,
		ServiceWrapper<TrainingLocationLocalService> {
	public TrainingLocationLocalServiceWrapper(
		TrainingLocationLocalService trainingLocationLocalService) {
		_trainingLocationLocalService = trainingLocationLocalService;
	}

	/**
	* Adds the training location to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingLocation the training location
	* @return the training location that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLocation addTrainingLocation(
		com.liferay.osb.model.TrainingLocation trainingLocation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLocationLocalService.addTrainingLocation(trainingLocation);
	}

	/**
	* Creates a new training location with the primary key. Does not add the training location to the database.
	*
	* @param trainingLocationId the primary key for the new training location
	* @return the new training location
	*/
	public com.liferay.osb.model.TrainingLocation createTrainingLocation(
		long trainingLocationId) {
		return _trainingLocationLocalService.createTrainingLocation(trainingLocationId);
	}

	/**
	* Deletes the training location with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingLocationId the primary key of the training location
	* @return the training location that was removed
	* @throws PortalException if a training location with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLocation deleteTrainingLocation(
		long trainingLocationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingLocationLocalService.deleteTrainingLocation(trainingLocationId);
	}

	/**
	* Deletes the training location from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingLocation the training location
	* @return the training location that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLocation deleteTrainingLocation(
		com.liferay.osb.model.TrainingLocation trainingLocation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLocationLocalService.deleteTrainingLocation(trainingLocation);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _trainingLocationLocalService.dynamicQuery();
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
		return _trainingLocationLocalService.dynamicQuery(dynamicQuery);
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
		return _trainingLocationLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
		return _trainingLocationLocalService.dynamicQuery(dynamicQuery, start,
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
		return _trainingLocationLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.TrainingLocation fetchTrainingLocation(
		long trainingLocationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLocationLocalService.fetchTrainingLocation(trainingLocationId);
	}

	/**
	* Returns the training location with the primary key.
	*
	* @param trainingLocationId the primary key of the training location
	* @return the training location
	* @throws PortalException if a training location with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLocation getTrainingLocation(
		long trainingLocationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingLocationLocalService.getTrainingLocation(trainingLocationId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingLocationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the training locations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training locations
	* @param end the upper bound of the range of training locations (not inclusive)
	* @return the range of training locations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingLocation> getTrainingLocations(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLocationLocalService.getTrainingLocations(start, end);
	}

	/**
	* Returns the number of training locations.
	*
	* @return the number of training locations
	* @throws SystemException if a system exception occurred
	*/
	public int getTrainingLocationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLocationLocalService.getTrainingLocationsCount();
	}

	/**
	* Updates the training location in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingLocation the training location
	* @return the training location that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLocation updateTrainingLocation(
		com.liferay.osb.model.TrainingLocation trainingLocation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLocationLocalService.updateTrainingLocation(trainingLocation);
	}

	/**
	* Updates the training location in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingLocation the training location
	* @param merge whether to merge the training location with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the training location that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLocation updateTrainingLocation(
		com.liferay.osb.model.TrainingLocation trainingLocation, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLocationLocalService.updateTrainingLocation(trainingLocation,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _trainingLocationLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trainingLocationLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trainingLocationLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.TrainingLocation addTrainingLocation(
		long userId, java.lang.String name, java.lang.String street1,
		java.lang.String street2, java.lang.String street3,
		java.lang.String city, java.lang.String zip, long regionId,
		long countryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingLocationLocalService.addTrainingLocation(userId, name,
			street1, street2, street3, city, zip, regionId, countryId);
	}

	public java.util.List<com.liferay.osb.model.TrainingLocation> getTrainingLocations(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLocationLocalService.getTrainingLocations(start, end,
			obc);
	}

	public com.liferay.osb.model.TrainingLocation updateTrainingLocation(
		long trainingLocationId, java.lang.String name,
		java.lang.String street1, java.lang.String street2,
		java.lang.String street3, java.lang.String city, java.lang.String zip,
		long regionId, long countryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingLocationLocalService.updateTrainingLocation(trainingLocationId,
			name, street1, street2, street3, city, zip, regionId, countryId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TrainingLocationLocalService getWrappedTrainingLocationLocalService() {
		return _trainingLocationLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTrainingLocationLocalService(
		TrainingLocationLocalService trainingLocationLocalService) {
		_trainingLocationLocalService = trainingLocationLocalService;
	}

	public TrainingLocationLocalService getWrappedService() {
		return _trainingLocationLocalService;
	}

	public void setWrappedService(
		TrainingLocationLocalService trainingLocationLocalService) {
		_trainingLocationLocalService = trainingLocationLocalService;
	}

	private TrainingLocationLocalService _trainingLocationLocalService;
}