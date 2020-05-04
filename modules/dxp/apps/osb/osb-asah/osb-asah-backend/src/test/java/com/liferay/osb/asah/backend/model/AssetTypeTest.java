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

import com.liferay.osb.asah.backend.test.util.BaseEnumTestCase;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Inácio Nery
 */
public class AssetTypeTest extends BaseEnumTestCase<AssetType> {

	@Test(expected = IllegalArgumentException.class)
	public void testOfAsset() {
		AssetType.of("asset");
	}

	@Test
	public void testOfBlog() {
		Assert.assertEquals(AssetType.BLOG, AssetType.of("blog"));
	}

	@Test
	public void testOfDocument() {
		Assert.assertEquals(AssetType.DOCUMENT, AssetType.of("document"));
	}

	@Test
	public void testOfForm() {
		Assert.assertEquals(AssetType.FORM, AssetType.of("form"));
	}

	@Test
	public void testOfJournal() {
		Assert.assertEquals(AssetType.JOURNAL, AssetType.of("journal"));
	}

	@Test
	public void testOfURL() {
		Assert.assertEquals(AssetType.PAGE, AssetType.of("page"));
	}

	@Override
	protected Class<? extends Enum<?>> getClazz() {
		return AssetType.class;
	}

}