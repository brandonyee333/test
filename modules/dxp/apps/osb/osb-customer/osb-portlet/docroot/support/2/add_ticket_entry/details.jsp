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
ProductEntry productEntry = (ProductEntry)request.getAttribute("add_ticket_entry.jsp-productEntry");

long accountEnvironmentId = ParamUtil.getLong(request, "accountEnvironmentId");
int component = ParamUtil.getInteger(request, "component");
String subject = ParamUtil.getString(request, "subject");
int systemStatus = ParamUtil.getInteger(request, "systemStatus");
String description = ParamUtil.getString(request, "description");
%>

<div class="ticket-detail">
	<aui:input data-field-required-status="<%= false %>" field-required-message='<%= HtmlUtil.escapeAttribute(LanguageUtil.get(request, "please-enter-a-summary-of-the-issue")) %>' label="summary" maxLength='<%= ModelHintsUtil.getMaxLength(TicketEntry.class.getName(), "subject") %>' name="subject" type="text" value="<%= subject %>" />

	<aui:field-wrapper helpMessage='<%= component != TicketEntryConstants.COMPONENT_LICENSE ? "please-try-to-be-as-specific-as-possible-when-describing-your-issue" : StringPool.BLANK %>' label="description">
		<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
			<liferay-util:param name="content" value="<%= description %>" />
			<liferay-util:param name="editorId" value="description" />
			<liferay-util:param name="fieldRequired" value="<%= Boolean.TRUE.toString() %>" />
			<liferay-util:param name="fieldRequiredMessage" value='<%= LanguageUtil.get(request, "please-enter-a-description-of-the-issue") %>' />
			<liferay-util:param name="name" value="description" />
			<liferay-util:param name="height" value='<%= (component != TicketEntryConstants.COMPONENT_LICENSE) ? "300" : "" %>' />
		</liferay-util:include>
	</aui:field-wrapper>

	<div class="component-details">
		<c:choose>
			<c:when test="<%= productEntry.isSocialOffice() %>">
				<liferay-util:include page="/support/2/add_ticket_entry/details_social_office.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test="<%= component == TicketEntryConstants.COMPONENT_CLUSTERING %>">
				<liferay-util:include page="/support/2/add_ticket_entry/details_clustering.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test="<%= component == TicketEntryConstants.COMPONENT_LICENSE %>">
				<liferay-util:include page="/support/2/add_ticket_entry/details_license.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test="<%= component == TicketEntryConstants.COMPONENT_UPGRADE %>">
				<liferay-util:include page="/support/2/add_ticket_entry/details_upgrade.jsp" servletContext="<%= application %>" />
			</c:when>
		</c:choose>
	</div>

	<aui:button-row cssClass="foot-details">
		<portlet:renderURL var="cancelURL">
			<portlet:param name="mvcPath" value="/support/2/view.jsp" />
		</portlet:renderURL>

		<aui:button cssClass="pull-left" href="<%= cancelURL.toString() %>" value="cancel" />

		<aui:button cssClass="pull-right" onClick='<%= renderResponse.getNamespace() + "submit();" %>' value="create" />
	</aui:button-row>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />submit',
		function() {
			var A = AUI();

			var firstNode = <portlet:namespace />validateFiles();

			var requiredFields = A.all('#<portlet:namespace />createTicket input[data-field-required-status="false"], #<portlet:namespace />createTicket select[data-field-required-status="false"], #<portlet:namespace />createTicket textarea[data-field-required-status="false"]');

			if (requiredFields.size()) {
				requiredFields.each(
					function(requiredField) {
						<portlet:namespace />validateRequiredField(requiredField);
					}
				);

				if (!firstNode) {
					firstNode = requiredFields.first();
				}
			}

			if (!firstNode) {
				var form = A.one('#<portlet:namespace />fm');

				if (form) {
					form.attr('encoding', 'multipart/form-data');
				}

				submitForm(document.<portlet:namespace />fm);
			}
			else {
				firstNode.scrollIntoView();
			}
		},
		['aui-base']
	);
</aui:script>