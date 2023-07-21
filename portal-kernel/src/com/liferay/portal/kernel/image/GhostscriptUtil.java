/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.image;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Ivica Cardic
 */
public class GhostscriptUtil {

	public static Future<?> execute(List<String> arguments) throws Exception {
		return getGhostscript().execute(arguments);
	}

	public static Ghostscript getGhostscript() {
		PortalRuntimePermission.checkGetBeanProperty(GhostscriptUtil.class);

		return _ghostscript;
	}

	public static boolean isEnabled() {
		return getGhostscript().isEnabled();
	}

	public static void reset() {
		getGhostscript().reset();
	}

	public void setGhostscript(Ghostscript ghostscript) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_ghostscript = ghostscript;
	}

	private static Ghostscript _ghostscript;

}