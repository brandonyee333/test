/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.js.loader.modules.extender.npm;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Iván Zaera
 */
public class ModuleNameUtilTest {

	@Test
	public void testGetPackageName() throws Exception {
		String packageName = ModuleNameUtil.getPackageName(
			"mypackage/lib/mymodule");

		Assert.assertEquals("mypackage", packageName);
	}

	@Test
	public void testGetPackageNameNoModule() throws Exception {
		String packageName = ModuleNameUtil.getPackageName("mypackage");

		Assert.assertEquals("mypackage", packageName);
	}

	@Test
	public void testGetPackageNameScoped() throws Exception {
		String packageName = ModuleNameUtil.getPackageName(
			"@myscope/mypackage/lib/mymodule");

		Assert.assertEquals("@myscope/mypackage", packageName);
	}

	@Test
	public void testGetPackageNameScopedNoModule() throws Exception {
		String packageName = ModuleNameUtil.getPackageName(
			"@myscope/mypackage");

		Assert.assertEquals("@myscope/mypackage", packageName);
	}

	@Test
	public void testGetPackagePath() throws Exception {
		String packagePath = ModuleNameUtil.getPackagePath(
			"mypackage/lib/mymodule");

		Assert.assertEquals("lib/mymodule", packagePath);
	}

	@Test
	public void testGetPackagePathNoModule() throws Exception {
		String packagePath = ModuleNameUtil.getPackagePath("mypackage");

		Assert.assertNull(packagePath);
	}

	@Test
	public void testGetPackagePathScoped() throws Exception {
		String packagePath = ModuleNameUtil.getPackagePath(
			"@myscope/mypackage/lib/mymodule");

		Assert.assertEquals("lib/mymodule", packagePath);
	}

	@Test
	public void testGetPackagePathScopedNoModule() throws Exception {
		String packagePath = ModuleNameUtil.getPackagePath(
			"@myscope/mypackage");

		Assert.assertNull(packagePath);
	}

}