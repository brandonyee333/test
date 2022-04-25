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

package com.liferay.osb.asah.batch.curator.bot.nanite.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

/**
 * @author Rachael Koestartyo
 */
public class ExpandoValueBigQueryMergeJobConfiguration
	extends BigQueryMergeJobConfiguration {

	public ExpandoValueBigQueryMergeJobConfiguration(String projectId) {
		super(projectId);
	}

	@Override
	public List<String> getInsertFields() {
		return Arrays.asList(
			"classPK", "classType.type", "columnId", "dataSourceId",
			"id.sha256HexId", "projectId", "value");
	}

	@Override
	public Map<String, String> getJoinFields() {
		Map<String, String> joinFields = new HashMap<>();

		joinFields.put("classPK", "classPK");
		joinFields.put("columnId", "columnId");
		joinFields.put("dataSourceId", "dataSourceId");
		joinFields.put("projectId", "projectId");

		return joinFields;
	}

	@Override
	public String getReplicaTable() {
		return getProjectId() + ".expandovalue";
	}

	@Override
	public String getStagingTableSQL(String previousRunDateString) {
		Map<String, String> mergeQueryValues = new HashMap<>();

		mergeQueryValues.put(
			"organizationType", "com.liferay.portal.kernel.model.Organization");
		mergeQueryValues.put("previousRunDateString", previousRunDateString);
		mergeQueryValues.put(
			"sha256HexId", getQueryTemplate("expandoValueSHA256HexId"));
		mergeQueryValues.put("stagingTable", getProjectId() + ".dxpentity");
		mergeQueryValues.put(
			"userType", "com.liferay.portal.kernel.model.User");

		return StringSubstitutor.replace(
			getQueryTemplate("expandoValueStaging"), mergeQueryValues, "{",
			"}");
	}

	@Override
	public List<String> getUpdateFields() {
		return Collections.singletonList("value");
	}

}