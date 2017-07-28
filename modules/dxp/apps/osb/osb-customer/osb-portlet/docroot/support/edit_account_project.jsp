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
long accountProjectId = ParamUtil.getLong(request, "accountProjectId");
long accountEntryId = ParamUtil.getLong(request, "accountEntryId");

AccountProject accountProject = null;

if (accountProjectId > 0) {
	accountProject = AccountProjectLocalServiceUtil.getAccountProject(accountProjectId);
}

String name = BeanParamUtil.getString(accountProject, request, "name");
%>

<c:if test="<%= OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.UPDATE_ACCOUNT_INFO) %>">
	<portlet:actionURL name="updateAccountProject" var="updateAccountProjectURL">
		<portlet:param name="mvcPath" value="/support/edit_account_project.jsp" />
	</portlet:actionURL>

	<aui:form action="<%= updateAccountProjectURL.toString() %>" method="post" name="fm" onSubmit="submitForm(this); return false;">
		<input name="<portlet:namespace />accountProjectId" type="hidden" value="<%= accountProjectId %>" />
		<input name="<portlet:namespace />accountEntryId" type="hidden" value="<%= accountEntryId %>" />

		<liferay-ui:error exception="<%= AccountProjectNameException.class %>" message="please-select-a-valid-project-name" />

		<div class="callout-a">
			<div class="aui-helper-clearfix callout-content">
				<div class="aui-w20 content-column customer-info-label">
					<liferay-ui:message key="project-name" />
				</div>

				<div class="aui-w80 content-column customer-info">
					<input name="<portlet:namespace />name" type="text" value="<%= HtmlUtil.escapeAttribute(name) %>" />
				</div>
			</div>

			<%
			for (int fieldId : AccountInformationConstants.ACCOUNT_PROJECT_FIELD_IDS) {
			%>

				<div class="aui-helper-clearfix callout-content">
					<div class="aui-w20 content-column customer-info-label">
						<liferay-ui:message key="<%= AccountInformationConstants.getFieldLabel(fieldId) %>" />
					</div>

					<div class="aui-w80 content-column customer-info">
						<textarea id="<portlet:namespace />field--<%= fieldId %>" maxlength="<%= OSBConstants.TEXTAREA_MAX_LENGTH %>" name="<portlet:namespace />field--<%= fieldId %>" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();"><%= (accountProject != null) ? HtmlUtil.escape(accountProject.getData(fieldId)) : "" %></textarea>
					</div>
				</div>

			<%
			}
			%>

			<div class="aui-helper-clearfix">
				<div class="callout-content fr">
					<c:if test="<%= accountProject != null %>">
						<liferay-ui:message arguments="<%= new Object[] {accountProject.getModifiedUserName(), fullDateFormatDateTime.format(accountProject.getModifiedDate())} %>" key="x-on-x" />
					</c:if>

					<input class="aui-button-input" type="submit" value="<liferay-ui:message key="save" />" />

					<input class="aui-button-input" onClick="javascript:Liferay.Util.getWindow().close();" type="button" value="<liferay-ui:message key="cancel" />" />
				</div>
			</div>
		</div>
	</aui:form>
</c:if>

<aui:script>
	<c:if test='<%= SessionMessages.contains(renderRequest, "requestProcessed") %>'>
		Liferay.Util.getWindow().close();
	</c:if>
</aui:script>