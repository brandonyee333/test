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

package com.liferay.osb.asah.common.spring.cache.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Inácio Nery
 */
public class OSBAsahCacheTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testKeyGenerator() throws Exception {
		Assertions.assertEquals(0, _bar.getIncrementCount());
		Assertions.assertEquals(10, _foo.getIncrementCount());
		Assertions.assertEquals(0, _bar.getIncrementCount());
		Assertions.assertEquals(10, _foo.getIncrementCount());
	}

	@Autowired
	private Bar _bar;

	@Autowired
	private Foo _foo;

}