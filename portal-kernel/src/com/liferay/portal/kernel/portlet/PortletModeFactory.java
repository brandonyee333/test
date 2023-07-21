/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletMode;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletModeFactory {

	public static PortletMode getPortletMode(String name) {
		return _instance._getPortletMode(name);
	}

	private PortletModeFactory() {
		_portletModes = new HashMap<>();

		_portletModes.put(_ABOUT, LiferayPortletMode.ABOUT);
		_portletModes.put(_CONFIG, LiferayPortletMode.CONFIG);
		_portletModes.put(_EDIT, LiferayPortletMode.EDIT);
		_portletModes.put(_EDIT_DEFAULTS, LiferayPortletMode.EDIT_DEFAULTS);
		_portletModes.put(_EDIT_GUEST, LiferayPortletMode.EDIT_GUEST);
		_portletModes.put(_HELP, LiferayPortletMode.HELP);
		_portletModes.put(_PREVIEW, LiferayPortletMode.PREVIEW);
		_portletModes.put(_PRINT, LiferayPortletMode.PRINT);
		_portletModes.put(_VIEW, LiferayPortletMode.VIEW);
	}

	private PortletMode _getPortletMode(String name) {
		if (Validator.isNull(name)) {
			return PortletMode.VIEW;
		}

		PortletMode portletMode = _portletModes.get(name);

		if (portletMode == null) {
			portletMode = new PortletMode(name);
		}

		return portletMode;
	}

	private static final String _ABOUT = LiferayPortletMode.ABOUT.toString();

	private static final String _CONFIG = LiferayPortletMode.CONFIG.toString();

	private static final String _EDIT = PortletMode.EDIT.toString();

	private static final String _EDIT_DEFAULTS =
		LiferayPortletMode.EDIT_DEFAULTS.toString();

	private static final String _EDIT_GUEST =
		LiferayPortletMode.EDIT_GUEST.toString();

	private static final String _HELP = PortletMode.HELP.toString();

	private static final String _PREVIEW =
		LiferayPortletMode.PREVIEW.toString();

	private static final String _PRINT = LiferayPortletMode.PRINT.toString();

	private static final String _VIEW = PortletMode.VIEW.toString();

	private static final PortletModeFactory _instance =
		new PortletModeFactory();

	private final Map<String, PortletMode> _portletModes;

}