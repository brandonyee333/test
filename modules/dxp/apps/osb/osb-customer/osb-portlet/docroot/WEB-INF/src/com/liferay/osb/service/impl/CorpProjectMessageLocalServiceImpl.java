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

package com.liferay.osb.service.impl;

import com.liferay.osb.exception.NoSuchCorpProjectMessageException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.CorpProjectMessage;
import com.liferay.osb.model.CorpProjectMessageConstants;
import com.liferay.osb.model.OfferingDefinitionConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.service.base.CorpProjectMessageLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.comparator.OfferingEntrySupportEndDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * @author Amos Fong
 */
public class CorpProjectMessageLocalServiceImpl
	extends CorpProjectMessageLocalServiceBaseImpl {

	public CorpProjectMessage addCorpProjectMessage(JSONObject jsonObject)
		throws PortalException {

		User user = userLocalService.fetchUserByUuidAndCompanyId(
			jsonObject.getString("userUuid"), OSBConstants.COMPANY_ID);

		if (user == null) {
			User remoteUser = remoteUserLocalService.translate(
				jsonObject.getJSONObject("user"));

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCreateDate(remoteUser.getCreateDate());
			serviceContext.setUuid(remoteUser.getUuid());

			user = userLocalService.addUser(
				OSBConstants.USER_DEFAULT_USER_ID, OSBConstants.COMPANY_ID,
				true, StringPool.BLANK, StringPool.BLANK, false,
				remoteUser.getScreenName(), remoteUser.getEmailAddress(), 0,
				StringPool.BLANK, remoteUser.getLocale(),
				remoteUser.getFirstName(), remoteUser.getMiddleName(),
				remoteUser.getLastName(), 0, 0, false, 0, 1, 1970,
				StringPool.BLANK, new long[0], remoteUser.getOrganizationIds(),
				remoteUser.getRoleIds(), new long[0], false, serviceContext);
		}

		CorpProject corpProject = corpProjectLocalService.getCorpProjectByUuid(
			jsonObject.getString("corpProjectUuid"));

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCreateDate(
			new Date(jsonObject.getLong("createDate")));
		serviceContext.setModifiedDate(
			new Date(jsonObject.getLong("modifiedDate")));
		serviceContext.setUuid(jsonObject.getString("uuid"));

		return addCorpProjectMessage(
			user.getUserId(), corpProject.getCorpProjectId(),
			jsonObject.getInt("type"), jsonObject.getInt("severityLevel"),
			jsonObject.getString("title"), jsonObject.getString("content"),
			jsonObject.getBoolean("displayCP"),
			jsonObject.getBoolean("displayLCS"),
			jsonObject.getBoolean("displayLESA"), serviceContext);
	}

	public void checkCorpProjects() throws Exception {
		Date now = new Date();

		int[] types = {OfferingEntryConstants.TYPE_REGULAR};

		Date supportEndDateGT = new Date(now.getTime() - (30 * Time.DAY));
		Date supportEndDateLT = new Date(now.getTime() + (30 * Time.DAY));

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put(
			"licenseLifetime",
			OfferingDefinitionConstants.LIFETIME_INDEFINITE_VALUE);

		cleanCorpProjectMessages(supportEndDateLT, types, params);

		List<OfferingEntry> offeringEntries =
			offeringEntryFinder.findByU_AEI_PEI_T_S_SED(
				0, 0, 0, types,
				new int[] {OfferingEntryConstants.STATUS_ACTIVE},
				supportEndDateGT, supportEndDateLT, params, true,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new OfferingEntrySupportEndDateComparator(false));

		Set<Long> accountEntryIds = new HashSet<>();

		for (OfferingEntry offeringEntry : offeringEntries) {
			if (accountEntryIds.contains(offeringEntry.getAccountEntryId())) {
				continue;
			}

			accountEntryIds.add(offeringEntry.getAccountEntryId());

			AccountEntry accountEntry = offeringEntry.getAccountEntry();

			if (accountEntry.getCorpProjectId() <= 0) {
				continue;
			}

			if ((accountEntry.getType() ==
					AccountEntryConstants.TYPE_INTERNAL_TEST) ||
				(accountEntry.getType() == AccountEntryConstants.TYPE_TRIAL)) {

				continue;
			}

			CorpProjectMessage corpProjectMessage =
				corpProjectMessagePersistence.fetchByC_T(
					accountEntry.getCorpProjectId(),
					CorpProjectMessageConstants.
						TYPE_AUTOMATED_SUBSCRIPTION_STATUS);

			if (corpProjectMessage != null) {
				continue;
			}

			List<OfferingEntry> accountEntryOfferingEntries =
				offeringEntryFinder.findByU_AEI_PEI_T_S_SED(
					0, accountEntry.getAccountEntryId(), 0, types,
					new int[] {OfferingEntryConstants.STATUS_ACTIVE},
					supportEndDateLT, null, params, true, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null);

			if (!accountEntryOfferingEntries.isEmpty()) {
				continue;
			}

			if (now.after(offeringEntry.getSupportEndDate())) {
				remoteCorpProjectMessageLocalService.addCorpProjectMessage(
					OSBConstants.USER_DEFAULT_USER_ID,
					accountEntry.getCorpProjectId(),
					CorpProjectMessageConstants.
						TYPE_AUTOMATED_SUBSCRIPTION_STATUS,
					CorpProjectMessageConstants.SEVERITY_LEVEL_URGENT,
					CorpProjectMessageConstants.AUTOMATED_TEMPLATE_PAST_DUE,
					CorpProjectMessageConstants.AUTOMATED_TEMPLATE_PAST_DUE,
					true, true, true);
			}
			else {
				remoteCorpProjectMessageLocalService.addCorpProjectMessage(
					OSBConstants.USER_DEFAULT_USER_ID,
					accountEntry.getCorpProjectId(),
					CorpProjectMessageConstants.
						TYPE_AUTOMATED_SUBSCRIPTION_STATUS,
					CorpProjectMessageConstants.SEVERITY_LEVEL_WARNING,
					CorpProjectMessageConstants.AUTOMATED_TEMPLATE_WARNING,
					CorpProjectMessageConstants.AUTOMATED_TEMPLATE_WARNING,
					true, true, true);
			}
		}
	}

	public CorpProjectMessage deleteCorpProjectMessage(JSONObject jsonObject)
		throws PortalException {

		CorpProjectMessage corpProjectMessage = getCorpProjectMessageByUuid(
			jsonObject.getString("uuid"));

		return deleteCorpProjectMessage(corpProjectMessage);
	}

	public CorpProjectMessage getCorpProjectMessageByUuid(String uuid)
		throws PortalException {

		List<CorpProjectMessage> corpProjectMessages =
			corpProjectMessagePersistence.findByUuid(uuid);

		if (corpProjectMessages.isEmpty()) {
			throw new NoSuchCorpProjectMessageException("{uuid=" + uuid + "}");
		}
		else {
			return corpProjectMessages.get(0);
		}
	}

	public List<CorpProjectMessage> getCorpProjectMessages(long corpProjectId) {
		return corpProjectMessagePersistence.findByCorpProjectId(corpProjectId);
	}

	public CorpProjectMessage updateCorpProjectMessage(JSONObject jsonObject)
		throws PortalException {

		long userId = 0;

		User user = userLocalService.fetchUserByUuidAndCompanyId(
			jsonObject.getString("userUuid"), OSBConstants.COMPANY_ID);

		if (user != null) {
			userId = user.getUserId();
		}

		CorpProjectMessage corpProjectMessage = getCorpProjectMessageByUuid(
			jsonObject.getString("uuid"));

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCreateDate(
			new Date(jsonObject.getLong("modifiedDate")));

		return updateCorpProjectMessage(
			userId, corpProjectMessage.getCorpProjectMessageId(),
			jsonObject.getInt("type"), jsonObject.getInt("severityLevel"),
			jsonObject.getString("title"), jsonObject.getString("content"),
			jsonObject.getBoolean("displayCP"),
			jsonObject.getBoolean("displayLCS"),
			jsonObject.getBoolean("displayLESA"), serviceContext);
	}

	protected CorpProjectMessage addCorpProjectMessage(
			long userId, long corpProjectId, int type, int severityLevel,
			String title, String content, boolean displayCP, boolean displayLCS,
			boolean displayLESA, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date createDate = serviceContext.getCreateDate(new Date());
		Date modifiedDate = serviceContext.getModifiedDate(new Date());

		long corpProjectMessageId = counterLocalService.increment();

		CorpProjectMessage corpProjectMessage =
			corpProjectMessagePersistence.create(corpProjectMessageId);

		corpProjectMessage.setUuid(serviceContext.getUuid());
		corpProjectMessage.setUserId(userId);
		corpProjectMessage.setUserName(user.getFullName());
		corpProjectMessage.setCreateDate(createDate);
		corpProjectMessage.setModifiedDate(modifiedDate);
		corpProjectMessage.setCorpProjectId(corpProjectId);
		corpProjectMessage.setType(type);
		corpProjectMessage.setSeverityLevel(severityLevel);
		corpProjectMessage.setTitle(title);
		corpProjectMessage.setContent(content);
		corpProjectMessage.setDisplayCP(displayCP);
		corpProjectMessage.setDisplayLCS(displayLCS);
		corpProjectMessage.setDisplayLESA(displayLESA);

		return corpProjectMessagePersistence.update(corpProjectMessage);
	}

	protected void cleanCorpProjectMessages(
			Date supportEndDateLT, int[] types,
			LinkedHashMap<String, Object> params)
		throws PortalException {

		List<CorpProjectMessage> corpProjectMessages =
			corpProjectMessagePersistence.findByType(
				CorpProjectMessageConstants.TYPE_AUTOMATED_SUBSCRIPTION_STATUS);

		for (CorpProjectMessage corpProjectMessage : corpProjectMessages) {
			List<OfferingEntry> offeringEntries = new ArrayList<>();

			AccountEntry accountEntry =
				accountEntryLocalService.fetchCorpProjectAccountEntry(
					corpProjectMessage.getCorpProjectId());

			if (accountEntry != null) {
				offeringEntries = offeringEntryFinder.findByU_AEI_PEI_T_S_SED(
					0, accountEntry.getAccountEntryId(), 0, types,
					new int[] {OfferingEntryConstants.STATUS_ACTIVE}, null,
					null, params, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new OfferingEntrySupportEndDateComparator(false));
			}

			if (offeringEntries.isEmpty()) {
				remoteCorpProjectMessageLocalService.deleteCorpProjectMessage(
					corpProjectMessage.getCorpProjectMessageId());

				continue;
			}

			OfferingEntry offeringEntry = offeringEntries.get(0);

			Date supportEndDate = offeringEntry.getSupportEndDate();

			boolean delete = false;

			if (supportEndDate.after(supportEndDateLT)) {
				delete = true;
			}
			else if (supportEndDate.after(new Date())) {
				if (corpProjectMessage.getSeverityLevel() !=
						CorpProjectMessageConstants.SEVERITY_LEVEL_WARNING) {

					delete = true;
				}
			}
			else {
				if (corpProjectMessage.getSeverityLevel() !=
						CorpProjectMessageConstants.SEVERITY_LEVEL_URGENT) {

					delete = true;
				}
			}

			if (delete) {
				remoteCorpProjectMessageLocalService.deleteCorpProjectMessage(
					corpProjectMessage.getCorpProjectMessageId());
			}
		}
	}

	protected CorpProjectMessage updateCorpProjectMessage(
			long userId, long corpProjectMessageId, int type, int severityLevel,
			String title, String content, boolean displayCP, boolean displayLCS,
			boolean displayLESA, ServiceContext serviceContext)
		throws PortalException {

		Date modifiedDate = serviceContext.getModifiedDate(new Date());

		CorpProjectMessage corpProjectMessage =
			corpProjectMessagePersistence.findByPrimaryKey(
				corpProjectMessageId);

		if (userId > 0) {
			User user = userLocalService.getUser(userId);

			corpProjectMessage.setUserId(userId);
			corpProjectMessage.setUserName(user.getFullName());
		}

		corpProjectMessage.setModifiedDate(modifiedDate);
		corpProjectMessage.setType(type);
		corpProjectMessage.setSeverityLevel(severityLevel);
		corpProjectMessage.setTitle(title);
		corpProjectMessage.setContent(content);
		corpProjectMessage.setDisplayCP(displayCP);
		corpProjectMessage.setDisplayLCS(displayLCS);
		corpProjectMessage.setDisplayLESA(displayLESA);

		return corpProjectMessagePersistence.update(corpProjectMessage);
	}

}