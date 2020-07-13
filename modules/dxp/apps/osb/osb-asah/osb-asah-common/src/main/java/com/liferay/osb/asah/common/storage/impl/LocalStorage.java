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

import com.liferay.osb.asah.common.storage.Storage;
import com.liferay.osb.asah.common.storage.StorageConfiguration;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Marcellus Tavares
 */
public class LocalStorage implements Storage {

	public LocalStorage(StorageConfiguration storageConfiguration) {
		_storageStorageConfiguration = storageConfiguration;

		if (_log.isInfoEnabled()) {
			_log.info(
				"Storage writer initialized for path " +
					_storageStorageConfiguration.getPath());
		}

		try {
			_open();
		}
		catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void close() {
		try {
			_bufferedOutputStream.close();
		}
		catch (IOException ioe) {
			_log.error(
				"Unable to close storage writer for path " +
					_storageStorageConfiguration.getPath(),
				ioe);
		}
	}

	public void flush() {
		try {
			_rollover();
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
	}

	@Override
	public File readSparkJobResult(
		Date sparkJobResultDateAfter, String sparkJobResultPathPrefix) {

		try {
			return _googleStorageArchiver.readSparkJobResult(
				_storageStorageConfiguration.getGoogleBucket(),
				_storageStorageConfiguration.getGoogleBucketFolder(),
				sparkJobResultDateAfter, sparkJobResultPathPrefix);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	public void setGoogleStorageArchiver(
		GoogleStorageArchiver googleStorageArchiver) {

		_googleStorageArchiver = googleStorageArchiver;
	}

	@Override
	public boolean write(InputStream inputStream) {
		boolean status = true;

		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

			String line = null;

			while ((line = bufferedReader.readLine()) != null) {
				if (!write(line)) {
					status = false;
				}
			}
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			status = false;
		}

		flush();

		return status;
	}

	@Override
	public boolean write(String data) {
		try {
			if (_isFileSizeLimitReached()) {
				_rollover();
			}

			String newLineAppendedData =
				data + System.getProperty("line.separator");

			_bufferedOutputStream.write(
				newLineAppendedData.getBytes(StandardCharsets.UTF_8));

			return true;
		}
		catch (IOException e) {
			return false;
		}
	}

	private void _archiveFile(File file) {
		if ((_googleStorageArchiver == null) ||
			(_storageStorageConfiguration.getGoogleBucket() == null)) {

			return;
		}

		_googleStorageArchiver.archiveAsync(
			_storageStorageConfiguration.getGoogleBucket(),
			_storageStorageConfiguration.getGoogleBucketFolder(), file,
			_getArchiveFileName(file.getName()));
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

	private void _deleteFile(File file) throws IOException {
		if (!file.exists()) {
			return;
		}

		boolean result = file.delete();

		if (!result) {
			throw new IOException("Unable to delete file " + file);
		}
	}

	private String _getArchiveFileName(String fileName) {
		int index = fileName.lastIndexOf('.');

		return String.format(
			"%s/%s", fileName.substring(0, index),
			fileName.substring(index + 1));
	}

	private boolean _isFileSizeLimitReached() {
		if (_file.length() >= _storageStorageConfiguration.getChunkSize()) {
			return true;
		}

		return false;
	}

	private void _open() throws IOException {
		_file = new File(_storageStorageConfiguration.getPath());

		_createMissingParentFileDirectories(_file);

		_fileOutputStream = new FileOutputStream(_file, true);

		_bufferedOutputStream = new BufferedOutputStream(
			_fileOutputStream, _DEFAULT_FILE_BUFFER_SIZE);
	}

	private void _renameFile(File srcFile, File targetFile) throws IOException {
		if (_log.isInfoEnabled()) {
			_log.info(
				String.format("Renaming file %s to %s ", srcFile, targetFile));
		}

		boolean result = srcFile.renameTo(targetFile);

		if (!result) {
			throw new IOException(
				String.format(
					"Unable to rename file %s to %s", srcFile, targetFile));
		}
	}

	private void _rollover() throws IOException {
		_bufferedOutputStream.close();

		File targetFile = new File(
			_storageStorageConfiguration.getPath() + "." +
				System.currentTimeMillis());

		_deleteFile(targetFile);

		_renameFile(_file, targetFile);

		_archiveFile(targetFile);

		_open();
	}

	private static final int _DEFAULT_FILE_BUFFER_SIZE = 8192;

	private static final Log _log = LogFactory.getLog(LocalStorage.class);

	private BufferedOutputStream _bufferedOutputStream;
	private File _file;
	private FileOutputStream _fileOutputStream;
	private GoogleStorageArchiver _googleStorageArchiver;
	private final StorageConfiguration _storageStorageConfiguration;

}