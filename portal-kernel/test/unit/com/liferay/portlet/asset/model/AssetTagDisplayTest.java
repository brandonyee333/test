/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.model;

import com.liferay.asset.kernel.model.AssetTagDisplay;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Spasic
 */
public class AssetTagDisplayTest {

	@Test
	public void testGetPage() {
		AssetTagDisplay assetTagDisplay = new AssetTagDisplay();

		assetTagDisplay.setStart(0);
		assetTagDisplay.setEnd(20);

		Assert.assertEquals(1, assetTagDisplay.getPage());

		assetTagDisplay.setStart(20);
		assetTagDisplay.setEnd(40);

		Assert.assertEquals(2, assetTagDisplay.getPage());

		assetTagDisplay.setEnd(0);

		Assert.assertEquals(0, assetTagDisplay.getPage());
	}

}