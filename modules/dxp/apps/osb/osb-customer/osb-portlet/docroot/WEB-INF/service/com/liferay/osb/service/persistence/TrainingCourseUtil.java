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

import com.liferay.osb.model.TrainingCourse;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the training course service. This utility wraps {@link TrainingCoursePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingCoursePersistence
 * @see TrainingCoursePersistenceImpl
 * @generated
 */
public class TrainingCourseUtil {
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
	public static void clearCache(TrainingCourse trainingCourse) {
		getPersistence().clearCache(trainingCourse);
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
	public static List<TrainingCourse> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TrainingCourse> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TrainingCourse> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TrainingCourse update(TrainingCourse trainingCourse,
		boolean merge) throws SystemException {
		return getPersistence().update(trainingCourse, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TrainingCourse update(TrainingCourse trainingCourse,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(trainingCourse, merge, serviceContext);
	}

	/**
	* Caches the training course in the entity cache if it is enabled.
	*
	* @param trainingCourse the training course
	*/
	public static void cacheResult(
		com.liferay.osb.model.TrainingCourse trainingCourse) {
		getPersistence().cacheResult(trainingCourse);
	}

	/**
	* Caches the training courses in the entity cache if it is enabled.
	*
	* @param trainingCourses the training courses
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingCourse> trainingCourses) {
		getPersistence().cacheResult(trainingCourses);
	}

	/**
	* Creates a new training course with the primary key. Does not add the training course to the database.
	*
	* @param trainingCourseId the primary key for the new training course
	* @return the new training course
	*/
	public static com.liferay.osb.model.TrainingCourse create(
		long trainingCourseId) {
		return getPersistence().create(trainingCourseId);
	}

	/**
	* Removes the training course with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCourseId the primary key of the training course
	* @return the training course that was removed
	* @throws com.liferay.osb.NoSuchTrainingCourseException if a training course with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCourse remove(
		long trainingCourseId)
		throws com.liferay.osb.NoSuchTrainingCourseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(trainingCourseId);
	}

	public static com.liferay.osb.model.TrainingCourse updateImpl(
		com.liferay.osb.model.TrainingCourse trainingCourse, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(trainingCourse, merge);
	}

	/**
	* Returns the training course with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingCourseException} if it could not be found.
	*
	* @param trainingCourseId the primary key of the training course
	* @return the training course
	* @throws com.liferay.osb.NoSuchTrainingCourseException if a training course with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCourse findByPrimaryKey(
		long trainingCourseId)
		throws com.liferay.osb.NoSuchTrainingCourseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(trainingCourseId);
	}

	/**
	* Returns the training course with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingCourseId the primary key of the training course
	* @return the training course, or <code>null</code> if a training course with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCourse fetchByPrimaryKey(
		long trainingCourseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(trainingCourseId);
	}

	/**
	* Returns all the training courses.
	*
	* @return the training courses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCourse> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the training courses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training courses
	* @param end the upper bound of the range of training courses (not inclusive)
	* @return the range of training courses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCourse> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the training courses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training courses
	* @param end the upper bound of the range of training courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of training courses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCourse> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the training courses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of training courses.
	*
	* @return the number of training courses
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TrainingCoursePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TrainingCoursePersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TrainingCoursePersistence.class.getName());

			ReferenceRegistry.registerReference(TrainingCourseUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(TrainingCoursePersistence persistence) {
	}

	private static TrainingCoursePersistence _persistence;
}