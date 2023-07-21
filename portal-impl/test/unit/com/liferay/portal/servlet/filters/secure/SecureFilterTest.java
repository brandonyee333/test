/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet.filters.secure;

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
public class SecureFilterTest {

	@Test
	public void testSecureFilterIsEnabledIfDisabled() {
		PowerMockito.mockStatic(PropsUtil.class);

		Mockito.when(
			PropsUtil.get(SecureFilter.class.getName())
		).thenReturn(
			"false"
		);

		SecureFilter secureFilter = new SecureFilter();

		secureFilter.init(_filterConfig);

		Assert.assertTrue(secureFilter.isFilterEnabled());
	}

	@Test
	public void testSecureFilterIsEnabledIfEnabled() {
		PowerMockito.mockStatic(PropsUtil.class);

		Mockito.when(
			PropsUtil.get(SecureFilter.class.getName())
		).thenReturn(
			"true"
		);

		SecureFilter secureFilter = new SecureFilter();

		secureFilter.init(_filterConfig);

		Assert.assertTrue(secureFilter.isFilterEnabled());
	}

	@Mock
	private FilterConfig _filterConfig;

}