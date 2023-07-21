/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.web.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Jenny Chen
 */
public class UpgradeZendeskCategory extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"alter table OSBCustomer_ZendeskCategory add column " +
				"remoteUserSegmentId LONG");
	}

}