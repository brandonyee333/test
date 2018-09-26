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

import com.liferay.osb.service.AccountEntryServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link AccountEntryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.model.AccountEntrySoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.model.AccountEntry}, that is translated to a
 * {@link com.liferay.osb.model.AccountEntrySoap}. Methods that SOAP cannot
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
 * @see AccountEntryServiceHttp
 * @see com.liferay.osb.model.AccountEntrySoap
 * @see AccountEntryServiceUtil
 * @generated
 */
@ProviderType
public class AccountEntryServiceSoap {
	public static com.liferay.osb.model.AccountEntrySoap fetchCorpProjectAccountEntry(
		java.lang.String corpProjectUuid) throws RemoteException {
		try {
			com.liferay.osb.model.AccountEntry returnValue = AccountEntryServiceUtil.fetchCorpProjectAccountEntry(corpProjectUuid);

			return com.liferay.osb.model.AccountEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.AccountEntrySoap[] getAccountEntries(
		java.lang.String userUuid, long[] productEntryIds)
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.AccountEntry> returnValue = AccountEntryServiceUtil.getAccountEntries(userUuid,
					productEntryIds);

			return com.liferay.osb.model.AccountEntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.AccountEntrySoap getAccountEntry(
		long accountEntryId) throws RemoteException {
		try {
			com.liferay.osb.model.AccountEntry returnValue = AccountEntryServiceUtil.getAccountEntry(accountEntryId);

			return com.liferay.osb.model.AccountEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.AccountEntrySoap getAccountEntryByCode(
		java.lang.String code) throws RemoteException {
		try {
			com.liferay.osb.model.AccountEntry returnValue = AccountEntryServiceUtil.getAccountEntryByCode(code);

			return com.liferay.osb.model.AccountEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.AccountEntrySoap getCorpProjectAccountEntry(
		java.lang.String corpProjectUuid) throws RemoteException {
		try {
			com.liferay.osb.model.AccountEntry returnValue = AccountEntryServiceUtil.getCorpProjectAccountEntry(corpProjectUuid);

			return com.liferay.osb.model.AccountEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.AccountEntrySoap[] getSecurityPatchAccountEntries(
		java.lang.String portletId) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.AccountEntry> returnValue = AccountEntryServiceUtil.getSecurityPatchAccountEntries(portletId);

			return com.liferay.osb.model.AccountEntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.AccountEntrySoap updateInstructions(
		long accountEntryId, java.lang.String instructions)
		throws RemoteException {
		try {
			com.liferay.osb.model.AccountEntry returnValue = AccountEntryServiceUtil.updateInstructions(accountEntryId,
					instructions);

			return com.liferay.osb.model.AccountEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AccountEntryServiceSoap.class);
}