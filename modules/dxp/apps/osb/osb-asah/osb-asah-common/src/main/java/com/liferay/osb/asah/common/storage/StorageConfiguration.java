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

import org.apache.avro.Schema;

/**
 * @author Marcellus Tavares
 * @author Riccardo Ferrari
 */
public class StorageConfiguration {

	public static Builder builder(String path) {
		return new Builder(path);
	}

	public long getChunkSize() {
		return _chunkSize;
	}

	public FileFormat getFileFormat() {
		return _fileFormat;
	}

	public Schema getFileSchema() {
		return _fileSchema;
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

			_storageConfiguration._path = path;
		}

		public StorageConfiguration build() {
			if (_storageConfiguration.getChunkSize() == 0) {
				_storageConfiguration._chunkSize = _DEFAULT_CHUNK_SIZE;
			}

			if ((_storageConfiguration._fileFormat ==
					FileFormat.SNAPPY_PARQUET) &&
				(_storageConfiguration._fileSchema == null)) {

				throw new IllegalStateException(
					"Schema is required for Parquet file format");
			}

			return _storageConfiguration;
		}

		public Builder chunkSize(long chunkSize) {
			_storageConfiguration._chunkSize = chunkSize;

			return this;
		}

		public Builder fileFormat(FileFormat fileFormat) {
			_storageConfiguration._fileFormat = fileFormat;

			return this;
		}

		public Builder fileSchema(Schema fileSchema) {
			_storageConfiguration._fileSchema = fileSchema;

			return this;
		}

		public Builder googleBucket(String googleBucket) {
			if (googleBucket == null) {
				throw new IllegalArgumentException("Google bucket is null");
			}

			_storageConfiguration._googleBucket = googleBucket;

			return this;
		}

		public Builder googleBucketFolder(String googleBucketFolder) {
			_storageConfiguration._googleBucketFolder = googleBucketFolder;

			return this;
		}

		private static final long _DEFAULT_CHUNK_SIZE = 64 * 1024 * 1024;

		private StorageConfiguration _storageConfiguration =
			new StorageConfiguration();

	}

	public enum FileFormat {

		JSON, SNAPPY_PARQUET

	}

	private StorageConfiguration() {
	}

	private long _chunkSize;
	private FileFormat _fileFormat = FileFormat.JSON;
	private Schema _fileSchema;
	private String _googleBucket;
	private String _googleBucketFolder;
	private String _path;

}