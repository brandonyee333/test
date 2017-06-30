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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the training exam service. This utility wraps {@link TrainingExamPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExamPersistence
 * @see TrainingExamPersistenceImpl
 * @generated
 */
public class TrainingExamUtil {
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
	public static void clearCache(TrainingExam trainingExam) {
		getPersistence().clearCache(trainingExam);
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
	public static List<TrainingExam> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TrainingExam> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TrainingExam> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TrainingExam update(TrainingExam trainingExam, boolean merge)
		throws SystemException {
		return getPersistence().update(trainingExam, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TrainingExam update(TrainingExam trainingExam, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(trainingExam, merge, serviceContext);
	}

	/**
	* Caches the training exam in the entity cache if it is enabled.
	*
	* @param trainingExam the training exam
	*/
	public static void cacheResult(
		com.liferay.osb.model.TrainingExam trainingExam) {
		getPersistence().cacheResult(trainingExam);
	}

	/**
	* Caches the training exams in the entity cache if it is enabled.
	*
	* @param trainingExams the training exams
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingExam> trainingExams) {
		getPersistence().cacheResult(trainingExams);
	}

	/**
	* Creates a new training exam with the primary key. Does not add the training exam to the database.
	*
	* @param trainingExamId the primary key for the new training exam
	* @return the new training exam
	*/
	public static com.liferay.osb.model.TrainingExam create(long trainingExamId) {
		return getPersistence().create(trainingExamId);
	}

	/**
	* Removes the training exam with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamId the primary key of the training exam
	* @return the training exam that was removed
	* @throws com.liferay.osb.NoSuchTrainingExamException if a training exam with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam remove(long trainingExamId)
		throws com.liferay.osb.NoSuchTrainingExamException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(trainingExamId);
	}

	public static com.liferay.osb.model.TrainingExam updateImpl(
		com.liferay.osb.model.TrainingExam trainingExam, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(trainingExam, merge);
	}

	/**
	* Returns the training exam with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingExamException} if it could not be found.
	*
	* @param trainingExamId the primary key of the training exam
	* @return the training exam
	* @throws com.liferay.osb.NoSuchTrainingExamException if a training exam with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam findByPrimaryKey(
		long trainingExamId)
		throws com.liferay.osb.NoSuchTrainingExamException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(trainingExamId);
	}

	/**
	* Returns the training exam with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingExamId the primary key of the training exam
	* @return the training exam, or <code>null</code> if a training exam with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam fetchByPrimaryKey(
		long trainingExamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(trainingExamId);
	}

	/**
	* Returns all the training exams where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @return the matching training exams
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingExam> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCertificateTemplateId(trainingCertificateTemplateId);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingExam> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCertificateTemplateId(trainingCertificateTemplateId,
			start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingExam> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCertificateTemplateId(trainingCertificateTemplateId,
			start, end, orderByComparator);
	}

	/**
	* Returns the first training exam in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam
	* @throws com.liferay.osb.NoSuchTrainingExamException if a matching training exam could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam findByTrainingCertificateTemplateId_First(
		long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCertificateTemplateId_First(trainingCertificateTemplateId,
			orderByComparator);
	}

	/**
	* Returns the first training exam in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam, or <code>null</code> if a matching training exam could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam fetchByTrainingCertificateTemplateId_First(
		long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTrainingCertificateTemplateId_First(trainingCertificateTemplateId,
			orderByComparator);
	}

	/**
	* Returns the last training exam in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam
	* @throws com.liferay.osb.NoSuchTrainingExamException if a matching training exam could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam findByTrainingCertificateTemplateId_Last(
		long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCertificateTemplateId_Last(trainingCertificateTemplateId,
			orderByComparator);
	}

	/**
	* Returns the last training exam in the ordered set where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam, or <code>null</code> if a matching training exam could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam fetchByTrainingCertificateTemplateId_Last(
		long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTrainingCertificateTemplateId_Last(trainingCertificateTemplateId,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingExam[] findByTrainingCertificateTemplateId_PrevAndNext(
		long trainingExamId, long trainingCertificateTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingCertificateTemplateId_PrevAndNext(trainingExamId,
			trainingCertificateTemplateId, orderByComparator);
	}

	/**
	* Returns all the training exams where name = &#63;.
	*
	* @param name the name
	* @return the matching training exams
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingExam> findByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingExam> findByName(
		java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingExam> findByName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name, start, end, orderByComparator);
	}

	/**
	* Returns the first training exam in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam
	* @throws com.liferay.osb.NoSuchTrainingExamException if a matching training exam could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam findByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName_First(name, orderByComparator);
	}

	/**
	* Returns the first training exam in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam, or <code>null</code> if a matching training exam could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam fetchByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName_First(name, orderByComparator);
	}

	/**
	* Returns the last training exam in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam
	* @throws com.liferay.osb.NoSuchTrainingExamException if a matching training exam could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam findByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName_Last(name, orderByComparator);
	}

	/**
	* Returns the last training exam in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam, or <code>null</code> if a matching training exam could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam fetchByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName_Last(name, orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingExam[] findByName_PrevAndNext(
		long trainingExamId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByName_PrevAndNext(trainingExamId, name,
			orderByComparator);
	}

	/**
	* Returns all the training exams.
	*
	* @return the training exams
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingExam> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingExam> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingExam> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the training exams where trainingCertificateTemplateId = &#63; from the database.
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
	* Removes all the training exams where name = &#63; from the database.
	*
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByName(name);
	}

	/**
	* Removes all the training exams from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of training exams where trainingCertificateTemplateId = &#63;.
	*
	* @param trainingCertificateTemplateId the training certificate template ID
	* @return the number of matching training exams
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByTrainingCertificateTemplateId(trainingCertificateTemplateId);
	}

	/**
	* Returns the number of training exams where name = &#63;.
	*
	* @param name the name
	* @return the number of matching training exams
	* @throws SystemException if a system exception occurred
	*/
	public static int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByName(name);
	}

	/**
	* Returns the number of training exams.
	*
	* @return the number of training exams
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TrainingExamPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TrainingExamPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TrainingExamPersistence.class.getName());

			ReferenceRegistry.registerReference(TrainingExamUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(TrainingExamPersistence persistence) {
	}

	private static TrainingExamPersistence _persistence;
}