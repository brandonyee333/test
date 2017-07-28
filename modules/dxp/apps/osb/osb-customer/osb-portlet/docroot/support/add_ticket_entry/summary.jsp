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

<%@ include file="/support/init.jsp" %>

<%
String step = ParamUtil.getString(request, "step");

int component = ParamUtil.getInteger(request, "component");
long accountEntryId = ParamUtil.getLong(request, "accountEntryId");
long offeringEntryId = ParamUtil.getLong(request, "offeringEntryId");
%>

<liferay-ui:error exception="<%= MaximumTicketEntryException.class %>" message="the-number-of-support-tickets-has-exceeded-the-maximum-number-of-allowed-support-tickets" />
<liferay-ui:error exception="<%= NoSuchOfferingEntryException.class %>" message="please-select-a-valid-support" />
<liferay-ui:error exception="<%= OfferingEntrySupportExpiredException.class %>" message="you-cannot-create-a-ticket-because-the-selected-support-has-expired" />

<c:if test="<%= !windowState.equals(LiferayWindowState.POP_UP) %>">
	<h2 class="section-heading">
		<liferay-ui:message key="project" />
	</h2>

	<%
	List<AccountEntry> accountEntries = null;

	if (liferayIncOrg) {
		accountEntries = AccountEntryLocalServiceUtil.getActiveAccountEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}
	else {
		accountEntries = AccountEntryLocalServiceUtil.getUserActiveAccountEntries(user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}
	%>

	<div class="callout-a">
		<div class="callout-content">
			<c:choose>
				<c:when test="<%= accountEntries.size() == 1 %>">

					<%
					AccountEntry accountEntry = accountEntries.get(0);

					accountEntryId = accountEntry.getAccountEntryId();
					%>

					<strong><%= HtmlUtil.escape(accountEntry.getName()) %></strong>

					<input name="<portlet:namespace />accountEntryId" type="hidden" value="<%= accountEntryId %>" />
				</c:when>
				<c:otherwise>
					<select name="<portlet:namespace />accountEntryId" onChange="<portlet:namespace />selectAccountEntry();">
						<option value=""></option>

						<%
						for (AccountEntry curAccountEntry : accountEntries) {
							if (curAccountEntry.getType() == AccountEntryConstants.TYPE_TRIAL) {
								continue;
							}
						%>

							<option <%= (curAccountEntry.getAccountEntryId() == accountEntryId) ? "selected" : "" %> value="<%= curAccountEntry.getAccountEntryId() %>"><%= HtmlUtil.escape(curAccountEntry.getName()) %></option>

						<%
						}
						%>

					</select>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</c:if>

<c:if test="<%= accountEntryId > 0 %>">
	<liferay-util:include page="/support/add_ticket_entry/offering_entries.jsp" servletContext="<%= application %>">
		<liferay-util:param name="accountEntryId" value="<%= String.valueOf(accountEntryId) %>" />
	</liferay-util:include>
</c:if>

<c:if test="<%= offeringEntryId > 0 %>">

	<%
	OfferingEntry offeringEntry = OfferingEntryLocalServiceUtil.getOfferingEntry(offeringEntryId);

	ProductEntry productEntry = offeringEntry.getProductEntry();
	%>

	<c:if test="<%= productEntry.isTicketComponentRequired() %>">
		<h2 class="section-heading">
			<liferay-ui:message key="select-a-component" />
		</h2>

		<div class="callout-a">
			<div class="callout-content">
				<select name="<portlet:namespace />component" onChange="<portlet:namespace />updateComponentMessage(this.value);">
					<option value=""></option>

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
							for (int curComponent : TicketEntryConstants.getProductComponents(productEntry)) {
							%>

								<option <%= (curComponent == component) ? "selected" : "" %> value="<%= curComponent %>"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getComponentLabel(curComponent)) %></option>

							<%
							}
							%>

						</c:otherwise>
					</c:choose>
				</select>

				<span class="aui-helper-hidden component-link" id="<portlet:namespace />componentLinkDisplay"></span>

				<div class="aui-helper-hidden component-message portlet-msg-info" id="<portlet:namespace />componentMessageDisplay"></div>
			</div>
		</div>

		<c:if test='<%= step.equals("summary") && (accountEntryId != 0) && (offeringEntryId != 0) %>'>
			<input class="btn <%= (component == 0) ? "aui-helper-hidden" : StringPool.BLANK %>" id="<portlet:namespace />submitButton" onClick="<portlet:namespace />submit();" type="button" value="<liferay-ui:message key="continue" />" />
		</c:if>
	</c:if>
