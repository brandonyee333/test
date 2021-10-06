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

package com.liferay.osb.customer.admin.internal.upgrade.v1_0_1;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Yuanyuan Huang
 */
public class UpgradeAdmin extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeListTypes();

		updateAccountEnvironments();
	}

	protected void insertListType(long listTypeId, String name, String type)
		throws Exception {

		StringBundler sb = new StringBundler(7);

		sb.append("insert into ListType (listTypeId, name, type_) values (");
		sb.append(String.valueOf(listTypeId));
		sb.append(", '");
		sb.append(name);
		sb.append("', '");
		sb.append(type);
		sb.append("')");

		runSQL(sb.toString());
	}

	protected void updateAccountEnvironments() throws Exception {
		runSQL(
			"update OSB_AccountEnvironment set envDB = 28042 where envLFR = " +
				"41300 and envDB = 28050");
	}

	protected void upgradeListTypes() throws Exception {
		insertListType(
			27072, "jboss-eap-7.4",
			"com.liferay.osb.customer.admin.model.AccountEnvironment.envAS");
		insertListType(
			27073, "wildfly-18.0",
			"com.liferay.osb.customer.admin.model.AccountEnvironment.envAS");
		insertListType(
			27074, "wildfly-23.0",
			"com.liferay.osb.customer.admin.model.AccountEnvironment.envAS");
		insertListType(
			39009, "google-cloud-filestore",
			"com.liferay.osb.customer.admin.model.AccountEnvironment.envCS");
		insertListType(
			39010, "ibm-cloud-file-storage",
			"com.liferay.osb.customer.admin.model.AccountEnvironment.envCS");
		insertListType(
			39011, "red-hat-openshift-on-ibm-cloud",
			"com.liferay.osb.customer.admin.model.AccountEnvironment.envCS");
		insertListType(
			28053, "postgresql-13",
			"com.liferay.osb.customer.admin.model.AccountEnvironment.envDB");
		insertListType(
			30055, "alpine-linux-3.13",
			"com.liferay.osb.customer.admin.model.AccountEnvironment.envOS");
		insertListType(
			30056, "oracle-linux-8",
			"com.liferay.osb.customer.admin.model.AccountEnvironment.envOS");
		insertListType(
			37025, "safari-14",
			"com.liferay.osb.customer.admin.model.AccountEnvironment." +
				"envBrowser");
		insertListType(
			44300, "4.0",
			"com.liferay.osb.customer.admin.model.ProductEntry." +
				"commerceAllVersions");
		insertListType(
			45003, "4",
			"com.liferay.osb.customer.admin.model.ProductEntry." +
				"commerceMajorVersions");
		insertListType(
			46005, "4.0",
			"com.liferay.osb.customer.admin.model.ProductEntry." +
				"commerceMinorVersions");

		runSQL("update ListType set name = 'aix-7.2' where listTypeId = 30046");
	}

}