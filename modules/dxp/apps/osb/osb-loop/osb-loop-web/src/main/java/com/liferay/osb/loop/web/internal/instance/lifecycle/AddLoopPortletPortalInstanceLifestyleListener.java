/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.instance.lifecycle;

import com.liferay.osb.loop.constants.LoopPortletKeys;
import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Timothy Bell
 */
@Component(immediate = true, service = PortalInstanceLifecycleListener.class)
public class AddLoopPortletPortalInstanceLifestyleListener
	extends BasePortalInstanceLifecycleListener {

	@Override
	public void portalInstanceRegistered(Company company) {
		Group group = _groupLocalService.fetchGroup(
			company.getCompanyId(), GroupConstants.GUEST);

		if (group == null) {
			return;
		}

		List<Layout> layouts = _layoutLocalService.getLayouts(
			group.getGroupId(), false);

		Layout layout = layouts.get(0);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		if (layoutTypePortlet.hasPortletId(LoopPortletKeys.LOOP)) {
			return;
		}

		layoutTypePortlet.setLayoutTemplateId(0, "1_column", false);

		UnicodeProperties typeSettingsProperties =
			layout.getTypeSettingsProperties();

		typeSettingsProperties.setProperty("column-1", LoopPortletKeys.LOOP);

		layout.setTypeSettingsProperties(typeSettingsProperties);

		_layoutLocalService.updateLayout(layout);
	}

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

}