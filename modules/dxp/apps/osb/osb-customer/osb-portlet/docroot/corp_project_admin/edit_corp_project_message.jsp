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
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

long corpProjectMessageId = ParamUtil.getLong(request, "corpProjectMessageId");

CorpProjectMessage corpProjectMessage = CorpProjectMessageLocalServiceUtil.fetchCorpProjectMessage(corpProjectMessageId);

long corpProjectId = BeanParamUtil.getLong(corpProjectMessage, request, "corpProjectId");
int type = BeanParamUtil.getInteger(corpProjectMessage, request, "type");
int severityLevel = BeanParamUtil.getInteger(corpProjectMessage, request, "severityLevel");
String title = BeanParamUtil.getString(corpProjectMessage, request, "title");
String content = BeanParamUtil.getString(corpProjectMessage, request, "content");
boolean displayCP = BeanParamUtil.getBoolean(corpProjectMessage, request, "displayCP");
boolean displayLCS = BeanParamUtil.getBoolean(corpProjectMessage, request, "displayLCS");
boolean displayLESA = BeanParamUtil.getBoolean(corpProjectMessage, request, "displayLESA");
%>

<liferay-ui:tabs
	backURL="<%= backURL %>"
	names="corp-project-message"
/>

<portlet:actionURL name="updateCorpProjectMessage" var="updateCorpProjectMessageURL">
	<portlet:param name="mvcPath" value="/corp_project_admin/edit_corp_project_message.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateCorpProjectMessageURL %>" method="post" name="fm">
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="corpProjectId" type="hidden" value="<%= corpProjectId %>" />
	<aui:input name="corpProjectMessageId" type="hidden" value="<%= corpProjectMessageId %>" />

	<liferay-ui:error exception="<%= CorpProjectMessageContentException.class %>" message="please-enter-a-valid-message" />
	<liferay-ui:error exception="<%= CorpProjectMessageSeverityLevelException.class %>" message="please-select-a-valid-severity" />
	<liferay-ui:error exception="<%= CorpProjectMessageTitleException.class %>" message="please-enter-a-valid-title" />
	<liferay-ui:error exception="<%= CorpProjectMessageTypeException.class %>" message="please-select-a-valid-type" />
	<liferay-ui:error exception="<%= CorpProjectSalesforceProjectKeyException.class %>" message="the-corp-project-must-have-a-valid-salesforce-project-key" />
	<liferay-ui:error exception="<%= NoSuchCorpProjectException.class %>" message="the-corp-project-does-not-exist" />

	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="title" />
		</td>
		<td>
			<aui:input label="" model="<%= CorpProjectMessage.class %>" name="title" value="<%= title %>" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="message" />
		</td>
		<td>
			<textarea class="lfr-textarea" name="<portlet:namespace />content"><%= HtmlUtil.escape(content) %></textarea>
		</td>
	</tr>
	<tr>
		<td>
		</td>
		<td>
			<div class="template-select">
				<liferay-ui:message key="use-template" />

				<select onChange="<portlet:namespace />updateContent(this.value);">
					<option value="" />
					<option value="<liferay-ui:message key="warning-your-subscription-status-is-on-hold.-to-ensure-continued-access-please-contact-your-sales-representative-to-resolve-the-subscription-access-issues" />"><liferay-ui:message key="subscription-on-hold-template" /></option>
				</select>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="type" />
		</td>
		<td>
			<select name="<portlet:namespace />type">
				<option value="<%= CorpProjectMessageConstants.TYPE_SUBSCRIPTION_STATUS %>"><%= LanguageUtil.get(pageContext, CorpProjectMessageConstants.getTypeLabel(CorpProjectMessageConstants.TYPE_SUBSCRIPTION_STATUS)) %></option>
			</select>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="severity" />
		</td>
		<td>
			<select name="<portlet:namespace />severityLevel">
				<option value="" />

				<%
				for (int i = 1; i < 4; i++) {
				%>

					<option <%= (severityLevel == i) ? "selected" : "" %> value="<%= i %>"><%= LanguageUtil.get(pageContext, CorpProjectMessageConstants.getSeverityLevelLabel(i)) %></option>

				<%
				}
				%>

			</select>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="display-cp" />
		</td>
		<td>
			<input <%= displayCP ? "checked" : "" %> name="<portlet:namespace />displayCP" type="checkbox" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="display-lcs" />
		</td>
		<td>
			<input <%= displayLCS ? "checked" : "" %> name="<portlet:namespace />displayLCS" type="checkbox" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="display-lesa" />
		</td>
		<td>
			<input <%= displayLESA ? "checked" : "" %> name="<portlet:namespace />displayLESA" type="checkbox" />
		</td>
	</tr>
	</table>

	<br />

	<aui:button type="submit" />
</aui:form>

<aui:script>
	function <portlet:namespace />updateContent(template) {
		if (template != '') {
			var content = document.<portlet:namespace />fm.<portlet:namespace />content.value;

			if (content != '') {
				if (!confirm('<liferay-ui:message key="this-will-overwrite-your-content.-are-you-sure-you-want-to-continue" />')) {
					return;
				}
			}

			document.<portlet:namespace />fm.<portlet:namespace />content.value = template;
		}
	}
</aui:script>