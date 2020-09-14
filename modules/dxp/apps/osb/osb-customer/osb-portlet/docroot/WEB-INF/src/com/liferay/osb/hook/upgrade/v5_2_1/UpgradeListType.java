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

package com.liferay.osb.hook.upgrade.v5_2_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Jenny Chen
 */
public class UpgradeListType extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			41300, "7.3",
			"com.liferay.osb.model.ProductEntry.digitalEnterpriseAllVersions");
		insertListType(
			43003, "7.3",
			"com.liferay.osb.model.ProductEntry." +
				"digitalEnterpriseMinorVersions");

		insertListType(
			27069, "jboss-eap-7.3",
			"com.liferay.osb.model.AccountEnvironment.envAS");
		insertListType(
			27070, "wildfly-14.0",
			"com.liferay.osb.model.AccountEnvironment.envAS");
		insertListType(
			27071, "wildfly-17.0",
			"com.liferay.osb.model.AccountEnvironment.envAS");

		insertListType(
			28050, "db2-11.5",
			"com.liferay.osb.model.AccountEnvironment.envDB");
		insertListType(
			28051, "mariadb-10.4",
			"com.liferay.osb.model.AccountEnvironment.envDB");
		insertListType(
			28052, "postgresql-12",
			"com.liferay.osb.model.AccountEnvironment.envDB");

		insertListType(
			30052, "alpine-linux-3.10",
			"com.liferay.osb.model.AccountEnvironment.envOS");
		insertListType(
			30053, "centos-8",
			"com.liferay.osb.model.AccountEnvironment.envOS");
		insertListType(
			30054, "debian-10",
			"com.liferay.osb.model.AccountEnvironment.envOS");

		insertListType(
			37023, "safari-13",
			"com.liferay.osb.model.AccountEnvironment.envBrowser");
		insertListType(
			37024, "firefox-esr",
			"com.liferay.osb.model.AccountEnvironment.envBrowser");

		insertListType(
			44200, "3.0",
			"com.liferay.osb.model.ProductEntry.commerceAllVersions");
		insertListType(
			45002, "3",
			"com.liferay.osb.model.ProductEntry.commerceMajorVersions");
		insertListType(
			46004, "3.0",
			"com.liferay.osb.model.ProductEntry.commerceMinorVersions");

		runSQL(
			"update ListType set name = 'postgresql-11' where listTypeId = " +
				"28047");
	}

}