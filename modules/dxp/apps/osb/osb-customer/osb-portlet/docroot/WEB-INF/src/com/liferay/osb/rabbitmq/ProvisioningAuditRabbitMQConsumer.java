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

package com.liferay.osb.rabbitmq;

import com.liferay.osb.admin.util.AdminUtil;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class ProvisioningAuditRabbitMQConsumer
	extends ProvisioningRabbitMQConsumer {

	public ProvisioningAuditRabbitMQConsumer() {
	}

	public void doParse(JSONObject jsonObject) throws PortalException {
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

	protected Map<String, Integer> getOfferingEntriesMap(JSONArray jsonArray)
		throws PortalException {

		List<OrderEntry> orderEntries = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			orderEntries.addAll(parseOrderEntries(jsonObject));
		}

		return SupportUtil.getOfferingEntriesMap(orderEntries);
	}

	protected int[] getOutOfSyncFields(
			AccountEntry existingAccountEntry, JSONArray jsonArray)
		throws PortalException {

		List<Integer> outOfDateFields = new ArrayList<>();

		// Offerings

		Map<String, Integer> existingofferingEntriesMap =
			SupportUtil.getOfferingEntriesMap(
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

		Address address = parseAddress(jsonObject);
		CorpProject corpProject = parseCorpProject(jsonObject);
		List<OrderEntry> orderEntries = parseOrderEntries(jsonObject);

		AccountEntry accountEntry = parseAccountEntry(
			jsonObject, address, corpProject, orderEntries);

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