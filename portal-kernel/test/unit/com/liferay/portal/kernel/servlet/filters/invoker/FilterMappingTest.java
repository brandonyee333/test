/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.servlet.filters.invoker;

import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Mika Koivisto
 */
@RunWith(PowerMockRunner.class)
public class FilterMappingTest extends PowerMockito {

	@Before
	public void setUp() {
		_dispatchers = new ArrayList<>();

		for (Dispatcher dispatcher : Dispatcher.values()) {
			_dispatchers.add(dispatcher.name());
		}

		_filter = mock(Filter.class);

		_filterConfig = mock(FilterConfig.class);

		when(
			_filterConfig.getInitParameter("url-regex-pattern")
		).thenReturn(
			StringPool.BLANK
		);

		when(
			_filterConfig.getInitParameter("url-regex-ignore-pattern")
		).thenReturn(
			StringPool.BLANK
		);
	}

	@Test
	public void testIsMatchURLPattern() {
		List<String> urlPatterns = new ArrayList<>();

		urlPatterns.add("/c/portal/login");

		FilterMapping filterMapping = new FilterMapping(
			StringPool.BLANK, _filter, _filterConfig, urlPatterns,
			_dispatchers);

		String uri = "/c/portal/login";

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest(HttpMethods.GET, uri);

		Assert.assertTrue(
			filterMapping.isMatch(
				mockHttpServletRequest, Dispatcher.REQUEST, uri));
	}

	private List<String> _dispatchers;
	private Filter _filter;
	private FilterConfig _filterConfig;

}