/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.internal.upgrade;

import com.liferay.mobile.device.rules.rule.group.rule.SimpleRuleHandler;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Tom Wang
 */
public class MDRRuleUpgrade extends UpgradeProcess {

	@Override
	public void doUpgrade() throws Exception {
		runSQL(
			StringBundler.concat(
				"update MDRRule set type_ = '",
				SimpleRuleHandler.class.getName(),
				"' where type_ = 'com.liferay.portal.mobile.device.rulegroup.",
				"rule.impl.SimpleRuleHandler'"));
	}

}