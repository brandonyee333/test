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

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.OfferingEntryGroup;
import com.liferay.osb.model.OfferingEntryGroupFactoryUtil;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.osb.service.SupportResponseLocalServiceUtil;
import com.liferay.osb.util.comparator.OfferingEntryPKComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class OfferingEntryImpl extends OfferingEntryBaseImpl {

	public OfferingEntryImpl() {
	}

	@JSON
	public AccountEntry getAccountEntry() throws PortalException {
		return AccountEntryLocalServiceUtil.getAccountEntry(
			getAccountEntryId());
	}

	@JSON
	public Date getActualStartDate() throws PortalException {
		OrderEntry orderEntry = getOrderEntry();

		return orderEntry.getActualStartDate();
	}

	public int getAvailableServers() throws PortalException {
		return getQuantity() - getLicenseKeysCount();
	}

	@JSON
	public String getKey() throws PortalException {
		StringBundler sb = new StringBundler(18);

		sb.append("supportEndDate=");

		Date supportEndDate = getSupportEndDate();

		sb.append(supportEndDate.getTime());

		sb.append(",productEntryId=");
		sb.append(getProductEntryId());
		sb.append(",supportResponseId=");
		sb.append(getSupportResponseId());
		sb.append(",licenses=");
		sb.append(isLicenses());
		sb.append(",licenseLifetime=");
		sb.append(getLicenseLifetime());
		sb.append(",supportLifetime=");
		sb.append(getSupportLifetime());
		sb.append(",type=");
		sb.append(getType());

		AccountEntry accountEntry = getAccountEntry();

		if (accountEntry.getType() == AccountEntryConstants.TYPE_INDIVIDUAL) {
			sb.append(",userId=");
			sb.append(getUserId());
		}

		sb.append(",status=");
		sb.append(getStatus());

		return sb.toString();
	}

	@JSON
	public List<LicenseKey> getLicenseKeys() {
		return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(
			getOfferingEntryId());
	}

	@JSON
	public int getLicenseKeysCount() {
		return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeysCount(
			getOfferingEntryId(), false, true);
	}

	public OfferingEntryGroup getOfferingEntryGroup() throws PortalException {
		long userId = 0;

		if (getType() == OfferingEntryConstants.TYPE_TRIAL) {
			userId = getUserId();
		}

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put(
			"validLicense",
			new Long[] {getProductEntryId(), getProductEntryId()});
		params.put("version", getVersion());

		List<OfferingEntry> offeringEntries =
			OfferingEntryLocalServiceUtil.search(
				userId, getAccountEntryId(), new int[] {getType()}, new int[0],
				0, 0, 0, 0, 0, 0, params, true, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new OfferingEntryPKComparator(true));

		Map<String, OfferingEntryGroup> offeringEntryGroupMap =
			OfferingEntryGroupFactoryUtil.createOfferingEntryGroupMap(
				offeringEntries);

		return offeringEntryGroupMap.get(getKey());
	}

	public OrderEntry getOrderEntry() throws PortalException {
		return OrderEntryLocalServiceUtil.getOrderEntry(getOrderEntryId());
	}

	public ProductEntry getProductEntry() throws PortalException {
		return ProductEntryLocalServiceUtil.getProductEntry(
			getProductEntryId());
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

}