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
		<portlet:param name="mvcPath" value="/support/2/edit_account_project.jsp" />
	</portlet:actionURL>

	<aui:form action="<%= updateAccountProjectURL.toString() %>" method="post" name="fm" onSubmit="submitForm(this); return false;">
		<aui:input name="accountProjectId" type="hidden" value="<%= accountProjectId %>" />
		<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />

		<liferay-ui:error exception="<%= AccountProjectNameException.class %>" message="please-select-a-valid-project-name" />

		<div class="clearfix">
			<div class="aui-w20 content-column customer-info-label">
				<liferay-ui:message key="project-name" />
			</div>

			<div class="aui-w80 content-column customer-info">
				<aui:input name="name" type="text" value="<%= name %>" />
			</div>
		</div>

		<%
		for (int fieldId : AccountInformationConstants.ACCOUNT_PROJECT_FIELD_IDS) {
		%>

			<div class="clearfix">
				<div class="aui-w20 content-column customer-info-label">
					<liferay-ui:message key="<%= AccountInformationConstants.getFieldLabel(fieldId) %>" />
				</div>

				<div class="aui-w80 content-column customer-info">
					<aui:input label='<%= (accountProject != null) ? accountProject.getData(fieldId) : "" %>' maxlength="<%= OSBConstants.TEXTAREA_MAX_LENGTH %>" name='<%= "field--" + fieldId %>' onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" type="textarea" />
				</div>
			</div>

		<%
		}
		%>

		<div class="clearfix">
			<div class="fr">
				<c:if test="<%= accountProject != null %>">
					<liferay-ui:message arguments="<%= new Object[] {accountProject.getModifiedUserName(), fullDateFormatDateTime.format(accountProject.getModifiedDate())} %>" key="x-on-x" />
				</c:if>

				<aui:button cssClass="aui-button-input" type="submit" value="save" />

				<aui:button cssClass="aui-button-input" onClick="Liferay.Util.getWindow().close();" value="cancel" />
			</div>
		</div>
	</aui:form>
</c:if>

<c:if test='<%= SessionMessages.contains(renderRequest, "requestProcessed") %>'>
	<aui:script>
		Liferay.Util.getWindow().close();
	</aui:script>
</c:if>