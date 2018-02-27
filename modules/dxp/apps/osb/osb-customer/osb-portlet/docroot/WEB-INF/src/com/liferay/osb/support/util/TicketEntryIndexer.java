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

package com.liferay.osb.support.util;

import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.model.SupportWorkerConstants;
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.TicketAttachmentConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketFeedbackConstants;
import com.liferay.osb.model.TicketFlagConstants;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerLocalServiceUtil;
import com.liferay.osb.service.TicketAttachmentLocalServiceUtil;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.osb.service.TicketFlagLocalServiceUtil;
import com.liferay.osb.service.TicketWorkerLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Subscription;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.InputStream;

import java.text.Format;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shinn Lok
 */
@Component(immediate = true, service = Indexer.class)
public class TicketEntryIndexer extends BaseIndexer<TicketEntry> {

	public static final String CLASS_NAME = TicketEntry.class.getName();

	public TicketEntryIndexer() {
		setDefaultSelectedFieldNames(
			Field.COMPANY_ID, Field.UID, Field.ENTRY_CLASS_NAME,
			Field.ENTRY_CLASS_PK, Field.TITLE, Field.DESCRIPTION);
		setFilterSearch(true);
		setPermissionAware(true);
		setStagingAware(false);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {

		BooleanQuery subsearchQuery = new BooleanQueryImpl();

		LinkedHashMap<String, Object> params =
			(LinkedHashMap<String, Object>)searchContext.getAttribute("params");

		boolean searchAttachments = true;

		if (params.containsKey("searchAttachments")) {
			searchAttachments = (Boolean)params.get("searchAttachments");
		}

		_addContentQuery(subsearchQuery, searchContext, searchAttachments);

		_addTicketStatusQuery(subsearchQuery, searchContext);

		Date closedDateGT = (Date)searchContext.getAttribute("closedDateGT");
		Date closedDateLT = (Date)searchContext.getAttribute("closedDateLT");

		_addDateQuery(
			subsearchQuery, searchContext, "closedDate", closedDateGT,
			closedDateLT);

		addSearchTerm(subsearchQuery, searchContext, "component", false);

		Date createDateGT = (Date)searchContext.getAttribute("createDateGT");
		Date createDateLT = (Date)searchContext.getAttribute("createDateLT");

		_addDateQuery(
			subsearchQuery, searchContext, "createDate", createDateGT,
			createDateLT);

		Date dueDateGT = (Date)searchContext.getAttribute("dueDateGT");
		Date dueDateLT = (Date)searchContext.getAttribute("dueDateLT");

		_addDateQuery(
			subsearchQuery, searchContext, "dueDate", dueDateGT, dueDateLT);

		addSearchTerm(subsearchQuery, searchContext, "envAS", false);
		addSearchTerm(subsearchQuery, searchContext, "envDB", false);
		addSearchTerm(subsearchQuery, searchContext, "envJVM", false);
		addSearchTerm(subsearchQuery, searchContext, "envLFR", false);
		addSearchTerm(subsearchQuery, searchContext, "envOS", false);
		addSearchTerm(subsearchQuery, searchContext, "escalationLevel", false);
		addSearchTerm(subsearchQuery, searchContext, "satisfiedDueDate", false);
		addSearchTerm(subsearchQuery, searchContext, "severity", false);

		Long userId = (Long)searchContext.getAttribute("userId");

		if (userId > 0) {
			subsearchQuery.addTerm("userId", userId);
		}

		long[] accountCustomerUserIds = (long[])params.get(
			"accountCustomerUserIds");
		long[] accountEntryIds = (long[])params.get("accountEntryIds");
		long[][] accountWorkers = (long[][])params.get("accountWorkers");

		boolean feedbackAvailable = false;

		if (params.containsKey("feedbackAvailable")) {
			feedbackAvailable = (Boolean)params.get("feedbackAvailable");
		}

		long[] partnerEntryIds = (long[])params.get("partnerEntryIds");
		int[] pendingTypes = (int[])params.get("pendingTypes");
		Object[] primaryTicketWorker = (Object[])params.get(
			"primaryTicketWorker");
		long[] productEntryIds = (long[])params.get("productEntryIds");
		long[] reporterUserIds = (long[])params.get("reporterUserIds");
		Long subscriptionUserId = (Long)params.get("subscription");
		long[] supportRegionIds = (long[])params.get("supportRegionIds");
		long[] supportTeamIds = (long[])params.get("supportTeamIds");
		long[] ticketWorkerUserIds = (long[])params.get("ticketWorkerUserIds");
		long[] ticketWorkerUserIdsCount = (long[])params.get(
			"ticketWorkerUserIdsCount");

		if (ArrayUtil.isNotEmpty(accountCustomerUserIds)) {
			BooleanQuery accountCustomerQuery = new BooleanQueryImpl();

			Set<Long> accountCustomerAccountEntryIds = new HashSet<>();

			for (long accountCustomerUserId : accountCustomerUserIds) {
				List<AccountCustomer> accountCustomers =
					AccountCustomerLocalServiceUtil.getUserAccountCustomers(
						accountCustomerUserId);

				for (AccountCustomer accountCustomer : accountCustomers) {
					accountCustomerAccountEntryIds.add(
						accountCustomer.getAccountEntryId());
				}
			}

			for (long accountEntryId : accountCustomerAccountEntryIds) {
				accountCustomerQuery.addTerm("accountEntryId", accountEntryId);
			}

			if (accountCustomerQuery.hasClauses()) {
				searchQuery.add(accountCustomerQuery, BooleanClauseOccur.MUST);
			}
		}

		if (ArrayUtil.isNotEmpty(accountEntryIds)) {
			BooleanQuery accountEntryQuery = new BooleanQueryImpl();

			for (long accountEntryId : accountEntryIds) {
				accountEntryQuery.addTerm("accountEntryId", accountEntryId);
			}

			searchQuery.add(accountEntryQuery, BooleanClauseOccur.MUST);
		}

		if (ArrayUtil.isNotEmpty(accountWorkers)) {
			BooleanQuery accountWorkerQuery = new BooleanQueryImpl();

			for (long[] accountWorker : accountWorkers) {
				long accountWorkerUserId = accountWorker[0];
				long accountWorkerRole = accountWorker[1];

				accountWorkerQuery.addTerm(
					"accountWorkers",
					AccountWorkerConstants.getKey(
						accountWorkerUserId, accountWorkerRole));
			}

			searchQuery.add(accountWorkerQuery, BooleanClauseOccur.MUST);
		}

		if (feedbackAvailable) {
			long searchUserId = (Long)searchContext.getAttribute(
				"searchUserId");

			BooleanQuery feedbackAvailableQuery = new BooleanQueryImpl();

			feedbackAvailableQuery.addRequiredTerm(
				"ticketFeedbackUserIds", searchUserId);

			searchQuery.add(
				feedbackAvailableQuery, BooleanClauseOccur.MUST_NOT);

			subsearchQuery.addRequiredTerm(
				"status", TicketEntryConstants.STATUS_CLOSED);

			Calendar startCal = Calendar.getInstance();
			Calendar endCal = Calendar.getInstance();

			startCal.add(Calendar.DATE, -30);

			_addDateQuery(
				searchQuery, searchContext, "closedDate", startCal.getTime(),
				endCal.getTime());
		}

		if (ArrayUtil.isNotEmpty(partnerEntryIds)) {
			BooleanQuery partnerEntryQuery = new BooleanQueryImpl();

			for (long partnerEntryId : partnerEntryIds) {
				List<AccountEntry> accountEntries =
					AccountEntryLocalServiceUtil.getPartnerAccountEntries(
						partnerEntryId);

				for (AccountEntry accountEntry : accountEntries) {
					if (accountEntry.isPartnerManagedSupport()) {
						partnerEntryQuery.addTerm(
							"accountEntryId", accountEntry.getAccountEntryId());
					}
				}
			}

			if (partnerEntryQuery.hasClauses()) {
				searchQuery.add(partnerEntryQuery, BooleanClauseOccur.MUST);
			}
		}

		if (ArrayUtil.isNotEmpty(pendingTypes)) {
			BooleanQuery pendingTypesQuery = new BooleanQueryImpl();

			for (int pendingType : pendingTypes) {
				pendingTypesQuery.addTerm("pendingTypes", pendingType);
			}

			searchQuery.add(pendingTypesQuery, BooleanClauseOccur.MUST);
		}

		if (ArrayUtil.isNotEmpty(primaryTicketWorker)) {
			BooleanQuery primaryTicketWorkerQuery = new BooleanQueryImpl();

			Long ticketWorkerUserId = (Long)primaryTicketWorker[0];
			Boolean primary = (Boolean)primaryTicketWorker[1];

			primaryTicketWorkerQuery.addTerm(
				"primaryTicketWorker", ticketWorkerUserId);

			if (primary) {
				searchQuery.add(
					primaryTicketWorkerQuery, BooleanClauseOccur.MUST);
			}
			else {
				BooleanQuery ticketWorkerQuery = new BooleanQueryImpl();

				ticketWorkerQuery.addTerm(
					"ticketWorkerUserIds", ticketWorkerUserId);

				searchQuery.add(ticketWorkerQuery, BooleanClauseOccur.MUST);

				searchQuery.add(
					primaryTicketWorkerQuery, BooleanClauseOccur.MUST_NOT);
			}
		}

		if (ArrayUtil.isNotEmpty(productEntryIds)) {
			BooleanQuery productEntryIdsQuery = new BooleanQueryImpl();

			for (long productEntryId : productEntryIds) {
				productEntryIdsQuery.addTerm("productEntryId", productEntryId);
			}

			searchQuery.add(productEntryIdsQuery, BooleanClauseOccur.MUST);
		}

		if (ArrayUtil.isNotEmpty(reporterUserIds)) {
			BooleanQuery reporterQuery = new BooleanQueryImpl();

			for (long reporterUserId : reporterUserIds) {
				reporterQuery.addTerm("reporterUserId", reporterUserId);
			}

			searchQuery.add(reporterQuery, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(subscriptionUserId)) {
			BooleanQuery subscriptionsQuery = new BooleanQueryImpl();

			subscriptionsQuery.addTerm("subscriptions", subscriptionUserId);

			searchQuery.add(subscriptionsQuery, BooleanClauseOccur.MUST);
		}

		if (ArrayUtil.isNotEmpty(supportRegionIds)) {
			BooleanQuery supportRegionQuery = new BooleanQueryImpl();

			for (long supportRegionId : supportRegionIds) {
				supportRegionQuery.addTerm("supportRegionId", supportRegionId);
			}

			searchQuery.add(supportRegionQuery, BooleanClauseOccur.MUST);
		}

		if (ArrayUtil.isNotEmpty(supportTeamIds)) {
			BooleanQuery supportTeamQuery = new BooleanQueryImpl();

			for (long supportTeamId : supportTeamIds) {
				List<SupportWorker> supportWorkers =
					SupportWorkerLocalServiceUtil.getTeamSupportWorkers(
						supportTeamId);

				for (SupportWorker supportWorker : supportWorkers) {
					if (supportWorker.getRole() !=
							SupportWorkerConstants.ROLE_WATCHER) {

						supportTeamQuery.addTerm(
							"ticketWorkerUserIds", supportWorker.getUserId());
					}
				}
			}

			if (supportTeamQuery.hasClauses()) {
				searchQuery.add(supportTeamQuery, BooleanClauseOccur.MUST);
			}
		}

		if (ArrayUtil.isNotEmpty(ticketWorkerUserIds)) {
			BooleanQuery ticketWorkerQuery = new BooleanQueryImpl();

			for (long ticketWorkerUserId : ticketWorkerUserIds) {
				ticketWorkerQuery.addTerm(
					"ticketWorkerUserIds", ticketWorkerUserId);
			}

			searchQuery.add(ticketWorkerQuery, BooleanClauseOccur.MUST);
		}

		if (ArrayUtil.isNotEmpty(ticketWorkerUserIdsCount)) {
			if (searchContext.isAndSearch()) {
				subsearchQuery.addRequiredTerm("ticketWorkerUserIdsCount", "0");
			}
			else {
				subsearchQuery.addTerm("ticketWorkerUserIdsCount", "0");
			}
		}

		if (subsearchQuery.hasClauses()) {
			searchQuery.add(subsearchQuery, BooleanClauseOccur.MUST);
		}

		_addViewableAccountEntriesQuery(searchQuery, searchContext);
	}

	protected void addSortableField(
		Document document, String name, long value) {

		String sortableFieldName = DocumentImpl.getSortableFieldName(name);

		Field field = new Field(sortableFieldName, String.valueOf(value));

		field.setNumeric(true);
		field.setNumericClass(Long.class);

		document.add(field);
	}

	protected void addSortableField(
		Document document, String name, String value) {

		String sortableFieldName = DocumentImpl.getSortableFieldName(name);

		Field field = new Field(
			sortableFieldName, value.replaceAll("\\W", StringPool.BLANK));

		field.setNumeric(false);

		document.add(field);
	}

	@Override
	protected void doDelete(TicketEntry ticketEntry) throws Exception {
		Document document = new DocumentImpl();

		document.addUID(
			CLASS_NAME, String.valueOf(ticketEntry.getTicketEntryId()));

		IndexWriterHelperUtil.deleteDocument(
			getSearchEngineId(), OSBConstants.COMPANY_ID,
			document.get(Field.UID), isCommitImmediately());
	}

	@Override
	protected Document doGetDocument(TicketEntry ticketEntry) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, ticketEntry);

		document.addUID(
			CLASS_NAME, String.valueOf(ticketEntry.getTicketEntryId()));

		document.addKeyword(
			Field.ENTRY_CLASS_NAME, TicketEntry.class.getName());
		document.addKeyword(
			Field.ENTRY_CLASS_PK, ticketEntry.getTicketEntryId());

		AccountEntry accountEntry = ticketEntry.getAccountEntry();

		document.addText("accountEntryCode", accountEntry.getCode());
		document.addKeyword("accountEntryId", accountEntry.getAccountEntryId());
		document.addText("accountEntryName", accountEntry.getName());
		document.addKeyword("accountEntryTier", accountEntry.getTier());

		String[] accountWorkerKeys = SupportUtil.getAccountWorkerKeys(
			ticketEntry.getAccountEntryId());

		document.addKeyword("accountWorkers", accountWorkerKeys);

		document.addDate("closedDate", ticketEntry.getClosedDate());
		document.addKeyword("component", ticketEntry.getComponent());
		document.addDate("createDate", ticketEntry.getCreateDate());
		document.addText("description", ticketEntry.getDescription());
		document.addDate("dueDate", ticketEntry.getDueDate());
		document.addKeyword("envAS", ticketEntry.getEnvAS());
		document.addKeyword("envDB", ticketEntry.getEnvDB());
		document.addKeyword("envJVM", ticketEntry.getEnvJVM());
		document.addKeyword("envLFR", ticketEntry.getEnvLFR());
		document.addKeyword("envOS", ticketEntry.getEnvOS());
		document.addKeyword(
			"escalationLevel", ticketEntry.getEscalationLevel());

		String[] liferayIncTicketComments = SupportUtil.getTicketEntryComments(
			ticketEntry.getTicketEntryId(), VisibilityConstants.LIFERAY_INC);

		document.addText("liferayIncTicketComments", liferayIncTicketComments);

		document.addKeyword("partnerEntryId", accountEntry.getPartnerEntryId());

		int[] pendingTypes = TicketFlagLocalServiceUtil.getTicketFlagTypes(
			ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPES_PENDING,
			TicketFlagConstants.FLAG_TRUE);

		document.addKeyword("pendingTypes", pendingTypes);

		TicketWorker primaryTicketWorker =
			TicketWorkerLocalServiceUtil.fetchPrimaryTicketWorker(
				ticketEntry.getTicketEntryId());

		if (primaryTicketWorker != null) {
			document.addKeyword(
				"primaryTicketWorker", primaryTicketWorker.getUserId());
		}

		document.addKeyword("productEntryId", ticketEntry.getProductEntryId());

		String[] publicTicketComments = SupportUtil.getTicketEntryComments(
			ticketEntry.getTicketEntryId(), VisibilityConstants.PUBLIC);

		document.addText("publicTicketComments", publicTicketComments);

		document.addKeyword("resolution", ticketEntry.getResolution());

		boolean satisfiedDueDate = false;

		Date closedDate = ticketEntry.getClosedDate();

		if ((closedDate != null) &&
			closedDate.before(ticketEntry.getDueDate())) {

			satisfiedDueDate = true;
		}

		document.addKeyword("satisfiedDueDate", satisfiedDueDate);

		document.addKeyword("severity", ticketEntry.getSeverity());
		document.addKeyword("status", ticketEntry.getStatus());
		document.addText("subject", ticketEntry.getSubject());

		List<Subscription> subscriptions =
			SubscriptionLocalServiceUtil.getSubscriptions(
				OSBConstants.COMPANY_ID, TicketEntry.class.getName(),
				ticketEntry.getTicketEntryId());

		long[] subscriptionUserIds = new long[subscriptions.size()];

		for (int i = 0; i < subscriptions.size(); i++) {
			Subscription subscription = subscriptions.get(i);

			subscriptionUserIds[i] = subscription.getUserId();
		}

		document.addKeyword("subscriptions", subscriptionUserIds);

		document.addKeyword(
			"supportRegionId", ticketEntry.getSupportRegionId());

		List<TicketAttachment> ticketAttachments =
			ticketEntry.getTicketAttachments();

		List<String> ticketAttachmentContents = new ArrayList<>();
		List<String> ticketAttachmentFileNames = new ArrayList<>();

		long indexMaxSize =
			PortletPropsValues.TICKET_ATTACHMENT_INDEXING_MAX_SIZE;

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			ticketAttachmentFileNames.add(ticketAttachment.getFileName());

			if (ArrayUtil.contains(
					TicketAttachmentConstants.TYPES_LARGE,
					ticketAttachment.getType())) {

				continue;
			}

			if ((indexMaxSize != -1) &&
				(ticketAttachment.getFileSize() > indexMaxSize)) {

				continue;
			}

			if (!ArrayUtil.contains(
					PortletPropsValues.TICKET_ATTACHMENT_INDEXING_EXTENSIONS,
					StringPool.PERIOD +
						FileUtil.getExtension(
							ticketAttachment.getFileName()))) {

				continue;
			}

			InputStream is = null;

			try {
				is = TicketAttachmentLocalServiceUtil.getFileAsStream(
					ticketAttachment);

				ticketAttachmentContents.add(
					FileUtil.extractText(is, ticketAttachment.getFileName()));
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to index " + ticketAttachment.getFileName(), e);
				}
			}
			finally {
				if (is != null) {
					is.close();
				}
			}
		}

		document.addText(
			"ticketAttachmentContents",
			ArrayUtil.toStringArray(ticketAttachmentContents.toArray()));
		document.addText(
			"ticketAttachmentFileNames",
			ArrayUtil.toStringArray(ticketAttachmentFileNames.toArray()));

		document.addNumber("ticketId", ticketEntry.getTicketId());

		long[] ticketFeedbackUserIds = SupportUtil.getTicketFeedbackUserIds(
			ticketEntry.getTicketEntryId(),
			TicketFeedbackConstants.SUBJECT_LIFERAY);

		document.addKeyword("ticketFeedbackUserIds", ticketFeedbackUserIds);

		long[] ticketWorkerUserIds = SupportUtil.getTicketWorkerUserIds(
			ticketEntry.getTicketEntryId());

		document.addKeyword("ticketWorkerUserIds", ticketWorkerUserIds);
		document.addKeyword(
			"ticketWorkerUserIdsCount", ticketWorkerUserIds.length);

		document.addKeyword("userId", ticketEntry.getUserId());

		String[] workersTicketComments = SupportUtil.getTicketEntryComments(
			ticketEntry.getTicketEntryId(), VisibilityConstants.WORKERS);

		document.addText("workersTicketComments", workersTicketComments);

		addSortableField(document, "accountEntryCode", accountEntry.getCode());

		String assignee = StringPool.BLANK;

		if (primaryTicketWorker != null) {
			User assigneeUser = UserLocalServiceUtil.fetchUserById(
				primaryTicketWorker.getUserId());

			assignee = assigneeUser.getFullName();
		}

		addSortableField(document, "assignee", assignee);

		Date createDate = ticketEntry.getCreateDate();

		addSortableField(document, "createDate", createDate.getTime());

		Date dueDate = ticketEntry.getDueDate();

		addSortableField(document, "dueDate", dueDate.getTime());

		Date modifiedDate = ticketEntry.getModifiedDate();

		addSortableField(document, "modifiedDate", modifiedDate.getTime());

		addSortableField(document, "resolution", ticketEntry.getResolution());
		addSortableField(document, "status", ticketEntry.getStatus());
		addSortableField(document, "subject", ticketEntry.getSubject());
		addSortableField(document, "ticketId", ticketEntry.getTicketId());

		return document;
	}

	@Override
	protected String doGetSortField(String orderByCol) {
		String sortFieldName = orderByCol;

		if (orderByCol.equals("account-code")) {
			sortFieldName = "accountEntryCode";
		}
		else if (orderByCol.equals("assignee")) {
			sortFieldName = "assignee";
		}
		else if (orderByCol.equals("create-date")) {
			sortFieldName = "createDate";
		}
		else if (orderByCol.equals("due-date")) {
			sortFieldName = "dueDate";
		}
		else if (orderByCol.equals("modified-date")) {
			sortFieldName = "modifiedDate";
		}
		else if (orderByCol.equals("status")) {
			sortFieldName = "status";
		}
		else if (orderByCol.equals("subject")) {
			sortFieldName = "subject";
		}
		else if (orderByCol.equals("ticket-id")) {
			sortFieldName = "ticketId";
		}

		return DocumentImpl.getSortableFieldName(sortFieldName);
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		String title = document.get(Field.TITLE);

		String description = snippet;

		if (Validator.isNull(snippet)) {
			description = StringUtil.shorten(
				document.get(Field.DESCRIPTION), 200);
		}

		return new Summary(locale, title, description);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		TicketEntry ticketEntry = TicketEntryLocalServiceUtil.getTicketEntry(
			classPK);

		doReindex(ticketEntry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexEntries(companyId);
	}

	@Override
	protected void doReindex(TicketEntry ticketEntry) throws Exception {
		Document document = getDocument(ticketEntry);

		IndexWriterHelperUtil.updateDocument(
			getSearchEngineId(), OSBConstants.COMPANY_ID, document,
			isCommitImmediately());
	}

	protected boolean isOrganizationLiferayInc(long userId) {
		if (OrganizationLocalServiceUtil.hasUserOrganization(
				userId, OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return true;
		}
		else {
			return false;
		}
	}

	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			TicketEntryLocalServiceUtil.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setInterval(
			PortletPropsValues.TICKET_ENTRY_INDEXING_INTERVAL);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<TicketEntry>() {

				@Override
				public void performAction(TicketEntry ticketEntry) {
					try {
						Document document = getDocument(ticketEntry);

						indexableActionableDynamicQuery.addDocuments(document);
					}
					catch (PortalException pe) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Unable to index ticket entry " +
									ticketEntry.getTicketEntryId(),
								pe);
						}
					}
				}

			});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private void _addAccountEntryQuery(
			BooleanQuery subsearchQuery, SearchContext searchContext)
		throws Exception {

		BooleanQuery accountEntryQuery = new BooleanQueryImpl();

		BooleanQuery accountEntryCodeQuery = new BooleanQueryImpl();

		addSearchTerm(
			accountEntryCodeQuery, searchContext, "accountEntryCode", true);

		if (accountEntryCodeQuery.hasClauses()) {
			accountEntryQuery.add(
				accountEntryCodeQuery, BooleanClauseOccur.SHOULD);
		}

		BooleanQuery accountEntryNameQuery = new BooleanQueryImpl();

		addSearchTerm(
			accountEntryNameQuery, searchContext, "accountEntryName", true);

		if (accountEntryNameQuery.hasClauses()) {
			accountEntryQuery.add(
				accountEntryNameQuery, BooleanClauseOccur.SHOULD);
		}

		Integer[] accountEntryTiers = (Integer[])searchContext.getAttribute(
			"accountEntryTier");

		if ((accountEntryTiers != null) && (accountEntryTiers.length > 0)) {
			BooleanQuery accountEntryTierQuery = new BooleanQueryImpl();

			addSearchTerm(
				accountEntryTierQuery, searchContext, "accountEntryTier",
				false);

			accountEntryQuery.add(
				accountEntryTierQuery, BooleanClauseOccur.SHOULD);
		}

		if (accountEntryQuery.hasClauses()) {
			subsearchQuery.add(accountEntryQuery, BooleanClauseOccur.SHOULD);
		}
	}

	private void _addContentQuery(
			BooleanQuery searchQuery, SearchContext searchContext,
			boolean searchAttachments)
		throws Exception {

		BooleanQuery subsearchQuery = new BooleanQueryImpl();

		_addAccountEntryQuery(subsearchQuery, searchContext);

		String content = (String)searchContext.getAttribute("content");

		if (Validator.isNotNull(content)) {
			BooleanQuery contentQuery = new BooleanQueryImpl();

			long userId = (Long)searchContext.getAttribute("searchUserId");

			boolean organizationLiferayInc = isOrganizationLiferayInc(userId);

			List<AccountEntry> accountEntries = new ArrayList<>();

			if (!organizationLiferayInc) {
				List<PartnerWorker> partnerWorkers =
					PartnerWorkerLocalServiceUtil.getUserPartnerWorkers(userId);

				for (PartnerWorker partnerWorker : partnerWorkers) {
					List<AccountEntry> curAccountEntries =
						AccountEntryLocalServiceUtil.getPartnerAccountEntries(
							partnerWorker.getPartnerEntryId());

					accountEntries.addAll(curAccountEntries);
				}
			}

			BooleanQuery accountEntryBooleanQuery = new BooleanQueryImpl();

			for (AccountEntry accountEntry : accountEntries) {
				accountEntryBooleanQuery.addTerm(
					"accountEntryId", accountEntry.getAccountEntryId());
			}

			contentQuery.addTerm(Field.DESCRIPTION, content);
			contentQuery.addTerm("publicTicketComments", content);
			contentQuery.addTerm("subject", content);

			if (searchAttachments) {
				contentQuery.addTerm("ticketAttachmentContents", content);
				contentQuery.addTerm("ticketAttachmentFileNames", content);
			}

			if (organizationLiferayInc) {
				contentQuery.addTerm("liferayIncTicketComments", content);
				contentQuery.addTerm("workersTicketComments", content);
			}
			else if (!accountEntries.isEmpty()) {
				BooleanQuery workersTicketCommentsQuery =
					new BooleanQueryImpl();

				workersTicketCommentsQuery.add(
					accountEntryBooleanQuery, BooleanClauseOccur.MUST);

				workersTicketCommentsQuery.addRequiredTerm(
					"workersTicketComments", content);

				contentQuery.add(
					workersTicketCommentsQuery, BooleanClauseOccur.SHOULD);
			}

			if (contentQuery.hasClauses()) {
				subsearchQuery.add(contentQuery, BooleanClauseOccur.SHOULD);
			}
		}

		if (subsearchQuery.hasClauses()) {
			searchQuery.add(subsearchQuery, BooleanClauseOccur.MUST);
		}
	}

	private void _addDateQuery(
			BooleanQuery searchQuery, SearchContext searchContext, String field,
			Date dateGT, Date dateLT)
		throws ParseException {

		if ((dateGT == null) && (dateLT == null)) {
			return;
		}

		String startValue = null;
		String endValue = null;

		Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"yyyyMMddHHmmss");

		if (dateGT != null) {
			startValue = dateFormat.format(dateGT);
		}

		if (dateLT != null) {
			endValue = dateFormat.format(dateLT);
		}

		BooleanQuery dateQuery = new BooleanQueryImpl();

		dateQuery.addRangeTerm(field, startValue, endValue);

		if (searchContext.isAndSearch()) {
			searchQuery.add(dateQuery, BooleanClauseOccur.MUST);
		}
		else {
			searchQuery.add(dateQuery, BooleanClauseOccur.SHOULD);
		}
	}

	private void _addTicketStatusQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		Integer[] resolutionValues = (Integer[])searchContext.getAttribute(
			"resolution");
		Integer[] statusValues = (Integer[])searchContext.getAttribute(
			"status");

		if (ArrayUtil.isEmpty(resolutionValues) &&
			ArrayUtil.isEmpty(statusValues)) {

			return;
		}

		BooleanQuery statusQuery = new BooleanQueryImpl();

		if (resolutionValues != null) {
			for (int resolutionValue : resolutionValues) {
				if (ArrayUtil.contains(
						statusValues, TicketEntryConstants.STATUS_CLOSED)) {

					BooleanQuery resolutionQuery = new BooleanQueryImpl();

					resolutionQuery.addRequiredTerm(
						"resolution", resolutionValue);
					resolutionQuery.addRequiredTerm(
						"status", TicketEntryConstants.STATUS_CLOSED);

					statusQuery.add(resolutionQuery, BooleanClauseOccur.SHOULD);
				}
				else {
					statusQuery.addTerm("resolution", resolutionValue);
				}
			}
		}

		if (statusValues != null) {
			for (int statusValue : statusValues) {
				if (ArrayUtil.isNotEmpty(resolutionValues) &&
					(statusValue == TicketEntryConstants.STATUS_CLOSED)) {

					continue;
				}

				statusQuery.addTerm("status", statusValue);
			}
		}

		LinkedHashMap<String, Object> params =
			(LinkedHashMap<String, Object>)searchContext.getAttribute("params");

		if (params.containsKey("pendingCustomer")) {
			BooleanQuery pendingCustomerQuery = new BooleanQueryImpl();

			pendingCustomerQuery.addRequiredTerm(
				"resolution", TicketEntryConstants.RESOLUTION_PENDING_CUSTOMER);
			pendingCustomerQuery.addRequiredTerm(
				"status", TicketEntryConstants.STATUS_CLOSED);

			statusQuery.add(pendingCustomerQuery, BooleanClauseOccur.SHOULD);
		}

		if (statusQuery.hasClauses()) {
			searchQuery.add(statusQuery, BooleanClauseOccur.MUST);
		}
	}

	private void _addViewableAccountEntriesQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws ParseException, PrincipalException {

		LinkedHashMap<String, Object> params =
			(LinkedHashMap<String, Object>)searchContext.getAttribute("params");

		long userId = GetterUtil.getLong(params.get("accountEntryMembership"));

		long accountEntryId = (Long)searchContext.getAttribute(
			"accountEntryId");

		BooleanQuery accountEntriesQuery = new BooleanQueryImpl();

		if (userId <= 0) {
			if (accountEntryId != 0) {
				accountEntriesQuery.addTerm(
					"accountEntryId", String.valueOf(accountEntryId));

				searchQuery.add(accountEntriesQuery, BooleanClauseOccur.MUST);
			}

			return;
		}

		List<Long> accountEntryIds =
			AccountEntryLocalServiceUtil.getUserAccountEntryIds(
				userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		if (accountEntryIds.isEmpty() ||
			((accountEntryId != 0) &&
			 !accountEntryIds.contains(accountEntryId))) {

			throw new PrincipalException();
		}

		if (accountEntryIds.contains(accountEntryId)) {
			accountEntriesQuery.addTerm(
				"accountEntryId", String.valueOf(accountEntryId));
		}
		else {
			for (Long curAccountEntryId : accountEntryIds) {
				accountEntriesQuery.addTerm(
					"accountEntryId", String.valueOf(curAccountEntryId));
			}
		}

		searchQuery.add(accountEntriesQuery, BooleanClauseOccur.MUST);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TicketEntryIndexer.class);

}