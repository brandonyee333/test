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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the training course service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingCoursePersistenceImpl
 * @see TrainingCourseUtil
 * @generated
 */
public interface TrainingCoursePersistence extends BasePersistence<TrainingCourse> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrainingCourseUtil} to access the training course persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the training course in the entity cache if it is enabled.
	*
	* @param trainingCourse the training course
	*/
	public void cacheResult(com.liferay.osb.model.TrainingCourse trainingCourse);

	/**
	* Caches the training courses in the entity cache if it is enabled.
	*
	* @param trainingCourses the training courses
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingCourse> trainingCourses);

	/**
	* Creates a new training course with the primary key. Does not add the training course to the database.
	*
	* @param trainingCourseId the primary key for the new training course
	* @return the new training course
	*/
	public com.liferay.osb.model.TrainingCourse create(long trainingCourseId);

	/**
	* Removes the training course with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCourseId the primary key of the training course
	* @return the training course that was removed
	* @throws com.liferay.osb.NoSuchTrainingCourseException if a training course with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCourse remove(long trainingCourseId)
		throws com.liferay.osb.NoSuchTrainingCourseException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TrainingCourse updateImpl(
		com.liferay.osb.model.TrainingCourse trainingCourse, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training course with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingCourseException} if it could not be found.
	*
	* @param trainingCourseId the primary key of the training course
	* @return the training course
	* @throws com.liferay.osb.NoSuchTrainingCourseException if a training course with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCourse findByPrimaryKey(
		long trainingCourseId)
		throws com.liferay.osb.NoSuchTrainingCourseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training course with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingCourseId the primary key of the training course
	* @return the training course, or <code>null</code> if a training course with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCourse fetchByPrimaryKey(
		long trainingCourseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training courses.
	*
	* @return the training courses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingCourse> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.TrainingCourse> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.TrainingCourse> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training courses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training courses.
	*
	* @return the number of training courses
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}