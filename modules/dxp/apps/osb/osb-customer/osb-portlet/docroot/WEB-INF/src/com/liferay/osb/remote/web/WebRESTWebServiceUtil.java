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
public class WebRESTWebServiceUtil {

	public static void deleteCorpEntriesUser(
			String dossieraAccountKey, String userUUID)
		throws RemoteServiceException {

		getWebRESTWebService().deleteCorpEntriesUser(
			dossieraAccountKey, userUUID);
	}

	public static void deleteCorpProjectMessages(String corpProjectMessageUUID)
		throws RemoteServiceException {

		getWebRESTWebService().deleteCorpProjectMessages(
			corpProjectMessageUUID);
	}

	public static void deleteCorpProjects(String corpProjectUUID)
		throws RemoteServiceException {

		getWebRESTWebService().deleteCorpProjects(corpProjectUUID);
	}

	public static void deleteOrganizationsUser(
			String organizationUUID, String userUUID)
		throws RemoteServiceException {

		getWebRESTWebService().deleteOrganizationsUser(
			organizationUUID, userUUID);
	}

	public static void deleteRolesUser(String roleUUID, String userUUID)
		throws RemoteServiceException {

		getWebRESTWebService().deleteRolesUser(roleUUID, userUUID);
	}

	public static JSONObject getUsers(String uuid)
		throws RemoteServiceException {

		return getWebRESTWebService().getUsers(uuid);
	}

	public static JSONObject getUsersEmailAddress(String emailAddress)
		throws RemoteServiceException {

		return getWebRESTWebService().getUsersEmailAddress(emailAddress);
	}

	public static WebRESTWebService getWebRESTWebService() {
		return _webRESTWebService;
	}

	public static JSONObject postCorpProjectMessages(
			String userUUID, String corpProjectUUID, int type,
			int severityLevel, String title, String content, boolean displayCP,
			boolean displayLCS)
		throws RemoteServiceException {

		return getWebRESTWebService().postCorpProjectMessages(
			userUUID, corpProjectUUID, type, severityLevel, title, content,
			displayCP, displayLCS);
	}

	public static JSONObject postCorpProjects(
			String creatorUserUUID, String ownerUserUUID,
			String dossieraProjectKey, String salesforceProjectKey, String name)
		throws RemoteServiceException {

		return getWebRESTWebService().postCorpProjects(
			creatorUserUUID, ownerUserUUID, dossieraProjectKey,
			salesforceProjectKey, name);
	}

	public static void putCorpEntriesUser(
			String dossieraAccountKey, String userUUID)
		throws RemoteServiceException {

		getWebRESTWebService().putCorpEntriesUser(dossieraAccountKey, userUUID);
	}

	public static void putCorpEntriesUserRole(
			String dossieraAccountKey, String userUUID, String roleUUID)
		throws RemoteServiceException {

		getWebRESTWebService().putCorpEntriesUserRole(
			dossieraAccountKey, userUUID, roleUUID);
	}

	public static JSONObject putCorpProjects(
			String corpProjectUUID, String name)
		throws RemoteServiceException {

		return getWebRESTWebService().putCorpProjects(corpProjectUUID, name);
	}

	public static void putCorpProjectsUser(
			String corpProjectUUID, String userUUID)
		throws RemoteServiceException {

		getWebRESTWebService().putCorpProjectsUser(corpProjectUUID, userUUID);
	}

	public static void putCorpProjectsUserRole(
			String corpProjectUUID, String userUUID, String roleUUID)
		throws RemoteServiceException {

		getWebRESTWebService().putCorpProjectsUserRole(
			corpProjectUUID, userUUID, roleUUID);
	}

	public static void putOrganizationsUser(
			String organizationUUID, String userUUID)
		throws RemoteServiceException {

		getWebRESTWebService().putOrganizationsUser(organizationUUID, userUUID);
	}

	public static void putRolesUser(String roleUUID, String userUUID)
		throws RemoteServiceException {

		getWebRESTWebService().putRolesUser(roleUUID, userUUID);
	}

	public static void putUsersExpandoValue(
			String userUUID, String expandoTableName, String expandoColumnName,
			String data)
		throws RemoteServiceException {

		getWebRESTWebService().putUsersExpandoValue(
			userUUID, expandoTableName, expandoColumnName, data);
	}

	public void setWebRESTWebService(WebRESTWebService webRESTWebService) {
		_webRESTWebService = webRESTWebService;
	}

	private static WebRESTWebService _webRESTWebService;

}