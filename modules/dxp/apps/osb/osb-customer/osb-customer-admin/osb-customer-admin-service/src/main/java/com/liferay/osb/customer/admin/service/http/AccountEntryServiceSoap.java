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

import com.liferay.osb.customer.admin.service.AccountEntryServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>AccountEntryServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.osb.customer.admin.model.AccountEntrySoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.osb.customer.admin.model.AccountEntry</code>, that is translated to a
 * <code>com.liferay.osb.customer.admin.model.AccountEntrySoap</code>. Methods that SOAP
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
 * @see AccountEntryServiceHttp
 * @generated
 */
public class AccountEntryServiceSoap {

	public static com.liferay.osb.customer.admin.model.AccountEntrySoap
			deleteAccountEntry(long accountEntryId)
		throws RemoteException {

		try {
			com.liferay.osb.customer.admin.model.AccountEntry returnValue =
				AccountEntryServiceUtil.deleteAccountEntry(accountEntryId);

			return com.liferay.osb.customer.admin.model.AccountEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEntrySoap
			fetchCorpProjectAccountEntry(String corpProjectUuid)
		throws RemoteException {

		try {
			com.liferay.osb.customer.admin.model.AccountEntry returnValue =
				AccountEntryServiceUtil.fetchCorpProjectAccountEntry(
					corpProjectUuid);

			return com.liferay.osb.customer.admin.model.AccountEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEntrySoap[]
			getAccountEntries(String userUuid, long[] productEntryIds)
		throws RemoteException {

		try {
			java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
				returnValue = AccountEntryServiceUtil.getAccountEntries(
					userUuid, productEntryIds);

			return com.liferay.osb.customer.admin.model.AccountEntrySoap.
				toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEntrySoap
			getAccountEntry(long accountEntryId)
		throws RemoteException {

		try {
			com.liferay.osb.customer.admin.model.AccountEntry returnValue =
				AccountEntryServiceUtil.getAccountEntry(accountEntryId);

			return com.liferay.osb.customer.admin.model.AccountEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEntrySoap
			getAccountEntryByCode(String code)
		throws RemoteException {

		try {
			com.liferay.osb.customer.admin.model.AccountEntry returnValue =
				AccountEntryServiceUtil.getAccountEntryByCode(code);

			return com.liferay.osb.customer.admin.model.AccountEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEntrySoap
			getCorpProjectAccountEntry(String corpProjectUuid)
		throws RemoteException {

		try {
			com.liferay.osb.customer.admin.model.AccountEntry returnValue =
				AccountEntryServiceUtil.getCorpProjectAccountEntry(
					corpProjectUuid);

			return com.liferay.osb.customer.admin.model.AccountEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEntrySoap
			updateInstructions(long accountEntryId, String instructions)
		throws RemoteException {

		try {
			com.liferay.osb.customer.admin.model.AccountEntry returnValue =
				AccountEntryServiceUtil.updateInstructions(
					accountEntryId, instructions);

			return com.liferay.osb.customer.admin.model.AccountEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AccountEntryServiceSoap.class);

}