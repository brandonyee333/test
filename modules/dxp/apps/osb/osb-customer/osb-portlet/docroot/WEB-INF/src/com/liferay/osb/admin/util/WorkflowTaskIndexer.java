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

package com.liferay.osb.admin.util;

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
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineHelperUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
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

/**
 * @author Amos Fong
 */
public class WorkflowTaskIndexer extends BaseIndexer<WorkflowTask> {

	public static final String CLASS_NAME = WorkflowTask.class.getName();

	public static final String[] CLASS_NAMES = {CLASS_NAME};

	public static final String PORTLET_ID = OSBPortletKeys.OSB_ADMIN;

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	public String[] getSearchClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter contextBooleanFilter, SearchContext searchContext)
		throws Exception {
	}

	@Override
	protected void doDelete(WorkflowTask workflowTask) throws Exception {
		Document document = new DocumentImpl();

		document.addUID(
			PORTLET_ID, CLASS_NAME,
			String.valueOf(workflowTask.getWorkflowTaskId()));

		IndexWriterHelperUtil.deleteDocument(
			getSearchEngineId(), OSBConstants.COMPANY_ID,
			document.get(Field.UID), false);
	}

	@Override
	protected Document doGetDocument(WorkflowTask workflowTask)
		throws Exception {

		Document document = new DocumentImpl();

		document.addUID(
			PORTLET_ID, CLASS_NAME,
			String.valueOf(workflowTask.getWorkflowTaskId()));

		document.addKeyword(Field.COMPANY_ID, OSBConstants.COMPANY_ID);
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

		if (accountEntry != null) {
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

			document.addText("projectCode", accountEntry.getCode());
			document.addText("projectName", accountEntry.getName());
		}

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
	protected void doReindex(String className, long classPK) throws Exception {
		WorkflowTask workflowTask = WorkflowTaskManagerUtil.getWorkflowTask(
			OSBConstants.COMPANY_ID, classPK);

		doReindex(workflowTask);
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
			int start = i * Indexer.DEFAULT_INTERVAL;

			int end = start + Indexer.DEFAULT_INTERVAL;

			reindexWorkflowTasks(companyId, start, end);
		}
	}

	@Override
	protected void doReindex(WorkflowTask workflowTask) throws Exception {
		Document document = getDocument(workflowTask);

		String searchEngineId = SearchEngineHelperUtil.getSearchEngineId(
			document);

		IndexWriterHelperUtil.updateDocument(
			searchEngineId, OSBConstants.COMPANY_ID, document, false);
	}

	protected void reindexWorkflowTasks(long companyId, int start, int end)
		throws Exception {

		List<WorkflowTask> workflowTasks =
			WorkflowTaskManagerUtil.getWorkflowTasks(
				companyId, null, start, end, null);

		if (workflowTasks.isEmpty()) {
			return;
		}

		Collection<Document> documents = new ArrayList<>();

		for (WorkflowTask workflowTask : workflowTasks) {
			try {
				Document document = getDocument(workflowTask);

				documents.add(document);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		IndexWriterHelperUtil.updateDocuments(
			getSearchEngineId(), companyId, documents, false);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WorkflowTaskIndexer.class);

}