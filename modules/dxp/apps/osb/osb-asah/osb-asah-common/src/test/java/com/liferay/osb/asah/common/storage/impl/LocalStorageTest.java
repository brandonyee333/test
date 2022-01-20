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

package com.liferay.osb.asah.common.storage.impl;

import com.liferay.osb.asah.common.storage.StorageConfiguration;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.core.io.ClassPathResource;

/**
 * @author Marcos Martins
 */
public class LocalStorageTest {

	@AfterEach
	public void tearDown() {
		File file = new File("./");

		File[] files = file.listFiles(
			new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					return StringUtils.startsWith(name, _PATH);
				}

			});

		if (files != null) {
			for (File currFile : files) {
				if (!currFile.delete()) {
					_log.error(
						"Unable to delete file " + currFile.getAbsolutePath());
				}
			}
		}
	}

	@Test
	public void testWrite() throws Exception {
		StorageConfiguration storageConfiguration = Mockito.mock(
			StorageConfiguration.class);

		Mockito.when(
			storageConfiguration.getChunkSize()
		).thenReturn(
			64L * 1024 * 1024
		);

		Mockito.when(
			storageConfiguration.getFileFormat()
		).thenReturn(
			StorageConfiguration.FileFormat.JSON
		);

		Mockito.when(
			storageConfiguration.getPath()
		).thenReturn(
			_PATH
		);

		LocalStorage localStorage = new LocalStorage(
			null, storageConfiguration);

		Assertions.assertTrue(localStorage.write(_getInputStream()));
	}

	private InputStream _getInputStream() throws Exception {
		ClassPathResource classPathResource = new ClassPathResource(
			"blog_info.json", getClass());

		return classPathResource.getInputStream();
	}

	private static final String _PATH = "test_local_storage.json";

	private static final Log _log = LogFactory.getLog(LocalStorageTest.class);

}