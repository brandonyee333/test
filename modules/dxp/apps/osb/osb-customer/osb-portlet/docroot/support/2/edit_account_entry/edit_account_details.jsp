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

<%@ include file="/support/2/init.jsp" %>

<%
AccountEntry accountEntry = (AccountEntry)request.getAttribute("edit_account_entry_dialog.jsp-accountEntry");

int type = BeanParamUtil.getInteger(accountEntry, request, "type", AccountEntryConstants.TYPE_GROUP);
int industry = BeanParamUtil.getInteger(accountEntry, request, "industry");
int tier = BeanParamUtil.getInteger(accountEntry, request, "tier", AccountEntryConstants.TIER_REGULAR);
%>

<div class="tab-content-tab" id="<portlet:namespace />accountDetails">
	<div class="field-group">
		<label id="<portlet:namespace />nameLabel"><liferay-ui:message key="project-name" /></label>

		<div class="field-align" field-required-message="<%= LanguageUtil.get(pageContext, "please-enter-a-valid-name") %>">
			<span class="inline long-field">
				<aui:input bean="<%= accountEntry %>" data-field-required-status="<%= false %>" label="" model="<%= AccountEntry.class %>" name="name" type="text" />
			</span>
		</div>
	</div>

	<div class="field-group">
		<label id="<portlet:namespace />codeLabel"><liferay-ui:message key="code" /></label>

		<div class="field-align" field-required-message="<%= LanguageUtil.get(pageContext, "please-enter-a-valid-code") %>">
			<span class="inline long-field">
				<aui:input bean="<%= accountEntry %>" data-field-required-status="<%= false %>" label="" model="<%= AccountEntry.class %>" name="code" type="text" />
			</span>
		</div>
	</div>

	<div class="field-group">
		<label id="<portlet:namespace />typeLabel"><liferay-ui:message key="type" /></label>

		<aui:select label="" name="type">

			<%
			for (int i = 1; i <= 3; i++) {
			%>

				<aui:option label="<%= AccountEntryConstants.getTypeLabel(i) %>" selected="<%= type == i %>" value="<%= i %>" />

			<%
			}
			%>

		</aui:select>
	</div>

	<div class="field-group">
		<label id="<portlet:namespace />industryLabel"><liferay-ui:message key="industry" /></label>

		<div class="field-align" field-required-message="<%= LanguageUtil.get(pageContext, "please-select-a-valid-industry") %>">
			<aui:select data-field-required-status="<%= false %>" label="" name="industry">
				<aui:option value="" />

				<%
				List<ListType> industryTypes = ListTypeServiceUtil.getListTypes(AccountEntryConstants.LIST_TYPE_INDUSTRY);

				for (ListType industryType : industryTypes) {
				%>

					<aui:option label="<%= industryType.getName() %>" selected="<%= industryType.getListTypeId() == industry %>" value="<%= industryType.getListTypeId() %>" />

				<%
				}
				%>

			</aui:select>
		</div>
	</div>

	<div class="field-group">
		<label id="<portlet:namespace />tierLabel"><liferay-ui:message key="tier" /></label>

		<aui:select label="" name="tier">

			<%
			for (int curTier : AccountEntryConstants.TIERS) {
			%>

				<aui:option label="<%= AccountEntryConstants.getTierLabel(curTier) %>" selected="<%= tier == curTier %>" value="<%= curTier %>" />

			<%
			}
			%>

		</aui:select>
	</div>

	<div class="field-group">
		<label id="<portlet:namespace />maxCustomersLabel"><liferay-ui:message key="maximum-contacts" /></label>

		<span class="inline long-field">
			<aui:input bean="<%= accountEntry %>" label="" model="<%= AccountEntry.class %>" name="maxCustomers" />
		</span>
	</div>

	<div class="field-group">
		<label id="<portlet:namespace />instructionsLabel"><liferay-ui:message key="special-instructions" /></label>

		<aui:input bean="<%= accountEntry %>" label="" model="<%= AccountEntry.class %>" name="instructions" />
	</div>

	<div class="field-group">
		<label id="<portlet:namespace />notesLabel"><liferay-ui:message key="additional-notes" /></label>

		<aui:input bean="<%= accountEntry %>" label="" model="<%= AccountEntry.class %>" name="notes" />
	</div>
</div>