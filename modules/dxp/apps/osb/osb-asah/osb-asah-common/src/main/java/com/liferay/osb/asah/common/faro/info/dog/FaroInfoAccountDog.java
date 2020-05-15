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
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class FaroInfoAccountDog extends BaseFaroInfoDog {

	public JSONObject addAccount(JSONObject accountJSONObject)
		throws Exception {

		accountJSONObject = _faroInfoFieldDog.addOwnerJSONObject(
			"accounts", accountJSONObject, "organization");

		String dateCreated = accountJSONObject.getString("dateCreated");

		_faroInfoIndividualSegmentDog.addIndividualSegment(
			JSONUtil.put(
				"activitiesCount", 0
			).put(
				"dateCreated", dateCreated
			).put(
				"dateModified", dateCreated
			).put(
				"filter",
				"((dataSourceAccountPKs/accountPKs eq '" +
					accountJSONObject.getString("accountPK") + "'))"
			).put(
				"name", "Account: " + accountJSONObject.getString("id")
			).put(
				"scope", "PROJECT"
			).put(
				"segmentType", "DYNAMIC"
			).put(
				"status", "INACTIVE"
			));

		_faroInfoOSBAsahTaskDog.addOSBAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"addFilter",
				"contains(filter, 'accounts.filter(') or contains(filter, " +
					"'accounts.filterByCount(')"
			).put(
				"dateModified", dateCreated
			).put(
				"removeFilter", "contains(filter, 'accounts.filterByCount(')"
			));

		return accountJSONObject;
	}

	public JSONObject addAccount(
			JSONObject dataJSONObject, JSONObject dataSourceJSONObject)
		throws Exception {

		JSONObject contextJSONObject = _faroInfoFieldDog.buildContextJSONObject(
			"organization", dataJSONObject, dataSourceJSONObject, "account");

		String dateString = DateUtil.newDateString();

		return addAccount(
			JSONUtil.put(
				"accountPK", dataJSONObject.getString("id")
			).put(
				"activitiesCount", 0
			).put(
				"activitiesCounts", new JSONArray()
			).put(
				"dataSourceId", dataSourceJSONObject.getString("id")
			).put(
				"dateCreated", dateString
			).put(
				"dateModified", dateString
			).put(
				"engagementScore", 0
			).put(
				"individualCount", 0
			).put(
				"individualCounts", new JSONArray()
			).put(
				"organization", contextJSONObject
			));
	}

	public void deleteAccount(JSONObject accountJSONObject) {
		String accountId = accountJSONObject.getString("id");

		JSONObject individualSegmentJSONObject = elasticsearchInvoker.fetch(
			"individual-segments",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("name", "Account: " + accountId)
			).filter(
				QueryBuilders.termQuery("status", "INACTIVE")
			));

		if (individualSegmentJSONObject != null) {
			String individualSegmentId = individualSegmentJSONObject.getString(
				"id");

			elasticsearchInvoker.delete(
				"individual-segments", individualSegmentId);

			_faroInfoOSBAsahTaskDog.addOSBAsahTask(
				"DeleteIndividualSegmentTasksNanite",
				JSONUtil.put("individualSegmentId", individualSegmentId));
		}
		else if (_log.isWarnEnabled()) {
			_log.warn(
				"Unable to get individual segment associated with account " +
					accountId);
		}

		elasticsearchInvoker.delete("accounts", accountId);

		_faroInfoOSBAsahTaskDog.addOSBAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"addFilter", "contains(filter, 'accounts.filterByCount(')"
			).put(
				"dateModified", DateUtil.newDateString()
			).put(
				"removeFilter",
				"contains(filter, 'accounts.filter(') or contains(filter, " +
					"'accounts.filterByCount(')"
			));
	}

	public JSONObject replaceAccount(JSONObject accountJSONObject) {
		accountJSONObject = elasticsearchInvoker.replace(
			"accounts", accountJSONObject);

		_faroInfoOSBAsahTaskDog.addOSBAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"addFilter",
				"contains(filter, 'accounts.filter(') or contains(filter, " +
					"'accounts.filterByCount(')"
			).put(
				"dateModified", DateUtil.newDateString()
			).put(
				"removeFilter",
				"contains(filter, 'accounts.filter(') or contains(filter, " +
					"'accounts.filterByCount(')"
			));

		return accountJSONObject;
	}

	public JSONObject updateAccount(
			JSONObject accountJSONObject, JSONObject dataJSONObject,
			JSONObject dataSourceJSONObject)
		throws Exception {

		return replaceAccount(
			_faroInfoFieldDog.updateContextFields(
				"organization", dataJSONObject, dataSourceJSONObject,
				accountJSONObject, "account", null, null));
	}

	public JSONObject updateAccount(
		String accountId, JSONObject partialAccountJSONObject) {

		JSONObject accountJSONObject = elasticsearchInvoker.update(
			"accounts", accountId, partialAccountJSONObject);

		_faroInfoOSBAsahTaskDog.addOSBAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"addFilter",
				"contains(filter, 'accounts.filter(') or contains(filter, " +
					"'accounts.filterByCount(')"
			).put(
				"dateModified", DateUtil.newDateString()
			).put(
				"removeFilter",
				"contains(filter, 'accounts.filter(') or contains(filter, " +
					"'accounts.filterByCount(')"
			));

		return accountJSONObject;
	}

	private static final Log _log = LogFactory.getLog(FaroInfoAccountDog.class);

	@Autowired
	private FaroInfoFieldDog _faroInfoFieldDog;

	@Autowired
	private FaroInfoIndividualSegmentDog _faroInfoIndividualSegmentDog;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

}