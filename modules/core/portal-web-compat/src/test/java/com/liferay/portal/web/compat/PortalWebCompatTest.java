/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.web.compat;

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;

import java.io.File;
import java.io.FileReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Preston Crary
 */
public class PortalWebCompatTest {

	@Test
	public void testNoConflictingImportedFiles() throws Exception {
		File importedClassesPropertiesFile = new File(
			"imported-files.properties");

		Assert.assertTrue(importedClassesPropertiesFile.exists());

		File docrootDir = new File("../../../portal-web/docroot/");

		docrootDir = docrootDir.getCanonicalFile();

		Assert.assertTrue(
			docrootDir + " is not portal-web/docroot", docrootDir.exists());

		try (FileReader fileReader = new FileReader(
				importedClassesPropertiesFile);
			UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(fileReader)) {

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				if (line.indexOf('=') >= 0) {
					Matcher matcher = _dependencyPattern.matcher(line);

					Assert.assertTrue(matcher.matches());
				}
				else {
					line = line.trim();

					if (line.endsWith(",\\")) {
						line = line.substring(0, line.length() - 2);
					}

					File file = new File(docrootDir, line);

					Assert.assertFalse(
						file.getPath() + " should not exist", file.exists());
				}
			}
		}
	}

	private static final Pattern _dependencyPattern = Pattern.compile(
		"com.liferay.portal\\\\:com.liferay.portal.web\\\\:" +
			"\\d+\\.\\d+\\.\\d+\\\\:jsp@war=\\\\");

}