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

package com.liferay.osb.marketplace.util;

import com.liferay.ecommerce.model.ECDocumentEntry;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * @author Douglas Wong
 * @author Joan Kim
 */
public class ECDocumentEntryExtraSettings {

	public ECDocumentEntryExtraSettings() {
		_extraSettingsProperties = new UnicodeProperties(true);
	}

	public ECDocumentEntryExtraSettings(ECDocumentEntry ecDocumentEntry) {
		_extraSettingsProperties = ecDocumentEntry.getExtraSettingsProperties();
	}

	public long getAddressId() {
		return GetterUtil.getLong(
			_extraSettingsProperties.getProperty("marketplaceAddressId"));
	}

	public long getAppEntryId() {
		return GetterUtil.getLong(
			_extraSettingsProperties.getProperty("marketplaceAppEntryId"));
	}

	public long getClassPK() {
		return GetterUtil.getLong(
			_extraSettingsProperties.getProperty("marketplaceClassPK"));
	}

	public String getCompanyName() {
		return GetterUtil.getString(
			_extraSettingsProperties.getProperty("companyName"));
	}

	public long getCountryId() {
		return GetterUtil.getLong(
			_extraSettingsProperties.getProperty("marketplaceCountryId"));
	}

	public long getDeveloperEntryId() {
		return GetterUtil.getLong(
			_extraSettingsProperties.getProperty(
				"marketplaceDeveloperEntryId"));
	}

	public int getDomain() {
		return GetterUtil.getInteger(
			_extraSettingsProperties.getProperty("marketplaceDomain"));
	}

	public User getEndUser() throws SystemException {
		return UserLocalServiceUtil.fetchUser(getEndUserId());
	}

	public String getEndUserCompanyName() {
		return GetterUtil.getString(
			_extraSettingsProperties.getProperty(
				"marketplaceEndUserCompanyName"));
	}

	public String getEndUserEmailAddress() {
		return GetterUtil.getString(
			_extraSettingsProperties.getProperty(
				"marketplaceEndUserEmailAddress"));
	}

	public String getEndUserFax() {
		return GetterUtil.getString(
			_extraSettingsProperties.getProperty("marketplaceEndUserFax"));
	}

	public String getEndUserFirstName() {
		return GetterUtil.getString(
			_extraSettingsProperties.getProperty(
				"marketplaceEndUserFirstName"));
	}

	public long getEndUserId() {
		return GetterUtil.getLong(
			_extraSettingsProperties.getProperty("marketplaceEndUserId"));
	}

	public String getEndUserLastName() {
		return GetterUtil.getString(
			_extraSettingsProperties.getProperty("marketplaceEndUserLastName"));
	}

	public String getEndUserPhone() {
		return GetterUtil.getString(
			_extraSettingsProperties.getProperty("marketplaceEndUserPhone"));
	}

	public String getEndUserVatNumber() {
		return GetterUtil.getString(
			_extraSettingsProperties.getProperty(
				"marketplaceEndUserVatNumber"));
	}

	public long getEulaContractEntryId() {
		return GetterUtil.getLong(
			_extraSettingsProperties.getProperty(
				"marketplaceEulaContractEntryId"));
	}

	public UnicodeProperties getExtraSettingsProperties() {
		return _extraSettingsProperties;
	}

	public double getFatcaWithholdingAmount() {
		return GetterUtil.getDouble(
			_extraSettingsProperties.getProperty(
				"marketplaceFatcaWithholdingAmount"));
	}

	public double getFatcaWithholdingAmountUSD() {
		return GetterUtil.getDouble(
			_extraSettingsProperties.getProperty(
				"marketplaceFatcaWithholdingAmountUSD"));
	}

	public String getLegalEntityName() {
		return GetterUtil.getString(
			_extraSettingsProperties.getProperty("marketplaceLegalEntityName"));
	}

	public String getOwnerClassName() {
		return GetterUtil.getString(
			_extraSettingsProperties.getProperty("marketplaceOwnerClassName"));
	}

	public long getOwnerClassPK() {
		return GetterUtil.getLong(
			_extraSettingsProperties.getProperty("marketplaceOwnerClassPK"));
	}

	public String getPayPalVendorEmailAddress() {
		return GetterUtil.getString(
			_extraSettingsProperties.getProperty("payPalVendorEmailAddress"));
	}

	public String getProductType() {
		return GetterUtil.getString(
			_extraSettingsProperties.getProperty("marketplaceProductType"));
	}

	public String getPurchaseType() {
		return GetterUtil.getString(
			_extraSettingsProperties.getProperty("marketplacePurchaseType"));
	}

	public long getRlaContractEntryId() {
		return GetterUtil.getLong(
			_extraSettingsProperties.getProperty(
				"marketplaceRlaContractEntryId"));
	}

	public long getTosContractEntryId() {
		return GetterUtil.getLong(
			_extraSettingsProperties.getProperty(
				"marketplaceTosContractEntryId"));
	}

	public double getVendorAmount() {
		return GetterUtil.getDouble(
			_extraSettingsProperties.getProperty("vendorAmount"));
	}

	public double getVendorAmountUSD() {
		return GetterUtil.getDouble(
			_extraSettingsProperties.getProperty("marketplaceVendorAmountUSD"));
	}

	public boolean isEndUserEmailReceipt() {
		return GetterUtil.getBoolean(
			_extraSettingsProperties.getProperty(
				"marketplaceEndUserEmailReceipt"));
	}

	public boolean isResale() {
		return GetterUtil.getBoolean(
			_extraSettingsProperties.getProperty("marketplaceResale"));
	}

