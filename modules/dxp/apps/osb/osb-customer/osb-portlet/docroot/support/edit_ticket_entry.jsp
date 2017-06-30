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
String tabs1 = ParamUtil.getString(request, "tabs1", "public");

String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

if (Validator.isNull(backURL)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/support/view.jsp");
	portletURL.setWindowState(LiferayWindowState.NORMAL);

	backURL = portletURL.toString();
}

TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

long accountEntryId = BeanParamUtil.getLong(ticketEntry, request, "accountEntryId");

AccountEntry accountEntry = null;

if (accountEntryId > 0) {
	accountEntry = AccountEntryServiceUtil.getAccountEntry(accountEntryId);
}
else {
	accountEntry = ticketEntry.getAccountEntry();
}

long offeringEntryId = BeanParamUtil.getLong(ticketEntry, request, "offeringEntryId");

OfferingEntry offeringEntry = null;

if (offeringEntryId > 0) {
	offeringEntry = OfferingEntryLocalServiceUtil.getOfferingEntry(offeringEntryId);
}
else {
	offeringEntry = ticketEntry.getOfferingEntry();
}

ProductEntry productEntry = offeringEntry.getProductEntry();
SupportResponse supportResponse = offeringEntry.getSupportResponse();

int component = BeanParamUtil.getInteger(ticketEntry, request, "component");

boolean hasUpdateAdmin = OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_ADMIN);

boolean hasUpdateAdvanced = hasUpdateAdmin || OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_ADVANCED);

boolean hasUpdateBasic = hasUpdateAdvanced || OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_BASIC);

boolean closed = false;

if (ticketEntry.getStatus() == TicketEntryConstants.STATUS_CLOSED) {
	closed = true;
}

boolean edit = ParamUtil.getBoolean(request, "edit");

if (!hasUpdateAdvanced || (closed && !liferayIncOrg)) {
	edit = false;
}

boolean accountCustomer = false;

if (AccountCustomerLocalServiceUtil.hasAccountCustomer(user.getUserId(), accountEntry.getAccountEntryId())) {
	accountCustomer = true;
}

boolean partnerWorker = false;

if (accountEntry.isPartnerManagedSupport() && PartnerWorkerLocalServiceUtil.hasPartnerWorker(user.getUserId(), accountEntry.getPartnerEntryId())) {
	partnerWorker = true;
}

boolean ticketWorker = false;

if (liferayIncOrg || partnerWorker) {
	ticketWorker = true;
}

boolean supportEngineer = SupportWorkerLocalServiceUtil.hasSupportWorker(user.getUserId(), SupportWorkerConstants.ROLE_WATCHER);

boolean clockedIn = SupportWorkerLocalServiceUtil.isClockedIn(user.getUserId());

if (accountCustomer || partnerWorker || (!clockedIn && liferayIncOrg && !supportEngineer)) {
	clockedIn = true;
}

boolean canForward = false;

