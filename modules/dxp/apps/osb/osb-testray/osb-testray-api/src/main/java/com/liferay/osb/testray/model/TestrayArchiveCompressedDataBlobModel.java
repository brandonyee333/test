/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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