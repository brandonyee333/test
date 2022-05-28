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

package com.liferay.osb.customer.admin.service.http;

import com.liferay.osb.customer.admin.service.AccountEnvironmentServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>AccountEnvironmentServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.osb.customer.admin.model.AccountEnvironmentSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.osb.customer.admin.model.AccountEnvironment</code>, that is translated to a
 * <code>com.liferay.osb.customer.admin.model.AccountEnvironmentSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
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
 * @see AccountEnvironmentServiceHttp
 * @generated
 */
public class AccountEnvironmentServiceSoap {

	public static com.liferay.osb.customer.admin.model.AccountEnvironmentSoap
			addAccountEnvironment(
				long accountEntryId, long productEntryId, String name,
				int envOS, String envOSCustom, int envDB, int envJVM, int envAS,
				int envLFR, int envCommerce, int envBrowser, int envCS,
				String envSearch)
		throws RemoteException {

		try {
			com.liferay.osb.customer.admin.model.AccountEnvironment
				returnValue =
					AccountEnvironmentServiceUtil.addAccountEnvironment(
						accountEntryId, productEntryId, name, envOS,
						envOSCustom, envDB, envJVM, envAS, envLFR, envCommerce,
						envBrowser, envCS, envSearch);

			return com.liferay.osb.customer.admin.model.AccountEnvironmentSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEnvironmentSoap
			deleteAccountEnvironment(long accountEnvironmentId)
		throws RemoteException {

		try {
			com.liferay.osb.customer.admin.model.AccountEnvironment
				returnValue =
					AccountEnvironmentServiceUtil.deleteAccountEnvironment(
						accountEnvironmentId);

			return com.liferay.osb.customer.admin.model.AccountEnvironmentSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEnvironmentSoap
			getAccountEnvironment(long accountEnvironmentId)
		throws RemoteException {

		try {
			com.liferay.osb.customer.admin.model.AccountEnvironment
				returnValue =
					AccountEnvironmentServiceUtil.getAccountEnvironment(
						accountEnvironmentId);

			return com.liferay.osb.customer.admin.model.AccountEnvironmentSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEnvironmentSoap[]
			getAccountEnvironments(long accountEntryId)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.osb.customer.admin.model.AccountEnvironment>
					returnValue =
						AccountEnvironmentServiceUtil.getAccountEnvironments(
							accountEntryId);

			return com.liferay.osb.customer.admin.model.AccountEnvironmentSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEnvironmentSoap
			updateAccountEnvironment(
				long accountEnvironmentId, long productEntryId, String name,
				int envOS, String envOSCustom, int envDB, int envJVM, int envAS,
				int envLFR, int envCommerce, int envBrowser, int envCS,
				String envSearch)
		throws RemoteException {

		try {
			com.liferay.osb.customer.admin.model.AccountEnvironment
				returnValue =
					AccountEnvironmentServiceUtil.updateAccountEnvironment(
						accountEnvironmentId, productEntryId, name, envOS,
						envOSCustom, envDB, envJVM, envAS, envLFR, envCommerce,
						envBrowser, envCS, envSearch);

			return com.liferay.osb.customer.admin.model.AccountEnvironmentSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AccountEnvironmentServiceSoap.class);

}