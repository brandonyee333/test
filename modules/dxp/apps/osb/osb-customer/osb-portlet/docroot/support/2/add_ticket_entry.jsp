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
long accountEntryId = ParamUtil.getLong(request, "accountEntryId");
long offeringEntryId = ParamUtil.getLong(request, "offeringEntryId");
long productEntryId = ParamUtil.getLong(request, "productEntryId");
int component = ParamUtil.getInteger(request, "component");

if (accountEntryId > 0) {
	AccountEntry accountEntry = AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

	request.setAttribute("add_ticket_entry.jsp-accountEntry", accountEntry);
}

if (offeringEntryId > 0) {
	OfferingEntry offeringEntry = OfferingEntryLocalServiceUtil.getOfferingEntry(offeringEntryId);

	request.setAttribute("add_ticket_entry.jsp-offeringEntry", offeringEntry);

	request.setAttribute("add_ticket_entry.jsp-productEntry", offeringEntry.getProductEntry());
}
%>

<div class="aui-w50 create-ticket" id="<portlet:namespace />createTicket">
	<liferay-ui:error exception="<%= MaximumTicketEntryException.class %>" message="the-number-of-support-tickets-has-exceeded-the-maximum-number-of-allowed-support-tickets" />
	<liferay-ui:error exception="<%= NoSuchOfferingEntryException.class %>" message="please-select-a-valid-support" />
	<liferay-ui:error exception="<%= OfferingEntrySupportExpiredException.class %>" message="you-cannot-create-a-ticket-because-the-selected-support-has-expired" />
	<liferay-ui:error exception="<%= TicketEntryDescriptionException.class %>" message="please-enter-a-description-of-the-issue" />
	<liferay-ui:error exception="<%= TicketEntrySubjectException.class %>" message="please-enter-a-summary-of-the-issue" />
	<liferay-ui:error exception="<%= TicketEntrySystemStatusException.class %>" message="please-select-a-valid-system-status" />

	<liferay-ui:error exception="<%= TicketInformationException.class %>">

		<%
		TicketInformationException tie = (TicketInformationException)errorException;
		%>

		<liferay-ui:message arguments="<%= tie.getMessage() %>" key="invalid-value-provided-for-x" />
	</liferay-ui:error>

	<liferay-ui:error exception="<%= RequiredFieldException.class %>">

		<%
		RequiredFieldException rfe = (RequiredFieldException)errorException;
		%>

		<liferay-ui:message arguments="<%= LanguageUtil.get(request, rfe.getLabelId()) %>" key="please-upload-a-x-file" />
	</liferay-ui:error>

	<portlet:actionURL name="updateTicketEntry" var="updateTicketEntryURL">
		<portlet:param name="mvcPath" value="/support/2/add_ticket_entry.jsp" />
	</portlet:actionURL>

	<aui:form action="<%= updateTicketEntryURL %>" method="post" name="fm">
		<liferay-util:include page="/support/2/add_ticket_entry/summary.jsp" servletContext="<%= application %>" />

		<%
		OfferingEntry offeringEntry = (OfferingEntry)request.getAttribute("add_ticket_entry.jsp-offeringEntry");
		ProductEntry productEntry = (ProductEntry)request.getAttribute("add_ticket_entry.jsp-productEntry");
		%>

		<c:if test="<%= (offeringEntry != null) && ((component != 0) || !productEntry.isTicketComponentRequired()) %>">
			<liferay-util:include page="/support/2/add_ticket_entry/details.jsp" servletContext="<%= application %>" />
		</c:if>
	</aui:form>
</div>

<%@ include file="/support/2/common/javascript/ticket_entry_validator_js.jspf" %>

<aui:script use="aui-base,node">
	A.all('select').each(
		function() {
			var selectedOption = this.one('option[selected]');

			if (selectedOption != null) {
				this.val(selectedOption.val());
			}
		}
	);

	var onValidate = function(e) {
		<portlet:namespace />validateRequiredField(this);
	};

	var createTicket = A.one('#<portlet:namespace />createTicket');

	createTicket.delegate('change', onValidate, 'select[data-field-required-status]');
	createTicket.delegate('keyup', onValidate, 'input[data-field-required-status], textarea[data-field-required-status]');
</aui:script>

<aui:script>
	<portlet:namespace />navSelect('newTicket');

	function <portlet:namespace />updateSupportMessage(envLFR, section) {
		var supportMessageDisplay_5_2 = AUI().one('#<portlet:namespace />support' + section + 'MessageDisplay_5_2');
		var supportMessageDisplay_6_0 = AUI().one('#<portlet:namespace />support' + section + 'MessageDisplay_6_0');
		var supportMessageDisplay_6_1 = AUI().one('#<portlet:namespace />support' + section + 'MessageDisplay_6_1');

		if ((envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_4 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_5 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_6 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_7 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_8 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_9 %>)) {
			supportMessageDisplay_5_2.show();
		}
		else {
			supportMessageDisplay_5_2.hide();
		}

		if ((envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_10 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_11 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_12 %>)) {
			supportMessageDisplay_6_0.show();
		}
		else {
			supportMessageDisplay_6_0.hide();
		}

		if ((envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_10 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_20 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_30 %>)) {
			supportMessageDisplay_6_1.show();
		}
		else {
			supportMessageDisplay_6_1.hide();
		}
	}

	function <portlet:namespace />validateFiles(field) {
		var node = null;
		var patchLevelMessage = A.one('#<portlet:namespace />patchLevelMessage');
		var portalExtMessage = A.one('#<portlet:namespace />portalExtMessage');

		if (!field || (field == 'portalExt')) {
			if (!<portlet:namespace />validateFile('portalExt')) {
				portalExtMessage.setContent('<%= UnicodeLanguageUtil.format(pageContext, "please-upload-a-x-file", "portal-ext") %>');
				portalExtMessage.show();

				node = portalExtMessage;
			}
			else if (!<portlet:namespace />validateFileConfirmed('portalExt')) {
				portalExtMessage.setContent('<%= UnicodeLanguageUtil.format(pageContext, "please-confirm-the-current-x-attachment", "portal-ext") %>');
				portalExtMessage.show();

				node = portalExtMessage;
			}
			else {
				portalExtMessage.hide();
			}
		}

		if (!field || (field == 'patchLevel')) {
			if (!<portlet:namespace />validateFile('patchLevel')) {
				patchLevelMessage.setContent('<%= UnicodeLanguageUtil.format(pageContext, "please-upload-a-x-file", "patch-level") %>');
				patchLevelMessage.show();

				node = patchLevelMessage;
			}
			else if (!<portlet:namespace />validateFileConfirmed('patchLevel')) {
				patchLevelMessage.setContent('<%= UnicodeLanguageUtil.format(pageContext, "please-confirm-the-current-x-attachment", "patch-level") %>');
				patchLevelMessage.show();

				node = patchLevelMessage;
			}
			else {
				patchLevelMessage.hide();
			}
		}

		return node;
	}
</aui:script>