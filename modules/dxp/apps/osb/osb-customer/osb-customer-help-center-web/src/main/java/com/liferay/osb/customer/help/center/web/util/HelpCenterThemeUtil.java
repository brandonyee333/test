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
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.OrganizationLocalService;

import java.util.LinkedHashMap;
import java.util.List;

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

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private boolean _hasMegaMenu(long userId) throws PortalException {
		if (_organizationLocalService.hasUserOrganization(
				userId, OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return true;
		}

		if (PartnerWorkerLocalServiceUtil.hasPartnerWorker(userId)) {
			return true;
		}

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("accountEntryMembership", Long.valueOf(userId));
		params.put("activeSupport", true);
		params.put("ticketSupport", true);

		List<AccountEntry> accountEntries = AccountEntryLocalServiceUtil.search(
			null, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (AccountEntry accountEntry : accountEntries) {
			AccountCustomer accountCustomer =
				AccountCustomerLocalServiceUtil.getAccountCustomer(
					userId, accountEntry.getAccountEntryId());

			if (accountCustomer.getRole() !=
					AccountCustomerConstants.ROLE_WATCHER) {

				return true;
			}
		}

		return false;
	}

	@Reference
	private OrganizationLocalService _organizationLocalService;

	private final PortalCache<Long, Boolean> _portalCache =
		SingleVMPoolUtil.getPortalCache(HelpCenterThemeUtil.class.getName());

}