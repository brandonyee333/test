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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the training exam result service. This utility wraps {@link TrainingExamResultPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExamResultPersistence
 * @see TrainingExamResultPersistenceImpl
 * @generated
 */
public class TrainingExamResultUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(TrainingExamResult trainingExamResult) {
		getPersistence().clearCache(trainingExamResult);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TrainingExamResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TrainingExamResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TrainingExamResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TrainingExamResult update(
		TrainingExamResult trainingExamResult, boolean merge)
		throws SystemException {
		return getPersistence().update(trainingExamResult, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TrainingExamResult update(
		TrainingExamResult trainingExamResult, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(trainingExamResult, merge, serviceContext);
	}

	/**
	* Caches the training exam result in the entity cache if it is enabled.
	*
	* @param trainingExamResult the training exam result
	*/
	public static void cacheResult(
		com.liferay.osb.model.TrainingExamResult trainingExamResult) {
		getPersistence().cacheResult(trainingExamResult);
	}

	/**
	* Caches the training exam results in the entity cache if it is enabled.
	*
	* @param trainingExamResults the training exam results
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingExamResult> trainingExamResults) {
		getPersistence().cacheResult(trainingExamResults);
	}

	/**
	* Creates a new training exam result with the primary key. Does not add the training exam result to the database.
	*
	* @param trainingExamResultId the primary key for the new training exam result
	* @return the new training exam result
	*/
	public static com.liferay.osb.model.TrainingExamResult create(
		long trainingExamResultId) {
		return getPersistence().create(trainingExamResultId);
	}

	/**
	* Removes the training exam result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultId the primary key of the training exam result
	* @return the training exam result that was removed
	* @throws com.liferay.osb.NoSuchTrainingExamResultException if a training exam result with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult remove(
		long trainingExamResultId)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(trainingExamResultId);
	}

	public static com.liferay.osb.model.TrainingExamResult updateImpl(
		com.liferay.osb.model.TrainingExamResult trainingExamResult,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(trainingExamResult, merge);
	}

	/**
	* Returns the training exam result with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingExamResultException} if it could not be found.
	*
	* @param trainingExamResultId the primary key of the training exam result
	* @return the training exam result
	* @throws com.liferay.osb.NoSuchTrainingExamResultException if a training exam result with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult findByPrimaryKey(
		long trainingExamResultId)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(trainingExamResultId);
	}

	/**
	* Returns the training exam result with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingExamResultId the primary key of the training exam result
	* @return the training exam result, or <code>null</code> if a training exam result with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult fetchByPrimaryKey(
		long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(trainingExamResultId);
	}

	/**
	* Returns all the training exam results where createDate &gt; &#63;.
	*
	* @param createDate the create date
	* @return the matching training exam results
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingExamResult> findByGtCreateDate(
		java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGtCreateDate(createDate);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingExamResult> findByGtCreateDate(
		java.util.Date createDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGtCreateDate(createDate, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingExamResult> findByGtCreateDate(
		java.util.Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtCreateDate(createDate, start, end, orderByComparator);
	}

	/**
	* Returns the first training exam result in the ordered set where createDate &gt; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam result
	* @throws com.liferay.osb.NoSuchTrainingExamResultException if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult findByGtCreateDate_First(
		java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtCreateDate_First(createDate, orderByComparator);
	}

	/**
	* Returns the first training exam result in the ordered set where createDate &gt; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam result, or <code>null</code> if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult fetchByGtCreateDate_First(
		java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGtCreateDate_First(createDate, orderByComparator);
	}

	/**
	* Returns the last training exam result in the ordered set where createDate &gt; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam result
	* @throws com.liferay.osb.NoSuchTrainingExamResultException if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult findByGtCreateDate_Last(
		java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtCreateDate_Last(createDate, orderByComparator);
	}

	/**
	* Returns the last training exam result in the ordered set where createDate &gt; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam result, or <code>null</code> if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult fetchByGtCreateDate_Last(
		java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGtCreateDate_Last(createDate, orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingExamResult[] findByGtCreateDate_PrevAndNext(
		long trainingExamResultId, java.util.Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtCreateDate_PrevAndNext(trainingExamResultId,
			createDate, orderByComparator);
	}

	/**
	* Returns the training exam result where registrationNumber = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingExamResultException} if it could not be found.
	*
	* @param registrationNumber the registration number
	* @return the matching training exam result
	* @throws com.liferay.osb.NoSuchTrainingExamResultException if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult findByRegistrationNumber(
		java.lang.String registrationNumber)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByRegistrationNumber(registrationNumber);
	}

	/**
	* Returns the training exam result where registrationNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param registrationNumber the registration number
	* @return the matching training exam result, or <code>null</code> if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult fetchByRegistrationNumber(
		java.lang.String registrationNumber)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByRegistrationNumber(registrationNumber);
	}

	/**
	* Returns the training exam result where registrationNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param registrationNumber the registration number
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching training exam result, or <code>null</code> if a matching training exam result could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult fetchByRegistrationNumber(
		java.lang.String registrationNumber, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByRegistrationNumber(registrationNumber,
			retrieveFromCache);
	}

	/**
	* Returns all the training exam results where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	*
	* @param startDate the start date
	* @param grade the grade
	* @param status the status
	* @return the matching training exam results
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingExamResult> findByGtSD_G_NotS(
		java.util.Date startDate, int grade, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGtSD_G_NotS(startDate, grade, status);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingExamResult> findByGtSD_G_NotS(
		java.util.Date startDate, int grade, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtSD_G_NotS(startDate, grade, status, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingExamResult> findByGtSD_G_NotS(
		java.util.Date startDate, int grade, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtSD_G_NotS(startDate, grade, status, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingExamResult findByGtSD_G_NotS_First(
		java.util.Date startDate, int grade, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtSD_G_NotS_First(startDate, grade, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingExamResult fetchByGtSD_G_NotS_First(
		java.util.Date startDate, int grade, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGtSD_G_NotS_First(startDate, grade, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingExamResult findByGtSD_G_NotS_Last(
		java.util.Date startDate, int grade, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtSD_G_NotS_Last(startDate, grade, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingExamResult fetchByGtSD_G_NotS_Last(
		java.util.Date startDate, int grade, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGtSD_G_NotS_Last(startDate, grade, status,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingExamResult[] findByGtSD_G_NotS_PrevAndNext(
		long trainingExamResultId, java.util.Date startDate, int grade,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtSD_G_NotS_PrevAndNext(trainingExamResultId,
			startDate, grade, status, orderByComparator);
	}

	/**
	* Returns all the training exam results.
	*
	* @return the training exam results
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingExamResult> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingExamResult> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingExamResult> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the training exam results where createDate &gt; &#63; from the database.
	*
	* @param createDate the create date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGtCreateDate(java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGtCreateDate(createDate);
	}

	/**
	* Removes the training exam result where registrationNumber = &#63; from the database.
	*
	* @param registrationNumber the registration number
	* @return the training exam result that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult removeByRegistrationNumber(
		java.lang.String registrationNumber)
		throws com.liferay.osb.NoSuchTrainingExamResultException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByRegistrationNumber(registrationNumber);
	}

	/**
	* Removes all the training exam results where startDate &gt; &#63; and grade = &#63; and status &ne; &#63; from the database.
	*
	* @param startDate the start date
	* @param grade the grade
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGtSD_G_NotS(java.util.Date startDate, int grade,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGtSD_G_NotS(startDate, grade, status);
	}

	/**
	* Removes all the training exam results from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of training exam results where createDate &gt; &#63;.
	*
	* @param createDate the create date
	* @return the number of matching training exam results
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGtCreateDate(java.util.Date createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGtCreateDate(createDate);
	}

	/**
	* Returns the number of training exam results where registrationNumber = &#63;.
	*
	* @param registrationNumber the registration number
	* @return the number of matching training exam results
	* @throws SystemException if a system exception occurred
	*/
	public static int countByRegistrationNumber(
		java.lang.String registrationNumber)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByRegistrationNumber(registrationNumber);
	}

	/**
	* Returns the number of training exam results where startDate &gt; &#63; and grade = &#63; and status &ne; &#63;.
	*
	* @param startDate the start date
	* @param grade the grade
	* @param status the status
	* @return the number of matching training exam results
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGtSD_G_NotS(java.util.Date startDate, int grade,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGtSD_G_NotS(startDate, grade, status);
	}

	/**
	* Returns the number of training exam results.
	*
	* @return the number of training exam results
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TrainingExamResultPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TrainingExamResultPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TrainingExamResultPersistence.class.getName());

			ReferenceRegistry.registerReference(TrainingExamResultUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(TrainingExamResultPersistence persistence) {
	}

	private static TrainingExamResultPersistence _persistence;
}