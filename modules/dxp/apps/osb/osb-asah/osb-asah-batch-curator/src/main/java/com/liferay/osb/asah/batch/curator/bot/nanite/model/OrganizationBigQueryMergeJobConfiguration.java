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
public class OrganizationBigQueryMergeJobConfiguration
	extends BigQueryMergeJobConfiguration {

	public OrganizationBigQueryMergeJobConfiguration(String projectId) {
		super(projectId);
	}

	@Override
	public List<String> getInsertFields() {
		return Arrays.asList(
			"dataSourceId", "expandoColumnIds", "id.sha256HexId",
			"modifiedDate", "name", "organizationId", "parentOrganizationId",
			"projectId", "treePath", "type.organizationType");
	}

	@Override
	public Map<String, String> getJoinFields() {
		Map<String, String> joinFields = new HashMap<>();

		joinFields.put("classPK", "organizationId");
		joinFields.put("dataSourceId", "dataSourceId");
		joinFields.put("projectId", "projectId");

		return joinFields;
	}

	@Override
	public String getReplicaTable() {
		return getProjectId() + ".organization";
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
							put("name", "STRING");
							put("organizationId", "INT64");
							put("parentOrganizationId", "INT64");
							put("treePath", "STRING");
							put("type.organizationType", "STRING");
						}
					}),
				", ", buildSelectExpandoColumnIds()),
			"com.liferay.portal.kernel.model.Organization");
	}

	@Override
	public List<String> getUpdateFields() {
		return Arrays.asList(
			"expandoColumnIds", "modifiedDate", "name", "type.organizationType",
			"parentOrganizationId", "treePath");
	}

}