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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.OfferingDefinitionConstants;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.osb.service.SupportResponseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class OfferingDefinitionImpl extends OfferingDefinitionBaseImpl {

	public OfferingDefinitionImpl() {
	}

	public String getLicensesLabel() {
		if (!isLicenses()) {
			return StringPool.BLANK;
		}
		else {
			return String.valueOf(getQuantity());
		}
	}

	public String getMaxConcurrentUsersLabel() {
		return OfferingDefinitionConstants.getMaxConcurrentUsersLabel(
			getMaxConcurrentUsers());
	}

	public String getMaxUsersLabel() {
		return OfferingDefinitionConstants.getMaxUsersLabel(getMaxUsers());
	}

	@JSON
	public ProductEntry getProductEntry() throws PortalException {
		return ProductEntryLocalServiceUtil.getProductEntry(
			getProductEntryId());
	}

	public int getQuantity() {
		if (isUnlimitedLicenses()) {
			return OfferingEntryConstants.QUANTITY_UNLIMITED;
		}
		else {
			return 1;
		}
	}

	public SupportResponse getSupportResponse() throws PortalException {
		return SupportResponseLocalServiceUtil.getSupportResponse(
			getSupportResponseId());
	}

	public String getSupportTicketsLabel() {
		if (isSupportTickets()) {
			return "unlimited";
		}
		else {
			return StringPool.BLANK;
		}
	}

}