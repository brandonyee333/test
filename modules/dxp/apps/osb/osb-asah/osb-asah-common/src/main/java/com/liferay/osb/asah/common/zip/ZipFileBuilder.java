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

package com.liferay.osb.asah.common.zip;

import com.liferay.osb.asah.common.function.UnsafeConsumer;

import java.io.File;
import java.io.FileOutputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Matthew Kong
 */
public class ZipFileBuilder {

	public ZipFileBuilder(File file) {
		_file = file;
	}

	public ZipFileBuilder(String fileName) {
		_file = new File(fileName);
	}

	public void addToZip(
		String fileName,
		UnsafeConsumer<ZipOutputStream, Exception> unsafeConsumer) {

		_unsafeConsumers.put(fileName, unsafeConsumer);
	}

	public void build() throws Exception {
		try (FileOutputStream fileOutputStream = new FileOutputStream(
				_file.getAbsolutePath())) {

			try (ZipOutputStream zipOutputStream = new ZipOutputStream(
					fileOutputStream)) {

				for (Map.Entry
						<String, UnsafeConsumer<ZipOutputStream, Exception>>
							entry : _unsafeConsumers.entrySet()) {

					zipOutputStream.putNextEntry(new ZipEntry(entry.getKey()));

					UnsafeConsumer<ZipOutputStream, Exception> unsafeConsumer =
						entry.getValue();

					try {
						unsafeConsumer.accept(zipOutputStream);
					}
					catch (Exception e) {
						_log.error(e, e);
					}

					zipOutputStream.closeEntry();
				}
			}
		}
	}

	private static final Log _log = LogFactory.getLog(ZipFileBuilder.class);

	private final File _file;
	private Map<String, UnsafeConsumer<ZipOutputStream, Exception>>
		_unsafeConsumers = new HashMap<>();

}