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
 * Provides the remote service utility for PartnerEntry. This utility wraps
 * {@link com.liferay.osb.service.impl.PartnerEntryServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see PartnerEntryService
 * @see com.liferay.osb.service.base.PartnerEntryServiceBaseImpl
 * @see com.liferay.osb.service.impl.PartnerEntryServiceImpl
 * @generated
 */
@ProviderType
public class PartnerEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.PartnerEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.model.PartnerEntry getPartnerEntry(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPartnerEntry(partnerEntryId);
	}

	public static int searchCount(java.lang.String code, int[] statuses,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().searchCount(code, statuses, params, andOperator);
	}

	public static int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().searchCount(keywords);
	}

	public static int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().searchCount(keywords, params);
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

	public static java.util.List<com.liferay.osb.model.PartnerEntry> search(
		java.lang.String code, int[] statuses,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .search(code, statuses, params, andOperator, start, end);
	}

	public static java.util.List<com.liferay.osb.model.PartnerEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().search(keywords, params, start, end);
	}

	public static void clearService() {
		_service = null;
	}

	public static PartnerEntryService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					PartnerEntryService.class.getName());

			if (invokableService instanceof PartnerEntryService) {
				_service = (PartnerEntryService)invokableService;
			}
			else {
				_service = new PartnerEntryServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(PartnerEntryServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static PartnerEntryService _service;
}