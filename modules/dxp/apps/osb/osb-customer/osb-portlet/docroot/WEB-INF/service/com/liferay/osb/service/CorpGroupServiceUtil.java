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
import com.liferay.portal.service.InvokableService;

/**
 * The utility for the corp group remote service. This utility wraps {@link com.liferay.osb.service.impl.CorpGroupServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpGroupService
 * @see com.liferay.osb.service.base.CorpGroupServiceBaseImpl
 * @see com.liferay.osb.service.impl.CorpGroupServiceImpl
 * @generated
 */
public class CorpGroupServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.CorpGroupServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

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

	public static com.liferay.osb.model.CorpGroup addCorpGroup(
		java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.io.File logoFile, java.lang.String emailAddress,
		java.lang.String website,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addCorpGroup(name, descriptionMap, logoFile, emailAddress,
			website, serviceContext);
	}

	public static com.liferay.osb.model.CorpGroup deleteCorpGroup(
		long corpGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteCorpGroup(corpGroupId);
	}

	public static com.liferay.osb.model.CorpGroup updateCorpGroup(
		long corpGroupId, java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.io.File logoFile, java.lang.String emailAddress,
		java.lang.String website,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCorpGroup(corpGroupId, name, descriptionMap,
			logoFile, emailAddress, website, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static CorpGroupService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					CorpGroupService.class.getName());

			if (invokableService instanceof CorpGroupService) {
				_service = (CorpGroupService)invokableService;
			}
			else {
				_service = new CorpGroupServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(CorpGroupServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(CorpGroupService service) {
	}

	private static CorpGroupService _service;
}