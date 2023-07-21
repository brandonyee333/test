/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service;

import com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for AccountEnvironmentAttachment. This utility wraps
 * <code>com.liferay.osb.customer.admin.service.impl.AccountEnvironmentAttachmentServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentAttachmentService
 * @generated
 */
public class AccountEnvironmentAttachmentServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountEnvironmentAttachmentServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static AccountEnvironmentAttachment getAccountEnvironmentAttachment(
			long accountEnvironmentAttachmentId)
		throws PortalException {

		return getService().getAccountEnvironmentAttachment(
			accountEnvironmentAttachmentId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static AccountEnvironmentAttachmentService getService() {
		return _service;
	}

	public static void setService(AccountEnvironmentAttachmentService service) {
		_service = service;
	}

	private static volatile AccountEnvironmentAttachmentService _service;

}