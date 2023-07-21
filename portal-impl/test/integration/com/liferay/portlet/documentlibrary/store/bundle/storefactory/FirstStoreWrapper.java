/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.store.bundle.storefactory;

import com.liferay.document.library.kernel.store.Store;
import com.liferay.document.library.kernel.store.StoreWrapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true,
	property = {"service.ranking:Integer=1", "store.type=test"},
	service = StoreWrapper.class
)
public class FirstStoreWrapper implements StoreWrapper {

	@Override
	public Store wrap(Store store) {
		return new FirstDelegatorStore(store);
	}

	public static class FirstDelegatorStore extends DelegatorStore {

		public FirstDelegatorStore(Store store) {
			super(store);
		}

	}

}