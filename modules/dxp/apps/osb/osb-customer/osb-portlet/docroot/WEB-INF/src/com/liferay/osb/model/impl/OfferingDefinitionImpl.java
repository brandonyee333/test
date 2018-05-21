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