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
 * The utility for the app audit local service. This utility wraps {@link com.liferay.osb.service.impl.AppAuditLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppAuditLocalService
 * @see com.liferay.osb.service.base.AppAuditLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AppAuditLocalServiceImpl
 * @generated
 */
public class AppAuditLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AppAuditLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the app audit to the database. Also notifies the appropriate model listeners.
	*
	* @param appAudit the app audit
	* @return the app audit that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit addAppAudit(
		com.liferay.osb.model.AppAudit appAudit)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAppAudit(appAudit);
	}

	/**
	* Creates a new app audit with the primary key. Does not add the app audit to the database.
	*
	* @param appAuditId the primary key for the new app audit
	* @return the new app audit
	*/
	public static com.liferay.osb.model.AppAudit createAppAudit(long appAuditId) {
		return getService().createAppAudit(appAuditId);
	}

	/**
	* Deletes the app audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appAuditId the primary key of the app audit
	* @return the app audit that was removed
	* @throws PortalException if a app audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit deleteAppAudit(long appAuditId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAppAudit(appAuditId);
	}

	/**
	* Deletes the app audit from the database. Also notifies the appropriate model listeners.
	*
	* @param appAudit the app audit
	* @return the app audit that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit deleteAppAudit(
		com.liferay.osb.model.AppAudit appAudit)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAppAudit(appAudit);
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

	public static com.liferay.osb.model.AppAudit fetchAppAudit(long appAuditId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAppAudit(appAuditId);
	}

	/**
	* Returns the app audit with the primary key.
	*
	* @param appAuditId the primary key of the app audit
	* @return the app audit
	* @throws PortalException if a app audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit getAppAudit(long appAuditId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppAudit(appAuditId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the app audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app audits
	* @param end the upper bound of the range of app audits (not inclusive)
	* @return the range of app audits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppAudit> getAppAudits(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppAudits(start, end);
	}

	/**
	* Returns the number of app audits.
	*
	* @return the number of app audits
	* @throws SystemException if a system exception occurred
	*/
	public static int getAppAuditsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppAuditsCount();
	}

	/**
	* Updates the app audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appAudit the app audit
	* @return the app audit that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit updateAppAudit(
		com.liferay.osb.model.AppAudit appAudit)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAppAudit(appAudit);
	}

	/**
	* Updates the app audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appAudit the app audit
	* @param merge whether to merge the app audit with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the app audit that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit updateAppAudit(
		com.liferay.osb.model.AppAudit appAudit, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAppAudit(appAudit, merge);
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

	public static void addAppAudit(long userId, long appEntryId,
		long appVersionId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().addAppAudit(userId, appEntryId, appVersionId, status);
	}

	public static java.util.List<com.liferay.osb.model.AppAudit> getAppAudits(
		long appVersionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppAudits(appVersionId, start, end, obc);
	}

	public static void clearService() {
		_service = null;
	}

	public static AppAuditLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AppAuditLocalService.class.getName());

			if (invokableLocalService instanceof AppAuditLocalService) {
				_service = (AppAuditLocalService)invokableLocalService;
			}
			else {
				_service = new AppAuditLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AppAuditLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AppAuditLocalService service) {
	}

	private static AppAuditLocalService _service;
}