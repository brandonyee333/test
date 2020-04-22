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

package com.liferay.post.upgrade.fix.LPS_65975.osgi.commands;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.post.upgrade.fix.osgi.commands.BasePostUpgradeFixOSGiCommands;

import org.osgi.service.component.annotations.Component;

/**
 * @author Gregory Bretall
 */
@Component(
	immediate = true,
	property = {
		"osgi.command.function=" + PostUpgradeFixOSGiCommands.FUNCTION,
		"osgi.command.scope=" + BasePostUpgradeFixOSGiCommands.SCOPE
	},
	service = PostUpgradeFixOSGiCommands.class
)
public class PostUpgradeFixOSGiCommands extends BasePostUpgradeFixOSGiCommands {

	public static final String FUNCTION = "LPS_65975";

	public void LPS_65975() {
		execute();
	}

	@Override
	protected void doExecute() throws Exception {
		for (String[] themeIds : _THEME_IDS) {
			String oldThemeId = themeIds[0];
			String newThemeId = themeIds[1];

			for (String tableName : _TABLE_NAMES) {
				runSQL(
					StringBundler.concat(
						"update ", tableName, " set themeId = '", newThemeId,
						"' where themeId = '", oldThemeId, "'"));
			}
		}
	}

	@Override
	protected String getFunction() {
		return FUNCTION;
	}

	private static final String[] _TABLE_NAMES = {
		"Layout", "LayoutRevision", "LayoutSet", "LayoutSetBranch"
	};

	private static final String[][] _THEME_IDS = {
		{"classic", "classic_WAR_classictheme"},
		{"controlpanel", "admin_WAR_admintheme"}
	};

}