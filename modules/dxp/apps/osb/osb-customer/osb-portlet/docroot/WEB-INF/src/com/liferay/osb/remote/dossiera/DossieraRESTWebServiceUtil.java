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