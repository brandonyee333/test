/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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