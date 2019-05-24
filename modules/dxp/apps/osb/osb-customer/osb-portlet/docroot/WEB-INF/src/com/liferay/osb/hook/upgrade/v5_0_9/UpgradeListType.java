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

package com.liferay.osb.hook.upgrade.v5_0_9;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Jenny Chen
 */
public class UpgradeListType extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			28045, "mysql-8", "com.liferay.osb.model.AccountEnvironment.envDB");
		insertListType(
			28046, "sql-server-2017",
			"com.liferay.osb.model.AccountEnvironment.envDB");
		insertListType(
			30046, "aix-7.x", "com.liferay.osb.model.AccountEnvironment.envOS");
		insertListType(
			30047, "alpine-linux-3.8",
			"com.liferay.osb.model.AccountEnvironment.envOS");
		insertListType(
			30048, "suse-enterprise-linux-15",
			"com.liferay.osb.model.AccountEnvironment.envOS");
		insertListType(
			30049, "ubuntu-lts-18.04",
			"com.liferay.osb.model.AccountEnvironment.envOS");
		insertListType(
			30050, "windows-server-2019",
			"com.liferay.osb.model.AccountEnvironment.envOS");
		insertListType(
			37021, "chrome-65",
			"com.liferay.osb.model.AccountEnvironment.envBrowser");
		insertListType(
			37022, "safari-12",
			"com.liferay.osb.model.AccountEnvironment.envBrowser");

		insertListType(
			41200, "7.2",
			"com.liferay.osb.model.ProductEntry.digitalEnterpriseAllVersions");
		insertListType(
			43002, "7.2",
			"com.liferay.osb.model.ProductEntry." +
				"digitalEnterpriseMinorVersions");

		runSQL(
			"update ListType set name = 'ibm-j9-jdk-8' where listTypeId = " +
				"29007");
		runSQL(
			"update ListType set name = 'solr-7.x' where listTypeId = 40009");
	}

}