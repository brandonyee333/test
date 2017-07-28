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

package com.liferay.osb.hook.upgrade.v3_6_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TrainingEventConstants;

*/

/**
 * @author Haote Chou
 */
public class Upgrade_20161102132052014_ListType extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20161102132052014L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			50000, "5.1",
			TrainingEventConstants.LIST_TYPE_PORTAL_MINOR_VERSIONS);

		insertListType(
			50001, "5.2",
			TrainingEventConstants.LIST_TYPE_PORTAL_MINOR_VERSIONS);

		insertListType(
			50002, "6.0",
			TrainingEventConstants.LIST_TYPE_PORTAL_MINOR_VERSIONS);

		insertListType(
			50003, "6.1",
			TrainingEventConstants.LIST_TYPE_PORTAL_MINOR_VERSIONS);

		insertListType(
			50004, "6.2",
			TrainingEventConstants.LIST_TYPE_PORTAL_MINOR_VERSIONS);

		insertListType(
			50005, "7.0",
			TrainingEventConstants.LIST_TYPE_PORTAL_MINOR_VERSIONS);
	}

}

*/

}