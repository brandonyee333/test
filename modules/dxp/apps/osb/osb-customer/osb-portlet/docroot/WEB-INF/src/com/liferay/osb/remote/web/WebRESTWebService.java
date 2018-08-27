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

package com.liferay.osb.remote.web;

import com.liferay.osb.exception.RemoteServiceException;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Amos Fong
 */
public interface WebRESTWebService {

	public void deleteCorpEntriesUser(
			String dossieraAccountKey, String userUUID)
		throws RemoteServiceException;

	public void deleteCorpEntriesUserRole(
			String dossieraAccountKey, String userUUID, String roleUUID)
		throws RemoteServiceException;

	public void deleteCorpProjectMessages(String corpProjectMessageUUID)
		throws RemoteServiceException;

	public void deleteCorpProjects(String corpProjectUUID)
		throws RemoteServiceException;

	public void deleteOrganizationsUser(
			String organizationUUID, String userUUID)
		throws RemoteServiceException;

	public void deleteRolesUser(String roleUUID, String userUUID)
		throws RemoteServiceException;

	public JSONObject getCorpEntry(String dossieraAccountKey)
		throws RemoteServiceException;

	public JSONObject getUsers(String uuid) throws RemoteServiceException;

	public JSONObject getUsersEmailAddress(String emailAddress)
		throws RemoteServiceException;

	public JSONObject postCorpProjectMessages(
			String userUUID, String corpProjectUUID, int type,
			int severityLevel, String title, String content, boolean displayCP,
			boolean displayLCS)
		throws RemoteServiceException;

	public JSONObject postCorpProjects(
			String creatorUserUUID, String ownerUserUUID,
			String dossieraProjectKey, String salesforceProjectKey, String name)
		throws RemoteServiceException;

	public void putCorpEntriesUser(String dossieraAccountKey, String userUUID)
		throws RemoteServiceException;

	public void putCorpEntriesUserRole(
			String dossieraAccountKey, String userUUID, String roleUUID)
		throws RemoteServiceException;

	public JSONObject putCorpProjects(String corpProjectUUID, String name)
		throws RemoteServiceException;

	public void putCorpProjectsUser(String corpProjectUUID, String userUUID)
		throws RemoteServiceException;

	public void putCorpProjectsUserRole(
			String corpProjectUUID, String userUUID, String roleUUID)
		throws RemoteServiceException;

	public void putOrganizationsUser(String organizationUUID, String userUUID)
		throws RemoteServiceException;

	public void putRolesUser(String roleUUID, String userUUID)
		throws RemoteServiceException;

	public void putUsersExpandoValue(
			String userUUID, String expandoTableName, String expandoColumnName,
			String data)
		throws RemoteServiceException;

}