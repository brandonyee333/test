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

package com.liferay.osb.rabbitmq;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.osb.admin.util.AdminUtil;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Address;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class ProvisioningAuditRabbitMQConsumer
	extends ProvisioningRabbitMQConsumer {

	public ProvisioningAuditRabbitMQConsumer() {
	}

	public void doParse(JSONObject jsonObject)
		throws PortalException, SystemException {

		long accountEntryId = jsonObject.getLong("accountEntryId");
		JSONArray jsonArray = jsonObject.getJSONArray("opportunities");
		long userId = jsonObject.getLong("userId");

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

		if (_log.isInfoEnabled()) {
			_log.info("Auditing account entry " + accountEntry.getName());
		}

		int[] outOfSyncFields = getOutOfSyncFields(accountEntry, jsonArray);

		AccountEntryLocalServiceUtil.updateLastAuditDate(
			userId, accountEntryId, StringPool.BLANK,
			StringUtil.merge(outOfSyncFields));
	}

	protected String getKey(OfferingEntry offeringEntry) {
		StringBundler sb = new StringBundler();

		sb.append(offeringEntry.getProductEntryId());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getSupportResponseId());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getProductDescription());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getType());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getVersion());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getLicenses());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getLicenseLifetime());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getSupportTickets());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getSupportLifetime());
		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getSizing());

		Date supportEndDate = offeringEntry.getSupportEndDate();

		sb.append(supportEndDate.getTime());

		sb.append(StringPool.POUND);
		sb.append(offeringEntry.getStatus());

		return sb.toString();
	}

	protected Map<String, Integer> getOfferingEntriesMap(JSONArray jsonArray)
		throws PortalException, SystemException {

		List<OrderEntry> orderEntries = new ArrayList<OrderEntry>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			orderEntries.addAll(parseOrderEntries(jsonObject));
		}

		return getOfferingEntriesMap(orderEntries);
	}

	protected Map<String, Integer> getOfferingEntriesMap(
			List<OrderEntry> orderEntries)
		throws SystemException {

		Map<String, Integer> offeringEntriesMap =
			new HashMap<String, Integer>();

		for (OrderEntry orderEntry : orderEntries) {
			List<OfferingEntry> offeringEntries =
				orderEntry.getOfferingEntries();

			for (OfferingEntry offeringEntry : offeringEntries) {
				String key = getKey(offeringEntry);

				Integer quantity = offeringEntriesMap.get(key);

				if (quantity == null) {
					quantity = offeringEntry.getQuantity();
				}
				else {
					quantity += offeringEntry.getQuantity();
				}

				offeringEntriesMap.put(key, quantity);
			}
		}

		return offeringEntriesMap;
	}

	protected Map<String, Integer> getOfferingEntriesMap(long accountEntryId)
		throws SystemException {

		List<OrderEntry> orderEntries =
			OrderEntryLocalServiceUtil.getAccountEntryOrderEntries(
				accountEntryId);

		return getOfferingEntriesMap(orderEntries);
	}

	protected int[] getOutOfSyncFields(
			AccountEntry existingAccountEntry, JSONArray jsonArray)
		throws PortalException, SystemException {

		List<Integer> outOfDateFields = new ArrayList<Integer>();

		// Offerings

		Map<String, Integer> existingofferingEntriesMap = getOfferingEntriesMap(
			existingAccountEntry.getAccountEntryId());

		Map<String, Integer> offeringEntriesMap = getOfferingEntriesMap(
			jsonArray);

		if (!existingofferingEntriesMap.equals(offeringEntriesMap)) {
			outOfDateFields.add(AuditEntryConstants.FIELD_OFFERINGS);
		}

		if (jsonArray.length() <= 0) {
			return ArrayUtil.toArray(outOfDateFields.toArray(new Integer[0]));
		}

		// Account Entry

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		CorpProject corpProject = parseCorpProject(jsonObject);
		List<OrderEntry> orderEntries = parseOrderEntries(jsonObject);

		AccountEntry accountEntry = parseAccountEntry(
			jsonObject, corpProject, orderEntries);

		String corpEntryName = existingAccountEntry.getCorpEntryName();

		if (!corpEntryName.equals(accountEntry.getCorpEntryName())) {
			outOfDateFields.add(AuditEntryConstants.FIELD_CORP_ENTRY_NAME);
		}

		String name = existingAccountEntry.getName();

		if (!name.equals(accountEntry.getName())) {
			outOfDateFields.add(AuditEntryConstants.FIELD_NAME);
		}

		if (existingAccountEntry.getIndustry() != accountEntry.getIndustry()) {
			outOfDateFields.add(AuditEntryConstants.FIELD_INDUSTRY);
		}

		if (existingAccountEntry.getPartnerManagedSupport() !=
				accountEntry.getPartnerManagedSupport()) {

			outOfDateFields.add(
				AuditEntryConstants.FIELD_PARTNER_MANAGED_SUPPORT);
		}

		String[] existingLanguageIds = existingAccountEntry.getLanguageIds();
		String[] languageIds = accountEntry.getLanguageIds();

		Arrays.sort(existingLanguageIds);
		Arrays.sort(languageIds);

		if (!Arrays.equals(existingLanguageIds, languageIds)) {
			outOfDateFields.add(AuditEntryConstants.FIELD_LANGUAGES);
		}

		long[] existingSupportRegionIds =
			existingAccountEntry.getSupportRegionIds();
		long[] supportRegionIds = accountEntry.getSupportRegionIds();

		Arrays.sort(existingSupportRegionIds);
		Arrays.sort(supportRegionIds);

		if (!Arrays.equals(existingSupportRegionIds, supportRegionIds)) {
			outOfDateFields.add(AuditEntryConstants.FIELD_SUPPORT_REGIONS);
		}

		// Partner

		long partnerEntryId = 0;

		PartnerEntry partnerEntry = parsePartnerEntry(jsonObject);

		if (partnerEntry != null) {
			partnerEntryId = partnerEntry.getPartnerEntryId();
		}

		if (existingAccountEntry.getPartnerEntryId() != partnerEntryId) {
			outOfDateFields.add(AuditEntryConstants.FIELD_PARTNER);
		}

		// Address

		Address oldAddress = existingAccountEntry.getAddress();
		Address address = parseAddress(jsonObject);

		String oldAddressString = AdminUtil.formatAddress(oldAddress);
		String addressString = AdminUtil.formatAddress(address);

		if (!oldAddressString.equals(addressString)) {
			outOfDateFields.add(AuditEntryConstants.FIELD_ADDRESS);
		}

		return ArrayUtil.toArray(outOfDateFields.toArray(new Integer[0]));
	}

	@Override
	protected void sendErrorNotification(
		String routingKey, String message, JSONObject jsonObject, Exception e) {

		_log.error("Unexpected error has occurred for message: " + message, e);
	}

	private static Log _log = LogFactoryUtil.getLog(
		ProvisioningAuditRabbitMQConsumer.class);

}