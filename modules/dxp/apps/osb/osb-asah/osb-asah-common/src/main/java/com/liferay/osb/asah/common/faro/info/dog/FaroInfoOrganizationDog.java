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

package com.liferay.osb.asah.common.faro.info.dog;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.json.JSONUtil;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class FaroInfoOrganizationDog extends BaseFaroInfoDog {

	public JSONObject addOrganization(
			JSONObject dataJSONObject, JSONObject dataSourceJSONObject)
		throws Exception {

		JSONObject organizationJSONObject = JSONUtil.put(
			"custom",
			_faroInfoFieldDog.buildContextJSONObject(
				"custom", dataJSONObject, dataSourceJSONObject, "organization")
		).put(
			"dataSourceId", dataSourceJSONObject.getString("id")
		).put(
			"dateCreated", DateUtil.newDateString()
		).put(
			"dateModified", dataJSONObject.getString("modifiedDate")
		).put(
			"name", dataJSONObject.getString("name")
		).put(
			"nameTreePath", dataJSONObject.optString("nameTreePath")
		).put(
			"organizationPK", dataJSONObject.get("organizationId")
		).put(
			"parentName", dataJSONObject.optString("parentName")
		).put(
			"parentOrganizationPK", dataJSONObject.opt("parentOrganizationId")
		).put(
			"type", dataJSONObject.optString("type")
		);

		return _faroInfoFieldDog.addOwnerJSONObject(
			"organizations", organizationJSONObject, "organization");
	}

	public void deleteOrganization(JSONObject organizationJSONObject) {
		if (organizationJSONObject == null) {
			return;
		}

		elasticsearchInvoker.delete("organizations", organizationJSONObject);

		_faroInfoOSBAsahTaskDog.addOSBAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"dateModified", DateUtil.newDateString()
			).put(
				"removeQueryBuilder",
				QueryBuilders.termQuery(
					"referencedOrganizationIds",
					organizationJSONObject.getString("id")
				).toString()
			));
	}

	public JSONObject updateOrganization(
			JSONObject dataJSONObject, JSONObject dataSourceJSONObject,
			JSONObject organizationJSONObject)
		throws Exception {

		organizationJSONObject = organizationJSONObject.put(
			"dateModified", dataJSONObject.getString("modifiedDate")
		).put(
			"name", dataJSONObject.getString("name")
		).put(
			"nameTreePath", dataJSONObject.optString("nameTreePath")
		).put(
			"parentName", dataJSONObject.optString("parentName")
		).put(
			"parentOrganizationPK", dataJSONObject.opt("parentOrganizationId")
		).put(
			"treePath", dataJSONObject.optString("treePath")
		).put(
			"type", dataJSONObject.optString("type")
		);

		organizationJSONObject = _faroInfoFieldDog.updateContextFields(
			"custom", dataJSONObject, dataSourceJSONObject,
			organizationJSONObject, "organization", null, null);

		organizationJSONObject = elasticsearchInvoker.update(
			"organizations", organizationJSONObject);

		_faroInfoOSBAsahTaskDog.addOSBAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"dateModified", DateUtil.newDateString()
			).put(
				"removeQueryBuilder",
				QueryBuilders.termsQuery(
					"referencedOrganizationIds",
					organizationJSONObject.getString("id")
				).toString()
			));

		return organizationJSONObject;
	}

	@Autowired
	private FaroInfoFieldDog _faroInfoFieldDog;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

}