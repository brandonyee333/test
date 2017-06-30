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
AccountEntry accountEntry = (AccountEntry)request.getAttribute("add_ticket_entry.jsp-accountEntry");

int systemStatus = ParamUtil.getInteger(request, "systemStatus");
long accountEnvironmentId = ParamUtil.getLong(request, "accountEnvironmentId");
int component = ParamUtil.getInteger(request, "component");
int subcomponent = ParamUtil.getInteger(request, "subcomponent");

List<AccountEntry> accountEntries = null;

if (liferayIncOrg) {
	accountEntries = AccountEntryLocalServiceUtil.getActiveAccountEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
}
else {
	accountEntries = AccountEntryLocalServiceUtil.getUserActiveAccountEntries(user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
}
%>

<c:if test="<%= !windowState.equals(LiferayWindowState.POP_UP) %>">
	<h2 class="section-heading create-ticket-title">
		<liferay-ui:message key="create-a-ticket" />
	</h2>

	<h2 class="section-heading">
		<liferay-ui:message key="project" />:
	</h2>

	<div>
		<c:choose>
			<c:when test="<%= accountEntries.size() == 1 %>">

				<%
				accountEntry = accountEntries.get(0);

				request.setAttribute("add_ticket_entry.jsp-accountEntry", accountEntry);
				%>

				<strong><%= HtmlUtil.escape(accountEntry.getName()) %></strong>

				<input id="<portlet:namespace />accountEntryId" name="<portlet:namespace />accountEntryId" type="hidden" value="<%= accountEntry.getAccountEntryId() %>" />
			</c:when>
			<c:otherwise>
				<select id="<portlet:namespace />accountEntryId" name="<portlet:namespace />accountEntryId" onChange="<portlet:namespace />selectAccountEntry();">
					<option value=""></option>

					<%
					for (AccountEntry curAccountEntry : accountEntries) {
					%>

						<option <%= ((accountEntry != null) && (curAccountEntry.getAccountEntryId() == accountEntry.getAccountEntryId())) ? "selected" : "" %> value="<%= curAccountEntry.getAccountEntryId() %>"><%= HtmlUtil.escape(curAccountEntry.getName()) %></option>

					<%
					}
					%>

				</select>
			</c:otherwise>
		</c:choose>
	</div>
</c:if>

<%
OfferingEntry offeringEntry = null;
ProductEntry productEntry = null;

boolean addTicketPermission = false;

if (accountEntry != null) {
	addTicketPermission = OSBAccountEntryPermission.contains(permissionChecker, accountEntry.getAccountEntryId(), OSBActionKeys.ADD_TICKET);
}
%>

<c:choose>
	<c:when test="<%= (accountEntry != null) && SupportUtil.isWatcher(accountEntry.getAccountEntryId(), user.getUserId()) && !addTicketPermission %>">
		<div class="portlet-msg-info">
			<liferay-ui:message key="you-are-a-watcher-on-this-project" />
		</div>
	</c:when>
	<c:when test="<%= (accountEntry != null) && !addTicketPermission %>">
		<div class="portlet-msg-error">
			<liferay-ui:message key="you-do-not-have-permission-to-access-the-requested-resource" />
		</div>
	</c:when>
	<c:when test="<%= (accountEntry != null) && addTicketPermission %>">
		<liferay-util:include page="/support/2/add_ticket_entry/offering_entries.jsp" servletContext="<%= application %>" />

		<%
		offeringEntry = (OfferingEntry)request.getAttribute("add_ticket_entry.jsp-offeringEntry");
		productEntry = (ProductEntry)request.getAttribute("add_ticket_entry.jsp-productEntry");

		PortletPreferences supportPortletPreferences = SupportUtil.getPortletPreferences();
		%>

		<c:if test="<%= component > 0 %>">

			<%
			String componentMessage = SupportUtil.getPreferenceValue(supportPortletPreferences, locale, "componentMessage_" + productEntry.getLESADisplayName() + StringPool.UNDERLINE + component);

			String componentMessageLink = supportPortletPreferences.getValue("componentMessageLink_" + productEntry.getLESADisplayName() + StringPool.UNDERLINE + component, StringPool.BLANK);
			%>

			<div class="portlet-msg-info component-message <%= Validator.isNotNull(componentMessage) ? StringPool.BLANK : "aui-helper-hidden" %>" id="<portlet:namespace />componentMessageDisplay">
				<c:choose>
					<c:when test="<%= Validator.isNotNull(componentMessageLink) %>">
						<a href="<%= HtmlUtil.escapeAttribute(componentMessageLink) %>" target="_blank"><%= HtmlUtil.escape(componentMessage) %></a>
					</c:when>
					<c:otherwise>
						<%= HtmlUtil.escape(componentMessage) %>
					</c:otherwise>
				</c:choose>
			</div>
		</c:if>

		<c:if test="<%= (productEntry != null) && productEntry.isTicketComponentRequired() %>">
			<h2 class="section-heading">
				<liferay-ui:message key="component" />:
			</h2>

			<div>
				<select id="<portlet:namespace />component" name="<portlet:namespace />component" onChange="<portlet:namespace />selectServerComponent();">
					<option <%= (component == 0) ? "selected" : "" %> value="0"></option>

					<c:choose>
						<c:when test="<%= productEntry.isDigitalEnterprise() %>">

							<%
							for (String componentGroup : TicketEntryConstants.COMPONENT_GROUPS_DE) {
							%>

								<optgroup label="<%= LanguageUtil.get(pageContext, componentGroup) %>">

									<%
									for (int curComponent : TicketEntryConstants.getGroupComponents(componentGroup)) {
									%>

										<option <%= (curComponent == component) ? "selected" : "" %> value="<%= curComponent %>"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getComponentLabel(curComponent)) %></option>

									<%
									}
									%>

								</optgroup>

							<%
							}
							%>

						</c:when>
						<c:otherwise>

							<%
							Map<String, Integer> componentMap = new TreeMap<String, Integer>();

							for (int curComponent : TicketEntryConstants.getProductComponents(productEntry)) {
								componentMap.put(TicketEntryConstants.getComponentLabel(curComponent), curComponent);
							}

							for (Map.Entry<String, Integer> componentEntry : componentMap.entrySet()) {
								String curComponentLabel = componentEntry.getKey();
								int curComponent = componentEntry.getValue();
							%>

								<option <%= (curComponent == component) ? "selected" : "" %> value="<%= curComponent %>"><%= LanguageUtil.get(pageContext, curComponentLabel) %></option>

							<%
							}
							%>

						</c:otherwise>
					</c:choose>
				</select>

				<c:if test="<%= component > 0 %>">

					<%
					String componentLink = SupportUtil.getPreferenceValue("componentLink_" + component);
					%>

					<c:if test="<%= Validator.isNotNull(componentLink) %>">
						<span class="component-link" id="<portlet:namespace />componentLinkDisplay">
							<%= LanguageUtil.format(pageContext, "creating-x-tickets", new Object[] {componentLink, TicketEntryConstants.getComponentLabel(component)}) %>
						</span>
					</c:if>

					<c:if test="<%= component == TicketEntryConstants.COMPONENT_LIFERAY_CONNECTED_SERVICES %>">
						<div>
							<h2 class="section-heading">
								<liferay-ui:message key="subcomponent" />:
							</h2>

							<select data-field-required-status="<%= false %>" field-required-message='<%= LanguageUtil.get(pageContext, "please-select-a-valid-subcomponent") %>' id="<portlet:namespace />subcomponent" name="<portlet:namespace />subcomponent" onChange="<portlet:namespace />validateSubcomponent();">
								<option value=""></option>

								<%
								int[] subcomponents = TicketEntryConstants.getSubcomponents(component);

								for (int curSubcomponent : subcomponents) {
								%>

									<option <%= (curSubcomponent == subcomponent) ? "selected" : "" %> value="<%= curSubcomponent %>"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getSubcomponentLabel(curSubcomponent)) %></option>

								<%
								}
								%>

							</select>
						</div>
					</c:if>
				</c:if>
			</div>
		</c:if>

		<c:if test="<%= (offeringEntry != null) && ((component > 0) || !productEntry.isTicketComponentRequired()) %>">
			<h2 class="section-heading">
				<liferay-ui:message key="saved-environment-configuration" />:
			</h2>

			<c:if test="<%= productEntry.isDigitalEnterprise() && (component == TicketEntryConstants.COMPONENT_UPGRADE) %>">
				<div>
					<liferay-ui:message key="please-provide-the-environment-configuration-details-for-the-liferay-dxp-environment-that-you-are-upgrading-to" />
				</div>
			</c:if>

			<div>
				<select data-field-required-status="<%= false %>" field-required-message='<%= LanguageUtil.get(pageContext, "please-select-a-valid-environment-configuration") %>' id="<portlet:namespace />accountEnvironmentId" name="<portlet:namespace />accountEnvironmentId" onChange="<portlet:namespace />selectAccountEnvironment(this);">
					<option value="0"></option>
					<option <%= (accountEnvironmentId == -1) ? "selected" : "" %> value="-1"><liferay-ui:message key="select-new-configuration" /></option>

					<%
					List<AccountEnvironment> accountEnvironments = AccountEnvironmentLocalServiceUtil.getAccountEnvironments(accountEntry.getAccountEntryId(), productEntry.getProductEntryId());

					for (AccountEnvironment curAccountEnvironment : accountEnvironments) {
					%>

						<option <%= (curAccountEnvironment.getAccountEnvironmentId() == accountEnvironmentId) ? "selected" : "" %> value="<%= curAccountEnvironment.getAccountEnvironmentId() %>"><%= HtmlUtil.escape(curAccountEnvironment.getName()) %></option>

					<%
					}
					%>

				</select>

				<div class="<%= (accountEnvironmentId == 0) ? "aui-helper-hidden" : StringPool.BLANK %> component-detail-environment" id="<portlet:namespace />componentDetailEnvironment">
					<div class="environment-detail" id="<portlet:namespace />environmentDetail">
						<liferay-util:include page="/support/2/common/details_environment.jsp" servletContext="<%= application %>">
							<portlet:param name="edit" value="<%= Boolean.TRUE.toString() %>" />
						</liferay-util:include>
					</div>
				</div>
			</div>

			<c:if test="<%= !productEntry.isSocialOffice() %>">
				<h2 class="section-heading">
					<liferay-ui:message key="system-status" />:
				</h2>

				<div>
					<select data-field-required-status="<%= false %>" field-required-message='<%= LanguageUtil.get(pageContext, "please-select-a-valid-system-status") %>' name="<portlet:namespace />systemStatus">
						<option value=""></option>

						<%
						for (Integer curSystemStatus : TicketEntryConstants.getSystemStatuses(component)) {
						%>

							<option <%= (curSystemStatus == systemStatus) ? "selected" : "" %> value="<%= curSystemStatus %>"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getSystemStatusLabel(curSystemStatus)) %></option>

						<%
						}
						%>

					</select>
				</div>
			</c:if>
		</c:if>
	</c:when>
