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

package com.liferay.osb.hook.upgrade.v3_5_0;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AuditEntryConstants;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20160524101837798_ListType extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160524101837798L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			AuditEntryConstants.FIELD_PARTNER, "partner",
			AuditEntryConstants.LIST_TYPE_FIELD);
		insertListType(
			AuditEntryConstants.FIELD_TIER, "tier",
			AuditEntryConstants.LIST_TYPE_FIELD);
	}

}