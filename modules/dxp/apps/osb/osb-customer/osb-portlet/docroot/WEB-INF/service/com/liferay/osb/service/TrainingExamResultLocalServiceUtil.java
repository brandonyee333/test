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
 * The utility for the training exam result local service. This utility wraps {@link com.liferay.osb.service.impl.TrainingExamResultLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExamResultLocalService
 * @see com.liferay.osb.service.base.TrainingExamResultLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TrainingExamResultLocalServiceImpl
 * @generated
 */
public class TrainingExamResultLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TrainingExamResultLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the training exam result to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResult the training exam result
	* @return the training exam result that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult addTrainingExamResult(
		com.liferay.osb.model.TrainingExamResult trainingExamResult)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTrainingExamResult(trainingExamResult);
	}

	/**
	* Creates a new training exam result with the primary key. Does not add the training exam result to the database.
	*
	* @param trainingExamResultId the primary key for the new training exam result
	* @return the new training exam result
	*/
	public static com.liferay.osb.model.TrainingExamResult createTrainingExamResult(
		long trainingExamResultId) {
		return getService().createTrainingExamResult(trainingExamResultId);
	}

	/**
	* Deletes the training exam result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultId the primary key of the training exam result
	* @return the training exam result that was removed
	* @throws PortalException if a training exam result with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult deleteTrainingExamResult(
		long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTrainingExamResult(trainingExamResultId);
	}

	/**
	* Deletes the training exam result from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResult the training exam result
	* @return the training exam result that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult deleteTrainingExamResult(
		com.liferay.osb.model.TrainingExamResult trainingExamResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTrainingExamResult(trainingExamResult);
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

	public static com.liferay.osb.model.TrainingExamResult fetchTrainingExamResult(
		long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTrainingExamResult(trainingExamResultId);
	}

	/**
	* Returns the training exam result with the primary key.
	*
	* @param trainingExamResultId the primary key of the training exam result
	* @return the training exam result
	* @throws PortalException if a training exam result with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult getTrainingExamResult(
		long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingExamResult(trainingExamResultId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.osb.model.TrainingExamResult> getTrainingExamResults(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingExamResults(start, end);
	}

	/**
	* Returns the number of training exam results.
	*
	* @return the number of training exam results
	* @throws SystemException if a system exception occurred
	*/
	public static int getTrainingExamResultsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingExamResultsCount();
	}

	/**
	* Updates the training exam result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResult the training exam result
	* @return the training exam result that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult updateTrainingExamResult(
		com.liferay.osb.model.TrainingExamResult trainingExamResult)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTrainingExamResult(trainingExamResult);
	}

	/**
	* Updates the training exam result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResult the training exam result
	* @param merge whether to merge the training exam result with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the training exam result that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingExamResult updateTrainingExamResult(
		com.liferay.osb.model.TrainingExamResult trainingExamResult,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTrainingExamResult(trainingExamResult, merge);
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

	public static com.liferay.osb.model.TrainingExamResult addTrainingExamResult(
		long userId, long trainingExamId, int recordType,
		java.lang.String registrationNumber, java.lang.String formKey,
		java.util.Date startDate, java.lang.String testScore, int correctCount,
		int incorrectCount, int skippedCount, int grade,
		java.lang.String emailAddress, java.lang.String firstName,
		java.lang.String lastName, java.lang.String legalEntityName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTrainingExamResult(userId, trainingExamId, recordType,
			registrationNumber, formKey, startDate, testScore, correctCount,
			incorrectCount, skippedCount, grade, emailAddress, firstName,
			lastName, legalEntityName);
	}

	public static void checkIgnoredTrainingExamResults()
		throws java.lang.Exception {
		getService().checkIgnoredTrainingExamResults();
	}

	public static void deleteTrainingExamResults(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteTrainingExamResults(userId);
	}

	public static com.liferay.osb.model.TrainingExamResult fetchTrainingExamResult(
		java.lang.String registrationNumber)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTrainingExamResult(registrationNumber);
	}

	public static java.util.List<com.liferay.osb.model.TrainingExamResult> getTrainingExamResults(
		int createDateGTDay, int createDateGTMonth, int createDateGTYear)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTrainingExamResults(createDateGTDay, createDateGTMonth,
			createDateGTYear);
	}

	public static java.util.List<com.liferay.osb.model.TrainingExamResult> getTrainingExamResults(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingExamResults(userId);
	}

	public static java.util.List<com.liferay.osb.model.TrainingExamResult> search(
		long userId, java.lang.String testKey, int startDateGTDay,
		int startDateGTMonth, int startDateGTYear, int startDateLTDay,
		int startDateLTMonth, int startDateLTYear, boolean andOperator,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(userId, testKey, startDateGTDay, startDateGTMonth,
			startDateGTYear, startDateLTDay, startDateLTMonth, startDateLTYear,
			andOperator, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingExamResult> search(
		long userId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(userId, keywords, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingExamResult> search(
		java.lang.String keywords, boolean groupByUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(keywords, groupByUserId, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingExamResult> search(
		java.lang.String firstName, java.lang.String lastName,
		java.lang.String emailAddress, java.lang.String candidateKey,
		boolean groupByUserId, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(firstName, lastName, emailAddress, candidateKey,
			groupByUserId, andOperator, start, end, obc);
	}

	public static int searchCount(long userId, java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(userId, keywords);
	}

	public static int searchCount(long userId, java.lang.String testKey,
		int startDateGTDay, int startDateGTMonth, int startDateGTYear,
		int startDateLTDay, int startDateLTMonth, int startDateLTYear,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(userId, testKey, startDateGTDay,
			startDateGTMonth, startDateGTYear, startDateLTDay,
			startDateLTMonth, startDateLTYear, andOperator);
	}

	public static int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(keywords);
	}

	public static int searchCount(java.lang.String firstName,
		java.lang.String lastName, java.lang.String emailAddress,
		java.lang.String candidateKey, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(firstName, lastName, emailAddress,
			candidateKey, andOperator);
	}

	public static com.liferay.osb.model.TrainingExamResult updateTrainingExamResult(
		long trainingExamResultId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTrainingExamResult(trainingExamResultId, status);
	}

	public static void clearService() {
		_service = null;
	}

	public static TrainingExamResultLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TrainingExamResultLocalService.class.getName());

			if (invokableLocalService instanceof TrainingExamResultLocalService) {
				_service = (TrainingExamResultLocalService)invokableLocalService;
			}
			else {
				_service = new TrainingExamResultLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TrainingExamResultLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TrainingExamResultLocalService service) {
	}

	private static TrainingExamResultLocalService _service;
}