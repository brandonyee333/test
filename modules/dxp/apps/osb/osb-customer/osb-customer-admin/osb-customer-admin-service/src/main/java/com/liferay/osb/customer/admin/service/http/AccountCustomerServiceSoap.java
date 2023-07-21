/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service.http;

import com.liferay.osb.customer.admin.service.AccountCustomerServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>AccountCustomerServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountCustomerServiceHttp
 * @generated
 */
public class AccountCustomerServiceSoap {

	public static com.liferay.portal.kernel.model.User[]
			getCorpProjectAccountCustomerUsers(String corpProjectUuid)
		throws RemoteException {

		try {
			java.util.List<com.liferay.portal.kernel.model.User> returnValue =
				AccountCustomerServiceUtil.getCorpProjectAccountCustomerUsers(
					corpProjectUuid);

			return returnValue.toArray(
				new com.liferay.portal.kernel.model.User[returnValue.size()]);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String[] getCorpProjectAccountCustomerUUIDs(
			String corpProjectUuid)
		throws RemoteException {

		try {
			java.util.List<String> returnValue =
				AccountCustomerServiceUtil.getCorpProjectAccountCustomerUUIDs(
					corpProjectUuid);

			return returnValue.toArray(new String[returnValue.size()]);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AccountCustomerServiceSoap.class);

}