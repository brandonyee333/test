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

import com.liferay.portal.kernel.json.JSONArray;

/**
 * @author Amos Fong
 */
public class RemoteDossieraOpportunityServiceUtil {

	public static JSONArray getOpportunitiesJSONArray(
		String salesforceProjectKey) {

		return getRemoteDossieraOpportunityService().getOpportunitiesJSONArray(
			salesforceProjectKey);
	}

	public static RemoteDossieraOpportunityService
		getRemoteDossieraOpportunityService() {

		return _remoteDossieraOpportunityService;
	}

	public void setRemoteDossieraOpportunityService(
		RemoteDossieraOpportunityService remoteDossieraOpportunityService) {

		_remoteDossieraOpportunityService = remoteDossieraOpportunityService;
	}

	private static RemoteDossieraOpportunityService
		_remoteDossieraOpportunityService;

}