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
 * The utility for the portal release local service. This utility wraps {@link com.liferay.osb.service.impl.PortalReleaseLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortalReleaseLocalService
 * @see com.liferay.osb.service.base.PortalReleaseLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.PortalReleaseLocalServiceImpl
 * @generated
 */
public class PortalReleaseLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.PortalReleaseLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the portal release to the database. Also notifies the appropriate model listeners.
	*
	* @param portalRelease the portal release
	* @return the portal release that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease addPortalRelease(
		com.liferay.osb.model.PortalRelease portalRelease)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addPortalRelease(portalRelease);
	}

	/**
	* Creates a new portal release with the primary key. Does not add the portal release to the database.
	*
	* @param portalReleaseId the primary key for the new portal release
	* @return the new portal release
	*/
	public static com.liferay.osb.model.PortalRelease createPortalRelease(
		long portalReleaseId) {
		return getService().createPortalRelease(portalReleaseId);
	}

	/**
	* Deletes the portal release with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param portalReleaseId the primary key of the portal release
	* @return the portal release that was removed
	* @throws PortalException if a portal release with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease deletePortalRelease(
		long portalReleaseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deletePortalRelease(portalReleaseId);
	}

	/**
	* Deletes the portal release from the database. Also notifies the appropriate model listeners.
	*
	* @param portalRelease the portal release
	* @return the portal release that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease deletePortalRelease(
		com.liferay.osb.model.PortalRelease portalRelease)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deletePortalRelease(portalRelease);
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

	public static com.liferay.osb.model.PortalRelease fetchPortalRelease(
		long portalReleaseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchPortalRelease(portalReleaseId);
	}

	/**
	* Returns the portal release with the primary key.
	*
	* @param portalReleaseId the primary key of the portal release
	* @return the portal release
	* @throws PortalException if a portal release with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease getPortalRelease(
		long portalReleaseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPortalRelease(portalReleaseId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the portal releases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of portal releases
	* @param end the upper bound of the range of portal releases (not inclusive)
	* @return the range of portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PortalRelease> getPortalReleases(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPortalReleases(start, end);
	}

	/**
	* Returns the number of portal releases.
	*
	* @return the number of portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static int getPortalReleasesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPortalReleasesCount();
	}

	/**
	* Updates the portal release in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param portalRelease the portal release
	* @return the portal release that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease updatePortalRelease(
		com.liferay.osb.model.PortalRelease portalRelease)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updatePortalRelease(portalRelease);
	}

	/**
	* Updates the portal release in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param portalRelease the portal release
	* @param merge whether to merge the portal release with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the portal release that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease updatePortalRelease(
		com.liferay.osb.model.PortalRelease portalRelease, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updatePortalRelease(portalRelease, merge);
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

	public static com.liferay.osb.model.PortalRelease addPortalRelease(
		java.lang.String versionName, int buildNumber, boolean ee,
		boolean marketplaceSupport, boolean osgiSupport, boolean paclSupport,
		boolean hidden)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addPortalRelease(versionName, buildNumber, ee,
			marketplaceSupport, osgiSupport, paclSupport, hidden);
	}

	public static com.liferay.osb.model.PortalRelease fetchBuildNumberPortalRelease(
		int buildNumber)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchBuildNumberPortalRelease(buildNumber);
	}

	public static com.liferay.osb.model.PortalRelease getPortalRelease(
		int buildNumber)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPortalRelease(buildNumber);
	}

	public static java.util.List<com.liferay.osb.model.PortalRelease> getPortalReleases(
		boolean marketplaceSupport)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPortalReleases(marketplaceSupport);
	}

	public static java.util.List<com.liferay.osb.model.PortalRelease> getPortalReleases(
		boolean marketplaceSupport, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getPortalReleases(marketplaceSupport, start, end, obc);
	}

	public static com.liferay.osb.model.PortalRelease updatePortalRelease(
		long portalReleaseId, java.lang.String versionName, int buildNumber,
		java.lang.String fixPackName, boolean ee, boolean marketplaceSupport,
		boolean osgiSupport, boolean paclSupport, boolean hidden)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updatePortalRelease(portalReleaseId, versionName,
			buildNumber, fixPackName, ee, marketplaceSupport, osgiSupport,
			paclSupport, hidden);
	}

	public static void clearService() {
		_service = null;
	}

	public static PortalReleaseLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					PortalReleaseLocalService.class.getName());

			if (invokableLocalService instanceof PortalReleaseLocalService) {
				_service = (PortalReleaseLocalService)invokableLocalService;
			}
			else {
				_service = new PortalReleaseLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(PortalReleaseLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(PortalReleaseLocalService service) {
	}

	private static PortalReleaseLocalService _service;
}