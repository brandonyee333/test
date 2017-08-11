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

package com.liferay.osb.hook.upgrade.v3_4_7;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.service.PortalReleaseLocalServiceUtil;

*/

/**
 * @author Yury Butrymovich
 */
public class Upgrade_20160414080234954_PortalRelease extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20160414080234954L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		PortalReleaseLocalServiceUtil.addPortalRelease(
			"Liferay Portal 6.1 CE GA2", 6101, false, true, false, false,
			false);
		PortalReleaseLocalServiceUtil.addPortalRelease(
			"Liferay Portal 6.1 CE GA3", 6102, false, true, false, true, false);
		PortalReleaseLocalServiceUtil.addPortalRelease(
			"Liferay Portal 6.1 EE GA2", 6120, true, true, false, false, false);
		PortalReleaseLocalServiceUtil.addPortalRelease(
			"Liferay Portal 6.1 EE GA3", 6130, true, true, false, true, false);
		PortalReleaseLocalServiceUtil.addPortalRelease(
			"Liferay Portal 6.2 CE GA1", 6200, false, true, true, true, false);
		PortalReleaseLocalServiceUtil.addPortalRelease(
			"Liferay Portal 6.2 CE GA2", 6201, false, true, true, true, false);
		PortalReleaseLocalServiceUtil.addPortalRelease(
			"Liferay Portal 6.2 CE GA3", 6202, false, true, true, true, false);
		PortalReleaseLocalServiceUtil.addPortalRelease(
			"Liferay Portal 6.2 CE GA4", 6203, false, true, true, true, false);
		PortalReleaseLocalServiceUtil.addPortalRelease(
			"Liferay Portal 6.2 CE GA5", 6204, false, true, true, true, false);
		PortalReleaseLocalServiceUtil.addPortalRelease(
			"Liferay Portal 6.2 CE GA6", 6205, false, true, true, true, false);
		PortalReleaseLocalServiceUtil.addPortalRelease(
			"Liferay Portal 6.2 EE GA1", 6210, true, true, true, true, false);
		PortalReleaseLocalServiceUtil.addPortalRelease(
			"Liferay CE Portal 7.0 GA1", 7000, false, true, true, true, false);
		PortalReleaseLocalServiceUtil.addPortalRelease(
			"Liferay DXP Digital Enterprise 7.0", 7010, false, true, true, true,
			true);
	}

	 */

}