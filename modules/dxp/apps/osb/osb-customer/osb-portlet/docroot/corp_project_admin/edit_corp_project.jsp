<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect", currentURL);
String backURL = ParamUtil.getString(request, "backURL", redirect);

long corpProjectId = ParamUtil.getLong(request, "corpProjectId");

CorpProject corpProject = CorpProjectLocalServiceUtil.fetchCorpProject(corpProjectId);

AccountEntry accountEntry = null;

if (corpProject != null) {
	accountEntry = AccountEntryLocalServiceUtil.fetchCorpProjectAccountEntry(corpProjectId);
}

long accountEntryId = BeanParamUtil.getLong(accountEntry, request, "accountEntryId");
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title='<%= (corpProject != null) ? corpProject.getName() : "new-project" %>'
/>

<portlet:actionURL name="updateCorpProject" var="updateCorpProjectURL" />

<aui:form action="<%= updateCorpProjectURL %>" method="post" name="fm">
	<aui:input name="mvcPath" type="hidden" value="/corp_project_admin/edit_corp_project.jsp" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="corpProjectId" type="hidden" value="<%= String.valueOf(corpProjectId) %>" />
	<aui:input name="accountEntryId" type="hidden" value="<%= String.valueOf(accountEntryId) %>" />

	<liferay-ui:error exception="<%= AccountEntryCorpProjectException.class %>">

		<%
		CorpProject curCorpProject = null;

		AccountEntry curAccountEntry = AccountEntryLocalServiceUtil.fetchAccountEntry(accountEntryId);

		if (curAccountEntry != null) {
			curCorpProject = CorpProjectLocalServiceUtil.fetchCorpProject(curAccountEntry.getCorpProjectId());
		}
		%>

		<c:if test="<%= curCorpProject != null %>">
			<liferay-ui:message arguments="<%= new Object[] {HtmlUtil.escape(curCorpProject.getName())} %>" key="the-support-project-is-already-linked-to-x" />
		</c:if>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= CorpProjectNameException.class %>" message="please-enter-a-valid-project-name" />
	<liferay-ui:error exception="<%= CorpProjectSalesforceProjectKeyException.class %>" message="please-enter-a-valid-salesforce-project-key" />
	<liferay-ui:error exception="<%= DuplicateCorpProjectException.class %>" message="please-enter-a-unique-dossiera-project-key" />

	<aui:model-context bean="<%= corpProject %>" model="<%= CorpProject.class %>" />

	<table class="lfr-table">
	<tr>
		<td>
			<strong><liferay-ui:message key="dossiera-project-key" /></strong>
		</td>
		<td>
			<aui:input label="" name="dossieraProjectKey" />
		</td>
	</tr>
	<tr>
		<td>
			<strong><liferay-ui:message key="salesforce-project-key" /></strong>
		</td>
		<td>
			<aui:input label="" name="salesforceProjectKey" />
		</td>
	</tr>
	<tr>
		<td>
			<strong><liferay-ui:message key="project-name" /></strong>
		</td>
		<td>
			<aui:input label="" name="name" />
		</td>
	</tr>

	<c:if test="<%= corpProject != null %>">
		<tr>
			<td>
				<strong><liferay-ui:message key="support-project" /></strong>
			</td>
			<td>
				<span id="<portlet:namespace />accountEntryName">
					<c:if test="<%= accountEntry != null %>">
						<liferay-portlet:renderURL portletName="<%= OSBPortletKeys.OSB_ADMIN %>" var="accountEntryURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
							<portlet:param name="mvcPath" value="/admin/edit_account_entry.jsp" />
							<portlet:param name="backURL" value="<%= currentURL %>" />
							<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
						</liferay-portlet:renderURL>

						<a href="<%= accountEntryURL.toString() %>" target="_blank">
							<%= HtmlUtil.escape(accountEntry.getName()) %>
						</a>
					</c:if>
				</span>

				<input onClick="var accountEntryWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/corp_project_admin/select_account_entry.jsp" /><portlet:param name="callback" value="selectAccountEntry" /></portlet:renderURL>', 'account-entry', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); corpProjectWindow.focus();" type="button" value="<liferay-ui:message key="select" />" />

				<input onClick="<portlet:namespace />removeAccountEntry();" type="button" value="<liferay-ui:message key="remove" />" />
			</td>
		</tr>
	</c:if>

	</table>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= backURL %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />removeAccountEntry() {
		document.<portlet:namespace />fm.<portlet:namespace />accountEntryId.value = 0;

		document.getElementById("<portlet:namespace />accountEntryName").innerHTML = '';
	}

	function <portlet:namespace />selectAccountEntry(accountEntryId, accountEntryName) {
		document.<portlet:namespace />fm.<portlet:namespace />accountEntryId.value = accountEntryId;

		document.getElementById("<portlet:namespace />accountEntryName").innerHTML = accountEntryName;
	}
</aui:script>