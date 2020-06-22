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

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageException;
import com.google.cloud.storage.StorageOptions;

import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.file.Files;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@ConditionalOnGoogleApplicationCredentials
public class GoogleStorageArchiver {

	public void archiveAsync(String bucket, String bucketPath, File file) {
		_executorService.submit(() -> _archive(bucket, bucketPath, file));
	}

	public File readSparkJobResult(
			String bucket, String pathPrefix, Date lastUpdate)
		throws Exception {

		BlobId successBlobId = BlobId.of(bucket, pathPrefix + "/_SUCCESS");

		Blob successBlob = _storage.get(successBlobId);

		if (!successBlob.exists()) {
			_log.error("_SUCCESS file missing");

			throw new IOException();
		}

		if ((lastUpdate != null) &&
			(lastUpdate.getTime() > successBlob.getCreateTime())) {

			if (_log.isDebugEnabled()) {
				_log.debug(
					String.format(
						"Bucket '%s' is older than %s", successBlob.getName(),
						lastUpdate));
			}

			return null;
		}

		Page<Blob> blobs = _storage.list(
			bucket, Storage.BlobListOption.prefix(pathPrefix));

		String tempFileName = _createTempFileName("export", ".zip");

		ZipOutputStream zipOutputStream = new ZipOutputStream(
			new FileOutputStream(tempFileName));

		File file = new File("export.json");

		zipOutputStream.putNextEntry(new ZipEntry(file.getName()));

		for (Blob blob : blobs.iterateAll()) {
			String blobName = blob.getName();

			if (!blobName.endsWith(".json")) {
				continue;
			}

			try {
				byte[] bytes = blob.getContent();

				zipOutputStream.write(bytes, 0, bytes.length);
			}
			catch (IOException e) {
				_log.error(e.getMessage(), e);
			}
		}

		zipOutputStream.closeEntry();

		zipOutputStream.close();

		return new File(tempFileName);
	}

	private void _archive(String bucket, String bucketPath, File file) {
		byte[] content = null;

		try {
			content = Files.readAllBytes(file.toPath());
		}
		catch (IOException ioe) {
			_log.error(
				String.format("Unable to read file %s bytes", file), ioe);

			return;
		}

		Blob blob = _uploadBlob(
			0,
			_buildBlobInfo(
				bucket, _getBucketFilePath(bucketPath, file.getName())),
			content);

		if (blob == null) {
			_log.error(
				String.format(
					"Unable to upload file %s to the bucket %s", file, bucket));

			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Successfully uploaded file %s to the bucket %s", file,
					bucket));
		}

		boolean result = file.delete();

		if (result && _log.isDebugEnabled()) {
			_log.debug("Deleted file " + file);
		}
	}

	private BlobInfo _buildBlobInfo(String bucket, String fileName) {
		BlobInfo.Builder builder = BlobInfo.newBuilder(
			BlobId.of(bucket, fileName));

		return builder.build();
	}

	private String _createTempFileName(String prefix, String extension) {
		StringBuilder sb = new StringBuilder(7);

		sb.append(System.getProperty(_TMP_DIR));
		sb.append(File.separator);
		sb.append(prefix);
		sb.append("-");
		sb.append(System.currentTimeMillis());
		sb.append(extension);

		return sb.toString();
	}

	@PreDestroy
	private void _destroy() {
		_executorService.shutdown();

		try {
			_executorService.awaitTermination(1, TimeUnit.MINUTES);
		}
		catch (InterruptedException ie) {
			_log.error(
				"Interrupted while waiting for termination of executor", ie);
		}
	}

	private String _getBucketFilePath(String bucketPath, String fileName) {
		if (bucketPath != null) {
			return String.format("%s/%s", bucketPath, fileName);
		}

		return fileName;
	}

	@PostConstruct
	private void _init() {
		StorageOptions storageOptions = StorageOptions.getDefaultInstance();

		_storage = storageOptions.getService();
	}

	private Blob _uploadBlob(int attempt, BlobInfo blobInfo, byte[] content) {
		try {
			return _storage.create(blobInfo, content);
		}
		catch (StorageException se) {
			_log.error(
				String.format(
					"Unable to upload blob %s to the bucket %s",
					blobInfo.getName(), blobInfo.getBucket()),
				se);

			if (attempt < 3) {
				_uploadBlob(++attempt, blobInfo, content);
			}
		}

		return null;
	}

	private static final String _TMP_DIR = "java.io.tmpdir";

	private static final Log _log = LogFactory.getLog(
		GoogleStorageArchiver.class);

	private final ExecutorService _executorService =
		Executors.newSingleThreadExecutor();
	private Storage _storage;

}