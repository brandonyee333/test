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
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.service.LCSSubscriptionEntryLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.osb.util.SalesforceConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;

import java.io.Serializable;

import java.util.Locale;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class OrderEntryWorkflowHandler<T> extends BaseWorkflowHandler<T> {

	public static final String CLASS_NAME = OrderEntry.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String getType(Locale locale) {
		return LanguageUtil.get(locale, "order");
	}

	@Override
	public void startWorkflowInstance(
			long companyId, long groupId, long userId, long classPK, T model,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		StringBundler sb = new StringBundler(6);

		sb.append("Provisioning Task - ");

		Integer salesforceOpportunityType = (Integer)workflowContext.get(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_TYPE);

		String salesforceOpportunityTypeLabel =
			SalesforceConstants.getOpportunityTypeLabel(
				salesforceOpportunityType);

		sb.append(LanguageUtil.get(Locale.US, salesforceOpportunityTypeLabel));

		sb.append(" for ");
		sb.append(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_SUPPORT_REGION_NAME));
		sb.append(" Region - ");

		OrderEntry orderEntry = (OrderEntry)model;

		AccountEntry accountEntry = orderEntry.getAccountEntry();

		sb.append(accountEntry.getCode());

		workflowContext.put(
			WorkflowConstants.CONTEXT_NOTIFICATION_SUBJECT, sb.toString());

		super.startWorkflowInstance(
			companyId, groupId, userId, classPK, model, workflowContext);
	}

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

		OrderEntry orderEntry = OrderEntryLocalServiceUtil.updateStatus(
			userId, classPK, status, serviceContext);

		if (status == WorkflowConstants.STATUS_APPROVED) {
			syncToLCS(orderEntry);
		}

		return (T)orderEntry;
	}

	protected void syncToLCS(OrderEntry orderEntry) {
		try {
			AccountEntry accountEntry = orderEntry.getAccountEntry();

			LCSSubscriptionEntryLocalServiceUtil.syncToLCS(
				accountEntry.getAccountEntryId());
		}
		catch (Exception e) {
			_log.error(
				"Unable to sync account entry " +
					orderEntry.getAccountEntryId() + " to LCS",
				e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OrderEntryWorkflowHandler.class);

}