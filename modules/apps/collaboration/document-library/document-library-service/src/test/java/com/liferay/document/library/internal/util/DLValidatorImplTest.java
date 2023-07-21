/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.internal.util;

import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.liferay.document.library.kernel.util.DLValidator;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.util.FileImpl;
import com.liferay.portal.util.PrefsPropsUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Adolfo Pérez
 */
@PrepareForTest(PrefsPropsUtil.class)
@RunWith(PowerMockRunner.class)
public class DLValidatorImplTest {

	@Before
	public void setUp() {
		PowerMockito.mockStatic(PrefsPropsUtil.class);

		_dlValidator = new DLValidatorImpl();

		FileUtil fileUtil = new FileUtil();

		fileUtil.setFile(FileImpl.getInstance());
	}

	@Test(expected = FileExtensionException.class)
	public void testInvalidExtension() throws Exception {
		_validateFileExtension("test.gıf");
	}

	@Test
	public void testValidLowerCaseExtension() throws Exception {
		_validateFileExtension("test.gif");
	}

	@Test
	public void testValidMixedCaseExtension() throws Exception {
		_validateFileExtension("test.GiF");
	}

	@Test
	public void testValidUpperCaseExtension() throws Exception {
		_validateFileExtension("test.GIF");
	}

	private void _validateFileExtension(String fileName)
		throws FileExtensionException {

		Mockito.when(
			PrefsPropsUtil.getStringArray(
				PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA)
		).thenReturn(
			new String[] {".gif"}
		);

		_dlValidator.validateFileExtension(fileName);

		Mockito.when(
			PrefsPropsUtil.getStringArray(
				PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA)
		).thenReturn(
			new String[] {"gif"}
		);

		_dlValidator.validateFileExtension(fileName);
	}

	private DLValidator _dlValidator;

}