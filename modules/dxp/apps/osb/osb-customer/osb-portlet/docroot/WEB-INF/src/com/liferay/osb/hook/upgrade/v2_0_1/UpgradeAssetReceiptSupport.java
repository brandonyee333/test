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

package com.liferay.osb.hook.upgrade.v2_0_1;

import com.liferay.compat.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.osb.model.impl.AssetReceiptSupportModelImpl;

/**
 * @author Ryan Park
 */
public class UpgradeAssetReceiptSupport extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateAssetReceiptSupport();
	}

	protected void updateAssetReceiptSupport() throws Exception {
		if (hasTable("OSB_AssetReceiptSupport")) {
			return;
		}

		runSQL(AssetReceiptSupportModelImpl.TABLE_SQL_CREATE);

		runSQL("create index IX_D261FB68 on OSB_AssetReceiptSupport (uuid_)");
	}

}