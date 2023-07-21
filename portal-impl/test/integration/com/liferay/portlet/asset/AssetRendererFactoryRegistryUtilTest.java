/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.SyntheticBundleRule;
import com.liferay.portal.util.PortalImpl;
import com.liferay.portlet.asset.bundle.assetrendererfactoryregistryutil.TestAssetRendererFactory;

import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Peter Fellwock
 */
public class AssetRendererFactoryRegistryUtilTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			new SyntheticBundleRule("bundle.assetrendererfactoryregistryutil"));

	@Test
	public void testGetAssetRendererFactories() {
		String className = TestAssetRendererFactory.class.getName();

		List<AssetRendererFactory<?>> assetRendererFactories =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactories(1);

		for (AssetRendererFactory<?> assetRendererFactory :
				assetRendererFactories) {

			Class<?> clazz = assetRendererFactory.getClass();

			if (className.equals(clazz.getName())) {
				Assert.assertTrue(true);

				return;
			}
		}

		Assert.fail();
	}

	@Test
	public void testGetAssetRendererFactoryByClassName() {
		String className = TestAssetRendererFactory.class.getName();

		AssetRendererFactory<?> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		Class<?> clazz = assetRendererFactory.getClass();

		Assert.assertEquals(className, clazz.getName());
	}

	@Test
	public void testGetAssetRendererFactoryByClassNameId() {
		PortalImpl portalImpl = new PortalImpl();

		long classNameId = portalImpl.getClassNameId(
			TestAssetRendererFactory.class);

		AssetRendererFactory<?> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.
				getAssetRendererFactoryByClassNameId(classNameId);

		Class<?> clazz = assetRendererFactory.getClass();

		Assert.assertEquals(
			TestAssetRendererFactory.class.getName(), clazz.getName());
	}

	@Test
	public void testGetAssetRendererFactoryByType() {
		AssetRendererFactory<?> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByType(
				TestAssetRendererFactory.class.getName());

		Class<?> clazz = assetRendererFactory.getClass();

		Assert.assertEquals(
			TestAssetRendererFactory.class.getName(), clazz.getName());
	}

	@Test
	public void testGetClassNameIds1() {
		long[] classNameIds = AssetRendererFactoryRegistryUtil.getClassNameIds(
			1);

		for (long classNameId : classNameIds) {
			if (classNameId == 1234567890) {
				Assert.assertTrue(true);

				return;
			}
		}

		Assert.fail();
	}

	@Test
	public void testGetClassNameIds2() {
		long[] classNameIds = AssetRendererFactoryRegistryUtil.getClassNameIds(
			1, true);

		for (long classNameId : classNameIds) {
			if (classNameId == 1234567890) {
				Assert.assertTrue(true);

				return;
			}
		}

		Assert.fail();
	}

	@Test
	public void testGetClassNameIds3() {
		long[] classNameIds = AssetRendererFactoryRegistryUtil.getClassNameIds(
			1, false);

		for (long classNameId : classNameIds) {
			if (classNameId == 1234567890) {
				Assert.assertTrue(true);

				return;
			}
		}

		Assert.fail();
	}

}