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

package com.liferay.osb.asah.batch.curator.bot.nanite.test;

import com.liferay.osb.asah.batch.curator.bot.nanite.DeleteTempFilesNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Matthew Kong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBatchCuratorSpringBootApplication.class)
public class DeleteTempFilesNaniteTest {

	@Before
	public void setUp() throws Exception {
		_tempPath = Files.createTempDirectory("temp");

		ReflectionTestUtils.setField(
			_deleteTempFilesNanite, "_tempPathName", _tempPath.toString());
	}

	@After
	public void tearDown() {
		File folder = _tempPath.toFile();

		File[] files = folder.listFiles();

		if (files != null) {
			for (File file : files) {
				if (!file.delete()) {
					_log.error(
						"Unable to delete file " + file.getAbsolutePath());
				}
			}
		}

		if (!folder.delete()) {
			_log.error("Unable to delete folder " + folder.getAbsolutePath());
		}
	}

	@Test
	public void testDelete() throws Exception {
		Path newCSVPath = Files.write(
			Paths.get(_tempPath.toString(), "new.csv"), new byte[0]);
		Path oldCSVPath = Files.write(
			Paths.get(_tempPath.toString(), "old.csv"), new byte[0]);

		LocalDateTime localDateTime = LocalDateTime.now();

		localDateTime = localDateTime.minusDays(2);

		Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

		Files.setAttribute(
			oldCSVPath, "lastModifiedTime", FileTime.from(instant));

		_deleteTempFilesNanite.run(null);

		File oldCSVFile = oldCSVPath.toFile();

		Assert.assertFalse(oldCSVFile.exists());

		File newCSVFile = newCSVPath.toFile();

		Assert.assertTrue(newCSVFile.exists());
	}

	private static final Log _log = LogFactory.getLog(
		DeleteTempFilesNaniteTest.class);

	@Autowired
	private DeleteTempFilesNanite _deleteTempFilesNanite;

	private Path _tempPath;

}