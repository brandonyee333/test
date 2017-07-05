/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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
		StringBundler sb = new StringBundler();

		sb.append("update PortalPreferences set preferences = ");
		sb.append("replace(preferences, '<name>auto.deploy.deploy.dir</name>");
		sb.append("<value>/root/liferay/deploy</value>', '') where ");
		sb.append("preferences like '%auto.deploy.deploy.dir%'");

		runSQL(sb.toString());
	}

}