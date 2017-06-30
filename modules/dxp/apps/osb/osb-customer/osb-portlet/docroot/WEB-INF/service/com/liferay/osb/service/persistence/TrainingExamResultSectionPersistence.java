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

import com.liferay.osb.model.TrainingExamResultSection;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the training exam result section service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExamResultSectionPersistenceImpl
 * @see TrainingExamResultSectionUtil
 * @generated
 */
public interface TrainingExamResultSectionPersistence extends BasePersistence<TrainingExamResultSection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrainingExamResultSectionUtil} to access the training exam result section persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the training exam result section in the entity cache if it is enabled.
	*
	* @param trainingExamResultSection the training exam result section
	*/
	public void cacheResult(
		com.liferay.osb.model.TrainingExamResultSection trainingExamResultSection);

	/**
	* Caches the training exam result sections in the entity cache if it is enabled.
	*
	* @param trainingExamResultSections the training exam result sections
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingExamResultSection> trainingExamResultSections);

	/**
	* Creates a new training exam result section with the primary key. Does not add the training exam result section to the database.
	*
	* @param trainingExamResultSectionId the primary key for the new training exam result section
	* @return the new training exam result section
	*/
	public com.liferay.osb.model.TrainingExamResultSection create(
		long trainingExamResultSectionId);

	/**
	* Removes the training exam result section with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultSectionId the primary key of the training exam result section
	* @return the training exam result section that was removed
	* @throws com.liferay.osb.NoSuchTrainingExamResultSectionException if a training exam result section with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection remove(
		long trainingExamResultSectionId)
		throws com.liferay.osb.NoSuchTrainingExamResultSectionException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TrainingExamResultSection updateImpl(
		com.liferay.osb.model.TrainingExamResultSection trainingExamResultSection,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam result section with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingExamResultSectionException} if it could not be found.
	*
	* @param trainingExamResultSectionId the primary key of the training exam result section
	* @return the training exam result section
	* @throws com.liferay.osb.NoSuchTrainingExamResultSectionException if a training exam result section with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection findByPrimaryKey(
		long trainingExamResultSectionId)
		throws com.liferay.osb.NoSuchTrainingExamResultSectionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam result section with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingExamResultSectionId the primary key of the training exam result section
	* @return the training exam result section, or <code>null</code> if a training exam result section with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection fetchByPrimaryKey(
		long trainingExamResultSectionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training exam result sections where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @return the matching training exam result sections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResultSection> findByTrainingExamResultId(
		long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training exam result sections where trainingExamResultId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param trainingExamResultId the training exam result ID
	* @param start the lower bound of the range of training exam result sections
	* @param end the upper bound of the range of training exam result sections (not inclusive)
	* @return the range of matching training exam result sections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResultSection> findByTrainingExamResultId(
		long trainingExamResultId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training exam result sections where trainingExamResultId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param trainingExamResultId the training exam result ID
	* @param start the lower bound of the range of training exam result sections
	* @param end the upper bound of the range of training exam result sections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training exam result sections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResultSection> findByTrainingExamResultId(
		long trainingExamResultId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training exam result section in the ordered set where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam result section
	* @throws com.liferay.osb.NoSuchTrainingExamResultSectionException if a matching training exam result section could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection findByTrainingExamResultId_First(
		long trainingExamResultId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultSectionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training exam result section in the ordered set where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam result section, or <code>null</code> if a matching training exam result section could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection fetchByTrainingExamResultId_First(
		long trainingExamResultId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training exam result section in the ordered set where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam result section
	* @throws com.liferay.osb.NoSuchTrainingExamResultSectionException if a matching training exam result section could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection findByTrainingExamResultId_Last(
		long trainingExamResultId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultSectionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training exam result section in the ordered set where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam result section, or <code>null</code> if a matching training exam result section could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection fetchByTrainingExamResultId_Last(
		long trainingExamResultId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam result sections before and after the current training exam result section in the ordered set where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultSectionId the primary key of the current training exam result section
	* @param trainingExamResultId the training exam result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training exam result section
	* @throws com.liferay.osb.NoSuchTrainingExamResultSectionException if a training exam result section with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultSection[] findByTrainingExamResultId_PrevAndNext(
		long trainingExamResultSectionId, long trainingExamResultId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultSectionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training exam result sections.
	*
	* @return the training exam result sections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResultSection> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.TrainingExamResultSection> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training exam result sections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training exam result sections
	* @param end the upper bound of the range of training exam result sections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of training exam result sections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResultSection> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training exam result sections where trainingExamResultId = &#63; from the database.
	*
	* @param trainingExamResultId the training exam result ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTrainingExamResultId(long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training exam result sections from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training exam result sections where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @return the number of matching training exam result sections
	* @throws SystemException if a system exception occurred
	*/
	public int countByTrainingExamResultId(long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training exam result sections.
	*
	* @return the number of training exam result sections
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}