/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.designer.web.internal.upgrade.v0_0_2;

import com.liferay.portal.workflow.kaleo.designer.web.constants.KaleoDesignerPortletKeys;

/**
 * @author Leonardo Barros
 */
public class UpgradePortletId
	extends com.liferay.portal.upgrade.util.UpgradePortletId {

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			{
				"1_WAR_kaleodesignerportlet",
				KaleoDesignerPortletKeys.KALEO_DESIGNER_LOADER
			},
			{
				"2_WAR_kaleodesignerportlet",
				KaleoDesignerPortletKeys.KALEO_DESIGNER
			}
		};
	}

}