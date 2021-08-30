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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Marcellus Tavares
 */
public class JSONFileEncoder implements FileEncoder {

	public JSONFileEncoder(String pathString) {
		_pathString = pathString;
	}

	@Override
	public void close() throws Exception {
		_bufferedOutputStream.close();
	}

	@Override
	public void encode(String data) throws Exception {
		String newLineAppendedData = data + System.lineSeparator();

		_bufferedOutputStream.write(
			newLineAppendedData.getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public long getDataSize() {
		return _file.length();
	}

	@Override
	public void open() throws Exception {
		_file = new File(_pathString);

		_createMissingParentFileDirectories(_file);

		_fileOutputStream = new FileOutputStream(_file, true);

		_bufferedOutputStream = new BufferedOutputStream(
			_fileOutputStream, _DEFAULT_FILE_BUFFER_SIZE);
	}

	private void _createMissingParentFileDirectories(File file) {
		File parentFile = file.getParentFile();

		if (parentFile == null) {
			return;
		}

		boolean result = parentFile.mkdirs();

		if (result && _log.isDebugEnabled()) {
			_log.debug("Parent directories created for file " + file);
		}
	}

	private static final int _DEFAULT_FILE_BUFFER_SIZE = 8192;

	private static final Log _log = LogFactory.getLog(JSONFileEncoder.class);

	private BufferedOutputStream _bufferedOutputStream;
	private File _file;
	private FileOutputStream _fileOutputStream;
	private final String _pathString;

}