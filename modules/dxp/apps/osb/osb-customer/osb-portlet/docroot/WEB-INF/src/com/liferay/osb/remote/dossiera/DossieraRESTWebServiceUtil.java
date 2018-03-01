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

package com.liferay.osb.remote.dossiera;

import com.liferay.osb.exception.RemoteServiceException;
import com.liferay.portal.kernel.json.JSONArray;

/**
 * @author Amos Fong
 */
public class DossieraRESTWebServiceUtil {

	public static DossieraRESTWebService getDossieraRESTWebService() {
		return _dossieraRESTWebService;
	}

	public static JSONArray getOpportunitiesJSONArray(
			String salesforceProjectKey)
		throws RemoteServiceException {

		return getDossieraRESTWebService().getOpportunitiesJSONArray(
			salesforceProjectKey);
	}

	public void setDossieraRESTWebService(
		DossieraRESTWebService dossieraRESTWebService) {

		_dossieraRESTWebService = dossieraRESTWebService;
	}

	private static DossieraRESTWebService _dossieraRESTWebService;

}