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
 * The utility for the ticket canned response remote service. This utility wraps {@link com.liferay.osb.service.impl.TicketCannedResponseServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketCannedResponseService
 * @see com.liferay.osb.service.base.TicketCannedResponseServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketCannedResponseServiceImpl
 * @generated
 */
public class TicketCannedResponseServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketCannedResponseServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static void incrementUseCount(long ticketCannedResponseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().incrementUseCount(ticketCannedResponseId);
	}

	public static java.util.List<com.liferay.osb.model.TicketCannedResponse> search(
		java.lang.String keywords, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().search(keywords, start, end);
	}

	public static java.util.List<com.liferay.osb.model.TicketCannedResponse> search(
		java.lang.String name, java.lang.String content, boolean andSearch,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().search(name, content, andSearch, start, end);
	}

	public static int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(keywords);
	}

	public static int searchCount(java.lang.String name,
		java.lang.String content, boolean andSearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(name, content, andSearch);
	}

	public static void clearService() {
		_service = null;
	}

	public static TicketCannedResponseService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketCannedResponseService.class.getName());

			if (invokableService instanceof TicketCannedResponseService) {
				_service = (TicketCannedResponseService)invokableService;
			}
			else {
				_service = new TicketCannedResponseServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(TicketCannedResponseServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TicketCannedResponseService service) {
	}

	private static TicketCannedResponseService _service;
}