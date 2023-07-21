/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.help.center.web.internal.util;

import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.OrganizationLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = HelpCenterThemeUtil.class)
public class HelpCenterThemeUtil {

	public void clearCache() {
		_portalCache.removeAll();
	}

	public boolean hasMegaMenu(long userId) throws PortalException {
		Boolean megaMenu = _portalCache.get(userId);

		if (megaMenu == null) {
			if (_hasMegaMenu(userId)) {
				megaMenu = true;
			}
			else {
				megaMenu = false;
			}

			_portalCache.put(userId, megaMenu);
		}

		return megaMenu;
	}

	private boolean _hasMegaMenu(long userId) throws PortalException {
		if (_organizationLocalService.hasUserOrganization(
				userId, OSBCustomerConstants.ORGANIZATION_CUSTOMER_ID)) {

			return true;
		}

		if (_organizationLocalService.hasUserOrganization(
				userId, OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return true;
		}

		if (_organizationLocalService.hasUserOrganization(
				userId, OSBCustomerConstants.ORGANIZATION_PARTNER_ID)) {

			return true;
		}

		return false;
	}

	@Reference
	private OrganizationLocalService _organizationLocalService;

	private final PortalCache<Long, Boolean> _portalCache =
		SingleVMPoolUtil.getPortalCache(HelpCenterThemeUtil.class.getName());

}