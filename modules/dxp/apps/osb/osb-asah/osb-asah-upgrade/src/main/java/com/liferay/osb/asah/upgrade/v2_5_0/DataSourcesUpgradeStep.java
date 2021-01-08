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

package com.liferay.osb.asah.upgrade.v2_5_0;

import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.BaseReindexUpgradeStep;

import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class DataSourcesUpgradeStep extends BaseReindexUpgradeStep {

	public String[] getCollectionNames() {
		return new String[] {"data-sources"};
	}

	public WeDeployDataService getWeDeployDataService() {
		return WeDeployDataService.OSB_ASAH_FARO_INFO;
	}

}