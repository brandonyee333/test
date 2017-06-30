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
 * The utility for the training import log local service. This utility wraps {@link com.liferay.osb.service.impl.TrainingImportLogLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingImportLogLocalService
 * @see com.liferay.osb.service.base.TrainingImportLogLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TrainingImportLogLocalServiceImpl
 * @generated
 */
public class TrainingImportLogLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TrainingImportLogLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the training import log to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingImportLog the training import log
	* @return the training import log that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingImportLog addTrainingImportLog(
		com.liferay.osb.model.TrainingImportLog trainingImportLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTrainingImportLog(trainingImportLog);
	}

	/**
	* Creates a new training import log with the primary key. Does not add the training import log to the database.
	*
	* @param trainingImportLogId the primary key for the new training import log
	* @return the new training import log
	*/
	public static com.liferay.osb.model.TrainingImportLog createTrainingImportLog(
		long trainingImportLogId) {
		return getService().createTrainingImportLog(trainingImportLogId);
	}

	/**
	* Deletes the training import log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingImportLogId the primary key of the training import log
	* @return the training import log that was removed
	* @throws PortalException if a training import log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingImportLog deleteTrainingImportLog(
		long trainingImportLogId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTrainingImportLog(trainingImportLogId);
	}

	/**
	* Deletes the training import log from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingImportLog the training import log
	* @return the training import log that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingImportLog deleteTrainingImportLog(
		com.liferay.osb.model.TrainingImportLog trainingImportLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTrainingImportLog(trainingImportLog);
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

	public static com.liferay.osb.model.TrainingImportLog fetchTrainingImportLog(
		long trainingImportLogId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTrainingImportLog(trainingImportLogId);
	}

	/**
	* Returns the training import log with the primary key.
	*
	* @param trainingImportLogId the primary key of the training import log
	* @return the training import log
	* @throws PortalException if a training import log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingImportLog getTrainingImportLog(
		long trainingImportLogId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingImportLog(trainingImportLogId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the training import logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training import logs
	* @param end the upper bound of the range of training import logs (not inclusive)
	* @return the range of training import logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingImportLog> getTrainingImportLogs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingImportLogs(start, end);
	}

	/**
	* Returns the number of training import logs.
	*
	* @return the number of training import logs
	* @throws SystemException if a system exception occurred
	*/
	public static int getTrainingImportLogsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingImportLogsCount();
	}

	/**
	* Updates the training import log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingImportLog the training import log
	* @return the training import log that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingImportLog updateTrainingImportLog(
		com.liferay.osb.model.TrainingImportLog trainingImportLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTrainingImportLog(trainingImportLog);
	}

	/**
	* Updates the training import log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingImportLog the training import log
	* @param merge whether to merge the training import log with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the training import log that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingImportLog updateTrainingImportLog(
		com.liferay.osb.model.TrainingImportLog trainingImportLog, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTrainingImportLog(trainingImportLog, merge);
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

	public static com.liferay.osb.model.TrainingImportLog addTrainingImportLog(
		int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addTrainingImportLog(type);
	}

	public static java.util.List<com.liferay.osb.model.TrainingImportLog> getTrainingImportLogs(
		int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingImportLogs(type, start, end);
	}

	public static void clearService() {
		_service = null;
	}

	public static TrainingImportLogLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TrainingImportLogLocalService.class.getName());

			if (invokableLocalService instanceof TrainingImportLogLocalService) {
				_service = (TrainingImportLogLocalService)invokableLocalService;
			}
			else {
				_service = new TrainingImportLogLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TrainingImportLogLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TrainingImportLogLocalService service) {
	}

	private static TrainingImportLogLocalService _service;
}