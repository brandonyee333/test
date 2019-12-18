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

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.osb.service.SupportResponseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
@JSON(strict = true)
public class OfferingEntryImpl extends OfferingEntryBaseImpl {

	public OfferingEntryImpl() {
	}

	public AccountEntry getAccountEntry() {

		// TODO

		return null;
	}

	@JSON
	public Date getActualStartDate() throws PortalException {
		OrderEntry orderEntry = getOrderEntry();

		return orderEntry.getActualStartDate();
	}

	public int getAvailableServers() throws PortalException {
		return getQuantity() - getLicenseKeysCount();
	}

	public String getKey() throws PortalException {
		StringBundler sb = new StringBundler(20);

		sb.append("productEntryId=");
		sb.append(getProductEntryId());
		sb.append(",supportResponseId=");
		sb.append(getSupportResponseId());
		sb.append(",type=");
		sb.append(getType());
		sb.append(",version=");
		sb.append(getVersion());
		sb.append(",licenses=");
		sb.append(isLicenses());
		sb.append(",licenseLifetime=");
		sb.append(getLicenseLifetime());
		sb.append(",sizing=");
		sb.append(getSizing());
		sb.append(",supportLifetime=");
		sb.append(getSupportLifetime());
		sb.append(",supportEndDate=");

		Date supportEndDate = getSupportEndDate();

		sb.append(supportEndDate.getTime());

		sb.append(",status=");
		sb.append(getStatus());

		return sb.toString();
	}

	public List<LicenseKey> getLicenseKeys() {
		return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(
			getOfferingEntryId());
	}

	public int getLicenseKeysCount() {
		return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeysCount(
			getOfferingEntryId(), false, true);
	}

	public OrderEntry getOrderEntry() throws PortalException {
		return OrderEntryLocalServiceUtil.getOrderEntry(getOrderEntryId());
	}

	public ProductEntry getProductEntry() {

		// TODO

		return null;
	}

	public String getProductType() {
		return _productType;
	}

	public String getSizingLabel() {
		return OfferingEntryConstants.getSizingLabel(getSizing());
	}

	@JSON
	public Date getStartDate() throws PortalException {
		OrderEntry orderEntry = getOrderEntry();

		return orderEntry.getStartDate();
	}

	public String getStatusLabel() {
		return OfferingEntryConstants.getStatusLabel(getStatus());
	}

	public SupportResponse getSupportResponse() throws PortalException {
		return SupportResponseLocalServiceUtil.getSupportResponse(
			getSupportResponseId());
	}

	public String getTypeLabel() {
		return OfferingEntryConstants.getTypeLabel(getType());
	}

	public boolean isInformationalOnly() {
		return _informationalOnly;
	}

	public void setInformationalOnly(boolean informationalOnly) {
		_informationalOnly = informationalOnly;
	}

	public void setProductType(String productType) {
		_productType = productType;
	}

	private boolean _informationalOnly;
	private String _productType;

}