/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.instance.lifecycle;

import com.liferay.osb.loop.service.LoopPersonLocalService;
import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Timothy Bell
 */
@Component(immediate = true, service = PortalInstanceLifecycleListener.class)
public class AddLoopPeoplePortalInstanceLifestyleListener
	extends BasePortalInstanceLifecycleListener {

	@Override
	public void portalInstanceRegistered(Company company) throws Exception {
		User defaultUser = _userLocalService.getDefaultUser(
			company.getCompanyId());

		Role role = _roleLocalService.getRole(
			company.getCompanyId(), RoleConstants.ADMINISTRATOR);

		long[] userIds = _userLocalService.getRoleUserIds(role.getRoleId());

		for (long userId : userIds) {
			if (_loopPersonLocalService.hasLoopPerson(userId)) {
				continue;
			}

			_loopPersonLocalService.addLoopPerson(
				defaultUser.getUserId(), userId);
		}
	}

	@Reference
	private LoopPersonLocalService _loopPersonLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}