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

package com.liferay.osb.asah.common.storage;

/**
 * @author Marcellus Tavares
 * @author Riccardo Ferrari
 */
public class StorageWriterConfiguration {

	public static Builder builder(String path) {
		return new Builder(path);
	}

	public long getChunkSize() {
		return _chunkSize;
	}

	public String getGoogleBucket() {
		return _googleBucket;
	}

	public String getGoogleBucketFolder() {
		return _googleBucketFolder;
	}

	public String getPath() {
		return _path;
	}

	public static class Builder {

		public Builder(String path) {
			if (path == null) {
				throw new IllegalArgumentException("Path is null");
			}

			_storageWriterConfiguration._path = path;
		}

		public StorageWriterConfiguration build() {
			if (_storageWriterConfiguration.getChunkSize() == 0) {
				_storageWriterConfiguration._chunkSize = _DEFAULT_CHUNK_SIZE;
			}

			return _storageWriterConfiguration;
		}

		public Builder chunkSize(long chunkSize) {
			_storageWriterConfiguration._chunkSize = chunkSize;

			return this;
		}

		public Builder googleBucket(String googleBucket) {
			if (googleBucket == null) {
				throw new IllegalArgumentException("Google bucket is null");
			}

			_storageWriterConfiguration._googleBucket = googleBucket;

			return this;
		}

		public Builder googleBucketFolder(String googleBucketFolder) {
			_storageWriterConfiguration._googleBucketFolder =
				googleBucketFolder;

			return this;
		}

		private static final long _DEFAULT_CHUNK_SIZE = 10 * 1024 * 1024;

		private StorageWriterConfiguration _storageWriterConfiguration =
			new StorageWriterConfiguration();

	}

	private StorageWriterConfiguration() {
	}

	private long _chunkSize;
	private String _googleBucket;
	private String _googleBucketFolder;
	private String _path;

}