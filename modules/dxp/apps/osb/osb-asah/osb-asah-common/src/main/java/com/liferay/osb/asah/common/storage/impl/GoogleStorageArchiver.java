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

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageException;
import com.google.cloud.storage.StorageOptions;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

	public void archiveAsync(String bucket, File file) {
		_executorService.submit(() -> _archive(bucket, file));
	}

	private void _archive(String bucket, File file) {
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
			0, _buildBlobInfo(bucket, file.getName()), content);

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
		String objectName = String.format(
			"%s/%s", ServiceConstants.LCP_PROJECT_ID, fileName);

		BlobInfo.Builder builder = BlobInfo.newBuilder(
			BlobId.of(bucket, objectName));

		return builder.build();
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

	private Blob _uploadBlob(int attempt, BlobInfo blobInfo, byte[] content) {
		try {
			StorageOptions storageOptions = StorageOptions.getDefaultInstance();

			Storage storage = storageOptions.getService();

			return storage.create(blobInfo, content);
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

	private static final Log _log = LogFactory.getLog(
		GoogleStorageArchiver.class);

	private final ExecutorService _executorService =
		Executors.newSingleThreadExecutor();

}