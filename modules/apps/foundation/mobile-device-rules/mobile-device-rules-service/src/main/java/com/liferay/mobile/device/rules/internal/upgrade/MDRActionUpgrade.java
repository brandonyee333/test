/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.internal.upgrade;

import com.liferay.mobile.device.rules.rule.group.action.LayoutTemplateModificationActionHandler;
import com.liferay.mobile.device.rules.rule.group.action.SimpleRedirectActionHandler;
import com.liferay.mobile.device.rules.rule.group.action.SiteRedirectActionHandler;
import com.liferay.mobile.device.rules.rule.group.action.ThemeModificationActionHandler;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Tom Wang
 */
public class MDRActionUpgrade extends UpgradeProcess {

	@Override
	public void doUpgrade() throws Exception {
		_updateMDRAction(
			LayoutTemplateModificationActionHandler.class.getSimpleName(),
			LayoutTemplateModificationActionHandler.class.getName());
		_updateMDRAction(
			SimpleRedirectActionHandler.class.getSimpleName(),
			SimpleRedirectActionHandler.class.getName());
		_updateMDRAction(
			SiteRedirectActionHandler.class.getSimpleName(),
			SiteRedirectActionHandler.class.getName());
		_updateMDRAction(
			ThemeModificationActionHandler.class.getSimpleName(),
			ThemeModificationActionHandler.class.getName());
	}

	private void _updateMDRAction(String oldType, String newType)
		throws Exception {

		runSQL(
			StringBundler.concat(
				"update MDRAction set type_ = '", newType, "' where type_ = ",
				"'com.liferay.portal.mobile.device.rulegroup.action.impl.",
				oldType, "'"));
	}

}