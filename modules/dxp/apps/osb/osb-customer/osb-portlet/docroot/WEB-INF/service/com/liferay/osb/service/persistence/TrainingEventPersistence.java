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

import com.liferay.osb.model.TrainingEvent;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the training event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingEventPersistenceImpl
 * @see TrainingEventUtil
 * @generated
 */
public interface TrainingEventPersistence extends BasePersistence<TrainingEvent> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrainingEventUtil} to access the training event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the training event in the entity cache if it is enabled.
	*
	* @param trainingEvent the training event
	*/
	public void cacheResult(com.liferay.osb.model.TrainingEvent trainingEvent);

	/**
	* Caches the training events in the entity cache if it is enabled.
	*
	* @param trainingEvents the training events
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingEvent> trainingEvents);

	/**
	* Creates a new training event with the primary key. Does not add the training event to the database.
	*
	* @param trainingEventId the primary key for the new training event
	* @return the new training event
	*/
	public com.liferay.osb.model.TrainingEvent create(long trainingEventId);

	/**
	* Removes the training event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingEventId the primary key of the training event
	* @return the training event that was removed
	* @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent remove(long trainingEventId)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TrainingEvent updateImpl(
		com.liferay.osb.model.TrainingEvent trainingEvent, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training event with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingEventException} if it could not be found.
	*
	* @param trainingEventId the primary key of the training event
	* @return the training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent findByPrimaryKey(
		long trainingEventId)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training event with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingEventId the primary key of the training event
	* @return the training event, or <code>null</code> if a training event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent fetchByPrimaryKey(
		long trainingEventId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training event where DDLRecordSetId = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingEventException} if it could not be found.
	*
	* @param DDLRecordSetId the d d l record set ID
	* @return the matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent findByDDLRecordSetId(
		long DDLRecordSetId)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training event where DDLRecordSetId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param DDLRecordSetId the d d l record set ID
	* @return the matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent fetchByDDLRecordSetId(
		long DDLRecordSetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training event where DDLRecordSetId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param DDLRecordSetId the d d l record set ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent fetchByDDLRecordSetId(
		long DDLRecordSetId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training events where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @return the matching training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training events where trainingCertificateTemplateId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param start the lower bound of the range of training events
	* @param end the upper bound of the range of training events (not inclusive)
	* @return the range of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training events where trainingCertificateTemplateId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param start the lower bound of the range of training events
	* @param end the upper bound of the range of training events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training event in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent findByTrainingCertificateTemplateId_First(
		long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training event in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent fetchByTrainingCertificateTemplateId_First(
		long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training event in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent findByTrainingCertificateTemplateId_Last(
		long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training event in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent fetchByTrainingCertificateTemplateId_Last(
		long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training events before and after the current training event in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingEventId the primary key of the current training event
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent[] findByTrainingCertificateTemplateId_PrevAndNext(
		long trainingEventId, long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training events where trainingCourseId = &#63;.
	*
	* @param trainingCourseId the training course ID
	* @return the matching training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingCourseId(
		long trainingCourseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training events where trainingCourseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param trainingCourseId the training course ID
	* @param start the lower bound of the range of training events
	* @param end the upper bound of the range of training events (not inclusive)
	* @return the range of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingCourseId(
		long trainingCourseId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training events where trainingCourseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param trainingCourseId the training course ID
	* @param start the lower bound of the range of training events
	* @param end the upper bound of the range of training events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingCourseId(
		long trainingCourseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training event in the ordered set where trainingCourseId = &#63;.
	*
	* @param trainingCourseId the training course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent findByTrainingCourseId_First(
		long trainingCourseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training event in the ordered set where trainingCourseId = &#63;.
	*
	* @param trainingCourseId the training course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent fetchByTrainingCourseId_First(
		long trainingCourseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training event in the ordered set where trainingCourseId = &#63;.
	*
	* @param trainingCourseId the training course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent findByTrainingCourseId_Last(
		long trainingCourseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training event in the ordered set where trainingCourseId = &#63;.
	*
	* @param trainingCourseId the training course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent fetchByTrainingCourseId_Last(
		long trainingCourseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training events before and after the current training event in the ordered set where trainingCourseId = &#63;.
	*
	* @param trainingEventId the primary key of the current training event
	* @param trainingCourseId the training course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent[] findByTrainingCourseId_PrevAndNext(
		long trainingEventId, long trainingCourseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training events where trainingLocationId = &#63;.
	*
	* @param trainingLocationId the training location ID
	* @return the matching training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingLocationId(
		long trainingLocationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training events where trainingLocationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param trainingLocationId the training location ID
	* @param start the lower bound of the range of training events
	* @param end the upper bound of the range of training events (not inclusive)
	* @return the range of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingLocationId(
		long trainingLocationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training events where trainingLocationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param trainingLocationId the training location ID
	* @param start the lower bound of the range of training events
	* @param end the upper bound of the range of training events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingLocationId(
		long trainingLocationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training event in the ordered set where trainingLocationId = &#63;.
	*
	* @param trainingLocationId the training location ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent findByTrainingLocationId_First(
		long trainingLocationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training event in the ordered set where trainingLocationId = &#63;.
	*
	* @param trainingLocationId the training location ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent fetchByTrainingLocationId_First(
		long trainingLocationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training event in the ordered set where trainingLocationId = &#63;.
	*
	* @param trainingLocationId the training location ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent findByTrainingLocationId_Last(
		long trainingLocationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training event in the ordered set where trainingLocationId = &#63;.
	*
	* @param trainingLocationId the training location ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent fetchByTrainingLocationId_Last(
		long trainingLocationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training events before and after the current training event in the ordered set where trainingLocationId = &#63;.
	*
	* @param trainingEventId the primary key of the current training event
	* @param trainingLocationId the training location ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent[] findByTrainingLocationId_PrevAndNext(
		long trainingEventId, long trainingLocationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training events where type = &#63; and startDate &gt; &#63;.
	*
	* @param type the type
	* @param startDate the start date
	* @return the matching training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findByT_SD(
		int type, java.util.Date startDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training events where type = &#63; and startDate &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param startDate the start date
	* @param start the lower bound of the range of training events
	* @param end the upper bound of the range of training events (not inclusive)
	* @return the range of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findByT_SD(
		int type, java.util.Date startDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training events where type = &#63; and startDate &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param startDate the start date
	* @param start the lower bound of the range of training events
	* @param end the upper bound of the range of training events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findByT_SD(
		int type, java.util.Date startDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training event in the ordered set where type = &#63; and startDate &gt; &#63;.
	*
	* @param type the type
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent findByT_SD_First(int type,
		java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training event in the ordered set where type = &#63; and startDate &gt; &#63;.
	*
	* @param type the type
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent fetchByT_SD_First(int type,
		java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training event in the ordered set where type = &#63; and startDate &gt; &#63;.
	*
	* @param type the type
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent findByT_SD_Last(int type,
		java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training event in the ordered set where type = &#63; and startDate &gt; &#63;.
	*
	* @param type the type
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent fetchByT_SD_Last(int type,
		java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training events before and after the current training event in the ordered set where type = &#63; and startDate &gt; &#63;.
	*
	* @param trainingEventId the primary key of the current training event
	* @param type the type
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent[] findByT_SD_PrevAndNext(
		long trainingEventId, int type, java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training events where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	*
	* @param trainingCourseId the training course ID
	* @param type the type
	* @param startDate the start date
	* @return the matching training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findByTC_T_SD(
		long trainingCourseId, int type, java.util.Date startDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training events where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param trainingCourseId the training course ID
	* @param type the type
	* @param startDate the start date
	* @param start the lower bound of the range of training events
	* @param end the upper bound of the range of training events (not inclusive)
	* @return the range of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findByTC_T_SD(
		long trainingCourseId, int type, java.util.Date startDate, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training events where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param trainingCourseId the training course ID
	* @param type the type
	* @param startDate the start date
	* @param start the lower bound of the range of training events
	* @param end the upper bound of the range of training events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findByTC_T_SD(
		long trainingCourseId, int type, java.util.Date startDate, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training event in the ordered set where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	*
	* @param trainingCourseId the training course ID
	* @param type the type
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent findByTC_T_SD_First(
		long trainingCourseId, int type, java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training event in the ordered set where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	*
	* @param trainingCourseId the training course ID
	* @param type the type
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent fetchByTC_T_SD_First(
		long trainingCourseId, int type, java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training event in the ordered set where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	*
	* @param trainingCourseId the training course ID
	* @param type the type
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent findByTC_T_SD_Last(
		long trainingCourseId, int type, java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training event in the ordered set where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	*
	* @param trainingCourseId the training course ID
	* @param type the type
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent fetchByTC_T_SD_Last(
		long trainingCourseId, int type, java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training events before and after the current training event in the ordered set where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	*
	* @param trainingEventId the primary key of the current training event
	* @param trainingCourseId the training course ID
	* @param type the type
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent[] findByTC_T_SD_PrevAndNext(
		long trainingEventId, long trainingCourseId, int type,
		java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training events.
	*
	* @return the training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training events
	* @param end the upper bound of the range of training events (not inclusive)
	* @return the range of training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training events
	* @param end the upper bound of the range of training events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of training events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingEvent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the training event where DDLRecordSetId = &#63; from the database.
	*
	* @param DDLRecordSetId the d d l record set ID
	* @return the training event that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingEvent removeByDDLRecordSetId(
		long DDLRecordSetId)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training events where trainingCertificateTemplateId = &#63; from the database.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training events where trainingCourseId = &#63; from the database.
	*
	* @param trainingCourseId the training course ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTrainingCourseId(long trainingCourseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training events where trainingLocationId = &#63; from the database.
	*
	* @param trainingLocationId the training location ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTrainingLocationId(long trainingLocationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training events where type = &#63; and startDate &gt; &#63; from the database.
	*
	* @param type the type
	* @param startDate the start date
	* @throws SystemException if a system exception occurred
	*/
	public void removeByT_SD(int type, java.util.Date startDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training events where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63; from the database.
	*
	* @param trainingCourseId the training course ID
	* @param type the type
	* @param startDate the start date
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTC_T_SD(long trainingCourseId, int type,
		java.util.Date startDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training events from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training events where DDLRecordSetId = &#63;.
	*
	* @param DDLRecordSetId the d d l record set ID
	* @return the number of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public int countByDDLRecordSetId(long DDLRecordSetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training events where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @return the number of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public int countByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training events where trainingCourseId = &#63;.
	*
	* @param trainingCourseId the training course ID
	* @return the number of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public int countByTrainingCourseId(long trainingCourseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training events where trainingLocationId = &#63;.
	*
	* @param trainingLocationId the training location ID
	* @return the number of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public int countByTrainingLocationId(long trainingLocationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training events where type = &#63; and startDate &gt; &#63;.
	*
	* @param type the type
	* @param startDate the start date
	* @return the number of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public int countByT_SD(int type, java.util.Date startDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training events where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	*
	* @param trainingCourseId the training course ID
	* @param type the type
	* @param startDate the start date
	* @return the number of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public int countByTC_T_SD(long trainingCourseId, int type,
		java.util.Date startDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training events.
	*
	* @return the number of training events
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}