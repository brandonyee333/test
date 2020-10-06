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
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;

import java.util.Objects;

import javax.annotation.PostConstruct;

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
		_cleanUp(ServiceConstants.LCP_PROJECT_ID + "/");
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private void _cleanUp(String directoryPrefix) {
		if (_isDirectorySkipped(directoryPrefix)) {
			return;
		}

		Blob latestBlob = null;

		for (Blob blob : _listBucket(directoryPrefix)) {
			if (Objects.equals(blob.getName(), directoryPrefix)) {
				continue;
			}

			if (blob.isDirectory()) {
				_cleanUp(blob.getName());
			}
			else {
				if (latestBlob == null) {
					latestBlob = blob;
				}
				else {
					if (latestBlob.getCreateTime() > blob.getCreateTime()) {
						_deleteBlob(blob);
					}
					else {
						_deleteBlob(latestBlob);

						latestBlob = blob;
					}
				}
			}
		}

		if (_log.isDebugEnabled() && (latestBlob != null)) {
			_log.debug("Most recent blob: " + latestBlob.getName());
		}
	}

	private void _deleteBlob(Blob blob) {
		if (_log.isDebugEnabled()) {
			_log.debug("Removing blob: " + blob.getName());
		}

		blob.delete();
	}

	@PostConstruct
	private void _init() {
		StorageOptions storageOptions = StorageOptions.getDefaultInstance();

		_storage = storageOptions.getService();
	}

	private boolean _isDirectorySkipped(String directoryPrefix) {
		String directoryName = directoryPrefix.substring(
			0, directoryPrefix.length() - 1);

		for (String skipDirectoryName : _SKIP_DIRECTORY_NAMES) {
			if (directoryName.endsWith(skipDirectoryName)) {
				return true;
			}
		}

		return false;
	}

	private Iterable<Blob> _listBucket(String directoryPrefix) {
		Bucket bucket = _storage.get(_dxpBatchEntitiesBucket);

		Page<Blob> blobs = bucket.list(
			Storage.BlobListOption.prefix(directoryPrefix),
			Storage.BlobListOption.currentDirectory());

		return blobs.iterateAll();
	}

	private static final String[] _SKIP_DIRECTORY_NAMES = {
		"com.liferay.headless.commerce.machine.learning.dto.v1_0." +
			"ProductContentRecommendation",
		"com.liferay.headless.commerce.machine.learning.dto.v1_0." +
			"ProductInteractionRecommendation",
		"com.liferay.headless.commerce.machine.learning.dto.v1_0." +
			"UserRecommendation"
	};

	private static final Log _log = LogFactory.getLog(
		DeleteDXPBatchEntitiesNanite.class);

	@Value(
		"${osb.asah.dxp.batch.entities.google.bucket:analytics-cloud-dxp-batch-entities}"
	)
	private String _dxpBatchEntitiesBucket;

	private Storage _storage;

}