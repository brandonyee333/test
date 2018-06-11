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