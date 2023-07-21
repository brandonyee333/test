/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.post.upgrade.fix.LPS_77842.osgi.commands;

import com.liferay.portal.upgrade.v7_0_6.UpgradeRepository;
import com.liferay.post.upgrade.fix.osgi.commands.BasePostUpgradeFixOSGiCommands;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amadea Fejes
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

	public static final String FUNCTION = "LPS_77842";

	public void LPS_77842() {
		execute();
	}

	@Override
	protected void doExecute() throws Exception {
		UpgradeRepository upgradeRepository = new UpgradeRepository();

		upgradeRepository.upgrade();
	}

	@Override
	protected String getFunction() {
		return FUNCTION;
	}

}