/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.store.ignore.duplicates.wrapper.internal;

import com.liferay.document.library.kernel.store.Store;
import com.liferay.document.library.kernel.store.StoreWrapper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(
	immediate = true,
	property = "store.type=com.liferay.portal.store.file.system.FileSystemStore",
	service = StoreWrapper.class
)
public class IgnoreDuplicatesFileSystemStoreWrapper implements StoreWrapper {

	@Override
	public Store wrap(Store store) {
		return new IgnoreDuplicatesStore(store);
	}

	@Reference(
		target = "(store.type=com.liferay.portal.store.file.system.FileSystemStore)"
	)
	private Store _store;

}