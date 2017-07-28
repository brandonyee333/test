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

package com.liferay.osb.hook.upgrade.v3_0_5;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.util.StringBundler;

*/

/**
 * @author Ryan Park
 */
public class Upgrade_20140722091751049_CorpProject extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20140722091751049L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeCorpProject();
	}

	protected void upgradeCorpProject() throws Exception {
		StringBundler sb = new StringBundler(5);

		sb.append("update Organization_ inner join OSB_CorpProject ON ");
		sb.append("OSB_CorpProject.organizationId = ");
		sb.append("Organization_.organizationId set ");
		sb.append("Organization_.parentOrganizationId = ");
		sb.append(OSBConstants.ORGANIZATION_CORPORATION_PROJECT_PARENT_ID);

		runSQL(sb.toString());
	}

}

*/

}