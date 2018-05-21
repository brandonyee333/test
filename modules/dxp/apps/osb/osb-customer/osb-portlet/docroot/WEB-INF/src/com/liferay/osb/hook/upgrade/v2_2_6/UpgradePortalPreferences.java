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

package com.liferay.osb.hook.upgrade.v2_2_6;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Rachael Koestartyo
 */
public class UpgradePortalPreferences extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updatePortalPreferences();
	}

	protected void updatePortalPreferences() throws Exception {
		StringBundler sb = new StringBundler(4);

		sb.append("update PortalPreferences set preferences = ");
		sb.append("replace(preferences, '<name>auto.deploy.deploy.dir</name>");
		sb.append("<value>/root/liferay/deploy</value>', '') where ");
		sb.append("preferences like '%auto.deploy.deploy.dir%'");

		runSQL(sb.toString());
	}

}