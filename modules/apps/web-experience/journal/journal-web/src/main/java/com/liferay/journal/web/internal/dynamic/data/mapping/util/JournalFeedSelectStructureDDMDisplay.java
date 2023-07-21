/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.dynamic.data.mapping.util;

import com.liferay.dynamic.data.mapping.util.DDMDisplay;
import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.web.dynamic.data.mapping.util.JournalDDMDisplay;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.util.Portal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = "javax.portlet.name=" + JournalPortletKeys.JOURNAL + ".selectStructureFeed",
	service = DDMDisplay.class
)
public class JournalFeedSelectStructureDDMDisplay extends JournalDDMDisplay {

	@Override
	public String getPortletId() {
		return JournalPortletKeys.JOURNAL + ".selectStructureFeed";
	}

	@Override
	public boolean isShowAddButton(Group scopeGroup) {
		return false;
	}

	@Override
	public boolean isShowConfirmSelectStructure() {
		return false;
	}

	@Reference(unbind = "-")
	protected void setPortal(Portal portal) {
		this.portal = portal;
	}

}