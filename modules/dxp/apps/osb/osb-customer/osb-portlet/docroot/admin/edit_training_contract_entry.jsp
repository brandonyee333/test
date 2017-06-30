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

<%@ include file="/marketplace_admin/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long contractEntryId = ParamUtil.getLong(request, "contractEntryId");

ContractEntry contractEntry = null;

if (contractEntryId > 0) {
	contractEntry = ContractEntryLocalServiceUtil.getContractEntry(contractEntryId);
}

int type = BeanParamUtil.getInteger(contractEntry, request, "type");
String content = BeanParamUtil.getString(contractEntry, request, "content");

ContractEntry latestContractEntry = null;

try {
	latestContractEntry = ContractEntryLocalServiceUtil.getLatestContractEntry(ContractEntryConstants.DEFAULT_CLASS_NAME_ID, ContractEntryConstants.DEFAULT_CLASS_PK, type);
}
catch (Exception e) {
}

String title = LanguageUtil.format(pageContext, "add-x", ContractEntryConstants.getDefaultTypeLabel(type));

int version = 1;

if (contractEntry != null) {
	version = contractEntry.getVersion();
}
else if (latestContractEntry != null) {
	version = latestContractEntry.getVersion() + 1;
}
%>

<div class="admin edit-training-contract-entry">
	<liferay-ui:header
		backURL="<%= redirect %>"
		title="<%= title %>"
	/>

	<portlet:actionURL name="addContractEntry" var="addContractEntryURL" />

	<aui:form action="<%= addContractEntryURL %>" method="post" name="fm">
		<aui:input name="mvcPath" type="hidden" value="/admin/edit_training_contract_entry.jsp" />
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="type" type="hidden" value="<%= String.valueOf(type) %>" />

		<liferay-ui:error exception="<%= ContractEntryContentException.class %>" message="please-enter-valid-content" />

		<div class="contract-edit-form">
			<strong><liferay-ui:message key="version" /></strong>

			<br />

			<%= version %>

			<aui:model-context bean="<%= contractEntry %>" model="<%= ContractEntry.class %>" />

			<liferay-util:include page="/common/localized_input.jsp" servletContext="<%= application %>">
				<liferay-util:param name="defaultLanguageId" value="<%= LocaleUtil.toLanguageId(LocaleUtil.US) %>" />
				<liferay-util:param name="disabled" value="<%= String.valueOf(contractEntry != null) %>" />
				<liferay-util:param name="label" value='<%= LanguageUtil.get(pageContext, "content") %>' />
				<liferay-util:param name="name" value="content" />
				<liferay-util:param name="required" value="<%= String.valueOf(true) %>" />
				<liferay-util:param name="value" value="<%= Validator.isNotNull(content) ? content : StringPool.BLANK %>" />
			</liferay-util:include>

			<aui:button-row>
				<c:if test="<%= contractEntry == null %>">
					<c:choose>
						<c:when test="<%= latestContractEntry != null %>">
							<aui:button type="submit" value="add-version" />
						</c:when>
						<c:otherwise>
							<aui:button type="submit" value="save" />
						</c:otherwise>
					</c:choose>
				</c:if>

				<aui:button href="<%= redirect %>" type="cancel" />
			</aui:button-row>
		</div>
	</aui:form>
</div>