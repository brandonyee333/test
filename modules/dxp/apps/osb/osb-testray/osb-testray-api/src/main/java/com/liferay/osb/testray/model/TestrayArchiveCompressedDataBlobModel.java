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

package com.liferay.osb.testray.model;

import java.sql.Blob;

/**
 * The Blob model class for lazy loading the compressedData column in TestrayArchive.
 *
 * @author Ethan Bustad
 * @see TestrayArchive
 * @generated
 */
public class TestrayArchiveCompressedDataBlobModel {

	public TestrayArchiveCompressedDataBlobModel() {
	}

	public TestrayArchiveCompressedDataBlobModel(long testrayArchiveId) {
		_testrayArchiveId = testrayArchiveId;
	}

	public TestrayArchiveCompressedDataBlobModel(
		long testrayArchiveId, Blob compressedDataBlob) {

		_testrayArchiveId = testrayArchiveId;
		_compressedDataBlob = compressedDataBlob;
	}

	public long getTestrayArchiveId() {
		return _testrayArchiveId;
	}

	public void setTestrayArchiveId(long testrayArchiveId) {
		_testrayArchiveId = testrayArchiveId;
	}

	public Blob getCompressedDataBlob() {
		return _compressedDataBlob;
	}

	public void setCompressedDataBlob(Blob compressedDataBlob) {
		_compressedDataBlob = compressedDataBlob;
	}

	private long _testrayArchiveId;
	private Blob _compressedDataBlob;

}