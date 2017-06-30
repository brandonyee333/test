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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the training event service. This utility wraps {@link TrainingEventPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingEventPersistence
 * @see TrainingEventPersistenceImpl
 * @generated
 */
public class TrainingEventUtil {
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
	public static void clearCache(TrainingEvent trainingEvent) {
		getPersistence().clearCache(trainingEvent);
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
	public static List<TrainingEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TrainingEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TrainingEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TrainingEvent update(TrainingEvent trainingEvent,
		boolean merge) throws SystemException {
		return getPersistence().update(trainingEvent, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TrainingEvent update(TrainingEvent trainingEvent,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(trainingEvent, merge, serviceContext);
	}

	/**
	* Caches the training event in the entity cache if it is enabled.
	*
	* @param trainingEvent the training event
	*/
	public static void cacheResult(
		com.liferay.osb.model.TrainingEvent trainingEvent) {
		getPersistence().cacheResult(trainingEvent);
	}

	/**
	* Caches the training events in the entity cache if it is enabled.
	*
	* @param trainingEvents the training events
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingEvent> trainingEvents) {
		getPersistence().cacheResult(trainingEvents);
	}

	/**
	* Creates a new training event with the primary key. Does not add the training event to the database.
	*
	* @param trainingEventId the primary key for the new training event
	* @return the new training event
	*/
	public static com.liferay.osb.model.TrainingEvent create(
		long trainingEventId) {
		return getPersistence().create(trainingEventId);
	}

	/**
	* Removes the training event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingEventId the primary key of the training event
	* @return the training event that was removed
	* @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent remove(
		long trainingEventId)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(trainingEventId);
	}

	public static com.liferay.osb.model.TrainingEvent updateImpl(
		com.liferay.osb.model.TrainingEvent trainingEvent, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(trainingEvent, merge);
	}

	/**
	* Returns the training event with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingEventException} if it could not be found.
	*
	* @param trainingEventId the primary key of the training event
	* @return the training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent findByPrimaryKey(
		long trainingEventId)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(trainingEventId);
	}

	/**
	* Returns the training event with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingEventId the primary key of the training event
	* @return the training event, or <code>null</code> if a training event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent fetchByPrimaryKey(
		long trainingEventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(trainingEventId);
	}

	/**
	* Returns the training event where DDLRecordSetId = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingEventException} if it could not be found.
	*
	* @param DDLRecordSetId the d d l record set ID
	* @return the matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent findByDDLRecordSetId(
		long DDLRecordSetId)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByDDLRecordSetId(DDLRecordSetId);
	}

	/**
	* Returns the training event where DDLRecordSetId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param DDLRecordSetId the d d l record set ID
	* @return the matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent fetchByDDLRecordSetId(
		long DDLRecordSetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByDDLRecordSetId(DDLRecordSetId);
	}

	/**
	* Returns the training event where DDLRecordSetId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param DDLRecordSetId the d d l record set ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent fetchByDDLRecordSetId(
		long DDLRecordSetId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByDDLRecordSetId(DDLRecordSetId, retrieveFromCache);
	}

	/**
	* Returns all the training events where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @return the matching training events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCertificateTemplateId(trainingCertificateTemplateId);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCertificateTemplateId(trainingCertificateTemplateId,
			start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCertificateTemplateId(trainingCertificateTemplateId,
			start, end, orderByComparator);
	}

	/**
	* Returns the first training event in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent findByTrainingCertificateTemplateId_First(
		long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCertificateTemplateId_First(trainingCertificateTemplateId,
			orderByComparator);
	}

	/**
	* Returns the first training event in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent fetchByTrainingCertificateTemplateId_First(
		long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTrainingCertificateTemplateId_First(trainingCertificateTemplateId,
			orderByComparator);
	}

	/**
	* Returns the last training event in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent findByTrainingCertificateTemplateId_Last(
		long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCertificateTemplateId_Last(trainingCertificateTemplateId,
			orderByComparator);
	}

	/**
	* Returns the last training event in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent fetchByTrainingCertificateTemplateId_Last(
		long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTrainingCertificateTemplateId_Last(trainingCertificateTemplateId,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingEvent[] findByTrainingCertificateTemplateId_PrevAndNext(
		long trainingEventId, long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCertificateTemplateId_PrevAndNext(trainingEventId,
			trainingCertificateTemplateId, orderByComparator);
	}

	/**
	* Returns all the training events where trainingCourseId = &#63;.
	*
	* @param trainingCourseId the training course ID
	* @return the matching training events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingCourseId(
		long trainingCourseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTrainingCourseId(trainingCourseId);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingCourseId(
		long trainingCourseId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCourseId(trainingCourseId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingCourseId(
		long trainingCourseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCourseId(trainingCourseId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first training event in the ordered set where trainingCourseId = &#63;.
	*
	* @param trainingCourseId the training course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent findByTrainingCourseId_First(
		long trainingCourseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCourseId_First(trainingCourseId,
			orderByComparator);
	}

	/**
	* Returns the first training event in the ordered set where trainingCourseId = &#63;.
	*
	* @param trainingCourseId the training course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent fetchByTrainingCourseId_First(
		long trainingCourseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTrainingCourseId_First(trainingCourseId,
			orderByComparator);
	}

	/**
	* Returns the last training event in the ordered set where trainingCourseId = &#63;.
	*
	* @param trainingCourseId the training course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent findByTrainingCourseId_Last(
		long trainingCourseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCourseId_Last(trainingCourseId,
			orderByComparator);
	}

	/**
	* Returns the last training event in the ordered set where trainingCourseId = &#63;.
	*
	* @param trainingCourseId the training course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent fetchByTrainingCourseId_Last(
		long trainingCourseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTrainingCourseId_Last(trainingCourseId,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingEvent[] findByTrainingCourseId_PrevAndNext(
		long trainingEventId, long trainingCourseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCourseId_PrevAndNext(trainingEventId,
			trainingCourseId, orderByComparator);
	}

	/**
	* Returns all the training events where trainingLocationId = &#63;.
	*
	* @param trainingLocationId the training location ID
	* @return the matching training events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingLocationId(
		long trainingLocationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTrainingLocationId(trainingLocationId);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingLocationId(
		long trainingLocationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingLocationId(trainingLocationId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByTrainingLocationId(
		long trainingLocationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingLocationId(trainingLocationId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first training event in the ordered set where trainingLocationId = &#63;.
	*
	* @param trainingLocationId the training location ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent findByTrainingLocationId_First(
		long trainingLocationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingLocationId_First(trainingLocationId,
			orderByComparator);
	}

	/**
	* Returns the first training event in the ordered set where trainingLocationId = &#63;.
	*
	* @param trainingLocationId the training location ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent fetchByTrainingLocationId_First(
		long trainingLocationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTrainingLocationId_First(trainingLocationId,
			orderByComparator);
	}

	/**
	* Returns the last training event in the ordered set where trainingLocationId = &#63;.
	*
	* @param trainingLocationId the training location ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event
	* @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent findByTrainingLocationId_Last(
		long trainingLocationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingLocationId_Last(trainingLocationId,
			orderByComparator);
	}

	/**
	* Returns the last training event in the ordered set where trainingLocationId = &#63;.
	*
	* @param trainingLocationId the training location ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent fetchByTrainingLocationId_Last(
		long trainingLocationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTrainingLocationId_Last(trainingLocationId,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingEvent[] findByTrainingLocationId_PrevAndNext(
		long trainingEventId, long trainingLocationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingLocationId_PrevAndNext(trainingEventId,
			trainingLocationId, orderByComparator);
	}

	/**
	* Returns all the training events where type = &#63; and startDate &gt; &#63;.
	*
	* @param type the type
	* @param startDate the start date
	* @return the matching training events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByT_SD(
		int type, java.util.Date startDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_SD(type, startDate);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByT_SD(
		int type, java.util.Date startDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_SD(type, startDate, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByT_SD(
		int type, java.util.Date startDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_SD(type, startDate, start, end, orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingEvent findByT_SD_First(
		int type, java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_SD_First(type, startDate, orderByComparator);
	}

	/**
	* Returns the first training event in the ordered set where type = &#63; and startDate &gt; &#63;.
	*
	* @param type the type
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent fetchByT_SD_First(
		int type, java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByT_SD_First(type, startDate, orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingEvent findByT_SD_Last(
		int type, java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_SD_Last(type, startDate, orderByComparator);
	}

	/**
	* Returns the last training event in the ordered set where type = &#63; and startDate &gt; &#63;.
	*
	* @param type the type
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training event, or <code>null</code> if a matching training event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent fetchByT_SD_Last(
		int type, java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByT_SD_Last(type, startDate, orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingEvent[] findByT_SD_PrevAndNext(
		long trainingEventId, int type, java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_SD_PrevAndNext(trainingEventId, type, startDate,
			orderByComparator);
	}

	/**
	* Returns all the training events where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	*
	* @param trainingCourseId the training course ID
	* @param type the type
	* @param startDate the start date
	* @return the matching training events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByTC_T_SD(
		long trainingCourseId, int type, java.util.Date startDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTC_T_SD(trainingCourseId, type, startDate);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByTC_T_SD(
		long trainingCourseId, int type, java.util.Date startDate, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTC_T_SD(trainingCourseId, type, startDate, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findByTC_T_SD(
		long trainingCourseId, int type, java.util.Date startDate, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTC_T_SD(trainingCourseId, type, startDate, start,
			end, orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingEvent findByTC_T_SD_First(
		long trainingCourseId, int type, java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTC_T_SD_First(trainingCourseId, type, startDate,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingEvent fetchByTC_T_SD_First(
		long trainingCourseId, int type, java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTC_T_SD_First(trainingCourseId, type, startDate,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingEvent findByTC_T_SD_Last(
		long trainingCourseId, int type, java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTC_T_SD_Last(trainingCourseId, type, startDate,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingEvent fetchByTC_T_SD_Last(
		long trainingCourseId, int type, java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTC_T_SD_Last(trainingCourseId, type, startDate,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingEvent[] findByTC_T_SD_PrevAndNext(
		long trainingEventId, long trainingCourseId, int type,
		java.util.Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTC_T_SD_PrevAndNext(trainingEventId,
			trainingCourseId, type, startDate, orderByComparator);
	}

	/**
	* Returns all the training events.
	*
	* @return the training events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingEvent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the training event where DDLRecordSetId = &#63; from the database.
	*
	* @param DDLRecordSetId the d d l record set ID
	* @return the training event that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent removeByDDLRecordSetId(
		long DDLRecordSetId)
		throws com.liferay.osb.NoSuchTrainingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByDDLRecordSetId(DDLRecordSetId);
	}

	/**
	* Removes all the training events where trainingCertificateTemplateId = &#63; from the database.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByTrainingCertificateTemplateId(trainingCertificateTemplateId);
	}

	/**
	* Removes all the training events where trainingCourseId = &#63; from the database.
	*
	* @param trainingCourseId the training course ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTrainingCourseId(long trainingCourseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTrainingCourseId(trainingCourseId);
	}

	/**
	* Removes all the training events where trainingLocationId = &#63; from the database.
	*
	* @param trainingLocationId the training location ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTrainingLocationId(long trainingLocationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTrainingLocationId(trainingLocationId);
	}

	/**
	* Removes all the training events where type = &#63; and startDate &gt; &#63; from the database.
	*
	* @param type the type
	* @param startDate the start date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByT_SD(int type, java.util.Date startDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByT_SD(type, startDate);
	}

	/**
	* Removes all the training events where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63; from the database.
	*
	* @param trainingCourseId the training course ID
	* @param type the type
	* @param startDate the start date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTC_T_SD(long trainingCourseId, int type,
		java.util.Date startDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTC_T_SD(trainingCourseId, type, startDate);
	}

	/**
	* Removes all the training events from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of training events where DDLRecordSetId = &#63;.
	*
	* @param DDLRecordSetId the d d l record set ID
	* @return the number of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public static int countByDDLRecordSetId(long DDLRecordSetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByDDLRecordSetId(DDLRecordSetId);
	}

	/**
	* Returns the number of training events where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @return the number of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByTrainingCertificateTemplateId(trainingCertificateTemplateId);
	}

	/**
	* Returns the number of training events where trainingCourseId = &#63;.
	*
	* @param trainingCourseId the training course ID
	* @return the number of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTrainingCourseId(long trainingCourseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTrainingCourseId(trainingCourseId);
	}

	/**
	* Returns the number of training events where trainingLocationId = &#63;.
	*
	* @param trainingLocationId the training location ID
	* @return the number of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTrainingLocationId(long trainingLocationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTrainingLocationId(trainingLocationId);
	}

	/**
	* Returns the number of training events where type = &#63; and startDate &gt; &#63;.
	*
	* @param type the type
	* @param startDate the start date
	* @return the number of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public static int countByT_SD(int type, java.util.Date startDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByT_SD(type, startDate);
	}

	/**
	* Returns the number of training events where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	*
	* @param trainingCourseId the training course ID
	* @param type the type
	* @param startDate the start date
	* @return the number of matching training events
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTC_T_SD(long trainingCourseId, int type,
		java.util.Date startDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTC_T_SD(trainingCourseId, type, startDate);
	}

	/**
	* Returns the number of training events.
	*
	* @return the number of training events
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TrainingEventPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TrainingEventPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TrainingEventPersistence.class.getName());

			ReferenceRegistry.registerReference(TrainingEventUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(TrainingEventPersistence persistence) {
	}

	private static TrainingEventPersistence _persistence;
}