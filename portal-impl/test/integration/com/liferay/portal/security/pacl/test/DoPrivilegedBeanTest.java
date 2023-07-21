/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.test;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.security.lang.DoPrivilegedUtil;
import com.liferay.portal.test.rule.PACLTestRule;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Raymond Augé
 */
public class DoPrivilegedBeanTest {

	@ClassRule
	@Rule
	public static final PACLTestRule paclTestRule = new PACLTestRule();

	@Test
	public void testEquals() throws Exception {
		String className = "TEST_CLASS_NAME";

		ExpandoBridge expandoBridge1 = DoPrivilegedUtil.wrap(
			new ExpandoBridgeImpl(TestPropsValues.getCompanyId(), className));

		ExpandoBridge expandoBridge2 = DoPrivilegedUtil.wrap(
			new ExpandoBridgeImpl(TestPropsValues.getCompanyId(), className));

		Assert.assertTrue(expandoBridge1.equals(expandoBridge2));
	}

}