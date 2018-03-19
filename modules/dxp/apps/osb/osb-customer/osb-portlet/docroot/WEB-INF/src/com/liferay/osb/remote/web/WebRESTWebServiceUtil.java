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

package com.liferay.osb.remote.web;

import com.liferay.osb.exception.RemoteServiceException;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Amos Fong
 */
public class WebRESTWebServiceUtil {

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
			boolean displayLCS, boolean displayLESA)
		throws RemoteServiceException {

		return getWebRESTWebService().postCorpProjectMessages(
			userUUID, corpProjectUUID, type, severityLevel, title, content,
			displayCP, displayLCS, displayLESA);
	}

	public static JSONObject postCorpProjects(
			String creatorUserUUID, String ownerUserUUID,
			String dossieraProjectKey, String salesforceProjectKey, String name)
		throws RemoteServiceException {

		return getWebRESTWebService().postCorpProjects(
			creatorUserUUID, ownerUserUUID, dossieraProjectKey,
			salesforceProjectKey, name);
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

	public void setWebRESTWebService(WebRESTWebService webRESTWebService) {
		_webRESTWebService = webRESTWebService;
	}

	private static WebRESTWebService _webRESTWebService;

}