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

package com.liferay.osb.remote.koroneiki;

import com.liferay.osb.exception.RemoteServiceException;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Amos Fong
 */
public class KoroneikiRESTWebServiceUtil {

	public static void deleteAccounts(String accountKey)
		throws RemoteServiceException {

		getKoroneikiRESTWebService().deleteAccounts(accountKey);
	}

	public static JSONObject getAccounts(String koroneikiAccountKey)
		throws RemoteServiceException {

		return getKoroneikiRESTWebService().getAccounts(koroneikiAccountKey);
	}

	public static JSONObject getAccounts(
			String domain, String entityName, String entityId)
		throws RemoteServiceException {

		return getKoroneikiRESTWebService().getAccounts(
			domain, entityName, entityId);
	}

	public static JSONObject getContacts(String uuid)
		throws RemoteServiceException {

		return getKoroneikiRESTWebService().getContacts(uuid);
	}

	public static KoroneikiRESTWebService getKoroneikiRESTWebService() {
		return _koroneikiRESTWebService;
	}

	public static JSONObject postAccountExternalLinks(
			String accountKey, String domain, String entityName,
			String entityId)
		throws RemoteServiceException {

		return getKoroneikiRESTWebService().postAccountExternalLinks(
			accountKey, domain, entityName, entityId);
	}

	public static JSONObject postAccounts(String parentAccountKey, String name)
		throws RemoteServiceException {

		return getKoroneikiRESTWebService().postAccounts(
			parentAccountKey, name);
	}

	public static JSONObject postContacts(
			String uuid, String emailAddress, String firstName,
			String middleName, String lastName, String languageId)
		throws RemoteServiceException {

		return getKoroneikiRESTWebService().postContacts(
			uuid, emailAddress, firstName, middleName, lastName, languageId);
	}

	public static void putAccountContactRoles(
			String accountKey, String contactUuid, String[] contactRoleKeys)
		throws RemoteServiceException {

		getKoroneikiRESTWebService().putAccountContactRoles(
			accountKey, contactUuid, contactRoleKeys);
	}

	public static void putAccountContacts(
			String accountKey, String[] contactUuids)
		throws RemoteServiceException {

		getKoroneikiRESTWebService().putAccountContacts(
			accountKey, contactUuids);
	}

	public static JSONObject putAccounts(String accountKey, String name)
		throws RemoteServiceException {

		return getKoroneikiRESTWebService().putAccounts(accountKey, name);
	}

	public void setKoroneikiRESTWebService(
		KoroneikiRESTWebService koroneikiRESTWebService) {

		_koroneikiRESTWebService = koroneikiRESTWebService;
	}

	private static KoroneikiRESTWebService _koroneikiRESTWebService;

}