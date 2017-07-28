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
 * Provides the remote service utility for OfferingEntry. This utility wraps
 * {@link com.liferay.osb.service.impl.OfferingEntryServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingEntryService
 * @see com.liferay.osb.service.base.OfferingEntryServiceBaseImpl
 * @see com.liferay.osb.service.impl.OfferingEntryServiceImpl
 * @generated
 */
@ProviderType
public class OfferingEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.OfferingEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.model.OfferingEntry updateOfferingEntry(
		long offeringEntryId, long accountEntryId, long orderEntryId,
		long productEntryId, long supportResponseId,
		java.lang.String productDescription, int type, int version,
		boolean licenses, long licenseLifetime, long maxConcurrentUsers,
		long maxUsers, boolean supportTickets, long supportLifetime,
		int sizing, int quantity)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateOfferingEntry(offeringEntryId, accountEntryId,
			orderEntryId, productEntryId, supportResponseId,
			productDescription, type, version, licenses, licenseLifetime,
			maxConcurrentUsers, maxUsers, supportTickets, supportLifetime,
			sizing, quantity);
	}

	public static com.liferay.osb.model.OfferingEntry updateStatus(
		long offeringEntryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateStatus(offeringEntryId, status);
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

	public static java.util.List<com.liferay.osb.model.OfferingEntry> getAccountEntryOfferingEntries(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAccountEntryOfferingEntries(accountEntryId);
	}

	public static java.util.List<com.liferay.osb.model.OfferingEntry> getOrderEntryOfferingEntries(
		long orderEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOrderEntryOfferingEntries(orderEntryId);
	}

	public static void clearService() {
		_service = null;
	}

	public static OfferingEntryService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					OfferingEntryService.class.getName());

			if (invokableService instanceof OfferingEntryService) {
				_service = (OfferingEntryService)invokableService;
			}
			else {
				_service = new OfferingEntryServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(OfferingEntryServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static OfferingEntryService _service;
}