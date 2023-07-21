/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface ProductEntryFinder {

	public int countByName(
		String name, java.util.LinkedHashMap<String, Object> params);

	public java.util.List<com.liferay.osb.customer.admin.model.ProductEntry>
		findByName(
			String name, java.util.LinkedHashMap<String, Object> params,
			int start, int end);

}