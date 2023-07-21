/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.model;

import com.liferay.asset.kernel.model.AssetCategoryDisplay;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Spasic
 */
public class AssetCategoryDisplayTest {

	@Test
	public void testGetPage() {
		AssetCategoryDisplay assetCategoryDisplay = new AssetCategoryDisplay();

		assetCategoryDisplay.setStart(0);
		assetCategoryDisplay.setEnd(20);

		Assert.assertEquals(1, assetCategoryDisplay.getPage());

		assetCategoryDisplay.setStart(20);
		assetCategoryDisplay.setEnd(40);

		Assert.assertEquals(2, assetCategoryDisplay.getPage());

		assetCategoryDisplay.setEnd(0);

		Assert.assertEquals(0, assetCategoryDisplay.getPage());
	}

}