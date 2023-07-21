/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.jira.rest.connector.service.impl;

import com.liferay.osb.customer.jira.rest.connector.exception.JIRAComponentProjectException;
import com.liferay.osb.customer.jira.rest.connector.service.JIRAComponentRESTService;
import com.liferay.osb.customer.jira.rest.connector.util.JIRAHttpUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = JIRAComponentRESTService.class)
public class JIRAComponentRESTServiceImpl implements JIRAComponentRESTService {

	public JSONArray getJIRAComponents(String project) throws PortalException {
		validate(project);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		JSONArray componentJSONArray = JIRAHttpUtil.getToJSONArray(
			"/rest/api/2/project/" + project + "/components");

		for (int i = 0; i < componentJSONArray.length(); i++) {
			JSONObject componentJSONObject = componentJSONArray.getJSONObject(
				i);

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("id", componentJSONObject.getLong("id"));
			jsonObject.put("name", componentJSONObject.getString("name"));

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	protected void validate(String project) throws PortalException {
		if (Validator.isNull(project)) {
			throw new JIRAComponentProjectException();
		}
	}

}