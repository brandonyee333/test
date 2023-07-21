/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.web.internal.upgrade.v1_0_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.service.persistence.ResourceActionPersistence;
import com.liferay.portal.kernel.service.persistence.ResourcePermissionPersistence;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Jenny Chen
 */
public class UpgradeResourceAction extends UpgradeProcess {

	public UpgradeResourceAction(
		ResourceActionPersistence resourceActionPersistence,
		ResourcePermissionPersistence resourcePermissionPersistence) {

		_resourceActionPersistence = resourceActionPersistence;
		_resourcePermissionPersistence = resourcePermissionPersistence;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeResourceName("ResourceAction");
		upgradeResourceName("ResourcePermission");

		_resourceActionPersistence.clearCache();
		_resourcePermissionPersistence.clearCache();
	}

	protected void upgradeResourceName(String tableName) throws Exception {
		runSQL(
			StringBundler.concat(
				"update ", tableName, " set name = ",
				"'com.liferay.osb.documentation.display' where name = ",
				"'com.liferay.osb.knowledge.base.display'"));
	}

	private final ResourceActionPersistence _resourceActionPersistence;
	private final ResourcePermissionPersistence _resourcePermissionPersistence;

}