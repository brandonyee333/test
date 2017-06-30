/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.corpmembers.portlet;

import com.liferay.osb.UnverifiedUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import javax.portlet.ActionRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Yury Butrymovich
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({RoleLocalServiceUtil.class, UserLocalServiceUtil.class})
public class CorpMembersPortletTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		setUpUtils();
	}

	@Test(expected = UnverifiedUserException.class)
	public void testAddUnverifiedCorpProjectUser() throws Exception {
		ActionRequest actionRequest = mock(ActionRequest.class);

		ThemeDisplay themeDisplay = mock(ThemeDisplay.class);

		when(
			actionRequest.getAttribute(WebKeys.THEME_DISPLAY)
		).thenReturn(
			themeDisplay
		);

		CorpMembersPortlet corpMembersPortlet = new CorpMembersPortlet();

		corpMembersPortlet.addCorpProjectUser(actionRequest, null);
	}

	private void setUpUtils() throws PortalException, SystemException {
		mockStatic(RoleLocalServiceUtil.class);
		mockStatic(UserLocalServiceUtil.class);

		when(
			RoleLocalServiceUtil.hasUserRole(
				Matchers.anyLong(), Matchers.anyLong())
		).thenReturn(
			false
		);

		User user = mock(User.class);

		when(
			user.isEmailAddressVerified()
		).thenReturn(
			false
		);

		when(
			UserLocalServiceUtil.getUserByEmailAddress(
				Matchers.anyLong(), Matchers.anyString())
		).thenReturn(
			user
		);
	}

}