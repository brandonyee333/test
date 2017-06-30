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
 * This class is a wrapper for {@link TrainingCertificateTemplateLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingCertificateTemplateLocalService
 * @generated
 */
public class TrainingCertificateTemplateLocalServiceWrapper
	implements TrainingCertificateTemplateLocalService,
		ServiceWrapper<TrainingCertificateTemplateLocalService> {
	public TrainingCertificateTemplateLocalServiceWrapper(
		TrainingCertificateTemplateLocalService trainingCertificateTemplateLocalService) {
		_trainingCertificateTemplateLocalService = trainingCertificateTemplateLocalService;
	}

	/**
	* Adds the training certificate template to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificateTemplate the training certificate template
	* @return the training certificate template that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificateTemplate addTrainingCertificateTemplate(
		com.liferay.osb.model.TrainingCertificateTemplate trainingCertificateTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplateLocalService.addTrainingCertificateTemplate(trainingCertificateTemplate);
	}

	/**
	* Creates a new training certificate template with the primary key. Does not add the training certificate template to the database.
	*
	* @param trainingCertificateTemplateId the primary key for the new training certificate template
	* @return the new training certificate template
	*/
	public com.liferay.osb.model.TrainingCertificateTemplate createTrainingCertificateTemplate(
		long trainingCertificateTemplateId) {
		return _trainingCertificateTemplateLocalService.createTrainingCertificateTemplate(trainingCertificateTemplateId);
	}

	/**
	* Deletes the training certificate template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificateTemplateId the primary key of the training certificate template
	* @return the training certificate template that was removed
	* @throws PortalException if a training certificate template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificateTemplate deleteTrainingCertificateTemplate(
		long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplateLocalService.deleteTrainingCertificateTemplate(trainingCertificateTemplateId);
	}

	/**
	* Deletes the training certificate template from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificateTemplate the training certificate template
	* @return the training certificate template that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificateTemplate deleteTrainingCertificateTemplate(
		com.liferay.osb.model.TrainingCertificateTemplate trainingCertificateTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplateLocalService.deleteTrainingCertificateTemplate(trainingCertificateTemplate);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _trainingCertificateTemplateLocalService.dynamicQuery();
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
		return _trainingCertificateTemplateLocalService.dynamicQuery(dynamicQuery);
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
		return _trainingCertificateTemplateLocalService.dynamicQuery(dynamicQuery,
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
		return _trainingCertificateTemplateLocalService.dynamicQuery(dynamicQuery,
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
		return _trainingCertificateTemplateLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.TrainingCertificateTemplate fetchTrainingCertificateTemplate(
		long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplateLocalService.fetchTrainingCertificateTemplate(trainingCertificateTemplateId);
	}

	/**
	* Returns the training certificate template with the primary key.
	*
	* @param trainingCertificateTemplateId the primary key of the training certificate template
	* @return the training certificate template
	* @throws PortalException if a training certificate template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificateTemplate getTrainingCertificateTemplate(
		long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplateLocalService.getTrainingCertificateTemplate(trainingCertificateTemplateId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplateLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the training certificate templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training certificate templates
	* @param end the upper bound of the range of training certificate templates (not inclusive)
	* @return the range of training certificate templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingCertificateTemplate> getTrainingCertificateTemplates(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplateLocalService.getTrainingCertificateTemplates(start,
			end);
	}

	/**
	* Returns the number of training certificate templates.
	*
	* @return the number of training certificate templates
	* @throws SystemException if a system exception occurred
	*/
	public int getTrainingCertificateTemplatesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplateLocalService.getTrainingCertificateTemplatesCount();
	}

	/**
	* Updates the training certificate template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificateTemplate the training certificate template
	* @return the training certificate template that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificateTemplate updateTrainingCertificateTemplate(
		com.liferay.osb.model.TrainingCertificateTemplate trainingCertificateTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplateLocalService.updateTrainingCertificateTemplate(trainingCertificateTemplate);
	}

	/**
	* Updates the training certificate template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificateTemplate the training certificate template
	* @param merge whether to merge the training certificate template with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the training certificate template that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificateTemplate updateTrainingCertificateTemplate(
		com.liferay.osb.model.TrainingCertificateTemplate trainingCertificateTemplate,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplateLocalService.updateTrainingCertificateTemplate(trainingCertificateTemplate,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _trainingCertificateTemplateLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trainingCertificateTemplateLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trainingCertificateTemplateLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.osb.model.TrainingCertificateTemplate addTrainingCertificateTemplate(
		long userId, java.lang.String name, java.lang.String description,
		int type, java.lang.String templateFileName,
		java.io.InputStream templateInputStream,
		java.lang.String badgeFileName, java.io.InputStream badgeInputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplateLocalService.addTrainingCertificateTemplate(userId,
			name, description, type, templateFileName, templateInputStream,
			badgeFileName, badgeInputStream);
	}

	public java.io.InputStream getTrainingCertificateTemplateAsStream(
		long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplateLocalService.getTrainingCertificateTemplateAsStream(trainingCertificateTemplateId);
	}

	public java.util.List<com.liferay.osb.model.TrainingCertificateTemplate> getTrainingCertificateTemplates(
		int type) throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplateLocalService.getTrainingCertificateTemplates(type);
	}

	public java.util.List<com.liferay.osb.model.TrainingCertificateTemplate> getTrainingCertificateTemplates(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplateLocalService.getTrainingCertificateTemplates(start,
			end, obc);
	}

	public boolean hasCompletedTrainingCertificateTemplate(long userId,
		long classNameId, long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplateLocalService.hasCompletedTrainingCertificateTemplate(userId,
			classNameId, trainingCertificateTemplateId);
	}

	public com.liferay.osb.model.TrainingCertificateTemplate updateTrainingCertificateTemplate(
		long companyId, long trainingCertificateTemplateId,
		java.lang.String name, java.lang.String description, int type,
		java.lang.String templateFileName,
		java.io.InputStream templateInputStream,
		java.lang.String badgeFileName, java.io.InputStream badgeInputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplateLocalService.updateTrainingCertificateTemplate(companyId,
			trainingCertificateTemplateId, name, description, type,
			templateFileName, templateInputStream, badgeFileName,
			badgeInputStream);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TrainingCertificateTemplateLocalService getWrappedTrainingCertificateTemplateLocalService() {
		return _trainingCertificateTemplateLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTrainingCertificateTemplateLocalService(
		TrainingCertificateTemplateLocalService trainingCertificateTemplateLocalService) {
		_trainingCertificateTemplateLocalService = trainingCertificateTemplateLocalService;
	}

	public TrainingCertificateTemplateLocalService getWrappedService() {
		return _trainingCertificateTemplateLocalService;
	}

	public void setWrappedService(
		TrainingCertificateTemplateLocalService trainingCertificateTemplateLocalService) {
		_trainingCertificateTemplateLocalService = trainingCertificateTemplateLocalService;
	}

	private TrainingCertificateTemplateLocalService _trainingCertificateTemplateLocalService;
}