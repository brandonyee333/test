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
TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

Map<Long, String> ticketInformationFieldsMap = ticketEntry.getTicketInformationFieldsMap();

int envLFR = ParamUtil.getInteger(request, "envLFR", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_LFR)));
int type = ParamUtil.getInteger(request, "type", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_LICENSE_TYPE)));
int purpose = ParamUtil.getInteger(request, "purpose", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_LICENSE_PURPOSE)));
String serverIds = ParamUtil.getString(request, "serverIds", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_SERVER_IDS)));
String ipAddresses = ParamUtil.getString(request, "ipAddresses", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_IP_ADDRESSES)));
String hostNames = ParamUtil.getString(request, "hostNames", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_HOST_NAMES)));
String additionalComments = ParamUtil.getString(request, "additionalComments", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ADDITIONAL_COMMENTS)));
%>

<div class="clearfix">
	<liferay-util:include page="/support/2/common/eosl_environment_liferay.jsp" servletContext="<%= application %>">
		<portlet:param name="envLFR" value="<%= String.valueOf(envLFR) %>" />
	</liferay-util:include>

	<div class="content-column w33">
		<div class="content-column-content left-column">
			<span class="txt-sb">
				<liferay-ui:message key="type-of-key" />:
			</span>

			<liferay-ui:message key="<%= TicketEntryConstants.getLicenseTypeLabel(type) %>" />
		</div>
	</div>

	<div class="content-column w33">
		<div class="content-column-content left-column">
			<span class="txt-sb">
				<liferay-ui:message key="purpose" />:
			</span>

			<liferay-ui:message key="<%= TicketEntryConstants.getLicensePurposeLabel(purpose) %>" />
		</div>
	</div>
</div>

<br />

<div>
	<span class="txt-sb"><liferay-ui:message key="server-ids-mac-addresses" />:</span>

	<pre><%= SupportUtil.getHTML(serverIds) %></pre>
</div>

<br />

<div>
	<span class="txt-sb"><liferay-ui:message key="ip-addresses" />:</span>

	<pre><%= SupportUtil.getHTML(ipAddresses) %></pre>
</div>

<br />

<div>
	<span class="txt-sb"><liferay-ui:message key="host-names" />:</span>

	<pre><%= SupportUtil.getHTML(hostNames) %></pre>
</div>

<br />

<div>
	<span class="txt-sb"><liferay-ui:message key="additional-comments-unstable-server-details-multiple-jvms-etc" />:</span>

	<pre><%= SupportUtil.getHTML(additionalComments) %></pre>
</div>