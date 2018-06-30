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
@JSON(strict = true)
public class OfferingEntryImpl extends OfferingEntryBaseImpl {

	public OfferingEntryImpl() {
	}

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

	public String getKey() throws PortalException {
		StringBundler sb = new StringBundler(22);

		AccountEntry accountEntry = getAccountEntry();

		if (accountEntry.getType() == AccountEntryConstants.TYPE_INDIVIDUAL) {
			sb.append("userId=");
			sb.append(getUserId());
		}

		sb.append(",productEntryId=");
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

	public OfferingEntryGroup getOfferingEntryGroup() throws PortalException {
		long userId = 0;

		if (getType() == OfferingEntryConstants.TYPE_TRIAL) {
			userId = getUserId();
		}

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put(
			"validLicense",
			new Long[] {getProductEntryId(), getProductEntryId()});

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

	@JSON
	public ProductEntry getProductEntry() throws PortalException {
		return ProductEntryLocalServiceUtil.getProductEntry(
			getProductEntryId());
	}

	public String getSizingLabel() {
		return OfferingEntryConstants.getSizingLabel(getSizing());
	}

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