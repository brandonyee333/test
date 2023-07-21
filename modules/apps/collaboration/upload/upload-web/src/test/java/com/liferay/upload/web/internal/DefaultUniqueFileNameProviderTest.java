/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.upload.web.internal;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.util.FileImpl;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Alejandro Tardín
 */
public class DefaultUniqueFileNameProviderTest {

	@Before
	public void setUp() {
		FileUtil fileUtil = new FileUtil();

		fileUtil.setFile(new FileImpl());
	}

	@Test
	public void testAppendsAParentheticalSuffixIfTheFileExists()
		throws Exception {

		String originalFileName = "filename.extension";

		for (int i = 1; i <= 50; i++) {
			String uniqueFileName = _defaultUniqueFileNameProvider.provide(
				originalFileName, _existsUntil(i));

			Assert.assertEquals(
				"filename (" + i + ").extension", uniqueFileName);
		}
	}

	@Test
	public void testAppendsAParentheticalSuffixIfTheFileExistsWithNoExtension()
		throws Exception {

		String originalFileName = "filename";

		String uniqueFileName = _defaultUniqueFileNameProvider.provide(
			originalFileName, _existsUntil(1));

		Assert.assertEquals("filename (1)", uniqueFileName);
	}

	@Test(expected = PortalException.class)
	public void testGivesUpIfTheFileExists51Times() throws Exception {
		_defaultUniqueFileNameProvider.provide(
			"filename.extension", _existsUntil(51));
	}

	@Test
	public void testModifiesOnlyTheLastExistingParentheticalSuffix()
		throws Exception {

		String originalFileName = "filename (1) (2) (3) (4) (1).extension";

		String uniqueFileName = _defaultUniqueFileNameProvider.provide(
			originalFileName, _existsUntil(2));

		Assert.assertEquals(
			"filename (1) (2) (3) (4) (2).extension", uniqueFileName);
	}

	@Test
	public void testModifiesTheExistingParentheticalSuffix() throws Exception {
		String originalFileName = "filename (1).extension";

		String uniqueFileName = _defaultUniqueFileNameProvider.provide(
			originalFileName, _existsUntil(2));

		Assert.assertEquals("filename (2).extension", uniqueFileName);
	}

	@Test
	public void testModifiesTheExistingParentheticalSuffixWithNoExtension()
		throws Exception {

		String originalFileName = "filename (1)";

		String uniqueFileName = _defaultUniqueFileNameProvider.provide(
			originalFileName, _existsUntil(2));

		Assert.assertEquals("filename (2)", uniqueFileName);
	}

	@Test
	public void testReturnsTheSameFileNameIfTheFileDoesNotExist()
		throws Exception {

		String originalFileName = "filename.extension";

		String uniqueFileName = _defaultUniqueFileNameProvider.provide(
			originalFileName, fileName -> false);

		Assert.assertEquals(originalFileName, uniqueFileName);
	}

	private Predicate<String> _existsUntil(int n) {
		AtomicInteger atomicInteger = new AtomicInteger(0);

		return fileName -> atomicInteger.addAndGet(1) <= n;
	}

	private final DefaultUniqueFileNameProvider _defaultUniqueFileNameProvider =
		new DefaultUniqueFileNameProvider();

}