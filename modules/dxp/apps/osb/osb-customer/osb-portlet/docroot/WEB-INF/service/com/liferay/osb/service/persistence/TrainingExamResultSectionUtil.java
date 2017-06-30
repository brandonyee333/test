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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the training exam result section service. This utility wraps {@link TrainingExamResultSectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExamResultSectionPersistence
 * @see TrainingExamResultSectionPersistenceImpl
 * @generated
 */
public class TrainingExamResultSectionUtil {
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
	public static void clearCache(
		TrainingExamResultSection trainingExamResultSection) {
		getPersistence().clearCache(trainingExamResultSection);
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
	public static List<TrainingExamResultSection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TrainingExamResultSection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TrainingExamResultSection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TrainingExamResultSection update(
		TrainingExamResultSection trainingExamResultSection, boolean merge)
		throws SystemException {
		return getPersistence().update(trainingExamResultSection, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TrainingExamResultSection update(
		TrainingExamResultSection trainingExamResultSection, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(trainingExamResultSection, merge, serviceContext);
	}

	/**
	* Caches the training exam result section in the entity cache if it is enabled.
	*
	* @param trainingExamResultSection the training exam result section
	*/
	public static void cacheResult(
		com.liferay.osb.model.TrainingExamResultSection trainingExamResultSection) {
		getPersistence().cacheResult(trainingExamResultSection);
	}

	/**
	* Caches the training exam result sections in the entity cache if it is enabled.
	*
	* @param trainingExamResultSections the training exam result sections
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingExamResultSection> trainingExamResultSections) {
		getPersistence().cacheResult(trainingExamResultSections);
	}

	/**
	* Creates a new training exam result section with the primary key. Does not add the training exam result section to the database.
	*
	* @param trainingExamResultSectionId the primary key for the new training exam result section
	* @return the new training exam result section
	*/
	public static com.liferay.osb.model.TrainingExamResultSection create(
		long trainingExamResultSectionId) {
		return getPersistence().create(trainingExamResultSectionId);
	}

	/**
	* Removes the training exam result section with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultSectionId the primary key of the training exam result section
	* @return the training exam result section that was removed
	* @throws com.liferay.osb.NoSuchTrainingExamResultSectionException if a training exam result section with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResultSection remove(
		long trainingExamResultSectionId)
		throws com.liferay.osb.NoSuchTrainingExamResultSectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(trainingExamResultSectionId);
	}

	public static com.liferay.osb.model.TrainingExamResultSection updateImpl(
		com.liferay.osb.model.TrainingExamResultSection trainingExamResultSection,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(trainingExamResultSection, merge);
	}

	/**
	* Returns the training exam result section with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingExamResultSectionException} if it could not be found.
	*
	* @param trainingExamResultSectionId the primary key of the training exam result section
	* @return the training exam result section
	* @throws com.liferay.osb.NoSuchTrainingExamResultSectionException if a training exam result section with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResultSection findByPrimaryKey(
		long trainingExamResultSectionId)
		throws com.liferay.osb.NoSuchTrainingExamResultSectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(trainingExamResultSectionId);
	}

	/**
	* Returns the training exam result section with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingExamResultSectionId the primary key of the training exam result section
	* @return the training exam result section, or <code>null</code> if a training exam result section with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResultSection fetchByPrimaryKey(
		long trainingExamResultSectionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(trainingExamResultSectionId);
	}

	/**
	* Returns all the training exam result sections where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @return the matching training exam result sections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingExamResultSection> findByTrainingExamResultId(
		long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTrainingExamResultId(trainingExamResultId);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingExamResultSection> findByTrainingExamResultId(
		long trainingExamResultId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingExamResultId(trainingExamResultId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingExamResultSection> findByTrainingExamResultId(
		long trainingExamResultId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingExamResultId(trainingExamResultId, start,
			end, orderByComparator);
	}

	/**
	* Returns the first training exam result section in the ordered set where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam result section
	* @throws com.liferay.osb.NoSuchTrainingExamResultSectionException if a matching training exam result section could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResultSection findByTrainingExamResultId_First(
		long trainingExamResultId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultSectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingExamResultId_First(trainingExamResultId,
			orderByComparator);
	}

	/**
	* Returns the first training exam result section in the ordered set where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam result section, or <code>null</code> if a matching training exam result section could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResultSection fetchByTrainingExamResultId_First(
		long trainingExamResultId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTrainingExamResultId_First(trainingExamResultId,
			orderByComparator);
	}

	/**
	* Returns the last training exam result section in the ordered set where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam result section
	* @throws com.liferay.osb.NoSuchTrainingExamResultSectionException if a matching training exam result section could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResultSection findByTrainingExamResultId_Last(
		long trainingExamResultId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultSectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingExamResultId_Last(trainingExamResultId,
			orderByComparator);
	}

	/**
	* Returns the last training exam result section in the ordered set where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam result section, or <code>null</code> if a matching training exam result section could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResultSection fetchByTrainingExamResultId_Last(
		long trainingExamResultId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTrainingExamResultId_Last(trainingExamResultId,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.TrainingExamResultSection[] findByTrainingExamResultId_PrevAndNext(
		long trainingExamResultSectionId, long trainingExamResultId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultSectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTrainingExamResultId_PrevAndNext(trainingExamResultSectionId,
			trainingExamResultId, orderByComparator);
	}

	/**
	* Returns all the training exam result sections.
	*
	* @return the training exam result sections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingExamResultSection> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.liferay.osb.model.TrainingExamResultSection> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingExamResultSection> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the training exam result sections where trainingExamResultId = &#63; from the database.
	*
	* @param trainingExamResultId the training exam result ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTrainingExamResultId(long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTrainingExamResultId(trainingExamResultId);
	}

	/**
	* Removes all the training exam result sections from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of training exam result sections where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @return the number of matching training exam result sections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTrainingExamResultId(long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTrainingExamResultId(trainingExamResultId);
	}

	/**
	* Returns the number of training exam result sections.
	*
	* @return the number of training exam result sections
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TrainingExamResultSectionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TrainingExamResultSectionPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TrainingExamResultSectionPersistence.class.getName());

			ReferenceRegistry.registerReference(TrainingExamResultSectionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(TrainingExamResultSectionPersistence persistence) {
	}

	private static TrainingExamResultSectionPersistence _persistence;
}