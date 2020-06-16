/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.testray.model;

import aQute.bnd.annotation.ProviderType;

import java.sql.Blob;

/**
 * The Blob model class for lazy loading the compressedData column in TestrayArchive.
 *
 * @author Ethan Bustad
 * @see TestrayArchive
 * @generated
 */
@ProviderType
public class TestrayArchiveCompressedDataBlobModel {
	public TestrayArchiveCompressedDataBlobModel() {
	}

	public TestrayArchiveCompressedDataBlobModel(long testrayArchiveId) {
		_testrayArchiveId = testrayArchiveId;
	}

	public TestrayArchiveCompressedDataBlobModel(long testrayArchiveId,
		Blob compressedDataBlob) {
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