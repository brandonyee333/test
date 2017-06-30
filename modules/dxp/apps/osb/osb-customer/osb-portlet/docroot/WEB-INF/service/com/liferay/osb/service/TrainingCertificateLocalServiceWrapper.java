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
 * This class is a wrapper for {@link TrainingCertificateLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingCertificateLocalService
 * @generated
 */
public class TrainingCertificateLocalServiceWrapper
	implements TrainingCertificateLocalService,
		ServiceWrapper<TrainingCertificateLocalService> {
	public TrainingCertificateLocalServiceWrapper(
		TrainingCertificateLocalService trainingCertificateLocalService) {
		_trainingCertificateLocalService = trainingCertificateLocalService;
	}

	/**
	* Adds the training certificate to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificate the training certificate
	* @return the training certificate that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate addTrainingCertificate(
		com.liferay.osb.model.TrainingCertificate trainingCertificate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.addTrainingCertificate(trainingCertificate);
	}

	/**
	* Creates a new training certificate with the primary key. Does not add the training certificate to the database.
	*
	* @param trainingCertificateId the primary key for the new training certificate
	* @return the new training certificate
	*/
	public com.liferay.osb.model.TrainingCertificate createTrainingCertificate(
		long trainingCertificateId) {
		return _trainingCertificateLocalService.createTrainingCertificate(trainingCertificateId);
	}

	/**
	* Deletes the training certificate with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificateId the primary key of the training certificate
	* @return the training certificate that was removed
	* @throws PortalException if a training certificate with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate deleteTrainingCertificate(
		long trainingCertificateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.deleteTrainingCertificate(trainingCertificateId);
	}

	/**
	* Deletes the training certificate from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificate the training certificate
	* @return the training certificate that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate deleteTrainingCertificate(
		com.liferay.osb.model.TrainingCertificate trainingCertificate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.deleteTrainingCertificate(trainingCertificate);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _trainingCertificateLocalService.dynamicQuery();
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
		return _trainingCertificateLocalService.dynamicQuery(dynamicQuery);
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
		return _trainingCertificateLocalService.dynamicQuery(dynamicQuery,
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
		return _trainingCertificateLocalService.dynamicQuery(dynamicQuery,
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
		return _trainingCertificateLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.TrainingCertificate fetchTrainingCertificate(
		long trainingCertificateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.fetchTrainingCertificate(trainingCertificateId);
	}

	/**
	* Returns the training certificate with the primary key.
	*
	* @param trainingCertificateId the primary key of the training certificate
	* @return the training certificate
	* @throws PortalException if a training certificate with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate getTrainingCertificate(
		long trainingCertificateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.getTrainingCertificate(trainingCertificateId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the training certificates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training certificates
	* @param end the upper bound of the range of training certificates (not inclusive)
	* @return the range of training certificates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingCertificate> getTrainingCertificates(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.getTrainingCertificates(start,
			end);
	}

	/**
	* Returns the number of training certificates.
	*
	* @return the number of training certificates
	* @throws SystemException if a system exception occurred
	*/
	public int getTrainingCertificatesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.getTrainingCertificatesCount();
	}

	/**
	* Updates the training certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificate the training certificate
	* @return the training certificate that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate updateTrainingCertificate(
		com.liferay.osb.model.TrainingCertificate trainingCertificate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.updateTrainingCertificate(trainingCertificate);
	}

	/**
	* Updates the training certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificate the training certificate
	* @param merge whether to merge the training certificate with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the training certificate that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate updateTrainingCertificate(
		com.liferay.osb.model.TrainingCertificate trainingCertificate,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.updateTrainingCertificate(trainingCertificate,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _trainingCertificateLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trainingCertificateLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trainingCertificateLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.osb.model.TrainingCertificate addTrainingCertificate(
		long userId, long trainingCustomerId, java.lang.String key,
		int surveyStatus, long userProfileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.addTrainingCertificate(userId,
			trainingCustomerId, key, surveyStatus, userProfileId);
	}

	public void addTrainingCertificates(long userId, long[] trainingCustomerIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_trainingCertificateLocalService.addTrainingCertificates(userId,
			trainingCustomerIds);
	}

	public void deleteTrainingCustomerTrainingCertificate(
		long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_trainingCertificateLocalService.deleteTrainingCustomerTrainingCertificate(trainingCustomerId);
	}

	public com.liferay.osb.model.TrainingCertificate fetchTrainingCertificateByTrainingCustomerId(
		long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.fetchTrainingCertificateByTrainingCustomerId(trainingCustomerId);
	}

	public void getTrainingCertificate(
		javax.portlet.ResourceResponse resourceResponse, long trainingCustomerId)
		throws java.lang.Exception {
		_trainingCertificateLocalService.getTrainingCertificate(resourceResponse,
			trainingCustomerId);
	}

	public com.liferay.osb.model.TrainingCertificate getTrainingCertificate(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.getTrainingCertificate(key);
	}

	public com.liferay.osb.model.TrainingCertificate getTrainingCertificate(
		java.lang.String key, java.lang.String lastName, long classNameId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.getTrainingCertificate(key,
			lastName, classNameId);
	}

	public com.liferay.osb.model.TrainingCertificate getTrainingCertificateByTrainingCustomerId(
		long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.getTrainingCertificateByTrainingCustomerId(trainingCustomerId);
	}

	public boolean hasTrainingCertificate(long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.hasTrainingCertificate(trainingCustomerId);
	}

	public boolean hasTrainingCertificateCertifiedDate(long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.hasTrainingCertificateCertifiedDate(trainingCustomerId);
	}

	public void sendTrainingCertificate(long trainingCustomerId)
		throws java.lang.Exception {
		_trainingCertificateLocalService.sendTrainingCertificate(trainingCustomerId);
	}

	public com.liferay.osb.model.TrainingCertificate updateCertifiedDate(
		long trainingCertificateId, java.util.Date date)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.updateCertifiedDate(trainingCertificateId,
			date);
	}

	public com.liferay.osb.model.TrainingCertificate updateCertifiedDate(
		long trainingCertificateId, long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.updateCertifiedDate(trainingCertificateId,
			trainingCustomerId);
	}

	public com.liferay.osb.model.TrainingCertificate updateTrainingCertificate(
		long trainingCertificateId, java.util.Date certifiedDate,
		int surveyStatus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.updateTrainingCertificate(trainingCertificateId,
			certifiedDate, surveyStatus);
	}

	public com.liferay.osb.model.TrainingCertificate updateTrainingCertificate(
		long trainingCertificateId, long userId, java.lang.String emailAddress,
		java.lang.String firstName, java.lang.String lastName,
		java.lang.String legalEntityName, int surveyStatus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.updateTrainingCertificate(trainingCertificateId,
			userId, emailAddress, firstName, lastName, legalEntityName,
			surveyStatus);
	}

	public com.liferay.osb.model.TrainingCertificate updateUserProfileHistoryId(
		long trainingCertificateId, long userId, java.lang.String emailAddress,
		java.lang.String firstName, java.lang.String lastName,
		java.lang.String legalEntityName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateLocalService.updateUserProfileHistoryId(trainingCertificateId,
			userId, emailAddress, firstName, lastName, legalEntityName);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TrainingCertificateLocalService getWrappedTrainingCertificateLocalService() {
		return _trainingCertificateLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTrainingCertificateLocalService(
		TrainingCertificateLocalService trainingCertificateLocalService) {
		_trainingCertificateLocalService = trainingCertificateLocalService;
	}

	public TrainingCertificateLocalService getWrappedService() {
		return _trainingCertificateLocalService;
	}

	public void setWrappedService(
		TrainingCertificateLocalService trainingCertificateLocalService) {
		_trainingCertificateLocalService = trainingCertificateLocalService;
	}

	private TrainingCertificateLocalService _trainingCertificateLocalService;
}