/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.advisor;

import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Mladen Cikara
 * @author Igor Beslic
 */
@PrepareForTest({DigesterUtil.class, FileUtil.class})
@RunWith(PowerMockRunner.class)
public class LCSKeyAdvisorTest extends PowerMockito {

	@Before
	public void setUp() throws IOException {
		mockStatic(DigesterUtil.class, FileUtil.class);

		when(
			FileUtil.getBytes(Matchers.<File>any())
		).thenReturn(
			_getTestBytes("lcs_server_id")
		);
	}

	@Test
	public void testGetKey() {
		LCSKeyAdvisor lcsKeyAdvisor = spy(new LCSKeyAdvisor());

		doReturn(
			_getTestLCSKeyFilePath("lcs_server_id")
		).when(
			lcsKeyAdvisor
		).getLCSKeyFilePath();

		String key = lcsKeyAdvisor.getKey();

		Assert.assertEquals("LCS key value", "aaaa-bbbb-cccc-dddd", key);
	}

	@Test
	public void testKeyIsNullIfFileDoesNotExist() {
		LCSKeyAdvisor lcsKeyAdvisor = spy(new LCSKeyAdvisor());

		doReturn(
			"/path/to/non_existing_file"
		).when(
			lcsKeyAdvisor
		).getLCSKeyFilePath();

		Assert.assertNull("LCS key value", lcsKeyAdvisor.getKey());
	}

	@Test
	public void testUpdateKey() throws Exception {
		byte[] legacyLCSKeyBytes = _getTestBytes("lcs_server_id_legacy");

		when(
			DigesterUtil.digestHex(
				Digester.MD5, Arrays.toString(legacyLCSKeyBytes))
		).thenReturn(
			"legacy-key-MD5-hashed"
		);

		when(
			FileUtil.getBytes(Matchers.<File>any())
		).thenReturn(
			legacyLCSKeyBytes
		);

		LCSKeyAdvisor lcsKeyAdvisor = spy(new LCSKeyAdvisor());

		doReturn(
			_getTestLCSKeyFilePath("lcs_server_id_legacy")
		).when(
			lcsKeyAdvisor
		).getLCSKeyFilePath();

		String key = lcsKeyAdvisor.getKey();

		Assert.assertEquals(
			"Legacy LCS key value", "legacy-key-MD5-hashed", key);

		lcsKeyAdvisor.updateKey("aaaa-bbbb-cccc-dddd");

		key = lcsKeyAdvisor.getKey();

		Assert.assertEquals(
			"Updated LCS key value", "aaaa-bbbb-cccc-dddd", key);
	}

	private byte[] _getTestBytes(String fileName) throws IOException {
		InputStream inputStream = null;

		try {
			File file = new File(_getTestLCSKeyFilePath(fileName));

			inputStream = new FileInputStream(file);

			byte[] bytes = new byte[(int)file.length()];

			inputStream.read(bytes);

			return bytes;
		}
		finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	private String _getTestLCSKeyFilePath(String fileName) {
		Class<LCSKeyAdvisorTest> clazz = LCSKeyAdvisorTest.class;

		URL resource = clazz.getResource("data/license/server/" + fileName);

		return resource.getPath();
	}

}