	public void setAddressId(long addressId) {
		_extraSettingsProperties.setProperty(
			"marketplaceAddressId", String.valueOf(addressId));
	}

	public void setAppEntryId(long appEntryId) {
		_extraSettingsProperties.setProperty(
			"marketplaceAppEntryId", String.valueOf(appEntryId));
	}

	public void setClassPK(long classPK) {
		_extraSettingsProperties.setProperty(
			"marketplaceClassPK", String.valueOf(classPK));
	}

	public void setCompanyName(String companyName) {
		_extraSettingsProperties.setProperty("companyName", companyName);
	}

	public void setCountryId(long countryId) {
		_extraSettingsProperties.setProperty(
			"marketplaceCountryId", String.valueOf(countryId));
	}

	public void setDeveloperEntryId(long developerEntryId) {
		_extraSettingsProperties.setProperty(
			"marketplaceDeveloperEntryId", String.valueOf(developerEntryId));
	}

	public void setDomain(int domain) {
		_extraSettingsProperties.setProperty(
			"marketplaceDomain", String.valueOf(domain));
	}

	public void setEndUserCompanyName(String endUserCompanyName) {
		_extraSettingsProperties.setProperty(
			"marketplaceEndUserCompanyName", endUserCompanyName);
	}

	public void setEndUserEmailAddress(String endUserEmailAddress) {
		_extraSettingsProperties.setProperty(
			"marketplaceEndUserEmailAddress", endUserEmailAddress);
	}

	public void setEndUserEmailReceipt(boolean endUserEmailReceipt) {
		_extraSettingsProperties.setProperty(
			"marketplaceEndUserEmailReceipt",
			String.valueOf(endUserEmailReceipt));
	}

	public void setEndUserFax(String endUserFax) {
		_extraSettingsProperties.setProperty(
			"marketplaceEndUserFax", endUserFax);
	}

	public void setEndUserFirstName(String endUserFirstName) {
		_extraSettingsProperties.setProperty(
			"marketplaceEndUserFirstName", endUserFirstName);
	}

	public void setEndUserId(long endUserId) {
		_extraSettingsProperties.setProperty(
			"marketplaceEndUserId", String.valueOf(endUserId));
	}

	public void setEndUserLastName(String endUserLastName) {
		_extraSettingsProperties.setProperty(
			"marketplaceEndUserLastName", endUserLastName);
	}

	public void setEndUserPhone(String endUserPhone) {
		_extraSettingsProperties.setProperty(
			"marketplaceEndUserPhone", endUserPhone);
	}

	public void setEndUserVatNumber(String endUserVatNumber) {
		_extraSettingsProperties.setProperty(
			"marketplaceEndUserVatNumber", endUserVatNumber);
	}

	public void setEulaContractEntryId(long eulaContractEntryId) {
		_extraSettingsProperties.setProperty(
			"marketplaceEulaContractEntryId",
			String.valueOf(eulaContractEntryId));
	}

	public void setExtraSettingsProperties(
		UnicodeProperties extraSettingsProperties) {

		_extraSettingsProperties = extraSettingsProperties;
	}

	public void setFatcaWithholdingAmount(double fatcaWithholdingAmount) {
		_extraSettingsProperties.setProperty(
			"marketplaceFatcaWithholdingAmount",
			String.valueOf(fatcaWithholdingAmount));
	}

	public void setFatcaWithholdingAmountUSD(double fatcaWithholdingAmountUSD) {
		_extraSettingsProperties.setProperty(
			"marketplaceFatcaWithholdingAmountUSD",
			String.valueOf(fatcaWithholdingAmountUSD));
	}

	public void setLegalEntityName(String legalEntityName) {
		_extraSettingsProperties.setProperty(
			"marketplaceLegalEntityName", legalEntityName);
	}

	public void setOwnerClassName(String ownerClassName) {
		_extraSettingsProperties.setProperty(
			"marketplaceOwnerClassName", ownerClassName);
	}

	public void setOwnerClassPK(long ownerClassPK) {
		_extraSettingsProperties.setProperty(
			"marketplaceOwnerClassPK", String.valueOf(ownerClassPK));
	}

	public void setPayPalVendorEmailAddress(String payPalVendorEmailAddress) {
		_extraSettingsProperties.setProperty(
			"payPalVendorEmailAddress", payPalVendorEmailAddress);
	}

	public void setProductType(String productType) {
		_extraSettingsProperties.setProperty(
			"marketplaceProductType", productType);
	}

	public void setPurchaseType(String purchaseType) {
		_extraSettingsProperties.setProperty(
			"marketplacePurchaseType", purchaseType);
	}

	public void setResale(boolean resale) {
		_extraSettingsProperties.setProperty(
			"marketplaceResale", String.valueOf(resale));
	}

	public void setRlaContractEntryId(long rlaContractEntryId) {
		_extraSettingsProperties.setProperty(
			"marketplaceRlaContractEntryId",
			String.valueOf(rlaContractEntryId));
	}

	public void setTosContractEntryId(long tosContractEntryId) {
		_extraSettingsProperties.setProperty(
			"marketplaceTosContractEntryId",
			String.valueOf(tosContractEntryId));
	}

	public void setVendorAmount(double vendorAmount) {
		_extraSettingsProperties.setProperty(
			"vendorAmount", String.valueOf(vendorAmount));
	}

	public void setVendorAmountUSD(double vendorAmountUSD) {
		_extraSettingsProperties.setProperty(
			"marketplaceVendorAmountUSD", String.valueOf(vendorAmountUSD));
	}

	@Override
	public String toString() {
		return _extraSettingsProperties.toString();
	}

	private UnicodeProperties _extraSettingsProperties;

}