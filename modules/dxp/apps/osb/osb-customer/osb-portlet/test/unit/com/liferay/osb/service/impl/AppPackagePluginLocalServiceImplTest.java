/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.service.impl;

import com.liferay.osb.exception.AppPackagePluginBundleSymbolicNameException;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppPackagePlugin;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.service.AppPackageLocalService;
import com.liferay.osb.service.persistence.AppPackagePluginPersistence;
import com.liferay.osb.service.persistence.AppVersionPersistence;
import com.liferay.osb.util.BundleUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.File;

import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;

/**
 * @author Yury Butrymovich
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({BundleUtil.class, FileUtil.class})
public class AppPackagePluginLocalServiceImplTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		setUpPersistence();
		setUpUtils();
	}

	@Test(expected = AppPackagePluginBundleSymbolicNameException.class)
	public void testAddAppPackagePlugin() throws Exception {
		_appPackagePluginLocalService.addAppPackagePlugin(
			0,0, "test.jar", null, 0, true);
	}

	@Test(expected = AppPackagePluginBundleSymbolicNameException.class)
	public void testValidateAppPackagePlugin() throws Exception {
		AppVersion appVersion = mock(AppVersion.class);
		AppPackage appPackage = mock(AppPackage.class);

		AppPackagePlugin appPackagePlugin = mock(AppPackagePlugin.class);

		when(
			appPackagePlugin.getContextName()
		).thenReturn(
			StringPool.BLANK
		);

		when(
			appPackagePlugin.getAppPackagePluginId()
		).thenReturn(
			1L
		);

		AssetAttachment assetAttachment = mock(AssetAttachment.class);

		when(
			assetAttachment.getFileName()
		).thenReturn(
			"test.jar"
		);

		when(
			appPackagePlugin.getAssetAttachment()
		).thenReturn(
			assetAttachment
		);

		mockStatic(FileUtil.class);

		_appPackagePluginLocalService.validateAppPackagePlugin(
			appVersion, appPackage, appPackagePlugin);
	}

	private void setUpPersistence() throws Exception {
		AppVersionPersistence appVersionPersistence = mock(
			AppVersionPersistence.class);

		when(
			appVersionPersistence.findByPrimaryKey(Mockito.anyLong())
		).thenReturn(
			mock(AppVersion.class)
		);

		_appPackagePluginLocalService.setAppVersionPersistence(
			appVersionPersistence);

		AppPackageLocalService appPackageLocalService = mock(
			AppPackageLocalService.class);

		when(
			appPackageLocalService.addAppPackage(
				Mockito.anyLong(), Mockito.anyLong(), Mockito.anyInt(),
				Mockito.anyBoolean())
		).thenReturn(
			mock(AppPackage.class)
		);

		_appPackagePluginLocalService.setAppPackageLocalService(
			appPackageLocalService);

		AppPackagePluginPersistence appPackagePluginPersistence = mock(
			AppPackagePluginPersistence.class);

		List<AppPackagePlugin> appPackagePluginList = mock(List.class);

		when(
			appPackagePluginList.size()
		).thenReturn(
			1
		);

		AppPackagePlugin appPackagePlugin = mock(AppPackagePlugin.class);

		when(
			appPackagePlugin.getAppPackagePluginId()
		).thenReturn(
			2L
		);

		when(
			appPackagePluginList.get(0)
		).thenReturn(
			appPackagePlugin
		);

		when(
			appPackagePluginPersistence.findByAPI_BSN_BV(
				Mockito.anyLong(), Mockito.anyString(), Mockito.anyString())
		).thenReturn(
			appPackagePluginList
		);

		_appPackagePluginLocalService.setAppPackagePluginPersistence(
			appPackagePluginPersistence);
	}

	private void setUpUtils() {
		Manifest manifest = mock(Manifest.class);
		Attributes attributes = mock(Attributes.class);

		when(
			attributes.getValue("Bundle-SymbolicName")
		).thenReturn(
			"com.liferay.test.bundle"
		);

		when(
			attributes.getValue("Bundle-Version")
		).thenReturn(
			"1.0.0"
		);

		when(
			manifest.getMainAttributes()
		).thenReturn(
			attributes
		);

		mockStatic(BundleUtil.class);

		when(
			BundleUtil.getManifest((File)any())
		).thenReturn(
			manifest
		);
	}

	private AppPackagePluginLocalServiceImpl _appPackagePluginLocalService =
		new AppPackagePluginLocalServiceImpl();

}