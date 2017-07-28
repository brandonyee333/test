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
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

if (Validator.isNull(backURL)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/support/view.jsp");
	portletURL.setWindowState(LiferayWindowState.NORMAL);

	backURL = portletURL.toString();
}

String step = ParamUtil.getString(request, "step");

long accountEntryId = ParamUtil.getLong(request, "accountEntryId");
long offeringEntryId = ParamUtil.getLong(request, "offeringEntryId");
long supportRegionId = ParamUtil.getLong(request, "supportRegionId");
long accountEnvironmentId = ParamUtil.getLong(request, "accountEnvironmentId");
int component = ParamUtil.getInteger(request, "component");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/add_ticket_entry.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
%>

<portlet:actionURL name="updateTicketEntry" var="updateTicketEntryURL">
	<portlet:param name="mvcPath" value="/support/add_ticket_entry.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateTicketEntryURL %>" method="post" name="fm">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
	<input name="<portlet:namespace />offeringEntryId" type="hidden" value="<%= offeringEntryId %>" />
	<input name="<portlet:namespace />supportRegionId" type="hidden" value="<%= supportRegionId %>" />
	<input name="<portlet:namespace />accountEnvironmentId" type="hidden" value="<%= accountEnvironmentId %>" />

	<div class="cleared section">
		<div class="fr">
			<a class="btn" href="<%= HtmlUtil.escapeAttribute(backURL) %>">&lt; <liferay-ui:message key="back-to-previous-page" /></a>
		</div>
	</div>

	<%
	boolean ticketComponentRequired = true;

	if (offeringEntryId > 0) {
		OfferingEntry offeringEntry = OfferingEntryLocalServiceUtil.getOfferingEntry(offeringEntryId);

		ProductEntry productEntry = offeringEntry.getProductEntry();

		request.setAttribute("add_ticket_entry.jsp-productEntry", productEntry);

		if (!productEntry.isTicketComponentRequired()) {
			ticketComponentRequired = false;
		}
	}
	%>

	<c:choose>
		<c:when test='<%= ((component == 0) && ticketComponentRequired) || step.equals("summary") %>'>
			<liferay-util:include page="/support/add_ticket_entry/summary.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:otherwise>
			<liferay-util:include page="/support/add_ticket_entry/details.jsp" servletContext="<%= application %>" />
		</c:otherwise>
	</c:choose>
</aui:form>

<aui:script>
	AUI().ready(
		function(A) {
			A.all('select').each(
				function() {
					var selectedOption = this.one('option[selected]');

					if (selectedOption != null) {
						this.val(selectedOption.val());
					}
				}
			);
		}
	);
</aui:script>