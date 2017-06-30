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

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.NoSuchCorpEntryException;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.model.DeveloperEntryConstants;
import com.liferay.osb.service.AssetAttachmentServiceUtil;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Region;
import com.liferay.portal.model.User;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.Date;
import java.util.List;

/**
 * @author Ryan Park
 * @author Douglas Wong
 */
public class DeveloperEntryImpl extends DeveloperEntryBaseImpl {

	public DeveloperEntryImpl() {
	}

	public Address getAddress() throws PortalException, SystemException {
		if (_address == null) {
			_address = AddressLocalServiceUtil.getAddress(getAddressId());
		}

		return _address;
	}

	@AutoEscape
	public String getCity() {
		try {
			Address address = getAddress();

			return address.getCity();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	public Country getCountry() {
		try {
			Address address = getAddress();

			return address.getCountry();
		}
		catch (Exception e) {
			return null;
		}
	}

	public String getCountryCode() {
		try {
			Country country = getCountry();

			return country.getA2();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	public long getCountryId() {
		try {
			Address address = getAddress();

			return address.getCountryId();
		}
		catch (Exception e) {
			return 0;
		}
	}

	public List<AssetAttachment> getDocumentAssetAttachments()
		throws PortalException, SystemException {

		return AssetAttachmentServiceUtil.getAssetAttachments(
			DeveloperEntry.class.getName(), getDeveloperEntryId(),
			AssetAttachmentConstants.TYPE_DOCUMENT, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public double getFatcaWithholdingPercentageValue()
		throws PortalException, SystemException {

		if (getFatcaWithholdingPercentage() ==
				DeveloperEntryConstants.FATCA_WITHOLDING_PERCENTAGE_DEFAULT) {

			String countryCode = getCountryCode();

			if (countryCode.equals("US")) {
				return 0;
			}
			else {
				return PortletPropsValues.
					MARKETPLACE_FATCA_WITHHOLDING_PERCENTAGE_DEFAULT;
			}
		}

		return getFatcaWithholdingPercentage();
	}

	public Group getGroup() throws PortalException, SystemException {
		if (isUser()) {
			User user = UserLocalServiceUtil.getUser(getUserId());

			return user.getGroup();
		}
		else {
			CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(
				getDossieraAccountKey());

			return corpEntry.getGroup();
		}
	}

	public long getGroupId() throws PortalException, SystemException {
		Group group = getGroup();

		return group.getGroupId();
	}

	@AutoEscape
	public String getName() throws PortalException, SystemException {
		if (Validator.isNotNull(_name)) {
			return _name;
		}

		if (isCompany()) {
			if (Validator.isNotNull(getDossieraAccountKey())) {
				try {
					CorpEntry corpEntry =
						CorpEntryLocalServiceUtil.getCorpEntry(
							getDossieraAccountKey());

					_name = corpEntry.getName();
				}
				catch (NoSuchCorpEntryException nscee) {
					_name = getLegalEntityName();
				}
			}
			else {
				_name = getLegalEntityName();
			}
		}
		else if (isUser()) {
			_name = PortalUtil.getUserName(getUserId(), StringPool.BLANK);

			if (Validator.isNull(_name)) {
				_name = getFirstName() + StringPool.SPACE + getLastName();
			}
		}

		return _name;
	}

	public String getProfileURL(ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		if (getStatus() != WorkflowConstants.STATUS_APPROVED) {
			return StringPool.BLANK;
		}

		if (isCompany()) {
			return PortalUtil.getLayoutFullURL(
				getGroupId(), "1_WAR_osbcorpprofileportlet");
		}
		else if (isUser()) {
			User user = UserLocalServiceUtil.getUser(getUserId());

			return user.getDisplayURL(themeDisplay);
		}

		return StringPool.BLANK;
	}

	public Region getRegion() {
		try {
			Address address = getAddress();

			return address.getRegion();
		}
		catch (Exception e) {
			return null;
		}
	}

	public long getRegionId() {
		try {
			Address address = getAddress();

			return address.getRegionId();
		}
		catch (Exception e) {
			return 0;
		}
	}

	public String getStatusLabel() {
		return WorkflowConstants.toLabel(getStatus());
	}

	@AutoEscape
	public String getStreet1() {
		try {
			Address address = getAddress();

			return address.getStreet1();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	@AutoEscape
	public String getStreet2() {
		try {
			Address address = getAddress();

			return address.getStreet2();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	@AutoEscape
	public String getStreet3() {
		try {
			Address address = getAddress();

			return address.getStreet3();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	@AutoEscape
	public String getZip() {
		try {
			Address address = getAddress();

			return address.getZip();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isCompany() {
		if (getType() == DeveloperEntryConstants.TYPE_COMPANY) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDomainStatusApproved() {
		if (getDomainStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isLiferayInc() {
		if (getDeveloperEntryId() ==
				OSBConstants.DEVELOPER_ENTRY_LIFERAY_INC_ID) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isSubscriptionApproved() {
		return !isSubscriptionExpired();
	}

	public boolean isSubscriptionExpired() {
		if (getSubscriptionExpirationDate() == null) {
			return true;
		}

		if (DateUtil.compareTo(
				getSubscriptionExpirationDate(), new Date()) > 0) {

			return false;
		}
		else {
			return true;
		}
	}

	public boolean isUser() {
		if (getType() == DeveloperEntryConstants.TYPE_USER) {
			return true;
		}
		else {
			return false;
		}
	}

	private Address _address;
	private String _name;

}