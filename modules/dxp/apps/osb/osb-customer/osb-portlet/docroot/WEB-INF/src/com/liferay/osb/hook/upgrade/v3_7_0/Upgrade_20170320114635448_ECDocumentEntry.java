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

package com.liferay.osb.hook.upgrade.v3_7_0;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Haote Chou
 */
public class Upgrade_20170320114635448_ECDocumentEntry
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20170320114635448L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateECDocumentEntry();

		updateDeveloperEntry();
	}

	protected void updateDeveloperEntry() throws Exception {
		StringBundler sb = new StringBundler(5);

		sb.append("update OSB_DeveloperEntry ");
		sb.append("set dossieraAccountKey = '");
		sb.append(OSBConstants.CORP_ENTRY_LIFERAY_INC_DOSSIERA_ACCOUNT_KEY);
		sb.append("' where classPK = ");
		sb.append(OSBConstants.CORP_ENTRY_LIFERAY_INC_ID);

		runSQL(sb.toString());
	}

	protected void updateECDocumentEntry() throws Exception {
		StringBundler sb = new StringBundler(6);

		sb.append("update ECommerce_ECDocumentEntry, OSB_DeveloperEntry ");
		sb.append("set vendorClassNameId = ");
		sb.append(PortalUtil.getClassNameId(DeveloperEntry.class.getName()));
		sb.append(", vendorClassPK = developerEntryId ");
		sb.append("where vendorClassNameId = classNameId ");
		sb.append("and vendorClassPK = classPK");

		runSQL(sb.toString());
	}

}