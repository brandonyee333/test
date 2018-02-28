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
String redirect = ParamUtil.getString(request, "redirect");

TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

Map<Long, String> ticketInformationFieldsMap = ticketEntry.getTicketInformationFieldsMap();

long accountEntryId = BeanParamUtil.getLong(ticketEntry, request, "accountEntryId");
int component = BeanParamUtil.getInteger(ticketEntry, request, "component");
int envLFR = ParamUtil.getInteger(request, "envLFR", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_LFR)));
long offeringEntryId = BeanParamUtil.getLong(ticketEntry, request, "offeringEntryId");

int toEnvLFR = GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_UPGRADE_TO_ENV_LFR));

AccountEntry accountEntry = null;

if (accountEntryId > 0) {
	accountEntry = AccountEntryServiceUtil.getAccountEntry(accountEntryId);
}
else {
	accountEntry = ticketEntry.getAccountEntry();
}

OfferingEntry offeringEntry = null;

if (offeringEntryId > 0) {
	offeringEntry = OfferingEntryLocalServiceUtil.getOfferingEntry(offeringEntryId);
}
else {
	offeringEntry = ticketEntry.getOfferingEntry();
}

ProductEntry productEntry = offeringEntry.getProductEntry();

boolean hasUpdateAdmin = OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_ADMIN);
boolean hasUpdateAdvanced = hasUpdateAdmin || OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_ADVANCED);
%>

<portlet:actionURL name="updateTicketEntry" var="updateTicketEntryURL">
	<portlet:param name="mvcPath" value="/support/2/edit_ticket_entry.jsp" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</portlet:actionURL>

<aui:form action="<%= updateTicketEntryURL %>" enctype="multipart/form-data" method="post" name="fm3">
	<aui:input name="accountEntryId" type="hidden" value="<%= accountEntry.getAccountEntryId() %>" />
	<aui:input name="modified" type="hidden" value="false" />
	<aui:input name="offeringEntryId" type="hidden" value="<%= offeringEntry.getOfferingEntryId() %>" />
	<aui:input name="reportedByUserId" type="hidden" value="<%= ticketEntry.getUserId() %>" />
	<aui:input name="ticketEntryId" type="hidden" value="<%= ticketEntry.getTicketEntryId() %>" />
	<aui:input name="weight" type="hidden" value="<%= ticketEntry.getWeight() %>" />

	<div class="tab-view-dialog">
		<div class="details" id="<portlet:namespace />editTicketDetails">
			<div class="tabs" id="<portlet:namespace />editTicketTabsDetails">
				<div>
					<span class="first selected" id="<portlet:namespace />ticketDetailsHeader" onClick="<portlet:namespace />revealDialogTab('ticketDetails');">
						<liferay-ui:message key="ticket-details" />
					</span>
					<span id="<portlet:namespace />environmentDetailsHeader" onClick="<portlet:namespace />revealDialogTab('environmentDetails');">
						<liferay-ui:message key="environment-details" />
					</span>
					<span class="component-tab hide" id="<portlet:namespace />clusteringDetailsHeader" onClick="<portlet:namespace />revealDialogTab('clusteringDetails');">
						<liferay-ui:message key="clustering-details" />
					</span>
					<span class="component-tab hide" id="<portlet:namespace />activationKeyDetailsHeader" onClick="<portlet:namespace />revealDialogTab('activationKeyDetails');">
						<liferay-ui:message key="activation-key-details" />
					</span>
					<span class="component-tab hide" id="<portlet:namespace />upgradeDetailsHeader" onClick="<portlet:namespace />revealDialogTab('upgradeDetails');">
						<liferay-ui:message key="upgrade-details" />
					</span>
				</div>
			</div>

			<div class="tab-content" id="<portlet:namespace />editTicketTabContent">

				<%
				request.setAttribute("edit_ticket_entry_dialog.jsp-accountEntry", accountEntry);
				request.setAttribute("edit_ticket_entry_dialog.jsp-component", component);
				request.setAttribute("edit_ticket_entry_dialog.jsp-envLFR", envLFR);
				request.setAttribute("edit_ticket_entry_dialog.jsp-hasUpdateAdmin", hasUpdateAdmin);
				request.setAttribute("edit_ticket_entry_dialog.jsp-hasUpdateAdvanced", hasUpdateAdvanced);
				request.setAttribute("edit_ticket_entry_dialog.jsp-offeringEntryId", offeringEntryId);
				request.setAttribute("edit_ticket_entry_dialog.jsp-productEntry", productEntry);
				%>

				<liferay-util:include page="/support/2/edit_ticket_entry/edit_ticket_details.jsp" servletContext="<%= application %>" />

				<div id="<portlet:namespace />editEnvironmentDetails">
					<liferay-util:include page="/support/2/edit_ticket_entry/edit_environment_details.jsp" servletContext="<%= application %>" />
				</div>

				<liferay-util:include page="/support/2/edit_ticket_entry/edit_component_details.jsp" servletContext="<%= application %>" />
			</div>
		</div>

		<aui:button-row>
			<aui:button onClick='<%= renderResponse.getNamespace() + "closeEditTicketDialog();" %>' value="cancel" />

			<aui:button cssClass="pull-right" type="submit" value="save" />
		</aui:button-row>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />closeEditTicketDialog() {
		var modified = document.getElementById('<portlet:namespace />modified');

		if (modified.value == 'true') {
			var cancelEdit = confirm('<%= UnicodeLanguageUtil.get(request, "you-have-unsaved-changes-on-this-ticket.-are-you-sure-you-want-to-cancel-editing") %>');

			if (!cancelEdit) {
				return;
			}
		}

		Liferay.fire(
			'closeWindow',
			{
				id: '<portlet:namespace />editTicketPopup'
			}
		);
	}

	Liferay.provide(
		window,
		'<portlet:namespace />loadEnvironmentDetailsTab',
		function(component) {
			var A = AUI();

			<portlet:renderURL copyCurrentRenderParameters="<%= true %>" var="tabURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
				<portlet:param name="mvcPath" value="/support/2/edit_ticket_entry/edit_environment_details.jsp" />
				<portlet:param name="envLFR" value="<%= ((component != TicketEntryConstants.COMPONENT_UPGRADE) && productEntry.isDigitalEnterprise()) ? String.valueOf(0) : String.valueOf(envLFR) %>" />
				<portlet:param name="toEnvLFR" value="<%= String.valueOf(toEnvLFR) %>" />
				<portlet:param name="hasUpdateAdmin" value="<%= String.valueOf(hasUpdateAdmin) %>" />
				<portlet:param name="hasUpdateAdvanced" value="<%= String.valueOf(hasUpdateAdvanced) %>" />
				<portlet:param name="offeringEntryId" value="<%= String.valueOf(offeringEntryId) %>" />
				<portlet:param name="productEntryId" value="<%= String.valueOf(productEntry.getProductEntryId()) %>" />
			</portlet:renderURL>

			var tabURL = '<%= tabURL %>&<portlet:namespace />component=' + component;

			var tabContentDiv = A.one('#<portlet:namespace />editEnvironmentDetails');

			A.io.request(
				tabURL,
				{
					on: {
						start: function() {
							tabContentDiv.html('<img src="<%= themeDisplay.getPathThemeImages() + "/aui/loading_indicator.gif" %>" style="display: block; margin: auto;" />');
						},
						success: function() {
							var response = this.get('responseData');

							tabContentDiv.html(response);

							<portlet:namespace />loadEnvironmentDetails(component, '<%= toEnvLFR %>');
						}
					}
				}
			);
		},
		['aui-io']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />revealDialogTab',
		function(id) {
			var A = AUI();

			var tab = A.one('.tab-view-dialog .details .tabs #<portlet:namespace />' + id + 'Header');

			if (tab) {
				A.all('.tab-view-dialog .details .tab-content-tab').hide();

				var tabContent = A.one('.tab-view-dialog .details .tab-content #<portlet:namespace />' + id);

				tabContent.show();

				A.all('.tab-view-dialog .details .tabs span').removeClass('selected');

				tab.addClass('selected');
			}
			else {
				<portlet:namespace />reveal('details');
			}

			window.scroll(0, 0);
		},
		['aui-base']
	);
