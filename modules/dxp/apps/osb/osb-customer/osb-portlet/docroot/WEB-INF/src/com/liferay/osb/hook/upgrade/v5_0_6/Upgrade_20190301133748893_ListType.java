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

package com.liferay.osb.hook.upgrade.v5_0_6;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Amos Fong
 */
public class Upgrade_20190301133748893_ListType extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			29010, "adoptopenjdk-8",
			"com.liferay.osb.model.AccountEnvironment.envJVM");
		insertListType(
			29011, "adoptopenjdk-11",
			"com.liferay.osb.model.AccountEnvironment.envJVM");
		insertListType(
			29012, "azul-zulu-jdk-8",
			"com.liferay.osb.model.AccountEnvironment.envJVM");
		insertListType(
			29013, "azul-zulu-jdk-11",
			"com.liferay.osb.model.AccountEnvironment.envJVM");

		runSQL(
			"update ListType set name = 'oracle-jdk-5' where listTypeId = " +
				"29000");
		runSQL(
			"update ListType set name = 'oracle-jdk-6' where listTypeId = " +
				"29001");
		runSQL(
			"update ListType set name = 'oracle-jdk-7' where listTypeId = " +
				"29002");
		runSQL(
			"update ListType set name = 'oracle-jdk-8' where listTypeId = " +
				"29006");
	}

}