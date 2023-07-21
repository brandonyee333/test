/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.string;

import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Alexander Chow
 * @author Shuyang Zhou
 * @author Hugo Huijser
 * @author Preston Crary
 */
public class StringUtilTest {

	@ClassRule
	public static final CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor() {

			@Override
			public void appendAssertClasses(List<Class<?>> assertClasses) {
				assertClasses.add(CharPool.class);
				assertClasses.add(StringPool.class);
			}

		};

	@Test
	public void testConstructors() {
		new CharPool();
		new StringPool();
		new StringUtil();
	}

	@Test
	public void testSplit() {
		Assert.assertSame(Collections.emptyList(), StringUtil.split(null));
		Assert.assertSame(
			Collections.emptyList(), StringUtil.split(StringPool.BLANK));
		Assert.assertSame(
			Collections.emptyList(), StringUtil.split(StringPool.SPACE));

		Assert.assertEquals(
			Collections.<String>emptyList(),
			StringUtil.split(StringPool.COMMA));
		Assert.assertEquals(
			Collections.<String>emptyList(), StringUtil.split(",,,"));

		Assert.assertEquals(
			Collections.singletonList("test"), StringUtil.split("test"));
		Assert.assertEquals(
			Collections.singletonList("test"), StringUtil.split("test,"));
		Assert.assertEquals(
			Collections.singletonList("test"), StringUtil.split(",test"));

		Assert.assertEquals(
			Arrays.asList("test1", "test2"),
			StringUtil.split("test1-test2", CharPool.DASH));
	}

}