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

import com.liferay.osb.asah.common.storage.StorageWriter;
import com.liferay.osb.asah.common.storage.StorageWriterConfiguration;
import com.liferay.osb.asah.common.storage.StorageWriterFactory;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class StorageWriterFactoryImpl implements StorageWriterFactory {

	@Override
	public StorageWriter getStorageWriter(
		StorageWriterConfiguration storageWriterConfiguration) {

		StorageWriter storageWriter = _storageWriters.get(
			storageWriterConfiguration);

		if (storageWriter != null) {
			return storageWriter;
		}

		LocalStorageWriter localStorageWriter = new LocalStorageWriter(
			storageWriterConfiguration);

		localStorageWriter.setGoogleStorageArchiver(_googleStorageArchiver);

		_storageWriters.put(storageWriterConfiguration, localStorageWriter);

		return localStorageWriter;
	}

	@PreDestroy
	private void _destroy() {
		for (StorageWriter storageWriter : _storageWriters.values()) {
			storageWriter.close();
		}
	}

	@Autowired(required = false)
	private GoogleStorageArchiver _googleStorageArchiver;

	private final Map<StorageWriterConfiguration, StorageWriter>
		_storageWriters = new HashMap<>();

}