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
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.model.Segment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

		accountJSONObject = _fieldDog.addOwnerJSONObject(
			"accounts", accountJSONObject, "organization");

		String dateCreated = accountJSONObject.getString("dateCreated");

		_segmentDog.addSegment(
			0L, DateUtil.toUTCDate(dateCreated),
			"((dataSourceAccountPKs/accountPKs eq '" +
				accountJSONObject.getString("accountPK") + "'))",
			DateUtil.toUTCDate(dateCreated),
			"Account: " + accountJSONObject.getString("id"), "PROJECT",
			"DYNAMIC", "INACTIVE");

		_asahTaskDog.scheduleAsahTask(
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
			JSONObject dataJSONObject, DataSource dataSource)
		throws Exception {

		JSONObject contextJSONObject = _fieldDog.buildContextJSONObject(
			"organization", dataJSONObject, dataSource, "account");

		String dateString = DateUtil.newDateString();

		return addAccount(
			JSONUtil.put(
				"accountPK", dataJSONObject.getString("id")
			).put(
				"activitiesCount", 0
			).put(
				"activitiesCounts", new JSONArray()
			).put(
				"dataSourceId", String.valueOf(dataSource.getId())
			).put(
				"dateCreated", dateString
			).put(
				"dateModified", dateString
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

		Segment segment = _segmentDog.fetchSegment(
			"Account: " + accountId, "INACTIVE");

		if (segment != null) {
			_segmentDog.deleteSegment(segment.getId());
		}
		else if (_log.isWarnEnabled()) {
			_log.warn(
				"Unable to get individual segment associated with account " +
					accountId);
		}

		elasticsearchInvoker.delete("accounts", accountId);

		_asahTaskDog.scheduleAsahTask(
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

	public JSONObject getAccountJSONObject(String accountId) {
		return elasticsearchInvoker.get("accounts", accountId);
	}

	public JSONObject replaceAccount(JSONObject accountJSONObject) {
		accountJSONObject = elasticsearchInvoker.replace(
			"accounts", accountJSONObject);

		_asahTaskDog.scheduleAsahTask(
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
			DataSource dataSource)
		throws Exception {

		return replaceAccount(
			_fieldDog.updateContextFields(
				"organization", dataJSONObject, dataSource, accountJSONObject,
				"account", null, null));
	}

	public JSONObject updateAccount(
		String accountId, JSONObject partialAccountJSONObject) {

		JSONObject accountJSONObject = elasticsearchInvoker.update(
			"accounts", accountId, partialAccountJSONObject);

		_asahTaskDog.scheduleAsahTask(
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
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private FieldDog _fieldDog;

	@Autowired
	private SegmentDog _segmentDog;

}