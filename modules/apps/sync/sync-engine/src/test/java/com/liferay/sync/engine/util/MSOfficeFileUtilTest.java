/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util;

import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class MSOfficeFileUtilTest {

	@Test
	public void testIsTempCreatedFile() throws Exception {
		String[] fileNames = {
			"B1F94A3.tmp", "ppt5039.tmp", "~$st.doc", "~WRF0000.tmp"
		};

		for (String fileName : fileNames) {
			Assert.assertTrue(
				MSOfficeFileUtil.isTempCreatedFile(Paths.get(fileName)));
		}

		Assert.assertFalse(
			MSOfficeFileUtil.isTempCreatedFile(Paths.get("1234567")));
		Assert.assertFalse(
			MSOfficeFileUtil.isTempCreatedFile(Paths.get("test.tmp")));
	}

	@Test
	public void testIsTempRenamedFile() throws Exception {

		// Excel

		testIsTempRenamedFile(
			new String[] {
				"test.csv", "test.xls", "test.xlsb", "test.xlsm", "test.xlsx",
				"test.xltx"
			},
			new String[] {"CF19E4A8.tmp", "6CFEC200"});

		Assert.assertFalse(
			MSOfficeFileUtil.isTempRenamedFile(
				Paths.get("test.xlsx"), Paths.get("6cfec200")));

		// Powerpoint

		testIsTempRenamedFile(
			new String[] {
				"test.pot", "test.potm", "test.potx", "test.ppa", "test.ppam",
				"test.pps", "test.ppsm", "test.ppsx", "test.ppt", "test.pptm",
				"test.pptx"
			},
			new String[] {"CF19E4A8.tmp", "6CFEC200"});

		Assert.assertFalse(
			MSOfficeFileUtil.isTempRenamedFile(
				Paths.get("test.pptx"), Paths.get("6cfec200")));

		// Publisher

		Assert.assertTrue(
			MSOfficeFileUtil.isTempRenamedFile(
				Paths.get("test.pub"), Paths.get("pubDD2F.tmp")));

		// Word

		testIsTempRenamedFile(
			new String[] {
				"test.doc", "test.docb", "test.docm", "test.docx", "test.dot",
				"test.dotm", "test.dotx"
			},
			new String[] {"~WRD1001.tmp", "~WRL0001.tmp", "~WRF1233.tmp"});

		Assert.assertFalse(
			MSOfficeFileUtil.isTempRenamedFile(
				Paths.get("test.docx"), Paths.get("CF19E4A8.tmp")));
	}

	protected void testIsTempRenamedFile(
		String[] sourceFilePathNames, String[] targetFilePathNames) {

		for (String sourceFilePathName : sourceFilePathNames) {
			for (String targetFilePathName : targetFilePathNames) {
				Assert.assertTrue(
					MSOfficeFileUtil.isTempRenamedFile(
						Paths.get(sourceFilePathName),
						Paths.get(targetFilePathName)));
			}
		}
	}

}