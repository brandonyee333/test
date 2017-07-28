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

long offeringEntryId = ParamUtil.getLong(request, "offeringEntryId");

OfferingEntry offeringEntry = OfferingEntryLocalServiceUtil.getOfferingEntry(offeringEntryId);

AccountEntry accountEntry = AccountEntryServiceUtil.getAccountEntry(offeringEntry.getAccountEntryId());

ProductEntry productEntry = offeringEntry.getProductEntry();
SupportResponse supportResponse = offeringEntry.getSupportResponse();

int systemStatus = ParamUtil.getInteger(request, "systemStatus");
String subject = ParamUtil.getString(request, "subject");
String description = ParamUtil.getString(request, "description");
int component = ParamUtil.getInteger(request, "component");
int subcomponent = ParamUtil.getInteger(request, "subcomponent");
%>

<liferay-ui:error exception="<%= TicketEntryDescriptionException.class %>" message="please-enter-a-valid-description" />
<liferay-ui:error exception="<%= TicketEntrySubcomponentException.class %>" message="please-select-a-valid-subcomponent" />
<liferay-ui:error exception="<%= TicketEntrySubjectException.class %>" message="please-enter-a-valid-subject" />
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

<input name="<portlet:namespace />accountEntryId" type="hidden" value="<%= accountEntry.getAccountEntryId() %>" />

<div class="page-heading">
	<h1>
		<liferay-ui:message key="create-a-new-ticket" />
	</h1>
</div>

<h2 class="section-heading steps">
	1. <liferay-ui:message key="verify-your-project-information" />
</h2>

<div class="callout-a">
	<div class="callout-content">
		<div>
			<span class="txt-b txt-up"><liferay-ui:message key="name" />:</span>

			<strong><%= HtmlUtil.escape(accountEntry.getName()) %></strong>
		</div>

		<br />

		<div>
			<span class="txt-b txt-up"><liferay-ui:message key="support" />:</span>

			<strong><%= HtmlUtil.escape(supportResponse.getName()) %></strong>

			<c:if test="<%= (accountEntry.getStatus() == WorkflowConstants.STATUS_CLOSED) || (accountEntry.getStatus() == WorkflowConstants.STATUS_EXPIRED) %>">
				<span class="expired-text">(<liferay-ui:message key="expired" />)</span>
			</c:if>
		</div>

		<br />

		<div>
			<span class="txt-b txt-up"><liferay-ui:message key="product" />:</span>

			<strong><%= HtmlUtil.escape(productEntry.getName()) %></strong>
		</div>

		<br />

		<c:if test="<%= productEntry.isTicketComponentRequired() %>">
			<div>
				<span class="txt-b txt-up"><liferay-ui:message key="component" />:</span>

				<strong><liferay-ui:message key="<%= TicketEntryConstants.getComponentLabel(component) %>" /></strong>

				<input name="<portlet:namespace />component" type="hidden" value="<%= component %>" />
			</div>

			<br />
		</c:if>

		<div>
			<input class="aui-button-input" onClick="<portlet:namespace />changeSummary();" type="button" value="<liferay-ui:message key="change" />" />
		</div>
	</div>
</div>

<h2 class="section-heading steps">
	2. <liferay-ui:message key="describe-your-issue" />
</h2>

<div class="callout-a">
	<div class="callout-content">
		<aui:input bean="<%= null %>" inlineLabel="left" label="provide-a-summary" model="<%= TicketEntry.class %>" name="subject" />

		<br />

		<c:if test="<%= component == TicketEntryConstants.COMPONENT_LIFERAY_CONNECTED_SERVICES %>">
			<div>
				<span class="txt-b"><liferay-ui:message key="select-a-subcomponent" /></span>

				<select name="<portlet:namespace />subcomponent">
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

		<br />

		<div>
			<c:choose>
				<c:when test="<%= productEntry.isSocialOffice() %>">
					<span class="txt-b"><liferay-ui:message key="select-a-system-status-optional" /></span>
				</c:when>
				<c:otherwise>
					<span class="txt-b"><liferay-ui:message key="select-a-system-status" /></span>
				</c:otherwise>
			</c:choose>

			<select name="<portlet:namespace />systemStatus">
				<option value=""></option>

				<%
				int[] systemStatuses = TicketEntryConstants.getSystemStatuses(component);

				for (Integer curSystemStatus : systemStatuses) {
				%>

					<option <%= (curSystemStatus == systemStatus) ? "selected" : "" %> value="<%= curSystemStatus %>"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getSystemStatusLabel(curSystemStatus)) %></option>

				<%
				}
				%>

			</select>
		</div>
	</div>
</div>

<div class="callout-a">
	<div class="callout-content">
		<span class="txt-b"><liferay-ui:message key="please-provide-a-description-of-the-issue" /></span>

		<c:if test="<%= component != TicketEntryConstants.COMPONENT_LICENSE %>">
			<br />

			<liferay-ui:message key="please-try-to-be-as-specific-as-possible-when-describing-your-issue" />
		</c:if>

		<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
			<liferay-util:param name="content" value="<%= description %>" />
			<liferay-util:param name="editorId" value="description" />
			<liferay-util:param name="name" value="description" />
			<liferay-util:param name="height" value='<%= (component != TicketEntryConstants.COMPONENT_LICENSE) ? "300" : "" %>' />
			<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.TRUE) %>" />
		</liferay-util:include>
	</div>
</div>

<div class="component-details">
	<c:choose>
		<c:when test="<%= component == TicketEntryConstants.COMPONENT_CLUSTERING %>">
			<liferay-util:include page="/support/add_ticket_entry/details_clustering.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test="<%= component == TicketEntryConstants.COMPONENT_LICENSE %>">
			<liferay-util:include page="/support/add_ticket_entry/details_license.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test="<%= component == TicketEntryConstants.COMPONENT_UI %>">
			<liferay-util:include page="/support/add_ticket_entry/details_ui.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test="<%= component == TicketEntryConstants.COMPONENT_UPGRADE %>">
			<liferay-util:include page="/support/add_ticket_entry/details_upgrade.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:otherwise>
			<liferay-util:include page="/support/add_ticket_entry/details_generic.jsp" servletContext="<%= application %>" />
		</c:otherwise>
	</c:choose>
</div>

<br />

<div>
	<input class="aui-button-input" onClick="javascript:<portlet:namespace />submit();" type="button" value="<liferay-ui:message key="submit" />" />

	<input class="aui-button-input" onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
</div>

<aui:script>
	function <portlet:namespace />changeSummary() {
		submitForm(document.<portlet:namespace />fm, '<portlet:renderURL><portlet:param name="mvcPath" value="/support/add_ticket_entry.jsp" /><portlet:param name="step" value="summary" /></portlet:renderURL>');
	}

	function <portlet:namespace />submit() {
		<c:if test="<%= component != TicketEntryConstants.COMPONENT_LICENSE %>">
			if (!<portlet:namespace />validateGeneric()) {
				return false;
			}
		</c:if>

		document.<portlet:namespace />fm.encoding="multipart/form-data";

		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />updateSupportMessage(envLFR) {
		var supportMessageDisplay_5_2 = AUI().one('#<portlet:namespace />supportMessageDisplay_5_2');
		var supportMessageDisplay_6_0 = AUI().one('#<portlet:namespace />supportMessageDisplay_6_0');
		var supportMessageDisplay_6_1 = AUI().one('#<portlet:namespace />supportMessageDisplay_6_1');

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
</aui:script>