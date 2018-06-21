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

package com.liferay.osb.admin.workflow;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.LCSSubscriptionEntryLocalServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class AccountEntryWorkflowHandler<T> extends BaseWorkflowHandler<T> {

	public static final String CLASS_NAME = AccountEntry.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String getType(Locale locale) {
		return LanguageUtil.get(locale, "support-project");
	}

	@Override
	public void startWorkflowInstance(
			long companyId, long groupId, long userId, long classPK, T model,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		StringBundler sb = new StringBundler(6);

		sb.append("Provisioning Task - ");

		String salesforceOpportunityTaskName = (String)workflowContext.get(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_TASK_NAME);

		sb.append(LanguageUtil.get(Locale.US, salesforceOpportunityTaskName));

		sb.append(" for ");
		sb.append(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_SUPPORT_REGION_NAME));
		sb.append(" Region - ");

		AccountEntry accountEntry = (AccountEntry)model;

		sb.append(accountEntry.getCode());

		workflowContext.put(
			WorkflowConstants.CONTEXT_NOTIFICATION_SUBJECT, sb.toString());

		super.startWorkflowInstance(
			companyId, groupId, userId, classPK, model, workflowContext);
	}

	@Override
	public T updateStatus(int status, Map<String, Serializable> workflowContext)
		throws PortalException {

		try {
			return doUpdateStatus(status, workflowContext);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);

			throw pe;
		}
		catch (RuntimeException re) {
			_log.error(re, re);

			throw re;
		}
	}

	protected T doUpdateStatus(
			int status, Map<String, Serializable> workflowContext)
		throws PortalException {

		long userId = GetterUtil.getLong(
			(String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
		long classPK = GetterUtil.getLong(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

		ServiceContext serviceContext = (ServiceContext)workflowContext.get(
			WorkflowConstants.CONTEXT_SERVICE_CONTEXT);

		if (status == WorkflowConstants.STATUS_APPROVED) {
			ArrayList<User> missingUsers = (ArrayList<User>)workflowContext.get(
				WorkflowConstants.CONTEXT_MISSING_USERS);

			serviceContext.setAttribute("missingUsers", missingUsers);
		}

		AccountEntry accountEntry = null;

		String salesforceOpportunityAction = GetterUtil.getString(
			workflowContext.get(
				WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_ACTION));

		if (salesforceOpportunityAction.equals(Constants.UPDATE)) {
			String salesforceOpportunityKey = (String)workflowContext.get(
				WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_KEY);

			accountEntry = AccountEntryLocalServiceUtil.updateStatus(
				userId, classPK, salesforceOpportunityKey, status,
				serviceContext);
		}
		else {
			List<Long> orderEntryIds = new ArrayList<>();

			String orderEntries = (String)workflowContext.get(
				WorkflowConstants.CONTEXT_ORDER_ENTRIES);

			if (Validator.isNotNull(orderEntries)) {
				JSONArray jsonArray = JSONFactoryUtil.createJSONArray(
					orderEntries);

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);

					orderEntryIds.add(jsonObject.getLong("orderEntryId"));
				}
			}

			serviceContext.setAttribute(
				"orderEntryIds", orderEntryIds.toArray(new Long[0]));

			accountEntry = AccountEntryLocalServiceUtil.updateStatus(
				userId, classPK, status, serviceContext);
		}

		if (status == WorkflowConstants.STATUS_APPROVED) {
			syncToLCS(accountEntry);
		}

		return (T)accountEntry;
	}

	protected void syncToLCS(AccountEntry accountEntry) {
		try {
			LCSSubscriptionEntryLocalServiceUtil.syncToLCS(
				accountEntry.getAccountEntryId());
		}
		catch (Exception e) {
			_log.error(
				"Unable to sync account entry " +
					accountEntry.getAccountEntryId() + " to LCS",
				e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountEntryWorkflowHandler.class);

}