</aui:script>

<aui:script use="aui-base,event-key">
	A.all('.component-tab').hide();

	var requiredStatus = 'true';

	<c:choose>
		<c:when test="<%= component == TicketEntryConstants.COMPONENT_CLUSTERING %>">
			var clusteringDetails = A.one('.component-tab#<portlet:namespace />clusteringDetailsHeader');

			if (clusteringDetails) {
				clusteringDetails.show();
			}

			requiredStatus = 'false';
		</c:when>
		<c:when test="<%= component == TicketEntryConstants.COMPONENT_LICENSE %>">
			var activationKeyDetails = A.one('.component-tab#<portlet:namespace />activationKeyDetailsHeader');

			if (activationKeyDetails) {
				activationKeyDetails.show();
			}
		</c:when>
		<c:when test="<%= component == TicketEntryConstants.COMPONENT_UPGRADE %>">
			var upgradeDetails = A.one('.component-tab#<portlet:namespace />upgradeDetailsHeader');

			if (upgradeDetails) {
				upgradeDetails.show();
			}
		</c:when>
	</c:choose>

	var serverTypeNode = A.one('#<portlet:namespace />serverCommunicationType');

	if (serverTypeNode) {
		serverTypeNode.attr('data-field-required-status', requiredStatus);
	}

	var onChange = function(event) {
		var name = event.currentTarget.getAttribute('name');

		var label = 'label#' + name + 'Label';

		if (name.indexOf('dueDate') > -1) {
			label = 'label#<portlet:namespace />dueDateLabel';
		}
		else if (name.indexOf('toEnvLFR') > -1) {
			label = '#<portlet:namespace />envLFRLabel';
		}

		label = A.one(label);

		if (label) {
			var labelAncestor = label.ancestor('.tab-content-tab');

			if (labelAncestor) {
				var tabId = labelAncestor.getAttribute('id');

				var tab = A.one('span#' + tabId);

				label.addClass('field-modified');

				var modified = '(Modified)'.bold();

				if (tab && (tab.html().indexOf('Modified') == -1)) {
					tab.append(modified);

					tab.addClass('field-modified');
				}

				document.getElementById('<portlet:namespace />modified').value = 'true';

				if (this.hasAttribute('data-field-required-status')) {
					<portlet:namespace />validateRequiredField(this);
				}
			}
		}
	};

	var editTicketTabContent = A.one('#<portlet:namespace />editTicketTabContent');

	if (editTicketTabContent) {
		editTicketTabContent.delegate('change', onChange, 'input[type=checkbox], select');
		editTicketTabContent.delegate('keyup', onChange, 'input[type=text], textarea');
	}

	A.getDoc().on('key', <portlet:namespace />closeEditTicketDialog, 'esc');
</aui:script>