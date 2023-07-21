/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v6_1_0;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.counter.kernel.service.CounterLocalServiceWrapper;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.upgrade.BaseUpgradeAdminPortlets;
import com.liferay.portal.spring.aop.ServiceWrapperProxyUtil;

import java.io.Closeable;

/**
 * @author Juan Fernández
 */
public class UpgradeAdminPortlets extends BaseUpgradeAdminPortlets {

	@Override
	protected void doUpgrade() throws Exception {
		try (Closeable closeable = ServiceWrapperProxyUtil.createProxy(
				PortalBeanLocatorUtil.locate(
					CounterLocalService.class.getName()),
				Pre7CounterLocalServiceImpl.class)) {

			updateAccessInControlPanelPermission("19", "162");
			updateAccessInControlPanelPermission("33", "161");
		}
	}

	private static class Pre7CounterLocalServiceImpl
		extends CounterLocalServiceWrapper {

		@Override
		public long increment(String name) {
			if (name.equals(ResourcePermission.class.getName())) {
				name = "com.liferay.portal.model.ResourcePermission";
			}

			return super.increment(name);
		}

		private Pre7CounterLocalServiceImpl(
			CounterLocalService counterLocalService) {

			super(counterLocalService);
		}

	}

}