/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.advisor.answer;

import javax.portlet.PortletPreferences;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * @author Igor Beslic
 */
public class LCSPortletPreferencesUtilAnswer implements Answer {

	public LCSPortletPreferencesUtilAnswer(
		PortletPreferences portletPreferences) {

		_portletPreferences = portletPreferences;
	}

	@Override
	public Object answer(InvocationOnMock invocation) throws Throwable {
		Object[] arguments = invocation.getArguments();

		_portletPreferences.setValue(
			String.valueOf(arguments[0]), String.valueOf(arguments[1]));

		return null;
	}

	private final PortletPreferences _portletPreferences;

}