/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet.filters.autologin;

import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.SyntheticBundleRule;
import com.liferay.portal.util.test.AtomicState;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Philip Jones
 */
public class AutoLoginFilterTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			new SyntheticBundleRule("bundle.autologinfilter"));

	@BeforeClass
	public static void setUpClass() {
		_atomicState = new AtomicState();
	}

	@AfterClass
	public static void tearDownClass() {
		_atomicState.close();
	}

	@Test
	public void testDoFilter() throws IOException, ServletException {
		AutoLoginFilter autoLoginFilter = new AutoLoginFilter();

		HttpServletRequest httpServletRequest = Mockito.mock(
			HttpServletRequest.class);

		Mockito.when(
			httpServletRequest.getRequestURI()
		).thenReturn(
			""
		);

		HttpSession httpSession = Mockito.mock(HttpSession.class);

		Mockito.when(
			httpSession.getAttribute("j_username")
		).thenReturn(
			null
		);

		Mockito.when(
			httpServletRequest.getSession()
		).thenReturn(
			httpSession
		);

		FilterChain filterChain = Mockito.mock(FilterChain.class);

		_atomicState.reset();

		autoLoginFilter.doFilter(httpServletRequest, null, filterChain);

		Assert.assertTrue(_atomicState.isSet());
	}

	private static AtomicState _atomicState;

}