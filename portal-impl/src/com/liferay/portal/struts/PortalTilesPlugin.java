/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.struts;

import org.apache.struts.config.ModuleConfig;
import org.apache.struts.tiles.TilesPlugin;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalTilesPlugin extends TilesPlugin {

	@Override
	protected void initRequestProcessorClass(ModuleConfig moduleConfig) {
	}

}