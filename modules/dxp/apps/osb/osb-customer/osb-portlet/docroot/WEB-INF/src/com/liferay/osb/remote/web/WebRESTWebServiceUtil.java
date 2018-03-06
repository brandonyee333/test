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

/**
 * @author Amos Fong
 */
public class WebRESTWebServiceUtil {

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

	public static WebRESTWebService getWebRESTWebService() {
		return _webRESTWebService;
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