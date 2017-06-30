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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AppFlagLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppFlagLocalService
 * @generated
 */
public class AppFlagLocalServiceWrapper implements AppFlagLocalService,
	ServiceWrapper<AppFlagLocalService> {
	public AppFlagLocalServiceWrapper(AppFlagLocalService appFlagLocalService) {
		_appFlagLocalService = appFlagLocalService;
	}

	/**
	* Adds the app flag to the database. Also notifies the appropriate model listeners.
	*
	* @param appFlag the app flag
	* @return the app flag that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag addAppFlag(
		com.liferay.osb.model.AppFlag appFlag)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.addAppFlag(appFlag);
	}

	/**
	* Creates a new app flag with the primary key. Does not add the app flag to the database.
	*
	* @param appFlagId the primary key for the new app flag
	* @return the new app flag
	*/
	public com.liferay.osb.model.AppFlag createAppFlag(long appFlagId) {
		return _appFlagLocalService.createAppFlag(appFlagId);
	}

	/**
	* Deletes the app flag with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appFlagId the primary key of the app flag
	* @return the app flag that was removed
	* @throws PortalException if a app flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag deleteAppFlag(long appFlagId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.deleteAppFlag(appFlagId);
	}

	/**
	* Deletes the app flag from the database. Also notifies the appropriate model listeners.
	*
	* @param appFlag the app flag
	* @return the app flag that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag deleteAppFlag(
		com.liferay.osb.model.AppFlag appFlag)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.deleteAppFlag(appFlag);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _appFlagLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AppFlag fetchAppFlag(long appFlagId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.fetchAppFlag(appFlagId);
	}

	/**
	* Returns the app flag with the primary key.
	*
	* @param appFlagId the primary key of the app flag
	* @return the app flag
	* @throws PortalException if a app flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag getAppFlag(long appFlagId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.getAppFlag(appFlagId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the app flags.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app flags
	* @param end the upper bound of the range of app flags (not inclusive)
	* @return the range of app flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppFlag> getAppFlags(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.getAppFlags(start, end);
	}

	/**
	* Returns the number of app flags.
	*
	* @return the number of app flags
	* @throws SystemException if a system exception occurred
	*/
	public int getAppFlagsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.getAppFlagsCount();
	}

	/**
	* Updates the app flag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appFlag the app flag
	* @return the app flag that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag updateAppFlag(
		com.liferay.osb.model.AppFlag appFlag)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.updateAppFlag(appFlag);
	}

	/**
	* Updates the app flag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appFlag the app flag
	* @param merge whether to merge the app flag with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the app flag that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppFlag updateAppFlag(
		com.liferay.osb.model.AppFlag appFlag, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.updateAppFlag(appFlag, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _appFlagLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_appFlagLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _appFlagLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.osb.model.AppFlag addAppFlag(long appEntryId,
		long appVersionId, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.addAppFlag(appEntryId, appVersionId, type);
	}

	public com.liferay.osb.model.AppFlag fetchAppFlag(long appVersionId,
		int type) throws com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.fetchAppFlag(appVersionId, type);
	}

	public boolean hasAppFlag(long appVersionId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.hasAppFlag(appVersionId, type);
	}

	public boolean hasAppFlags(long developerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appFlagLocalService.hasAppFlags(developerEntryId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AppFlagLocalService getWrappedAppFlagLocalService() {
		return _appFlagLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAppFlagLocalService(
		AppFlagLocalService appFlagLocalService) {
		_appFlagLocalService = appFlagLocalService;
	}

	public AppFlagLocalService getWrappedService() {
		return _appFlagLocalService;
	}

	public void setWrappedService(AppFlagLocalService appFlagLocalService) {
		_appFlagLocalService = appFlagLocalService;
	}

	private AppFlagLocalService _appFlagLocalService;
}