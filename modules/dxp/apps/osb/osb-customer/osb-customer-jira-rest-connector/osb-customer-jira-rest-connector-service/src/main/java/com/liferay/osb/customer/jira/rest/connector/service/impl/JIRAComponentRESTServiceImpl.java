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