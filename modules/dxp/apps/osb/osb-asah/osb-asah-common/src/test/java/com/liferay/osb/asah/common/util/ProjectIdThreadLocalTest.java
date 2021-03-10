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

package com.liferay.osb.asah.common.util;

import com.liferay.osb.asah.common.multitenancy.exception.InvalidProjectIdException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author André Miranda
 */
public class ProjectIdThreadLocalTest {

	@Test(expected = InvalidProjectIdException.class)
	public void testSetInvalidProjectId() {
		ProjectIdThreadLocal.setProjectId("../../../now_whatever?param1=&");
	}

	@Test
	public void testSetProjectId() {
		ProjectIdThreadLocal.setProjectId(
			"asah652a6babdba143d086a19db542781bc2");

		Assert.assertEquals(
			"asah652a6babdba143d086a19db542781bc2",
			ProjectIdThreadLocal.getProjectId());
	}

}