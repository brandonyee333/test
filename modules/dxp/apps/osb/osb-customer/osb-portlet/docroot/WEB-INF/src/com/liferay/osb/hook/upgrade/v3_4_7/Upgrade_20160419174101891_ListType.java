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

package com.liferay.osb.hook.upgrade.v3_4_7;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.ProductEntry;

/**
 * @author Amos Fong
 */
public class Upgrade_20160419174101891_ListType extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160419174101891L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			20100, "7.0 DE",
			ProductEntry.class.getName() + ".portalAllVersions");
		insertListType(
			21002, "7", ProductEntry.class.getName() + ".portalMajorVersions");
	}

}