</c:if>

<aui:script>
	function <portlet:namespace />selectAccountEntry() {
		document.<portlet:namespace />fm.<portlet:namespace />offeringEntryId.value = 0;

		<portlet:namespace />submit();
	}

	function <portlet:namespace />selectAccountEnvironment(offeringEntryId, ticketComponentRequired) {
		document.<portlet:namespace />fm.<portlet:namespace />offeringEntryId.value = offeringEntryId;

		var supportRegionId = document.getElementById("<portlet:namespace />supportRegion_" + offeringEntryId).value;

		document.<portlet:namespace />fm.<portlet:namespace />supportRegionId.value = supportRegionId;

		if (!ticketComponentRequired) {
			submitForm(document.<portlet:namespace />fm, '<portlet:renderURL><portlet:param name="mvcPath" value="/support/add_ticket_entry.jsp" /><portlet:param name="step" value="details" /></portlet:renderURL>');
		}
		else {
			submitForm(document.<portlet:namespace />fm, '<portlet:renderURL><portlet:param name="mvcPath" value="/support/add_ticket_entry.jsp" /><portlet:param name="step" value="summary" /></portlet:renderURL>');
		}
	}

	function <portlet:namespace />submit() {
		submitForm(document.<portlet:namespace />fm, '<portlet:renderURL><portlet:param name="mvcPath" value="/support/add_ticket_entry.jsp" /><portlet:param name="step" value="details" /></portlet:renderURL>');
	}

	function <portlet:namespace />toggleSelect(offeringEntryId, accountEnvironmentId, ticketComponentRequired) {
		var A = AUI();

		document.<portlet:namespace />fm.<portlet:namespace />accountEnvironmentId.value = accountEnvironmentId;

		A.all("#<portlet:namespace />offeringEntriesContainer .create-ticket-button").each(
			function(item, index, collection) {
				item.set('disabled', false);

				item.val('<%= LanguageUtil.get(request, "choose") %>');

				item.attr('onClick', '');
				item.detachAll();

				item.on(
					'click',
					function(e) {
						e.preventDefault();

						location.href = 'javascript:<portlet:namespace />toggleAccountEnvironments(' + item.get('id') + ');';
					}
				);
			}
		);

		var offeringEntry = A.one('#<portlet:namespace />offeringEntry_' + offeringEntryId);

		offeringEntry.addClass('collapsed');

		var createTicketButton = offeringEntry.one('.create-ticket-button');

		createTicketButton.val('<%= LanguageUtil.get(request, "confirm") %>');

		createTicketButton.attr('onClick', '');
		createTicketButton.detachAll();

		createTicketButton.on(
			'click',
			function(e) {
				e.preventDefault();

				location.href = 'javascript:<portlet:namespace />selectAccountEnvironment(' + offeringEntryId + ', ' + ticketComponentRequired + ');';
			}
		);

		var accountEnvironments = A.one('#<portlet:namespace />accountEnvironments_' + offeringEntryId);

		accountEnvironments.addClass('collapsed');
	}

	Liferay.provide(
		window,
		'<portlet:namespace />updateComponentMessage',
		function(component) {
			var A = AUI();

			var componentLinkDisplay = A.one('#<portlet:namespace />componentLinkDisplay');
			var componentMessageDisplay = A.one('#<portlet:namespace />componentMessageDisplay');
			var submitButton = A.one('#<portlet:namespace />submitButton');

			if (component == 0) {
				componentLinkDisplay.hide();
				componentMessageDisplay.hide();
				submitButton.hide();

				return;
			}
			else {
				submitButton.show();
			}

			A.io.request(
				'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="ticketComponentMessage" />',
				{
					data: {
						<portlet:namespace />component: component
					},
					dataType: 'json',
					method: 'post',
					on: {
						success: function(event, id, obj) {
							var response = this.get('responseData');

							if (response && response.componentLink) {
								componentLinkDisplay.html(response.componentLink);

								componentLinkDisplay.show();
							}
							else {
								componentLinkDisplay.hide();
							}

							if (response && response.componentMessage) {
								if (response.componentMessageLink) {
									componentMessageDisplay.html('<a href="' + response.componentMessageLink + '" target="_blank">' + response.componentMessage + '</a>');
								}
								else {
									componentMessageDisplay.html(response.componentMessage);
								}

								componentMessageDisplay.show();
							}
							else {
								componentMessageDisplay.hide();
							}
						}
					}
				}
			);
		},
		['aui-io']
	);
</aui:script>