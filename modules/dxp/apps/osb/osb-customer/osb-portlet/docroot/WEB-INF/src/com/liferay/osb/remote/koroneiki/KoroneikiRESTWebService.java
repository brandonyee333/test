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
public interface KoroneikiRESTWebService {

	public void deleteAccounts(String accountKey) throws RemoteServiceException;

	public JSONObject getAccounts(String koroneikiAccountKey)
		throws RemoteServiceException;

	public JSONObject getAccounts(
			String domain, String entityName, String entityId)
		throws RemoteServiceException;

	public JSONObject getContacts(String uuid) throws RemoteServiceException;

	public JSONObject postAccountExternalLinks(
			String accountKey, String domain, String entityName,
			String entityId)
		throws RemoteServiceException;

	public JSONObject postAccounts(String parentAccountKey, String name)
		throws RemoteServiceException;

	public JSONObject postContacts(
			String uuid, String emailAddress, String firstName,
			String middleName, String lastName, String languageId)
		throws RemoteServiceException;

	public void putAccountContactRoles(
			String accountKey, String contactUuid, String[] contactRoleKeys)
		throws RemoteServiceException;

	public void putAccountContacts(String accountKey, String[] contactUuids)
		throws RemoteServiceException;

	public JSONObject putAccounts(String accountKey, String name)
		throws RemoteServiceException;

}