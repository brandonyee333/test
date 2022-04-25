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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rachael Koestartyo
 */
public class TeamBigQueryMergeJobConfiguration
	extends BigQueryMergeJobConfiguration {

	public TeamBigQueryMergeJobConfiguration(String projectId) {
		super(projectId);
	}

	@Override
	public List<String> getInsertFields() {
		return Arrays.asList(
			"dataSourceId", "groupId", "id.sha256HexId", "modifiedDate", "name",
			"projectId", "teamId");
	}

	@Override
	public Map<String, String> getJoinFields() {
		Map<String, String> joinFields = new HashMap<>();

		joinFields.put("classPK", "teamId");
		joinFields.put("dataSourceId", "dataSourceId");
		joinFields.put("projectId", "projectId");

		return joinFields;
	}

	@Override
	public String getReplicaTable() {
		return getProjectId() + ".team";
	}

	@Override
	public String getStagingTableSQL(String previousRunDateString) {
		return buildStagingTableSQL(
			previousRunDateString,
			String.join(
				"", "*, ",
				buildSelectFields(
					new HashMap<String, String>() {
						{
							put("groupId", "INT64");
							put("name", "STRING");
							put("teamId", "INT64");
						}
					})),
			"com.liferay.portal.kernel.model.Team");
	}

	@Override
	public List<String> getUpdateFields() {
		return Arrays.asList("groupId", "modifiedDate", "name");
	}

}