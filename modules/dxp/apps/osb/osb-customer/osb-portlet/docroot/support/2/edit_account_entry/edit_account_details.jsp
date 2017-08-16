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
		<div class="field-align" field-required-message="<%= LanguageUtil.get(request, "please-enter-a-valid-name") %>">
			<span class="inline long-field">
				<aui:input bean="<%= accountEntry %>" data-field-required-status="<%= false %>" label="project-name" model="<%= AccountEntry.class %>" name="name" type="text" />
			</span>
		</div>
	</div>

	<div class="field-group">
		<div class="field-align" field-required-message="<%= LanguageUtil.get(request, "please-enter-a-valid-code") %>">
			<span class="inline long-field">
				<aui:input bean="<%= accountEntry %>" data-field-required-status="<%= false %>" label="code" model="<%= AccountEntry.class %>" name="code" type="text" />
			</span>
		</div>
	</div>

	<div class="field-group">
		<aui:select label="type" name="type">

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
		<div class="field-align" field-required-message="<%= LanguageUtil.get(request, "please-select-a-valid-industry") %>">
			<aui:select data-field-required-status="<%= false %>" label="industry" name="industry">
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
		<aui:select label="tier" name="tier">

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
		<span class="inline long-field">
			<aui:input bean="<%= accountEntry %>" label="maximum-contacts" model="<%= AccountEntry.class %>" name="maxCustomers" />
		</span>
	</div>

	<div class="field-group">
		<aui:input bean="<%= accountEntry %>" label="special-instructions" model="<%= AccountEntry.class %>" name="instructions" />
	</div>

	<div class="field-group">
		<aui:input bean="<%= accountEntry %>" label="additional-notes" model="<%= AccountEntry.class %>" name="notes" />
	</div>
</div>