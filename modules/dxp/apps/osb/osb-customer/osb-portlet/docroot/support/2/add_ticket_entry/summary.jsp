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
	<h2 class="create-ticket-title section-heading">
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

				<aui:input id="accountEntryId" name="accountEntryId" type="hidden" value="<%= accountEntry.getAccountEntryId() %>" />
			</c:when>
			<c:otherwise>
				<aui:select id="accountEntryId" name="accountEntryId" onChange="<portlet:namespace />selectAccountEntry();">
					<aui:option value=""></option>

					<%
					for (AccountEntry curAccountEntry : accountEntries) {
					%>

						<aui:option label="<%= HtmlUtil.escape(curAccountEntry.getName()) %>" selected="<%= (accountEntry != null) && (curAccountEntry.getAccountEntryId() == accountEntry.getAccountEntryId()) %>" value="<%= curAccountEntry.getAccountEntryId() %>" />

					<%
					}
					%>

				</aui:select>
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

			<div class="portlet-msg-info component-message <%= Validator.isNotNull(componentMessage) ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />componentMessageDisplay">
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
				<aui:select id="component" name="component" onChange="<portlet:namespace />selectServerComponent();">
					<aui:option selected="<%= component == 0 %> value="0" />

					<c:choose>
						<c:when test="<%= productEntry.isDigitalEnterprise() %>">

							<%
							for (String componentGroup : TicketEntryConstants.COMPONENT_GROUPS_DE) {
							%>

								<optgroup label="<%= LanguageUtil.get(pageContext, componentGroup) %>">

									<%
									for (int curComponent : TicketEntryConstants.getGroupComponents(componentGroup)) {
									%>

										<aui:option label="<%= TicketEntryConstants.getComponentLabel(curComponent) %>" selected="<%= (curComponent == component) %>" value="<%= curComponent %>"> />

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

								<aui:option label="<%= curComponentLabel %>" selected="<%= curComponent == component) %>" value="<%= curComponent %>"> />

							<%
							}
							%>

						</c:otherwise>
					</c:choose>
				</aui:select>

				<c:if test="<%= component > 0 %>">

					<%
					String componentLink = SupportUtil.getPreferenceValue("componentLink_" + component);
					%>

					<c:if test="<%= Validator.isNotNull(componentLink) %>">
						<span class="component-link" id="<portlet:namespace />componentLinkDisplay">
							<%= LanguageUtil.format(pageContext, "creating-x-tickets", new Object[] {"<a href=\"" + componentLink + "\" target=\"_blank\">", TicketEntryConstants.getComponentLabel(component), "</a>"}) %>
						</span>
					</c:if>

					<c:if test="<%= component == TicketEntryConstants.COMPONENT_LIFERAY_CONNECTED_SERVICES %>">
						<div>
							<h2 class="section-heading">
								<liferay-ui:message key="subcomponent" />:
							</h2>

							<aui:select data-field-required-status="<%= false %>" field-required-message="<%= LanguageUtil.get(pageContext, "please-select-a-valid-subcomponent") %>" id="subcomponent" name="subcomponent" onChange="<portlet:namespace />validateSubcomponent();">
								<aui:option value="" />

								<%
								int[] subcomponents = TicketEntryConstants.getSubcomponents(component);

								for (int curSubcomponent : subcomponents) {
								%>

									<aui:option label="<%= TicketEntryConstants.getSubcomponentLabel(curSubcomponent) %>" selected="<%= curSubcomponent == subcomponent %>" value="<%= curSubcomponent %>" />

								<%
								}
								%>

							</aui:select>
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
				<aui:select data-field-required-status="<%= false %>" field-required-message="<%= LanguageUtil.get(pageContext, "please-select-a-valid-environment-configuration") %>" id="accountEnvironmentId" name="accountEnvironmentId" onChange="<portlet:namespace />selectAccountEnvironment(this);">
					<aui:option value="0" />
					<aui:option label="select-new-configuration" selected="<%= accountEnvironmentId == -1 %>" value="-1" />

					<%
					List<AccountEnvironment> accountEnvironments = AccountEnvironmentLocalServiceUtil.getAccountEnvironments(accountEntry.getAccountEntryId(), productEntry.getProductEntryId());

					for (AccountEnvironment curAccountEnvironment : accountEnvironments) {
					%>

						<aui:option label="<%= HtmlUtil.escape(curAccountEnvironment.getName()) %>" selected="<%= curAccountEnvironment.getAccountEnvironmentId() == accountEnvironmentId) %>" value="<%= curAccountEnvironment.getAccountEnvironmentId() %>" />

					<%
					}
					%>

				</select>

				<div class="<%= (accountEnvironmentId == 0) ? "hide" : StringPool.BLANK %> component-detail-environment" id="<portlet:namespace />componentDetailEnvironment">
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
					<aui:select data-field-required-status="<%= false %>" field-required-message="<%= LanguageUtil.get(pageContext, "please-select-a-valid-system-status") %>" name="systemStatus">
						<aui:option value="" />

						<%
						for (Integer curSystemStatus : TicketEntryConstants.getSystemStatuses(component)) {
						%>

							<aui:option label="<%= TicketEntryConstants.getSystemStatusLabel(curSystemStatus) %>" selected="<%= curSystemStatus == systemStatus %>" value="<%= curSystemStatus %>" />

						<%
						}
						%>

					</aui:select>
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
		var ids = [
			'envAS',
			'envBrowser',
			'envDB',
			'envJVM',
			'envLFR',
			'envOS'
		];

		ids.forEach(
			function(id) {
				var node = A.one('#<portlet:namespace />' + id);

				if (node) {
					node.val('');
				}
			}
		);

		var envBrowserCustom = A.one('#<portlet:namespace />envBrowserCustom');

		if (envBrowserCustom) {
			envBrowserCustom.remove();
		}

		var envOSCustom = A.one('#<portlet:namespace />envOSCustom');

		if(envOSCustom) {
			envOSCustom.remove();
		}
	}

	function <portlet:namespace />selectAccountEntry() {
		var productEntryDisplayName = A.one('#<portlet:namespace />productEntryDisplayName');

		if (productEntryDisplayName) {
			productEntryDisplayName.val('');
		}

		<portlet:namespace />selectProductEntry();
	}

	function <portlet:namespace />selectProductEntry() {
		var offeringEntryId = A.one('#<portlet:namespace />offeringEntryId');

		if (offeringEntryId) {
			offeringEntryId.val(0);
		}

		var component = A.one('#<portlet:namespace />component');

		if (component) {
			component.val(0);
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

		if (!((accountEntryId.val() == oldAccountEntryId) && productEntryDisplayName && (productEntryDisplayName.val() == oldProductEntryDisplayName) && component && offeringEntryId && ((component.val() == 0) || (offeringEntryId.val() == 0)) && !accountEnvironmentId)) {
			if (accountEnvironmentId && (offeringEntryId.val() != oldOfferingEntryId)) {
				accountEnvironmentId.val(0);
			}

			document.<portlet:namespace />fm.encoding='application/x-www-form-urlencoded';

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