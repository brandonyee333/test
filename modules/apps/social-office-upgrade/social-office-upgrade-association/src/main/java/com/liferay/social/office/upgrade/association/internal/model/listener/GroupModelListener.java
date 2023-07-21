/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.office.upgrade.association.internal.model.listener;

import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.social.office.upgrade.association.internal.constants.RoleConstants;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 * @author Sergio González
 */
@Component(immediate = true, service = ModelListener.class)
public class GroupModelListener extends BaseModelListener<Group> {

	@Override
	public void onAfterUpdate(Group group) throws ModelListenerException {
		try {
			setSocialOfficeEnabled(group);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void setSocialOfficeEnabled(Group group) throws Exception {
		Role role = roleLocalService.fetchRole(
			group.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER);

		if (role == null) {
			return;
		}

		boolean socialOfficeEnabled = false;

		String customJspServletContextName = GetterUtil.getString(
			group.getTypeSettingsProperty("customJspServletContextName"));

		if (customJspServletContextName.equals("so-hook")) {
			socialOfficeEnabled = true;
		}

		expandoValueLocalService.addValue(
			group.getCompanyId(), Group.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, "socialOfficeEnabled",
			group.getGroupId(), socialOfficeEnabled);
	}

	@Reference
	protected ExpandoValueLocalService expandoValueLocalService;

	@Reference
	protected RoleLocalService roleLocalService;

}