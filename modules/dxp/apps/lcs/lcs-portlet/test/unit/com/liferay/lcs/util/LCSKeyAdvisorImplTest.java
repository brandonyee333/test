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

package com.liferay.lcs.util;

import com.liferay.lcs.advisor.LCSKeyAdvisor;
import com.liferay.portal.kernel.license.util.LicenseManagerUtil;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Mladen Cikara
 * @author Igor Beslic
 */
@PrepareForTest({DigesterUtil.class, LicenseManagerUtil.class, PropsUtil.class})
@RunWith(PowerMockRunner.class)
public class LCSKeyAdvisorImplTest extends PowerMockito {

	@Before
	public void setUp() {
		mockStatic(LicenseManagerUtil.class);

		when(
			LicenseManagerUtil.getHostName()
		).thenReturn(
			"www.test.com"
		);
	}

	@Test
	public void testKeyIsNullIfNeverUpdated() {
		LCSKeyAdvisor lcsKeyAdvisor = new LCSKeyAdvisor();

		Assert.assertNull("LCS key value", lcsKeyAdvisor.getKey());
	}

	@Test
	public void testUpdateKey() {
		LCSKeyAdvisor lcsKeyAdvisor = new LCSKeyAdvisor();

		String key = lcsKeyAdvisor.getKey();

		Assert.assertNull("LCS key value", key);

		lcsKeyAdvisor.updateKey("aaaa-bbbb-cccc-dddd");

		key = lcsKeyAdvisor.getKey();

		Assert.assertEquals("LCS key value", "aaaa-bbbb-cccc-dddd", key);
	}

}