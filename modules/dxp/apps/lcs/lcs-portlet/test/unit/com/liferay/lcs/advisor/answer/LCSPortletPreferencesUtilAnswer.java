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

package com.liferay.lcs.advisor.answer;

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