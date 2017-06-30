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

package com.liferay.osb.service.persistence;

import com.liferay.osb.model.TrainingExam;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the training exam service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExamPersistenceImpl
 * @see TrainingExamUtil
 * @generated
 */
public interface TrainingExamPersistence extends BasePersistence<TrainingExam> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrainingExamUtil} to access the training exam persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the training exam in the entity cache if it is enabled.
	*
	* @param trainingExam the training exam
	*/
	public void cacheResult(com.liferay.osb.model.TrainingExam trainingExam);

	/**
	* Caches the training exams in the entity cache if it is enabled.
	*
	* @param trainingExams the training exams
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingExam> trainingExams);

	/**
	* Creates a new training exam with the primary key. Does not add the training exam to the database.
	*
	* @param trainingExamId the primary key for the new training exam
	* @return the new training exam
	*/
	public com.liferay.osb.model.TrainingExam create(long trainingExamId);

	/**
	* Removes the training exam with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamId the primary key of the training exam
	* @return the training exam that was removed
	* @throws com.liferay.osb.NoSuchTrainingExamException if a training exam with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExam remove(long trainingExamId)
		throws com.liferay.osb.NoSuchTrainingExamException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TrainingExam updateImpl(
		com.liferay.osb.model.TrainingExam trainingExam, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingExamException} if it could not be found.
	*
	* @param trainingExamId the primary key of the training exam
	* @return the training exam
	* @throws com.liferay.osb.NoSuchTrainingExamException if a training exam with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExam findByPrimaryKey(
		long trainingExamId)
		throws com.liferay.osb.NoSuchTrainingExamException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingExamId the primary key of the training exam
	* @return the training exam, or <code>null</code> if a training exam with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExam fetchByPrimaryKey(
		long trainingExamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training exams where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @return the matching training exams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExam> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training exams where trainingCertificateTemplateId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param start the lower bound of the range of training exams
	* @param end the upper bound of the range of training exams (not inclusive)
	* @return the range of matching training exams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExam> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training exams where trainingCertificateTemplateId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param start the lower bound of the range of training exams
	* @param end the upper bound of the range of training exams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training exams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExam> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training exam in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam
	* @throws com.liferay.osb.NoSuchTrainingExamException if a matching training exam could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExam findByTrainingCertificateTemplateId_First(
		long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training exam in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam, or <code>null</code> if a matching training exam could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExam fetchByTrainingCertificateTemplateId_First(
		long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training exam in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam
	* @throws com.liferay.osb.NoSuchTrainingExamException if a matching training exam could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExam findByTrainingCertificateTemplateId_Last(
		long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training exam in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam, or <code>null</code> if a matching training exam could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExam fetchByTrainingCertificateTemplateId_Last(
		long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exams before and after the current training exam in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingExamId the primary key of the current training exam
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training exam
	* @throws com.liferay.osb.NoSuchTrainingExamException if a training exam with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExam[] findByTrainingCertificateTemplateId_PrevAndNext(
		long trainingExamId, long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training exams where name = &#63;.
	*
	* @param name the name
	* @return the matching training exams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExam> findByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training exams where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of training exams
	* @param end the upper bound of the range of training exams (not inclusive)
	* @return the range of matching training exams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExam> findByName(
		java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training exams where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of training exams
	* @param end the upper bound of the range of training exams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training exams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExam> findByName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training exam in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam
	* @throws com.liferay.osb.NoSuchTrainingExamException if a matching training exam could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExam findByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training exam in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam, or <code>null</code> if a matching training exam could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExam fetchByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training exam in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam
	* @throws com.liferay.osb.NoSuchTrainingExamException if a matching training exam could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExam findByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training exam in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam, or <code>null</code> if a matching training exam could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExam fetchByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exams before and after the current training exam in the ordered set where name = &#63;.
	*
	* @param trainingExamId the primary key of the current training exam
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training exam
	* @throws com.liferay.osb.NoSuchTrainingExamException if a training exam with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExam[] findByName_PrevAndNext(
		long trainingExamId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training exams.
	*
	* @return the training exams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExam> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training exams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training exams
	* @param end the upper bound of the range of training exams (not inclusive)
	* @return the range of training exams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExam> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training exams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training exams
	* @param end the upper bound of the range of training exams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of training exams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExam> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training exams where trainingCertificateTemplateId = &#63; from the database.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training exams where name = &#63; from the database.
	*
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training exams from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training exams where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @return the number of matching training exams
	* @throws SystemException if a system exception occurred
	*/
	public int countByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training exams where name = &#63;.
	*
	* @param name the name
	* @return the number of matching training exams
	* @throws SystemException if a system exception occurred
	*/
	public int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training exams.
	*
	* @return the number of training exams
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}