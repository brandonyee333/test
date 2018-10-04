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

package com.liferay.osb.customer.downloads.display.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.osb.customer.downloads.display.web.internal.constants.DownloadsDisplayPortletKeys;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.util.PortletCategoryKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=1000",
		"panel.category.key=" + PortletCategoryKeys.CONFIGURATION
	},
	service = PanelApp.class
)
public class DownloadsMigrationPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return DownloadsDisplayPortletKeys.DOWNLOADS_MIGRATION;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + DownloadsDisplayPortletKeys.DOWNLOADS_MIGRATION + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}