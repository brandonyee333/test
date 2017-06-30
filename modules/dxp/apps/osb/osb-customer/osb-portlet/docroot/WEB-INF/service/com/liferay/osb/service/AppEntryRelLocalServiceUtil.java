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
 * The utility for the app entry rel local service. This utility wraps {@link com.liferay.osb.service.impl.AppEntryRelLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppEntryRelLocalService
 * @see com.liferay.osb.service.base.AppEntryRelLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AppEntryRelLocalServiceImpl
 * @generated
 */
public class AppEntryRelLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AppEntryRelLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the app entry rel to the database. Also notifies the appropriate model listeners.
	*
	* @param appEntryRel the app entry rel
	* @return the app entry rel that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntryRel addAppEntryRel(
		com.liferay.osb.model.AppEntryRel appEntryRel)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAppEntryRel(appEntryRel);
	}

	/**
	* Creates a new app entry rel with the primary key. Does not add the app entry rel to the database.
	*
	* @param appEntryRelId the primary key for the new app entry rel
	* @return the new app entry rel
	*/
	public static com.liferay.osb.model.AppEntryRel createAppEntryRel(
		long appEntryRelId) {
		return getService().createAppEntryRel(appEntryRelId);
	}

	/**
	* Deletes the app entry rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appEntryRelId the primary key of the app entry rel
	* @return the app entry rel that was removed
	* @throws PortalException if a app entry rel with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntryRel deleteAppEntryRel(
		long appEntryRelId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAppEntryRel(appEntryRelId);
	}

	/**
	* Deletes the app entry rel from the database. Also notifies the appropriate model listeners.
	*
	* @param appEntryRel the app entry rel
	* @return the app entry rel that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntryRel deleteAppEntryRel(
		com.liferay.osb.model.AppEntryRel appEntryRel)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAppEntryRel(appEntryRel);
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

	public static com.liferay.osb.model.AppEntryRel fetchAppEntryRel(
		long appEntryRelId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAppEntryRel(appEntryRelId);
	}

	/**
	* Returns the app entry rel with the primary key.
	*
	* @param appEntryRelId the primary key of the app entry rel
	* @return the app entry rel
	* @throws PortalException if a app entry rel with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntryRel getAppEntryRel(
		long appEntryRelId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppEntryRel(appEntryRelId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the app entry rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app entry rels
	* @param end the upper bound of the range of app entry rels (not inclusive)
	* @return the range of app entry rels
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppEntryRel> getAppEntryRels(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppEntryRels(start, end);
	}

	/**
	* Returns the number of app entry rels.
	*
	* @return the number of app entry rels
	* @throws SystemException if a system exception occurred
	*/
	public static int getAppEntryRelsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppEntryRelsCount();
	}

	/**
	* Updates the app entry rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appEntryRel the app entry rel
	* @return the app entry rel that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntryRel updateAppEntryRel(
		com.liferay.osb.model.AppEntryRel appEntryRel)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAppEntryRel(appEntryRel);
	}

	/**
	* Updates the app entry rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appEntryRel the app entry rel
	* @param merge whether to merge the app entry rel with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the app entry rel that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppEntryRel updateAppEntryRel(
		com.liferay.osb.model.AppEntryRel appEntryRel, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAppEntryRel(appEntryRel, merge);
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

	public static com.liferay.osb.model.AppEntryRel addAppEntryRel(
		long appEntryId1, long appEntryId2, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addAppEntryRel(appEntryId1, appEntryId2, type);
	}

	public static java.util.List<com.liferay.osb.model.AppEntryRel> getAppEntryRels(
		long appEntryId1, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppEntryRels(appEntryId1, type);
	}

	public static void clearService() {
		_service = null;
	}

	public static AppEntryRelLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AppEntryRelLocalService.class.getName());

			if (invokableLocalService instanceof AppEntryRelLocalService) {
				_service = (AppEntryRelLocalService)invokableLocalService;
			}
			else {
				_service = new AppEntryRelLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AppEntryRelLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AppEntryRelLocalService service) {
	}

	private static AppEntryRelLocalService _service;
}