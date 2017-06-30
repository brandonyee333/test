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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the training exam result section local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExamResultSectionLocalServiceUtil
 * @see com.liferay.osb.service.base.TrainingExamResultSectionLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TrainingExamResultSectionLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface TrainingExamResultSectionLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrainingExamResultSectionLocalServiceUtil} to access the training exam result section local service. Add custom service methods to {@link com.liferay.osb.service.impl.TrainingExamResultSectionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the training exam result section to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultSection the training exam result section
	* @return the training exam result section that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection addTrainingExamResultSection(
		com.liferay.osb.model.TrainingExamResultSection trainingExamResultSection)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new training exam result section with the primary key. Does not add the training exam result section to the database.
	*
	* @param trainingExamResultSectionId the primary key for the new training exam result section
	* @return the new training exam result section
	*/
	public com.liferay.osb.model.TrainingExamResultSection createTrainingExamResultSection(
		long trainingExamResultSectionId);

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
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the training exam result section from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultSection the training exam result section
	* @return the training exam result section that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection deleteTrainingExamResultSection(
		com.liferay.osb.model.TrainingExamResultSection trainingExamResultSection)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

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
		throws com.liferay.portal.kernel.exception.SystemException;

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
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.osb.model.TrainingExamResultSection fetchTrainingExamResultSection(
		long trainingExamResultSectionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam result section with the primary key.
	*
	* @param trainingExamResultSectionId the primary key of the training exam result section
	* @return the training exam result section
	* @throws PortalException if a training exam result section with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.osb.model.TrainingExamResultSection getTrainingExamResultSection(
		long trainingExamResultSectionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.TrainingExamResultSection> getTrainingExamResultSections(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training exam result sections.
	*
	* @return the number of training exam result sections
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTrainingExamResultSectionsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the training exam result section in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultSection the training exam result section
	* @return the training exam result section that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection updateTrainingExamResultSection(
		com.liferay.osb.model.TrainingExamResultSection trainingExamResultSection)
		throws com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	public com.liferay.osb.model.TrainingExamResultSection addTrainingExamResultSection(
		long trainingExamResultId, java.lang.String title,
		java.lang.String sectionKey, boolean scoreIndicator,
		java.lang.String scoringAlgorithm, java.lang.String masteryScore,
		java.lang.String score, java.lang.String standardErrorOfEstimate,
		int correctCount, int incorrectCount, int skippedCount, int grade)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.TrainingExamResultSection> getTrainingExamResultSections(
		long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.TrainingExamResultSection> search(
		long trainingExamResultId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException;
}