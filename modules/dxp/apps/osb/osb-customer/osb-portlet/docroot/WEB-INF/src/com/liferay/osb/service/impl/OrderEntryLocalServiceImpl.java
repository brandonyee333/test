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

package com.liferay.osb.service.impl;

import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.exception.NoSuchOrderEntryException;
import com.liferay.osb.exception.OrderEntryActualStartDateException;
import com.liferay.osb.exception.OrderEntryStartDateException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.service.base.OrderEntryLocalServiceBaseImpl;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.SalesforceConstants;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class OrderEntryLocalServiceImpl extends OrderEntryLocalServiceBaseImpl {

	public List<OrderEntry> addOrderEntriesWithWorkflow(
			String salesforceOpportunityKey, AccountEntry accountEntry,
			PartnerEntry partnerEntry, Address address,
			AccountWorker accountWorker, List<OrderEntry> orderEntries,
			ServiceContext serviceContext)
		throws PortalException {

		AccountEntry oldAccountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntry.getAccountEntryId());

		// Address

		Address oldAddress = oldAccountEntry.getAddress();

		String street1 = address.getStreet1();

		if (!street1.equals("N/A") &&
			(accountEntry.getType() !=
				AccountEntryConstants.TYPE_ANALYTICS_CLOUD_BASIC)) {

			oldAddress.setStreet1(address.getStreet1());
			oldAddress.setCity(address.getCity());
			oldAddress.setZip(address.getZip());
			oldAddress.setRegionId(address.getRegionId());
			oldAddress.setCountryId(address.getCountryId());
		}

		// Account entry

		StringBundler sb = new StringBundler(3);

		sb.append(oldAccountEntry.getNotes());
		sb.append("\n\n--------------------------------------------------\n\n");
		sb.append(accountEntry.getNotes());

		if (accountEntry.getType() ==
				AccountEntryConstants.TYPE_ANALYTICS_CLOUD_BASIC) {

			oldAccountEntry.setDossieraAccountKey(
				accountEntry.getDossieraAccountKey());
			oldAccountEntry.setCorpEntryName(accountEntry.getCorpEntryName());
			oldAccountEntry.setIndustry(accountEntry.getIndustry());
			oldAccountEntry.setNotes(sb.toString());

			accountEntryLocalService.updateAccountEntry(oldAccountEntry);
		}
		else {
			accountEntryLocalService.updateAccountEntry(
				OSBConstants.USER_DEFAULT_USER_ID,
				oldAccountEntry.getAccountEntryId(),
				oldAccountEntry.getCorpProjectUuid(),
				accountEntry.getDossieraAccountKey(),
				accountEntry.getCorpEntryName(), oldAccountEntry.getName(),
				oldAccountEntry.getCode(), oldAccountEntry.getType(),
				accountEntry.getIndustry(), oldAccountEntry.getPartnerEntryId(),
				oldAccountEntry.getPartnerManagedSupport(),
				oldAccountEntry.getTier(), oldAccountEntry.getMaxCustomers(),
				oldAccountEntry.getInstructions(), sb.toString(),
				oldAccountEntry.getLanguageIds(),
				oldAccountEntry.getSupportRegionIds(),
				oldAddress.getAddressId(), oldAddress.getStreet1(),
				oldAddress.getStreet2(), oldAddress.getStreet3(),
				oldAddress.getCity(), oldAddress.getZip(),
				oldAddress.getRegionId(), oldAddress.getCountryId(),
				oldAccountEntry.getEWSADossieraProjectKey());
		}

		// Account worker

		if (accountWorker != null) {
			User user = userLocalService.getUser(accountWorker.getUserId());

			accountWorkerLocalService.addAccountWorker(
				OSBConstants.USER_DEFAULT_USER_ID, user.getEmailAddress(),
				accountEntry.getAccountEntryId(), accountWorker.getRole());
		}

		// Order entries

		List<OrderEntry> addedOrderEntries = new ArrayList<>();

		for (OrderEntry orderEntry : orderEntries) {
			Calendar startCal = Calendar.getInstance();

			startCal.setTime(orderEntry.getStartDate());

			OrderEntry curOrderEntry = addOrderEntry(
				orderEntry.getUserId(), accountEntry.getAccountEntryId(),
				StringPool.BLANK, startCal.get(Calendar.MONTH),
				startCal.get(Calendar.DATE), startCal.get(Calendar.YEAR), false,
				0, 0, 0, WorkflowConstants.STATUS_PENDING,
				salesforceOpportunityKey, orderEntry.getOfferingEntries());

			addedOrderEntries.add(curOrderEntry);
		}

		// Workflow

		TreeMap<String, String> oldAccountEntryAttributes = new TreeMap<>();
		TreeMap<String, String> newAccountEntryAttributes = new TreeMap<>();

		String oldAccountEntryName = oldAccountEntry.getName();

		if (!oldAccountEntryName.equals(accountEntry.getName())) {
			oldAccountEntryAttributes.put("name", oldAccountEntryName);
			newAccountEntryAttributes.put("name", accountEntry.getName());
		}

		if (partnerEntry != null) {
			if (oldAccountEntry.getPartnerEntryId() !=
					partnerEntry.getPartnerEntryId()) {

				oldAccountEntryAttributes.put(
					"partnerEntryId",
					String.valueOf(oldAccountEntry.getPartnerEntryId()));
				newAccountEntryAttributes.put(
					"partnerEntryId",
					String.valueOf(partnerEntry.getPartnerEntryId()));
			}
		}
		else if (oldAccountEntry.getPartnerEntryId() > 0) {
			oldAccountEntryAttributes.put(
				"partnerEntryId",
				String.valueOf(oldAccountEntry.getPartnerEntryId()));
			newAccountEntryAttributes.put("partnerEntryId", StringPool.BLANK);
		}

		if (oldAccountEntry.getPartnerManagedSupport() !=
				accountEntry.getPartnerManagedSupport()) {

			oldAccountEntryAttributes.put(
				"partnerManagedSupport",
				String.valueOf(oldAccountEntry.getPartnerManagedSupport()));
			newAccountEntryAttributes.put(
				"partnerManagedSupport",
				String.valueOf(accountEntry.getPartnerManagedSupport()));
		}

		HashMap<String, Serializable> workflowContext = new HashMap<>();

		if (!oldAccountEntryAttributes.isEmpty() &&
			!newAccountEntryAttributes.isEmpty()) {

			workflowContext.put(
				WorkflowConstants.CONTEXT_OLD_ACCOUNT_ENTRY_ATTRIBUTES,
				oldAccountEntryAttributes);
			workflowContext.put(
				WorkflowConstants.CONTEXT_NEW_ACCOUNT_ENTRY_ATTRIBUTES,
				newAccountEntryAttributes);
		}

		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_ACTION,
			Constants.ADD);

		int salesforceOpportunityType = GetterUtil.getInteger(
			serviceContext.getAttribute("salesforceOpportunityType"));

		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_TYPE,
			salesforceOpportunityType);

		String salesforceOpportunityTaskName =
			SalesforceConstants.getOpportunityTaskName(
				salesforceOpportunityType, Constants.ADD);

		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_TASK_NAME,
			salesforceOpportunityTaskName);

		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_KEY,
			salesforceOpportunityKey);
		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_STAGE_NAME,
			serviceContext.getAttribute("salesforceOpportunityStageName"));

		List<SupportRegion> supportRegions = accountEntry.getSupportRegions();

		if (!supportRegions.isEmpty()) {
			SupportRegion supportRegion = supportRegions.get(0);

			workflowContext.put(
				WorkflowConstants.CONTEXT_SUPPORT_REGION_NAME,
				supportRegion.getName());
		}

		workflowContext.put(
			WorkflowConstants.CONTEXT_WARNING_MESSAGES,
			serviceContext.getAttribute("warningMessages"));

		for (OrderEntry orderEntry : addedOrderEntries) {
			workflowContext.put(
				WorkflowConstants.CONTEXT_ORDER_ENTRY,
				SupportUtil.serialize(orderEntry));

			ServiceContext workflowServiceContext = new ServiceContext();

			workflowServiceContext.setAttribute(
				"workflowContext", workflowContext);

			WorkflowHandlerRegistryUtil.startWorkflowInstance(
				OSBConstants.COMPANY_ID, orderEntry.getUserId(),
				OrderEntry.class.getName(), orderEntry.getOrderEntryId(),
				orderEntry, workflowServiceContext);
		}

		return orderEntries;
	}

	public OrderEntry addOrderEntry(
			long userId, long accountEntryId, String purchaseOrderKey,
			int startDateMonth, int startDateDay, int startDateYear,
			boolean prorated, int actualStartDateMonth, int actualStartDateDay,
			int actualStartDateYear, int status,
			String salesforceOpportunityKey,
			List<OfferingEntry> offeringEntries)
		throws PortalException {

		// Order entry

		User user = userLocalService.getUser(userId);

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear,
			OrderEntryStartDateException.class);

		Date actualStartDate = null;

		if (prorated) {
			actualStartDate = PortalUtil.getDate(
				actualStartDateMonth, actualStartDateDay, actualStartDateYear,
				OrderEntryActualStartDateException.class);
		}

		Date now = new Date();

		validate(0, accountEntryId, salesforceOpportunityKey);

		long orderEntryId = counterLocalService.increment();

		OrderEntry orderEntry = orderEntryPersistence.create(orderEntryId);

		orderEntry.setUserId(user.getUserId());
		orderEntry.setUserName(user.getFullName());
		orderEntry.setCreateDate(now);
		orderEntry.setModifiedUserId(user.getUserId());
		orderEntry.setModifiedUserName(user.getFullName());
		orderEntry.setModifiedDate(now);
		orderEntry.setAccountEntryId(accountEntryId);
		orderEntry.setPurchaseOrderKey(purchaseOrderKey);
		orderEntry.setStartDate(startDate);
		orderEntry.setProrated(prorated);
		orderEntry.setActualStartDate(actualStartDate);
		orderEntry.setStatus(status);

		orderEntryPersistence.update(orderEntry);

		// External ids

		long classNameId = classNameLocalService.getClassNameId(
			OrderEntry.class);

		if (Validator.isNotNull(salesforceOpportunityKey)) {
			externalIdMapperLocalService.addExternalIdMapper(
				classNameId, orderEntry.getOrderEntryId(),
				ExternalIdMapperConstants.TYPE_SALESFORCE,
				salesforceOpportunityKey);
		}

		// Offering entries

		int offeringEntryStatus = getOfferingEntryStatus(status);

		for (OfferingEntry offeringEntry : offeringEntries) {
			offeringEntryLocalService.addOfferingEntry(
				userId, accountEntryId, orderEntryId,
				offeringEntry.getProductEntryId(),
				offeringEntry.getSupportResponseId(),
				offeringEntry.getProductDescription(), offeringEntry.getType(),
				offeringEntry.getVersion(), offeringEntry.getLicenses(),
				offeringEntry.getLicenseLifetime(),
				offeringEntry.getMaxConcurrentUsers(),
				offeringEntry.getMaxUsers(), offeringEntry.getSupportTickets(),
				offeringEntry.getSupportLifetime(), offeringEntry.getSizing(),
				offeringEntry.getQuantity(), offeringEntryStatus);
		}

		return orderEntry;
	}

	public void checkOrderEntries() {
		Set<Long> accountEntryIds = new HashSet<>();

		Date startDateLT = new Date();

		Date startDateGT = new Date(startDateLT.getTime() - Time.DAY);

		List<OrderEntry> orderEntries =
			orderEntryFinder.findByU_CD_MU_MD_AE_PO_S_SD_P_ASD(
				null, null, null, null, null, null, null, null,
				new int[] {WorkflowConstants.STATUS_APPROVED}, startDateGT,
				startDateLT, null, null, null, null, true, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		for (OrderEntry orderEntry : orderEntries) {
			if (accountEntryIds.contains(orderEntry.getAccountEntryId())) {
				continue;
			}

			try {
				lcsSubscriptionEntryLocalService.syncToLCS(
					orderEntry.getAccountEntryId());
			}
			catch (Exception e) {
				_log.error(
					"Unable to sync account entry " +
						orderEntry.getAccountEntryId() + " to LCS",
					e);
			}

			accountEntryIds.add(orderEntry.getAccountEntryId());
		}
	}

	@Override
	public OrderEntry deleteOrderEntry(long orderEntryId)
		throws PortalException {

		OrderEntry orderEntry = orderEntryPersistence.findByPrimaryKey(
			orderEntryId);

		return deleteOrderEntry(orderEntry);
	}

	@Override
	public OrderEntry deleteOrderEntry(OrderEntry orderEntry)
		throws PortalException {

		// Order entry

		orderEntryPersistence.remove(orderEntry);

		// External ids

		long classNameId = classNameLocalService.getClassNameId(
			OrderEntry.class.getName());

		externalIdMapperPersistence.removeByC_C(
			classNameId, orderEntry.getOrderEntryId());

		// Offering entries

		List<OfferingEntry> offeringEntries =
			offeringEntryPersistence.findByOrderEntryId(
				orderEntry.getOrderEntryId());

		for (OfferingEntry offeringEntry : offeringEntries) {
			offeringEntryLocalService.deleteOfferingEntry(offeringEntry);
		}

		return orderEntry;
	}

	public List<OrderEntry> getAccountEntryOrderEntries(long accountEntryId) {
		return orderEntryPersistence.findByAccountEntryId(accountEntryId);
	}

	public OrderEntry getOrderEntry(String uuid) throws PortalException {
		List<OrderEntry> orderEntries = orderEntryPersistence.findByUuid(uuid);

		if (orderEntries.isEmpty()) {
			throw new NoSuchOrderEntryException();
		}

		return orderEntries.get(0);
	}

	public OrderEntry renewOrderEntry(
			long userId, long orderEntryId, int renewCount)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		OrderEntry orderEntry = orderEntryPersistence.findByPrimaryKey(
			orderEntryId);

		orderEntry.setRenewCount(renewCount);

		orderEntryPersistence.update(orderEntry);

		// Offering entries

		List<OfferingEntry> offeringEntries =
			offeringEntryPersistence.findByOrderEntryId(orderEntryId);

		Date startDate = orderEntry.getStartDate();

		for (OfferingEntry offeringEntry : offeringEntries) {
			Date supportEndDate = new Date(
				startDate.getTime() + offeringEntry.getSupportLifetime() +
					(Time.YEAR * renewCount));

			offeringEntry.setSupportEndDate(supportEndDate);

			if (supportEndDate.after(now)) {
				offeringEntry.setStatus(OfferingEntryConstants.STATUS_ACTIVE);
			}

			offeringEntryPersistence.update(offeringEntry);
		}

		// Account entry

		accountEntryLocalService.recalculateHighestSupportResponse(
			orderEntry.getAccountEntryId());

		accountEntryLocalService.updateSupportStatus(
			orderEntry.getAccountEntryId());

		// Audit entry

		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class.getName());
		long fieldClassNameId = classNameLocalService.getClassNameId(
			OrderEntry.class.getName());

		long auditSetId = auditEntryLocalService.getNextAuditSetId(
			AccountEntry.class.getName(), orderEntry.getAccountEntryId());

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), new Date(), classNameId,
			orderEntry.getAccountEntryId(), auditSetId, fieldClassNameId,
			orderEntryId, AuditEntryConstants.ACTION_RENEW,
			AuditEntryConstants.FIELD_RENEW_COUNT, VisibilityConstants.ADMIN,
			StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
			String.valueOf(renewCount), StringPool.BLANK);

		return orderEntry;
	}

	public List<OrderEntry> search(
		Long createUserId, int createDateGTDay, int createDateGTMonth,
		int createDateGTYear, int createDateLTDay, int createDateLTMonth,
		int createDateLTYear, Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear, Long accountEntryId,
		String purchaseOrderKey, int[] statuses, int startDateGTDay,
		int startDateGTMonth, int startDateGTYear, int startDateLTDay,
		int startDateLTMonth, int startDateLTYear, Boolean prorated,
		int actualStartDateGTDay, int actualStartDateGTMonth,
		int actualStartDateGTYear, int actualStartDateLTDay,
		int actualStartDateLTMonth, int actualStartDateLTYear,
		LinkedHashMap<String, Object> params, boolean andOperator, int start,
		int end, OrderByComparator obc) {

		Date createDateGT = PortalUtil.getDate(
			createDateGTMonth, createDateGTDay, createDateGTYear);
		Date createDateLT = PortalUtil.getDate(
			createDateLTMonth, createDateLTDay, createDateLTYear);
		Date modifiedDateGT = PortalUtil.getDate(
			modifiedDateGTMonth, modifiedDateGTDay, modifiedDateGTYear);
		Date modifiedDateLT = PortalUtil.getDate(
			modifiedDateLTMonth, modifiedDateLTDay, modifiedDateLTYear);
		Date startDateGT = PortalUtil.getDate(
			startDateGTMonth, startDateGTDay, startDateGTYear);
		Date startDateLT = PortalUtil.getDate(
			startDateLTMonth, startDateLTDay, startDateLTYear);
		Date actualStartDateGT = PortalUtil.getDate(
			actualStartDateGTMonth, actualStartDateGTDay,
			actualStartDateGTYear);
		Date actualStartDateLT = PortalUtil.getDate(
			actualStartDateLTMonth, actualStartDateLTDay,
			actualStartDateLTYear);

		return orderEntryFinder.findByU_CD_MU_MD_AE_PO_S_SD_P_ASD(
			createUserId, createDateGT, createDateLT, modifiedUserId,
			modifiedDateGT, modifiedDateLT, accountEntryId, purchaseOrderKey,
			statuses, startDateGT, startDateLT, prorated, actualStartDateGT,
			actualStartDateLT, params, andOperator, start, end, obc);
	}

	public List<OrderEntry> search(
		String keywords, LinkedHashMap<String, Object> params, int start,
		int end, OrderByComparator obc) {

		return orderEntryFinder.findByKeywords(
			keywords, params, start, end, obc);
	}

	public int searchCount(
		Long createUserId, int createDateGTDay, int createDateGTMonth,
		int createDateGTYear, int createDateLTDay, int createDateLTMonth,
		int createDateLTYear, Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear, Long accountEntryId,
		String purchaseOrderKey, int[] statuses, int startDateGTDay,
		int startDateGTMonth, int startDateGTYear, int startDateLTDay,
		int startDateLTMonth, int startDateLTYear, Boolean prorated,
		int actualStartDateGTDay, int actualStartDateGTMonth,
		int actualStartDateGTYear, int actualStartDateLTDay,
		int actualStartDateLTMonth, int actualStartDateLTYear,
		LinkedHashMap<String, Object> params, boolean andOperator) {

		Date createDateGT = PortalUtil.getDate(
			createDateGTMonth, createDateGTDay, createDateGTYear);
		Date createDateLT = PortalUtil.getDate(
			createDateLTMonth, createDateLTDay, createDateLTYear);
		Date modifiedDateGT = PortalUtil.getDate(
			modifiedDateGTMonth, modifiedDateGTDay, modifiedDateGTYear);
		Date modifiedDateLT = PortalUtil.getDate(
			modifiedDateLTMonth, modifiedDateLTDay, modifiedDateLTYear);
		Date startDateGT = PortalUtil.getDate(
			startDateGTMonth, startDateGTDay, startDateGTYear);
		Date startDateLT = PortalUtil.getDate(
			startDateLTMonth, startDateLTDay, startDateLTYear);
		Date actualStartDateGT = PortalUtil.getDate(
			actualStartDateGTMonth, actualStartDateGTDay,
			actualStartDateGTYear);
		Date actualStartDateLT = PortalUtil.getDate(
			actualStartDateLTMonth, actualStartDateLTDay,
			actualStartDateLTYear);

		return orderEntryFinder.countByU_CD_MU_MD_AE_PO_S_SD_P_ASD(
			createUserId, createDateGT, createDateLT, modifiedUserId,
			modifiedDateGT, modifiedDateLT, accountEntryId, purchaseOrderKey,
			statuses, startDateGT, startDateLT, prorated, actualStartDateGT,
			actualStartDateLT, params, andOperator);
	}

	public int searchCount(
		String keywords, LinkedHashMap<String, Object> params) {

		return orderEntryFinder.countByKeywords(keywords, params);
	}

	public OrderEntry updateOrderEntry(
			long userId, long orderEntryId, long accountEntryId,
			String purchaseOrderKey, int startDateMonth, int startDateDay,
			int startDateYear, boolean prorated, int actualStartDateMonth,
			int actualStartDateDay, int actualStartDateYear,
			String salesforceOpportunityKey,
			List<OfferingEntry> offeringEntries)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear,
			OrderEntryStartDateException.class);

		Date actualStartDate = null;

		if (prorated) {
			actualStartDate = PortalUtil.getDate(
				actualStartDateMonth, actualStartDateDay, actualStartDateYear,
				OrderEntryActualStartDateException.class);
		}

		validate(orderEntryId, accountEntryId, salesforceOpportunityKey);

		OrderEntry orderEntry = orderEntryPersistence.findByPrimaryKey(
			orderEntryId);

		orderEntry.setModifiedUserId(user.getUserId());
		orderEntry.setModifiedUserName(user.getFullName());
		orderEntry.setModifiedDate(new Date());
		orderEntry.setAccountEntryId(accountEntryId);
		orderEntry.setPurchaseOrderKey(purchaseOrderKey);
		orderEntry.setStartDate(startDate);
		orderEntry.setProrated(prorated);
		orderEntry.setActualStartDate(actualStartDate);

		orderEntryPersistence.update(orderEntry);

		// External ids

		long classNameId = classNameLocalService.getClassNameId(
			OrderEntry.class);

		List<ExternalIdMapper> externalIdMappers =
			externalIdMapperLocalService.getExternalIdMappers(
				classNameId, orderEntryId,
				ExternalIdMapperConstants.TYPE_SALESFORCE);

		if (Validator.isNotNull(salesforceOpportunityKey)) {
			if (externalIdMappers.isEmpty()) {
				externalIdMapperLocalService.addExternalIdMapper(
					classNameId, orderEntry.getOrderEntryId(),
					ExternalIdMapperConstants.TYPE_SALESFORCE,
					salesforceOpportunityKey);
			}
			else {
				ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

				externalIdMapperLocalService.updateExternalIdMapper(
					externalIdMapper.getExternalIdMapperId(), classNameId,
					orderEntry.getOrderEntryId(),
					ExternalIdMapperConstants.TYPE_SALESFORCE,
					salesforceOpportunityKey);
			}
		}
		else if (!externalIdMappers.isEmpty()) {
			for (ExternalIdMapper externalIdMapper : externalIdMappers) {
				externalIdMapperLocalService.deleteExternalIdMapper(
					externalIdMapper.getExternalIdMapperId());
			}
		}

		// Offering entries

		List<OfferingEntry> orderEntryOfferingEntries =
			offeringEntryPersistence.findByOrderEntryId(orderEntryId);

		orderEntryOfferingEntries = ListUtil.copy(orderEntryOfferingEntries);

		for (OfferingEntry offeringEntry : offeringEntries) {
			if (orderEntryOfferingEntries.remove(offeringEntry)) {
				offeringEntryLocalService.updateOfferingEntry(
					userId, offeringEntry.getOfferingEntryId(), accountEntryId,
					orderEntryId, offeringEntry.getProductEntryId(),
					offeringEntry.getSupportResponseId(),
					offeringEntry.getProductDescription(),
					offeringEntry.getType(), offeringEntry.getVersion(),
					offeringEntry.getLicenses(),
					offeringEntry.getLicenseLifetime(),
					offeringEntry.getMaxConcurrentUsers(),
					offeringEntry.getMaxUsers(),
					offeringEntry.getSupportTickets(),
					offeringEntry.getSupportLifetime(),
					offeringEntry.getSizing(), offeringEntry.getQuantity());
			}
			else {
				int offeringEntryStatus = getOfferingEntryStatus(
					orderEntry.getStatus());

				offeringEntryLocalService.addOfferingEntry(
					userId, accountEntryId, orderEntryId,
					offeringEntry.getProductEntryId(),
					offeringEntry.getSupportResponseId(),
					offeringEntry.getProductDescription(),
					offeringEntry.getType(), offeringEntry.getVersion(),
					offeringEntry.getLicenses(),
					offeringEntry.getLicenseLifetime(),
					offeringEntry.getMaxConcurrentUsers(),
					offeringEntry.getMaxUsers(),
					offeringEntry.getSupportTickets(),
					offeringEntry.getSupportLifetime(),
					offeringEntry.getSizing(), offeringEntry.getQuantity(),
					offeringEntryStatus);
			}
		}

		for (OfferingEntry orderEntryOfferingEntry :
				orderEntryOfferingEntries) {

			offeringEntryLocalService.deleteOfferingEntry(
				orderEntryOfferingEntry);
		}

		// Account entry

		accountEntryLocalService.updateSupportStatus(
			orderEntry.getAccountEntryId());

		return orderEntry;
	}

	public OrderEntry updateStatus(
			long userId, long orderEntryId, int status,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		// Order entry

		OrderEntry orderEntry = orderEntryPersistence.findByPrimaryKey(
			orderEntryId);

		orderEntry.setModifiedDate(serviceContext.getModifiedDate(now));
		orderEntry.setStatus(status);
		orderEntry.setStatusByUserId(userId);
		orderEntry.setStatusByUserName(user.getFullName());
		orderEntry.setStatusDate(serviceContext.getModifiedDate(now));

		orderEntry = orderEntryPersistence.update(orderEntry);

		// Offering entries

		int offeringEntryStatus = getOfferingEntryStatus(status);

		List<OfferingEntry> offeringEntries =
			offeringEntryPersistence.findByOrderEntryId(orderEntryId);

		AccountEntry accountEntry = orderEntry.getAccountEntry();

		for (OfferingEntry offeringEntry : offeringEntries) {
			offeringEntryLocalService.updateStatus(
				userId, offeringEntry.getOfferingEntryId(),
				offeringEntryStatus);

			if ((status == WorkflowConstants.STATUS_APPROVED) &&
				(accountEntry.getType() ==
					AccountEntryConstants.TYPE_ANALYTICS_CLOUD_BASIC)) {

				ProductEntry productEntry = offeringEntry.getProductEntry();

				if (productEntry.isAnalyticsCloudBusiness() ||
					productEntry.isAnalyticsCloudEnterprise()) {

					accountEntry.setType(AccountEntryConstants.TYPE_GROUP);

					accountEntryLocalService.updateAccountEntry(accountEntry);

					accountEntryLocalService.recalculateHighestSupportResponse(
						orderEntry.getAccountEntryId());

					accountEntryLocalService.updateSupportStatus(
						orderEntry.getAccountEntryId());
				}
			}
		}

		// Account entry

		if ((accountEntry.getStatus() == WorkflowConstants.STATUS_REJECTED) &&
			(status == WorkflowConstants.STATUS_APPROVED)) {

			accountEntryLocalService.updateStatus(
				userId, accountEntry.getAccountEntryId(), status,
				serviceContext);
		}

		return orderEntry;
	}

	protected int getOfferingEntryStatus(int status) {
		if (status == WorkflowConstants.STATUS_APPROVED) {
			return OfferingEntryConstants.STATUS_ACTIVE;
		}
		else if ((status == WorkflowConstants.STATUS_PENDING) ||
				 (status == WorkflowConstants.STATUS_PENDING_VALIDATION)) {

			return OfferingEntryConstants.STATUS_PENDING;
		}
		else {
			return OfferingEntryConstants.STATUS_CLOSED;
		}
	}

	protected void validate(
			long orderEntryId, long accountEntryId,
			String salesforceOpportunityKey)
		throws PortalException {

		if (orderEntryId > 0) {
			OrderEntry orderEntry = orderEntryPersistence.findByPrimaryKey(
				orderEntryId);

			if ((orderEntry.getUserId() == OSBConstants.USER_DEFAULT_USER_ID) &&
				!salesforceOpportunityKey.equals(
					orderEntry.getSalesforceOpportunityKey())) {

				throw new PrincipalException();
			}
		}

		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		if (accountEntry.getRedirectAccountEntryId() > 0) {
			throw new NoSuchAccountEntryException();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OrderEntryLocalServiceImpl.class);

}