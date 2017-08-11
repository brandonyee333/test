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

package com.liferay.osb.hook.upgrade.v3_1_6;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

*/

/**
 * @author Brent Krone-Schmidt
 */
public class Upgrade_20141217141217651_Layout extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
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

	 */

}