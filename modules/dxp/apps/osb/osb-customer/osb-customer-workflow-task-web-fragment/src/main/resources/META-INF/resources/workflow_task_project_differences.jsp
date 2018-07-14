<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
WorkflowTask workflowTask = workflowTaskDisplayContext.getWorkflowTask();

WorkflowInstance workflowInstance = WorkflowInstanceManagerUtil.getWorkflowInstance(company.getCompanyId(), workflowTask.getWorkflowInstanceId());

Map<String, Serializable> workflowContext = workflowInstance.getWorkflowContext();

Map<String, String> oldAccountEntryAttributes = (TreeMap<String, String>)workflowContext.get("osbOldAccountEntryAttributes");

if ((oldAccountEntryAttributes != null) && !oldAccountEntryAttributes.isEmpty()) {
	Set<String> keys = oldAccountEntryAttributes.keySet();

	Iterator<String> iterator = keys.iterator();

	while (iterator.hasNext()) {
		String field = iterator.next();

		String label = field;

		if (field.equals("ewsaDossieraProjectKey")) {
			label = "ewsa-dossiera-project-key";
		}
		else if (field.equals("languageIds")) {
			label = "languages";
		}
		else if (field.equals("partnerEntryId")) {
			label = "partner";
		}
		else if (field.equals("partnerManagedSupport")) {
			label = "partner-first-line-support";
		}
		else if (field.equals("supportRegionIds")) {
			label = "support-regions";
		}
%>

		<%= LanguageUtil.get(request, label) %><%= iterator.hasNext() ? ", " : "" %>

<%
	}
}
%>