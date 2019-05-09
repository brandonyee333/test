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

package com.liferay.osb.hook.upgrade.v5_0_8;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Jenny Chen
 */
public class UpgradeRole extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"update Role_ set name = 'Customer - Liferay Commerce Connector " +
				"to Salesforce' where roleId = 3525738");
		runSQL(
			"update Organization_ set name = 'Customer - Liferay Commerce " +
				"Connector to Salesforce' where organizationId = 3510990");
	}

}