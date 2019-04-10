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

package com.liferay.osb.hook.upgrade.v5_0_7;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Jenny Chen
 */
public class UpgradeListType extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			29014, "red-hat-openjdk-8",
			"com.liferay.osb.model.AccountEnvironment.envJVM");
		insertListType(
			29015, "red-hat-openjdk-11",
			"com.liferay.osb.model.AccountEnvironment.envJVM");
		insertListType(
			29016, "oracle-jdk-11",
			"com.liferay.osb.model.AccountEnvironment.envJVM");
		insertListType(
			29017, "amazon-corretto-jdk-8",
			"com.liferay.osb.model.AccountEnvironment.envJVM");
		insertListType(
			29018, "amazon-corretto-jdk-11",
			"com.liferay.osb.model.AccountEnvironment.envJVM");

		insertListType(
			39007, "google-cloud-compute",
			"com.liferay.osb.model.AccountEnvironment.envCS");
		insertListType(
			39008, "google-cloud-sql",
			"com.liferay.osb.model.AccountEnvironment.envCS");

		runSQL("delete from ListType where listTypeId = 29010");
		runSQL("delete from ListType where listTypeId = 29011");
	}

}