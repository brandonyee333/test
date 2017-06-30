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

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.corpmembers.util.CorpMembersUtil;
import com.liferay.osb.marketplace.util.MarketplaceUtil;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.CorpMembershipRequest;
import com.liferay.osb.service.base.CorpMembershipRequestLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.SubscriptionSender;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Douglas Wong
 * @author Ryan Park
 */
public class CorpMembershipRequestLocalServiceImpl
	extends CorpMembershipRequestLocalServiceBaseImpl {

	public CorpMembershipRequest addCorpMembershipRequest(
			long userId, long corpEntryId)
		throws PortalException, SystemException {

		if (hasPendingRequest(userId, corpEntryId)) {
			return null;
		}

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		long corpMembershipRequestId = counterLocalService.increment();

		CorpMembershipRequest corpMembershipRequest =
			corpMembershipRequestPersistence.create(corpMembershipRequestId);

		corpMembershipRequest.setUserId(userId);
		corpMembershipRequest.setUserName(user.getFullName());
		corpMembershipRequest.setCreateDate(now);
		corpMembershipRequest.setModifiedDate(now);
		corpMembershipRequest.setCorpEntryId(corpEntryId);
		corpMembershipRequest.setKey(PortalUUIDUtil.generate());
		corpMembershipRequest.setEmailAddress(user.getEmailAddress());
		corpMembershipRequest.setStatus(WorkflowConstants.STATUS_PENDING);

		corpMembershipRequestPersistence.update(corpMembershipRequest, false);

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CorpMembershipRequest.class);

		indexer.reindex(corpMembershipRequest);

		sendRequestMail(corpMembershipRequest);

		return corpMembershipRequest;
	}

	public CorpMembershipRequest fetchCorpMembershipRequest(String key)
		throws SystemException {

		return corpMembershipRequestPersistence.fetchByKey(key);
	}

	public CorpMembershipRequest getCorpMembershipRequest(String key)
		throws PortalException, SystemException {

		return corpMembershipRequestPersistence.findByKey(key);
	}

	public List<CorpMembershipRequest> getCorpMembershipRequests(
			long corpEntryId, int[] statuses, int start, int end)
		throws SystemException {

		return corpMembershipRequestPersistence.findByCEI_S(
			corpEntryId, statuses, start, end);
	}

	public int getCorpMembershipRequestsCount(long corpEntryId, int[] statuses)
		throws SystemException {

		return corpMembershipRequestPersistence.countByCEI_S(
			corpEntryId, statuses);
	}

	public boolean hasPendingRequest(long userId, long corpEntryId)
		throws SystemException {

		int[] statuses = new int[] {
			WorkflowConstants.STATUS_PENDING,
			WorkflowConstants.STATUS_PENDING_VALIDATION
		};

		int count = corpMembershipRequestPersistence.countByU_CEI_S(
			userId, corpEntryId, statuses);

		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public CorpMembershipRequest updateStatus(
			long corpMembershipRequestId, int status)
		throws PortalException, SystemException {

		CorpMembershipRequest corpMembershipRequest =
			corpMembershipRequestPersistence.findByPrimaryKey(
				corpMembershipRequestId);

		int oldStatus = corpMembershipRequest.getStatus();

		corpMembershipRequest.setStatus(status);

		corpMembershipRequestPersistence.update(corpMembershipRequest, false);

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CorpMembershipRequest.class);

		indexer.reindex(corpMembershipRequest);

		if (status == WorkflowConstants.STATUS_PENDING_VALIDATION) {
			sendValidationMail(corpMembershipRequest);
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			if (oldStatus != WorkflowConstants.STATUS_PENDING_VALIDATION) {
				return null;
			}

			CorpEntry corpEntry = corpEntryLocalService.getCorpEntry(
				corpMembershipRequest.getCorpEntryId());

			userLocalService.addOrganizationUsers(
				corpEntry.getOrganizationId(),
				new long[] {corpMembershipRequest.getUserId()});
		}

		return corpMembershipRequest;
	}

	protected String getValidateURL(String key)
		throws PortalException, SystemException {

		StringBundler sb = new StringBundler(5);

		Layout layout = LayoutLocalServiceUtil.getLayout(
			OSBConstants.GROUP_GUEST_ID, false, 1);

		Company company = CompanyLocalServiceUtil.getCompany(
			layout.getCompanyId());

		String portalURL = PortalUtil.getPortalURL(
			company.getVirtualHostname(), PortalUtil.getPortalPort(false),
			false);

		sb.append(portalURL);
		sb.append(layout.getFriendlyURL());
		sb.append(Portal.FRIENDLY_URL_SEPARATOR);
		sb.append("company_membership_request/");
		sb.append(key);

		return sb.toString();
	}

	protected void sendRequestMail(CorpMembershipRequest corpMembershipRequest)
		throws PortalException, SystemException {

		String fromName = "Liferay Marketplace";
		String fromAddress = "noreply@liferay.com";

		PortletPreferences preferences =
			MarketplaceUtil.getPortletPreferences();

		Map<Locale, String> subjectMap =
			CorpMembersUtil.getEmailCorpMembershipRequestSubjectMap(
				preferences);
		Map<Locale, String> bodyMap =
			CorpMembersUtil.getEmailCorpMembershipRequestBodyMap(preferences);

		CorpEntry corpEntry = corpEntryPersistence.findByPrimaryKey(
			corpMembershipRequest.getCorpEntryId());

		Group group = corpEntry.getGroup();

		String corpMemberURL = PortalUtil.getLayoutFullURL(
			group.getGroupId(), OSBPortletKeys.OSB_CORP_MEMBERS);

		List<User> adminUsers =
			CorpMembersUtil.getCorpEntryUsers(
				corpMembershipRequest.getCorpEntryId(), StringPool.BLANK,
				OSBConstants.ROLE_OSB_CORP_ADMIN_ID, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		for (User adminUser : adminUsers) {
			String toName = adminUser.getFullName();
			String toAddress = adminUser.getEmailAddress();

			SubscriptionSender subscriptionSender = new SubscriptionSender();

			subscriptionSender.setCompanyId(adminUser.getCompanyId());
			subscriptionSender.setContextAttributes(
				"[$ADMIN_FULL_NAME$]", adminUser.getFullName(),
				"[$CORP_MEMBERS_URL$]", corpMemberURL, "[$CORP_NAME$]",
				corpEntry.getName(), "[$USER_EMAIL_ADDRESS$]",
				corpMembershipRequest.getEmailAddress(), "[$USER_FULL_NAME$]",
				corpMembershipRequest.getUserName());
			subscriptionSender.setFrom(fromAddress, fromName);
			subscriptionSender.setHtmlFormat(true);
			subscriptionSender.setLocalizedBodyMap(bodyMap);
			subscriptionSender.setLocalizedSubjectMap(subjectMap);
			subscriptionSender.setMailId(
				"corp_membership_request_id",
				corpMembershipRequest.getCorpMembershipRequestId());
			subscriptionSender.setUserId(adminUser.getUserId());

			subscriptionSender.addRuntimeSubscribers(toAddress, toName);

			subscriptionSender.flushNotificationsAsync();
		}
	}

	protected void sendValidationMail(
			CorpMembershipRequest corpMembershipRequest)
		throws PortalException, SystemException {

		String fromName = "Liferay Marketplace";
		String fromAddress = "noreply@liferay.com";

		String toName = corpMembershipRequest.getUserName();
		String toAddress = corpMembershipRequest.getEmailAddress();

		PortletPreferences preferences =
			MarketplaceUtil.getPortletPreferences();

		Map<Locale, String> subjectMap =
			CorpMembersUtil.getEmailCorpMembershipValidateSubjectMap(
				preferences);
		Map<Locale, String> bodyMap =
			CorpMembersUtil.getEmailCorpMembershipValidateBodyMap(preferences);

		User user = userPersistence.findByPrimaryKey(
			corpMembershipRequest.getUserId());

		CorpEntry corpEntry = corpEntryPersistence.findByPrimaryKey(
			corpMembershipRequest.getCorpEntryId());

		String validateURL = getValidateURL(corpMembershipRequest.getKey());

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setCompanyId(user.getCompanyId());
		subscriptionSender.setContextAttributes(
			"[$CORP_NAME$]", corpEntry.getName(), "[$CORP_VALIDATE_URL$]",
			validateURL, "[$USER_FULL_NAME$]", user.getFullName());
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId(
			"corp_membership_request_id",
			corpMembershipRequest.getCorpMembershipRequestId());
		subscriptionSender.setUserId(user.getUserId());

		subscriptionSender.addRuntimeSubscribers(toAddress, toName);

		subscriptionSender.flushNotificationsAsync();
	}

}