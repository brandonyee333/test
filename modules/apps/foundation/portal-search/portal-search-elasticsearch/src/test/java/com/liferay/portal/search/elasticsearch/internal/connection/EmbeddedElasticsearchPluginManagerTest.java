/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.connection;

import com.liferay.portal.kernel.test.util.RandomTestUtil;

import java.io.IOException;

import java.nio.file.Path;

import org.elasticsearch.Version;
import org.elasticsearch.common.cli.Terminal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author André de Oliveira
 */
public class EmbeddedElasticsearchPluginManagerTest {

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		setUpPluginManagerFactory();
		setUpPluginZipFactory();
	}

	@Test
	public void testInstallForTheFirstTime() throws Exception {
		install();

		Mockito.verify(
			_pluginManager
		).downloadAndExtract(
			Mockito.eq(_PLUGIN_NAME), Mockito.<Terminal>any(), Mockito.eq(true)
		);

		Mockito.verify(
			_pluginZipFactory
		).createPluginZip(
			"/plugins/" + _PLUGIN_NAME + "-" + Version.CURRENT + ".zip"
		);

		Mockito.verify(
			_pluginZip
		).delete();
	}

	@Test
	public void testInstallForTheSecondTime() throws Exception {
		setUpAlreadyInstalled(true);

		install();

		Mockito.verify(
			_pluginManager, Mockito.never()
		).downloadAndExtract(
			Mockito.anyString(), Mockito.<Terminal>any(), Mockito.anyBoolean()
		);

		Mockito.verify(
			_pluginZipFactory, Mockito.never()
		).createPluginZip(
			Mockito.anyString()
		);

		Mockito.verifyZeroInteractions(_pluginZip);
	}

	@Test
	public void testInstallOverObsoleteVersion() throws Exception {
		setUpAlreadyInstalled(false);

		install();

		Mockito.verify(
			_pluginManager
		).removePlugin(
			Mockito.eq(_PLUGIN_NAME), Mockito.<Terminal>any()
		);
	}

	@Test
	public void testPluginZipIsDeletedOnError() throws Exception {
		IOException ioException = new IOException();

		setUpBrokenDownloadAndExtract(ioException);

		try {
			install();

			Assert.fail();
		}
		catch (IOException ioe) {
			Assert.assertSame(ioException, ioe);
		}

		Mockito.verify(
			_pluginZip
		).delete();
	}

	@Test
	public void testSurviveConcurrentInstallInSameDir() throws Exception {
		setUpBrokenDownloadAndExtract(
			new IOException(
				"already exists. To update the plugin, uninstall it first"));

		install();

		Mockito.verify(
			_pluginZip
		).delete();
	}

	protected Path createPath(String pluginName) {
		Path path = Mockito.mock(Path.class);

		Mockito.doReturn(
			true
		).when(
			path
		).endsWith(
			pluginName
		);

		return path;
	}

	protected void install() throws IOException {
		EmbeddedElasticsearchPluginManager embeddedElasticsearchPluginManager =
			new EmbeddedElasticsearchPluginManager(
				_PLUGIN_NAME, _PLUGINS_PATH_STRING, _pluginManagerFactory,
				_pluginZipFactory);

		embeddedElasticsearchPluginManager.removeObsoletePlugin();

		embeddedElasticsearchPluginManager.install();
	}

	protected void setUpAlreadyInstalled(boolean currentVersion)
		throws Exception {

		Path path = createPath(_PLUGIN_NAME);

		Mockito.doReturn(
			new Path[] {path}
		).when(
			_pluginManager
		).getInstalledPluginsPaths();

		Mockito.doReturn(
			currentVersion
		).when(
			_pluginManager
		).isCurrentVersion(
			path
		);
	}

	protected void setUpBrokenDownloadAndExtract(IOException ioException)
		throws Exception {

		Mockito.doThrow(
			ioException
		).when(
			_pluginManager
		).downloadAndExtract(
			Mockito.anyString(), Mockito.<Terminal>any(), Mockito.anyBoolean()
		);
	}

	protected void setUpPluginManagerFactory() {
		Mockito.doReturn(
			_pluginManager
		).when(
			_pluginManagerFactory
		).createPluginManager();

		Mockito.doReturn(
			_pluginManager
		).when(
			_pluginManagerFactory
		).createPluginManager(
			Mockito.<PluginZip>any()
		);
	}

	protected void setUpPluginZipFactory() throws IOException {
		Mockito.doReturn(
			_pluginZip
		).when(
			_pluginZipFactory
		).createPluginZip(
			Mockito.anyString()
		);
	}

	private static final String _PLUGIN_NAME = RandomTestUtil.randomString();

	private static final String _PLUGINS_PATH_STRING = ".";

	@Mock
	private PluginManager _pluginManager;

	@Mock
	private PluginManagerFactory _pluginManagerFactory;

	@Mock
	private PluginZip _pluginZip;

	@Mock
	private PluginZipFactory _pluginZipFactory;

}