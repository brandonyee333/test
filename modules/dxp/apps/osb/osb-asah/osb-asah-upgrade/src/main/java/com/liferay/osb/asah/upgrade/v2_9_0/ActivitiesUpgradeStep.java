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

package com.liferay.osb.asah.upgrade.v2_9_0;

import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.BaseReindexUpgradeStep;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author Geyson Silva
 */
@Component
public class ActivitiesUpgradeStep extends BaseReindexUpgradeStep {

	@Override
	public String[] getCollectionNames() {
		return new String[] {"activities", "activity-groups"};
	}

	@Override
	public Map<String, Object> getParams() {
		return new HashMap<>();
	}

	@Override
	public String getScript() {
		return ScriptUtil.loadScriptSource(
			getClass(), "new-fields-activities-script.painless");
	}

	@Override
	public WeDeployDataService getWeDeployDataService() {
		return WeDeployDataService.OSB_ASAH_FARO_INFO;
	}

}