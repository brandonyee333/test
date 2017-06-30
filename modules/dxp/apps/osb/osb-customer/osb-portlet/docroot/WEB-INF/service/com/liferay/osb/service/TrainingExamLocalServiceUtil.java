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
 * The utility for the training exam local service. This utility wraps {@link com.liferay.osb.service.impl.TrainingExamLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExamLocalService
 * @see com.liferay.osb.service.base.TrainingExamLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TrainingExamLocalServiceImpl
 * @generated
 */
public class TrainingExamLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TrainingExamLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the training exam to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExam the training exam
	* @return the training exam that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam addTrainingExam(
		com.liferay.osb.model.TrainingExam trainingExam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTrainingExam(trainingExam);
	}

	/**
	* Creates a new training exam with the primary key. Does not add the training exam to the database.
	*
	* @param trainingExamId the primary key for the new training exam
	* @return the new training exam
	*/
	public static com.liferay.osb.model.TrainingExam createTrainingExam(
		long trainingExamId) {
		return getService().createTrainingExam(trainingExamId);
	}

	/**
	* Deletes the training exam with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamId the primary key of the training exam
	* @return the training exam that was removed
	* @throws PortalException if a training exam with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam deleteTrainingExam(
		long trainingExamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTrainingExam(trainingExamId);
	}

	/**
	* Deletes the training exam from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExam the training exam
	* @return the training exam that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam deleteTrainingExam(
		com.liferay.osb.model.TrainingExam trainingExam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTrainingExam(trainingExam);
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

	public static com.liferay.osb.model.TrainingExam fetchTrainingExam(
		long trainingExamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTrainingExam(trainingExamId);
	}

	/**
	* Returns the training exam with the primary key.
	*
	* @param trainingExamId the primary key of the training exam
	* @return the training exam
	* @throws PortalException if a training exam with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam getTrainingExam(
		long trainingExamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingExam(trainingExamId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.osb.model.TrainingExam> getTrainingExams(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingExams(start, end);
	}

	/**
	* Returns the number of training exams.
	*
	* @return the number of training exams
	* @throws SystemException if a system exception occurred
	*/
	public static int getTrainingExamsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingExamsCount();
	}

	/**
	* Updates the training exam in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingExam the training exam
	* @return the training exam that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam updateTrainingExam(
		com.liferay.osb.model.TrainingExam trainingExam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTrainingExam(trainingExam);
	}

	/**
	* Updates the training exam in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingExam the training exam
	* @param merge whether to merge the training exam with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the training exam that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExam updateTrainingExam(
		com.liferay.osb.model.TrainingExam trainingExam, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTrainingExam(trainingExam, merge);
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

	public static com.liferay.osb.model.TrainingExam addTrainingExam(
		java.lang.String name, java.lang.String testKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTrainingExam(name, testKey);
	}

	public static java.util.List<com.liferay.osb.model.TrainingExam> getTrainingExams(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingExams(name);
	}

	public static com.liferay.osb.model.TrainingExam updateTrainingExam(
		long trainingExamId, long trainingCertificateTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTrainingExam(trainingExamId,
			trainingCertificateTemplateId);
	}

	public static void clearService() {
		_service = null;
	}

	public static TrainingExamLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TrainingExamLocalService.class.getName());

			if (invokableLocalService instanceof TrainingExamLocalService) {
				_service = (TrainingExamLocalService)invokableLocalService;
			}
			else {
				_service = new TrainingExamLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TrainingExamLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TrainingExamLocalService service) {
	}

	private static TrainingExamLocalService _service;
}