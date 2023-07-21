/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactAccountView;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public interface ContactAccountViewWebService {

	public List<ContactAccountView>
			getContactByUuidContactUuidContactAccountViewsPage(
				String contactUuid, int page, int pageSize)
		throws Exception;

}