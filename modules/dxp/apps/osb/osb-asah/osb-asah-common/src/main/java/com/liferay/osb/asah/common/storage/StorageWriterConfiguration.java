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
 */
public class StorageWriterConfiguration {

	public static Builder builder(String path) {
		return new Builder(path);
	}

	public String getGoogleBucket() {
		return _googleBucket;
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
			return _storageWriterConfiguration;
		}

		public Builder googleBucket(String googleBucket) {
			if (googleBucket == null) {
				throw new IllegalArgumentException("Google bucket is null");
			}

			_storageWriterConfiguration._googleBucket = googleBucket;

			return this;
		}

		private StorageWriterConfiguration _storageWriterConfiguration =
			new StorageWriterConfiguration();

	}

	private StorageWriterConfiguration() {
	}

	private String _googleBucket;
	private String _path;

}