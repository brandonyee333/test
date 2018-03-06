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
import com.liferay.osb.remote.BaseWebService;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpMessage;

/**
 * @author Amos Fong
 */
public class WebRESTWebServiceImpl
	extends BaseWebService implements WebRESTWebService {

	@Override
	public void deleteOrganizationsUser(
			String organizationUUID, String userUUID)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("userUUID", userUUID);

		doDelete(
			_URL_API_REST_ORGANIZATIONS + organizationUUID + "/user",
			parameters);
	}

	@Override
	public void deleteRolesUser(String roleUUID, String userUUID)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("userUUID", userUUID);

		doDelete(_URL_API_REST_ROLES + roleUUID + "/user", parameters);
	}

	@Override
	public void putOrganizationsUser(String organizationUUID, String userUUID)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("userUUID", userUUID);

		doPut(
			_URL_API_REST_ORGANIZATIONS + organizationUUID + "/user",
			parameters);
	}

	@Override
	public void putRolesUser(String roleUUID, String userUUID)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("userUUID", userUUID);

		doPut(_URL_API_REST_ROLES + roleUUID + "/user", parameters);
	}

	@Override
	protected void addHeaders(
		HttpMessage httpMessage, Map<String, String> headers) {

		headers.put(
			"Authorization",
			"token " + PortletPropsValues.REMOTE_REST_SERVICE_API_WEB_TOKEN);

		super.addHeaders(httpMessage, headers);
	}

	private static final String _URL_API_REST = "/osb-entity-web";

	private static final String _URL_API_REST_ORGANIZATIONS =
		_URL_API_REST + "/organizations/";

	private static final String _URL_API_REST_ROLES = _URL_API_REST + "/roles/";

	private static final Log _log = LogFactoryUtil.getLog(
		WebRESTWebServiceImpl.class);

}