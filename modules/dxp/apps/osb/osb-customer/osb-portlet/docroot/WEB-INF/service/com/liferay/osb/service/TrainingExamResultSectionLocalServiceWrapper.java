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
 * This class is a wrapper for {@link TrainingExamResultSectionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingExamResultSectionLocalService
 * @generated
 */
public class TrainingExamResultSectionLocalServiceWrapper
	implements TrainingExamResultSectionLocalService,
		ServiceWrapper<TrainingExamResultSectionLocalService> {
	public TrainingExamResultSectionLocalServiceWrapper(
		TrainingExamResultSectionLocalService trainingExamResultSectionLocalService) {
		_trainingExamResultSectionLocalService = trainingExamResultSectionLocalService;
	}

	/**
	* Adds the training exam result section to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultSection the training exam result section
	* @return the training exam result section that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection addTrainingExamResultSection(
		com.liferay.osb.model.TrainingExamResultSection trainingExamResultSection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultSectionLocalService.addTrainingExamResultSection(trainingExamResultSection);
	}

	/**
	* Creates a new training exam result section with the primary key. Does not add the training exam result section to the database.
	*
	* @param trainingExamResultSectionId the primary key for the new training exam result section
	* @return the new training exam result section
	*/
	public com.liferay.osb.model.TrainingExamResultSection createTrainingExamResultSection(
		long trainingExamResultSectionId) {
		return _trainingExamResultSectionLocalService.createTrainingExamResultSection(trainingExamResultSectionId);
	}

	/**
	* Deletes the training exam result section with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultSectionId the primary key of the training exam result section
	* @return the training exam result section that was removed
	* @throws PortalException if a training exam result section with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection deleteTrainingExamResultSection(
		long trainingExamResultSectionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultSectionLocalService.deleteTrainingExamResultSection(trainingExamResultSectionId);
	}

	/**
	* Deletes the training exam result section from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultSection the training exam result section
	* @return the training exam result section that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection deleteTrainingExamResultSection(
		com.liferay.osb.model.TrainingExamResultSection trainingExamResultSection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultSectionLocalService.deleteTrainingExamResultSection(trainingExamResultSection);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _trainingExamResultSectionLocalService.dynamicQuery();
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
		return _trainingExamResultSectionLocalService.dynamicQuery(dynamicQuery);
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
		return _trainingExamResultSectionLocalService.dynamicQuery(dynamicQuery,
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
		return _trainingExamResultSectionLocalService.dynamicQuery(dynamicQuery,
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
		return _trainingExamResultSectionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.TrainingExamResultSection fetchTrainingExamResultSection(
		long trainingExamResultSectionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultSectionLocalService.fetchTrainingExamResultSection(trainingExamResultSectionId);
	}

	/**
	* Returns the training exam result section with the primary key.
	*
	* @param trainingExamResultSectionId the primary key of the training exam result section
	* @return the training exam result section
	* @throws PortalException if a training exam result section with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection getTrainingExamResultSection(
		long trainingExamResultSectionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultSectionLocalService.getTrainingExamResultSection(trainingExamResultSectionId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultSectionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the training exam result sections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training exam result sections
	* @param end the upper bound of the range of training exam result sections (not inclusive)
	* @return the range of training exam result sections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResultSection> getTrainingExamResultSections(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultSectionLocalService.getTrainingExamResultSections(start,
			end);
	}

	/**
	* Returns the number of training exam result sections.
	*
	* @return the number of training exam result sections
	* @throws SystemException if a system exception occurred
	*/
	public int getTrainingExamResultSectionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultSectionLocalService.getTrainingExamResultSectionsCount();
	}

	/**
	* Updates the training exam result section in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultSection the training exam result section
	* @return the training exam result section that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection updateTrainingExamResultSection(
		com.liferay.osb.model.TrainingExamResultSection trainingExamResultSection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultSectionLocalService.updateTrainingExamResultSection(trainingExamResultSection);
	}

	/**
	* Updates the training exam result section in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultSection the training exam result section
	* @param merge whether to merge the training exam result section with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the training exam result section that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection updateTrainingExamResultSection(
		com.liferay.osb.model.TrainingExamResultSection trainingExamResultSection,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultSectionLocalService.updateTrainingExamResultSection(trainingExamResultSection,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _trainingExamResultSectionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trainingExamResultSectionLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trainingExamResultSectionLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.osb.model.TrainingExamResultSection addTrainingExamResultSection(
		long trainingExamResultId, java.lang.String title,
		java.lang.String sectionKey, boolean scoreIndicator,
		java.lang.String scoringAlgorithm, java.lang.String masteryScore,
		java.lang.String score, java.lang.String standardErrorOfEstimate,
		int correctCount, int incorrectCount, int skippedCount, int grade)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultSectionLocalService.addTrainingExamResultSection(trainingExamResultId,
			title, sectionKey, scoreIndicator, scoringAlgorithm, masteryScore,
			score, standardErrorOfEstimate, correctCount, incorrectCount,
			skippedCount, grade);
	}

	public java.util.List<com.liferay.osb.model.TrainingExamResultSection> getTrainingExamResultSections(
		long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultSectionLocalService.getTrainingExamResultSections(trainingExamResultId);
	}

	public java.util.List<com.liferay.osb.model.TrainingExamResultSection> search(
		long trainingExamResultId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultSectionLocalService.search(trainingExamResultId,
			params);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TrainingExamResultSectionLocalService getWrappedTrainingExamResultSectionLocalService() {
		return _trainingExamResultSectionLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTrainingExamResultSectionLocalService(
		TrainingExamResultSectionLocalService trainingExamResultSectionLocalService) {
		_trainingExamResultSectionLocalService = trainingExamResultSectionLocalService;
	}

	public TrainingExamResultSectionLocalService getWrappedService() {
		return _trainingExamResultSectionLocalService;
	}

	public void setWrappedService(
		TrainingExamResultSectionLocalService trainingExamResultSectionLocalService) {
		_trainingExamResultSectionLocalService = trainingExamResultSectionLocalService;
	}

	private TrainingExamResultSectionLocalService _trainingExamResultSectionLocalService;
}