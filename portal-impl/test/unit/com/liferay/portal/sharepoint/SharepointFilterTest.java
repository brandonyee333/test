/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.sharepoint;

import com.liferay.portal.util.PropsUtil;

import javax.servlet.FilterConfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Mariano Álvaro Sáiz
 */
@PrepareForTest(PropsUtil.class)
@RunWith(PowerMockRunner.class)
public class SharepointFilterTest {

	@Test
	public void testSharepointFilterIsDisabled() {
		PowerMockito.mockStatic(PropsUtil.class);

		Mockito.when(
			PropsUtil.get(SharepointFilter.class.getName())
		).thenReturn(
			"false"
		);

		SharepointFilter sharepointFilter = new SharepointFilter();

		sharepointFilter.init(_filterConfig);

		Assert.assertFalse(sharepointFilter.isFilterEnabled());
	}

	@Test
	public void testSharepointFilterIsEnabled() {
		PowerMockito.mockStatic(PropsUtil.class);

		Mockito.when(
			PropsUtil.get(SharepointFilter.class.getName())
		).thenReturn(
			"true"
		);

		SharepointFilter sharepointFilter = new SharepointFilter();

		sharepointFilter.init(_filterConfig);

		Assert.assertTrue(sharepointFilter.isFilterEnabled());
	}

	@Mock
	private FilterConfig _filterConfig;

}