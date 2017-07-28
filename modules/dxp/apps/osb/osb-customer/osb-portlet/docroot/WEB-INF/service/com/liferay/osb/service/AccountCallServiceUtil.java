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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for AccountCall. This utility wraps
 * {@link com.liferay.osb.service.impl.AccountCallServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccountCallService
 * @see com.liferay.osb.service.base.AccountCallServiceBaseImpl
 * @see com.liferay.osb.service.impl.AccountCallServiceImpl
 * @generated
 */
@ProviderType
public class AccountCallServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AccountCallServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.model.AccountCall deleteAccountCall(
		long accountCallId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAccountCall(accountCallId);
	}

	public static com.liferay.osb.model.AccountCall updateAccountCall(
		long accountCallId, long accountEntryId, int type, int callDateMonth,
		int callDateDay, int callDateYear, int callDateHour,
		int callDateMinute, long callLength, java.lang.String summary,
		java.lang.String clientsPresent, java.lang.String notes,
		java.lang.String actionItems)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateAccountCall(accountCallId, accountEntryId, type,
			callDateMonth, callDateDay, callDateYear, callDateHour,
			callDateMinute, callLength, summary, clientsPresent, notes,
			actionItems);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void clearService() {
		_service = null;
	}

	public static AccountCallService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AccountCallService.class.getName());

			if (invokableService instanceof AccountCallService) {
				_service = (AccountCallService)invokableService;
			}
			else {
				_service = new AccountCallServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(AccountCallServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static AccountCallService _service;
}