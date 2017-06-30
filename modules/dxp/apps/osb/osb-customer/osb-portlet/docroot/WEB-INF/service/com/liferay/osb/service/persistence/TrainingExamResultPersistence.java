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

import com.liferay.osb.model.TrainingExamResult;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the training exam result service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExamResultPersistenceImpl
 * @see TrainingExamResultUtil
 * @generated
 */
public interface TrainingExamResultPersistence extends BasePersistence<TrainingExamResult> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrainingExamResultUtil} to access the training exam result persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the training exam result in the entity cache if it is enabled.
	*
	* @param trainingExamResult the training exam result
	*/
	public void cacheResult(
		com.liferay.osb.model.TrainingExamResult trainingExamResult);

	/**
	* Caches the training exam results in the entity cache if it is enabled.
	*
	* @param trainingExamResults the training exam results
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingExamResult> trainingExamResults);

	/**
	* Creates a new training exam result with the primary key. Does not add the training exam result to the database.
	*
	* @param trainingExamResultId the primary key for the new training exam result
	* @return the new training exam result
	*/
	public com.liferay.osb.model.TrainingExamResult create(
		long trainingExamResultId);

	/**
	* Removes the training exam result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultId the primary key of the training exam result
	* @return the training exam result that was removed
	* @throws com.liferay.osb.NoSuchTrainingExamResultException if a training exam result with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult remove(
		long trainingExamResultId)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TrainingExamResult updateImpl(
		com.liferay.osb.model.TrainingExamResult trainingExamResult,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam result with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingExamResultException} if it could not be found.
	*
	* @param trainingExamResultId the primary key of the training exam result
	* @return the training exam result
	* @throws com.liferay.osb.NoSuchTrainingExamResultException if a training exam result with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult findByPrimaryKey(
		long trainingExamResultId)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam result with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingExamResultId the primary key of the training exam result
	* @return the training exam result, or <code>null</code> if a training exam result with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult fetchByPrimaryKey(
		long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training exam results where createDate &gt; &#63;.
	*
	* @param createDate the create date
	* @return the matching training exam results
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResult> findByGtCreateDate(
		java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training exam results where createDate &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of training exam results
	* @param end the upper bound of the range of training exam results (not inclusive)
	* @return the range of matching training exam results
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResult> findByGtCreateDate(
		java.util.Date createDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training exam results where createDate &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of training exam results
	* @param end the upper bound of the range of training exam results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training exam results
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResult> findByGtCreateDate(
		java.util.Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training exam result in the ordered set where createDate &gt; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam result
	* @throws com.liferay.osb.NoSuchTrainingExamResultException if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult findByGtCreateDate_First(
		java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training exam result in the ordered set where createDate &gt; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam result, or <code>null</code> if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult fetchByGtCreateDate_First(
		java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training exam result in the ordered set where createDate &gt; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam result
	* @throws com.liferay.osb.NoSuchTrainingExamResultException if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult findByGtCreateDate_Last(
		java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training exam result in the ordered set where createDate &gt; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam result, or <code>null</code> if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult fetchByGtCreateDate_Last(
		java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam results before and after the current training exam result in the ordered set where createDate &gt; &#63;.
	*
	* @param trainingExamResultId the primary key of the current training exam result
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training exam result
	* @throws com.liferay.osb.NoSuchTrainingExamResultException if a training exam result with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult[] findByGtCreateDate_PrevAndNext(
		long trainingExamResultId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam result where registrationNumber = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingExamResultException} if it could not be found.
	*
	* @param registrationNumber the registration number
	* @return the matching training exam result
	* @throws com.liferay.osb.NoSuchTrainingExamResultException if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult findByRegistrationNumber(
		java.lang.String registrationNumber)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam result where registrationNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param registrationNumber the registration number
	* @return the matching training exam result, or <code>null</code> if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult fetchByRegistrationNumber(
		java.lang.String registrationNumber)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam result where registrationNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param registrationNumber the registration number
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching training exam result, or <code>null</code> if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult fetchByRegistrationNumber(
		java.lang.String registrationNumber, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training exam results where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	*
	* @param startDate the start date
	* @param grade the grade
	* @param status the status
	* @return the matching training exam results
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResult> findByGtSD_G_NotS(
		java.util.Date startDate, int grade, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training exam results where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param startDate the start date
	* @param grade the grade
	* @param status the status
	* @param start the lower bound of the range of training exam results
	* @param end the upper bound of the range of training exam results (not inclusive)
	* @return the range of matching training exam results
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResult> findByGtSD_G_NotS(
		java.util.Date startDate, int grade, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training exam results where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param startDate the start date
	* @param grade the grade
	* @param status the status
	* @param start the lower bound of the range of training exam results
	* @param end the upper bound of the range of training exam results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training exam results
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResult> findByGtSD_G_NotS(
		java.util.Date startDate, int grade, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training exam result in the ordered set where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	*
	* @param startDate the start date
	* @param grade the grade
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam result
	* @throws com.liferay.osb.NoSuchTrainingExamResultException if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult findByGtSD_G_NotS_First(
		java.util.Date startDate, int grade, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training exam result in the ordered set where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	*
	* @param startDate the start date
	* @param grade the grade
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam result, or <code>null</code> if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult fetchByGtSD_G_NotS_First(
		java.util.Date startDate, int grade, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training exam result in the ordered set where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	*
	* @param startDate the start date
	* @param grade the grade
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam result
	* @throws com.liferay.osb.NoSuchTrainingExamResultException if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult findByGtSD_G_NotS_Last(
		java.util.Date startDate, int grade, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training exam result in the ordered set where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	*
	* @param startDate the start date
	* @param grade the grade
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam result, or <code>null</code> if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult fetchByGtSD_G_NotS_Last(
		java.util.Date startDate, int grade, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam results before and after the current training exam result in the ordered set where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	*
	* @param trainingExamResultId the primary key of the current training exam result
	* @param startDate the start date
	* @param grade the grade
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training exam result
	* @throws com.liferay.osb.NoSuchTrainingExamResultException if a training exam result with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult[] findByGtSD_G_NotS_PrevAndNext(
		long trainingExamResultId, java.util.Date startDate, int grade,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training exam results.
	*
	* @return the training exam results
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResult> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training exam results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training exam results
	* @param end the upper bound of the range of training exam results (not inclusive)
	* @return the range of training exam results
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResult> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training exam results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training exam results
	* @param end the upper bound of the range of training exam results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of training exam results
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResult> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training exam results where createDate &gt; &#63; from the database.
	*
	* @param createDate the create date
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGtCreateDate(java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the training exam result where registrationNumber = &#63; from the database.
	*
	* @param registrationNumber the registration number
	* @return the training exam result that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResult removeByRegistrationNumber(
		java.lang.String registrationNumber)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training exam results where startDate &gt; &#63; and grade = &#63; and status &ne; &#63; from the database.
	*
	* @param startDate the start date
	* @param grade the grade
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGtSD_G_NotS(java.util.Date startDate, int grade,
		int status) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training exam results from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training exam results where createDate &gt; &#63;.
	*
	* @param createDate the create date
	* @return the number of matching training exam results
	* @throws SystemException if a system exception occurred
	*/
	public int countByGtCreateDate(java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training exam results where registrationNumber = &#63;.
	*
	* @param registrationNumber the registration number
	* @return the number of matching training exam results
	* @throws SystemException if a system exception occurred
	*/
	public int countByRegistrationNumber(java.lang.String registrationNumber)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training exam results where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	*
	* @param startDate the start date
	* @param grade the grade
	* @param status the status
	* @return the number of matching training exam results
	* @throws SystemException if a system exception occurred
	*/
	public int countByGtSD_G_NotS(java.util.Date startDate, int grade,
		int status) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training exam results.
	*
	* @return the number of training exam results
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}