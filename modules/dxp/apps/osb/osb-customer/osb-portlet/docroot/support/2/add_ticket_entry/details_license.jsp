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

int envLFR = ParamUtil.getInteger(request, "envLFR");
int type = ParamUtil.getInteger(request, "type");
int purpose = ParamUtil.getInteger(request, "purpose");
String serverIds = ParamUtil.getString(request, "serverIds");
String ipAddresses = ParamUtil.getString(request, "ipAddresses");
String hostNames = ParamUtil.getString(request, "hostNames");
String additionalComments = ParamUtil.getString(request, "additionalComments");
%>

<aui:fieldset-group>
	<aui:fieldset label="activation-key-details" helpMessage="please-provide-accurate-activation-key-details-these-details-will-help-us-reproduce-your-issue-and-come-to-a-faster-resolution">
		<div class="clearfix">
			<div class="pull-left w45">
				<aui:select data-field-required-status="<%= false %>" field-required-message='<%= HtmlUtil.escapeAttribute(LanguageUtil.format(request, "invalid-value-provided-for-x", "type-of-key")) %>' label="type-of-key" name="type">
					<aui:option value="0" />

					<%
					int[] types = TicketEntryConstants.getLicenseTypes();

					for (int curType : types) {
					%>

						<aui:option label="<%= TicketEntryConstants.getLicenseTypeLabel(curType) %>" selected="<%= curType == type %>" value="<%= curType %>" />

					<%
					}
					%>

				</aui:select>
			</div>

			<div class="pull-right w45">
				<aui:select data-field-required-status="<%= false %>" field-required-message='<%= HtmlUtil.escapeAttribute(LanguageUtil.format(request, "invalid-value-provided-for-x", "purpose")) %>' label="purpose" name="purpose">
					<aui:option value="0" />

					<%
					int[] purposes = TicketEntryConstants.getLicensePurposes();

					for (int curPurpose : purposes) {
					%>

						<aui:option label="<%= TicketEntryConstants.getLicensePurposeLabel(curPurpose) %>" selected="<%= curPurpose == purpose %>" value="<%= curPurpose %>" />

					<%
					}
					%>

				</aui:select>
			</div>
		</div>

		<aui:field-wrapper label="server-ids-mac-addresses-optional">
			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= serverIds %>" />
				<liferay-util:param name="editorId" value="serverIds" />
				<liferay-util:param name="name" value="serverIds" />
			</liferay-util:include>
		</aui:field-wrapper>

		<aui:field-wrapper label="ip-addresses-optional">
			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= ipAddresses %>" />
				<liferay-util:param name="editorId" value="ipAddresses" />
				<liferay-util:param name="name" value="ipAddresses" />
			</liferay-util:include>
		</aui:field-wrapper>

		<aui:field-wrapper label="host-names-optional">
			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= hostNames %>" />
				<liferay-util:param name="editorId" value="hostNames" />
				<liferay-util:param name="name" value="hostNames" />
			</liferay-util:include>
		</aui:field-wrapper>
	</aui:fieldset>
</aui:fieldset-group>