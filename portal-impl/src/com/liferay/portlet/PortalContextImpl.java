/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.ReleaseInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.portlet.PortalContext;
import javax.portlet.PortletMode;
import javax.portlet.WindowState;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalContextImpl implements PortalContext {

	public static List<PortletMode> portletModes = new ArrayList<>();
	public static Properties properties = new Properties();
	public static List<WindowState> windowStates = new ArrayList<>();

	public static boolean isSupportedPortletMode(PortletMode portletMode) {
		return _portletModes.contains(portletMode);
	}

	public static boolean isSupportedWindowState(WindowState windowState) {
		return _windowStates.contains(windowState);
	}

	@Override
	public String getPortalInfo() {
		return ReleaseInfo.getReleaseInfo();
	}

	@Override
	public String getProperty(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		return properties.getProperty(name);
	}

	@Override
	public Enumeration<String> getPropertyNames() {
		return (Enumeration<String>)properties.propertyNames();
	}

	@Override
	public Enumeration<PortletMode> getSupportedPortletModes() {
		return Collections.enumeration(_portletModes);
	}

	@Override
	public Enumeration<WindowState> getSupportedWindowStates() {
		return Collections.enumeration(_windowStates);
	}

	private static final List<PortletMode> _portletModes = new ArrayList<>();
	private static final Set<WindowState> _windowStates = new HashSet<>();

	static {
		properties.setProperty(
			MARKUP_HEAD_ELEMENT_SUPPORT, Boolean.TRUE.toString());

		portletModes.add(PortletMode.EDIT);
		portletModes.add(PortletMode.HELP);
		portletModes.add(PortletMode.VIEW);
		portletModes.add(LiferayPortletMode.ABOUT);
		portletModes.add(LiferayPortletMode.CONFIG);
		portletModes.add(LiferayPortletMode.EDIT_DEFAULTS);
		portletModes.add(LiferayPortletMode.PREVIEW);
		portletModes.add(LiferayPortletMode.PRINT);

		_portletModes.addAll(portletModes);

		windowStates.add(WindowState.MAXIMIZED);
		windowStates.add(WindowState.MINIMIZED);
		windowStates.add(WindowState.NORMAL);
		windowStates.add(LiferayWindowState.EXCLUSIVE);
		windowStates.add(LiferayWindowState.POP_UP);

		_windowStates.addAll(windowStates);
	}

}