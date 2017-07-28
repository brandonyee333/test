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
long offeringEntryId = ParamUtil.getLong(request, "offeringEntryId");

OfferingEntry offeringEntry = OfferingEntryLocalServiceUtil.getOfferingEntry(offeringEntryId);

ProductEntry productEntry = offeringEntry.getProductEntry();

int envLFR = ParamUtil.getInteger(request, "envLFR");
int type = ParamUtil.getInteger(request, "type");
int purpose = ParamUtil.getInteger(request, "purpose");
String serverIds = ParamUtil.getString(request, "serverIds");
String ipAddresses = ParamUtil.getString(request, "ipAddresses");
String hostNames = ParamUtil.getString(request, "hostNames");
String additionalComments = ParamUtil.getString(request, "additionalComments");
%>

<div>
	<h2 class="component-title section-heading">
		<liferay-ui:message key="activation-key-details" />
	</h2>

	<div>
		<liferay-ui:message key="please-provide-accurate-activation-key-details-these-details-will-help-us-reproduce-your-issue-and-come-to-a-faster-resolution" />
	</div>
</div>

<br />

<div class="aui-helper-clearfix">
	<div class="aui-w45 fl">
		<span class="section-heading"><liferay-ui:message key="type-of-key" />:</span>

		<select data-field-required-status="<%= false %>" field-required-message="<%= HtmlUtil.escapeAttribute(LanguageUtil.format(pageContext, "invalid-value-provided-for-x", "type-of-key")) %>" name="<portlet:namespace />type">
			<option value="0"></option>

			<%
			int[] types = TicketEntryConstants.getLicenseTypes();

			for (int curType : types) {
			%>

				<option <%= (curType == type) ? "selected" : "" %> value="<%= curType %>"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getLicenseTypeLabel(curType)) %></option>

			<%
			}
			%>

		</select>
	</div>

	<div class="aui-w45 fr">
		<span class="section-heading"><liferay-ui:message key="purpose" />:</span>

		<select data-field-required-status="<%= false %>" field-required-message="<%= HtmlUtil.escapeAttribute(LanguageUtil.format(pageContext, "invalid-value-provided-for-x", "purpose")) %>" name="<portlet:namespace />purpose">
			<option value="0"></option>

			<%
			int[] purposes = TicketEntryConstants.getLicensePurposes();

			for (int curPurpose : purposes) {
			%>

				<option <%= (curPurpose == purpose) ? "selected" : "" %> value="<%= curPurpose %>"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getLicensePurposeLabel(curPurpose)) %></option>

			<%
			}
			%>

		</select>
	</div>
</div>

<div>
	<h2 class="section-heading">
		<liferay-ui:message key="server-ids-mac-addresses-optional" />:
	</h2>

	<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
		<liferay-util:param name="content" value="<%= serverIds %>" />
		<liferay-util:param name="editorId" value="serverIds" />
		<liferay-util:param name="name" value="serverIds" />
	</liferay-util:include>
</div>

<div>
	<h2 class="section-heading">
		<liferay-ui:message key="ip-addresses-optional" />:
	</h2>

	<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
		<liferay-util:param name="content" value="<%= ipAddresses %>" />
		<liferay-util:param name="editorId" value="ipAddresses" />
		<liferay-util:param name="name" value="ipAddresses" />
	</liferay-util:include>
</div>

<div>
	<h2 class="section-heading">
		<liferay-ui:message key="host-names-optional" />:
	</h2>

	<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
		<liferay-util:param name="content" value="<%= hostNames %>" />
		<liferay-util:param name="editorId" value="hostNames" />
		<liferay-util:param name="name" value="hostNames" />
	</liferay-util:include>
</div>