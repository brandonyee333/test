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

package com.liferay.lcs.portal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Igor Beslic
 */
@RunWith(PowerMockRunner.class)
public class SiteTypeTest extends PowerMockito {

	@Test
	public void testIsSpecificSiteType() {
		Assert.assertTrue(
			SiteType.isOrganization(SiteType.ORGANIZATION.getType()));
		Assert.assertFalse(SiteType.isOrganization(SiteType.SITE.getType()));
		Assert.assertTrue(SiteType.isSite(SiteType.SITE.getType()));
		Assert.assertFalse(SiteType.isSite(SiteType.ORGANIZATION.getType()));
	}

	@Test
	public void testToSiteType() {
		for (SiteType siteType : SiteType.values()) {
			Assert.assertEquals(
				siteType, SiteType.toSiteType(siteType.getType()));
		}

		Assert.assertEquals(SiteType.ORGANIZATION, SiteType.toSiteType(-1));
	}

}