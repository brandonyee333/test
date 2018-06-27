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

package com.liferay.osb.hook.upgrade.v3_9_5;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountEnvironmentConstants;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20180319111155800_ListType extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20180319111155800L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"update ListType set name = 'elasticsearch-2.x' where listTypeId " +
				"= " +
					AccountEnvironmentConstants.ENV_SEARCH_ELASTICSEARCH_2_X);
		runSQL(
			"update ListType set name = 'kibana-4.x' where listTypeId = " +
				AccountEnvironmentConstants.ENV_SEARCH_KIBANA_4_X);
		runSQL(
			"update ListType set name = 'marvel-2.x' where listTypeId = " +
				AccountEnvironmentConstants.ENV_SEARCH_MARVEL_2_X);

		insertListType(
			40006, "elasticsearch-6.x",
			"com.liferay.osb.model.AccountEnvironment.envSearch");
		insertListType(
			40007, "kibana-6.x",
			"com.liferay.osb.model.AccountEnvironment.envSearch");
		insertListType(
			40008, "x-pack-6.x",
			"com.liferay.osb.model.AccountEnvironment.envSearch");
	}

}