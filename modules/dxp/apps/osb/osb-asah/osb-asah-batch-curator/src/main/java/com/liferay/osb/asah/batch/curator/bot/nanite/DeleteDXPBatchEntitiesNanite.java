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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Riccardo Ferrari
 */
@Component
@ConditionalOnGoogleApplicationCredentials
public class DeleteDXPBatchEntitiesNanite extends BaseNanite {

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		_scanDirectory(ProjectIdThreadLocal.getProjectId() + "/");
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private void _cleanUp(String directoryPrefix) {
		String latestUploadFolderName = _getLatestUploadFolderName(
			directoryPrefix);

		if (latestUploadFolderName == null) {
			return;
		}

		for (Blob blob :
				_listBucket(Storage.BlobListOption.prefix(directoryPrefix))) {

			String blobName = blob.getName();

			if (Objects.equals(blobName, directoryPrefix)) {
				continue;
			}

			if (blobName.contains(latestUploadFolderName)) {
				continue;
			}

			_deleteBlob(blob);
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"%s latest upload in folder: %s", directoryPrefix,
					latestUploadFolderName));
		}
	}

	private void _deleteBlob(Blob blob) {
		if (_log.isDebugEnabled()) {
			_log.debug("Removing blob: " + blob.getName());
		}

		blob.delete();
	}

	private String _getLatestUploadFolderName(String directoryPrefix) {
		Blob latestBlob = null;

		for (Blob blob :
				_listBucket(Storage.BlobListOption.prefix(directoryPrefix))) {

			if (Objects.equals(blob.getName(), directoryPrefix)) {
				continue;
			}

			String blobName = blob.getName();

			if (blobName.endsWith("_SUCCESS")) {
				if (latestBlob == null) {
					latestBlob = blob;
				}
				else if (blob.getCreateTime() > latestBlob.getCreateTime()) {
					_deleteBlob(latestBlob);

					latestBlob = blob;
				}
			}
		}

		if (latestBlob == null) {
			return null;
		}

		String latestBlobName = latestBlob.getName();

		String[] parts = latestBlobName.split("/");

		return parts[parts.length - 2];
	}

	@PostConstruct
	private void _init() {
		StorageOptions storageOptions = StorageOptions.getDefaultInstance();

		_storage = storageOptions.getService();
	}

	private boolean _isBatchUploadDirectory(String directoryPrefix) {
		String directoryName = directoryPrefix.substring(
			0, directoryPrefix.length() - 1);

		for (String batchUploadDirectoryName : _BATCH_UPLOAD_DIRECTORY_NAMES) {
			if (directoryName.endsWith(batchUploadDirectoryName)) {
				return true;
			}
		}

		return false;
	}

	private Iterable<Blob> _listBucket(
		Storage.BlobListOption... blobListOptions) {

		Page<Blob> blobs = _storage.list(
			StringUtils.replace(
				_dxpBatchEntitiesBucketTemplate, "{region}",
				System.getenv("LCP_PROJECT_CLUSTER")),
			blobListOptions);

		return blobs.iterateAll();
	}

	private void _scanDirectory(String directoryPrefix) {
		for (Blob blob :
				_listBucket(
					Storage.BlobListOption.prefix(directoryPrefix),
					Storage.BlobListOption.currentDirectory())) {

			String blobName = blob.getName();

			if (Objects.equals(blobName, directoryPrefix)) {
				continue;
			}

			if (blob.isDirectory()) {
				if (_isBatchUploadDirectory(blobName)) {
					_cleanUp(blobName);
				}

				_scanDirectory(blobName);
			}
		}
	}

	private static final String[] _BATCH_UPLOAD_DIRECTORY_NAMES = {
		"com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product",
		"com.liferay.headless.commerce.admin.order.dto.v1_0.Order"
	};

	private static final Log _log = LogFactory.getLog(
		DeleteDXPBatchEntitiesNanite.class);

	@Value(
		"${osb.asah.dxp.batch.entities.google.bucket:analytics-cloud-dxp-batch-entities-{region}}"
	)
	private String _dxpBatchEntitiesBucketTemplate;

	private Storage _storage;

}