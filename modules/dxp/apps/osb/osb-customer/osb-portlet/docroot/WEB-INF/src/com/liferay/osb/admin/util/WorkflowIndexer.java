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

package com.liferay.osb.admin.util;

import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.SalesforceConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineHelperUtil;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskAssignee;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class WorkflowIndexer<T> extends BaseIndexer<T> {

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void doDelete(T object) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Document doGetDocument(T object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doReindex(T object) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/*public static final String[] CLASS_NAMES = {
		WorkflowTask.class.getName()
	};

	public static final String PORTLET_ID = OSBPortletKeys.OSB_ADMIN;

	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	public String getPortletId() {
		return PORTLET_ID;
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		WorkflowTask workflowTask = (WorkflowTask)obj;

		Document document = new DocumentImpl();

		document.addUID(
			PORTLET_ID, WorkflowTask.class.getName(),
			String.valueOf(workflowTask.getWorkflowTaskId()));

		String searchEngineId = SearchEngineHelperUtil.getSearchEngineId(
			document);

		SearchEngineUtil.deleteDocument(
			searchEngineId, OSBConstants.COMPANY_ID, document.get(Field.UID));
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		WorkflowTask workflowTask = (WorkflowTask)obj;

		Document document = new DocumentImpl();

		document.addUID(
			PORTLET_ID, WorkflowTask.class.getName(),
			String.valueOf(workflowTask.getWorkflowTaskId()));

		document.addDate(Field.CREATE_DATE, workflowTask.getCreateDate());
		document.addKeyword(
			Field.ENTRY_CLASS_NAME, WorkflowTask.class.getName());
		document.addKeyword(
			Field.ENTRY_CLASS_PK, workflowTask.getWorkflowTaskId());
		document.addKeyword(Field.NAME, workflowTask.getName());
		document.addKeyword(Field.PORTLET_ID, PORTLET_ID);

		Map<String, Serializable> workflowContext =
			workflowTask.getOptionalAttributes();

		List<WorkflowTaskAssignee> workflowTaskAssignees =
			workflowTask.getWorkflowTaskAssignees();

		for (WorkflowTaskAssignee workflowTaskAssignee :
				workflowTaskAssignees) {

			document.addText(
				"assigneeClassName",
				workflowTaskAssignee.getAssigneeClassName());
			document.addKeyword(
				"assigneeClassPK", workflowTaskAssignee.getAssigneeClassPK());
		}

		document.addKeyword("completed", workflowTask.isCompleted());
		document.addDate("completionDate", workflowTask.getCompletionDate());
		document.addDate("dueDate", workflowTask.getDueDate());

		String salesforceOpportunityAction = GetterUtil.getString(
			workflowContext.get(
				WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_ACTION));
		int salesforceOpportunityType = GetterUtil.getInteger(
			workflowContext.get(
				WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_TYPE));

		String salesforceOpportunityTaskName =
			SalesforceConstants.getOpportunityTaskName(
				salesforceOpportunityType, salesforceOpportunityAction);

		document.addKeyword(
			"salesforceOpportunityTaskName", salesforceOpportunityTaskName);

		document.addKeyword(
			"salesforceOpportunityType", salesforceOpportunityType);
		document.addKeyword(
			"salesforceOpportunityTypeLabel",
			SalesforceConstants.getOpportunityTypeLabel(
				salesforceOpportunityType));

		String className = (String)workflowContext.get(
			WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);
		long classPK = GetterUtil.getLong(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

		AccountEntry accountEntry = null;

		if (className.equals(AccountEntry.class.getName())) {
			accountEntry = AccountEntryLocalServiceUtil.getAccountEntry(
				classPK);
		}
		else if (className.equals(OrderEntry.class.getName())) {
			OrderEntry orderEntry = OrderEntryLocalServiceUtil.getOrderEntry(
				classPK);

			accountEntry = orderEntry.getAccountEntry();
		}

		document.addText("projectCode", accountEntry.getCode());
		document.addText("projectName", accountEntry.getName());

		return document;
	}

	@Override
	protected Summary doGetSummary(
			Document document, Locale locale, String snippet, 
			PortletRequest portletRequest, PortletResponse portletResponse) 
		throws Exception {

		return null;
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		Document document = getDocument(obj);

		String searchEngineId = SearchEngineHelperUtil.getSearchEngineId(
			document);

		SearchEngineUtil.updateDocument(
			searchEngineId, OSBConstants.COMPANY_ID, document);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		if (companyId != OSBConstants.COMPANY_ID) {
			return;
		}

		int count = WorkflowTaskManagerUtil.getWorkflowTaskCount(
			OSBConstants.COMPANY_ID, null);

		int pages = count / Indexer.DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = (i * Indexer.DEFAULT_INTERVAL);
			int end = start + Indexer.DEFAULT_INTERVAL;

			reindexWorkflowTasks(companyId, start, end);
		}
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected void reindexWorkflowTasks(long companyId, int start, int end)
		throws Exception {

		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasks(
				companyId, null, start, end, null);

		if (workflowTasks.isEmpty()) {
			return;
		}

		Collection<Document> documents = new ArrayList<Document>();

		for (WorkflowTask workflowTask : workflowTasks) {
			try {
				Document document = getDocument(workflowTask);

				documents.add(document);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		String searchEngineId = SearchEngineHelperUtil.getSearchEngineId(
			documents);

		SearchEngineUtil.updateDocuments(searchEngineId, companyId, documents);
	}

	private static Log _log = LogFactoryUtil.getLog(WorkflowIndexer.class);

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return null;
	}

*/

}