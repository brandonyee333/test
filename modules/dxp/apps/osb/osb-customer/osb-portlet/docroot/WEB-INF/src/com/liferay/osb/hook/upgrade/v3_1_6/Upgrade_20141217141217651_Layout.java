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

package com.liferay.osb.hook.upgrade.v3_1_6;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Brent Krone-Schmidt
 */
public class Upgrade_20141217141217651_Layout extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20141217141217651L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateLayout();
	}

	protected void updateLayout() throws Exception {
		StringBundler sb = new StringBundler(8);

		sb.append("update Layout inner join Group_ on Group_.groupId = ");
		sb.append("Layout.groupId set Layout.friendlyURL = ");
		sb.append("'/account-settings', Layout.name = '");
		sb.append("<?xml version=\\'1.0\\' encoding=\\'UTF-8\\'?><root ");
		sb.append("available-locales=\"en_US\" default-locale=\"en_US\">");
		sb.append("<Name language-id=\"en_US\">Account Settings</Name>");
		sb.append("</root>' where (Group_.classNameId = 9) and ");
		sb.append("Layout.friendlyURL = '/my-account'");

		runSQL(sb.toString());
	}

}