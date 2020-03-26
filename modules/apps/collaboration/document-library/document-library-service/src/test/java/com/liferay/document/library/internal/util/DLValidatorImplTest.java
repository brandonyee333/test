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
		DLValidatorImpl dlValidatorImpl = new DLValidatorImpl();

		Mockito.when(
			PrefsPropsUtil.getStringArray(
				PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA)
		).thenReturn(
			new String[] {"gif"}
		);

		_dlValidator = dlValidatorImpl;

		FileUtil fileUtil = new FileUtil();

		fileUtil.setFile(FileImpl.getInstance());
	}

	@Test(expected = FileExtensionException.class)
	public void testInvalidExtension() throws Exception {
		_dlValidator.validateFileExtension("test.gıf");
	}

	@Test
	public void testValidLowerCaseExtension() throws Exception {
		_dlValidator.validateFileExtension("test.gif");
	}

	@Test
	public void testValidMixedCaseExtension() throws Exception {
		_dlValidator.validateFileExtension("test.GiF");
	}

	@Test
	public void testValidUpperCaseExtension() throws Exception {
		_dlValidator.validateFileExtension("test.GIF");
	}

	private DLValidator _dlValidator;

}