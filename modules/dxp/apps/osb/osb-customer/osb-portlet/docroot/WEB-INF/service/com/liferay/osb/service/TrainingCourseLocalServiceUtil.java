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

package com.liferay.osb.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the training course local service. This utility wraps {@link com.liferay.osb.service.impl.TrainingCourseLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingCourseLocalService
 * @see com.liferay.osb.service.base.TrainingCourseLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TrainingCourseLocalServiceImpl
 * @generated
 */
public class TrainingCourseLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TrainingCourseLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the training course to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCourse the training course
	* @return the training course that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCourse addTrainingCourse(
		com.liferay.osb.model.TrainingCourse trainingCourse)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTrainingCourse(trainingCourse);
	}

	/**
	* Creates a new training course with the primary key. Does not add the training course to the database.
	*
	* @param trainingCourseId the primary key for the new training course
	* @return the new training course
	*/
	public static com.liferay.osb.model.TrainingCourse createTrainingCourse(
		long trainingCourseId) {
		return getService().createTrainingCourse(trainingCourseId);
	}

	/**
	* Deletes the training course with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCourseId the primary key of the training course
	* @return the training course that was removed
	* @throws PortalException if a training course with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCourse deleteTrainingCourse(
		long trainingCourseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTrainingCourse(trainingCourseId);
	}

	/**
	* Deletes the training course from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCourse the training course
	* @return the training course that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCourse deleteTrainingCourse(
		com.liferay.osb.model.TrainingCourse trainingCourse)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTrainingCourse(trainingCourse);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.osb.model.TrainingCourse fetchTrainingCourse(
		long trainingCourseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTrainingCourse(trainingCourseId);
	}

	/**
	* Returns the training course with the primary key.
	*
	* @param trainingCourseId the primary key of the training course
	* @return the training course
	* @throws PortalException if a training course with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCourse getTrainingCourse(
		long trainingCourseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingCourse(trainingCourseId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.osb.model.TrainingCourse> getTrainingCourses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingCourses(start, end);
	}

	/**
	* Returns the number of training courses.
	*
	* @return the number of training courses
	* @throws SystemException if a system exception occurred
	*/
	public static int getTrainingCoursesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingCoursesCount();
	}

	/**
	* Updates the training course in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingCourse the training course
	* @return the training course that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCourse updateTrainingCourse(
		com.liferay.osb.model.TrainingCourse trainingCourse)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTrainingCourse(trainingCourse);
	}

	/**
	* Updates the training course in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingCourse the training course
	* @param merge whether to merge the training course with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the training course that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCourse updateTrainingCourse(
		com.liferay.osb.model.TrainingCourse trainingCourse, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTrainingCourse(trainingCourse, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.osb.model.TrainingCourse addTrainingCourse(
		long userId, java.lang.String name, java.lang.String description,
		int creditAmount, java.lang.String courseURL, boolean archived)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTrainingCourse(userId, name, description, creditAmount,
			courseURL, archived);
	}

	public static java.util.List<com.liferay.osb.model.TrainingCourse> getTrainingCourses(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingCourses(start, end, obc);
	}

	public static com.liferay.osb.model.TrainingCourse updateTrainingCourse(
		long trainingCourseId, java.lang.String name,
		java.lang.String description, int creditAmount,
		java.lang.String courseURL, boolean archived)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTrainingCourse(trainingCourseId, name, description,
			creditAmount, courseURL, archived);
	}

	public static void clearService() {
		_service = null;
	}

	public static TrainingCourseLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TrainingCourseLocalService.class.getName());

			if (invokableLocalService instanceof TrainingCourseLocalService) {
				_service = (TrainingCourseLocalService)invokableLocalService;
			}
			else {
				_service = new TrainingCourseLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TrainingCourseLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TrainingCourseLocalService service) {
	}

	private static TrainingCourseLocalService _service;
}