if ((ticketEntry.getSeverity() == SupportResponseConstants.SEVERITY_CRITICAL) && supportResponse.isPlatinumLevel() && OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.FORWARD)) {
	canForward = true;
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/edit_ticket_entry.jsp");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));
%>

<portlet:actionURL name="updateTicketEntry" var="updateTicketEntryURL">
	<portlet:param name="mvcPath" value="/support/edit_ticket_entry.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateTicketEntryURL %>" class="uni-form" enctype="multipart/form-data" method="post" name="fm1">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
	<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(backURL) %>" />
	<input name="<portlet:namespace />ticketEntryId" type="hidden" value="<%= ticketEntry.getTicketEntryId() %>" />
	<input name="<portlet:namespace />edit" type="hidden" value="<%= edit %>" />
	<input name="<portlet:namespace />body" type="hidden" value="" />
	<input name="<portlet:namespace />visibility" type="hidden" value="" />

	<div class="cleared section">
		<div class="fr">
			<a class="btn" href="<%= HtmlUtil.escapeAttribute(backURL) %>">&lt; <liferay-ui:message key="back-to-previous-page" /></a>
		</div>
	</div>

	<c:if test="<%= !clockedIn %>">
		<div class="portlet-msg-info">
			<liferay-ui:message key="you-are-not-clocked-in-and-will-not-be-assigned-tickets-to-change-this-setting-please-contact-your-support-manager" />
		</div>
	</c:if>

	<%
	request.setAttribute("edit_ticket_entry.jsp-accountCustomer", accountCustomer);
	request.setAttribute("edit_ticket_entry.jsp-accountEntry", accountEntry);
	request.setAttribute("edit_ticket_entry.jsp-canForward", canForward);
	request.setAttribute("edit_ticket_entry.jsp-clockedIn", clockedIn);
	request.setAttribute("edit_ticket_entry.jsp-closed", closed);
	request.setAttribute("edit_ticket_entry.jsp-edit", edit);
	request.setAttribute("edit_ticket_entry.jsp-hasUpdateAdmin", hasUpdateAdmin);
	request.setAttribute("edit_ticket_entry.jsp-hasUpdateAdvanced", hasUpdateAdvanced);
	request.setAttribute("edit_ticket_entry.jsp-hasUpdateBasic", hasUpdateBasic);
	request.setAttribute("edit_ticket_entry.jsp-offeringEntry", offeringEntry);
	request.setAttribute("edit_ticket_entry.jsp-partnerWorker", partnerWorker);
	request.setAttribute("edit_ticket_entry.jsp-portletURL", portletURL);
	request.setAttribute("edit_ticket_entry.jsp-productEntry", productEntry);
	request.setAttribute("edit_ticket_entry.jsp-supportResponse", supportResponse);
	request.setAttribute("edit_ticket_entry.jsp-ticketWorker", ticketWorker);
	%>

	<liferay-util:include page="/support/edit_ticket_entry/details.jsp" servletContext="<%= application %>" />

	<br />

	<liferay-util:include page="/support/edit_ticket_entry/ticket_attachments_links.jsp" servletContext="<%= application %>" />

	<liferay-util:include page="/support/edit_ticket_entry/buttons.jsp" servletContext="<%= application %>" />
</aui:form>

<c:if test="<%= canForward && !edit && clockedIn %>">
	<liferay-util:include page="/support/edit_ticket_entry/forward.jsp" servletContext="<%= application %>" />
</c:if>

<liferay-util:include page="/support/edit_ticket_entry/discussion.jsp" servletContext="<%= application %>" />

<%
StringBuilder sb = new StringBuilder();

sb.append(StringPool.OPEN_BRACKET);
sb.append(ticketEntry.getDisplayId());
sb.append(StringPool.CLOSE_BRACKET);
sb.append(StringPool.SPACE);
sb.append(ticketEntry.getSubject());

PortalUtil.setPageSubtitle(sb.toString(), request);
%>

<aui:script>
	function <portlet:namespace />closeTicket(resolution, addCommentBody) {
		var closeTicketURL = '<portlet:actionURL name="closeTicketEntry"><portlet:param name="mvcPath" value="/support/edit_ticket_entry.jsp" /></portlet:actionURL>';

		document.<portlet:namespace />fm1.<portlet:namespace />redirect.value = '<%= portletURL.toString() %>';
		document.<portlet:namespace />fm1.<portlet:namespace />resolution.value = resolution;
		document.<portlet:namespace />fm1.<portlet:namespace />body.value = addCommentBody;
		submitForm(document.<portlet:namespace />fm1, closeTicketURL);
	}

	function <portlet:namespace />escalateTicket(addCommentBody, visibility) {
		document.<portlet:namespace />fm1.<portlet:namespace />redirect.value = '<%= portletURL.toString() %>';
		document.<portlet:namespace />fm1.<portlet:namespace />body.value = addCommentBody;
		document.<portlet:namespace />fm1.<portlet:namespace />visibility.value = visibility;
		submitForm(document.<portlet:namespace />fm1, '<portlet:actionURL name="escalateTicketEntry"><portlet:param name="mvcPath" value="/support/edit_ticket_entry.jsp" /></portlet:actionURL>');
	}
</aui:script>