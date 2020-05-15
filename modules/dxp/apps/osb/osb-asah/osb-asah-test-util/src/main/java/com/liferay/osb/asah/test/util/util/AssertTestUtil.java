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

package com.liferay.osb.asah.test.util.util;

import java.util.Collection;

import org.junit.Assert;

/**
 * @author Brian Wing Shun Chan
 */
public class AssertTestUtil {

	public static void assertSize(int expectedSize, Collection<?> actualList) {
		Assert.assertEquals(
			actualList.toString(), expectedSize, actualList.size());
	}

}