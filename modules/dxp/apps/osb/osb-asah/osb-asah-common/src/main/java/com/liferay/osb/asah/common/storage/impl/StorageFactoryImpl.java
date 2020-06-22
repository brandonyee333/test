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

import com.liferay.osb.asah.common.storage.Storage;
import com.liferay.osb.asah.common.storage.StorageConfiguration;
import com.liferay.osb.asah.common.storage.StorageFactory;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class StorageFactoryImpl implements StorageFactory {

	@Override
	public Storage getStorage(StorageConfiguration storageConfiguration) {
		Storage storage = _storageInstances.get(storageConfiguration);

		if (storage != null) {
			return storage;
		}

		LocalStorage localStorage = new LocalStorage(storageConfiguration);

		localStorage.setGoogleStorageArchiver(_googleStorageArchiver);

		_storageInstances.put(storageConfiguration, localStorage);

		return localStorage;
	}

	@PreDestroy
	private void _destroy() {
		for (Storage storage : _storageInstances.values()) {
			storage.close();
		}
	}

	@Autowired(required = false)
	private GoogleStorageArchiver _googleStorageArchiver;

	private final Map<StorageConfiguration, Storage> _storageInstances =
		new HashMap<>();

}