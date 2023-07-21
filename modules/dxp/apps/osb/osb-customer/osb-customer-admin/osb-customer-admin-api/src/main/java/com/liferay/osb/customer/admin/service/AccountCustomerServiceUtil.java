/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for AccountCustomer. This utility wraps
 * <code>com.liferay.osb.customer.admin.service.impl.AccountCustomerServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccountCustomerService
 * @generated
 */
public class AccountCustomerServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountCustomerServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static List<com.liferay.portal.kernel.model.User>
			getCorpProjectAccountCustomerUsers(String corpProjectUuid)
		throws PortalException {

		return getService().getCorpProjectAccountCustomerUsers(corpProjectUuid);
	}

	public static List<String> getCorpProjectAccountCustomerUUIDs(
			String corpProjectUuid)
		throws PortalException {

		return getService().getCorpProjectAccountCustomerUUIDs(corpProjectUuid);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static AccountCustomerService getService() {
		return _service;
	}

	public static void setService(AccountCustomerService service) {
		_service = service;
	}

	private static volatile AccountCustomerService _service;

}