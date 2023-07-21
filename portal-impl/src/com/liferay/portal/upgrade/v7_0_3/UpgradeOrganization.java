/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_0_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PropsValues;

import java.util.List;

/**
 * @author Manuel de la Peña
 */
public class UpgradeOrganization extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateOrganizationsType();
	}

	protected void updateOrganizationsType() throws Exception {
		List<String> organizationsTypes = ListUtil.toList(
			PropsValues.ORGANIZATIONS_TYPES);

		String organizationsTypesString = ListUtil.toString(
			organizationsTypes, StringPool.NULL, "', '");

		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			runSQL(
				StringBundler.concat(
					"update Organization_ set type_ = 'organization' where ",
					"type_ not in ('", organizationsTypesString, "')"));
		}
	}

}