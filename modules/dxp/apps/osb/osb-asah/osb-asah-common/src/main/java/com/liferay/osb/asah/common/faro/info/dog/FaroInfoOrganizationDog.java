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
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.FieldDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DataSource;

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
			JSONObject dataJSONObject, DataSource dataSource)
		throws Exception {

		JSONObject organizationJSONObject = JSONUtil.put(
			"custom",
			_fieldDog.buildContextJSONObject(
				"custom", dataJSONObject, dataSource, "organization")
		).put(
			"dataSourceId", String.valueOf(dataSource.getId())
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

		return _fieldDog.addOwnerJSONObject(
			"organizations", organizationJSONObject, "organization");
	}

	public void deleteOrganization(JSONObject organizationJSONObject) {
		if (organizationJSONObject == null) {
			return;
		}

		elasticsearchInvoker.delete("organizations", organizationJSONObject);

		_asahTaskDog.scheduleAsahTask(
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
			JSONObject dataJSONObject, DataSource dataSource,
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

		organizationJSONObject = _fieldDog.updateContextFields(
			"custom", dataJSONObject, dataSource, organizationJSONObject,
			"organization", null, null);

		organizationJSONObject = elasticsearchInvoker.update(
			"organizations", organizationJSONObject);

		_asahTaskDog.scheduleAsahTask(
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
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private FieldDog _fieldDog;

}