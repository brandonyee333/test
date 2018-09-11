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

package com.liferay.osb.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.service.AccountEnvironmentServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link AccountEnvironmentServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.model.AccountEnvironmentSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.model.AccountEnvironment}, that is translated to a
 * {@link com.liferay.osb.model.AccountEnvironmentSoap}. Methods that SOAP cannot
 * safely wire are skipped.
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
 * @see com.liferay.osb.model.AccountEnvironmentSoap
 * @see AccountEnvironmentServiceUtil
 * @generated
 */
@ProviderType
public class AccountEnvironmentServiceSoap {
	public static com.liferay.osb.model.AccountEnvironmentSoap addAccountEnvironment(
		long accountEntryId, long productEntryId, java.lang.String name,
		int envOS, java.lang.String envOSCustom, int envDB, int envJVM,
		int envAS, int envLFR, int envBrowser, int envCS,
		java.lang.String envSearch,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types) throws RemoteException {
		try {
			com.liferay.osb.model.AccountEnvironment returnValue = AccountEnvironmentServiceUtil.addAccountEnvironment(accountEntryId,
					productEntryId, name, envOS, envOSCustom, envDB, envJVM,
					envAS, envLFR, envBrowser, envCS, envSearch, files, types);

			return com.liferay.osb.model.AccountEnvironmentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.AccountEnvironmentSoap deleteAccountEnvironment(
		long accountEnvironmentId) throws RemoteException {
		try {
			com.liferay.osb.model.AccountEnvironment returnValue = AccountEnvironmentServiceUtil.deleteAccountEnvironment(accountEnvironmentId);

			return com.liferay.osb.model.AccountEnvironmentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.AccountEnvironmentSoap getAccountEnvironment(
		long accountEnvironmentId) throws RemoteException {
		try {
			com.liferay.osb.model.AccountEnvironment returnValue = AccountEnvironmentServiceUtil.getAccountEnvironment(accountEnvironmentId);

			return com.liferay.osb.model.AccountEnvironmentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.AccountEnvironmentSoap[] getAccountEnvironments(
		long accountEntryId) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.AccountEnvironment> returnValue =
				AccountEnvironmentServiceUtil.getAccountEnvironments(accountEntryId);

			return com.liferay.osb.model.AccountEnvironmentSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.AccountEnvironmentSoap updateAccountEnvironment(
		long accountEnvironmentId, long productEntryId, java.lang.String name,
		int envOS, java.lang.String envOSCustom, int envDB, int envJVM,
		int envAS, int envLFR, int envBrowser, int envCS,
		java.lang.String envSearch,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types) throws RemoteException {
		try {
			com.liferay.osb.model.AccountEnvironment returnValue = AccountEnvironmentServiceUtil.updateAccountEnvironment(accountEnvironmentId,
					productEntryId, name, envOS, envOSCustom, envDB, envJVM,
					envAS, envLFR, envBrowser, envCS, envSearch, files, types);

			return com.liferay.osb.model.AccountEnvironmentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AccountEnvironmentServiceSoap.class);
}