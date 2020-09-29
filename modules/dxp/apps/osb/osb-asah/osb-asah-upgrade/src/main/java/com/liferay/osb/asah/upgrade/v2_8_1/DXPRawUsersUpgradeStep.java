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

package com.liferay.osb.asah.upgrade.v2_8_1;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.BaseReindexUpgradeStep;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class DXPRawUsersUpgradeStep extends BaseReindexUpgradeStep {

	@Override
	public String[] getCollectionNames() {
		return new String[] {"users"};
	}

	@Override
	public Map<String, Object> getParams() {
		return new HashMap<String, Object>() {
			{
				put(
					"fieldNames",
					new String[] {
						"ctCollectionId", "failedLoginAttempts",
						"graceLoginCount", "lastFailedLoginDate",
						"lastLoginDate", "lastLoginIP", "lockout",
						"lockoutDate", "loginDate", "loginIP", "mvccVersion",
						"reminderQueryAnswer", "reminderQueryQuestion"
					});
			}
		};
	}

	@Override
	public QueryBuilder getQueryBuilder() {
		return BoolQueryBuilderUtil.should(
			QueryBuilders.existsQuery("ctCollectionId")
		).should(
			QueryBuilders.existsQuery("failedLoginAttempts")
		).should(
			QueryBuilders.existsQuery("graceLoginCount")
		).should(
			QueryBuilders.existsQuery("lastFailedLoginDate")
		).should(
			QueryBuilders.existsQuery("lastLoginDate")
		).should(
			QueryBuilders.existsQuery("lastLoginIP")
		).should(
			QueryBuilders.existsQuery("lockout")
		).should(
			QueryBuilders.existsQuery("lockoutDate")
		).should(
			QueryBuilders.existsQuery("loginDate")
		).should(
			QueryBuilders.existsQuery("loginIP")
		).should(
			QueryBuilders.existsQuery("mvccVersion")
		).should(
			QueryBuilders.existsQuery("reminderQueryAnswer")
		).should(
			QueryBuilders.existsQuery("reminderQueryQuestion")
		);
	}

	@Override
	public String getScript() {
		return ScriptUtil.loadScriptSource(
			getClass(), "users-field-removal-script.painless");
	}

	@Override
	public WeDeployDataService getWeDeployDataService() {
		return WeDeployDataService.OSB_ASAH_DXP_RAW;
	}

}