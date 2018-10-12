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

package com.liferay.lcs.advisor;

import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.Arrays;

import org.apache.commons.io.IOUtils;

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
public class LCSKeyAdvisorImplTest extends PowerMockito {

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
			inputStream = new FileInputStream(_getTestLCSKeyFilePath(fileName));

			byte[] bytes = IOUtils.toByteArray(inputStream);

			return bytes;
		}
		finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	private String _getTestLCSKeyFilePath(String fileName) {
		Class<LCSKeyAdvisorImplTest> clazz = LCSKeyAdvisorImplTest.class;

		URL resource = clazz.getResource(
			"dependencies/data/license/server/" + fileName);

		return resource.getPath();
	}

}