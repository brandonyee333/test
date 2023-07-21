/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.model.impl;

import com.liferay.osb.customer.admin.constants.LicenseEntryConstants;
import com.liferay.osb.customer.admin.model.ProductEntry;
import com.liferay.osb.customer.admin.service.ProductEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class LicenseEntryImpl extends LicenseEntryBaseImpl {

	public LicenseEntryImpl() {
	}

	public ProductEntry getProductEntry() throws PortalException {
		return ProductEntryLocalServiceUtil.getProductEntry(
			getProductEntryId());
	}

	public String getVersionLabel() {
		return LicenseEntryConstants.getVersionLabel(
			getVersionMin(), getVersionMax());
	}

}