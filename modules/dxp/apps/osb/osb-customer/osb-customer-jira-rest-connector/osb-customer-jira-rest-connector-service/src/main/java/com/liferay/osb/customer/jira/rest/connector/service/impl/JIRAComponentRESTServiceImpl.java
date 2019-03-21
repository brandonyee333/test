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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = JIRAComponentRESTService.class)
public class JIRAComponentRESTServiceImpl implements JIRAComponentRESTService {

	public JSONArray getJIRAComponents(String project) throws PortalException {
		validate(project);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		String endpoint =
			"/rest/net.brokenbuild.subcomponents/latest/subcomponents/" +
				project;

		String refType = "\"refType\":\"COMPONENT\",";

		JSONArray subcomponentJSONArray = JIRAHttpUtil.get(endpoint, refType);

		List<Long> componentIds = new ArrayList<>();

		for (int i = 0; i < subcomponentJSONArray.length(); i++) {
			JSONObject parentJSONObject =
				subcomponentJSONArray.getJSONObject(i);

			Long id = parentJSONObject.getLong("refId");
			String name = parentJSONObject.getString("name");

			componentIds.add(id);

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("name", name);
			jsonObject.put("value", id);

			jsonArray.put(jsonObject);

			JSONArray childJSONArray = parentJSONObject.getJSONArray(
				"children");

			if (childJSONArray != null) {
				componentIds = getJIRASubcomponentIds(
					componentIds, childJSONArray);
			}
		}

		endpoint = "/rest/api/2/project/" + project + "/components";

		JSONArray componentJSONArray = JIRAHttpUtil.get(
			endpoint, StringPool.BLANK);

		for (int i = 0; i < componentJSONArray.length(); i++) {
			JSONObject componentJSONObject =
				componentJSONArray.getJSONObject(i);

			String stringId = componentJSONObject.getString("id");

			Long id = Long.valueOf(stringId);

			if (!componentIds.contains(id)) {
				String name = componentJSONObject.getString("name");

				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put("name", name);
				jsonObject.put("value", id);

				jsonArray.put(jsonObject);
			}
		}

		return jsonArray;
	}

	protected List<Long> getJIRASubcomponentIds(
		List<Long> componentIds, JSONArray jsonArray) {

		if (componentIds == null) {
			componentIds = new ArrayList<>();
		}

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			componentIds.add(jsonObject.getLong("refId"));

			JSONArray childJSONArray = jsonObject.getJSONArray("children");

			if (childJSONArray != null) {
				componentIds =
					getJIRASubcomponentIds(componentIds, childJSONArray);
			}
		}

		return componentIds;
	}

	protected void validate(String project) throws PortalException {
		if (Validator.isNull(project)) {
			throw new JIRAComponentProjectException();
		}
	}

}