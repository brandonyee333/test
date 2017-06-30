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

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Time;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.CorpProjectMessageContentException;
import com.liferay.osb.CorpProjectMessageSeverityLevelException;
import com.liferay.osb.CorpProjectMessageTitleException;
import com.liferay.osb.CorpProjectMessageTypeException;
import com.liferay.osb.corpprojectadmin.util.CorpProjectAdminUtil;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.CorpProjectMessage;
import com.liferay.osb.model.CorpProjectMessageConstants;
import com.liferay.osb.model.OfferingDefinitionConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.remote.RemoteLCSWebServiceUtil;
import com.liferay.osb.service.base.CorpProjectMessageLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.comparator.AccountEntryNameComparator;
import com.liferay.osb.util.comparator.OfferingEntrySupportEndDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.model.User;

import java.text.DateFormat;
import java.text.Format;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Amos Fong
 */
public class CorpProjectMessageLocalServiceImpl
	extends CorpProjectMessageLocalServiceBaseImpl {

	public CorpProjectMessage addCorpProjectMessage(
			long userId, long corpProjectId, int type, int severityLevel,
			String title, String content, boolean displayCP, boolean displayLCS,
			boolean displayLESA)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(corpProjectId, type, severityLevel, title, content);

		corpProjectLocalService.validateSalesforceProjectKey(corpProjectId);

		long corpProjectMessageId = counterLocalService.increment();

		CorpProjectMessage corpProjectMessage =
			corpProjectMessagePersistence.create(corpProjectMessageId);

		corpProjectMessage.setUserId(userId);
		corpProjectMessage.setUserName(user.getFullName());
		corpProjectMessage.setCreateDate(now);
		corpProjectMessage.setModifiedDate(now);
		corpProjectMessage.setCorpProjectId(corpProjectId);
		corpProjectMessage.setType(type);
		corpProjectMessage.setSeverityLevel(severityLevel);
		corpProjectMessage.setTitle(title);
		corpProjectMessage.setContent(content);
		corpProjectMessage.setDisplayCP(displayCP);
		corpProjectMessage.setDisplayLCS(displayLCS);
		corpProjectMessage.setDisplayLESA(displayLESA);

		corpProjectMessagePersistence.update(corpProjectMessage, false);

		return corpProjectMessage;
	}

	public void checkCorpProjects() throws Exception {
		Date now = new Date();

		int[] types = {
			OfferingEntryConstants.TYPE_REGULAR,
			OfferingEntryConstants.TYPE_SUBSCRIPTION
		};

		Date supportEndDateGT = new Date(now.getTime() - (30 * Time.DAY));
		Date supportEndDateLT = new Date(now.getTime() + (30 * Time.DAY));

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

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

		Set<Long> accountEntryIds = new HashSet<Long>();

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
				addCorpProjectMessage(
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
				addCorpProjectMessage(
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

	@Override
	public CorpProjectMessage deleteCorpProjectMessage(
			long corpProjectMessageId)
		throws PortalException, SystemException {

		CorpProjectMessage corpProjectMessage =
			corpProjectMessagePersistence.findByPrimaryKey(
				corpProjectMessageId);

		corpProjectLocalService.validateSalesforceProjectKey(
			corpProjectMessage.getCorpProjectId());

		return corpProjectMessagePersistence.remove(corpProjectMessageId);
	}

	public void deleteFromLCS(CorpProjectMessage corpProjectMessage)
		throws SystemException {

		if (!PortletPropsValues.REMOTE_JSON_SERVICE_API_LCS_ENABLED) {
			return;
		}

		if (corpProjectMessage.isDisplayLCS()) {
			RemoteLCSWebServiceUtil.deleteLCSMessage(
				corpProjectMessage.getCorpProjectId(),
				corpProjectMessage.getCorpProjectMessageId());
		}
	}

	public List<CorpProjectMessage> getCorpProjectMessages(long corpProjectId)
		throws SystemException {

		return corpProjectMessagePersistence.findByCorpProjectId(corpProjectId);
	}

	public Map<Integer, List<CorpProjectMessage>> getCorpProjectMessagesMap(
			long userId, boolean displayCP, boolean displayLESA)
		throws Exception {

		Map<Integer, List<CorpProjectMessage>> corpProjectMessagesMap =
			new HashMap<Integer, List<CorpProjectMessage>>();

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("accountEntryMembership", new Long(userId));

		List<AccountEntry> accountEntries = accountEntryLocalService.search(
			null, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new AccountEntryNameComparator(true));

		for (AccountEntry accountEntry : accountEntries) {
			if (accountEntry.getType() == AccountEntryConstants.TYPE_TRIAL) {
				continue;
			}

			if (accountEntry.getCorpProjectId() <= 0) {
				continue;
			}

			List<CorpProjectMessage> corpProjectMessages =
				getCorpProjectMessages(accountEntry.getCorpProjectId());

			for (CorpProjectMessage corpProjectMessage : corpProjectMessages) {
				if ((displayCP && !corpProjectMessage.isDisplayCP()) ||
					(displayLESA && !corpProjectMessage.isDisplayLESA())) {

					continue;
				}

				corpProjectMessage.setAccountEntry(accountEntry);

				if (corpProjectMessage.getType() ==
						CorpProjectMessageConstants.
							TYPE_AUTOMATED_SUBSCRIPTION_STATUS) {

					String title = getTitle(
						userId, corpProjectMessage.getTitle());

					corpProjectMessage.setTitle(title);

					String content = getContent(
						userId, accountEntry.getAccountEntryId(),
						corpProjectMessage.getContent(), params);

					if (Validator.isNotNull(content)) {
						corpProjectMessage.setContent(content);
					}
				}

				List<CorpProjectMessage> corpProjectMessagesList =
					corpProjectMessagesMap.get(
						corpProjectMessage.getSeverityLevel());

				if (corpProjectMessagesList == null) {
					corpProjectMessagesList =
						new ArrayList<CorpProjectMessage>();

					corpProjectMessagesMap.put(
						corpProjectMessage.getSeverityLevel(),
						corpProjectMessagesList);
				}

				corpProjectMessagesList.add(corpProjectMessage);
			}
		}

		return corpProjectMessagesMap;
	}

	public void syncToLCS(long corpProjectMessageId)
		throws PortalException, SystemException {

		if (!PortletPropsValues.REMOTE_JSON_SERVICE_API_LCS_ENABLED) {
			return;
		}

		CorpProjectMessage corpProjectMessage =
			corpProjectMessagePersistence.findByPrimaryKey(
				corpProjectMessageId);

		if (corpProjectMessage.isDisplayLCS()) {
			RemoteLCSWebServiceUtil.sendLCSMessage(
				corpProjectMessage.getCorpProjectId(), corpProjectMessageId,
				corpProjectMessage.getContent(),
				corpProjectMessage.getSeverityLevel(),
				corpProjectMessage.getTitle(), corpProjectMessage.getType());
		}
	}

	public CorpProjectMessage updateCorpProjectMessage(
			long userId, long corpProjectMessageId, int type, int severityLevel,
			String title, String content, boolean displayCP, boolean displayLCS,
			boolean displayLESA)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		CorpProjectMessage corpProjectMessage =
			corpProjectMessagePersistence.findByPrimaryKey(
				corpProjectMessageId);

		validate(
			corpProjectMessage.getCorpProjectId(), type, severityLevel, title,
			content);

		corpProjectLocalService.validateSalesforceProjectKey(
			corpProjectMessage.getCorpProjectId());

		corpProjectMessage.setUserId(userId);
		corpProjectMessage.setUserName(user.getFullName());
		corpProjectMessage.setModifiedDate(new Date());
		corpProjectMessage.setType(type);
		corpProjectMessage.setSeverityLevel(severityLevel);
		corpProjectMessage.setTitle(title);
		corpProjectMessage.setContent(content);
		corpProjectMessage.setDisplayCP(displayCP);
		corpProjectMessage.setDisplayLCS(displayLCS);
		corpProjectMessage.setDisplayLESA(displayLESA);

		corpProjectMessagePersistence.update(corpProjectMessage, false);

		return corpProjectMessage;
	}

	protected void cleanCorpProjectMessages(
			Date supportEndDateLT, int[] types,
			LinkedHashMap<String, Object> params)
		throws PortalException, SystemException {

		List<CorpProjectMessage> corpProjectMessages =
			corpProjectMessagePersistence.findByType(
				CorpProjectMessageConstants.TYPE_AUTOMATED_SUBSCRIPTION_STATUS);

		for (CorpProjectMessage corpProjectMessage : corpProjectMessages) {
			List<OfferingEntry> offeringEntries =
				new ArrayList<OfferingEntry>();

			AccountEntry accountEntry = corpProjectMessage.getAccountEntry();

			if (accountEntry != null) {
				offeringEntries = offeringEntryFinder.findByU_AEI_PEI_T_S_SED(
					0, accountEntry.getAccountEntryId(), 0, types,
					new int[] {OfferingEntryConstants.STATUS_ACTIVE}, null,
					null, params, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new OfferingEntrySupportEndDateComparator(false));
			}

			if (offeringEntries.isEmpty()) {
				deleteCorpProjectMessage(
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
				deleteCorpProjectMessage(
					corpProjectMessage.getCorpProjectMessageId());
			}
		}
	}

	protected String getContent(
			long userId, long accountEntryId, String templateContent,
			LinkedHashMap<String, Object> params)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		String content = CorpProjectAdminUtil.getPreferenceValue(
			user.getLocale(), "projectMessage_" + templateContent);

		if (Validator.isNull(content) ||
			(!content.contains("[$SERVICES_END_DATE$]") &&
			 !content.contains("[$SUPPORT_END_DATE$]"))) {

			return content;
		}

		int[] types = {
			OfferingEntryConstants.TYPE_REGULAR,
			OfferingEntryConstants.TYPE_SUBSCRIPTION
		};

		List<OfferingEntry> offeringEntries =
			offeringEntryFinder.findByU_AEI_PEI_T_S_SED(
				0, accountEntryId, 0, types,
				new int[] {OfferingEntryConstants.STATUS_ACTIVE}, null, null,
				params, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new OfferingEntrySupportEndDateComparator(false));

		if (!offeringEntries.isEmpty()) {
			OfferingEntry offeringEntry = offeringEntries.get(0);

			Format shortDateFormatDate = FastDateFormatFactoryUtil.getDate(
				DateFormat.SHORT, user.getLocale(), user.getTimeZone());

			Date supportEndDate = offeringEntry.getSupportEndDate();

			Date servicesEndDate = new Date(
				supportEndDate.getTime() + (Time.DAY * 30));

			content = StringUtil.replace(
				content,
				new String[] {
					"[$SERVICES_END_DATE$]", "[$SUPPORT_END_DATE$]"
				},
				new String[] {
					shortDateFormatDate.format(servicesEndDate),
					shortDateFormatDate.format(supportEndDate)
				});
		}

		return content;
	}

	protected String getTitle(long userId, String templateTitle)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		return CorpProjectAdminUtil.getPreferenceValue(
			user.getLocale(), "projectTitle_" + templateTitle);
	}

	protected void validate(
			long corpProjectId, int type, int severityLevel, String title,
			String content)
		throws PortalException, SystemException {

		corpProjectPersistence.findByPrimaryKey(corpProjectId);

		if (type <= 0) {
			throw new CorpProjectMessageTypeException();
		}

		if (severityLevel <= 0) {
			throw new CorpProjectMessageSeverityLevelException();
		}

		if (Validator.isNull(title)) {
			throw new CorpProjectMessageTitleException();
		}

		if (Validator.isNull(content)) {
			throw new CorpProjectMessageContentException();
		}
	}

}