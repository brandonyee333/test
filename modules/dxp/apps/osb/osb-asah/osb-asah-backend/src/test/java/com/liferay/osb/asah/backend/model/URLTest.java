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

package com.liferay.osb.asah.backend.model;

import com.liferay.osb.asah.backend.constants.DataConstants;
import com.liferay.osb.asah.backend.test.util.BaseBeanTestCase;
import com.liferay.petra.string.StringPool;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Inácio Nery
 */
public class URLTest extends BaseBeanTestCase<URL> {

	public URLTest() {
		super(null, Arrays.asList("getURL"));
	}

	@Test
	public void testURL1() {
		URL url = URL.url(DataConstants.ANY);

		Assert.assertEquals(URL.any(), url);
	}

	@Test
	public void testURL2() {
		URL url = URL.url(StringPool.BLANK);

		Assert.assertEquals(URL.any(), url);
	}

	@Test
	public void testURL3() {
		URL url = URL.url(null);

		Assert.assertEquals(URL.any(), url);
	}

	@Override
	protected URL newInstance() {
		return URL.any();
	}

}