</c:choose>

<aui:script>
	var A = AUI();

	var accountEnvironmentId = A.one('#<portlet:namespace />accountEnvironmentId');
	var component = A.one('#<portlet:namespace />component');
	var offeringEntryId = A.one('#<portlet:namespace />offeringEntryId');
	var subcomponent = A.one('#<portlet:namespace />subcomponent');

	if (component) {
		<portlet:namespace />validateRequiredField(offeringEntryId);
	}

	if (accountEnvironmentId && (accountEnvironmentId.val() != 0)) {
		<portlet:namespace />validateRequiredField(accountEnvironmentId);
	}

	if (subcomponent) {
		<portlet:namespace />validateSubcomponent();
	}

	function <portlet:namespace />clearAccountEnvironment() {
		var envAS = A.one('#<portlet:namespace />envAS');

		envAS.set('value', '');

		var envBrowser = A.one('#<portlet:namespace />envBrowser');

		envBrowser.set('value', '');

		var envBrowserCustom = A.one('#<portlet:namespace />envBrowserCustom');

		envBrowserCustom.remove();

		var envDB = A.one('#<portlet:namespace />envDB');

		envDB.set('value', '');

		var envJVM = A.one('#<portlet:namespace />envJVM');

		envJVM.set('value', '');

		var envLFR = A.one('#<portlet:namespace />envLFR');

		envLFR.set('value', '');

		var envOS = A.one('#<portlet:namespace />envOS');

		envOS.set('value', '');

		var envOSCustom = A.one('#<portlet:namespace />envOSCustom');

		envOSCustom.remove();
	}

	function <portlet:namespace />selectAccountEntry() {
		var productEntryDisplayName = A.one('#<portlet:namespace />productEntryDisplayName');

		if (productEntryDisplayName) {
			productEntryDisplayName.set('value', '');
		}

		<portlet:namespace />selectProductEntry();
	}

	function <portlet:namespace />selectProductEntry() {
		var offeringEntryId = A.one('#<portlet:namespace />offeringEntryId');

		if (offeringEntryId) {
			offeringEntryId.set('value', 0);
		}

		var component = A.one('#<portlet:namespace />component');

		if (component) {
			component.set('value', 0);
		}

		<portlet:namespace />selectServerComponent();
	}

	function <portlet:namespace />selectServerComponent() {
		var accountEntryId = A.one('#<portlet:namespace />accountEntryId');
		var accountEnvironmentId = A.one('#<portlet:namespace />accountEnvironmentId');
		var component = A.one('#<portlet:namespace />component');
		var offeringEntryId = A.one('#<portlet:namespace />offeringEntryId');
		var oldAccountEntryId = <%= (accountEntry != null) ? accountEntry.getAccountEntryId() : 0 %>;
		var oldProductEntryDisplayName = '<%= (productEntry != null) ? HtmlUtil.escapeJS(productEntry.getLESADisplayName()) : "" %>';
		var oldOfferingEntryId = <%= (offeringEntry != null) ? offeringEntry.getOfferingEntryId() : 0 %>;
		var productEntryDisplayName = A.one('#<portlet:namespace />productEntryDisplayName');

		if (component) {
			<portlet:namespace />validateRequiredField(offeringEntryId);
		}

		if ((accountEntryId.val() == oldAccountEntryId) && productEntryDisplayName && (productEntryDisplayName.val() == oldProductEntryDisplayName) && component && offeringEntryId && ((component.val() == 0) || (offeringEntryId.val() == 0)) && !accountEnvironmentId) {
			return;
		}
		else {
			if (accountEnvironmentId && (offeringEntryId.val() != oldOfferingEntryId)) {
				accountEnvironmentId.set('value', 0);
			}

			document.<portlet:namespace />fm.encoding="application/x-www-form-urlencoded";

			submitForm(document.<portlet:namespace />fm, '<portlet:renderURL><portlet:param name="mvcPath" value="/support/2/add_ticket_entry.jsp" /></portlet:renderURL>');
		}
	}

	function <portlet:namespace />selectAccountEnvironment(accountEnvironment) {
		var accountEnvironmentId = accountEnvironment.value;
		var componentDetailEnvironment = A.one('#<portlet:namespace />componentDetailEnvironment');

		if (accountEnvironmentId == 0) {
			componentDetailEnvironment.hide();
		}
		else if (accountEnvironmentId == -1) {
			<portlet:namespace />clearAccountEnvironment();

			document.<portlet:namespace />fm.encoding="application/x-www-form-urlencoded";

			submitForm(document.<portlet:namespace />fm, '<portlet:renderURL><portlet:param name="mvcPath" value="/support/2/add_ticket_entry.jsp" /><portlet:param name="validateEnvironment" value="<%= Boolean.FALSE.toString() %>" /></portlet:renderURL>');
		}
		else {
			<portlet:namespace />clearAccountEnvironment();

			document.<portlet:namespace />fm.encoding="application/x-www-form-urlencoded";

			submitForm(document.<portlet:namespace />fm, '<portlet:renderURL><portlet:param name="mvcPath" value="/support/2/add_ticket_entry.jsp" /><portlet:param name="validateEnvironment" value="<%= Boolean.TRUE.toString() %>" /></portlet:renderURL>');
		}
	}

	function <portlet:namespace />validateSubcomponent() {
		if (subcomponent.val()) {
			<portlet:namespace />validateRequiredField(subcomponent);
		}
	}
</aui:script>