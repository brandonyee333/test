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

package com.liferay.osb.customer.help.center.web.util;

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