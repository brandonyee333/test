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

package com.liferay.osb.hook.upgrade.v4_0_3;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.ProductEntry;

/**
 * @author Wesley Gong
 */
public class Upgrade_20180523174852043_ListType extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			41100, "7.1",
			ProductEntry.class.getName() + ".digitalEnterpriseAllVersions");
		insertListType(
			43001, "7.1",
			ProductEntry.class.getName() + ".digitalEnterpriseMinorVersions");
		insertListType(
			27061, "jboss-eap-7.1",
			"com.liferay.osb.model.AccountEnvironment.envAS");
		insertListType(
			27062, "tcServer-4.0",
			"com.liferay.osb.model.AccountEnvironment.envAS");
		insertListType(
			27063, "tomcat-9.0",
			"com.liferay.osb.model.AccountEnvironment.envAS");
		insertListType(
			27064, "websphere-9.0",
			"com.liferay.osb.model.AccountEnvironment.envAS");
		insertListType(
			27065, "wildfly-11.0",
			"com.liferay.osb.model.AccountEnvironment.envAS");
		insertListType(
			37019, "firefox-esr-52",
			"com.liferay.osb.model.AccountEnvironment.envBrowser");
		insertListType(
			37020, "safari-11",
			"com.liferay.osb.model.AccountEnvironment.envBrowser");
		insertListType(
			28041, "amazon-aurora",
			"com.liferay.osb.model.AccountEnvironment.envDB");
		insertListType(
			28042, "db2-11.1",
			"com.liferay.osb.model.AccountEnvironment.envDB");
		insertListType(
			28043, "mariadb-10.2",
			"com.liferay.osb.model.AccountEnvironment.envDB");
		insertListType(
			28044, "postgresql-10.3",
			"com.liferay.osb.model.AccountEnvironment.envDB");
		insertListType(
			30044, "debian-9",
			"com.liferay.osb.model.AccountEnvironment.envOS");
		insertListType(
			40009, "solr-5",
			"com.liferay.osb.model.AccountEnvironment.envSearch");
	}

}