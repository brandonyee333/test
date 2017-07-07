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

package com.liferay.osb.hook.upgrade.v3_0_6;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.model.Organization;

*/

/**
 * @author Joan Kim
 */
public class Upgrade_20140722163425757_CorpEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20140722163425757L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeCorpEntry();
		upgradeCorpProject();
	}

	protected void upgradeCorpEntry() throws Exception {
		StringBundler sb = new StringBundler(6);

		sb.append("update Organization_ inner join OSB_CorpEntry on ");
		sb.append("OSB_CorpEntry.organizationId = ");
		sb.append("Organization_.organizationId set Organization_.name = ");
		sb.append("replace(Organization_.name, 'MP', 'CorpEntry') ");
		sb.append("where Organization_.parentOrganizationId = ");
		sb.append(OSBConstants.ORGANIZATION_CORPORATION_PARENT_ID);

		runSQL(sb.toString());

		sb = new StringBundler(10);

		sb.append("update Group_ inner join OSB_CorpEntry on ");
		sb.append("OSB_CorpEntry.organizationId = ");
		sb.append("Group_.classPK and Group_.classNameId = ");
		sb.append(PortalUtil.getClassNameId(Organization.class));
		sb.append(" inner join Organization_ on ");
		sb.append("Organization_.organizationId = ");
		sb.append("OSB_CorpEntry.organizationId ");
		sb.append("set Group_.name = replace(Group_.name, 'MP', 'CorpEntry') ");
		sb.append("where Organization_.parentOrganizationId = ");
		sb.append(OSBConstants.ORGANIZATION_CORPORATION_PARENT_ID);

		runSQL(sb.toString());
	}

	protected void upgradeCorpProject() throws Exception {
		StringBundler sb = new StringBundler(10);

		sb.append("update Group_ inner join OSB_CorpProject on ");
		sb.append("OSB_CorpProject.organizationId = ");
		sb.append("Group_.classPK and Group_.classNameId = ");
		sb.append(PortalUtil.getClassNameId(Organization.class));
		sb.append(" inner join Organization_ on ");
		sb.append("Organization_.organizationId = ");
		sb.append("OSB_CorpProject.organizationId ");
		sb.append("set Group_.name = replace(Group_.name, 'MP', 'CorpEntry') ");
		sb.append("where Organization_.parentOrganizationId = ");
		sb.append(OSBConstants.ORGANIZATION_CORPORATION_PROJECT_PARENT_ID);

		runSQL(sb.toString());
	}

}
*/

}