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
public class UserBigQueryMergeJobConfiguration
	extends BigQueryMergeJobConfiguration {

	public UserBigQueryMergeJobConfiguration(String projectId) {
		super(projectId);
	}

	@Override
	public List<String> getInsertFields() {
		return Arrays.asList(
			"accountId", "birthday", "classNameId", "classPK", "contactId",
			"createDate", "dataSourceId", "emailAddress", "expandoColumnIds",
			"firstName", "groupIds", "id.sha256HexId", "jobTitle", "languageId",
			"lastName", "male", "middleName", "modifiedDate", "organizationIds",
			"parentContactId", "prefixId", "projectId", "roleIds", "screenName",
			"suffixId", "teamIds", "timeZoneId", "userGroupIds", "userId",
			"userName", "uuid");
	}

	@Override
	public Map<String, String> getJoinFields() {
		Map<String, String> joinFields = new HashMap<>();

		joinFields.put("classPK", "userId");
		joinFields.put("dataSourceId", "dataSourceId");
		joinFields.put("projectId", "projectId");

		return joinFields;
	}

	@Override
	public String getReplicaTable() {
		return getProjectId() + ".user";
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
							put("accountId", "INT64");
							put("birthday", "TIMESTAMP");
							put("classNameId", "INT64");
							put("contactId", "INT64");
							put("createDate", "TIMESTAMP");
							put("emailAddress", "STRING");
							put("firstName", "STRING");
							put("groupIds", "REPEATED");
							put("jobTitle", "STRING");
							put("languageId", "STRING");
							put("lastName", "STRING");
							put("male", "BOOL");
							put("middleName", "STRING");
							put("organizationIds", "REPEATED");
							put("parentContactId", "INT64");
							put("prefixId", "STRING");
							put("roleIds", "REPEATED");
							put("screenName", "STRING");
							put("suffixId", "STRING");
							put("teamIds", "REPEATED");
							put("timeZoneId", "STRING");
							put("userGroupIds", "REPEATED");
							put("userId", "INT64");
							put("userName", "STRING");
							put("uuid", "STRING");
						}
					}),
				", ", buildSelectExpandoColumnIds()),
			"com.liferay.portal.kernel.model.User");
	}

	@Override
	public List<String> getUpdateFields() {
		return Arrays.asList(
			"accountId", "birthday", "emailAddress", "expandoColumnIds",
			"firstName", "groupIds", "jobTitle", "languageId", "lastName",
			"male", "middleName", "modifiedDate", "organizationIds",
			"parentContactId", "prefixId", "roleIds", "screenName", "suffixId",
			"teamIds", "timeZoneId", "userGroupIds", "userName");
	}

}