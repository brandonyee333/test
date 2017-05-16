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

package com.liferay.osb.customer.web.internal.upgrade.v1_0_0;

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
			"update " + tableName + " set name = " +
				"'com.liferay.osb.documentation.display' where name = " +
					"'com.liferay.osb.knowledge.base.display'");
	}

	private final ResourceActionPersistence _resourceActionPersistence;
	private final ResourcePermissionPersistence _resourcePermissionPersistence;

}