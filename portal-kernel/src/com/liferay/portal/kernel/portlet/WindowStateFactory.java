/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.WindowState;

/**
 * @author Brian Wing Shun Chan
 */
public class WindowStateFactory {

	public static WindowState getWindowState(String name) {
		return _instance._getWindowState(name);
	}

	private WindowStateFactory() {
		_windowStates = new HashMap<>();

		_windowStates.put(_EXCLUSIVE, LiferayWindowState.EXCLUSIVE);
		_windowStates.put(_MAXIMIZED, LiferayWindowState.MAXIMIZED);
		_windowStates.put(_MINIMIZED, LiferayWindowState.MINIMIZED);
		_windowStates.put(_NORMAL, LiferayWindowState.NORMAL);
		_windowStates.put(_POP_UP, LiferayWindowState.POP_UP);
	}

	private WindowState _getWindowState(String name) {
		if (Validator.isNull(name)) {
			return WindowState.NORMAL;
		}

		WindowState windowState = _windowStates.get(name);

		if (windowState == null) {
			windowState = new WindowState(name);
		}

		return windowState;
	}

	private static final String _EXCLUSIVE =
		LiferayWindowState.EXCLUSIVE.toString();

	private static final String _MAXIMIZED = WindowState.MAXIMIZED.toString();

	private static final String _MINIMIZED = WindowState.MINIMIZED.toString();

	private static final String _NORMAL = WindowState.NORMAL.toString();

	private static final String _POP_UP = LiferayWindowState.POP_UP.toString();

	private static final WindowStateFactory _instance =
		new WindowStateFactory();

	private final Map<String, WindowState> _windowStates;

}